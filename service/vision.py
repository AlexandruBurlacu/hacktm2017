import requests

ENDPOINT = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0"
KEY1     = "5c7b666fc17f4b91b762981ce533d4f9"
KEY2     = "be5494e0f8df4b0e9ffa61982b0b72a2"
RETRIES  = 3

def processRequest( json, data, headers, params ):
  """
    Helper function to process the request to Project Oxford
    Parameters:
      json: Used when processing images from its URL. See API Documentation
      data: Used when processing image read from disk. See API Documentation
      headers: Used to pass the key information and the data type request
  """
  retries = 0
  result = None
  while True:
    response = requests.request("post", ENDPOINT,
                                 json = json, data = data,
                                 headers = headers, params = params)
    if response.status_code == 429: 

      print( "Message: %s" % (response.json()["error"]["message"]))

      if retries <= RETRIES: 
        time.sleep(1) 
        retries += 1
        continue
      else: 
        print("Error: failed after retrying!")
        break

    elif response.status_code == 200 or response.status_code == 201:
      if 'content-length' in response.headers and int(response.headers['content-length']) == 0: 
        result = None 
      elif 'content-type' in response.headers and isinstance(response.headers['content-type'], str):
        if 'application/json' in response.headers['content-type'].lower():
          result = response.json() if response.content else None
        elif 'image' in response.headers['content-type'].lower(): 
          result = response.content
    else:
      print("Error code: %d" % (response.status_code))
      print("Message: %s" % (response.json()["error"]["message"]))
    break
    
  return result
