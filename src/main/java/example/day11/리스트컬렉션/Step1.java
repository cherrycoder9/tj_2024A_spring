package example.day11.리스트컬렉션;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class Step1 {
    public static void main(String[] args) {

        // 1. 리스트 컬렉션
        List<String> list = new ArrayList<>();
        // ArrayList<String> list2 = new ArrayList<>();

        // 2. 리스트내 요소 추가
        list.add( "유재석" );
        list.add( "강호동" );
        list.add( "신동엽" );
        list.add( "서장훈" );
        System.out.println("list = " + list);

        // 3. 리스트내 특정 위치 요소 추가
        list.set( 0 , "박명수" );
        list.set( 3 , "김희철" );
        System.out.println("list = " + list);
        
        // 4. 리스트내 요소 개수 
        int size = list.size();
        System.out.println("size = " + size);
        
        // 5. 리스트내 특정 위치 요소 호출 
        String str1 = list.get( 1 );
        System.out.println("str1 = " + str1);
        
        // 6. 리스트내 특정 요소의 값 검색 , boolean 반환
        boolean bool1 = list.contains( "강호동" );
        System.out.println("bool1 = " + bool1);

        // 7. 리스트내 특정 요소의 값 검색 , 인덱스 반환
        int index1 = list.indexOf( "강호동" );
        System.out.println("index1 = " + index1);

        // 8. 리스트내 특정 요소 삭제
        list.remove( 1 );
        System.out.println(list);

        // 9. 리스트내 요소 순회
        // 9-1 일반 for문
        for( int i = 0 ; i < list.size() ; i++  ){
            System.out.println( list.get(i) );
        }

        // 9-2 향상된 for 문
        for( String s : list ){ System.out.println( s ); }

        // 9-3 forEach함수 , 요소의 개수를 하나씩 반환해서 반복 , return 없다.
        list.forEach( s -> { System.out.println(s); });

        // 9-4 .stream().map().collect()  ,
        // 요소의 개수를 하나씩 반환해서 반복후 결과(return) 해서 반환 , return 있다.
            // 반복하면서 return 값들을 하나의 배열/컬렉션으로 반환 받을수 있다.
            // 주로 카피/복사 할때 사용된다. 반복하면서 실행결과를 반환 받을수 있다.
        List<String> newList = list.stream()
                                .map( s -> { System.out.println(s); return s; } )
                                .collect(Collectors.toList() );
        System.out.println("newList = " + newList);
        newList.remove(0);
        System.out.println( list );
        System.out.println( newList );
        
        // ======
        List<String> vector = new Vector<>();
        vector.add("유재석");
        System.out.println("vector = " + vector);

        // ======
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new LinkedList<>();

        long startTime;
        long endTime;
        long result;

        startTime = System.nanoTime();
        for( int i = 0 ; i<10000 ; i++ ){
            list1.add( 0 ,  String.valueOf( i ) );
        }
        endTime = System.nanoTime();
        result = endTime - startTime;
        System.out.println("1만개 저장하는 ArrayList 걸린시간 :" + result );

        startTime = System.nanoTime();
        for( int i = 0 ; i<10000 ; i++ ){
            list2.add( 0 ,  String.valueOf( i ) );
        }
        endTime = System.nanoTime();
        result = endTime - startTime;
        System.out.println("1만개 저장하는 LinkedList 걸린시간:" + result );



        

    }
}








