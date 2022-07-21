import java.lang.Thread;

class ListenThread extends Thread {
	
	public TankListener tankListener;
	
    public void run()
    {
        try {
            // Displaying the thread that is running
            tankListener = new TankListener(int shootKey, int moveKeys[], Tank player){
				tankListener.shootKey = shootKey;
				tankListener.moveKeys = moveKeys;
				tankListener.player = player;
			}
		}
	}
				
			
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}