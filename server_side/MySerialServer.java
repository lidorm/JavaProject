package server_side;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MySerialServer implements Server{

private int port;
private ClientHandler ch; 
private volatile boolean stop;

	public MySerialServer(int port,ClientHandler c) {
		this.port=port;
		this.ch = c;
		stop = false;
	}
	@Override
	public void open(int port,ClientHandler c) {
		this.port=port;
		this.ch = c;
		stop = false;
	
	}
	
	public void start() {
		new Thread(()->{
			try {
				runServer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	public void runServer() throws Exception{
		
		ServerSocket server=new ServerSocket(port);
		server.setSoTimeout(1000);
		while(!stop){
		try{
			Socket aClient=server.accept(); // blocking call
		try {
			ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
			aClient.getInputStream().close();
			aClient.getOutputStream().close(); 
			aClient.close();
	} catch (IOException e) {/*...*/}
	}catch(SocketTimeoutException e) {/*...*/}
			
		}
		server.close();
}
	@Override
	public void stop() {
		stop = true;		
	}

}
