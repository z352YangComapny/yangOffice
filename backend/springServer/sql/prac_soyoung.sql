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