drop database if exists springexample;
create database springexample;
use springexample;
drop table if exists todolist;
create table todolist(
	tno int auto_increment, tcontent varchar(20), tstate int default 0,
	primary key(tno)
);
select * from todolist;
#샘플 
insert into todolist(tcontent) values("밥먹기");
insert into todolist(tcontent) values("공부");
insert into todolist(tcontent) values("운동");
# 1. 할일 등록 
insert into todolist(tcontent) values("자바공부");
# insert into todolist(tcontent) values( ? );

# 2. 할일 전체 출력
select * from todolist;

# 3. 할일 (상태) 수정
update todolist set tstate = 1 where tno = 1;
# update todolist set tstate = ? where tno = ?;

# 4. 할일 삭제
delete from todolist where tno = 1;
# delete from todolist where tno = ?;

select * from todolist;

