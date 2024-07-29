package example.day12;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Step3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List< User > list = new LinkedList<>();
        while(true){
            System.out.print("1.등록 2.전체출력 3.수정 4.삭제 ");
            int ch = scan.nextInt();
            if( ch ==1 ){
                System.out.print(" 이름 : "); String name = scan.next();
                System.out.print(" 나이 : "); int age = scan.nextInt();
                User user = new User();
                user.name = name ; user.age = age;
                list.add( user );
            }
            if( ch ==2 ){
                for( int i = 0 ; i<list.size() ; i++ ){
                    System.out.printf(" %d\t%s\t%d\n" , i , list.get(i).name , list.get(i).age );
                }
            }
            if( ch ==3 ){
                System.out.print("수정할 인덱스:"); int index = scan.nextInt();
                try{
                    User user = list.get(index);
                    System.out.print("수정할 나이 : "); int age = scan.nextInt();
                    user.age = age;
                }catch ( IndexOutOfBoundsException e ){  System.out.println("존재하지 않는 인덱스 ");   }
            }
            if( ch ==4 ){
                System.out.print("삭제할 인덱스:"); int index = scan.nextInt();
                try{  list.remove(index);
                }catch ( IndexOutOfBoundsException  e ){  System.out.println("존재하지 않는 인덱스 "); }
            }
        } // w end
    } // main emd
} // class end
