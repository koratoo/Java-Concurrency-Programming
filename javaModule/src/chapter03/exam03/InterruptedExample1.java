package chapter03.exam03;

public class InterruptedExample1 {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            System.out.println("인터럽트 상태 1 : " + Thread.currentThread().isInterrupted());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("스레드가 인터럽트 되었습니다.");
                System.out.println("인터럽트 상태 2 : " + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
            }
        });

        thread.start();
    }
}
