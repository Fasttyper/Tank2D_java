import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;


public class Bullet {
	
	public Transform transform = new Transform(0, 0);
	private int direction = 0;
	
	//public Tank shooter;
	
	public Bullet(int x, int y, int dir)//, Tank shooter)
	{
		transform.x = x;
		transform.y = y;
		direction = dir;
		//this.shooter = shooter;
	}
	
	public void move()
	{
		switch(direction){    
			case 0:    
				transform.x += 5;
			break;   
			case 1:    
				transform.y -= 5;
			break;
			case 2: 
				transform.x -= 5;
			break;	
			case 3: 
				transform.y +=5;
			break;
		}  			
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.blue);
		g.fillOval(transform.x, transform.y, 10, 10);
	}
	
	public int getX()
	{
		return transform.x;
	}
	public int getY()
	{
		return transform.y;
	}

}
