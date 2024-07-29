package example.day07.restcontroller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class RestController2 {
    // [1] HTTP GET
    @RequestMapping( value = "/example/rest2" , method = RequestMethod.GET)
    @ResponseBody   // 응답할 데이터의 자바타입을 HTTP타입으로 자동 타입 설정 : (java) String -> (HTTP) text/plain;
    public String getRest2(HttpServletRequest request ){
        System.out.println("RestController2.getRest2");
        // 1.요청
        String key = request.getParameter("key");
        System.out.println("key = " + key);
        // 2.응답   // 2-1 메소드의 반환타입을 정의한다.
        return "[GET]ClientHi";  // 2-2 @ResponseBody 메소드위에 정의한다.
    }
    // [2] HTTP POST
    @RequestMapping( value = "/example/rest2" , method = RequestMethod.POST )
    @ResponseBody    // 응답할 데이터의 자바타입을 HTTP타입으로 자동 타입 설정 : (java) [] -> (HTTP) application/json;
    //public String[] getPost2( HttpServletRequest request ){
    //public ArrayList<String> getPost2( HttpServletRequest request ){
    //public RestDto getPost2( HttpServletRequest request ){
    public ArrayList< RestDto > getPost2( HttpServletRequest request ){
        System.out.println("RestController2.getPost2");
        String key = request.getParameter("key");   System.out.println("key = " + key);
        // (1) 배열[] 타입      -> [ "[POST]","ClientHi" ]
        // String[] strArray = new String[2];
        // strArray[0] = "[POST]"; strArray[1] ="ClientHi";
        // (2) 리스트 타입       -> [ "[POST]","ClientHi" ]
        // ArrayList<String> strArray = new ArrayList<>();
        // strArray.add("[POST]"); strArray.add("ClientHi");
        // (3) 객체(DTO) 타입   -> {  "key1": "[POST]", "key2": "ClientHi"  }
        // RestDto strArray = new RestDto("[POST]" , "ClientHi");
        // (4) 리스트 안에 DTO 타입 -> [ { "key1": "[POST1]", "key2": "ClientHi1" }, { "key1": "[POST2]","key2": "ClientHi2"} ]
        ArrayList< RestDto > strArray = new ArrayList<>();
        strArray.add( new RestDto("[POST1]" , "ClientHi1") );
        strArray.add( new RestDto("[POST2]" , "ClientHi2") );
        return strArray;
    }
    // [3] HTTP PUT
    @RequestMapping(value = "/example/rest2" , method = RequestMethod.PUT)
    @ResponseBody
    public int putRest2( HttpServletRequest request ){
        System.out.println("RestController2.putRest2");
        String key = request.getParameter("key");
        System.out.println("key = " + key);
        return 10+10; // application/json
    }

    // [4] HTTP delete
    @RequestMapping( value = "/example/rest2" , method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteRest2(HttpServletRequest request ){
        System.out.println("RestController2.deleteRest2");
        String key = request.getParameter("key");
        System.out.println("key = " + key);
        return true; // application/json
    }
}














