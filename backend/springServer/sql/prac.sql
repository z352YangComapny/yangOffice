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



