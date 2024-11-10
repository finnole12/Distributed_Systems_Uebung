package ueb1;

public class Counter {

    private int c = 0;
    
    /**
     * increments c
     * 
     * synchronized prevents multiple threads from accessing this method 
     * at the same time
     */
    public synchronized void increment () {
        int b = c ;
        // Sleep is used to simulate execution time
        try { Thread.sleep(2000); } 
        catch (Exception e) { System.out.println(e.toString()); }
        b = b +1;
        c = b ;
    }

    /**
     * decrements c
     * 
     * synchronized prevents multiple threads from accessing this method 
     * at the same time
     */
    public synchronized void decrement () {
        int d = c ;
        d = d -1;
        c = d ;
    }

    /**
     * returns c
     * 
     * synchronized prevents multiple threads from accessing this method 
     * at the same time
     */
    public synchronized int value () {
        return c ;
    }
}
