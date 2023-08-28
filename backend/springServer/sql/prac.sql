select * from member;

select * from photo_feed;
select
    atc.*
from 
    photo_feed pf join attachment_photo_feed apf
    on pf.id = apf.photo_feed_id,
    attachment_photo_feed apf join attachment atc
    on apf.photo_feed_id = atc.id
where
    pf.id = 2;
select * from likes;
select count(*) from likes where photo_feed_id = 1;

select * from photo_feed where id= 2;
select * from attachment;
select * from attachment_photo_feed;

select * from member;

update member set password ='$2a$10$uXnleXBDUNo050kiahyM8uktWRfCf3ySpQdo55MwFeS0md8r5Q2OS';

SELECT c.id AS comments_id, c.writer_id, c.content, c.reg_date
FROM comments c
INNER JOIN comments_feed cf ON c.id = cf.comments_id
INNER JOIN photo_feed pf ON cf.photo_feed_id = pf.id
WHERE pf.id = 5;

select * from attachment_photo_feed;


select
    atc.*
from
    photo_feed pf join attachment_photo_feed apf
    on pf.id = apf.photo_feed_id,
    attachment_photo_feed apf join attachment atc
    on apf.photo_feed_id = atc.id
where
    pf.id = 3;
    
select * from photo_feed;
select * from attachment_photo_feed;
select * from attachment;

select 
    atc.*
from
    photo_feed pf join attachment_photo_feed apf
    on pf.id = apf.photo_feed_id,
    attachment_photo_feed apf join attachment atc
    on apf.attachment_id = atc.id
where
    pf.id = 49;
    select c.id, c.writer_id, c.content, c.reg_date from comments_feed cf join comments c on cf.comments_id = c.id where cf.photo_feed_id = 2;
    select * from comments;
    
SELECT c.id, c.writer_id, c.content, c.reg_date
FROM comments c
INNER JOIN comments_feed cf ON c.id = cf.comments_id
WHERE cf.photo_feed_id = 2;

select * from photo_feed;
select * from comments_feed;
select * from member;
insert into likes values(25, 12);
select * from likes;

select 
    cm.*
from
    photo_feed pf join comments_feed cf
    on pf.id = cf.photo_feed_id,
    comments_feed cf join comments cm
    on cf.photo_feed_id = cm.id
where
    pf.id = 2;    

select * from member where id = 12;

SELECT c.*
FROM comments c
JOIN comment_feed cf ON c.id = cf.comments_id
WHERE cf.photo_feed_id = 21;

    
SELECT a.*
FROM attachment a
JOIN attachment_photo_feed af ON a.id = af.attachment_id
WHERE af.photo_feed_id = 21; 

select * from photo_feed;

select * from comments;
select * from comments_feed;
select * from attachment_photo_feed;

select c.id, c.writer_id, c.content, c.reg_date from comments_feed cf join comments c on cf.comments_id = c.id where cf.photo_feed_id = 2;

delete from comments where ;

select * from comments;

select * from report_photo_feed;
select * from photo_feed;
select * from attachment;
select * from attachment_photo_feed;
select * from comments;
select  * from user_role_privs;
select * from member ;
select * from authorities;
select * from profile;
select * from comments;
select * from likes;

-- comments 닉네임 찾는 쿼리
select * from comments cm join member mem on cm.writer_id = mem.id where mem.id = 14;

select * from comments cm join member mem on cm.writer_id = mem.id where;
select * from comments cm;
select * from member where id = 12;

select * from photo_feed where writer_id = 12;

select * from comments_feed where photo_feed_id = 54;
select * from report;
select * from report_comments;
select * from comments where id = 21;

select * from comments where writer_id = 12;

select * from member me join photo_feed pf on me.id = pf.id;

select * from photo_feed where writer_id = 12;

select * from photo_feed;

select * from attachment_photo_feed;
select * from attachment_photo_feed where photo_feed_Id = 50;
select * from attachment where id = 54;