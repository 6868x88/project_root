import feedparser
import requests
import re
from newspaper import Article
# from summa.summarizer import summarize


# 뉴스 수집(뉴스 원문 크롤링) 
def crawl_article(url: str):
    article = Article(url, language='ko')
    article.download()
    article.parse()
    return {
        "title" : article.title,
        "text" : article.text
        }
    
# 기사 대표 이미지 추출
def extract_image_url(soup):
    og = soup.select_one("meta[property='og:image']")
    if og and og.get("content"):
        return og["content"].strip()

    img = soup.select_one("article img")
    if img and img.get("src"):
        return img["src"].strip()

    return None
  

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
def summarize_text(text: str):
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
    return limit_summary(sentences)

# 영상 기사 스킵
def is_invalid_article(text: str) -> bool:
    if not text:
        return True

    if "video 태그를 지원하지 않습니다" in text:
        return True

    if len(text.strip()) < 100:
        return True

    return False
