import java.awt.Color;

import javax.swing.JFrame;


public class Main {

	public static Transform Screen = new Transform(800, 630);
	public static int unit = 10;

	public static void main(String[] args) {
		JFrame obj=new JFrame();
		Gameplay gamePlay = new Gameplay();
		
		obj.setBounds(10, 10, Screen.x, Screen.y);
		obj.setTitle("Tank 2D");	
		obj.setBackground(Color.gray);
		obj.setResizable(false);
		
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gamePlay);
		obj.setVisible(true);

	}

}
