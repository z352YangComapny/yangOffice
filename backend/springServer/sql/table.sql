create table member
(
    id          number,
    username    varchar2(50)  not null,
    name        varchar2(50)  not null,
    password    varchar2(300) not null,
    nickname    varchar2(50)  not null,
    birthday    date          not null,
    gender      char(1)       not null,
    phone       varchar2(20)  not null,
    email       varchar2(200) not null,
    provider    varchar2(50),
    reg_date    date default sysdate,
    constraints p_member_id primary key( id),
    constraints c_member_gender check (gender in ('M', 'F')),
    constraints u_member_username unique(username),
    constraints u_member_name unique( name),
    constraints u_member_nickname unique(nickname),
    constraints u_member_phone unique (phone),
    constraints u_member_email unique (email),
    constraints c_member_provider check (provider in ('YANG', 'NAVER', 'GIT', 'KAKAO', 'GOOGLE'))
);
create sequence seq_member_id;

create table authorities
(
    member_id   number,
    authority varchar2(1000),
    constraints p_authorities_id primary key(member_id, authority),
    constraints f_authorities_member_id foreign key (member_id) references member (id) on delete cascade
);

create table attachment
(
    id                number,
    original_filename varchar2(500) not null,
    renamed_filename  varchar2(500) not null,
    reg_date          date default sysdate,
    constraints       p_attachment_id primary key( id)
);
create sequence seq_attachment_id;

create table report (
    id number,
    reporter_id number not null,
    reported_id number not null,
    content varchar2(1000) not null,
    reg_date date default sysdate,
    constraints p_report_id primary key( id),
    constraints f_report_reporter_id foreign key(reporter_id) references member(id) on delete cascade,
    constraints f_report_reported_id foreign key(reported_id) references member(id) on delete cascade
);
create sequence seq_report_id;

create table profile
(
    id           number,
    member_id    number  not null,
    state        char(1) not null,
    introduction varchar2(300),
    constraints  p_profile_id primary key( id),
    constraints  f_profile_member_id foreign key (member_id) references member (id) on delete cascade,
    constraints  c_profile_state check (state in ('A', 'B', 'C', 'D', 'E'))
);
create sequence seq_profile_id;

create table report_profile
(
    report_id   number,
    profile_id  number,
    constraints p_rep_profile_id primary key(report_id),
    constraints f_rep_profile_reprot_id foreign key (report_id) references report(id) on delete cascade,
    constraints f_rep_profile_profile_id foreign key (profile_id) references profile (id) on delete cascade
);

create table attachment_profile
(
    attachment_id number,
    profile_id    number,
    constraints   p_att_profile_id primary key(attachment_id),
    constraints   f_att_profile_attachment_id foreign key (attachment_id) references attachment(id) on delete cascade,
    constraints   f_att_profile_profile_id foreign key (profile_id) references profile (id) on delete cascade
);

create table dm
(
    id          number,
    receiver_id number not null,
    sender_id   number not null,
    content     varchar2(2000) not null,
    reg_date    date default sysdate,
    constraints p_dm_id primary key( id),
    constraints f_dm_sender_id foreign key (sender_id) references member (id) on delete cascade,
    constraints f_dm_receiver_id foreign key (receiver_id) references member (id) on delete cascade
);
create sequence seq_dm_id;

create table report_dm
(
    report_id   number,
    dm_id       number,
    constraints p_rep_dm_id primary key(report_id),
    constraints f_rep_dm_reprot_id foreign key (report_id) references report(id) on delete cascade,
    constraints f_rep_dm_dm_id foreign key (dm_id) references dm(id) on delete cascade
);

create table photo_feed
(
    id          number,
    writer_id   number   not null,
    content     varchar2(1000) not null,
    reg_date    date default sysdate,
    constraints p_pho_feed_id primary key( id),
    constraints f_pho_feed_writer_id foreign key (writer_id) references member (id) on delete cascade
);
create sequence seq_photo_feed_id;

create table attachment_photo_feed
(
    attachment_id number,
    photo_feed_id number,
    constraints   p_att_photo_feed_id primary key(attachment_id),
    constraints   f_att_photo_feed_attachment_id foreign key (attachment_id) references attachment(id) on delete cascade,
    constraints   f_att_photo_feed_photo_feed_id foreign key (photo_feed_id) references photo_feed(id) on delete cascade
);

create table report_photo_feed
(
    report_id     number,
    photo_feed_id number,
    constraints   p_rep_photo_feed_id primary key(report_id),
    constraints   f_rep_photo_feed_reprot_id foreign key (report_id) references report(id) on delete cascade,
    constraints   f_rep_photo_feed_photo_feed_id foreign key (photo_feed_id) references photo_feed(id) on delete cascade
);

create table likes
(
    photo_feed_id number,
    member_id     number,
    constraints   p_lik_id primary key(photo_feed_id, member_id),
    constraints   f_lik_photo_feed_id foreign key (photo_feed_id) references photo_feed(id) on delete cascade,
    constraints   f_lik_photo_member_id foreign key (member_id) references member (id) on delete cascade
);

create table comments
(
    id          number,
    writer_id   number         not null,
    content     varchar2(1000) not null,
    reg_date    date default sysdate,
    constraints p_comments_id primary key( id),
    constraints f_comments_writer_id foreign key (writer_id) references member (id) on delete cascade
);
create sequence seq_comments_id;

