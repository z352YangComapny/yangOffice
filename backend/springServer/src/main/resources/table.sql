create table member (
    id number,
    username varchar2(50) not null,
    name varchar2(50) not null,
    password varchar2(300) not null,
    nickname varchar2(50) not null,
    birthday date not null,
    gender char(1) not null,
    phone varchar2(20) not null,
    email varchar2(200) not null,
    auth varchar2(60) default 'USER' not null,
    provider varchar2(50),
    reg_date date default sysdate,
    constraints pk_member_id primary key(id),
    constraints ck_member_gender check(gender in ('M', 'F')),
    constraints uk_member_username unique(username),
    constraints uk_member_name unique(name),
    constraints uk_member_nickname unique(nickname),
    constraints uk_member_phone unique (phone),
    constraints uk_member_email unique (email)
);
create sequence seq_member_id;

create table provider(
    member_id number,
    provider varchar2(30),
    constraints pk_provider_id primary key(member_id, provider),
    constraints fk_provider_member_id foreign key(member_id) references member(id) on delete cascade,
    constraints ck_provider_provider check(provider in ('YANG', 'NAVER' , 'GIT' , 'KAKAO', 'GOOGLE'))
);

create table authorities(
    member_id number,
    authority varchar2(30),
    constraints pk_authorities_id primary key(member_id, authority),
    constraints fk_authorities_member_id foreign key(member_id) references member(id) on delete cascade,
    constraints ck_authorities_authority check(authority in ('USER', 'ADMIN'))
);

create table attachment (
    id number,
    original_filename varchar2(500) not null,
    renamed_filename varchar2(500) not null,
    reg_date date default sysdate,
    constraints pk_attachment_id primary key(id)
);
create sequence seq_attachment_id;

create table report(
    id number,
    reporter_id number not null,
    reported_id number not null,
    content varchar2(1000) not null,
    reg_date date default sysdate,
    constraints pk_report_id primary key(id),
    constraints fk_report_reporter_id foreign key(reporter_id) references member(id) on deleted cascade,
    constraints fk_report_reported_id foreign key(reported_id) references member(id) on deleted cascade
);
create sequence seq_profile_id;

create table profile(
    id number,
    member_id number not null,
    state char(1) not null,
    introduction varchar2(300),
    constraints pk_profile_id primary key(id),
    constraints fk_profile_member_id foreign key(member_id) references member(id) on deleted cascade,
    constraints ck_profile_state check(state in ('A', 'B','C','D','E'))
);
create sequence seq_profile_id;

create table report_profile(
      report_id number,
      profile_id number,
      constraints pk_report_profile_id primary key(report_id, profile_id),
      constraints fk_report_profile_reprot_id foreign key(report_id) references report(id) on delete cascade,
      constraints fk_report_profile_profile_id foreign key(profile_id) references profile(id) on delete cascade
);

create table attachment_profile(
    attachment_id number,
    profile_id number,
    constraints pk_attachment_profile_id primary key(attachment_id, profile_id),
    constraints fk_attachment_profile_attachment_id foreign key(attachment_id) references attachment(id) on delete cascade,
    constraints fk_attachment_profile_profile_id foreign key(profile_id) references profile(id) on delete cascade
);

create table dm(
    id number,
    receiver_id number not null,
    sender_id number not null,
    content number not null,
    reg_date date default sysdate,
    constraints pk_dm_id primary key(id),
    constraints fk_dm_sender_id foreign key(sender_id) references member(id) on deleted cascade,
    constraints fk_dm_receiver_id foreign key(receiver_id) references member(id) on deleted cascade
);
create sequence seq_dm_id;

create table report_dm(
    report_id number,
    dm_id number,
    constraints pk_report_dm_id primary key(report_id, dm_id),
    constraints fk_report_dm_reprot_id foreign key(report_id) references report(id) on delete cascade,
    constraints fk_report_dm_dm_id foreign key(dm_id) references dm(id) on delete cascade
);

create table photo_feed(
    id number,
    writer_id number not null,
    content varchar2(1000) not null,
    reg_date date default sysdate,
    constraints pk_photo_feed_id primary key(id),
    constraints fk_photo_feed_writer_id foreign key(writer_id) references member(id) on delete cascade
);
create sequence seq_photo_feed_id;

