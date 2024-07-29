package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.service.AuthService;

@RestController //@Controller + @ResponseBody
@RequestMapping("/auth") // 해당 클래스내 메소드들의 공통 HTML URL
public class AuthController {

    // 스프링 컨테이너의 빈(객체) 가져오기/주입
    @Autowired AuthService authService;

    // 1. 인증번호 요청/생성
    @GetMapping("/code")
    public boolean authCode( String email ) {
        return authService.authCode( email );
    }

    // 2. 입력받은 값과 인증 번호 를 인증/비교
    @PostMapping("/check")
    public boolean authCheck( String authCodeInput ){
        return authService.authCheck( authCodeInput );
    }
}

