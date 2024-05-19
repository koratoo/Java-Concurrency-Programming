package chapter10.exam01;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleThreadPool {

    private int numberOfThreads;
    private Queue<Runnable> taskQueue;
    private Thread[] threads;
    private volatile boolean isShutdown; /*메모리에서 바라볼 수 있도록*/

    public SimpleThreadPool(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
        this.taskQueue = new LinkedList<>();
        this.threads = new Thread[numberOfThreads];
        this.isShutdown = false;

        // 스레드 풀이 생성되자 마자 실행되는 구조
        for (int i = 0; i < numberOfThreads ; i++) {
            threads[i] = new WorkerThread();
            threads[i].start();
        }
    }

    public void submit(Runnable task) { //Queue에 작업을 추가
        if(!isShutdown) {
            synchronized (taskQueue) {
                taskQueue.offer(task);
                taskQueue.notifyAll();
            }
        }
    }

    public void setShutdown() {
        isShutdown = true;
        synchronized (taskQueue) {
            taskQueue.notifyAll();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class WorkerThread extends Thread
        @Override
        public void run() {
            while(!isShutdown) {
                Runnable task;
                synchronized (taskQueue) {
                    while(taskQueue.isEmpty() && !isShutdown) {
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                if(!taskQueue.isEmpty()) {
                    task = taskQueue.poll();
                } else {
                    continue;
                }
                task.run();
            }
        }
    }
}
