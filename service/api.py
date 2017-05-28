# -*- coding: utf-8 -*-

import hug
import vision
from io import BytesIO
from handlers import *

vision.init()

@hug.post("/isAuthorized") 
def isAuthorized(image1, image2):
    """Currently it requires links to the images.
    """

    response = vision.areSimilar(BytesIO(image1), BytesIO(image2))

    if not response["isIdentical"]:
      handler = HandleUnauthorized()
    else:
      handler = HandleAuthorized()

    handler.handle(image1)

    return response