create table attachment_photo_feed(
   attachment_id number,
   photo_feed_id number,
   constraints pk_attachment_photo_feed_id primary key(attachment_id, photo_feed_id),
   constraints fk_attachment_photo_feed_attachment_id foreign key(attachment_id) references attachment(id) on delete cascade,
   constraints fk_attachment_photo_feed_photo_feed_id foreign key(photo_feed_id) references photo_feed(id) on delete cascade
);

create table report_photo_feed(
  report_id number,
  photo_feed_id number,
  constraints pk_report_photo_feed_id primary key(report_id, dm_id),
  constraints fk_report_photo_feed_reprot_id foreign key(report_id) references report(id) on delete cascade,
  constraints fk_report_photo_feed_photo_feed_id foreign key(photo_feed_id) references photo_feed(id) on delete cascade
);

create table like(
    photo_feed_id number,
    member_id number,
    constraints pk_like_id primary key(photo_feed_id, member_id),
    constraints fk_like_photo_feed_id foreign key(photo_feed_id) references photo_feed(id) on delete cascade,
    constraints fk_like_photo_member_id foreign key(member_id) references member(id) on delete cascade
);

create table comment(
    id number,
    writer_id number not null,
    content varchar2(1000) not null,
    reg_date date default sysdate,
    constraints pk_comment_id primary key(id),
    constraints fk_comment_writer_id foreign key(writer_id) references member(id) on deleted cascade
);
create sequence seq_comment_id;

create table comment_feed(
    comment_id number,
    photo_feed_id number,
    constraints pk_comment_feed_id primary key(comment_id, photo_feed_id),
    constraints fk_comment_feed_photo_feed_id foreign key(photo_feed_id) references photo_feed(id) on deleted cascade,
    constraints fk_comment_feed_comment_id foreign key(comment_id) references comment(id) on deleted cascade
);

create table report_comment_feed(
  report_id number,
  comment_id number,
  constraints pk_report_comment_feed_id primary key(report_id, comment_id),
  constraints fk_report_comment_feed_reprot_id foreign key(report_id) references report(id) on delete cascade,
  constraints fk_report_comment_feed_comment_id foreign key(comment_id) references comment(id) on delete cascade
);

create table follow(
    follower number,
    followee number,
    constraints pk_follow_id primary key(follower, followee),
    constraints fk_follow_follower foreign key(follower) references member(id) on delete cascade,
    constraints fk_follow_followee foreign key(followee) references member(id) on delete cascade
);

create table story(
    id number,
    writer_id number not null,
    content varchar2(1000) not null,
    reg_date date default sysdate,
    constraints pk_story_id primary key(id),
    constraints fk_story_writer_id foreign key(follower) references member(id) on delete cascade
);
create sequence seq_story_id;

create table report_story(
   report_id number,
   story_id number,
   constraints pk_report_story_id primary key(report_id, story_id),
   constraints fk_report_story_reprot_id foreign key(report_id) references report(id) on delete cascade,
   constraints fk_report_story_story_id foreign key(story_id) references story(id) on delete cascade
);

create table guestbook(
      id number,
      writer_id number not null,
      member_id number not null,
      content varchar2(1000) not null,
      reg_date date default sysdate,
      constraints pk_guestbook_id primary key(id),
      constraints fk_guestbook_writer_id foreign key(writer_id) references member(id) on delete cascade,
      constraints fk_guestbook_member_id foreign key(member_id) references member(id) on delete cascade
);
create sequence seq_guestbook_id;

create table report_guestbook(
     report_id number,
     guestbook_id number,
     constraints pk_report_guestbook_id primary key(report_id, guestbook_id),
     constraints fk_report_guestbook_reprot_id foreign key(report_id) references report(id) on delete cascade,
     constraints fk_report_guestbook_id foreign key(guestbook_id) references guestbook(id) on delete cascade
);

create table answer(
      id number,
      writer_id number not null,
      content varchar2(4000) not null,
      reg_date date default sysdate,
      constraints pk_answer_id primary key(id),
      constraints fk_answer_writer_id foreign key(writer_id) references member(id) on delete cascade
);
create sequence seq_answer_id;

create table comment_answer(
     comment_id number,
     answer_id number,
     constraints pk_comment_answer_id primary key(comment_id, photo_feed_id),
     constraints fk_comment_answer_answer_id foreign key(answer_id) references answer(id) on deleted cascade,
     constraints fk_comment_answer_comment_id foreign key(comment_id) references comment(id) on deleted cascade
);