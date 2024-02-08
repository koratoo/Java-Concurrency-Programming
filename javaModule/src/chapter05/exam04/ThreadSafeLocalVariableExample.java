package chapter05.exam04;

public class ThreadSafeLocalVariableExample {

    //int localSum = 0;
    public void printNumbers(int plus) {
        // 지역변수, 매개변수로 정의된 변수, 각 스레드는 이 변수의 독립된 복사본을 가짐.
        int localSum = 0;

        for (int i = 0; i < 5; i++) {
            localSum += i;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        localSum += plus;
        System.out.println(Thread.currentThread().getName() + " - 현재 합계: " +localSum);
    }
}
