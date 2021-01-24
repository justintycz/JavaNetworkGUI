import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class pingResults extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static Font font = new Font(Font.SERIF, 0, 20);
	
	public pingResults(boolean found) {
		/*
		 * Builds the pass or Fail Displays depening on the boolean input false == pass, true == fail
		 */
		
		//Builds the Pass or Fail displays depending on the result
		setTitle("Ping");
		if(found == false) {
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
			getContentPane().setBackground(Color.GRAY);
			JLabel Label = new JLabel("***  Device Found  ***");
			Label.setFont(font);
			Label.setForeground(Color.GREEN);
			add(Label, BorderLayout.CENTER);
		}
		else {
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
			getContentPane().setBackground(Color.LIGHT_GRAY);
			JLabel Label = new JLabel("***  Device NOT Found  ***");
			Label.setFont(font);
			Label.setForeground(Color.RED);
			add(Label, BorderLayout.CENTER);
		}
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
