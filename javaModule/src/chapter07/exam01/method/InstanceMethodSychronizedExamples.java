package chapter07.exam01.method;

public class InstanceMethodSychronizedExamples {

    private int count = 0;

    public synchronized void increment() {
        count++;
        System.out.println(Thread.currentThread().getName() + " 가 증가시켰습니다. 현재 값:" + count);
    }

    public synchronized void decrement() {
        count--;
        System.out.println(Thread.currentThread().getName() + " 가 감소시켰습니다. 현재 값:" + count);
    }

    public int getCount(){
        return count;
    }

    public static void main(String[] args) {
        InstanceMethodSychronizedExamples counter = new InstanceMethodSychronizedExamples();

        Thread threadA = new Thread(()->{
            for (int i = 0; i < 100000; i++) {
                counter.increment();
            }
        });

        Thread threadB = new Thread(()->{
            for (int i = 0; i < 100000; i++) {
                counter.decrement();
            }
        });

        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("최종값: " + counter.getCount());

    }

}
