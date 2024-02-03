package chapter04.exam02;

public class FlagThreadStopExample {

//    private static boolean running = true;

    //volatile을 쓰게 되면 캐시가 아니라 메인메모리에서 작업이 이루어지게 된다.
    private volatile static boolean running = true; 
    public static void main(String[] args) {

       new Thread(()->{
            int counter = 0;
            while(running) {
                counter++;
                //cpu마다 캐시메모리를 가지고 있다. 각 스레드는 캐시메모리에서 가져와 연산을 하는게 빠르다.
                //sleep을 걸어줘도 running -> false가 되는데 문맥교환을 하면서 캐시 정보를 비워주기 때문이다.
                //새로운 캐시를 참조할때 아래 Thread의 running = false를 가져오는 셈
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
            running = false;
        }).start();

    }
}
