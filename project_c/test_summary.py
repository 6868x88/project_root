""" 
RSS → 원문 기사 → 직접 가공(사진/캡션 제거, 문단 → 문장) → 정책 기반 요약
:원문을 직접 수정해 문장 단위로 분리하고, 서비스 정책에 맞게 직접 생성하도록 구현
""" 

import feedparser
import requests
import re
from newspaper import Article
from summa.summarizer import summarize




# 뉴스 수집(뉴스 원문 크롤링)
def crawl_article(url: str):
    article = Article(url, language='ko')
    article.download()
    article.parse()
    return {
        "title" : article.title,
        "text" : article.text
        }

# 사진/캡션 제거
def clean_text(text: str):
    text = re.sub(r'▲.*?\n', '', text)
    text = re.sub(r'\(사진=.*?\)', '', text)
    return text.strip()

# 한국어 뉴스 문장 분리
def split_korean_sentences(text: str):
    # 1단계: 줄바꿈 기준으로 문단 분리
    paragraphs = re.split(r'\n+', text)

    sentences = []
    for para in paragraphs:
        para = para.strip()
        if len(para) < 20:
            continue

        # 2단계: 마침표 기준 문장 분리
        parts = re.split(r'(?<=\.)', para)
        for p in parts:
            p = p.strip()
            if len(p) >= 20:
                sentences.append(p)

    return sentences


# 요약 길이 제한
def limit_summary(sentences, max_sentences=2, max_chars=250):
    result = []
    total_len = 0

    for s in sentences:
        if total_len + len(s) > max_chars:
            break
        result.append(s)
        total_len += len(s)
        if len(result) >= max_sentences:
            break

    return " ".join(result)


# 요약
def summarize_txt(text: str):
    if not text or len(text) < 50:
        return "[본문 수집 실패]"

    text = clean_text(text)
    sentences = split_korean_sentences(text)

    if not sentences:
        return "[본문 수집 실패]"

    # 문장이 1개뿐이면 그 문장이 요약
    if len(sentences) == 1:
        return sentences[0]

    # 정상 요약
    return limit_summary(sentences, max_sentences=2, max_chars=250)

# 메인
def main():
    rss_url = "https://news.sbs.co.kr/news/SectionRssFeed.do?sectionId=01&plink=RSSREADER"
    feed = feedparser.parse(rss_url)

    print("RSS entries:", len(feed.entries))

    for entry in feed.entries[:5]:
        print("=" * 60)
        print("기사 URL:", entry.link)

        article = crawl_article(entry.link)
        summary = summarize_txt(article["text"])

        print("제목:", article["title"])
        print("요약:", summary)

if __name__ == "__main__":
    main()