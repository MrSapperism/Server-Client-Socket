
import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
    private Socket socket = null;
    private DataInputStream stdin = null; //reads characters from input stream
    private DataInputStream socket_in = null; //reads chars from socket inputstream
    private DataOutputStream out = null; //sends character to output stream

    public Client (String address, int port){
        try {
            socket = new Socket(address, port);
            System.out.println("Connected: " + address);

            //Setting up input streams
            stdin = new DataInputStream(System.in);
            socket_in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            //Setting up output streams
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("\"HELO\"");

            String inputLine, outputLine;
            inputLine = outputLine = "INIT";

            System.out.println("Accepting input");

            outputLine = socket_in.readUTF();
            if(outputLine != null ) System.out.println(outputLine);

            while(true){

                if(stdin.available() > 0){
                    inputLine = stdin.readLine();
                    if(inputLine.equals("Over")){
                        out.writeUTF("Over");
                        break;
                    }
                }
                if (inputLine != null) out.writeUTF(inputLine);

                if(socket_in.available() > 0)
                    outputLine = socket_in.readUTF();
                if (outputLine != null) System.out.println("Server:" + outputLine);

                inputLine = null;
                outputLine = null;
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
        int port = 50000;

        System.out.println("Attempting Connection: " + addr + ":" + port);
        Client client = new Client(addr, port);
    }
}
