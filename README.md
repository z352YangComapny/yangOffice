# SSOY STORY BY YangCompany
- PPT TO INTRODUCE @ https://www.miricanvas.com/v/12cduwe
- OVERVIEW CLIP 0.0.1 @ https://youtu.be/62z0jJcMNn4?si=xdt1M4hXqY3ZOdN1

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

### 🧾개요

web3.0 SNS 을 만들기 위한 토대로써, web2 기반입니다.
사용자 마다 사용자의 페이지가 주어졌으며 앞으로 해당 공간을 탈중앙화 관리 예정입니다.
각 사용자는 자신의 페이지를 기반을 다른 사용자의 페이지를 방문하고 소통할 수 있으며, 오픈 월드에서 각자의 아바타가 네트워크에서 소통을 할 수 있습니다.
팀 프로젝트로, Project Leader , Project Manager , Architecture Design , Back , DB , Front 를 맡았습니다.

### 👨‍👦‍👦개발 기간 및 개발 인원

08.18 ~ 09.05 JSP&SB 0.0.1 버전 완료    
08.25 ~  FE : REACT,PHASER / BE : SB,GIN 1.0.1 버전 개발중

soyoung0420 - PM,FE,BE,DBE   
GRyu1 - PL,AE,BE,FE,DBE   
ejum01 - BE,DBE   
eunc823 - BE,DBE   
hongseungyoung - BE,DBE   
joohyee - BE,DBE   
username98865 - BE,DBE   

### 💻개발 환경
- windows10
- windows11
- MacOS
- Intelli J Ultimate
- STS3 & STS4
- VS code
- Go Land
- Mongo COMPASS ,REDIS INSIGHT , SQLDEVELOPER

### 💾사용기술

#### Front End
- JS React.js 18
- BootStrap5

#### Back End
- JAVA 11, Spring Boot 2.7.14
- Spring Security
- MyBatis
- Lombok
- STOMP
  
- GO 1.24
- GIN
- WebSocket

#### Build Tool
- Gradle

#### Data Base
- Oracle 21C
- Mongo DB
- Redis
- Amazon S3

### 💿아키텍쳐
- Project
 ![파이널_쏘이스토리_최종발표-001](https://github.com/z352YangComapny/yangOffice/assets/125647772/12a96cda-7185-41dc-bbb0-efc94e357e06)
- Spring Server
 ![파이널_쏘이스토리_최종발표-002](https://github.com/z352YangComapny/yangOffice/assets/125647772/c7e41629-149c-4211-92c0-d7546fa0a3fc)
- GIN Server
  ![파이널_쏘이스토리_최종발표-003](https://github.com/z352YangComapny/yangOffice/assets/125647772/4defba48-07f0-404b-bb50-e9c738510989)

### 🔑E-R 다이어그램
- https://www.erdcloud.com/d/29Wk3hSMH9Nn4KmyF
![image](https://github.com/z352YangComapny/yangOffice/assets/125647772/4e71cdfe-0c0c-4340-9703-4d182fb1d712)
### 💎Api 명세서
- 준비중
### OVERVIEW
|INDEX|SIGN IN|SIGN UP|FEED|
|:---:|:---:|:---:|:---:|
|내용 9|내용 10|내용 11|내용 12|   


|WORLD|USER|READY FOR UPDATE|READY FOR UPDATE|
|:---:|:---:|:---:|:---:|
|내용 5|내용 6|내용 7|내용 8|

### 👓프로젝트 목적
'Dev-Grow' 성장함에 따라 얻은 지식 및 기술스택을 적용해보고, 소통 할 수 있는 공간을 만들어갑니다.
### 🔎핵심 기능
로그인 , 회원가입 , 소셜로그인
프로필 설정 - 개인의 사진 , 소개말 , 상태를 표현 할 수 있는 기능   
Follower / Followee - 다른 사용자를 팔로우 할수 있음.   
사진피드 - 사진 첨부가 되는 게시판 CRUD   
방명록 , 댓글 , QNA - CRUD   
DM - webSocket / 사용자의 접속 상태를 알려주며, 1:1 메세지 기능  
STORY - webSocket / 24시간만 지속되는 문자열 상태알림 기능
