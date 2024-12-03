package src_Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        // creating the server
        JSON_RPC_Server server = new JSON_RPC_Server();
        try {
            // starting the server
            server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}