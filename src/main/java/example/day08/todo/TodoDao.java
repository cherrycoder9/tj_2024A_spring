package example.day08.todo;

import example.day02.consolemvc.model.dao.PhoneDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TodoDao {
    // [1] 싱글톤 패턴
    private static TodoDao todoDao = new TodoDao();
    private TodoDao(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springexample" , "root","1234");
        }catch (Exception e ){  System.out.println( e );  }
    }
    public static TodoDao getInstance( ){ return todoDao;  }
    // [2] JDBC 인터페이스
    private Connection conn; private PreparedStatement ps; private ResultSet rs;

    // 1. 할일 등록
    public boolean todoCreate( String tcontent ){
        try{ String sql ="insert into todolist(tcontent) values( ? )";
            ps = conn.prepareStatement(sql);
            ps.setString( 1 , tcontent );
            int count = ps.executeUpdate();
            if( count == 1 ) return true;
        }catch (Exception e ){  System.out.println( e );   }
        return false;
    }
    // 2. 할일 전체 출력
    public ArrayList<TodoDto> todoReadAll( ){
        ArrayList<TodoDto> list = new ArrayList<>();
        try{ String sql ="select * from todolist";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while ( rs.next() ){
                TodoDto todoDto = new TodoDto(
                        rs.getInt("tno") ,
                        rs.getString("tcontent"),
                        rs.getInt("tstate")
                );
                list.add( todoDto );
            }
        }catch (Exception e ){  System.out.println( e );   }
        return list;
    }
    // 3. 할일 (상태) 수정
    public boolean todoUpdate( int tno ){
        try{ // 수정전에 수정할 할일 상태 조회
            String sql2 = "select * from todolist where tno = ?";
            ps = conn.prepareStatement(sql2);
            ps.setInt( 1 , tno );
            rs = ps.executeQuery();
            if( rs.next() ){
                // 기존 상태 출력
                int tstate = rs.getInt("tstate");
                // 상태 변경 : 기존상태가 0이면 1 , 1이면 0 으로 변경
                tstate = tstate == 0 ? 1 : 0;
                // 수정 SQL
                String sql ="update todolist set tstate = ? where tno = ? ";
                ps = conn.prepareStatement(sql);
                ps.setInt( 1 , tstate );
                ps.setInt( 2 , tno );
                int count = ps.executeUpdate();
                if( count == 1 ) return true;
            }
        }catch (Exception e ){  System.out.println( e );   }
        return false;
    }
    // 4. 할일 삭제
    public boolean todoDelete( int tno ){
        try{ String sql ="delete from todolist where tno = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt( 1 , tno );
            int count = ps.executeUpdate();
            if( count == 1 ) return true;
        }catch (Exception e ){  System.out.println( e );   }
        return false;
    }
}















