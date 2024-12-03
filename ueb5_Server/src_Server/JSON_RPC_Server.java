package src_Server;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Random;


/*
 * Server that accepts Client requests. Each client will be dealt with in a seperate
 * Thread.
 */
public class JSON_RPC_Server {

    private final int port = 12345;
    ArrayList<String> database = new ArrayList<>();
    private final ObjectMapper mapper;

    public JSON_RPC_Server() {
        JsonFactory factory = new JsonFactory();
        factory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        mapper = new ObjectMapper(factory);
    }

    /*
     * Starts the server. Will continuisly listen for incoming client requests.
     * 
     */
    public void startServer() throws IOException {
        System.out.println("Server: starting");
        initilizeDatabase();

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            // Thread tasked with accepting incoming client requests
            final Thread thread = new Thread(() -> {
                while(true) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        System.out.println("Server: client connected");
                        startClientThread(clientSocket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startClientThread(Socket clientSocket) {
        final Thread thread = new Thread(() -> {
            try {
                BufferedInputStream bis = new BufferedInputStream(clientSocket.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(bis));
                String line;
                while ((line = br.readLine()) != null) {
                        // DBRequest request = mapper.readValue(clientSocket.getInputStream(), DBRequest.class);
                        DBRequest request = mapper.readValue(line, DBRequest.class);
                        handleResponse(request, clientSocket);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Server: thread ended");
        });
        thread.start();
    }

    private void handleResponse(DBRequest request, Socket clientSocket) throws IOException {
        // execute request
        String output = distributeRequests(request.method, request.params);

        // build response
        DBResponse response = new DBResponse();
        response.message = output;

        // send response
        mapper.writeValue(clientSocket.getOutputStream(), response);
        clientSocket.getOutputStream().write("\n".getBytes());
        clientSocket.getOutputStream().flush();
        System.out.println("Server: response: " + response.message);
    }

    private String distributeRequests(String method, Object params) throws JsonProcessingException {
        JsonNode node = mapper.readTree(mapper.writeValueAsString(params));

        String output;
        System.out.println("Server: Operation request for: " + method);
        switch(method) {
            case "GETSIZE":
                output = "" + getSize();
                break;
            case "GETRECORD":
                if (!node.has("index")) {
                    throw new InvalidParameterException();
                }
                output = getRecord(node.get("index").asInt());
                break;
            case "ADDRECORD":
                if (!node.has("index") || !node.has("record")) {
                    throw new InvalidParameterException();
                }
                output = "" + addRecord(node.get("record").asText(), node.get("index").asInt());
                break;
            default:
                output = "Error: Invalid Operation";
        }
        return output;
    }

    private int getSize() {
        return database.size();
    }

    private String getRecord(int index) {
        return database.get(index);
    }

    private boolean addRecord(String record, int index) {
        database.add(index, record);
        return true;
    }

    private void initilizeDatabase() {
        for (int i = 0; i < 5000; i++) {
            database.add("Random Entry number: " + new Random().nextInt());
        }
    }
}
