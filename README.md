<h1> 💌️ Le(cue)* </h1>

> 함께 꾸미며 ✨최애✨에게 진심을 전하는 롤링페이퍼 서비스

<img src="https://github.com/Team-Lecue/Lecue-Server/assets/67463603/77faaad4-1dc0-49ff-a19c-71f07e52b9ac" width="300"/>

<hr>

## 💌️ 큐버(레큐 서버) 소개

|                                                                이동섭 🔥                                                                 |                                                                김동휘 ⚡️                                                                 |                                                                현예진 ❄️️                                                                |
|:-------------------------------------------------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------:|
| <img src="https://github.com/Team-Lecue/Lecue-Server/assets/67463603/67cf5665-ab6c-4eb0-8fa2-85a6dd8188b6" width="200" height="200"/> | <img src="https://github.com/Team-Lecue/Lecue-Server/assets/67463603/76b91fd6-3589-4c74-9b70-2b6e251ac384" width="200" height="200"/> | <img src="https://github.com/Team-Lecue/Lecue-Server/assets/67463603/25641e5f-9e28-4852-9458-380313c953cc" width="200" height="200"/> |
|                                               [ddongseop](https://github.com/ddongseop)                                               |                                                [dong2ast](https://github.com/dong2ast)                                                |                                                [eeddiinn](https://github.com/eeddiinn)                                                |

## 💌️️ 역할 분담

| 챌린징 요소 | 담당자 |
| --- | --- |
| Presigned URL (S3 Management) | 이동섭🔥 |
| Social Login (OpenFeign, Spring Security, Redis) | 이동섭🔥 |
| CI/CD Script 작성 (Github Actions) | 이동섭🔥 |
| AOP Logger 구현 + Discord 서버 알림 구현 | 이동섭🔥 |
| 서버 부하 테스트 (Jmeter) | 이동섭🔥 |
| Soft Delete (RDS Management) | 예진❄️ |
| 비속어 필터링 (Bad Word Filtering 라이브러리) | 예진❄️ |
| Architecture (Docker Compose, Nginx) | 동휘⚡️ |
| Status Dashboard 구축 (Netdata, Portainer, Sentry) | 동휘⚡️ |
| Deployment including React Project | 동휘⚡️ |
| 무중단 배포 구현 | 동휘⚡️ |
| 이미지 캐시 서버 구축  | 동휘⚡️ |


<hr>

## 💌 Tech Stack

| Category         | Used                          |
|------------------|-------------------------------|
| IDE              | Intellij                      |
| Java version     | Java 17                       |
| Spring version   | 3.1.7                         |
| Cloud Computing  | AWS EC2 (Ubuntu 22.04 LTS)    |
| Database         | AWS RDS (MySQL 8.0.33), Redis |
| Build Management | Gradle                        |
| CI/CD            | Github Actions, Docker, Nginx |
| Monitoring       | Netdata, Portainer            |
| API Test         | PostMan, Swagger              |

## 💌 Architecture

<img width="1000" alt="image" src="https://github.com/Team-Lecue/Lecue-Server/assets/67463603/6e94699e-9061-450e-bf3e-fee17333302d">

## 💌 Structure

🗂 `Domain Package Structure` & 🏛️`Facade Pattern`

```
🗂 lecueServer
├── 🗂 domain
│    ├── 📝 book
│    │    ├── controller
│    │    ├── dto
│    │    ├── 🏛 facade
│    │    ├── model
│    │    ├── repository
│    │    └── service
│    ├── 💗 common
│    ├── 🎁 member
│    ├── 💌 note
│    └── 🎨 sticker
└── 🗂 global
     ├── 🔒 auth
     │    ├── fegin
     │    │   └── kakao
     │    ├── jwt
     │    ├── redis
     │    └── security
     ├── 📦 common
     │    ├── dto
     │    └── model
     ├── 🔧 config
     ├── 🚨 exception
     │    ├── enums
     │    └── model
     └── 📷 s3
          ├── controller
          ├── dto
          ├── enums
          └── service
```

<hr>

## 💌 Convention Docs

### 🔗 [레버들은 어떻게 일할까..? 브랜치 전략 등 바로 보러 가기!](https://rileybyeon.notion.site/Backend-Convention-809638273ff2424a83e1182632f6aada?pvs=4)

## 💌 ERD

🎨 `sticker`와 📝 `book`은 `JPA의 연관관계 매핑`을 사용하지 않음 ❌

<img width="630" alt="image" src="https://github.com/Team-Lecue/Lecue-Server/assets/67463603/7daab7a7-0a5b-4af3-9929-474723d972cd">
