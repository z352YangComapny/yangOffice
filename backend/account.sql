--==============================
-- yangcompany
--==============================
alter session set "_oracle_script" = true;

create user yang
identified by yang
default tablespace users;

alter user yang quota unlimited on users;

grant connect, resource to yang;