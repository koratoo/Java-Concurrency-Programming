package chapter04.exam02;

import java.util.concurrent.atomic.AtomicBoolean;

public class FlagThreadStopExample2 {

//    boolean running = true;
    private AtomicBoolean running = new AtomicBoolean(true);

    public static void main(String[] args) {
        new FlagThreadStopExample2().flagTest();
    }

    private void flagTest() {
        new Thread(()->{
            int counter = 0;
            while(running.get()) {
                counter++;
            }
            System.out.println("스레드 1 종료, count: " + counter);
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("스레드 2 종료");
            running.set(false);
        }).start();
    }
}
