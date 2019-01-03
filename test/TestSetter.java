package test;

import server_side.FileCacheManager;

// change this to match your code

import server_side.MySerialServer;
import server_side.MyTestClientHandler;
import server_side.Server;


public class TestSetter {
	

	static Server s; 
	
	public static void runServer(int port) {
		
			s =	new MySerialServer(port,new MyTestClientHandler<String,String>(
				str -> new StringBuilder((String)str).reverse().toString(),
				new FileCacheManager<String,String>())); 
	      		s.start();
	      }

	public static void stopServer() {
		s.stop();
	}
	

}
