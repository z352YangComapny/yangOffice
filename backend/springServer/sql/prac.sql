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