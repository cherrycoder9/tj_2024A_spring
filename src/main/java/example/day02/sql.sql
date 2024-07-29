# [1] 
drop database if exists springexample;
create database springexample;
use springexample;
# [2]
drop table if exists phonebook;
create table phonebook(
	id int auto_increment , 
    name varchar(20) , 
    phone varchar(15),
	primary key ( id )
);
# [3] 레코드 등록 
insert into phonebook( name , phone ) values ( '유재석' , '010-3333-3333' );
# [4] 레코드 조회
select * from phonebook;
