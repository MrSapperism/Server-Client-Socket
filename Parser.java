import javax.xml.parsers.*;
import java.io.*;
import org.w3c.dom.*;
import java.util.*;
import com.Server;
import com.ServerConfig;

class Parser {

	File input_file;
	DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;
	Document doc;
	Element root;

	public Parser(){
		try {
			this.dbFactory = DocumentBuilderFactory.newInstance();
			this.dBuilder = this.dbFactory.newDocumentBuilder();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public Parser(String input_file){
		try {
			this.input_file = new File(input_file);
			this.dbFactory = DocumentBuilderFactory.newInstance();
			this.dBuilder = this.dbFactory.newDocumentBuilder();
			this.doc = this.dBuilder.parse(this.input_file);
			this.doc.getDocumentElement().normalize();
			this.root = this.doc.getDocumentElement();
		} catch(Exception e) {
			System.out.println(e);
		}

	}

	public static void PrintElement(Element elmnt, String name) {
		System.out.println(elmnt.getNodeName());
		NodeList nodes = elmnt.getElementsByTagName(name);
		PrintAttribute(nodes);
	}

	public static void PrintAttribute(NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			Node child = nodes.item(i);
			System.out.print("Node: " + child.getNodeName());
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				NamedNodeMap attribs = child.getAttributes();
				for (int y = 0; y < attribs.getLength(); y++) {
					Node temp = attribs.item(y);
					System.out.print(" " + temp.getNodeName() + "=" + temp.getNodeValue());
				}
				System.out.println("");
			}
		}
	}

	public static void convertAttribs(NodeList nodes, ServerConfig serverList){
		for (int i = 0; i < nodes.getLength(); i++) {
			Node child = nodes.item(i);
			//System.out.print("Node: " + child.getNodeName());
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				NamedNodeMap attribs = child.getAttributes();
				Server tmp = new Server();
				serverList.addServer(tmp);
				for (int y = 0; y < attribs.getLength(); y++) {
					Node temp = attribs.item(y);
					tmp.setAttrib(temp.getNodeName(), temp.getNodeValue());
					//System.out.print(" " + temp.getNodeName() + "=" + temp.getNodeValue());
				}
				//System.out.println("");
			}
		}
	}


	public static void main(String[] args) {
		ServerConfig serverConfig = new ServerConfig(); 
		try {
			Parser parser = new Parser("./ds-sim/system.xml");

			Element server_root = (Element) parser.root.getElementsByTagName("servers").item(0);
			//Element job_root = (Element) root.getElementsByTagName("jobs").item(0);
			//NodeList workload = root.getElementsByTagName("workload");
			//Element termination_root = (Element) root.getElementsByTagName("termination").item(0);


			PrintElement(server_root, "server");
			convertAttribs(server_root.getElementsByTagName("server"), serverConfig);

			System.out.println(serverConfig.getLargest().toString());
			//PrintElement(job_root, "job");
			//PrintAttribute(workload);
			//PrintElement(termination_root, "condition");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
