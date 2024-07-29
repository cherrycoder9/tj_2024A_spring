package example.day13;

public class Member implements Comparable<Member> {
    String name;
    int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // * Comparable인터페이스의 추상메소드 정의
    @Override
    public int compareTo(Member o) {
        // 1. name 기준으로 정렬 , String 클래스내 compareTo 활용
        // return this.name.compareTo( o.name );
        // 2. age 기준으로 정렬  , int 기본타입 이므로 직접 정렬기준 비교하기
            // 오름차순 : -1 :매개변수보다 더 작으면  , 0 : 같으면  , 1 : 매개변수보다 크면
            // 내림차순 : 1 :매개변수보다 더 작으면  , 0 : 같으면  , -1 : 매개변수보다 크면
        if( this.age < o.age ) { return -1; }
        else if( this.age == o.age ) { return 0; }
        else return 1;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
