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