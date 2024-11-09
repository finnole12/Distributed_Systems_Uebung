package ueb1;

public class Counter {

    private int c = 0;
    
    public synchronized void increment () {
        int b = c ;
        try { Thread.sleep(2000); } catch (Exception e) { System.out.println(e.toString()); }
        b = b +1;
        c = b ;
    }

    public synchronized void decrement () {
        int d = c ;
        try { Thread.sleep(2000); } catch (Exception e) { System.out.println(e.toString()); }
        d = d -1;
        c = d ;
    }

    public synchronized int value () {
        return c ;
    }
}
