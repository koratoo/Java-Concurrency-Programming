package chapter08.exam02;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockWithTimeoutExample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread threadA = new Thread(()->{

            try {
                if(lock.tryLock(2, TimeUnit.SECONDS)){
                    System.out.println("스레드A가 락을 획득했다");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e){}
                    finally {
                        lock.unlock();
                        System.out.println("스레드A가 락을 해제했습니다.");
                    }
                } else {
                    System.out.println("스레드A가 락을 획득하지 못했습니다.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    }
                }
            //tryLock Timeout의 경우 Interrupted Exception 예외처리가 필요하다.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread threadB = new Thread(()->{
            boolean aquired = false;
            while(!aquired){
                aquired = lock.tryLock();
                if(aquired){
                    System.out.println("스레드B가 락을 획득했다");
                    try {
                        Thread.sleep(2000);
                    }  catch (InterruptedException e){}
                    finally {
                        lock.unlock();
                        System.out.println("스레드B가 락을 해제했습니다.");
                    }
                } else {
                    System.out.println("스레드B가 락을 획득하지 못했습니다.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
