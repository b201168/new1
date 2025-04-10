import java.io.*;
import java.net.*;

public class FileServer {

    public static void main(String[] args) {
        int port = 12345; // The port to listen on

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            
            while (true) {
                // Wait for a client to connect
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("New client connected: " + socket.getInetAddress().getHostAddress());
                    
                    // Get input and output streams
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    
                    // Read the file name
                    String fileName = reader.readLine();  // The client first sends the file name
                    System.out.println("Receiving file: " + fileName);
                    
                    // Prepare to receive the file content
                    FileOutputStream fileOutputStream = new FileOutputStream("received_" + fileName);
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }

                    System.out.println("File received successfully.");
                    fileOutputStream.close();

                    // Now read the content of the file (assuming it's a text file)
                    BufferedReader fileReader = new BufferedReader(new FileReader("received_" + fileName));
                    String line;
                    while ((line = fileReader.readLine()) != null) {
                        System.out.println("File content: " + line);
                    }
                    fileReader.close();
                } catch (IOException e) {
                    System.out.println("Error handling client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error starting the server: " + e.getMessage());
        }
    }
}

