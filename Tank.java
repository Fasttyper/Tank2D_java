import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Tank
{
	
	private String imagePath;
	
	public Bullet bullet = null;

	public ImageIcon tankImage;
	
	public String type = "1";
	
	public int direction = 1;
	
	public Transform transform = new Transform(0, 0);	
	
	public int score = 0;
	
	public int hp = 5;
	
	public BrickGenerator br;
	
	public boolean loaded = true;
	
	public TankListener tankListener;
	
	// draft
	
	public void shoot(){

		loaded = false;
		
		switch(direction){    
			case 0:    
				bullet = new Bullet(transform.x + 40, transform.y + 20, 0); 
				break;
			case 1:    
				bullet = new Bullet(transform.x + 20, transform.y, 1);
				break;
			case 2:    

				bullet = new Bullet(transform.x, transform.y + 20, 2);
				break;
			case 3:    
				bullet = new Bullet(transform.x + 20, transform.y + 40, 3);	
				break;
		}    					
		
	}
		
	public void DrawColRectangle(Graphics g){
		g.setColor(Color.blue);
		if(direction == 0)
		{
			g.fillRect(transform.x + 50, transform.y + 5, 5, 40);
		}
		else if (direction == 1)
		{
			g.fillRect(transform.x + 5, transform.y - 5, 40, 5);
		}
		else if (direction == 2)
		{
			g.fillRect(transform.x - 5, transform.y + 5, 5, 40);
		}
		else if (direction == 3)
		{
			g.fillRect(transform.x + 5, transform.y + 50, 40, 5);
		} 	
		//g.fillRect(660, 0, 140, 600);
	}
	
	
	public Rectangle colRectangle(){
		if(direction == 0)
		{
			return new Rectangle(transform.x + 50, transform.y + 5, 5, 40);
		}
		else if (direction == 1)
		{
			return new Rectangle(transform.x + 5, transform.y - 5, 40, 5);
		}
		else if (direction == 2)
		{
			return new Rectangle(transform.x - 5, transform.y + 5, 5, 40);
		}
		else if (direction == 3)
		{
			return new Rectangle(transform.x + 5, transform.y + 50, 40, 5);
		} 
		return new Rectangle(100000,100000,1,1);
	}
	
	public void shooting(Graphics g){
		if(bullet != null)
		{
			bullet.move();
			bullet.draw(g);				
			
			if(br.checkCollision(bullet.getX(), bullet.getY()))
			{
				bullet = null;
				loaded = true;				
			}

			if(bullet.transform.y < 1
				|| bullet.transform.y > 580
				|| bullet.transform.x < 1
				|| bullet.transform.x > 630)
			{
				bullet = null;
				loaded = true;
			}
		}	
	}
		
	public void Move(int dir, int speed){
		if (direction == dir){
			switch(direction){    
				case 0:    
					if(transform.x < 600 && !br.isColliding(colRectangle())) //Main.Screen.x)
						transform.x += speed;
					break;
				case 1:    
					if(transform.y > speed - 5 && !br.isColliding(colRectangle()))
						transform.y -= speed;
					break;
				case 2:    
					if(transform.x > speed - 5 && !br.isColliding(colRectangle()))
						transform.x -= speed;
					break;
				case 3:    
					if(transform.y < Main.Screen.y - 80 && !br.isColliding(colRectangle()))
						transform.y += speed;	
					break;				
			}
		} else {
			direction = dir;
		}		
	}
		
	public void updateDir(){
		String imgPath = imagePath + type + "\\" + String.valueOf(direction) + ".png";
		tankImage =  new ImageIcon(imgPath);
	}
	
	public Tank(String imgPath, int dir, int x, int y, int hp, 
		String type, BrickGenerator br, 
		int shootKey, int MoveKeys[])
	{
			
			
		imagePath = imgPath;
		direction = dir;
		this.br = br;
		this.type = type;
		this.hp = hp;
		transform.setPos(x, y);
		
		//ListenThread thread = new ListenThread(shootKey, MoveKeys, this);
		
		//thread.start();
		
		//tankListener = thread.tankListener();
		
		
		tankListener = new TankListener(shootKey, MoveKeys, this);
		
		updateDir();
	}
}
