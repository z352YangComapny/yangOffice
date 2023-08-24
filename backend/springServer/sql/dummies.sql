-- @member 테이블에 예시 데이터 삽입
-- admin 유저 삽입
INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'admin', 'Administrator', '1234', 'Admin', TO_DATE('1990-01-01', 'YYYY-MM-DD'), 'M', '01012345678', 'admin@example.com', 'YANG');

-- user1부터 순서대로 삽입
INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user1', 'John Doe', '1234', 'JD', TO_DATE('1990-05-15', 'YYYY-MM-DD'), 'M', '01012309078', 'john@example.com', 'YANG');

INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user2', 'Jane Smith', '1234', 'JS', TO_DATE('1985-08-21', 'YYYY-MM-DD'), 'F', '01098765432', 'jane@example.com', 'NAVER');

INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user3', 'Michael Johnson', '1234', 'MJ', TO_DATE('1995-02-10', 'YYYY-MM-DD'), 'M', '01055556666', 'michael@example.com', 'GIT');

INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user4', 'Emily Brown', '1234', 'EB', TO_DATE('1992-11-30', 'YYYY-MM-DD'), 'F', '01044445555', 'emily@example.com', 'KAKAO');

INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user5', 'Daniel Wilson', '1234', 'DW', TO_DATE('1988-07-07', 'YYYY-MM-DD'), 'M', '01077778888', 'daniel@example.com', 'GOOGLE');

INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user6', 'Olivia Davis', '1234', 'OD', TO_DATE('1997-04-25', 'YYYY-MM-DD'), 'F', '01022223333', 'olivia@example.com', 'NAVER');

INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user7', 'William Martin', '1234', 'WM', TO_DATE('1991-09-17', 'YYYY-MM-DD'), 'M', '01066669999', 'william@example.com', 'YANG');

INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user8', 'Sophia Johnson', '1234', 'SJ', TO_DATE('1987-03-12', 'YYYY-MM-DD'), 'F', '01011112222', 'sophia@example.com', 'GIT');

INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user9', 'James Anderson', '1234', 'JA', TO_DATE('1994-12-02', 'YYYY-MM-DD'), 'M', '01099990000', 'james@example.com', 'KAKAO');

INSERT INTO member (id, username, name, password, nickname, birthday, gender, phone, email, provider)
VALUES (seq_member_id.NEXTVAL, 'user10', 'Mia Martinez', '1234', 'MM', TO_DATE('1996-06-08', 'YYYY-MM-DD'), 'F', '01055556677', 'mia@example.com', 'GOOGLE');
--=============================================================================
--=============================================================================
--=============================================================================
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

select * from member;

--=============================================================================
--=============================================================================
--=============================================================================
--=============================================================================

-- @dm_room dummies

-- 첫 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 1, 2, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 두 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 2, 3, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 세 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 3, 4, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 네 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 4, 5, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 다섯 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 5, 6, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 여섯 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 6, 7, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 일곱 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 7, 8, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 여덟 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 8, 9, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 아홉 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 9, 10, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 10, 11, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열한 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 1, 3, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

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

-- 열여섯 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 6, 8, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열일곱 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 7, 9, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열여덟 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 8, 10, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열아홉 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 9, 11, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 스무 번째 INSERT 문
INSERT INTO dm_room (id, participant1, participant2, reg_date)
VALUES (seq_dm_room_id.NEXTVAL, 1, 11, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

--=============================================================================
--=============================================================================
--=============================================================================
--=============================================================================

-- @Story

DECLARE
    current_time DATE := SYSDATE;
BEGIN
    FOR i IN 1..20 LOOP
            INSERT INTO story (id, writer_id, content, reg_date)
            VALUES (seq_story_id.NEXTVAL,
                    TRUNC(DBMS_RANDOM.VALUE(1, 11)),  -- writer_id 랜덤 선택 (1부터 11까지)
                    'Dummy Content ' || i,
                    current_time - DBMS_RANDOM.VALUE(1, 36) / 24);  -- 현재시간으로부터 36시간 이내의 랜덤한 값

            COMMIT;
        END LOOP;
END;
/

--=============================================================================
--=============================================================================
--=============================================================================
--=============================================================================

-- @Question 테이블 테스트 데이터

-- Q 타입 데이터
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 1, 'Q1 Title', 'Q1 Content', 'Q', SYSDATE - 14);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 2, 'Q2 Title', 'Q2 Content', 'Q', SYSDATE - 13);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 3, 'Q3 Title', 'Q3 Content', 'Q', SYSDATE - 12);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 4, 'Q4 Title', 'Q4 Content', 'Q', SYSDATE - 11);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 5, 'Q5 Title', 'Q5 Content', 'Q', SYSDATE - 10);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 6, 'Q6 Title', 'Q6 Content', 'Q', SYSDATE - 9);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 7, 'Q7 Title', 'Q7 Content', 'Q', SYSDATE - 8);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 8, 'Q8 Title', 'Q8 Content', 'Q', SYSDATE - 7);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 9, 'Q9 Title', 'Q9 Content', 'Q', SYSDATE - 6);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 10, 'Q10 Title', 'Q10 Content', 'Q', SYSDATE - 5);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 11, 'Q11 Title', 'Q11 Content', 'Q', SYSDATE - 4);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 1, 'Q12 Title', 'Q12 Content', 'Q', SYSDATE - 3);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 2, 'Q13 Title', 'Q13 Content', 'Q', SYSDATE - 2);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 3, 'Q14 Title', 'Q14 Content', 'Q', SYSDATE - 1);

