import cognitive_face as CF

if __name__ == "__main__":
  KEY = "5c7b666fc17f4b91b762981ce533d4f9"
  CF.Key.set(KEY)

  BASE_URL = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/"
  CF.BaseUrl.set(BASE_URL)

  img_url = "https://scontent.xx.fbcdn.net/v/t34.0-12/18762501_1228727593891558_429387755_n.jpg?oh=090cf35f9d3867d72d2edc2e381ee6d5&oe=592BDE31"
  result = CF.face.detect(img_url)

  print (result)
