# -*- coding: utf-8 -*-

import hug
import vision
from handlers import *

vision.init()

@hug.post("/isAuthorized") 
def isAuthorized(image1, image2):

  with open("temp_img1.jpg", "wb") as img1, \
       open("temp_img2.jpg", "wb") as img2:
    img1.write(image1)
    img2.write(image2)
  
    return vision.areSimilar(img1, img2)

