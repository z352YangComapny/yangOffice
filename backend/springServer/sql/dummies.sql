-- @member 테이블에 예시 데이터 삽입
-- admin 유저 삽입
INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'admin', '관리자', '$2a$10$aQNQv/pTTOgHvdV5NWWp0uW3IPkUU2QmXvV8Ln/wL7U9dkWziT6yq', 'Admin', TO_DATE('1990-01-01', 'YYYY-MM-DD'), 'M', '01012345678', 'admin@example.com', 'YANG');

-- user1부터 순서대로 삽입
INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user1', '소영', '$2a$10$Hz57HYjADUXDgY.8Ps1ew.2rVtSf4.VLs1PmwsJ9H9KkcPnglD5m.', 'YANG', TO_DATE('1990-05-15', 'YYYY-MM-DD'), 'F', '01012309078', 'john@example.com', 'YANG');

INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user2', '윤아', '$2a$10$eiCAKSJ4yXTrrEGbonM7mepdiyjjaVWcuJtejtBUfbUPIr6ma7poK', 'dbsdk1', TO_DATE('1985-08-21', 'YYYY-MM-DD'), 'F', '01098765432', 'jane@example.com', 'YANG');

INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user3', '주혜', '$2a$10$sPwNsSVSCE6AqpiFyU0BJ.VFIblev4eurceOXoi0r1ybMgcMG.GNW', 'Joohyee', TO_DATE('1995-02-10', 'YYYY-MM-DD'), 'F', '01055556666', 'michael@example.com', 'KAKAO');

INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user4', '건룡', '$2a$10$gx93699zWaLIrC.m796u4OInR0XooA5CKb/VmSQGimxTdxAUC0MQG', 'gryu1', TO_DATE('1992-11-30', 'YYYY-MM-DD'), 'M', '01044445555', 'emily@example.com', 'KAKAO');

INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user5', '경빈', '$2a$10$DwnkBiihttdzEzfWZ0YL.eaaAfbiIrBjOJ9PEIABWEImgwPRAoDoG', 'ejum1', TO_DATE('1988-07-07', 'YYYY-MM-DD'), 'M', '01077778888', 'daniel@example.com', 'GOOGLE');

INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user6', '종호', '$2a$10$CPNRmK4/pqum5XBbPictweWJBkGJtKjbXV9DfmL.kaundxM8of.0C', 'bae', TO_DATE('1997-04-25', 'YYYY-MM-DD'), 'M', '01022223333', 'olivia@example.com', 'NAVER');

INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user7', '승영', '$2a$10$qIRxGB.2x0/GID5nlfnXXOkYq8bLQ4P/rdIyhBulIyRWkQre.09Y2', 'tlfghlrl1', TO_DATE('1991-09-17', 'YYYY-MM-DD'), 'M', '01066669999', 'william@example.com', 'YANG');

INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user8', '소피아', '$2a$10$xekfbl/jfR2jsyMfprZsA.4kMb3bcikCpuyYJT2raaqXSg5SP5dCy', 'SJ', TO_DATE('1987-03-12', 'YYYY-MM-DD'), 'F', '01011112222', 'sophia@example.com', 'YANG');

INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user9', '제임슨', '$2a$10$81HTJd3kZypGW81rCea17uPo1ZQut/Pndv8Qj0eiTKfT4UF6G4X02', 'JA', TO_DATE('1994-12-02', 'YYYY-MM-DD'), 'M', '01099990000', 'james@example.com', 'KAKAO');

INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user10', '미아', '$2a$10$l/A0h1jPd.6dCdr4fQqmJO5whZmoKCGzOfWEr/AflS9JNUZSyrNcW', 'MMii', TO_DATE('1996-06-08', 'YYYY-MM-DD'), 'F', '01055556677', 'mia@example.com', 'GOOGLE');

--=============================================================================
-- @authorities 테이블에 예시 데이터 삽입
INSERT ALL
    INTO authorities (member_id, authority)
