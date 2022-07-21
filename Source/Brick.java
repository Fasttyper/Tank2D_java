public class Brick{
	
	public Transform transform = new Transform(0, 0);
	
	public int hp;
	
	public Brick(int x, int y, int unit, int hp){
		transform.x = x * unit;
		transform.y = y * unit;
		this.hp = hp;
	}	
}