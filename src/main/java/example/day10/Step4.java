package example.day10;

public class Step4 {
    public static void main(String[] args) {

        MyList<String> list = new MyList<>();
        list.add( "텍스트1" );
        list.add( "텍스트2" );
        System.out.println( list );
        System.out.println( list.get(0) );
        System.out.println( list.get(3) );
        list.remove( 0 );
        System.out.println( list );


        MyList<Integer> list2 = new MyList<>();
        list2.add( 1 );
        list2.add( 2 );
        System.out.println( list2 );
        System.out.println( list2.get(1) );
        list2.remove( 0 );
        System.out.println( list2 );

    }
}