VALUES (1, 'ROLE_ADMIN')
INTO authorities (member_id, authority)
VALUES (1, 'ROLE_USER')
INTO authorities (member_id, authority)
VALUES (1, 'ROLE_MANAGER')

INTO authorities (member_id, authority)
VALUES (2, 'ROLE_USER')

INTO authorities (member_id, authority)
VALUES (3, 'ROLE_USER')

INTO authorities (member_id, authority)
VALUES (4, 'ROLE_USER')

INTO authorities (member_id, authority)
VALUES (5, 'ROLE_USER')

INTO authorities (member_id, authority)
VALUES (6, 'ROLE_USER')

INTO authorities (member_id, authority)
VALUES (7, 'ROLE_USER')

INTO authorities (member_id, authority)
VALUES (8, 'ROLE_USER')

INTO authorities (member_id, authority)
VALUES (9, 'ROLE_USER')

INTO authorities (member_id, authority)
VALUES (10, 'ROLE_USER')

INTO authorities (member_id, authority)
VALUES (11, 'ROLE_USER')
SELECT 1 FROM DUAL;

--=============================================================================
-- @Story

INSERT INTO story (id, writer_id, content, reg_date) VALUES( seq_story_id.NEXTVAL, 2, '오늘 점심 뭐머글까요?', SYSDATE - DBMS_RANDOM.VALUE(1, 36) / 24);
INSERT INTO story (id, writer_id, content, reg_date) VALUES( seq_story_id.NEXTVAL, 4, '너무 덥당 오늘.....', SYSDATE - DBMS_RANDOM.VALUE(1, 36) / 24);
INSERT INTO story (id, writer_id, content, reg_date) VALUES( seq_story_id.NEXTVAL, 3, '노래 추천해주세요', SYSDATE - DBMS_RANDOM.VALUE(1, 36) / 24);
INSERT INTO story (id, writer_id, content, reg_date) VALUES( seq_story_id.NEXTVAL, 5, '집에 있는데도 배달 음식 시켜먹는 내 모습....하..', SYSDATE - DBMS_RANDOM.VALUE(1, 36) / 24);
INSERT INTO story (id, writer_id, content, reg_date) VALUES( seq_story_id.NEXTVAL, 6, '오늘은 팀원들과 온라인 프로젝트 회의를 했어용', SYSDATE - DBMS_RANDOM.VALUE(1, 36) / 24);
INSERT INTO story (id, writer_id, content, reg_date) VALUES( seq_story_id.NEXTVAL, 7, '넷플릭스 재밌는거 없나?', SYSDATE - DBMS_RANDOM.VALUE(1, 36) / 24);
INSERT INTO story (id, writer_id, content, reg_date) VALUES( seq_story_id.NEXTVAL, 9, '학원가기싫다 ㅋ', SYSDATE - DBMS_RANDOM.VALUE(1, 36) / 24);
INSERT INTO story (id, writer_id, content, reg_date) VALUES( seq_story_id.NEXTVAL, 8, '오늘도 거울을 보면서 잘생긴나의 외모에 감탄한다.', SYSDATE - DBMS_RANDOM.VALUE(1, 36) / 24);
INSERT INTO story (id, writer_id, content, reg_date) VALUES( seq_story_id.NEXTVAL, 10, '엽떡 먹고 싶당', SYSDATE - DBMS_RANDOM.VALUE(1, 36) / 24);
INSERT INTO story (id, writer_id, content, reg_date) VALUES( seq_story_id.NEXTVAL, 11, '아싸 금요일', SYSDATE - DBMS_RANDOM.VALUE(1, 36) / 24);

