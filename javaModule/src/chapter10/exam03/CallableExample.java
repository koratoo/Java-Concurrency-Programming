package chapter10.exam03;

import java.util.concurrent.*;

public class CallableExample {
    public static void main(String[] args) {
       ExecutorService executorService =  Executors.newFixedThreadPool(1);

       Callable<Integer> callableTask = ()->{
           System.out.println("Callable 작업 수행중...");
           System.out.println("Callable 작업 완료...");

           return 42;
       };

       Future<Integer> future = executorService.submit(callableTask); //submit은 Future타입을 리턴한다.
        int result;
        try {
            result =  future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Callble 작업 결과 : " + result);

        executorService.shutdown();
    }
}
