select * from member;

delete from member where username = 'test2';
delete from member where username = 'soso1';


select * from profile where member_id = 2;

select * from member where username like '%t%';

select * from FOLLOW where follower = 2;

select * from follow f left join member m  on f.followee = m.id where f.follower = 2;

select * from follow member where follower =2;

select * from follow where follower =5;

select * from follow where follower = 2;

select count(*)  from member;
select m.*, (select count(*)  from member)totalMemberCount from member m;

select m.*, (select count(*)  from member where username like '%t%' ) totalMemberCount from member m where username like '%t%';

select * from member;

delete from member where id = 22;

commit;
select *
from member M
         left join authorities A
                   on M.id = A.member_id;

SELECT
    m.id,
    m.username,
    m.name,
    m.password,
    m.nickname,
    m.birthday,
    m.gender,
    m.phone,
    m.email,
    m.provider,
    m.reg_date,
    LISTAGG(a.authority, ', ') WITHIN GROUP (ORDER BY a.authority) AS authorities
FROM
    member m
        JOIN
    authorities a ON m.id = a.member_id
GROUP BY
    m.id, m.username, m.name, m.password, m.nickname, m.birthday, m.gender, m.phone, m.email, m.provider, m.reg_date;

SELECT
    *
FROM
    photo_feed pf
        JOIN
    attachment_photo_feed apf ON pf.id = apf.photo_feed_id
        JOIN
    attachment a ON apf.attachment_id = a.id;


SELECT
    pf.id AS photo_feed_id,
    pf.content AS photo_feed_content,
    LISTAGG(a.original_filename, ', ') WITHIN GROUP (ORDER BY apf.attachment_id) AS attached_files
FROM
    photo_feed pf
        JOIN
    attachment_photo_feed apf ON pf.id = apf.photo_feed_id
        JOIN
    attachment a ON apf.attachment_id = a.id
GROUP BY
    pf.id, pf.content;

SELECT
    pf.id AS photo_feed_id,
    pf.content AS photo_feed_content,
    pf.reg_date AS photo_feed_reg_date,
    LISTAGG(a.original_filename, ', ') WITHIN GROUP (ORDER BY apf.attachment_id) AS attached_files,
    m.username AS member_username
FROM
    photo_feed pf
        JOIN
    attachment_photo_feed apf ON pf.id = apf.photo_feed_id
        JOIN
    attachment a ON apf.attachment_id = a.id
        JOIN
    member m ON pf.writer_id = m.id
WHERE
        pf.id = 1-- 원하는 피드 ID를 여기에 넣으세요
GROUP BY
    pf.id, pf.content, pf.reg_date, m.username;

SELECT * FROM attachment a JOIN attachment_profile ap ON a.id = ap.attachment_id where profile_id=1;


select * from report;
select *from report_comments_feed;

select * from REPORT_PHOTO_FEED;

-- 포토피드 report
select
    r.id as id,
    m.username as writer,
    r.content as content,
    r.REG_DATE as regdate
from report r
     join REPORT_PHOTO_FEED rpf on r.ID = rpf.REPORT_ID
     join PHOTO_FEED pf on rpf.PHOTO_FEED_ID=pf.id
    join MEMBER m on r.REPORTED_ID = m.id;

select * from report;

-- 댓글 report
select  r.id as id,
        m.username as writer,
        r.content as content,
        r.REG_DATE as regdate
from report r
    join REPORT_COMMENTS_FEED rcf on r.id = rcf.REPORT_ID
    join COMMENTS_FEED cf on rcf.COMMENTS_ID = cf.COMMENTS_ID
    join comments c on cf.COMMENTS_ID = c.id
    join MEMBER m on r.REPORTED_ID = m.id;


-- 스토리 report
select
    r.id as id,
    m.username as writer,
    r.content as content,
    r.REG_DATE as regdate
from report r
    join REPORT_STORY rs on r.id = rs.REPORT_ID
    join STORY s on rs.STORY_ID = s.id
    join MEMBER m on r.REPORTED_ID = m.id;

-- 방명록 report
select
    r.id as id,
    m.username as writer,
    r.content as content,
    r.REG_DATE as regdate
    from report r
    join REPORT_GUESTBOOK rg on r.id = rg.REPORT_ID
    join GUESTBOOK g on rg.GUESTBOOK_ID = g.id
    join MEMBER m on r.REPORTED_ID = m.id;

-- DM report
select
    r.id as id,
    m.username as writer,
    r.content as content,
    r.REG_DATE as regdate
from report r
     join REPORT_DM rd on r.id = rd.REPORT_ID
     join DM d on rd.DM_ID = d.id
     join MEMBER M on r.REPORTED_ID = M.ID;

