package example.day07.restcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 1. 내장 톰캣( 웹서버 ) 실행 ,
// 2. 동일패키지또는하위패키지들의 MVC어노테이션(@Controller)들을 사용하는 클래스들을 스캔해서 빈 등록 (상위패키지 스캔불가능)
public class AppStart { public static void main(String[] args) { SpringApplication.run( AppStart.class );   }}