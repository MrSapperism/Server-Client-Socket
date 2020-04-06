import java.net.*;
import java.io.*;

public class Client {
    private Socket clientSocket;
    private DataOutputStream out;
    private BufferedReader in;

    public void startConnection(final String ip, final int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new BufferedReader(
                     new InputStreamReader(clientSocket.getInputStream()));
        } catch(final Exception e){
            System.out.println(e);
        }
     }

    public String sendMessage(final String msg) {
        char resp[] = new char[200];
        try{
            out.write(msg.getBytes());
            out.flush();
            in.read(resp);
            return new String(resp);
        } catch(final Exception e){
            System.out.println(e);
        }
        return new String(resp);
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch(final Exception e){
            System.out.println(e);
        } finally {
        }
    }

    public static void main(final String args[]){

        final String addr = "127.0.0.1";
        final int port = 50000;

        System.out.println("Attempting Connection: " + addr + ":" + port);

        final Client client = new Client();
        client.startConnection("127.0.0.1", 50000);

        String in_msg = "";
        int job_id = 0;
        in_msg = client.sendMessage("HELO");
        System.out.println(in_msg);
        in_msg = client.sendMessage("AUTH comp355");
        System.out.println(in_msg);
        in_msg = client.sendMessage("REDY");
        System.out.println(in_msg);
        if(in_msg.contains("JOBN")){
            String[] res = in_msg.split(" ");
            in_msg = client.sendMessage("RESC ALL" +
                                        " " + res[4] +
                                        " " + res[5] +
                                        " " + res[6]);
            System.out.println(in_msg);
            in_msg = client.sendMessage("SCHD 0 large 0 ");
            System.out.println(in_msg);
        } else {
            in_msg = client.sendMessage("QUIT");
        }
        System.out.println(in_msg);
    }

}
