package ueb2;

import java.io.IOException;

public class testStart {
    public static void main (String[] args) {
        Server server = new Server();



        try {
            server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        Client client;
        try {
            client = new Client("localhost", 12345);
            client.connect();
            client.sendRequest("GET / HTTP/1.1\r\nHost:stud.fh-wedel.de\r\n\r\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }







    }
    
}
