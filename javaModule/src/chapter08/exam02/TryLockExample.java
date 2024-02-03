package chapter08.exam02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//lock의 값이 false,true에 따라서 결정하고 싶을때 tryLock을 사용한다.
public class TryLockExample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread threadA = new Thread(()->{
            boolean aquired = false;
            while(!aquired){
                aquired = lock.tryLock();
                if(aquired){
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
