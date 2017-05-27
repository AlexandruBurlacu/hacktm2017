# -*- coding: utf-8 -*-

import hug
import vision
from handlers import *

vision.init()

@hug.post("/isAuthorized") 
def isAuthorized(image1, image2):

    return vision.areSimilar(image1, image2)

