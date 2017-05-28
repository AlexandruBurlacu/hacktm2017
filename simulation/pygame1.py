import pygame.camera
import pygame.image
import time

pygame.camera.init()
cam = pygame.camera.Camera(pygame.camera.list_cameras()[0])


frames = 1
while (frames <= 2):
	cam.start()
	img = cam.get_image()
	pygame.image.save(img, "photo"+str(frames)+".bmp")
	pygame.camera.quit()
	cam.stop()
	time.sleep(1)
	frames = frames + 1
	

import requests
url = 'http://10.37.95.16:3000/validate_driver'
files = {'media': open('photo1.bmp', 'rb')}
requests.put(url, files=files)