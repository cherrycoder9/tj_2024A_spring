package example.day07.restcontroller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// @Controller
@RestController //   @Controller + @ResponseBody : 해당 클래스의 메소드들은 모두 @ResponseBody 적용된다.
@RequestMapping(value = "/example") // 컨트롤러 클래스 매핑 : 해당 클래스의 메소들의 공통 URL 정의한다.
public class RestController3 {
    //[1] HTTP GET
    // @RequestMapping( value = "/example/rest3" , method = RequestMethod.GET )
    @GetMapping("/rest3")   // @GetMapping("HTTP URL정의") : HTTP GET메소드의 URL 정의
    public String getRest3(HttpServletRequest request ){
        System.out.println("RestController3.getRest3");
        String key = request.getParameter("key");   System.out.println("key = " + key);
        return "[GET]ClientHi";
    }

    //[2] HTTP POST
    @PostMapping("/rest3")  // @PostMapping("HTTP URL정의") : HTTP POST메소드의 URL 정의
    public RestDto postRest3(HttpServletRequest request ){
        System.out.println("RestController3.postRest3");
        String key = request.getParameter("key");   System.out.println("key = " + key);
        RestDto restDto = new RestDto("[POST]" , "ClientHi");
        return restDto;
    }
    //[3] HTTP PUT
    @PutMapping("/rest3")
    public int putRest3( HttpServletRequest request ){
        System.out.println("RestController3.putRest3");
        String key = request.getParameter("key");   System.out.println("key = " + key);
        return 10+10;
    }
    //[4] HTTP DELETE
    @DeleteMapping("/rest3")
    public boolean deleteRest3( HttpServletRequest request ){
        System.out.println("RestController3.deleteRest3");
        String key = request.getParameter("key");   System.out.println("key = " + key);
        return true;
    }
}
