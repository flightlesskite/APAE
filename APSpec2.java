
public class APSpec2 {
	
	public static void main(String[] args) throws InterruptedException {
		RoadManager rm= new RoadManager (10, 20);
	
		Thread gridThread= new Thread(rm);
		gridThread.start();
		
		Generator gen= new Generator('n', rm, "default");
		Thread t1= new Thread(gen);
		t1.start();
		Generator gen2= new Generator('w', rm, "default");
		Thread t2= new Thread(gen2);
		t2.start();
		Generator gen3= new Generator('s', rm, "speed");
		Thread t3= new Thread(gen3);
		t3.start();
		Generator gen4= new Generator('e', rm, "time");
		Thread t4= new Thread(gen4);
		t4.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		
		System.exit(0);
	}	
}
