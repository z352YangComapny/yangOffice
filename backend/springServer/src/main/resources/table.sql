create table member (
    id number,
    username varchar2(50),
    password varchar2(300) not null,
    name varchar2(50) not null,
    gender char(1),
    email varchar2(200),
    phone varchar2(20) not null,
    auth varchar2(60) default 'USER' not null,
    provider varchar2(50),
    reg_date date default sysdate,
    constraints pk_member_id primary key(id),
    constraints ck_member_gender check(gender in ('M', 'F'))
);
delete from member where username='test1';
select * from member where username='test1';
create sequence seq_member_id;
drop table member;
drop sequence seq_member_id;
select * from member;