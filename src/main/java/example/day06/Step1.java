package example.day06;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Step1 {
    public static void main(String[] args) {
        
        // - 래퍼클래스 : 기본타입 --> 참조타입 , 기본타입을 참조타입으로 만들기 
            // - 기본타입을 참조타입으로 사용해야 되는 경우가 있다.
                // - 기본타입은 클래스가 아니므로 멤버 변수나 메소드를 포함하지 않는다.
                // - 기본타입의 데이터가 메소드/기능 를 사용해야 되는 경우가 있을수 있다. ( 주로. 타입변환 )
        int value1 = 100;                       // 기본타입 100
        // value1.                              // 도트 연산자를 사용할수 없다.
        Integer value2 = new Integer( 100 );    // 참조타입 100 , 래퍼 클래스 
        value2.intValue();                      // 도트 연산자를 사용할수 있다. ( 참조타입 , 클래스 )
        
        // - 자주 사용되는 메소드
            // 1. .intValue(); : 기본타입의 값을 반환하는 메소드
        int myValue = value2.intValue();
        System.out.println("myValue = " + myValue);
            // 2.  Integer.valueOf( 정수또는문자열 ) : Integer객체 반환 메소드
        Integer value3 = Integer.valueOf( "100" );
        Integer value4 = Integer.valueOf( 100 );
            // 3. * Integer.parseInt( 문자열 ) : int타입 반환 메소드
        // === 문자열 타입을 기본 타입으로 변환 : 주로 자바외 데이터를 가져올때 === //
        int value5 = Integer.parseInt("100");
        double value6 = Double.parseDouble("3.14");
        float value7 = Float.parseFloat("3.14");
        byte value8 = Byte.parseByte("100");
        short value9 = Short.parseShort("30000");
        long value10 = Long.parseLong("3000000");
            // 4. 오토박싱 , 언박싱
        int value11 = value1 + value2;  // 기본타입 + 참조타입(언박싱)
        System.out.println("value11 = " + value11);
        Integer value12 = value11;      // 참조타입 = 기본타입 ( 오토박싱 )
        
        // =============================================
        //[1]
        String s = new String();
        Class c = s.getClass();
        System.out.println("c.toString() = " + c.toString());       // class java.lang.String
        //[2]
        Class c2 = String.class;
        System.out.println("c2.toString()  = " + c2.toString() );   // class java.lang.String
        //[3]
        try {   Class c3 = Class.forName("java.lang.String");       // class java.lang.String
                System.out.println("c3.toString() = " + c3.toString());
        }catch (Exception e){ System.out.println("e = " + e);  }
        // --- JDBC 
        try { 
            Class c4 = Class.forName("com.mysql.cj.jdbc.Driver");   // class com.mysql.cj.jdbc.Driver
            System.out.println("c4.toString() = " + c4.toString());
        }catch (Exception e ){ System.out.println("e = " + e);  }

        // -- 내가 만든 클래스
        Person person = new Person();

        Class class1 = person.getClass();
        System.out.println("class1.toString() = " + class1.toString());

        Class class2 = Person.class;
        System.out.println("class2.toString() = " + class2.toString());
        try { // Class.forName( 패키지명.클래스명 );
            Class class3 = Class.forName("example.day06.Person");
        }catch (Exception e ){  System.out.println(e);     }

        //-------------------
            // 1. - String 클래스의 모든 생성자 정보( 선언부 ) 호출
        Constructor[] cons = c.getConstructors();
        for( Constructor con : cons ){ System.out.println("con = " + con); }

            // 2. - String 클래스의 모든 필드 정보( 선언부 ) 호출
        Field[] fields = c.getFields();
        for( Field f : fields ){ System.out.println("f = " + f); }

            // 3. - String 클래스의 모든 함수 정보( 선언부 ) 호출
        Method[] methods = c.getMethods();
        for( Method m : methods ){   System.out.println("m = " + m); }

        // ------------------
        Person p2 = new Person();
        System.out.println("p2 = " + p2);
        try {
            Class pClass = Class.forName("example.day06.Person");
            Person p3 = (Person)pClass.newInstance();
            System.out.println("p3 = " + p3);
        }catch (Exception e ){   System.out.println("e = " + e);  }























        
        
        
        
        
        










        
    }
}
