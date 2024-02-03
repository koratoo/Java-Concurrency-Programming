package chapter03.exam01;

public class BasicSleepExample {
    public static void main(String[] args) {

        for (int i = 0; i < 7; i++) {
            try {
                System.out.println("2초 후 메시지 출력!" + i);
                Thread.sleep(2000);
                System.out.println("Hello World!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
