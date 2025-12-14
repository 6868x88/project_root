from fastapi import FastAPI
import feedparser
from news_service import crawl_article, summarize_text

app = FastAPI(title="News Summary API")

@app.get("/")
def health_check():
    return {"status": "ok"}

@app.get("/news/summary")
def summarize_news(limit: int = 5):
    rss_url = "https://news.sbs.co.kr/news/SectionRssFeed.do?sectionId=01&plink=RSSREADER"
    feed = feedparser.parse(rss_url)

    results = []

    for entry in feed.entries[:limit]:
        article = crawl_article(entry.link)
        summary = summarize_text(article["text"])

        results.append({
            "title": article["title"],
            "url": entry.link,
            "summary": summary
        })

    return {
        "count": len(results),
        "articles": results
    }
