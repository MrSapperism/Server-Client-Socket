package com;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Parser {

	File input_file;
	DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;
	Document doc;
	public Element root;

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

	public void PrintElement(Element elmnt, String name) {
		System.out.println(elmnt.getNodeName());
		NodeList nodes = elmnt.getElementsByTagName(name);
		PrintAttribute(nodes);
	}

	public void PrintAttribute(NodeList nodes) {
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

	public void convertAttribs(NodeList nodes, ServerConfig serverList){
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

	/*
	public static void main(String[] args) {
		ServerConfig serverConfig = new ServerConfig(); 
		try {
			Parser parser = new Parser("./ds-sim/system.xml");

			Element server_root = (Element) parser.root.getElementsByTagName("servers").item(0);

			PrintElement(server_root, "server");
			convertAttribs(server_root.getElementsByTagName("server"), serverConfig);

			System.out.println(serverConfig.getLargest().toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}
