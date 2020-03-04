
import java.net.*;
import java.io.*;

public class Client {
    private Socket socket = null;
    private BufferedReader in = null;
    private DataOutputStream out = null;

    public Client (String address, int port){
        try {
            socket = new Socket(address, port);
            System.out.println("Connected: " + address);
            Reader reader = new InputStreamReader(System.in);
            in = new BufferedReader(reader);

            out = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u){
            System.out.println("Unknown Host");
            System.out.println(u);
        }
        catch(IOException i){
            System.out.println(i);
            System.out.println("IO Exception 1");
        }

        String line = "";

        while(!line.equals("Over")){
            try{
                line = in.readLine();
                out.writeUTF(line);
            }
            catch(IOException i){
                System.out.println("IO Exception 2");
                System.out.println(i);
                break;
            }
        }
        try {
            in.close();
            out.close();
            socket.close();
        }
        catch(IOException i){
            System.out.println(i);
            System.out.println("IO Exception 3");
        }

    }

    public static void main(String args[]){
        Client client = new Client("127.0.0.1", 5000);
    }
}
