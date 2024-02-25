package chapter06.exam01;

public class SharedData {

    private int sharedValue = 0;
    private Mutex mutex;

    public SharedData(Mutex mutex) {
        this.mutex = mutex;
    }

    public void sum() {
        try {
//            mutex.acquired(); //락을 처리하는 부분
            for (int i = 0; i < 10000000; i++) {
                sharedValue ++;
            }
        } finally {
            mutex.release();
        }
    }

    public int getSum() {
        return sharedValue;
    }

}