-- N 타입 데이터
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 4, 'N1 Title', 'N1 Content', 'N', SYSDATE - 14);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 5, 'N2 Title', 'N2 Content', 'N', SYSDATE - 13);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 6, 'N3 Title', 'N3 Content', 'N', SYSDATE - 12);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 7, 'N4 Title', 'N4 Content', 'N', SYSDATE - 11);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 8, 'N5 Title', 'N5 Content', 'N', SYSDATE - 10);
INSERT INTO question (id, writer_id, title, content, type, reg_date) VALUES (seq_question_id.nextval, 9, 'N6 Title', 'N6 Content', 'N', SYSDATE - 9);

--=============================================================================
--=============================================================================
--=============================================================================
--=============================================================================

-- @GuestBook 테이블 테스트 데이터

-- GuestBook 테스트 케이스 데이터
-- 1시간을 3600초로 변환하였습니다.
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 1, 2, 'Test Content 1', SYSDATE - 3600);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 2, 3, 'Test Content 2', SYSDATE - 7200);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 3, 4, 'Test Content 3', SYSDATE - 10800);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 4, 5, 'Test Content 4', SYSDATE - 14400);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 5, 6, 'Test Content 5', SYSDATE - 18000);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 6, 7, 'Test Content 6', SYSDATE - 21600);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 7, 8, 'Test Content 7', SYSDATE - 25200);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 8, 9, 'Test Content 8', SYSDATE - 28800);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 9, 10, 'Test Content 9', SYSDATE - 32400);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 10, 11, 'Test Content 10', SYSDATE - 36000);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 11, 1, 'Test Content 11', SYSDATE - 39600);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 1, 2, 'Test Content 12', SYSDATE - 43200);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 2, 3, 'Test Content 13', SYSDATE - 46800);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 3, 4, 'Test Content 14', SYSDATE - 50400);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 4, 5, 'Test Content 15', SYSDATE - 54000);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 5, 6, 'Test Content 16', SYSDATE - 57600);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 6, 7, 'Test Content 17', SYSDATE - 61200);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 7, 8, 'Test Content 18', SYSDATE - 64800);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 8, 9, 'Test Content 19', SYSDATE - 68400);
INSERT INTO guestbook (id, writer_id, member_id, content, reg_date) VALUES (seq_guestbook_id.nextval, 9, 10, 'Test Content 20', SYSDATE - 72000);

--=============================================================================
--=============================================================================
--=============================================================================
--=============================================================================

-- @photo_feed table 테이블 테스트 데이터

-- 1
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 1, 'Content 1', SYSDATE - INTERVAL '1' DAY);

-- 2
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 2, 'Content 2', SYSDATE - INTERVAL '2' DAY);

-- 3
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 3, 'Content 3', SYSDATE - INTERVAL '3' DAY);

-- 4
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 4, 'Content 4', SYSDATE - INTERVAL '4' DAY);

-- 5
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 5, 'Content 5', SYSDATE - INTERVAL '5' DAY);

-- 6
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 6, 'Content 6', SYSDATE - INTERVAL '6' DAY);

-- 7
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 7, 'Content 7', SYSDATE - INTERVAL '7' DAY);

