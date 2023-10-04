# SSOY STORY BY YangCompany
- main branch : 0.0.1 (Now)
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

## 🧾개요

web3.0 SNS 을 만들기 위한 토대로써, web2 기반입니다.
사용자 마다 사용자의 페이지가 주어졌으며 앞으로 해당 공간을 탈중앙화 관리 예정입니다.
각 사용자는 자신의 페이지를 기반을 다른 사용자의 페이지를 방문하고 소통할 수 있으며, 오픈 월드에서 각자의 아바타가 네트워크에서 소통을 할 수 있습니다.

## 👨‍👦‍👦개발 기간 및 개발 인원

08.18 ~ 09.05 JSP&REACT&SB 0.0.1 버전 완료    
08.25 ~  FE : REACT,PHASER / BE : Spring Boot , GIN 1.0.1 버전 개발중

soyoung0420 - PM,DBA,Dev(Be,Fe)   
GRyu1 - PL,AA,DBA,Dev(Be,Fe)   
ejum01 - DBA,Dev(Be,Fe)      
eunc823 - DBA,Dev(Be,Fe)      
hongseungyoung - DBA,Dev(Be,Fe)      
joohyee - DBA,Dev(Be,Fe)      
username98865 - DBA,Dev(Be,Fe)      

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
- JAVA 11, Spring Boot 2.7.14
- Spring Security
- MyBatis
- Lombok
- STOMP
  
- GO 1.24
- GIN
- WebSocket

### Build Tool
- Gradle

### Data Base
- Oracle 21C
- Mongo DB
- Redis
- Amazon S3

## 💿아키텍쳐
- Project
 ![파이널_쏘이스토리_최종발표-001](https://github.com/z352YangComapny/yangOffice/assets/125647772/12a96cda-7185-41dc-bbb0-efc94e357e06)
- Spring Server
 ![파이널_쏘이스토리_최종발표-002](https://github.com/z352YangComapny/yangOffice/assets/125647772/c7e41629-149c-4211-92c0-d7546fa0a3fc)
- GIN Server
  ![파이널_쏘이스토리_최종발표-003](https://github.com/z352YangComapny/yangOffice/assets/125647772/4defba48-07f0-404b-bb50-e9c738510989)

## 🔑E-R 다이어그램
- https://www.erdcloud.com/d/29Wk3hSMH9Nn4KmyF
![image](https://github.com/z352YangComapny/yangOffice/assets/125647772/4e71cdfe-0c0c-4340-9703-4d182fb1d712)
## 💎Api 명세서
- 준비중
## 🌏OVERVIEW
|INDEX|
|:---:|
|![image](https://github.com/z352YangComapny/yangOffice/assets/125647772/e43f07b1-1259-470d-aa21-07b3e96c4b07)|
|SIGN IN|
|![image](https://github.com/z352YangComapny/yangOffice/assets/125647772/834683a9-64f1-47f6-8125-4239b3b40071)|
|SIGN UP|
|![image](https://github.com/z352YangComapny/yangOffice/assets/125647772/fda8826e-af27-40ec-b419-d1190644ca96)|   
|FEED|
|![image](https://github.com/z352YangComapny/yangOffice/assets/125647772/0049c987-655e-428e-9705-41e8d145fb09)|   
|USER|
|![image](https://github.com/z352YangComapny/yangOffice/assets/125647772/c9c9d4ce-6832-4848-9567-d0631c209805)|
|WORLD|
|![image](https://github.com/z352YangComapny/yangOffice/assets/125647772/b979390c-021a-415d-86e8-a0d1079cae5b)|

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
