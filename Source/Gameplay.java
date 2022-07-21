import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Gameplay  extends JPanel implements ActionListener 
{
	// Game properties
	private Timer timer;
	
	private int delay = 8;
	
	private boolean play = true;
	
	// Event properties
	private GameListener gameListener;
	
	private BrickGenerator br;
	
	public java.util.List<Tank> tankHolder = new java.util.ArrayList<Tank>();
	
	
	public Gameplay()
	{
		br = new BrickGenerator();
		
		Tank player1;
		int MoveKeys[] = new int[]{KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S};
		player1 = new Tank("assets\\tanks\\", 1, 0, 550, 5, "1", br, KeyEvent.VK_U, MoveKeys);
		
		Tank player2;
		int MoveKeys1[] = new int[]{KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN};
		player2 = new Tank("assets\\tanks\\", 1, 600, 0, 5, "2", br, KeyEvent.VK_ENTER, MoveKeys1);
		
		tankHolder.add(player1);
		tankHolder.add(player2);
	
		setFocusable(true);
		
		for(int i = 0; i < tankHolder.size(); i++){
			addKeyListener(tankHolder.get(i).tankListener);
		}
		
		gameListener = new GameListener();
		GameListener gameListener = new GameListener();
		addKeyListener(gameListener);

		setFocusTraversalKeysEnabled(false);
		
        timer=new Timer(delay,this);
		
		timer.start();
	}
	
	public boolean isColliding(Transform bullet, Transform tank){
		if(new Rectangle(bullet.x, bullet.y, 5, 5)
			.intersects(new Rectangle(tank.x, tank.y, 50, 50)))
		{
			return true;
		}
		return false;
	}
	
	public void paint(Graphics g)
	{    		
		// play background
		g.setColor(new Color(248, 234, 144));
		g.fillRect(0, 0, 650, 600);
		
		// right side background
		g.setColor(Color.DARK_GRAY);
		g.fillRect(660, 0, 140, 600);
	
		
		
		br.draw(this, g);
		
		if(play)
		{
			
			g.setColor(Color.blue);
			
			//for(int i = 0; i < tankHolder.size(); i++){
			//	tankHolder.get(i).DrawColRectangle(g);
			//}
			
			for(int i = 0; i < tankHolder.size(); i++){
				for(int j = 0; j < tankHolder.size(); j++){
					if(i != j && tankHolder.get(i).bullet != null){
						if(isColliding(tankHolder.get(i).bullet.transform, tankHolder.get(j).transform)){
							tankHolder.get(i).score += 10;
							tankHolder.get(j).hp--;
							tankHolder.get(i).bullet = null;
							tankHolder.get(i).loaded = true;
						}
					}
				}
			}

			for(int i = 0; i < tankHolder.size(); i++){
				tankHolder.get(i).updateDir();
				tankHolder.get(i).tankImage.paintIcon(this, g, tankHolder.get(i).transform.x, tankHolder.get(i).transform.y);
				if (tankHolder.get(i).bullet != null)
				{
					tankHolder.get(i).shooting(g);
				}
			}
		}
	
		
		// the scores 		
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 15));
		g.drawString("Scores", 700, 30);
		int cursor = 60;
		for(int i = 0; i < tankHolder.size(); i++){
			
			g.drawString("Player " + (i + 1) + " :  " + tankHolder.get(i).score, 670, cursor);
			cursor += 30;
			g.drawString("Player " + (i + 1) + " HP :  " + tankHolder.get(i).hp, 670, cursor);
			cursor += 30;
		}
		
		if(tankHolder.get(0).hp <= 0)
		{			
			g.setColor(Color.white);
			g.setFont(new Font("serif",Font.BOLD, 60));
			
			g.setColor(Color.blue);
			ImageIcon gameOverImage = new ImageIcon("assets\\gameover.png");
			gameOverImage.paintIcon(this, g, 100, 100);
			//g.fillRect(100, 100, 500, 400);
			
			g.setColor(Color.white);
			g.drawString("Game Over!", 200, 200);
			g.drawString("Player 1 Won", 180,280);
			
			play = false;
			g.setColor(Color.white);
			g.setFont(new Font("serif",Font.BOLD, 30));
			g.drawString("(Space to Restart)", 230,430);
		}
		
		if(tankHolder.get(1).hp <= 0)
		{			
			g.setColor(Color.white);
			g.setFont(new Font("serif",Font.BOLD, 60));
			
			g.setColor(Color.blue);
			ImageIcon gameOverImage = new ImageIcon("assets\\gameover.png");
			gameOverImage.paintIcon(this, g, 100, 100);
			//g.fillRect(100, 100, 500, 400);
			
			g.setColor(Color.white);
			g.drawString("Game Over!", 200, 200);
			g.drawString("Player 2 Won", 180,280);
			
			play = false;
			g.setColor(Color.white);
			g.setFont(new Font("serif",Font.BOLD, 30));
			g.drawString("(Space to Restart)", 230,430);
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		repaint();
	}


	public void Restart(){
		
		br = new BrickGenerator();
		
		tankHolder.get(0).transform.setPos(0, 550);	
		tankHolder.get(1).transform.setPos(600, 0);
		
		for(int i = 0; i < tankHolder.size(); i++){
			tankHolder.get(i).direction = 1;
			tankHolder.get(i).updateDir();
			tankHolder.get(i).score = 0;
			tankHolder.get(i).hp = 5;
			tankHolder.get(i).br = br;
			tankHolder.get(i).bullet = null;
			tankHolder.get(i).loaded = true;
		}
		play = true;
		repaint();		
	}	
		
	private class GameListener implements KeyListener{
		public void keyTyped(KeyEvent e) {}
		
		public void keyReleased(KeyEvent e) {}
		
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()== KeyEvent.VK_SPACE && (tankHolder.get(0).hp <= 0 || tankHolder.get(1).hp <= 0) )
			{
				Restart();
			}
		}
	}
}