--=============================================================================
-- @Comments 테이블 테스트 데이터
-- 게시판 댓글
INSERT INTO comments (id, writer_id, content, reg_date) VALUES( seq_comments_id.NEXTVAL, 1, '계정 비활성화 기능은 따로 없습니다. 회원 탈퇴만 가능합니다.', SYSDATE - 2);
INSERT INTO comments (id, writer_id, content, reg_date) VALUES( seq_comments_id.NEXTVAL, 1, '로그인 화면에 비밀번호찾기 서비스를 이용해주세요.', SYSDATE - 2);
INSERT INTO comments (id, writer_id, content, reg_date) VALUES( seq_comments_id.NEXTVAL, 1, '광고 게시는 1회당 10만원입니다 ^^. 고객센터 문의 바랍니다.', SYSDATE - 2);
INSERT INTO comments (id, writer_id, content, reg_date) VALUES( seq_comments_id.NEXTVAL, 1, '잘 관리 하세요', SYSDATE - 2);
INSERT INTO comments (id, writer_id, content, reg_date) VALUES( seq_comments_id.NEXTVAL, 1, '공지사항 확인 바랍니다. 자꾸 이러면 경찰에 신고합니다.', SYSDATE - 2);

-- 피드 댓글
INSERT INTO comments (id, writer_id, content, reg_date) VALUES( seq_comments_id.NEXTVAL, 3, '너무 기대돼요!!!!!!', SYSDATE - 2);
INSERT INTO comments (id, writer_id, content, reg_date) VALUES( seq_comments_id.NEXTVAL, 4, '사전예약 가능한가요?', SYSDATE - 2);
INSERT INTO comments (id, writer_id, content, reg_date) VALUES( seq_comments_id.NEXTVAL, 5, '버전2는 언제나오나요?', SYSDATE - 2);
INSERT INTO comments (id, writer_id, content, reg_date) VALUES( seq_comments_id.NEXTVAL, 6, '사진만 봐도 너무 시원해요!!', SYSDATE - 2);
INSERT INTO comments (id, writer_id, content, reg_date) VALUES( seq_comments_id.NEXTVAL, 2, '너무 귀여워', SYSDATE - 2);
INSERT INTO comments (id, writer_id, content, reg_date) VALUES( seq_comments_id.NEXTVAL, 3, '그쵸? ㅎㅎ', SYSDATE - 1);
INSERT INTO comments (id, writer_id, content, reg_date) VALUES( seq_comments_id.NEXTVAL, 5, '요즘 MZ 따라가기 힘들다...', SYSDATE - 2);
INSERT INTO comments (id, writer_id, content, reg_date) VALUES( seq_comments_id.NEXTVAL, 4, '이런 감성 너무 좋아요!!', SYSDATE - 2);
INSERT INTO comments (id, writer_id, content, reg_date) VALUES( seq_comments_id.NEXTVAL, 8, '맛있겠다 ...', SYSDATE - 2);
INSERT INTO comments (id, writer_id, content, reg_date) VALUES( seq_comments_id.NEXTVAL, 7, '디자인이 옛날감성 자극하네요.', SYSDATE - 2);
INSERT INTO comments (id, writer_id, content, reg_date) VALUES( seq_comments_id.NEXTVAL, 6, '진짜 짜증나네요. 신고합니다.', SYSDATE - 2);
INSERT INTO comments (id, writer_id, content, reg_date) VALUES( seq_comments_id.NEXTVAL, 4, '치즈가 너무 맛있었어요!!', SYSDATE - 2);

--=============================================================================
-- @Question 테이블 테스트 데이터

-- Q 타입 데이터
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 2, '이용문의', '이용문의는 여기에 작성하면 되나요?', 'Q', SYSDATE - 14);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 2, '계정 복구에 관한 문의', '계정 비밀번호를 분실했어요.. 복구 방법 알려주세요.', 'Q', SYSDATE - 13);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 3, '컨텐츠 신고 문의합니다.', '부적절한 컨텐츠가 있는데 어떻게 신고하나요?', 'Q', SYSDATE - 12);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 4, '광고 게시 문의입니다.', '광고 게시를 위한 요금과 방법 알려주세요.', 'Q', SYSDATE - 11);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 5, '개인정보', '개인정보 관리는 어떻게하나요?', 'Q', SYSDATE - 10);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 6, '계정 문의요', '계정 공개/비공개 설정이 가능한가요?', 'Q', SYSDATE - 9);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 7, '앱 기능 문의', '앱에서도 할수있나요?', 'Q', SYSDATE - 8);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 8, '알림 기능 문의', '특정 사용자의 댓글과 좋아요 알림 설정이 가능한가요?', 'Q', SYSDATE - 7);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 9, '팔로워 문의요', '팔로워 목록을 숨기는 방법이 있나요?', 'Q', SYSDATE - 6);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 10, '쏘이스토리 일하는거 맞냐?', '고객센터 왜 전화 안받음?', 'Q', SYSDATE - 5);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 4, '계정 비활성화되나요?', '제곧내입니다.', 'Q', SYSDATE - 4);

