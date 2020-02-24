import numpy as np
import socket
import argparse
import subprocess
import cv2
import csv
import os
import time
from datetime import datetime
from subprocess import check_output

# construct the argument parse and parse the arguments
ap = argparse.ArgumentParser()
ap.add_argument("-u", "--user", required=True, help="user name")
ap.add_argument("-c", "--confidence", type=float, default=0.1, help="minimum probability to filter weak detections")
ap.add_argument("-t", "--threshold", type=float, default=0.1, help="threshold when applyong non-maxima suppression")
args = vars(ap.parse_args())

# derive the paths to the YOLO weights and model configuration and load the COCO class labels our YOLO model was trained on
PATH="C:\\Users\\HENRY\\Desktop\\yolo-object-detection"
weightsPath = PATH + "\\yolo-fruits\\darknet-yolov3_final.weights"
configPath  = PATH + "\\yolo-fruits\\darknet-yolov3.cfg"
labelsPath  = PATH + "\\yolo-fruits\\classes.names"
imgPath     = PATH + "\\images\\" + args["user"] + ".jpg"
csvPath     = PATH + "\\output\\" + args["user"] + ".csv"

os.system("IF EXIST {}\\output\\*.jpg (del {}\\output\\*.jpg)".format(PATH, PATH))
os.system("IF EXIST {} (del {})".format(csvPath, csvPath))
os.system("type nul > {}".format(csvPath))

# socket for RPi connection
client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(('192.168.137.10', 65432))
client.send(args["user"].encode('utf-8'))

flag = bool(client.recv(1024).decode())

client.close()

if flag: 

	# initialize a list of colors to represent each possible class label
	np.random.seed(42)
	LABELS = open(labelsPath).read().strip().split("\n")
	COLORS = np.random.randint(0, 255, size=(len(LABELS), 3), dtype="uint8")

	# load our YOLO object detector trained on COCO dataset (80 classes)
	net = cv2.dnn.readNetFromDarknet(configPath, weightsPath)

	# load our input image and grab its spatial dimensions
	image = cv2.imread(imgPath)
	(H, W) = image.shape[:2]

	# determine only the *output* layer names that we need from YOLO
	ln = net.getLayerNames()
	ln = [ln[i[0] - 1] for i in net.getUnconnectedOutLayers()]

	# construct a blob from the input image and then perform a forward pass of the YOLO object detector, 
	# giving us our bounding boxes and associated probabilities
	blob = cv2.dnn.blobFromImage(image, 1 / 255.0, (416, 416), swapRB=True, crop=False)
	net.setInput(blob)
	layerOutputs = net.forward(ln)

	# initialize our lists of detected bounding boxes, confidences, and class IDs, respectively
	boxes = []
	confidences = []
	classIDs = []

	# loop over each of the layer outputs
	for output in layerOutputs:

		# loop over each of the detections
		for detection in output:

			# extract the class ID and confidence (i.e., probability) of the current object detection
			scores = detection[5:]
			classID = np.argmax(scores)
			confidence = scores[classID]

			# filter out weak predictions by ensuring the detected probability is greater than the minimum probability
			if confidence > args["confidence"]:
				# scale the bounding box coordinates back relative to the size of the image, keeping in mind that YOLO actually
				# returns the center (x, y)-coordinates of the bounding box followed by the boxes' width and height
				box = detection[0:4] * np.array([W, H, W, H])
				(centerX, centerY, width, height) = box.astype("int")

				# use the center (x, y)-coordinates to derive the top and left corner of the bounding box
				x = int(centerX - (width / 2))
				y = int(centerY - (height / 2))

				# update our list of bounding box coordinates, confidences, and class IDs
				boxes.append([x, y, int(width), int(height)])
				confidences.append(float(confidence))
				classIDs.append(classID)

	# apply non-maxima suppression to suppress weak, overlapping bounding boxes
	idxs = cv2.dnn.NMSBoxes(boxes, confidences, args["confidence"], args["threshold"]) # numpy.ndarray

	date = datetime.now().strftime("%Y/%m/%d")
	time = datetime.now().strftime("%H:%M:%S")

	# ensure at least one detection exists
	if len(idxs) > 0:

		# loop over the indexes we are keeping
		for i in idxs.flatten():

			# extract the bounding box coordinates
			(x, y) = (boxes[i][0], boxes[i][1])
			(w, h) = (boxes[i][2], boxes[i][3])

			# draw a bounding box rectangle and label on the image
			color = [int(c) for c in COLORS[classIDs[i]]]
			text = "{}: {:.4f}".format(LABELS[classIDs[i]], confidences[i])

			#cv2.rectangle(image, (x, y), (x + w, y + h), color, 2)	
			cv2.putText(image, text, (x, y + 15), cv2.FONT_HERSHEY_SIMPLEX, 0.5, color, 2)
			cv2.imwrite("{}\\output\\{}_{}.jpg".format(PATH, LABELS[classIDs[i]].strip(), i), image[y:y+h, x:x+w])

			with open(csvPath, "a", newline="") as csvfile:
				w = csv.writer(csvfile, delimiter=",")
				w.writerow([i, LABELS[classIDs[i]], confidences[i], date, time])
