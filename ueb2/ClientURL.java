package ueb2;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class ClientURL {

    public static void main (String[] args) {
        
        // System.setProperty("http.proxyHost", "3.126.147.182");
        // System.setProperty("http.proxyPort", "3128");
        try {
            String url = "http://stud.fh-wedel.de/index.html";
            sendRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String url = "http://httpbin.org";
            sendRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void sendRequest(String request) throws IOException {
        URL url = new URL(request);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);


        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine).append("\n");
            }
            in.close();
            connection.disconnect();
            System.out.println("Response Content:");
            System.out.println(content.toString());
        } else {
            System.out.println("Failed to retrieve data. HTTP Response Code: " + responseCode);
        }
  
    }
}
