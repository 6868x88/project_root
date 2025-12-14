from newspaper import Article
from summa.summarizer import summarize

url = "https://www.inven.co.kr/webzine/news/?news=273743"

article = Article(url, language="ko")
article.download()
article.parse()

text = article.text

print("원문 길이:", len(text))

summary = summarize(text, ratio=0.3)

print("\n요약 결과:")
print(summary)
