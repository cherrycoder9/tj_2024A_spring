package example.day08.thread;

import java.util.Scanner;

public class Step2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // 1. Thread 객체 생성
        DigitalTime digitalTime = new DigitalTime();
        // 2. Thread 실행
        digitalTime.start();

        //
        Timer timer = null;

        while ( true ){
            System.out.print("1.start 2.stop : ");
            int ch = scan.nextInt();
            if( ch == 1 ){
                timer = new Timer();
                Thread thread = new Thread( timer );
                thread.start();
            }
            if( ch == 2 ){
                timer.state = false;
            }
        }
    }
}