-- Q타입 댓글 데이터
INSERT INTO comments_question (comments_id, question_id) VALUES (1, 11);
INSERT INTO comments_question (comments_id, question_id) VALUES (2, 2);
INSERT INTO comments_question (comments_id, question_id) VALUES (3, 4);
INSERT INTO comments_question (comments_id, question_id) VALUES (4, 5);
INSERT INTO comments_question (comments_id, question_id) VALUES (5, 10);

-- N 타입 데이터
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 1, '긴급 공지사항 08.29-09.05', '긴급한 사항으로 인해 서비스 이용에 불편을 드릴 수 있습니다. 양해 부탁드립니다.', 'N', SYSDATE - 14);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 1, '시스템 개선 작업 안내', '서비스의 안정성을 위한 시스템 개선 작업이 진행 중입니다.', 'N', SYSDATE - 13);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 1, '고객센터 연락처 변경 안내', '고객센터 연락처가 변경되어 사용자님들의 궁금증을 빠르게 해결해 드릴 수 있습니다.', 'N', SYSDATE - 12);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 1, '신규 기능 추가 소식', '도토리로 프로필을 꾸밀 수 있는 새로운 기능이 추가되어 더욱 향상된 서비스를 경험하실 수 있습니다.', 'N', SYSDATE - 11);


--=============================================================================
-- @GuestBook 테이블 테스트 데이터
-- 1시간을 3600초로 변환하였습니다.
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 2, 3, '너무 멋진 사진을 공유해주셔서 감사합니다. 잘봤어요!!', SYSDATE - 7200);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 3, 4, '추천해주신 책 읽었는데 너무 좋았어요. 감사합니다.', SYSDATE - 10800);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 6, 2, '항상 좋은 음악 추천해주셔서 감사해요!!', SYSDATE - 14400);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 5, 2, '우리 팀장 최고당.', SYSDATE - 18000);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 6, 3, '소통해요!!', SYSDATE - 21600);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 7, 2, '쏘이스토리 마니 사랑해주세요', SYSDATE - 25200);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 8, 2, '들렀다갑니다 ㅎㅎ', SYSDATE - 28800);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 9, 2, '출석체크 하고가요!!', SYSDATE - 32400);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 10, 4, '마우스 상품명이 궁금해요.. 귀여운 마우스', SYSDATE - 36000);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 11, 5, '당신의 코딩능력에 감탄하고갑니다.', SYSDATE - 39600);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 8, 5, '백준문제 너무 잘푸셔서 감동이에요...', SYSDATE - 43200);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 2, 5, '혹시 코딩 강의는 따로안하시나요?', SYSDATE - 46800);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 3, 5, '오늘도 잘 보고갑니다!! 열코딩!!', SYSDATE - 50400);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 4, 5, '이렇게 읽기 쉬운 코드는 난생 처음이에요;;..', SYSDATE - 54000);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 5, 8, '하이하잉~ 들렀다가요', SYSDATE - 57600);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 6, 7, '맞방명록 쓰러 왔습니당^^', SYSDATE - 61200);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 7, 6, '출첵하고가용 잘봤습니다!!', SYSDATE - 64800);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 8, 9, '피드가 너무 갬성적이에요!!', SYSDATE - 68400);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 4, 2, '안녕하세요. 여행을 사랑하는 사람들의 모임 여사모 매니저입니다. 혹시 피드에 부산 사진을 사용해도 될까요??', SYSDATE - 72000);

