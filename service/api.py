import hug
import vision
from handlers import *

@hug.post("/isAuthorized") 
def isAuthorized():
  return "it fuckin works!"

