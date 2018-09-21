
public class APSpec1 {

	public static void main(String[] args) throws InterruptedException {
		
		RoadManager rm= new RoadManager (10, 20);
		
		Thread gridThread= new Thread(rm);
		gridThread.start();
		
		
		while(rm.isFinished()==false) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Car c1=new Car('n', rm);
			Thread t1= new Thread(c1);
			t1.start();
			
			Car c2=new Car('w', rm);
			Thread t2= new Thread(c2);
			t2.start();
		}
		if (rm.isFinished()==true) {
			System.out.println("Simulation ended.");
			System.exit(0);
		}
		
	}	
		
}
