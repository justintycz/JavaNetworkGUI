import java.io.*;

public class commandPrompt {
	/*
	 * This class will hold all the Command Prompt Commands, Mainly the Ping
	 */
	
	public void ping(String ip) {
		
		//Builds the command using the IP Input
		String command = "ping " + ip;
		try {
			//Calls the ping command using the command prompt
			ProcessBuilder builder = new ProcessBuilder(
		            "cmd.exe", "/c", command);
		        builder.redirectErrorStream(true);
		        Process p = builder.start();
		        //Calls the command
		        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		        String line;
		        String full = "";
		        //Takes the entire output on the command prompt and turns it into a string
		        while (true) {
		            line = r.readLine();
		            if (line == null) { break; }
		            full = full + line;
		        }
		        
		        //Searches the entire result string for indication of failure
		        boolean search = full.contains("Destination host unreachable") || full.contains("Request timed out");
		        
		        //Calls the Class that builds the display of the results (Pass/Fail)
		        new pingResults(search);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new InvalidEntry(); //Calls the invalid display class
		}
		
	}
	
	public void ipconfig() {
		/*
		 * Calls the IP Config command thru the command prompt
		 * 
		 * Not currently used, not relevant
		 */
		
		//Builds the command
		String command = "ipconfig";
		try {
			//Like the Ping, this code will build the full command and call the command prompt
			ProcessBuilder builder = new ProcessBuilder(
		            "cmd.exe", "/c", command);
		        builder.redirectErrorStream(true);
		        Process p = builder.start();
		        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		        String line;
		        String full = "";
		        //Builds the entire result into a string
		        while (true) {
		            line = r.readLine();
		            if (line == null) { 
		            	break; 
		            }
		            full = full + line;
		        }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new InvalidEntry(); //Calls the invalid display class
		}
	}
}
