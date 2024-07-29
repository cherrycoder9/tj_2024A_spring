package example.day13;

import java.util.TreeSet;

public class Step3 {
    public static void main(String[] args) {

        // 1.
        TreeSet< Member > members = new TreeSet<>();
            // 오류:class example.day13.Member cannot be cast to class java.lang.Comparable
        // 2.
        members.add( new Member("홍길동" , 29 ) );
        members.add( new Member("유재석" , 40 ) );
        members.add( new Member("강호동" , 35 ) );
        members.add( new Member("신동엽" , 24 ) );
        System.out.println("members = " + members);
    }
}
