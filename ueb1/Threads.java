package ueb1;

public class Threads {
    public static void main(String [] args) {
        
        final Thread thread1 = new Thread() {
            public void run() {
                System.out.println("Thread1 is printing");
                System.out.println("Thread1 is printing");
                System.out.println("Thread1 is printing");
                System.out.println("Thread1 is printing");
                System.out.println("Thread1 is printing");
                System.out.println("Thread1 is printing");
            }
        };

        final Thread thread2 = new Thread() {
            public void run() {
                System.out.println("Thread2 is printing");
                System.out.println("Thread2 is printing");
                System.out.println("Thread2 is printing");
                System.out.println("Thread2 is printing");
                System.out.println("Thread2 is printing");
                System.out.println("Thread2 is printing");
            }
        }; 
        
        final Thread thread3 = new Thread() {
            public void run() {
                System.out.println("Thread3 is printing");
                System.out.println("Thread3 is printing");
                System.out.println("Thread3 is printing");
                System.out.println("Thread3 is printing");
                System.out.println("Thread3 is printing");
                System.out.println("Thread3 is printing");
            }
        };
        
        final Thread thread4 = new Thread() {
            public void run() {
                System.out.println("Thread4 is printing");
                System.out.println("Thread4 is printing");
                System.out.println("Thread4 is printing");
                System.out.println("Thread4 is printing");
                System.out.println("Thread4 is printing");
                System.out.println("Thread4 is printing");
            }
        };

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
