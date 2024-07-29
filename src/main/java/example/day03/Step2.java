package example.day03;

public class Step2 {
    public static void main(String[] args) {

        // 1. 2개의 문자열객체 생성
        String str1 = new String("abc");    // str1 -> 302번지( 멤버변수 -> 502번지 )
        String str2 = new String("abc");    // str2 -> 402번지( 멤버변수 -> 502번지 )
        String str3 = "abc";                // str3 -> 502번지

        int i1 = 100;
        int i2 = 100;
        Integer i3 = 100;
        // 래퍼(감싼)클래스 : int형 기본타입은 메소드를 사용할수 없으므로 int형 타입도 메소드를 사용하기 위해 만들어진 참조타입
            // JAVA : Integer.parseInt( ) : 문자열 타입을 정수타입 반환 , "10" VS 10 다르다.
            // vs JS : parseInt( )
        System.out.println( str1.hashCode() );  // 96354 , String 타입의 객체 같은 경우 문자열의 저장위치를 참조
        System.out.println( str2.hashCode() );  // 96354
        System.out.println( str3.hashCode() );  // 96354    // "2개의 객체의 멤버변수와 "abc"는 동이한 저장위치를 참조한다

        System.out.println( str1 == str2 );         // false    // 302번지 == 402번지 , false
        System.out.println( str1.equals( str2 ) );  // true     // 302번지.equals( 402번지 ) , true
        System.out.println( str1 == str3 );         // false    // 302번지 == 502번지 , false
        System.out.println( str1.equals( str3 ));   // true     // 302번지.equals( 502번지 ) , true

        System.out.println( i1 == i2 ); // true
        // System.out.println( i1.equals ); // int는 참조타입이 아니므로 Object클래스로부터 상속받지 못했다.
        System.out.println( i3.equals( i1 ) ); // Integer 는 참조타입 이므로 Object클래스로부터 상속 받았으므로 .equals() 사용가능하다.

        //
        // str1.clone(); // 기본적으로 clone() 메소드 사용이 불가능
        Object object = new Object();
        // object.clone(); // 기본적으로 clone() 메소드 사용이 불가능

    }

}
