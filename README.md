# Server-Client-Socket

Note: Requires JDK11

## Installing the required libraries
```sudo bash install.sh```

## Building the Client and Server
```bash
bash build.sh
```
  - can also build with ```javac Server.java Client.java```

## Running the Client and Server
```bash
java Server
java Client
```

## Running the Client with DS-SERVER
```bash
./ds-server -c config_simple1.xml -v all
java Client
```
