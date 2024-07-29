# 조작어 : DML =======================================
# [2] 로그인
select * from member where id = 'qwe' and pw ='qwe';
# select * from member where id = ? and pw =?;
# [3] 내정보 검색 , 중복없는 식별 필드
select * from member where no = 1;
# select * from member where no = ?;
select * from member;
# [4] 아이디 중복검사
select * from member where id = 'qwe123';
select * from member where id = 'QWE123';
	# 만일 대소문자를 구분하는 데이터 검색 할때는 binary( 필드 ) 사용
    # binary( 필드 ) : 문자 가 아닌 바이트를 기준으로 비교,검색 한다.
select * from member where binary( id ) = 'qwe123';	# 소문자qwe
select * from member where binary( id ) = 'QWE123';	# 대문자qwe
	# JDBC : select * from member where binary( id ) = ? ;
# [5] 탈퇴
delete from member where no = 2;
delete from member where no = 2 and pw = '1234';	# 패스워드는 중복이 가능하므로 식별 역할이 불가능하다.
	# JDBC : delete from member where no = ? and pw = ? ;









