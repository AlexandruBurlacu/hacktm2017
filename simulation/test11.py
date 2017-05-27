import requests
url = 'http://file.api.wechat.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE'
files = {'media': open('photo1.bmp', 'rb')}
requests.post(url, files=files)