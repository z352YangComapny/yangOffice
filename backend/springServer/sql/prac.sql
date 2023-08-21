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
    
    
SELECT a.*
FROM attachment a
JOIN attachment_photo_feed af ON a.id = af.attachment_id
WHERE af.photo_feed_id = 49; 



select * from comments;
