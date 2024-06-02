package chapter10.exam02;

import java.util.concurrent.Executor;

public class SyncExecutorExample {
    public static void main(String[] args) {
        Executor syncExecutor = new SyncExecutor();

        syncExecutor.execute(() -> {
            System.out.println("동기 작업1 수행 중...");
            //작업 수행
            System.out.println("동기 작업1 완료...");
        });

        syncExecutor.execute(() -> {
            System.out.println("동기 작업2 수행 중...");
            //작업수행
            System.out.println("동기 작업2 완료...");
        });
    }

    static class SyncExecutor implements Executor {
        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }
}
