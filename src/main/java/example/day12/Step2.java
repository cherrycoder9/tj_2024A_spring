package example.day12;

import java.util.*;
import java.util.stream.Collectors;

public class Step2 {
    public static void main(String[] args) {

        // 1. Map 컬렉션 객체
        Map< String , Integer > map = new HashMap<>();
        // 2. 
        map.put( "유재석" , 85 );
        map.put( "홍길동" , 90 );
        map.put( "강호동" , 100 );
        map.put( "신동엽" , 90 );  // value값은 중복이 가능하다.
        map.put( "유재석" , 78 );  // key 값이 중복이면 기존 key 제거되고 교체
        System.out.println("map = " + map);
        // 3.
        int size = map.size();
        System.out.println("size = " + size);
        // 4.
        int point = map.get("강호동");
        System.out.println("point = " + point);
        // 5.
        map.remove("강호동");
        System.out.println("map = " + map);
        // 6.
        Set<String> keys = map.keySet();
        System.out.println("keys = " + keys);
        
        Collection<Integer> values = map.values();
        System.out.println("values = " + values);

        Set< Map.Entry<String,Integer> > entries = map.entrySet();
        System.out.println("entries = " + entries);
        
        // map 객체내 엔트리 순회 
        // (1)
        Iterator<String> rs = map.keySet().iterator();
        while ( rs.hasNext() ){
            String key = rs.next();
            System.out.println( key );   System.out.println( map.get( key ));
        }
        // (2) 향상된 for문
        for( String key : map.keySet() ){
            System.out.println( key );   System.out.println( map.get( key) );
        }
        for( Integer value : map.values() ){
            System.out.println("value = " + value);
        }
        // (3) forEach 문
        map.keySet().forEach( key -> {
            System.out.println( key );  System.out.println( map.get(key) );
        });
        // (4) map 문
        map.keySet().stream().map( key -> {
            System.out.println(key); System.out.println( map.get(key) );
            return key;
        }).collect( Collectors.toSet() );
    }
}
