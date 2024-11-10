package ueb1;

public class Synchronization {
   
    /*
     * starts two threads that decrement and increment a counter class respectively
     * they are running at the same time.
     * Thread1 which increments, gets a delay of 500ms to simulate elongated execution time
     * This is done to showcase the interference effects that two threads can have when
     * using the same object. When the object is synchronized the issue is fixed.
     */
    public static void main(String[] args) {

        Counter counter = new Counter();

        final Thread thread1 = new Thread() {
            public void run() {
                counter.increment();

                // sleep is used to simulate execution time
                try { Thread.sleep(500); } 
                catch (Exception e) { System.out.println(e.toString()); }
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
