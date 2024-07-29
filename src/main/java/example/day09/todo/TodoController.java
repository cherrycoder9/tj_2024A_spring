package example.day09.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController // 해당 클래스가 스프링MVC 패턴에서 controller 역할 , 스프링 컨테이너(JVM저장소) 빈(객체) 등록
public class TodoController {

    @Autowired TodoService todoService;

    // 1. 할일 등록
    @PostMapping("/todo/create")
    public boolean todoCreate( String tcontent ){
        System.out.println( todoService );
        boolean result = todoService.todoCreate( tcontent );
        return result;
    }
    // 2. 할일 전체 출력
    @GetMapping("/todo/readall")
    public ArrayList<TodoDto> todoReadAll( ){
        ArrayList<TodoDto> result = todoService.todoReadAll();
        return result;
    }
    // 3. 할일 (상태) 수정
    // [UPDATE]
    @PutMapping("/todo/update")
    public boolean todoUpdate(  int tno ){
        boolean result = todoService.todoUpdate( tno );
        return result;
    }
    // 4. 할일 삭제
    @DeleteMapping("/todo/delete")
    public boolean todoDelete( int tno ){
        boolean result = todoService.todoDelete( tno );
        return result;
    }

}
