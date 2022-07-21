class Transform
{
	public int x;
	public int y;
	
	public void setPos(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Transform(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Transform(){
		x = 0;
		y = 0;
	}
}