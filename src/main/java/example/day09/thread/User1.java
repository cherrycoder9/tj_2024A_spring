package example.day09.thread;

public class User1 extends Thread {
    Calculator calculator;
    int value;
    @Override
    public void run() {
        calculator.setMemory( value );
    }
}
