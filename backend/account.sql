--===================================
-- 관리자계정 - spring계정 생성 v0.0.1
--===================================
alter session set "_oracle_script" = true;

create user yang
identified by yang
default tablespace users;

alter user yang quota unlimited on users;

grant connect, resource to yang;

--===================================
-- 관리자계정 - spring계정 생성 v1.0.1
--===================================

--member-service (MySql)
create user 'yang'@'%' identified by 'yang';
GRANT ALL PRIVILEGES ON *.* TO 'yang'@'%';
create database ssoystory_member;
use ssoystory_member;
select * from ssoystory_member;

--feed-service (MySql)
create user 'yang'@'%' identified by 'yang';
GRANT ALL PRIVILEGES ON *.* TO 'yang'@'%';
create database ssoystory_feed;
use ssoystory_feed;
select * from ssoystory_feed;

--dm-service
create user 'yang'@'%' identified by 'yang';
GRANT ALL PRIVILEGES ON *.* TO 'yang'@'%';
create database ssoystory_dm;
use ssoystory_dm;
select * from ssoystory_dm;

--story-service
create user 'yang'@'%' identified by 'yang';
GRANT ALL PRIVILEGES ON *.* TO 'yang'@'%';
create database ssoystory_story;
use ssoystory_story;
select * from ssoystory_story;

--Game (MongoDB)


