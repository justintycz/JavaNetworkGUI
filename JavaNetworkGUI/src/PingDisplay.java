
public class PingDisplay {
	
	public String getIP(String ip){
		/*
		 * Builds the Ping display
		 */
		
		String partial = "";
		String address = ip;
		
		//Splits the string by periods (".")
		String[] arr = address.split("\\.");
		//Loop that only builds the first 3 oct of the ip address, this will allow the user to put in the last oct
		for(int i=0; i<arr.length-1; i++) {
			
			partial = partial + arr[i] + ".";
		}
		
		return partial;
	}
}
