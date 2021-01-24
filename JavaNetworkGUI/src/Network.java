import java.net.*;

public class Network {
	
	public String IP(){
		/*
		 * Pulls the IP address 
		 */
		
		String ip = "*Failed*";
		InetAddress localHost;
		
		try {
			//Pulls the IP address from the computer using the net import
			localHost = InetAddress.getLocalHost();
			//Trims the string by getting rid of white space
			ip = (localHost.getHostAddress()).trim();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new InvalidEntry(); // Calls the invalid display
		} 
		
        return ip;
	
	}
	
	public String Mask() {
		/*
		 * Finds the Subnet Mask
		 */
		
		String mask = "*Failed*";
    	InetAddress localHost;
    	
		try {
			//Calls the local host using the net import
			localHost = Inet4Address.getLocalHost();
			NetworkInterface networkInterface;
			try {
				//Pulls the network interface
				networkInterface = NetworkInterface.getByInetAddress(localHost);
				//Takes the network interface and builds the mask by pulling the oct
				int shft = 0xffffffff<<(32-networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength());
				    	int oct1 = ((byte) ((shft&0xff000000)>>24)) & 0xff;
				    	int oct2 = ((byte) ((shft&0x00ff0000)>>16)) & 0xff;
				    	int oct3 = ((byte) ((shft&0x0000ff00)>>8)) & 0xff;
				    	int oct4 = ((byte) (shft&0x000000ff)) & 0xff;
				    	mask = oct1+"."+oct2+"."+oct3+"."+oct4;
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				new InvalidEntry(); // Calls the invalid display
			}  
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new InvalidEntry(); // Calls the invalid display
		}         
        
		return mask;
	}
	
	public String Gateway() {
		/*
		 * Finds the Gateway
		 * 
		 * This section is empty, the option to be added to the panel
		 */
		
		String gateway = "*Failed*";
    	 
        
		return gateway;
	}
}