--=============================================================================
-- @photo_feed table 테이블 테스트 데이터
-- 1
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 2, '쏘이스토리 9월5일 시작 !!!', SYSDATE - INTERVAL '1' DAY);

-- 2
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 2, '여름 바다 좋다 ~', SYSDATE - INTERVAL '2' DAY);

-- 3
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 3, '치라미에요', SYSDATE - INTERVAL '3' DAY);

-- 4
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 2, '요즘 유행하는 갬성샷', SYSDATE - INTERVAL '4' DAY);

-- 5
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 2, '오늘 저녁메뉴!!', SYSDATE - INTERVAL '5' DAY);

-- 6
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 5, '나는 지금 아무생각이 없다..', SYSDATE - INTERVAL '6' DAY);

-- 7
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 7, '지하철 막힌다..', SYSDATE - INTERVAL '7' DAY);

-- 8
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 8, '퇴근후 수육국밥에 혼술^^', SYSDATE - INTERVAL '8' DAY);

-- 9
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 9, '오늘 먹은것들 !!', SYSDATE - INTERVAL '9' DAY);

-- 10
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 8, '강동원 뺨치는 나', SYSDATE - INTERVAL '10' DAY);

-- 11
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 2, '양컴퍼니 회식 ~', SYSDATE - INTERVAL '11' DAY);


-- @Comments_feed 포토피드 댓글 테스트 데이터

INSERT INTO comments_feed (comments_id, photo_feed_id) VALUES (6, 1);
INSERT INTO comments_feed (comments_id, photo_feed_id) VALUES (7, 1);
INSERT INTO comments_feed (comments_id, photo_feed_id) VALUES (8, 1);
INSERT INTO comments_feed (comments_id, photo_feed_id) VALUES (9, 2);
INSERT INTO comments_feed (comments_id, photo_feed_id) VALUES (10, 3);
INSERT INTO comments_feed (comments_id, photo_feed_id) VALUES (11, 3);
INSERT INTO comments_feed (comments_id, photo_feed_id) VALUES (12, 4);
INSERT INTO comments_feed (comments_id, photo_feed_id) VALUES (13, 4);
INSERT INTO comments_feed (comments_id, photo_feed_id) VALUES (14, 5);
INSERT INTO comments_feed (comments_id, photo_feed_id) VALUES (15, 1);
INSERT INTO comments_feed (comments_id, photo_feed_id) VALUES (16, 10);
INSERT INTO comments_feed (comments_id, photo_feed_id) VALUES (17, 7);


-- @LIKES 포도피드 좋아요 테스트 데이터
INSERT INTO likes (photo_feed_id, member_id) VALUES(1, 3);
INSERT INTO likes (photo_feed_id, member_id) VALUES(1, 4);
INSERT INTO likes (photo_feed_id, member_id) VALUES(1, 5);
INSERT INTO likes (photo_feed_id, member_id) VALUES(1, 6);
INSERT INTO likes (photo_feed_id, member_id) VALUES(2, 3);
INSERT INTO likes (photo_feed_id, member_id) VALUES(3, 5);
INSERT INTO likes (photo_feed_id, member_id) VALUES(3, 6);
INSERT INTO likes (photo_feed_id, member_id) VALUES(10, 6);
INSERT INTO likes (photo_feed_id, member_id) VALUES(10, 2);
INSERT INTO likes (photo_feed_id, member_id) VALUES(8, 3);
INSERT INTO likes (photo_feed_id, member_id) VALUES(7, 2);
INSERT INTO likes (photo_feed_id, member_id) VALUES(7, 5);
INSERT INTO likes (photo_feed_id, member_id) VALUES(4, 7);
INSERT INTO likes (photo_feed_id, member_id) VALUES(4, 5);

--=============================================================================
-- @attachment table 테이블 테스트 데이터
-- photofeed attachment 테이블 테스트 데이터
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file1.png', '20230828_164622436_614.png', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file2.png', 'summer.jpg', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file3.png', '20230829_155126456_263.jpg', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file4.png', '20230827_180658955_142.png', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file5.png', 'food.jpg', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file6.png', '20230830_113852857_110.jpg', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file7.png', 'subway.jpg', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file8.png', 'suyuk.jpg', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file9.png', 'pasta.jpg', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file10.png', '강동원.jpg', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file11.png', '29pub.jpg', SYSDATE);

