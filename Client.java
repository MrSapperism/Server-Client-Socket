import java.net.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import com.Parser; 
import com.Server; 
import com.ServerConfig;
import org.w3c.dom.Element;
//com.Server and Server.config are not java libraries but other areas of our assignment

import static java.lang.System.exit;
//Above we have important all of the libraries we will use for this Client side dataset. 
//Bellow is the entire data block for the Client class 
public class Client {
	private Socket clientSocket;
	private OutputStream out;
	private BufferedReader in;
	private String ipAddress;
	private int port;

	public Client(final String ip, final int port){ 
		//This code sets the public Client class as the IP Address as well as port number of the Client side
 		this.ipAddress = ip; 
		this.port = port;
	}

	public void startConnection() throws IOException {
		System.out.println("Attempting Connection: " + this.ipAddress + ":" + this.port); // Displays attempting connection as well as IP address anad port of target when attempting to make a connection with server side
		clientSocket = new Socket(this.ipAddress, this.port);
		out = clientSocket.getOutputStream(); //output stream
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //input stream
	}

	public String sendMessage(final String msg) throws IOException { //printing empty arrays fixed
		final char resp[] = new char[200];
		out.write(msg.getBytes());
		out.flush();
		int noOfChar = in.read(resp);
		return new String(resp, 0, noOfChar);
	}
	//This code block is used to stop connections. 
	public void stopConnection() throws IOException { 
		in.close();
		//input is closed
		out.close();
		//output is closed
		clientSocket.close();
		//clientsocket is closed 
	}

	public String schedule_job(String jobID, Server server) throws IOException{
		String status = this.sendMessage("SCHD " + jobID + " " + server.getType() + " 0");
		return status;
	}

	public static void main(final String args[]) throws	IOException {

		final Client client = new Client("127.0.0.1", 50000);
		client.startConnection();
		//code above start connection

		ServerConfig serverConfig = new ServerConfig(); 
		//code above applies the server configuration 
		//code bellow is relevant between creating the communication between client and server side 
		String in_msg = "";
		in_msg = client.sendMessage("HELO");
		System.out.println(in_msg);
		in_msg = client.sendMessage("AUTH root");
		System.out.println(in_msg);
		if (in_msg.contains("OK")){
			//TimeUnit.SECONDS.sleep(10);
			Parser parser = new Parser("./ds-sim/system.xml");
			System.out.println("Parsing system.xml");
			Element server_root = (Element) parser.root.getElementsByTagName("servers").item(0);
			parser.convertAttribs(server_root.getElementsByTagName("server"), serverConfig);
		}
		System.out.println(serverConfig.getLargest().toString()); //code and bellow is necessary for getting largest as specified

		in_msg = client.sendMessage("REDY");
		while (in_msg.contains("JOBN")) {
			final String[] res = in_msg.split(" ");
			String status = client.schedule_job(res[2], serverConfig.getLargest());
			System.out.println(status);
			in_msg = client.sendMessage("REDY");
		}
		System.out.println(in_msg);
		client.sendMessage("QUIT");
	}

}

/*
 * in_msg = client.sendMessage("RESC ALL" + " " + res[4] + " " + res[5] + " " +
 * res[6]);
 */
