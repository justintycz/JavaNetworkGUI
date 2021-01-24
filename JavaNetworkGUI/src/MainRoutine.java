import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class MainRoutine extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static Network network = new Network();
	public static commandPrompt command = new commandPrompt();
	
	public static Font font = new Font(Font.SERIF, 0, 50);
	public static Font font2 = new Font(Font.SANS_SERIF, 0, 25);
	public static Font font3 = new Font(Font.SERIF, 0, 40);
	
	public static LineBorder border = new LineBorder(Color.GRAY, 2);
	public static TitledBorder borderCurrent = BorderFactory.createTitledBorder(border, "Current Network", TitledBorder.LEFT, TitledBorder.TOP, font2);
	public static TitledBorder borderPing = BorderFactory.createTitledBorder(border, "Ping", TitledBorder.LEFT, TitledBorder.TOP, font2);
	public static TitledBorder borderChange = BorderFactory.createTitledBorder(border, "Change Network", TitledBorder.LEFT, TitledBorder.TOP, font2);
	
	private static JPanel display;
	private static JPanel ping;
	private static JPanel pingButtonDisplay;
	private static JPanel pingIPDisplay;
	private static JPanel changeDisplay;
	private static JPanel changeDisplay1;
	private static JPanel changeDisplay2;
	private static JPanel changeIPDisplay;
	private static JPanel changeSMDisplay;
	private static JPanel changeGWDisplay;
	private static JPanel changeButtonDisplay;
	private static JTextField textInput;
	private static JTextField Oct1IP;
	private static JTextField Oct2IP;
	private static JTextField Oct3IP;
	private static JTextField Oct4IP;
	private static JTextField Oct1SM;
	private static JTextField Oct2SM;
	private static JTextField Oct3SM;
	private static JTextField Oct4SM;
	private static JTextField Oct1GW;
	private static JTextField Oct2GW;
	private static JTextField Oct3GW;
	private static JTextField Oct4GW;
	private static PingDisplay pingDisplay = new PingDisplay();
	private JButton pingButton;
	private JButton changeButton;
	
	public void createCurrent() {
		/*
		 * This Method gets the Current IP, Subnet, Gateway and creates a display
		 */
		
		//Calls the Network Class that pulls IP
		JLabel ipLabel = new JLabel("IP Address:     " + network.IP());
		ipLabel.setFont(font);
		//Calls the Network Class that pulls the Subnet Mask
		JLabel maskLabel = new JLabel("Subnet Mask:  " + network.Mask());
		maskLabel.setFont(font);
		//Calls the Network Class that pulls the Gateway
		JLabel gatewayLabel = new JLabel("Default Gateway:    " + network.Gateway());
		gatewayLabel.setFont(font);
		
		display = new JPanel();
		//display.setPreferredSize(new Dimension(250, 100));
		display.setLayout(new BorderLayout());
		//Builds the IP Label on the Panel
		display.add(ipLabel, BorderLayout.NORTH);
		//Builds the Mask Label on the Panel
		display.add(maskLabel, BorderLayout.CENTER);
		//Builds the Gateway Label on the Panel
		//display.add(gatewayLabel, BorderLayout.SOUTH); //Gateway is blanked out so it does not display
		display.setBorder(borderCurrent); //Border
		pack();
		display.setVisible(true);
	}
	
	public void createPing() {
		/*
		 * Builds the Ping Panel
		 */
		
		pingButtonDisplay(); //Calls Method
		pingIPDisplay(); //Calls Method
		
		ping = new JPanel();
		ping.setLayout(new BorderLayout());
		ping.add(pingIPDisplay, BorderLayout.NORTH);
		ping.add(pingButtonDisplay, BorderLayout.SOUTH);
		ping.setBorder(borderPing);
		pack();
		ping.setVisible(true);
	}
	
	public void pingButtonDisplay() {
		/*
		 * Builds the Button to Ping an IP Address
		 */
		
		pingButton = new JButton("Ping");
		pingButton.setFont(font3);
		pingButton.addActionListener(new pingButtonListener());
		
		pingButtonDisplay = new JPanel();
		pingButtonDisplay.add(pingButton);
		pack();
		pingButtonDisplay.setVisible(true);
	}
	
	public void pingIPDisplay() {
		
		/*
		 * Builds the display that requires the user input
		 */
		
		//Builds the label by sending it thru the Class that takes the first 3 oct
		JLabel pingLabel = new JLabel(pingDisplay.getIP(network.IP()));
		pingLabel.setFont(font); //Pulls the font
		//Adds a text field the user will put in the last oct
		textInput = new JTextField("123",3);
		textInput.setFont(font);
		
		pingIPDisplay = new JPanel();
		pingIPDisplay.setLayout(new FlowLayout());
		pingIPDisplay.add(pingLabel, FlowLayout.LEFT);
		pingIPDisplay.add(textInput, FlowLayout.CENTER);
		pack();
		pingIPDisplay.setVisible(true);
	}
	
	private class pingButtonListener implements ActionListener {
		/*
		 * When the button is pressed it gets the last Octate and combines it with the first 3, 
		 * then sends it thru the command prompt
		 */
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String lastOct = textInput.getText(); // Gets the user input
			
			try {
				if(lastOct.length()<4 && lastOct.length()>0 && Integer.parseInt(lastOct)>0) {
					//Builds the IP Address
					String pingAddress = pingDisplay.getIP(network.IP()) + lastOct;
					//Sends the IP address thru the command prompt to be pinged
					command.ping(pingAddress);
				}
				else {
					new InvalidEntry(); //Calls Class that builds the invalid entry display
				}
		      } catch (NumberFormatException e1) {  
		    	new InvalidEntry(); //Calls Class that builds the invalid entry display
		      }
			
		}
		
	}
	
	public void changeIPDisplay() {
		
		/*
		 * This Method creates the Panel for Changing the IP Address
		 */
		
		//Builds the user inputs and the periods, attaching Fonts to each
		JLabel changeIPLabel = new JLabel("IP Address:          ");
		changeIPLabel.setFont(font);
		Oct1IP = new JTextField("",3);
		Oct1IP.setFont(font);
		Oct2IP = new JTextField("",3);
		Oct2IP.setFont(font);
		Oct3IP = new JTextField("",3);
		Oct3IP.setFont(font);
		Oct4IP = new JTextField("",3);
		Oct4IP.setFont(font);
		JLabel periodLabel = new JLabel(".");
		periodLabel.setFont(font);
		JLabel periodLabel2 = new JLabel(".");
		periodLabel2.setFont(font);
		JLabel periodLabel3 = new JLabel(".");
		periodLabel3.setFont(font);
		
		//Builds the display in the order it needs to be in
		changeIPDisplay = new JPanel();
		changeIPDisplay.setLayout(new FlowLayout());
		changeIPDisplay.add(changeIPLabel);
		changeIPDisplay.add(Oct1IP);
		changeIPDisplay.add(periodLabel);
		changeIPDisplay.add(Oct2IP);
		changeIPDisplay.add(periodLabel2);
		changeIPDisplay.add(Oct3IP);
		changeIPDisplay.add(periodLabel3);
		changeIPDisplay.add(Oct4IP);
		pack();
		changeIPDisplay.setVisible(true);
	}
	
	public void changeSMDisplay() {
		
		/*
		 * his Method creates the Panel for Changing the Subnet Mask Address
		 */
		
		//Builds the user inputs and the periods, attaching Fonts to each
		JLabel changeIPLabel = new JLabel("Subnet Mask:       ");
		changeIPLabel.setFont(font);
		Oct1SM = new JTextField("",3);
		Oct1SM.setFont(font);
		Oct2SM = new JTextField("",3);
		Oct2SM.setFont(font);
		Oct3SM = new JTextField("",3);
		Oct3SM.setFont(font);
		Oct4SM = new JTextField("",3);
		Oct4SM.setFont(font);
		JLabel periodLabel = new JLabel(".");
		periodLabel.setFont(font);
		JLabel periodLabel2 = new JLabel(".");
		periodLabel2.setFont(font);
		JLabel periodLabel3 = new JLabel(".");
		periodLabel3.setFont(font);
		
		//Builds the display in the order it needs to be in
		changeSMDisplay = new JPanel();
		changeSMDisplay.setLayout(new FlowLayout());
		changeSMDisplay.add(changeIPLabel);
		changeSMDisplay.add(Oct1SM);
		changeSMDisplay.add(periodLabel);
		changeSMDisplay.add(Oct2SM);
		changeSMDisplay.add(periodLabel2);
		changeSMDisplay.add(Oct3SM);
		changeSMDisplay.add(periodLabel3);
		changeSMDisplay.add(Oct4SM);
		pack();
		changeSMDisplay.setVisible(true);
	}
	
	public void changeGWDisplay() {
		/*
		 * his Method creates the Panel for Changing the Gateway Address
		 */
		
		//Builds the user inputs and the periods, attaching Fonts to each
		JLabel changeIPLabel = new JLabel("Default Gateway: ");
		changeIPLabel.setFont(font);
		Oct1GW = new JTextField("",3);
		Oct1GW.setFont(font);
		Oct2GW = new JTextField("",3);
		Oct2GW.setFont(font);
		Oct3GW = new JTextField("",3);
		Oct3GW.setFont(font);
		Oct4GW = new JTextField("",3);
		Oct4GW.setFont(font);
		JLabel periodLabel = new JLabel(".");
		periodLabel.setFont(font);
		JLabel periodLabel2 = new JLabel(".");
		periodLabel2.setFont(font);
		JLabel periodLabel3 = new JLabel(".");
		periodLabel3.setFont(font);
		
		//Builds the display in the order it needs to be in
		changeGWDisplay = new JPanel();
		changeGWDisplay.setLayout(new FlowLayout());
		changeGWDisplay.add(changeIPLabel);
		changeGWDisplay.add(Oct1GW);
		changeGWDisplay.add(periodLabel);
		changeGWDisplay.add(Oct2GW);
		changeGWDisplay.add(periodLabel2);
		changeGWDisplay.add(Oct3GW);
		changeGWDisplay.add(periodLabel3);
		changeGWDisplay.add(Oct4GW);
		pack();
		changeGWDisplay.setVisible(true);
	}
	
	public void changeButtonDisplay() {
		
		//Builds the button to change the Network
		changeButton = new JButton("Change");
		changeButton.setFont(font3);
		changeButton.addActionListener(new changeButtonListener());
		
		changeButtonDisplay = new JPanel();
		changeButtonDisplay.add(changeButton);
		pack();
		changeButtonDisplay.setVisible(true);
	}
	
	private class changeButtonListener implements ActionListener {
		/*
		 * Changes the IP, Subnet, and Gateway when the button is pressed
		 */
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String IP = "";
			String Subnet = "";
			String Gateway = "";
			
			try {
			
			//Pulls all the user inputs and creates the strings for the new IP, Subnet, and Gateway
			if(Oct1IP.getText().length()<4 && Oct1IP.getText().length()>0 && Integer.parseInt(Oct1IP.getText())>-1 && Oct2IP.getText().length()<4 && Oct2IP.getText().length()>0 && Integer.parseInt(Oct2IP.getText())>-1 && Oct3IP.getText().length()<4 && Oct3IP.getText().length()>0 && Integer.parseInt(Oct3IP.getText())>-1 && Oct4IP.getText().length()<4 && Oct4IP.getText().length()>0 && Integer.parseInt(Oct4IP.getText())>-1) {
				IP = Oct1IP.getText() + "." + Oct2IP.getText() + "." + Oct3IP.getText() + "." + Oct4IP.getText();
			}
			else{
				System.out.println("IP"); //Used for Troubleshooting
				new InvalidEntry(); //Creates invalid outputs display
			}
			if(Oct1SM.getText().length()<4 && Oct1SM.getText().length()>0 && Integer.parseInt(Oct1SM.getText())>-1 && Oct2SM.getText().length()<4 && Oct2SM.getText().length()>0 && Integer.parseInt(Oct2SM.getText())>-1 && Oct3SM.getText().length()<4 && Oct3SM.getText().length()>0 && Integer.parseInt(Oct3SM.getText())>-1 && Oct4SM.getText().length()<4 && Oct4SM.getText().length()>0 && Integer.parseInt(Oct4SM.getText())>-1) {
				Subnet = Oct1SM.getText() + "." + Oct2SM.getText() + "." + Oct3SM.getText() + "." + Oct4SM.getText();
			}
			else{
				System.out.println("Subnet"); //Used for Troubleshooting
				new InvalidEntry(); //Creates invalid outputs display
			}
			if((Oct1GW.getText().length()<4 && Oct1GW.getText().length()>0 && Integer.parseInt(Oct1GW.getText())>-1 && Oct2GW.getText().length()<4 && Oct2GW.getText().length()>0 && Integer.parseInt(Oct2GW.getText())>-1 && Oct3GW.getText().length()<4 && Oct3GW.getText().length()>0 && Integer.parseInt(Oct3GW.getText())>-1 && Oct4GW.getText().length()<4 && Oct4GW.getText().length()>0 && Integer.parseInt(Oct4GW.getText())>-1) || (Oct1GW.getText().length()==0 && Oct2GW.getText().length()==0 && Oct3GW.getText().length()==0 && Oct4GW.getText().length()==0)) {
				if((Oct1GW.getText().length()==0 && Oct2GW.getText().length()==0 && Oct3GW.getText().length()==0 && Oct4GW.getText().length()==0)) {
					
					Gateway = "none";
				}
				else {
					Gateway = Oct1GW.getText() + "." + Oct2GW.getText() + "." + Oct3GW.getText() + "." + Oct4GW.getText();
				}
			}
			else{
				System.out.println("Gateway"); //Used for Troubleshooting
				new InvalidEntry(); //Creates invalid outputs display
			}
			
			if(IP.length()>0 && Subnet.length()>0 && Gateway.length()>0) {
				
				//Sends the stings to the Build Bath File class
				buildBatchFile batch = new buildBatchFile();
				batch.buildBatch(IP, Subnet, Gateway);
				System.out.println("Batch");
			}
			else{
				new InvalidEntry(); //Creates invalid outputs display
			}
				
			} catch (NumberFormatException e1) {  
				System.out.println("Overall"); //Used for Troubleshooting
				new InvalidEntry(); //Creates invalid outputs display
			}
			
		}
		
	}
	
	public void changeDisplay1(){
		/*
		 * Builds the whole Change Network panel
		 */
		
		//Calls the methods to build the individual panels 
		changeIPDisplay();
		changeSMDisplay();
		changeGWDisplay();
		
		//Builds the whole change Network display
		changeDisplay1 = new JPanel();
		changeDisplay1.setLayout(new BorderLayout());
		changeDisplay1.add(changeIPDisplay, BorderLayout.NORTH);
		changeDisplay1.add(changeSMDisplay, BorderLayout.CENTER);
		//changeDisplay1.add(changeGWDisplay, BorderLayout.SOUTH);
		pack();
		changeDisplay1.setVisible(true);
	}
	
	public void changeDisplay2(){
		
		//Builds a seperate section for the button so the display can have 4 rows on the Change Network 
		changeButtonDisplay();
		
		changeDisplay2 = new JPanel();
		changeDisplay2.add(changeButtonDisplay);
		pack();
		changeDisplay2.setVisible(true);
	}
	
	public void changeDisplay(){
		
		//Combines the user inputs and the button to one display
		changeDisplay1();
		changeDisplay2();
		
		changeDisplay = new JPanel();
		changeDisplay.setLayout(new BorderLayout());
		changeDisplay.add(changeDisplay1,BorderLayout.NORTH);
		changeDisplay.add(changeDisplay2,BorderLayout.CENTER);
		changeDisplay.setBorder(borderChange);
		pack();
		changeDisplay.setVisible(true);
	}

	public MainRoutine() {
		/*
		 * Builds the overall display with all the combines panels
		 */
		
		//int Window_Width = 500;
		//int Window_Height = 150;
		
		//Sets the title of the pop-up
		setTitle("Networking");
		
		//setPreferredSize(new Dimension(Window_Width, Window_Height));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		//Calls all the panels
		createCurrent();
		createPing();
		changeDisplay();
		
		//Builds the display
		add(display, BorderLayout.CENTER);
		add(ping, BorderLayout.EAST);
		add(changeDisplay, BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public static void main(String[] args) {
		
		//System.out.println("Ip Address: --------- " + network.IP());
		//System.out.println("Subnet Mask: -------- " + network.Mask());
		//System.out.println("Default Gateway: ---- " + network.Gateway());
		
		//Calls the Main Routine
		new MainRoutine();
	}
}
