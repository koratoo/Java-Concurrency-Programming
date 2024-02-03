package chapter05.exam03;

public class NonRaceConditionExample {
    private static int sharedResource = 0;

    public static void main(String[] args) throws InterruptedException{
        // 스레드 100개를 생성하여 공유 리소스를 동시에 증가시킨다.
        Thread[] incrementThreads = new Thread[100];

        for (int i = 0; i < incrementThreads.length ; i++) {
            incrementThreads[i] = new Thread(() -> {

            });
        }
    }
}