select count(*) from report;


select * from photo_feed where id = 2;

-- 방명록 일자별 조회 쿼리
SELECT
    TRUNC(reg_date) AS guestbook_date,
    COUNT(*) AS guestbook_count
FROM
    guestbook
WHERE
        reg_date > TRUNC(SYSDATE) - 10
  AND reg_date <= TRUNC(SYSDATE)
GROUP BY
    TRUNC(reg_date)
ORDER BY
    TRUNC(reg_date);

SELECT
    TRUNC(dates.guestbook_date) AS guestbook_date,
    NVL(COUNT(GUESTBOOK.reg_date), 0) AS guestbook_count
FROM
    (SELECT TRUNC(SYSDATE) - LEVEL + 1 AS guestbook_date
     FROM dual
     CONNECT BY LEVEL <= 10) dates
        LEFT JOIN
    GUESTBOOK ON TRUNC(GUESTBOOK.reg_date) = dates.guestbook_date
GROUP BY
    TRUNC(dates.guestbook_date)
ORDER BY
    TRUNC(dates.guestbook_date);






-- 스토리 일자별 조회쿼리

SELECT
    TRUNC(dates.story_date) AS story_date,
    NVL(COUNT(story.reg_date), 0) AS story_count
FROM
    (SELECT TRUNC(SYSDATE) - LEVEL + 1 AS story_date
     FROM dual
     CONNECT BY LEVEL <= 10) dates
        LEFT JOIN
    story ON TRUNC(story.reg_date) = dates.story_date
GROUP BY
    TRUNC(dates.story_date)
ORDER BY
    TRUNC(dates.story_date);






--포토피드 일자별 쿼리
SELECT
    TRUNC(reg_date) AS photofeed_date,
    COUNT(*) AS photofeed_count
FROM
    PHOTO_FEED
WHERE
        reg_date > TRUNC(SYSDATE) - 10
  AND reg_date <= TRUNC(SYSDATE)
GROUP BY
    TRUNC(reg_date)
ORDER BY
    TRUNC(reg_date);

SELECT
    TRUNC(dates.photofeed_date) AS photofeed_date,
    NVL(COUNT(PHOTO_FEED.reg_date), 0) AS photofeed_count
FROM
    (SELECT TRUNC(SYSDATE) - LEVEL + 1 AS photofeed_date
     FROM dual
     CONNECT BY LEVEL <= 10) dates
        LEFT JOIN
    PHOTO_FEED ON TRUNC(PHOTO_FEED.reg_date) = dates.photofeed_date
GROUP BY
    TRUNC(dates.photofeed_date)
ORDER BY
    TRUNC(dates.photofeed_date);


SELECT TO_CHAR(reg_date, 'MM') AS registration_month,
       COUNT(*) AS member_count
FROM member
GROUP BY TO_CHAR(reg_date, 'MM')
ORDER BY TO_CHAR(reg_date, 'MM');

-- 월별 가입자
WITH Months AS (
    SELECT LEVEL AS month
    FROM DUAL
    CONNECT BY LEVEL <= 12
)
SELECT TO_CHAR(Months.month, 'FM00') AS month,
       COUNT(member.id) AS member_count
FROM Months
         LEFT JOIN member ON TO_CHAR(member.reg_date, 'MM') = TO_CHAR(Months.month, 'FM00')
GROUP BY Months.month
ORDER BY Months.month;

-- 월별 탈퇴자 수
WITH Months AS (
    SELECT LEVEL AS month
    FROM DUAL
    CONNECT BY LEVEL <= 12
)
SELECT TO_CHAR(Months.month, 'FM00') AS month,
       COALESCE(COUNT(DELETED_MEMBER.username), 0) AS member_count
FROM Months
         LEFT JOIN deleted_member
                   ON TO_CHAR(deleted_member.deleted_date, 'MM') = TO_CHAR(Months.month, 'FM00')
GROUP BY Months.month
ORDER BY Months.month;

-- OAuth별 회원수 조회
SELECT provider, COUNT(*) AS member_count
FROM (
         SELECT
             CASE
                 WHEN username like '%kakao%' THEN 'kakao'
                 WHEN username like '%naver%' THEN 'naver'
                 -- 다른 provider에 대한 패턴을 추가
                 ELSE 'unknown'
                 END AS provider
         FROM Member
     ) provider_subquery
GROUP BY provider
ORDER BY provider;
-- 다른 provider에 대한 패턴도 필요한 경우 이어서 추가


SELECT provider, COUNT(*) AS member_count
FROM member
GROUP BY provider
ORDER BY provider;

