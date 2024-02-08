package chapter06.exam01;

public class SharedData {

    private int sharedValue = 0;
    private Mutex mutex;

    public SharedData(Mutex mutex) {
        this.mutex = mutex;
    }

    public void sum() {
        try {
            mutex.acquired();
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
