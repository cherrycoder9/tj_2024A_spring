package example.day08.todo;

import org.springframework.web.bind.annotation.*;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

@RestController // 해당 클래스가 스프링MVC 패턴에서 controller 역할 , 스프링 컨테이너(JVM저장소) 빈(객체) 등록
public class TodoController {
    // ( console 할때만 사용 ) [1] 싱글톤 패턴  : JVM 메소드영역에 static 변수에 객체주소값 저장
//    private static TodoController todoController = new TodoController();
//    private TodoController(){ }
//    public static TodoController getInstance( ){ return todoController;  }
    // 1. 할일 등록
    @PostMapping("/todo/create")
    // [POST] http://localhost:8080/todo/create?tcontent=책읽기
    public boolean todoCreate( String tcontent ){
        boolean result = TodoDao.getInstance().todoCreate( tcontent );
        return result;
    }
    // 2. 할일 전체 출력
    @GetMapping("/todo/readall")
    // [GET] http://localhost:8080/todo/readall
    public ArrayList<TodoDto> todoReadAll( ){
        ArrayList<TodoDto> result = TodoDao.getInstance().todoReadAll();
        return result;
    }
    // 3. 할일 (상태) 수정
    // [UPDATE]
    @PutMapping("/todo/update")
    // [PUT] http://localhost:8080/todo/update?tno=3
    public boolean todoUpdate(  int tno ){
        boolean result = TodoDao.getInstance().todoUpdate( tno );
        return result;
    }
    // 4. 할일 삭제
    @DeleteMapping("/todo/delete")
    // [Delete] http://localhost:8080/todo/delete?tno=2
    public boolean todoDelete( int tno ){
        boolean result = TodoDao.getInstance().todoDelete( tno );
        return result;
    }

}