-- 8
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 8, 'Content 8', SYSDATE - INTERVAL '8' DAY);

-- 9
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 9, 'Content 9', SYSDATE - INTERVAL '9' DAY);

-- 10
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 10, 'Content 10', SYSDATE - INTERVAL '10' DAY);

-- 11
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 11, 'Content 11', SYSDATE - INTERVAL '11' DAY);

-- 12
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 1, 'Content 12', SYSDATE - INTERVAL '12' DAY);

-- 13
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 2, 'Content 13', SYSDATE - INTERVAL '13' DAY);

-- 14
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 3, 'Content 14', SYSDATE - INTERVAL '14' DAY);

-- 15
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 4, 'Content 15', SYSDATE - INTERVAL '15' DAY);

-- 16
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 5, 'Content 16', SYSDATE - INTERVAL '16' DAY);

-- 17
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 6, 'Content 17', SYSDATE - INTERVAL '17' DAY);

-- 18
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 7, 'Content 18', SYSDATE - INTERVAL '18' DAY);

-- 19
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 8, 'Content 19', SYSDATE - INTERVAL '19' DAY);

-- 20
INSERT INTO photo_feed (id, writer_id, content, reg_date)
VALUES (seq_photo_feed_id.NEXTVAL, 9, 'Content 20', SYSDATE - INTERVAL '20' DAY);


--=============================================================================
--=============================================================================
--=============================================================================
--=============================================================================


-- @attachment table 테이블 테스트 데이터
BEGIN
    FOR i IN 1..10 LOOP
            -- 첫 번째 테스트 케이스
            INSERT INTO attachment (id, original_filename, renamed_filename, reg_date)
            VALUES (seq_attachment_id.NEXTVAL, 'original_file1.png', '20230814_040508555_255.png', SYSDATE);

            -- 두 번째 테스트 케이스
            INSERT INTO attachment (id, original_filename, renamed_filename, reg_date)
            VALUES (seq_attachment_id.NEXTVAL, 'original_file2.png', '20239816_210836445_486.png', SYSDATE);
        END LOOP;
END;
/
--=============================================================================
--=============================================================================
--=============================================================================
--=============================================================================

-- @attachment_photo_feed table 테이블 테스트 데이터

-- 어태치먼트 아이디 1-20, 포토 피드 아이디 1-20 모두 매칭
INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (1, 1);

INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (2, 2);

INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (3, 3);

INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (4, 4);

INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (5, 5);

INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (6, 6);

INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (7, 7);

INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (8, 8);

INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (9, 9);

INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (10, 10);

INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (11, 11);

INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (12, 12);

INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (13, 13);

INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (14, 14);

INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (15, 15);

INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (16, 16);

INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (17, 17);

INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (18, 18);

INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (19, 19);

INSERT INTO attachment_photo_feed (attachment_id, photo_feed_id)
VALUES (20, 20);


--=============================================================================
--=============================================================================
--=============================================================================
--=============================================================================

-- @profile table 테이블 테스트 데이터

-- 어태치먼트 아이디 1-20, 포토 피드 아이디 1-20 모두 매칭


insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 1, 'A', 'Introduction for Member 1');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 2, 'B', 'Introduction for Member 2');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 3, 'C', 'Introduction for Member 3');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 4, 'D', 'Introduction for Member 4');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 5, 'E', 'Introduction for Member 5');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 6, 'A', 'Introduction for Member 6');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 7, 'B', 'Introduction for Member 7');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 8, 'C', 'Introduction for Member 8');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 9, 'D', 'Introduction for Member 9');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 10, 'E', 'Introduction for Member 10');
insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, 11, 'A', 'Introduction for Member 11');

--=============================================================================
--=============================================================================
--=============================================================================
--=============================================================================

-- @follow table 테이블 테스트 데이터

