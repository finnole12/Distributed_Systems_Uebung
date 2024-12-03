package src_Client;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;

public class JSON_RPC_Client {

    private Socket clientSocket;
    private OutputStream os;
    private InputStream is;
    private final ObjectMapper mapper;

    public JSON_RPC_Client(String host, int port) throws IOException {
        clientSocket = new Socket(host, port);
        JsonFactory factory = new JsonFactory();
        factory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        mapper = new ObjectMapper(factory);
    }

    public void connect() throws IOException {
        System.out.println("Client: trying to connect");

        is = clientSocket.getInputStream();
        os = clientSocket.getOutputStream();
    }

    public void sendJSON_RPC_Request(DBRequest[] json_rpc_requests) throws IOException, InterruptedException {
        for (DBRequest json_rpc_request : json_rpc_requests) {
            mapper.writeValue(os, json_rpc_request);
            os.write("\n".getBytes());
            os.flush();
            awaitResponse();
        }
    }

    void awaitResponse() throws IOException, InterruptedException {

        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedReader br = new BufferedReader(new InputStreamReader(bis));
        String dbResponse = br.readLine();
        // String dbResponse = mapper.readValue(is, String.class);


        System.out.println("Client: recieved: " + dbResponse);
    }
}
