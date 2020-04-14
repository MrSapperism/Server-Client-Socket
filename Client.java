import java.net.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import com.Parser;
import com.Server;
import com.ServerConfig;
import org.w3c.dom.Element;


public class Client {
	private Socket clientSocket;
	private DataOutputStream out;
	private BufferedReader in;
	private String ipAddress;
	private int port;

	public Client(final String ip, final int port){
		this.ipAddress = ip;
		this.port = port;
	}

	public void startConnection() {
		try {
			System.out.println("Attempting Connection: " + this.ipAddress + ":" + this.port);
			clientSocket = new Socket(this.ipAddress, this.port);
			out = new DataOutputStream(clientSocket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (final Exception e) {
			System.out.println(e);
		}
	}

	public String sendMessage(final String msg) {
		final char resp[] = new char[200];
		try {
			out.write(msg.getBytes());
			out.flush();
			in.read(resp);
			return new String(resp);
		} catch (final Exception e) {
			System.out.println(e);
		}
		return new String(resp);
	}

	public void stopConnection() {
		try {
			in.close();
			out.close();
			clientSocket.close();
		} catch (final Exception e) {
			System.out.println(e);
		} finally {
		}
	}

	String schedule_job(String jobID, Server server) {
		String status = this.sendMessage("SCHD " + jobID + " " + server.getType() + " 0");
		return status;
	}

	public static void main(final String args[]) {

		final Client client = new Client("127.0.0.1", 50000);
		client.startConnection();

		ServerConfig serverConfig = new ServerConfig();

		String in_msg = "";
		in_msg = client.sendMessage("HELO");
		System.out.println(in_msg);
		in_msg = client.sendMessage("AUTH root");
		System.out.println(in_msg);
		if (in_msg.contains("OK")){
			try {
				TimeUnit.SECONDS.sleep(10);
				Parser parser = new Parser("./ds-sim/system.xml");
				System.out.println("Parsing system.xml");
				Element server_root = (Element) parser.root.getElementsByTagName("servers").item(0);
				parser.convertAttribs(server_root.getElementsByTagName("server"), serverConfig);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println(serverConfig.getLargest().toString());

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
