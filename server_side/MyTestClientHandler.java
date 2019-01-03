package server_side;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.swing.text.ChangedCharSetException;

public class MyTestClientHandler<Problem,Solution> implements ClientHandler {
	
	private Solver solver;
	private CacheManager cm;

	public MyTestClientHandler(Solver s,CacheManager c) {
		this.solver=s;
		this.cm=c;
	}
	
	@Override
	public void handleClient(InputStream In, OutputStream Out) {
		
		BufferedReader input = new BufferedReader(new InputStreamReader(In));
		PrintWriter output = new PrintWriter(new OutputStreamWriter(Out));
		readInputAndSend(input, output, "end");
	}

	private void readInputAndSend(BufferedReader in,PrintWriter out,String exitStr) {
		try {
			String line;
			while(!(line=in.readLine()).equals(exitStr)){
				Solution solutionStr;
				if(cm.isSolutionCached(line)) {
				out.println(cm.getSolution(line));
					
				}
				else {
					solutionStr = (Solution) solver.solve(line);
					cm.saveSolution(line, solutionStr);
					out.println(solutionStr);
				}
				out.flush();
			}
			

		}catch (IOException e) {e.printStackTrace();}
	}
}

	
	

	