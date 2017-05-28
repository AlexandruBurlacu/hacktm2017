import pygame.camera
import pygame.image
import time

pygame.camera.init()
cam = pygame.camera.Camera(pygame.camera.list_cameras()[0])


frames = 1
while (frames <= 1):
	cam.start()
	img = cam.get_image()
	pygame.image.save(img, "photo"+str(frames)+".jpg")
	pygame.camera.quit()
	cam.stop()
	# time.sleep(1)
	frames = frames + 1
	

import requests
url = 'http://92.222.66.166:3000/validate_driver'
files = {'photo': open('photo1.jpg', 'rb')}
requests.put(url, files=files)