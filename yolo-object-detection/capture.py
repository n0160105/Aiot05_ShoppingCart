import socket
import os

PATH="C:\\Users\\User\\Desktop\\yolo-object-detection"
os.system("IF EXIST {}\\output\\QR.jpg (del {}\\output\\QR.jpg)".format(PATH, PATH))

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.settimeout(5)
client.connect(('192.168.137.10', 65432))
client.close()