-- 멤버 간의 팔로우 관계
insert into follow (follower, followee) values (1, 2);
insert into follow (follower, followee) values (1, 3);
insert into follow (follower, followee) values (2, 3);
insert into follow (follower, followee) values (2, 4);
insert into follow (follower, followee) values (3, 1);
insert into follow (follower, followee) values (3, 5);
insert into follow (follower, followee) values (4, 2);
insert into follow (follower, followee) values (5, 6);
insert into follow (follower, followee) values (5, 1);
insert into follow (follower, followee) values (6, 1);
insert into follow (follower, followee) values (6, 3);
insert into follow (follower, followee) values (7, 4);
insert into follow (follower, followee) values (8, 2);
insert into follow (follower, followee) values (8, 3);
insert into follow (follower, followee) values (9, 1);
insert into follow (follower, followee) values (9, 6);
insert into follow (follower, followee) values (10, 3);
insert into follow (follower, followee) values (10, 4);
insert into follow (follower, followee) values (11, 1);
insert into follow (follower, followee) values (11, 5);
-- 멤버 간의 팔로우 관계가 아닌 경우 (추가 20개)
insert into follow (follower, followee) values (1, 4);
insert into follow (follower, followee) values (2, 5);
insert into follow (follower, followee) values (3, 6);
insert into follow (follower, followee) values (4, 1);
insert into follow (follower, followee) values (5, 2);
insert into follow (follower, followee) values (6, 4);
insert into follow (follower, followee) values (7, 5);
insert into follow (follower, followee) values (8, 6);
insert into follow (follower, followee) values (9, 4);
insert into follow (follower, followee) values (10, 5);
insert into follow (follower, followee) values (11, 6);
insert into follow (follower, followee) values (1, 7);
insert into follow (follower, followee) values (2, 8);
insert into follow (follower, followee) values (3, 9);
insert into follow (follower, followee) values (4, 10);
insert into follow (follower, followee) values (5, 11);
insert into follow (follower, followee) values (6, 7);
insert into follow (follower, followee) values (7, 8);
insert into follow (follower, followee) values (8, 9);
insert into follow (follower, followee) values (9, 10);
insert into follow (follower, followee) values (10, 11);

--=============================================================================
--=============================================================================
--=============================================================================
--=============================================================================

-- @follow table 테이블 테스트 데이터
-- 프로필에 어태치먼트 연결
insert into attachment_profile (attachment_id, profile_id) values (1, 1);
insert into attachment_profile (attachment_id, profile_id) values (2, 2);
insert into attachment_profile (attachment_id, profile_id) values (3, 3);
insert into attachment_profile (attachment_id, profile_id) values (4, 4);
insert into attachment_profile (attachment_id, profile_id) values (5, 5);
insert into attachment_profile (attachment_id, profile_id) values (6, 6);
insert into attachment_profile (attachment_id, profile_id) values (7, 7);
insert into attachment_profile (attachment_id, profile_id) values (8, 8);
insert into attachment_profile (attachment_id, profile_id) values (9, 9);
insert into attachment_profile (attachment_id, profile_id) values (10, 10);
insert into attachment_profile (attachment_id, profile_id) values (11, 11);

-- 첫 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 2, 1, '안녕하세요!', 1, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 두 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 3, 2, '안녕하세요~', 2, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 세 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 4, 3, '반가워요!', 3, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 네 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 5, 4, '만나서 기뻐요!', 4, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 다섯 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 6, 5, '안녕하세요! 어떻게 지내세요?', 5, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 여섯 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 7, 6, '안녕하세요~ 잘 지내고 있어요!', 6, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 일곱 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 8, 7, '오늘 날씨가 좋네요!', 7, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 여덟 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 9, 8, '맞아요, 기분 좋아지네요!', 8, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 아홉 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 10, 9, '어디 가기 좋을까요?', 9, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 11, 10, '저는 공원이 좋아요!', 10, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열한 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 3, 1, '안녕하세요! 오랜만이네요~', 11, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열두 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 4, 2, '네, 오랜만이네요~', 12, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열세 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 5, 3, '어디 좋은 카페 아시나요?', 13, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열네 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 6, 4, '옆에 있는 커피숍이 좋아요!', 14, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열다섯 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 7, 5, '좋아요, 한번 가보겠습니다!', 15, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열여섯 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 8, 6, '기다리겠습니다~', 16, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열일곱 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 9, 7, '저녁에 뭐 드실까요?', 17, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열여덟 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 10, 8, '피자 어때요?', 18, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 열아홉 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 11, 9, '저도 좋아요!', 19, SYSDATE - DBMS_RANDOM.VALUE(1, 365));

-- 스무 번째 dm 인서트 문
INSERT INTO dm (id, receiver_id, sender_id, content, dm_room_id, reg_date)
VALUES (seq_dm_id.NEXTVAL, 11, 1, '안녕하세요! 만나서 반가워요~', 20, SYSDATE - DBMS_RANDOM.VALUE(1, 365));



commit;