-- profile attachment 테이블 테스트 데이터
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file12.png', '김용명.jpg', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file13.png', '프로필사진.jpg', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file14.png', '프로필사진2.jpg', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file15.png', '프로필사진3.jpg', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file16.png', '프로필사진4.jpg', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file17.png', '개.jpg', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file18.png', '안경고양이.jpg', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file19.png', '마스크팩고양이.jpg', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file20.png', '프로필사진5.jpg', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file21.png', '박진영.jpg', SYSDATE);
INSERT INTO attachment (id, original_filename, renamed_filename, reg_date) VALUES (seq_attachment_id.NEXTVAL, 'original_file22.png', '피글렛인가.jpg', SYSDATE);


--=============================================================================
-- @attachment_photo_feed table 테이블 테스트 데이터
INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id) VALUES (1, 1);
INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id) VALUES (2, 2);
INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id) VALUES (3, 3);
INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id) VALUES (4, 4);
INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id) VALUES (5, 5);
INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id) VALUES (6, 6);
INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id) VALUES (7, 7);
INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id) VALUES (8, 8);
INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id) VALUES (9, 9);
INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id) VALUES (10, 10);
INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id) VALUES (11, 11);

--=============================================================================
-- @profile table 테이블 테스트 데이터
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 1, 'A', '관리자 계정입니다만?.');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 2, 'B', '양팀장이에요');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 3, 'C', '뭘 쳐다봐 두질래');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 4, 'D', '주혜의 집에 놀러온걸 환영합니다.');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 5, 'E', '코딩천재입니다.');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 6, 'A', '안녕하세요 얼굴천재 경빈이에요');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 7, 'B', '안녕하세요.');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 8, 'C', '고수');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 9, 'D', '안녕하세요. 소피아입니다.');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 10, 'E', '안녕 와따시와 제임슨데스.');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 11, 'A', '미아월드에 오신걸 환영해용');

-- @attachment_profile 프로필에 어태치먼트 연결
insert into attachment_profile (attachment_id, profile_id) values (12, 1);
insert into attachment_profile (attachment_id, profile_id) values (13, 2);
insert into attachment_profile (attachment_id, profile_id) values (14, 3);
insert into attachment_profile (attachment_id, profile_id) values (15, 4);
insert into attachment_profile (attachment_id, profile_id) values (16, 5);
insert into attachment_profile (attachment_id, profile_id) values (17, 6);
insert into attachment_profile (attachment_id, profile_id) values (18, 7);
insert into attachment_profile (attachment_id, profile_id) values (19, 8);
insert into attachment_profile (attachment_id, profile_id) values (20, 9);
insert into attachment_profile (attachment_id, profile_id) values (21, 10);
insert into attachment_profile (attachment_id, profile_id) values (22, 11);

--=============================================================================
-- @follow table 테이블 테스트 데이터

-- 멤버 간의 팔로우 관계
insert into follow values (SEQ_FOLLOW_ID.nextval,2, 6, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,2, 5, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,2, 3, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,2, 4, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,3, 4, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,3, 5, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,4, 2, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,5, 6, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,3, 6, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,5, 3, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,6, 3, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,6, 5, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,5, 2, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,3, 2, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,6, 2, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,7, 2, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,2, 7, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,11, 3, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,3, 11, default);

-- 멤버 간의 팔로우 관계가 아닌 경우 (추가 20개)
insert into follow values (SEQ_FOLLOW_ID.nextval,2, 8, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,8, 3, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,3, 10, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,10, 2, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,10, 5, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,7, 4, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,7, 5, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,3, 7, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,9, 5, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,9, 2, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,2, 9, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,3, 9, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,10, 7, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,4, 9, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,4, 10, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,5, 11, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,11, 4, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,9, 11, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,8, 9, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,9, 10, default);
insert into follow values (SEQ_FOLLOW_ID.nextval,10, 11, default);

