package chapter10.exam02;

import java.util.concurrent.Executor;

public class AsyncExecutorExample {
    public static void main(String[] args) {
        Executor asyncExecutor = new AsyncExecutorExample.AsyncExecutor();

        asyncExecutor.execute(() -> {
            System.out.println("비동기 작업1 수행 중...");
            //작업 수행
            System.out.println("비동기 작업1 완료...");
        });

        asyncExecutor.execute(() -> {
            System.out.println("비동기 작업2 수행 중...");
            //작업수행
            System.out.println("비동기 작업2 완료...");
        });
    }

    static class AsyncExecutor implements Executor {
        @Override
        public void execute(Runnable command) {
            Thread thread = new Thread(command);
            thread.start();;
        }
    }
}
