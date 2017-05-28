import pyfcm
import json

class HandleUnauthorized:
  with open("config.json") as fp:
    config = json.load(fp)["firebase"]

  def __init__(self):
    self.push_service = pyfcm.FCMNotification(api_key=config["apiKey"])

  def handle(self, image):
    message_title = "Un-authorized access into your car"
    message_body = "Hi Peter, it look like someone un-authorized tries to break into your car."
    result = self.push_service.notify_single_device(registration_id = """eWtfcgoxcO0:APA91bEFUXPC5pIU0Y4Ofwy
                                                                  Sp8gKyRRvyfxFHU1ZxA8xzu8yHUzoWwcjXHG
                                                                  -hmFlwETzdtOKHoGQvVQdSaYt4fy4P3-hHy_Hc
                                                                  jRkrgK9F7mzf3Qb1V5rqtepqBvm-y4flryTjP-QC9Cw""",
                                               message_title = message_title,
                                               message_body = message_body)

if __name__ == '__main__':

  with open("config.json") as fp:
    config = json.load(fp)["firebase"]

  print(config)

  push_service = pyfcm.FCMNotification(api_key="AAAAcZK_ysc:APA91bHSc_MoZqca0ihYaMxM8QCsXUSmeQhPVPXkLqPUt0t0_aUUi0SC9hBV3I1bOGoX_3peuHJT3r7ZU2-K4EOjOGC-9o8KBvuTDIWqrwwrzDOVoA-Q9RdTdR2g5Os628y0_EKr2jbI")

  data_message = {
    "some": "fukery"
  }

  message_title = "Un-authorized access into your car"
  message_body = "Hi Peter, it look like someone un-authorized tries to break into your car."
  result = push_service.notify_single_device(registration_id = "ecDm6e9IIB4:APA91bEQbkqj7ftS1Z-KIclvrld81U6TKvd6Q0G42rmQzjCINnIR3dVymX3GFvwoXFCmQi4_Z2Ur01bXo1Mm0Yo0FOfcLvH7Hr_Pnk4sg0Jg-X4Fcj4_DNiZxaQJo7Jq9mF9FZYerlcB",
                                             message_title = message_title,
                                             message_body = message_body,
                                             data_message = data_message)