package src_Client;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        RMI_Client client = new RMI_Client();
        client.execute();
    }
}
