package chapter05.exam02;


//동기화 처리를 해준경우
public class CpuSyncExample {
    private static int count = 0;
    private static final int ITERATIONS = 100000;
    public static void main(String[] args) throws InterruptedException{
        Thread thread1 = new Thread(() ->{
            for (int i = 0; i < ITERATIONS; i++) {
                synchronized (CpuSyncExample.class){
                    count++;
                }
            }
        });

        Thread thread2 = new Thread(() ->{
            for (int i = 0; i < ITERATIONS; i++) {
                synchronized (CpuSyncExample.class){
                    count++;
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("예상 결과 : " + (2 * ITERATIONS));
        System.out.println("실제 결과 : " + count); //cpu가 원자성을 보장하지 못함 -> 수행할 때마다 결과가 바뀜
    }
}
