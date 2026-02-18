# SpringBoot + Python Project
## 📰 개인화 뉴스 요약 플랫폼

<br>
<img src = "https://github.com/user-attachments/assets/35a49ccb-be8e-4b69-97d7-30312e0c1829" width="90%" />
<br>
<br>

```
Python과 Java(Spring)를 연동하여 뉴스 기사를 수집·요약하고 API 형태로 제공하는 개인화 뉴스 요약 플랫폼입니다. 
뉴스 요약 로직은 Python으로 구현하고, 메인 백엔드는 Spring Boot에서 해당 서비스를 호출하는 구조로 설계했습니다.
```
<br>
👇 프로그램 개발 및 기능 설명은 아래 링크에서 확인하실 수 있습니다. 
(pdf 링크)<br>
(https://6868x88.github.io/project_root/RecoNews.pdf)

***


### 💻 프로그램
- 개요 
 - FastAPI를 활용한 뉴스 크롤링 엔진과 Spring Boot 기반의 사용자 관리 시스템이 결합된 뉴스 서비스 플랫폼
- 개발기간 
 - 2025/12 ~ 2026/02 (개인 프로젝트, 주 2~3회 진행)
<br>

### 🏗️ 시스템 아키텍처
Python → Spring REST API → MySQL

#### 기술 스택
>**Backend & API**
- Spring Boot: RESTful API 설계 및 비즈니스 로직 구현
- Spring Data JPA: 객체 지향적 쿼리 및 MySQL 데이터베이스 연동
>**Frontend**
- TypeScript / HTML5 / CSS3: 타입 안정성을 확보한 사용자 UI/UX 구현  
>**Data Processing (Crawling)**
- Python 3.x: 뉴스 데이터 수집 및 전처리 파이프라인 구축
- Newspaper3k & Feedparser: 뉴스 본문 자동 추출 및 RSS 피드 파싱  
>**Database & Storage**
- MySQL: 사용자 정보 및 뉴스 메타데이터 저장
- HttpSession: 사용자 세션 기반의 인증 및 로그인 상태 관리
 
<br>

### 📃 기능
#### 1. 뉴스 수집
- RSS 피드를 통해 최신 뉴스 URL 수집
- 언론사 기사 원문 크롤링

#### 2. 기사 전처리
- 사진 캡션, 불필요한 메타 정보 제거
- 문단 → 문장 단위 분리

#### 3. 정책 기반 요약
- 최대 2문장 / 250자 이내 요약
- 요약 불가 시 첫 문장 fallback 적용
- 항상 요약 결과를 반환하도록 설계

#### 4. Python 뉴스 요약 API
- FastAPI 기반 REST API 제공

#### 5. Spring ↔ Python 연동
- Spring Boot에서 Python API 호출
- JSON 응답을 DTO로 매핑
- 외부 서비스 장애 대비 fallback 처리


