package chapter07.exam01.method;

public class InstanceMethodSychronizedExamples2 {

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
        //두 개의 객체로 모니터링 하는 경우
        InstanceMethodSychronizedExamples2 counter1 = new InstanceMethodSychronizedExamples2();
        InstanceMethodSychronizedExamples2 counter2 = new InstanceMethodSychronizedExamples2();

        Thread threadA = new Thread(()->{
            for (int i = 0; i < 100000; i++) {
                counter1.increment();
                counter2.decrement();
            }
        });

        Thread threadB = new Thread(()->{
            for (int i = 0; i < 100000; i++) {
                counter1.decrement();
                counter2.increment();
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

        System.out.println("최종값: " + counter1.getCount());
        System.out.println("최종값: " + counter2.getCount());

    }

}
