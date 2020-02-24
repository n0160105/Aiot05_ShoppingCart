
import socket
import pko_sftp as pko
import time
from time import sleep
from picamera import PiCamera

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
server.bind(('', 65432))
server.listen(5)

while True:

	print('waiting for connect...')

	connect, (host, port) = server.accept()
	user = connect.recv(1024).decode("utf-8")

	local = '/home/pi/Desktop/{}.jpg'.format(user)
	remote = 'C:\\Users\\HENRY\\Desktop\\yolo-object-detection\\images\\{}.jpg'.format(user)

	with PiCamera() as camera:
		camera.resolution = (640, 480)
		time.sleep(1)
		camera.capture(local)

	connection = pko.get_conn('192.168.137.1', 'HENRY')
	pko.upload(connection, local, remote)

	connect.send(b'True')
	connect.close()
