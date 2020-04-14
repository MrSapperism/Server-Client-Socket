package com;

public class Server {
	int bootupTime;
	int coreCount;
	int disk;
	int limit;
	int memory;
	float rate;
	String type;

	public Server(String type, int bootupTime, int coreCount, int disk, int limit, int memory, float rate){
		setType(type);
		setBootupTime(bootupTime);
		setCoreCount(coreCount);
		setDisk(disk);
		setLimit(limit);
		setMemory(memory);
		setRate(rate);
	}

	public Server(){
		setType("Empty");
	}

	public String setAttrib(String attrib, String attribVal){
		if(attrib.equals("bootupTime"))
			setBootupTime(Integer.parseInt(attribVal));
		if (attrib.equals("coreCount"))
			setCoreCount(Integer.parseInt(attribVal));
		if (attrib.equals("disk"))
			setDisk(Integer.parseInt(attribVal));
		if (attrib.equals("limit"))
			setLimit(Integer.parseInt(attribVal));
		if (attrib.equals("memory"))
			setMemory(Integer.parseInt(attribVal));
		if (attrib.equals("rate"))
			setRate(Float.parseFloat(attribVal));
		if (attrib.equals("type"))
			setType(attribVal);

		return attrib;
	}

	public String toString (){
		return getType() + " " + getCoreCount();
	}

	public int getBootupTime(){
		return this.bootupTime;
	}

	public int getCoreCount(){
		return this.coreCount;
	}

	public int getDisk(){
		return this.disk;
	}

	public int getLimit(){
		return this.limit;
	}

	public int getMemory(){
		return this.memory;
	}

	public float getRate(){
		return this.rate;
	}

	public String getType(){
		return this.type;
	}

	public void setBootupTime(int bootupTime){
		this.bootupTime = bootupTime;
	}

	public void setCoreCount(int coreCount){
		this.coreCount = coreCount;
	}

	public void setDisk(int disk){
		this.disk = disk;
	}

	public void setLimit(int limit){
		this.limit = limit;
	}

	public void setMemory(int memory){
		this.memory = memory;
	}

	public void setRate(float rate){
		this.rate = rate;
	}

	public void setType(String type){
		this.type = type;
	}

}
