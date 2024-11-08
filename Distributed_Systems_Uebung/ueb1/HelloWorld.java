package Distributed_Systems_Uebung.ueb1;

public class HelloWorld {
    public static void main(String [] args) {
        if (args.length > 0) {
            System.out.println("Hello, World! Parameter: " + args[0]);
        } else {
            System.out.println("Hello, World! No parameter provided.");
        }
    }
}