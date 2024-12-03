package src_Client;


import java.io.File;
import java.io.IOException;

/*
 * Test class for client-Server-communication. The client will send a
 * request to the server and will recieve a html file. the file contents will be
 * printed to the terminal.
 * While the client and the server communicate it is possible to communicate with
 * the server through a webbrowser or telnet at the same time.
 */
public class Main {

    /*
     * Creates and starts a server instance and a client instance.
     * The client sends a GET request to the server.
     */
    public static void main (String[] args) {

        File logfile = new File("./log.txt");
        if (logfile.exists()) logfile.delete();

        // creating a client
        final Thread clientThread = new Thread(() -> {
            try {
                JSON_RPC_Client client = new JSON_RPC_Client("localhost", 12345);
                client.connect();
                client.sendJSON_RPC_Request(getRequestArr());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Client: thread ended");
        });

        clientThread.start();
    }

    private static DBRequest buildGetSizeRPC () {
        DBRequest dbRequest = new DBRequest();
        dbRequest.method = "GETSIZE";

        return dbRequest;
    }

    private static DBRequest buildAddRecordRPC (String record, int index) {

        DBRequest dbRequest = new DBRequest();
        dbRequest.method = "ADDRECORD";
        dbRequest.params = new src_Client.DBRequest.AddRecordParams(record, index);

        return dbRequest;
    }

    private static DBRequest buildGetRecordRPC (int index) {

        DBRequest dbRequest = new DBRequest();
        dbRequest.method = "GETRECORD";
        dbRequest.params = new src_Client.DBRequest.GetRecordParams(index);

        return dbRequest;
    }

    private static DBRequest[] getRequestArr() {
        DBRequest[] requests = new DBRequest[9];
        requests[0] = buildAddRecordRPC("Appen", 4101);
        requests[1] = buildAddRecordRPC("Ahrensburg", 4102);
        requests[2] = buildAddRecordRPC("Wedel", 4103);
        requests[3] = buildAddRecordRPC("Aumühle", 4104);
        requests[4] = buildAddRecordRPC("Seevetal", 4105);
        requests[5] = buildAddRecordRPC("Quickborn", 4106);
        requests[6] = buildGetRecordRPC(4103);
        requests[7] = buildGetRecordRPC(4107);
        requests[8] = buildGetSizeRPC();
        return requests;
    }
}
