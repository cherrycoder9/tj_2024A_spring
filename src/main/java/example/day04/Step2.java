package example.day04;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Step2 {
    public static void main(String[] args) {

        // 문자열 에서 자주 사용되는 함수
        // [1] 문자열.charAt(인덱스) : 해당 인덱스번호의 문자 1개 반환 함수
        String ssn = "012312-1230123";
        char gender = ssn.charAt( 7 );
        switch ( gender ){
            case '1':
            case '3': System.out.println("남자"); break;
            case '2':
            case '4':  System.out.println("여자"); break;
        }
        // 활용 : Scanner 에서 문자 입력 메소드가 없다.
        Scanner scan = new Scanner(System.in);
        char _char = scan.next().charAt(0); // 문자 1개 입력받기 
        System.out.println("_char = " + _char);

        // [2] 문자열.length() : 문자열의 길이 반환 함수
        System.out.println( ssn.length() );
        // 활용 : for문과 활용하면 문자1개 씩 순회
        for( int i = 0 ; i<ssn.length() ; i++ ){
            System.out.println( ssn.charAt( i ) ); // 문자열내 i번째 인덱스의 문자 1개씩 출력 
        }
        
        // [3] 문자열.replace( 기존문자열 , 변경할문자열 );
        // - 문자열내 기존문자열이 존재하면 변경할문자열로 치환/교체 된 새로운문자열 반환 함수
        String oldStr ="자바의 문자열은 불변입니다. 자바 문자열은 String 입니다.";
        String newStr = oldStr.replace( "자바" , "JAVA" ); // 자바 --> JAVA 치환/교체
        System.out.println("oldStr = " + oldStr );
        System.out.println("newStr = " + newStr );
        // 활용 : 서로 다른 언어들간의 문법 치환/교체 , HTML 줄바꿈<br/> 자바 \n
        String htmlStr = "안녕하세요<br/>유재석";
        System.out.println("htmlStr = " + htmlStr);
        String javaStr = htmlStr.replace( "<br/>" , "\n" );
        System.out.println("javaStr = " + javaStr);

        // [4] 문자열.subString( 시작인덱스 , [끝인덱스] ) : 문자열을 인덱스 기준으로 잘라낸 새로운 문자열 반환
            // ssn = 012312-1230123;
        String firstNum = ssn.substring( 0 , 6 );   // 0번 인덱스부터 6번 인덱스전까지 추출 
        System.out.println("firstNum = " + firstNum);
        String endNum = ssn.substring( 7 ); // 7번 인덱스부터 끝까지(끝인덱스를 생략 가능 )
        System.out.println("endNum = " + endNum);
        // [5] 문자열.split("구분 문자") : 문자열내 구분문자 기준으로 분해 해서 새로운 문자열 배열 반환
        String[] strArray = ssn.split("-"); // "-" 기준으로 문자열 분해
            // Arrays.toString( 배열변수명 ) : 해당 배열의 요소들의 값들을 문자열로 반환
        System.out.println("Arrays.toString( strArray)  = " + Arrays.toString( strArray) );
        System.out.println( "[0] : " + strArray[0] );   // 012312
        System.out.println( "[1] : " + strArray[1] );   // 1230123
        // 활용 : * CSV 형식의 문자열 다루기
        String csvStr = "유재석,80,90,100\n강호동,70,50,90\n신동엽,30,60,40";
            // - 행구분자인 \n 기준으로 분해 해서 배열로 받기
        String[] rowsStr = csvStr.split("\n");  System.out.println( Arrays.toString( rowsStr ) );
            // - 열구분자인 , 기준으로 분해 해서 배열로 받기
        for( int i = 0 ; i < rowsStr.length ; i++ ){
            String[] colsStr = rowsStr[i].split(","); System.out.println(Arrays.toString( colsStr ));
            for( int j = 0 ; j < colsStr.length ; j++ ){
                System.out.println( colsStr[j] );
            }
        }

        // [6] 문자열.indexOf("찾을문자열")       : 문자열내 찾을문자가 존재하면 찾은문자열의 인덱스 반환 , 없으면 -1 반환
        String subject = "자바 프로그래밍 언어";

        int findIndex = subject.indexOf("자바"); // 찾은 문자열의 인덱스 번호 반환
        System.out.println("findIndex = " + findIndex); // 자바 : 0 , 프로 : 3 , 파이썬 : -1

        // [7] 문자열.contains("찾을문자열")      : 문자열내 찾을문자가 존재하면 true 반환 , 없으면 false 반환
        boolean findCheck = subject.contains("자바");
        System.out.println("findCheck = " + findCheck); // 자바 : true , 프로 : true , 파이썬 : false

        // [8] 문자열.getBytes()                 : 문자열내 문자 하나씩 바이트로 변환된 바이트 배열로 반환
        byte[] bytes = subject.getBytes();
        System.out.println(" Arrays.toString( bytes )  = " +  Arrays.toString( bytes ) );

        // 문자 와 바이트 관계
            // 1. 영문 / 특정 특수문자 : 문자1개당 1바이트
            // 2. 한글 : 문자1개당 2바이트 + 규칙1(UTF-8-인코딩헤더규칙)바이트 => 총 3바이트
        byte _byte1 = 'a';
        System.out.println("_byte1 = " + _byte1);   // a , 97 // 유니코드(아스키코드기반)
        _byte1++;
        System.out.println("_byte1 = " + _byte1);   // b , 98
        System.out.println("(char)_byte1  = " + (char)_byte1 ); // b
        System.out.println( "a".getBytes().length );    // 1 : 영문
        System.out.println( "가".getBytes().length );    // 3 : 한글
            // - char : 문자1개 표현 , 65536글자 표현 , 부호:signed 부호없음:unsigned vs short : +-3만
        char _char1 = '가';
        System.out.println("_char1 = " + _char1);       // 가
        System.out.println("_char1 = " + (int)_char1 ); // 44032
        char _char2 = 'a';
        System.out.println("_char2 = " + (int)_char2 );
        // [활용] 난수 문자열을 만들기 ,
        System.out.println( new Random().nextInt() ); // int타입의 허용범위내 난수 생성
        System.out.println( new Random().nextInt(26) ); // 0 ~ 25 사의 난수 생성
        System.out.println( new Random().nextInt(26)+97 ); // 97 ~ 122 사의 난수 생성
        System.out.println( (char)(new Random().nextInt(26)+97 ) ); // a ~ z 의 영소문자 난수 생성
        String newPwd = "";
        for( int i = 0 ; i<10 ; i++ ){
            newPwd += (char)(new Random().nextInt(26)+97);
        }
        System.out.println( newPwd );

    } // main end
} // class end











