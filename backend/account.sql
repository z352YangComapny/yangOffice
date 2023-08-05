--==============================
-- 관리자계정 - spring계정 생성
--==============================
alter session set "_oracle_script" = true;

create user yang
identified by yang
default tablespace users;

alter user yang quota unlimited on users;

grant connect, resource to yang;