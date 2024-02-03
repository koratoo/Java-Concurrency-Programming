package chapter04.exam02;

public class InterruptedExceptionThreadStopExample4 {
    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            try {
                System.out.println("작업 스레드가 실행중입니다.");
                System.out.println("인터럽트 상태 1 : " + Thread.currentThread().isInterrupted());
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("인터럽트 상태 2 : " + Thread.currentThread().isInterrupted());
            }
            System.out.println("작업 스레드가 중단되었습니다.");
            System.out.println("인터럽트 상태 2 : " + Thread.currentThread().isInterrupted());
        });
    }
}
