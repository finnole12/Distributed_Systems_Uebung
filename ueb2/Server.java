package ueb2;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class Server {

    int port = 12345;
    String filePath = "ueb2\\testseite.html";
    File file = new File(filePath);


    public void startServer() throws IOException {
        System.out.println("starting server...");

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            final Thread thread = new Thread() {
                public void run() {
                    int i = 0;
                    while(true) {
                        try {
                            Socket clientSocket = serverSocket.accept();
                            System.out.println("client connected");
                            startClientThread(clientSocket, i++);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startClientThread(Socket clientSocket, int num) {
        final Thread thread = new Thread() {
            public void run() {
                try {
                    BufferedReader iSR = new BufferedReader(new InputStreamReader (clientSocket.getInputStream()));
                    OutputStream oS = clientSocket.getOutputStream();
                    
                    BufferedInputStream fileInputStream = new BufferedInputStream(new FileInputStream(filePath));

                    String b;
                    try {
                        while((b = iSR.readLine()) != null) {
                            System.out.print(b);
                            //if (b.equals("file")) {
                                System.out.println("MATCH");
                                byte[] fileBytes = new byte[(int) file.length()];
                                fileInputStream.read(fileBytes);
                                oS.write(fileBytes);
                                oS.flush();
                            //}
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                // file auswählen

                // file senden

                // connection closen
            }
        };
        thread.start();
    }
}
