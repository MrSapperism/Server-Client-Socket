package com;

import java.util.ArrayList;

public class ServerConfig {
	ArrayList<Server> servers;

	public ServerConfig(){
		this.servers = new ArrayList<Server>();
	}

	public void addServer(Server server){
		servers.add(server);
	}

	public Server getLargest(){
		Server tmp = new Server();
		for(Server s : servers){
			if(tmp.type.equals("Empty") || s.getCoreCount() > tmp.getCoreCount() ){
				tmp = s;
			}
		}
		return tmp;
	}

	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		for (Server s : this.servers)
			stringBuilder.append(s.toString() + "\n");
		return stringBuilder.toString();
	}


}
