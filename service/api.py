import hug

@hug.post("/isAuthorized") 
def isAuthorized():
  return "it fuckin works!"

