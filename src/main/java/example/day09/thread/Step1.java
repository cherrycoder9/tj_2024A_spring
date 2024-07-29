package example.day09.thread;

public class Step1 {
    // 메인 thread 제공받는다.
    public static void main(String[] args) {

        // 1. 현재 코드를 읽어드리는 스레드의 이름 호출
        Thread thread = Thread.currentThread();
        System.out.println("해당코드를 읽어드리는 스레드명 : " + thread.getName() ); // main

        // 2. 여러개의 스레드를 만들어서 스레드 이름 확인
        for( int i = 1 ; i<=5 ; i++ ){ //  지역변수 i 는 main스레드의 지역변수
            Thread threadA = new Thread(){
                @Override
                public void run() { // mian 스레드 아닌 해당 각 스레드의 스택영역
                    // System.out.println( i ); // mian스레드의; i지역변수 호출 불가능
                    Thread thread = Thread.currentThread();
                    //thread.setName("내가만든 작업스레드"); // 스레드 이름 정의하기.
                    System.out.println("해당코드를 읽어드리는 스레드명 : " + thread.getName() );
                } // run end
            }; // constructor end
            threadA.start(); // thread start
        }  // for end

        // 3. 현재 스레드를 주어진 시간동안 일시정지
        System.out.println("===== 5초간 (main thread) 대기중 =====");
        try {Thread.sleep(5000); // Thread.sleep( 밀리초 ); // 밀리초: 1/1000
        }catch (Exception e ){    System.out.println( e );      }
        System.out.println("===== 5초후 (main thread) 실행됨 =====");

        // 4. 서로 다른 스레드의 종료를 기다림
        SumThread sumThread = new SumThread();
        sumThread.start();
            // -- main스레드가 SumThread스레드 누적합계를 구하기전에 결과를 출력
        System.out.println(" JOIN 전 합계 결과 : " + sumThread.sum );
            // -- main스레드가 SumThread스레드 가 종료될때까지 기다림.
        try {
            sumThread.join();// main스레드와 SumThread스레드 가 조인(흐름 합치기)
        }catch (Exception e ){  System.out.println(e); }
        System.out.println(" JOIN 후 합계 결과 : " + sumThread.sum );

        // 5. 다른 스레드의 순서를 양보
        WorkThread workThreadA = new WorkThread(); // 스레드 객체 생성
            workThreadA.setName("작업스레드A");      // 스레드 이름 정의
        WorkThread workThreadB = new WorkThread();
            workThreadB.setName("작업스레드B");

        workThreadA.start();    // 각 스레드 실행
        workThreadB.start();

        try{ Thread.sleep( 5000 ); }    // main스레드 5초 일시정지
        catch (Exception e ){ System.out.println(e);  }

        workThreadA.work = false;       // 작업스레드A의 필드값 변경

        try{ Thread.sleep( 5000 ); }    // main스레드 5초 일시정지
        catch (Exception e ){ System.out.println(e);   }

        workThreadA.work = true;         // 작업스레드A의 필드값 변경

    } // main end
} // class end







