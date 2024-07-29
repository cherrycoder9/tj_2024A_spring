package example.day08.thread;

import java.time.LocalTime;

public class DigitalTime extends Thread {
    @Override
    public void run() {
        // === 멀티스레드가 처리할 코드들 === //
        while (true){
            System.out.println( LocalTime.now() );
            try{ Thread.sleep( 1000 ); }
            catch (Exception e ){
                System.out.println(e);
            }
        } // w end
    } // m r end
} // class end

