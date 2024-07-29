package web.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {

    public Connection conn = null;
    // === DB 연동하는 부모 클래스 사용 ==== //
    public Dao(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/springweb" ,
                    "root","1234");
        }catch (Exception e ){  System.out.println( e );  }
    }

}
