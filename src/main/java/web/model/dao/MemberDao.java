package web.model.dao;

import org.springframework.stereotype.Component;
import web.model.dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

@Component
public class MemberDao extends Dao {
    // 1. 회원가입
    public boolean mSignup( MemberDto memberDto ){
        System.out.println("MemberDao.mSignup");
        System.out.println("memberDto = " + memberDto);
        try{
            String sql = "insert into member( id , pw , name , email , phone ) values( ? , ? , ? , ? , ? )";
            PreparedStatement ps = super.conn.prepareStatement(sql);
            ps.setString( 1 , memberDto.getId() );
            ps.setString( 2 , memberDto.getPw() );
            ps.setString( 3 , memberDto.getName() );
            ps.setString( 4 , memberDto.getEmail() );
            ps.setString( 5 , memberDto.getPhone() );
            int count = ps.executeUpdate();
            if( count == 1 ) return true;
        }catch (Exception e ){  System.out.println("e = " + e);     }
        return false;
    }
    // 2. 로그인 : 로그인 성공한 회원번호 반환( 세션에 저장할려고 )
    public int mLogin( MemberDto memberDto ){
        System.out.println("MemberDao.mLogin");
        System.out.println("memberDto = " + memberDto);
        try{String sql = "select * from member where id = ? and pw =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString( 1 , memberDto.getId() );
            ps.setString( 2 , memberDto.getPw() );
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){ return rs.getInt("no"); }
        }catch (Exception e ){ System.out.println(e);   }
        return 0; // 0 은 회원번호가 없다 뜻
    }
    // 5. 마이페이지 정보
    public MemberDto mMyInfo( int loginMno ){
        try{ String sql ="select * from member where no = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt( 1 , loginMno );
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                return MemberDto.builder()
                        .no( rs.getInt("no") )
                        .id( rs.getString("id"))
                        .phone( rs.getString("phone"))
                        .email( rs.getString("email"))
                        .name( rs.getString("name"))
                        .build();
            }
        }catch (Exception e ){  System.out.println(e);  } return null;
    }
    // 6. 아이디 중복 검사 ( C *R U D )
    public boolean mIdCheck( String id ){  System.out.println("MemberDao.mIdCheck"); System.out.println("id = " + id);
        // sql는 대소문자를 구분하지 않고 검색 진행.
        try{ String sql = "select id from member where binary(id) = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString( 1 , id );
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){ return true; } // 중복이다.
        }catch (Exception e ){ System.out.println(e); }
        return false; // 중복이 아니다.
    }
    // [7]. 회원 탈퇴
    public boolean mLeave( int loginNo , String pwConfirm ){
        try{String sql ="delete from member where no = ? and pw = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt( 1 , loginNo );
            ps.setString( 2, pwConfirm );
            int count = ps.executeUpdate();
            if( count == 1 ) return true;
        }catch (Exception e ){ System.out.println("e = " + e);}
        return false;
    }
    // [8]
    public boolean mUpdate( Map<String , String> map ){
        try{
            String sql = "update member " +
                    " set name = ? , pw = ? , phone = ? " +
                    " where no = ? and pw = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString( 1 , map.get("name") );
            ps.setString( 2 , map.get("newPw") );
            ps.setString( 3 , map.get("phone") );
            ps.setInt( 4 , Integer.parseInt( map.get("no") ) );
            ps.setString( 5 , map.get("pw") );
            int count = ps.executeUpdate();
            if( count == 1 ) { return true; }
        }catch (Exception e ){ System.out.println(e); }
        return false;
    }
}


















