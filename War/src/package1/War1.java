package package1;
//Carter Crouch
import java.awt.*;
import javax.swing.*;
//creates and initializes the frame
public class War1 {
	public static void main(String[] args) {
		JFrame frame = new JFrame("WAR");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		War2 panel = new War2();
		frame.getContentPane().add(panel);
		
		panel.setBorder(BorderFactory.createBevelBorder(0, Color.CYAN, Color.DARK_GRAY));
		frame.pack();
		frame.setVisible(true);
		frame.setLocation(400,100);
	}
}
