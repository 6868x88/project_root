import feedparser
from news_service import crawl_article, summarize_text, is_invalid_article
from send_to_spring import send_news_to_spring
from datetime import datetime


RSS_URL = "https://www.yonhapnewstv.co.kr/browse/feed/"


def collect_news():
    news_list = []

    feed = feedparser.parse(RSS_URL)

    for entry in feed.entries:
        url = entry.link

        try:
            article = crawl_article(url)
            summary = summarize_text(article["text"])
            
            if is_invalid_article(article["text"]):
                print("영상/비정상 기사 스킵:", url)
                continue

            news = {
                "title": article["title"],
                "url": url,
                "content": article["text"],
                "summary": summary
            }

            news_list.append(news)

        except Exception as e:
            print("기사 처리 실패:", url)
            print(e)

    return news_list


def main():
    print("===================================")
    print("뉴스 자동 수집 시작")
    print(datetime.now())
    print("===================================")

    news_list = collect_news()

    for news in news_list:
        send_news_to_spring(news)

    print("===================================")
    print("뉴스 자동 수집 종료")
    print(datetime.now())
    print("===================================")


if __name__ == "__main__":
    main()
