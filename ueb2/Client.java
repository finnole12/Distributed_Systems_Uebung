package ueb2;
import java.net.Socket;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Client {

    public static void startClientConnections () {
        Client client;
        try {
            client = new Client("stud.fh-wedel.de", 80);
            client.connect();
            client.sendRequest("GET / HTTP/1.1\r\nHost:stud.fh-wedel.de\r\n\r\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Client client2;
        try {
            client2 = new Client("httpbin.org", 80);
            client2.connect();
            client2.sendRequest("GET / HTTP/1.1\r\nHost:httpbin.org\r\n\r\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private Socket clientSocket;
    private OutputStream os;
    private InputStream is;

    public Client(String host, int port) throws IOException {
        clientSocket = new Socket(host, port);
    }

    public void connect() throws IOException {
        System.out.println("trying to connect...");

        os = clientSocket.getOutputStream();
        is = clientSocket.getInputStream();
    }
    
    public void sendRequest(String request) throws IOException {

        os.write(request.getBytes());
        os.flush();

        int b;
        while (true) {
            try {
                if ((b = is.read()) == -1) {
                    break;
                }
                System.out.print((char) b);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
