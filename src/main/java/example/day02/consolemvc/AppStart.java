package example.day02.consolemvc;

import example.day02.consolemvc.view.PhoneView;

public class AppStart {
    public static void main(String[] args) {
        PhoneView.getInstance().run();
    }
}
/*
    - 서로 다른 클래스들 간의 메소드를 호출하는 방법
    1. 인스턴스 생성 후 메소드 호출
        클래스명 변수명 = new 생성자명()
        변수명.메소드명();
    2. 인스턴스 생성 후 메소드 호출
        new 생성자명().메소드명();
    3. [싱글톤] 미리 인스턴스 만들고 미리 만든 인스턴스 호출 후 메소드 호출
        클래스명.getInstance().메소드명();
    4. 메소드 가 static으로 선언되었을때
        클래스명.메소드명();
*/
