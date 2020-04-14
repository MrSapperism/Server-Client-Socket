
class Server {
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

	int getBootupTime(){
		return this.bootupTime;
	}

	int getCoreCount(){
		return this.coreCount;
	}

	int getDisk(){
		return this.disk;
	}

	int getLimit(){
		return this.limit;
	}

	int getMemory(){
		return this.memory;
	}

	float getRate(){
		return this.rate;
	}

	String getType(){
		return this.type;
	}

	void setBootupTime(int bootupTime){
		this.bootupTime = bootupTime;
	}

	void setCoreCount(int coreCount){
		this.coreCount = coreCount;
	}

	void setDisk(int disk){
		this.disk = disk;
	}

	void setLimit(int limit){
		this.limit = limit;
	}

	void setMemory(int memory){
		this.memory = memory;
	}

	void setRate(float rate){
		this.rate = rate;
	}

	void setType(String type){
		this.type = type;
	}

}