create table comments_feed
(
    comments_id    number,
    photo_feed_id number,
    constraints   p_com_feed_id primary key(comments_id),
    constraints   f_com_feed_photo_feed_id foreign key (photo_feed_id) references photo_feed(id) on delete cascade,
    constraints   f_com_feed_comments_id foreign key (comments_id) references comments(id) on delete cascade
);

create table report_comments_feed
(
    report_id   number,
    comments_id  number,
    constraints p_rep_cmt_feed_id primary key(report_id),
    constraints f_rep_cmt_feed_reprot_id foreign key (report_id) references report(id) on delete cascade,
    constraints f_rep_cmt_feed_comments_id foreign key (comments_id) references comments (id) on delete cascade
);

create table follow
(
    follower    number,
    followee    number,
    reg_date    date default sysdate,
    constraints p_follow_id primary key(follower, followee),
    constraints f_follow_follower foreign key (follower) references member (id) on delete cascade,
    constraints f_follow_followee foreign key (followee) references member (id) on delete cascade
);

create table story
(
    id          number,
    writer_id   number         not null,
    content     varchar2(1000) not null,
    reg_date    date default sysdate,
    constraints p_story_id primary key( id),
    constraints f_story_writer_id foreign key (writer_id) references member (id) on delete cascade
);
create sequence seq_story_id;

create table report_story
(
    report_id   number,
    story_id    number,
    constraints p_rep_story_id primary key(report_id),
    constraints f_rep_story_reprot_id foreign key (report_id) references report(id) on delete cascade,
    constraints f_rep_story_story_id foreign key (story_id) references story(id) on delete cascade
);

create table guestbook
(
    id          number,
    writer_id   number         not null,
    member_id   number         not null,
    content     varchar2(1000) not null,
    reg_date    date default sysdate,
    constraints p_gue_id primary key( id),
    constraints f_gue_writer_id foreign key (writer_id) references member (id) on delete cascade,
    constraints f_gue_member_id foreign key (member_id) references member (id) on delete cascade
);
create sequence seq_guestbook_id;

create table report_guestbook
(
    report_id    number,
    guestbook_id number,
    constraints  p_rep_guestbook_id primary key(report_id),
    constraints  f_rep_guestbook_reprot_id foreign key (report_id) references report(id) on delete cascade,
    constraints  f_rep_guestbook_id foreign key (guestbook_id) references guestbook(id) on delete cascade
);

create table question
(
    id          number,
    writer_id   number         not null,
    title varchar2(100) not null,
    content     varchar2(4000) not null,
    type char(1) not null,
    reg_date    date default sysdate,
    constraints p_question_id primary key( id),
    constraints f_question_writer_id foreign key (writer_id) references member (id) on delete cascade
);
create sequence seq_question_id;
select * from question;
create table comments_question
(
    comments_id  number,
    question_id   number,
    constraints p_com_question_id primary key(comments_id),
    constraints f_com_question_question_id foreign key (question_id) references question(id) on delete cascade,
    constraints f_com_question_comments_id foreign key (comments_id) references comments (id) on delete cascade
);

create table deleted_member
(
    username     varchar2(50)  not null,
    name         varchar2(50)  not null,
    password     varchar2(300) not null,
    nickname     varchar2(50)  not null,
    birthday     date          not null,
    gender       char(1)       not null,
    phone        varchar2(20)  not null,
    email        varchar2(200) not null,
    provider     varchar2(50),
    reg_date     date default sysdate,
    deleted_date date default sysdate
);


CREATE OR REPLACE TRIGGER trg_member_deleted
BEFORE DELETE ON member
FOR EACH ROW
BEGIN
    INSERT INTO deleted_member (
        username,
        name,
        password,
        nickname,
        birthday,
        gender,
        phone,
        email,
        provider,
        reg_date,
        deleted_date
    ) VALUES (
        :OLD.username,
        :OLD.name,
        :OLD.password,
        :OLD.nickname,
        :OLD.birthday,
        :OLD.gender,
        :OLD.phone,
        :OLD.email,
        :OLD.provider,
        :OLD.reg_date,
        SYSDATE
    );
END;
/

--
--
-- -- 계정에 속한 모든 테이블를 삭제합니다.
-- BEGIN
--    FOR tab IN (SELECT table_name FROM user_tables) LOOP
--        EXECUTE IMMEDIATE 'DROP TABLE ' || tab.table_name || ' CASCADE CONSTRAINTS';
--    END LOOP;
-- END;
-- /
-- -- 계정에 속한 모든 시퀀스를 삭제합니다.
-- BEGIN
--    FOR seq IN (SELECT sequence_name FROM user_sequences WHERE sequence_name LIKE 'SEQ\_%' ESCAPE '\') LOOP
--        EXECUTE IMMEDIATE 'DROP SEQUENCE ' || seq.sequence_name;
--    END LOOP;
-- END;
-- /
-- -- 계정에 속한 모든 트리거를 삭제합니다.
-- BEGIN
--    FOR trg IN (SELECT trigger_name FROM user_triggers) LOOP
--        EXECUTE IMMEDIATE 'DROP TRIGGER ' || trg.trigger_name;
--    END LOOP;
-- END;
-- /

select * from member;
select * from authorities;
select * from guestbook;

-- member 테이블에 예시 데이터 삽입
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
-- authorities 테이블에 예시 데이터 삽입
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
select * from dm;
commit;
