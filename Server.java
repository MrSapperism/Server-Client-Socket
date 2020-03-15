
import java.net.*;
import java.io.*;

public class Server {

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private DataOutputStream = null;

    public Server(int port)
    {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started...\n\tWaiting for Client...");

            socket = server.accept();
            System.out.println("Client accepted");

            // Setting up input stream
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            // Setting up output stream
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("Hello From Server");

            String line = "";

            // reads message from client until "Over" is sent
            System.out.println("Starting to read");

            while (!line.equals("FIN"))
            {
                line = in.readLine();
                System.out.println(line);
                if(line.equals("HI")) out.println("HELLO");
            }
            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        Server server = new Server(5000); 
    }
}
