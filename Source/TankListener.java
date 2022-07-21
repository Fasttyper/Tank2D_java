import java.awt.event.*;

public class TankListener implements KeyListener
{
	public Tank player;
	public int shootKey;
	public int moveKeys[] = new int[4];
	
	private boolean isKeyReleased[] = new boolean[5];
	
	TankListener(int shootKey, int moveKeys[], Tank player){
		this.shootKey = shootKey;
		this.moveKeys = moveKeys;
		this.player = player;
	}
	
	public void keyTyped(KeyEvent e) {}	
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()== shootKey)
		{
			if (player.loaded){
				isKeyReleased[0] = true;
			}
		} else if(e.getKeyCode()== moveKeys[0])
		{
			isKeyReleased[1] = true;
		} else if(e.getKeyCode()== moveKeys[1])
		{
			isKeyReleased[2] = true;
		} else if (e.getKeyCode()== moveKeys[2])
		{
			isKeyReleased[3] = true;
		} else if(e.getKeyCode()== moveKeys[3])
		{
			isKeyReleased[4] = true;
		}
	}	
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== shootKey && isKeyReleased[0])
		{
			if (player.loaded){
				player.shoot();
				isKeyReleased[0] = false;
			}
		} else if(e.getKeyCode()== moveKeys[0] && isKeyReleased[1])
		{
			player.Move(0, 10);
			isKeyReleased[1] = false;
		} else if(e.getKeyCode()== moveKeys[1] && isKeyReleased[2])
		{
			player.Move(1, 10);
			isKeyReleased[2] = false;
		} else if (e.getKeyCode()== moveKeys[2] && isKeyReleased[3])
		{
			player.Move(2, 10);
			isKeyReleased[3] = false;
		} else if(e.getKeyCode()== moveKeys[3] && isKeyReleased[4])
		{
			player.Move(3, 10);
			isKeyReleased[4] = false;
		}
	}
}