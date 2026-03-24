public class Bai3_ThreadRunnable {
    public static void main(String[] args) {
        WorkerThread t1 = new WorkerThread();
        Thread t2 = new Thread(new WorkerRunnable());

        t1.start();
        t2.start();
    }
}
