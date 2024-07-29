package example.day09.thread;

public class WorkThread extends Thread{
    // 필드
    public boolean work = true;

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            }catch (Exception e ){
                System.out.println(e);
            } // catch end

            if( work ){
                System.out.println( getName() );
            }else{
                Thread.yield(); // 다른 스레드에게 양보
            }
        } // while end
    } // run end
} // class end
