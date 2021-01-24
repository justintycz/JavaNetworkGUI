import java.awt.*;

import javax.swing.*;

public class InvalidEntry extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static Font font = new Font(Font.SERIF, 0, 20);
	
	public InvalidEntry() {
		/*
		 * Builds the display when there is an Invalid Entry
		 */
		
		setTitle("Invalid");
		setBounds(100, 100, 450, 300);
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.YELLOW);
		setLayout(new BorderLayout());
		
		//Creates the Label that displays the Invalid Entry
		JLabel Label = new JLabel("***  INVALID ENTRY  ***");
		Label.setFont(font);
		//Sets the Label to Red
		Label.setForeground(Color.RED);
		add(Label, BorderLayout.CENTER);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
