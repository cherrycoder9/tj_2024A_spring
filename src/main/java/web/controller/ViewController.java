package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// == AJAX 통신용이 아닌 템플릿 반환 하는 컨트롤러== //
// @RestController // @Controller + @ResponseBody( 응답 JSON객체 )
@Controller     // JSON객체가 아닌 템플릿 파일 반환 하므로 @ResponseBody 없이 사용
public class ViewController {

    // ======== [1] 레이아웃 =============== //
    @GetMapping("/")    // http://localhost:8080  // 페이지 요청은 HTTP의 GET 방식을 주로 사용된다.
    public String index(){
        return "/index.html";   // templates 폴더내 반환할 경로와 파일명
    }
    // ======== [2] 회원 관련 =================== //
    @GetMapping("/member/signup")
    public String mSignup(){
        return "/member/signup.html";
    }
    @GetMapping("/member/login")
    public String mLogin(){
        return "/member/login.html";
    }
    @GetMapping("/member/mypage")
    public String mMyPage(){
        return "/member/myinfo.html";
    }
    @GetMapping("/member/update")
    public String mUpdate(){   return "/member/update.html";  }

    @GetMapping("/member/leave")
    public String mLeave(){  return "/member/leave.html";   }

    // ======== [3] 게시판 관련 =================== //
    @GetMapping("/board")   // 전체 게시물 페이지
    public String board(){  return "/board/board.html";   }

    @GetMapping("/board/write") // 게시물 쓰기 페이지
    public String bWrite(){  return "/board/write.html";   }

    @GetMapping("/board/update") // 게시물 수정 페이지
    public String bUpdate(){  return "/board/update.html";   }

    @GetMapping("/board/view") // 게시물 상세 페이지
    public String bView(){  return "/board/view.html";   }

    // ======== [4] api 관련 =================== //
    @GetMapping("/api")
    public String api(){ return "/api/datago.html";}

}