--=============================================================================
-- @dm_room dummies

-- 첫 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 6, 2, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 두 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 2, 3, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 세 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 3, 4, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 네 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 2, 5, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 다섯 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 5, 6, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 여섯 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 6, 7, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 일곱 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 6, 8, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 여덟 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 8, 2, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 아홉 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 9, 10, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 10, 11, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열한 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 8, 3, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열두 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 2, 4, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열세 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 3, 5, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열네 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 4, 6, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열다섯 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 5, 7, SYSDATE - DBMS_RANDOM.VALUE(1, 365));



-- 열일곱 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 2, 9, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열여덟 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 8, 10, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열아홉 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 9, 11, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 스무 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 2, 11, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

--=============================================================================
-- @DM 테이블 테스트 데이터

-- 첫 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 2, 6, '팀장님 오늘 어떤 업무 하면되나요?', 1, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 두 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 6, 2, '경빈씨 포토피드 오류 왕창나는데 고쳐주세요^^', 1, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 세 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 3, 2, '어디야??? 지각하는거야???', 2, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 네 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 3, 2, '빨리와!!!', 2, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 다섯 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 5, 2, '오늘 안올거니?', 4, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 여섯 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 4, 2, '다들 왜 안오는거니..? 어디야??', 12, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 일곱 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 2, 6, '네.......', 1, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 여덟 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 3, 5, '윤아님 오늘 PPT 템플릿 만들어주세요.', 13, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 아홉 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 3, 5, '오늘 더미데이터/디엠웹소켓 어떤거 만드실래요', 13, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 5, 3, '네... 저는 더미데이터 만들겠습니다...', 13, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열한 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 5, 6, '오늘 몇시에 오실건가요?', 5, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열두 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 6, 7, '안녕 모해?', 6, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열세 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 8, 6, '승영이형 왜안나와요 술좀 그만드세요', 7, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열네 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 8, 6, '빨리 프로필 css 해주세요', 7, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열다섯 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 7, 5, '좋아요, 한번 가보겠습니다!', 15, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열여섯 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 9, 2, '오늘 지하철 사람이 너무 많아 ㅠㅠ', 17, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열일곱 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 2, 9, '조심해!!', 17, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열여덟 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 2, 11, '오늘 평택에서 볼거지?!', 20, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열아홉 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 3, 8, '수육국밥 먹을래?', 11, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 스무 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 7, 6, '인강 과제 제출하셨나요?', 6, SYSDATE - DBMS_RANDOM.VALUE(1, 365));


--=============================================================================
-- @REPORT 테이블 테스트 데이터
-- dm
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 3, 8, '자꾸 수육국밥 먹자고 집착해요. 차단해주세요.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 6, 2, '괴롭힘이 심합니다 도와주세요.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 3, 5, '욕을 너무 많이해요..', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 2, 11, '광고성 DM 을 자꾸 보냅니다.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 8, 6, '부적절한 메세지를 계속 전송합니다.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- profile
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 2, 8, '프로필 사진이 너무 부적절해요.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 5, 8, '소개글에 이상한 글을 작성해놨습니다.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 6, 10, '제 사진을 무단으로 프로필사진으로 사용합니다.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 4, 8, '허위사실을 소개글에 게시해놨어요..', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 7, 11, '프로필에 불법 광고와 판매를 합니다. 막아주세요,', SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- guestbook
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 5, 2, '자꾸 제 방명록에 혐오스러운 글을 작성합니다.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 2, 8, '방명록에 폭력적인 메세지를 씁니다....', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 2, 9, '자꾸 도배를 합니다 너무 화가나요.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 4, 5, '광고성 글을 자꾸 작성합니다.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 8, 3, '집단 괴롭힘이 너무 심합니다.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- photofeed
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 3, 2, '충격적인 사진을 모자이크도 없이 올렸어요...', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 7, 5, '제 사진을 무단으로 퍼가서 사용합니다.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 8, 7, '제 얼굴이 찍힌 사진을 사용했습니다.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 3, 8, '사진이 너무 노출적입니다.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 6, 2, '광고가 너무 심합니다.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- photofeed_comments
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 2, 5, '저를 무시하는 기분나쁜 댓글을 작성했습니다.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 8, 6, '너무 불건전한 내용의 댓글이에요.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 7, 5, '댓글알바 인것같아요 광고가 너무 심해요.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 7, 4, '광고가 너무 심합니다.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 7, 3, '광고 댓글좀 삭제해주세요.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- story
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 2, 3, '맨날 똑같은 스토리만 도배해서 올립니다.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 3, 9, '학원가기 싫다는 말을 자꾸 합니다.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 6, 10, '다이어트중인데 음식얘기를 합니다.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 4, 8, '허위사실을 스토리에 작성합니다.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));
INSERT INTO report  (id, reporter_id, reported_id, content, reg_date) VALUES (seq_report_id.NEXTVAL, 2, 8, '말도안되는 헛소리를 합니다.', SYSDATE - DBMS_RANDOM.VALUE(1, 365));

