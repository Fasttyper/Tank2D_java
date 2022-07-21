import java.util.*; // for using lists
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.Random;

public class BrickGenerator {
	
	private int u = Main.unit * 5;
	
	List<Brick> Bricks = new ArrayList<Brick>();
	
	private void GenereteRandomMap(){
		
		
		Random rand = new Random();
		
		int rand_hp = rand.nextInt(4) + 1;
		/*
		for(int i = 0; i < 13; i++){
			for (int j = 0; j < 12; j++){
				boolean canSpawn = rand.nextInt(1000) > 650;
				if (canSpawn){
					rand_hp = rand.nextInt(4) + 1;
					Brick br = new Brick(i, j, u, rand_hp);
					Bricks.add(br);
				}				
			}
		}	
		*/

		int i = 0;
		int spawnPoint = rand.nextInt(5);
		while(i < 156){			
			rand_hp = rand.nextInt(4) + 1;
			
			if (!((i == 0) || (i == 12) || (i == 143) || (i == 155))){
				if (spawnPoint <= i){
					int div = (i - i % 13) / 13;
					Brick br = new Brick(i % 13, div, u, rand_hp);
					Bricks.add(br);
					spawnPoint += rand.nextInt(5);
				}// else {
				//	Brick.add(null);	
				//}
			}
			System.out.println("i - " + i + " Spawn Point is : " + spawnPoint);
			i++;
		}
	}
	
	private ImageIcon breakBrickImage;
	private ImageIcon solidBrickImage;
	
	public BrickGenerator()
	{
		breakBrickImage = new ImageIcon("assets\\bricks\\1.jpg");
		solidBrickImage = new ImageIcon("assets\\bricks\\2.jpg");	
		GenereteRandomMap();
	}
	
	public boolean isColliding(Rectangle collider){
		for(int i = 0; i < Bricks.size(); i++){
			if (Bricks.get(i).hp > 0){
				if(collider.intersects(new Rectangle(Bricks.get(i).transform.x, Bricks.get(i).transform.y, 50, 50)))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public void draw(Component c, Graphics g)
	{
		for(int i=0; i < Bricks.size(); i++)
		{
			if (Bricks.get(i).hp > 0)
			{
				if (Bricks.get(i).hp == 1){
					breakBrickImage.paintIcon(c, g, Bricks.get(i).transform.x, Bricks.get(i).transform.y);
				} else {
					solidBrickImage.paintIcon(c, g, Bricks.get(i).transform.x, Bricks.get(i).transform.y);
				}
			}
		}
	}
	
	public boolean checkCollision(int x, int y)
	{
		boolean collided = false;
		
		for(int i=0; i< Bricks.size(); i++)
		{
			if (Bricks.get(i).hp > 0){
				if(new Rectangle(x, y, 10, 10).intersects(new Rectangle(Bricks.get(i).transform.x, Bricks.get(i).transform.y, 50, 50)))
				{
					Bricks.get(i).hp--;
					collided = true;
					break;
				}
			}
		}
		return collided;
	}
}
