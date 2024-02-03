package chapter03.exam01;

public class MultiThreadSleepExample {
    public static void main(String[] args) throws InterruptedException {

     Thread sleepingThread = new Thread(()->{
         try {
             System.out.println("2초 후에 메세지가 출력됩니다.");
             Thread.sleep(9999999);
             System.out.println("헬로 월드!");

         } catch (InterruptedException e) {
             throw new RuntimeException(e);
         }
     });

    sleepingThread.start();

    Thread.sleep(9999999);


    }
}