--@REPORT_DM 테이블 테스트 데이터
INSERT INTO report_dm (report_id, dm_id) VALUES (1, 19);
INSERT INTO report_dm (report_id, dm_id) VALUES (2, 7);
INSERT INTO report_dm (report_id, dm_id) VALUES (3, 9);
INSERT INTO report_dm (report_id, dm_id) VALUES (4, 18);
INSERT INTO report_dm (report_id, dm_id) VALUES (5, 14);

--@REPORT_PROFILE 테이블 테스트 데이터
INSERT INTO report_profile (report_id, profile_id) VALUES(6, 8);
INSERT INTO report_profile (report_id, profile_id) VALUES(7, 8);
INSERT INTO report_profile (report_id, profile_id) VALUES(8, 10);
INSERT INTO report_profile (report_id, profile_id) VALUES(9, 8);
INSERT INTO report_profile (report_id, profile_id) VALUES(10, 11);

--@REPORT_GUESTBOOK 테이블 테스트 데이터
INSERT INTO report_guestbook (report_id, guestbook_id) VALUES(11, 12);
INSERT INTO report_guestbook (report_id, guestbook_id) VALUES(12, 1);
INSERT INTO report_guestbook (report_id, guestbook_id) VALUES(13, 8);
INSERT INTO report_guestbook (report_id, guestbook_id) VALUES(14, 14);
INSERT INTO report_guestbook (report_id, guestbook_id) VALUES(15, 15);

--@REPORT_PHOTOFEED 테이블 테스트 데이터
INSERT INTO report_photo_feed (report_id, photo_feed_id) VALUES(16, 2);
INSERT INTO report_photo_feed (report_id, photo_feed_id) VALUES(17, 6);
INSERT INTO report_photo_feed (report_id, photo_feed_id) VALUES(18, 7);
INSERT INTO report_photo_feed (report_id, photo_feed_id) VALUES(19, 10);
INSERT INTO report_photo_feed (report_id, photo_feed_id) VALUES(20, 1);

--@REPORT_PHOTOFEED_COMMENTS 테이블 테스트 데이터
INSERT INTO report_photo_feed (report_id, photo_feed_id) VALUES(21, 4);
INSERT INTO report_photo_feed (report_id, photo_feed_id) VALUES(22, 6);
INSERT INTO report_photo_feed (report_id, photo_feed_id) VALUES(23, 1);
INSERT INTO report_photo_feed (report_id, photo_feed_id) VALUES(24, 1);
INSERT INTO report_photo_feed (report_id, photo_feed_id) VALUES(25, 1);

--@REPORT_STORY 테이블 테스트 데이터
INSERT INTO report_story (report_id, story_id) VALUES(26, 3);
INSERT INTO report_story (report_id, story_id) VALUES(27, 7);
INSERT INTO report_story (report_id, story_id) VALUES(28, 9);
INSERT INTO report_story (report_id, story_id) VALUES(29, 8);
INSERT INTO report_story (report_id, story_id) VALUES(30, 8);

commit;
