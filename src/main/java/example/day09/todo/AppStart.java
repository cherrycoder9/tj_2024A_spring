package example.day09.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// [2] Web ( springboot )
@SpringBootApplication
// 1. 내장톰캣(웹서버) 실행 2. restful 지원 3. 스프링MVC 지원
public class AppStart{
    public static void main(String[] args) {
        SpringApplication.run( AppStart.class );
    }
}
// [1] Console
/*
public class AppStart {
    public static void main(String[] args) {
        // 1. &#xD560;&#xC77C; &#xB4F1;&#xB85D;      , &#xC8FC;&#xACE0;( &#xD560;&#xC77C;&#xB0B4;&#xC6A9; ) &#xBC1B;&#xAE30;( &#xACB0;&#xACFC; )
        TodoController.getInstance().todoCreate("&#xD30C;&#xC774;&#xC36C;&#xACF5;&#xBD80;");
        // 2. &#xD560;&#xC77C; &#xC804;&#xCCB4; &#xCD9C;&#xB825;  , &#xC8FC;&#xACE0;( x ) &#xBC1B;&#xAE30;( &#xD560;&#xC77C;&#xB9AC;&#xC2A4;&#xD2B8; )
        ArrayList<TodoDto> result
                = TodoController.getInstance().todoReadAll();
        System.out.println( result );
        // 3. &#xD560;&#xC77C; (&#xC0C1;&#xD0DC;) &#xC218;&#xC815; , &#xC8FC;&#xACE0;( &#xC218;&#xC815;&#xD560;&#xD560;&#xC77C;&#xBC88;&#xD638; ) &#xBC1B;&#xAE30;( &#xACB0;&#xACFC; )
        TodoController.getInstance().todoUpdate( 3 );
        // 4. &#xD560;&#xC77C; &#xC0AD;&#xC81C;       ,  &#xC8FC;&#xACE0;( &#xC0AD;&#xC81C;&#xD560;&#xD560;&#xC77C;&#xBC88;&#xD638; ) &#xBC1B;&#xAE30;( &#xACB0;&#xACFC; )
        TodoController.getInstance().todoDelete( 2 );
    }
}
*/
