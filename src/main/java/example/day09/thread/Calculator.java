package example.day09.thread;

public class Calculator {
    int memory;
    public synchronized void setMemory( int memory ){
    //public void setMemory( int memory ){
        System.out.println("memory = " + memory);
        this.memory = memory; // 매개변수 값을 필드 저장
        try{Thread.sleep( 2000 );} // 2초간 일시정지
        catch (Exception e ){System.out.println(e);}
        System.out.println( Thread.currentThread().getName() );
        System.out.println(  this.memory );   // 현재 필드값 확인
    }
}
