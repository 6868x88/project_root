import requests
import json

SPRING_API_URL = "http://localhost:8080/api/news"

def send_news_to_spring(news: dict):
    reponse = requests.post(
        SPRING_API_URL,
        headers={"Content-Type": "application/json"},
        data=json.dumps(news),
        timeout=5
    )
    
    if reponse.status_code != 200:
        print("전송실패: ",reponse.status_code, reponse.text)
    else:
        print("전송 성공")