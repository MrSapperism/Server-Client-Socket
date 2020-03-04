
import java.net.*;
import java.io.*;
import java.util.*;

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
        Scanner scanner = new Scanner(System.in);
        List<String> tokens = new ArrayList<>();
        boolean end = false;
        int i = 0;

        System.out.println("IP PORT;");
        while(!end){
          tokens.add(scanner.nextLine());
          if(tokens.get(i).equals(";"))
              end = true;
          i++;
        }
        System.out.println("Attempting Connection: " + tokens.get(i-3) + " " + tokens.get(i-2));
        Client client = new Client(tokens.get(i-3), Integer.parseInt(tokens.get(i-2)));
    }
}
