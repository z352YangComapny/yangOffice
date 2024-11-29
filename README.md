# SSOY STORY BY YangCompany
- main branch : 1.1.1 (Now)
- PPT TO INTRODUCE @ [link](https://docs.google.com/presentation/d/1sHi8_4CFx_fAg2eUbYid4TlB3IMoedKbA80q9g94jyw/edit?usp=sharing)
- OVERVIEW CLIP 0.0.1 @ https://youtu.be/62z0jJcMNn4?si=xdt1M4hXqY3ZOdN1

- 아키택쳐 변경중 Monololithic A -> MSA
- Spring Cloud, Spring Kafka , Zoo keeper(or Kraft)

## 📖목차
- 개요
- 개발환경
- 사용기술
- 아키택쳐
- ERD
- API명세서
- Release Note
- 프로젝트 목적
- 화면 구성
- 핵심 기능

## 🧾개요

web3.0 SNS 을 만들기 위한 토대로써, web2 기반입니다.
사용자 마다 사용자의 페이지가 주어졌으며 앞으로 해당 공간을 탈중앙화 관리 예정입니다.
각 사용자는 자신의 페이지를 기반을 다른 사용자의 페이지를 방문하고 소통할 수 있으며, 오픈 월드에서 각자의 아바타가 네트워크에서 소통을 할 수 있습니다.

## 👨‍👦‍👦개발 기간 및 개발 인원

08.18 ~ 09.05 JSP&REACT&SB 0.0.1 버전 7인 개발 완료    
soyoung0420 - PM,DBA,Dev(Be,Fe)   
GRyu1 - PL,AA,DBA,Dev(Be,Fe)   
ejum01 - DBA,Dev(Be,Fe)      
eunc823 - DBA,Dev(Be,Fe)      
hongseungyoung - DBA,Dev(Be,Fe)      
joohyee - DBA,Dev(Be,Fe)      
username98865 - DBA,Dev(Be,Fe)      

08.25 ~ 1.04 1인 개발완료



## 💻개발 환경
- windows10
- windows11
- MacOS
- Intelli J Ultimate
- STS3 & STS4
- VS code
- Go Land
- Mongo COMPASS ,REDIS INSIGHT , SQLDEVELOPER

## 💾사용기술

### Front End
- JS React.js 18
- BootStrap5
- JSP
- HTML
- CSS
- JS

### Back End
- JAVA 17, Spring Boot 3.1.4
- Spring Cloud
- JPA
- MySQL
- S3
  
- GO 1.24
- GIN
- WebSocket

### Build Tool
- Gradle

### Data Base
- MySQL
- Mongo DB
- Redis
- Amazon S3

## 💿아키텍쳐
 - ![image](https://github.com/z352YangComapny/yangOffice/assets/125647772/8986eeaf-36ce-465d-9aa6-1adcac982189)

## 🔑E-R 다이어그램
- https://www.erdcloud.com/d/29Wk3hSMH9Nn4KmyF
![image](https://github.com/z352YangComapny/yangOffice/assets/125647772/fc1d03e0-17fe-4753-b32e-438588c72f29)

## 💎Api 명세서
- 준비중
## 🌏OVERVIEW
|INDEX|
|:---:|
|![image](https://github.com/z352YangComapny/yangOffice/assets/125647772/6d5f1677-706e-4947-9c1c-7491f3566f53)|
|FEED|
|![image](https://github.com/z352YangComapny/yangOffice/assets/125647772/66469348-5aa4-4fc1-9d52-cfe9929adf40)|   
|USER|
|![image](https://github.com/z352YangComapny/yangOffice/assets/125647772/6558ec8b-a427-42fa-9dcb-fd61a23fdc3d)|
|WORLD|
|![image](https://github.com/z352YangComapny/yangOffice/assets/125647772/33f7514e-608f-4065-975f-6d83244ff1c3)|

## 👓프로젝트 목적
'Dev-Grow' 성장함에 따라 얻은 지식 및 기술스택을 적용해보고, 소통 할 수 있는 공간을 만들어갑니다.
## 🔎핵심 기능
로그인 , 회원가입 , 소셜로그인
프로필 설정 - 개인의 사진 , 소개말 , 상태를 표현 할 수 있는 기능.   
Follower / Followee - 다른 사용자를 팔로우 할수 있음.   
사진피드 - 사진 첨부가 되는 게시판 CRUD.   
방명록 , 댓글 , QNA - CRUD.   
DM - webSocket / 사용자의 접속 상태를 알려주며, 1:1 메세지 기능.  
STORY - webSocket / 24시간만 지속되는 문자열 상태알림 기능.
WORLD - webSocket / 실시간 움직임 및 채팅 기능.
