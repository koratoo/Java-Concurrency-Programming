package chapter06.exam01;

public class MutexExample {
    public static void main(String[] args) throws InterruptedException {
        SharedData sharedData = new SharedData(new Mutex());

        Thread thread1 = new Thread(() -> {
            sharedData.sum();
        });

        Thread thread2 = new Thread(() -> {
            sharedData.sum();
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("최종 합계 : " + sharedData.getSum());
    }
}
