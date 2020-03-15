
import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
    private Socket socket = null;
    private BufferedReader stdin = null; //reads characters from input stream
    private BufferedReader socket_in = null; //reads chars from socket inputstream
    private PrintWriter out = null; //sends character to output stream

    public Client (String address, int port){
        try {
            socket = new Socket(address, port);
            System.out.println("Connected: " + address);

            //Setting up input streams
            stdin = new BufferedReader(new InputStreamReader(System.in));
            socket_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Setting up output streams
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hello From Client");

            String inputLine, outputLine;
            inputLine = outputLine = "INIT";

            System.out.println("Accepting input");

            while(true){

                inputLine = stdin.readLine();
                outputLine = socket_in.readLine();

                System.out.println("Writing:" + inputLine);
                if (inputLine != null) out.println(inputLine);

                if (outputLine != null) System.out.println("Server:" + outputLine);
            }

        }
        catch(UnknownHostException u){
            System.out.println("Unknown Host");
            System.out.println(u);
        }
        catch(IOException i){
            System.out.println(i);
            System.out.println("Server not present");
            System.exit(0);
        }

    }


    public static void main(String args[]){

        String addr = "127.0.0.1";
        int port = 5000;

        System.out.println("Attempting Connection: " + addr + ":" + port);
        Client client = new Client(addr, port);
    }
}
