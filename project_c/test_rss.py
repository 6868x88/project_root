import feedparser

rss_url = "https://news.sbs.co.kr/news/SectionRssFeed.do?sectionId=01&plink=RSSREADER"



feed = feedparser.parse(
    rss_url,
    agent="Mozilla/5.0 (Windows NT 10.0; Win64; x64)"
)

print("entries 개수:", len(feed.entries))

for entry in feed.entries[:5]:
    print(entry.title)
    print(entry.link)
    print(entry.summary)