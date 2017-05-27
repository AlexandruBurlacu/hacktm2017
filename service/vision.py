from cognitive_face import Key, BaseUrl
from cognitive_face.face import detect, verify
import json

def init():
  with open("config.json") as fp:
    config = json.load(fp)

  key = config["key"]
  Key.set(key)

  url = config["url"]
  BaseUrl.set(url)

def areSimilar(face1, face2):

  get_face_id = lambda objs: str(objs[0]["faceId"])

  return verify(get_face_id(detect(face1)),
                get_face_id(detect(face2)))


if __name__ == "__main__":
  init() 

  face1 = open("resources/iura1.jpg", "rb")
  face2 = open("resources/iura2.jpg", "rb")

  print(areSimilar(face1, face2))
