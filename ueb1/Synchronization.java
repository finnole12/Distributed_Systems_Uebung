package ueb1;

public class Synchronization {
   
    public static void main(String[] args) {

        Counter counter = new Counter();

        final Thread thread1 = new Thread() {
            public void run() {
                counter.increment();
                //try { Thread.sleep(100); } catch (Exception e) { System.out.println(e.toString()); }
                int newVal = counter.value();
                System.out.println("Thread1 after inc:" + newVal);
            }
        };

        final Thread thread2 = new Thread() {
            public void run() {
                counter.decrement();
                int newVal = counter.value();
                System.out.println("Thread2 after dec:" + newVal);
            }
        };

        thread1.start();
        thread2.start();
        
    }
}
