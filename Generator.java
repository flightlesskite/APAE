
import java.util.ArrayList;
import java.util.Random;

// new class for Spec2
// allows for separate traffic generators
public class Generator implements Runnable {
	
	private char direction;
	private RoadManager rm;
	private int speed;
	
	// time delay for next vehicle to be generated
	private int time;
	
	// traffic condition for generator
	// generates traffic with different distribution of speeds
	// or different distribution of times
	private String trafficCondition;
	
	private char genID;
	private ArrayList<Long> runTimeList;
	

	/**
	 * @param direction
	 * @param rm
	 * @param trafficCondition- String denotes specific condition applied to generator
	 * @throws InterruptedException
	 */
	Generator(char direction, RoadManager rm, String trafficCondition) throws InterruptedException{
		
		this.direction=direction;
		this.rm=rm;
		this.trafficCondition=trafficCondition;
		this.genID=direction;
		
		// default generation time is set to 1000
		this.time=1000;
		
		runTimeList= new ArrayList<Long>();
	}

	/**
	 * @param direction
	 * @param rm
	 * @return vehicle object created
	 * creates/generates a new Car object
	 * can be extended to create other vehicle objects
	 */
	private Vehicle generateVehicle(char direction, RoadManager rm)	{
		
		Vehicle car=new Car(direction, rm);
		return car;
	}
	
	public void addRunTime(long time)	{
		runTimeList.add(time);
	}
	
	
	/**
	 * @param vehicle- set the lanes based on direction the vehicle can drive in
	 * applies speed traffic condition- more likely to generate slower cars
	 * applies time traffic condition- more likely to generate cars at a slower pace
	 */
	private void createTraffic (Vehicle vehicle) {
		
		vehicle.setGen(this);
		
		// sets 'n' cars to be generated in the first 10 columns
		if (direction=='n') {
			vehicle.setLane(new Random().nextInt(9+1));
		}
		// sets 's' cars to be generated in the last 10 columns
		else if (direction=='s') {
			vehicle.setLane(new Random().nextInt(9+1)+10);
		}
		// sets 'w' cars to be generated in the first 5 rows
		else if (direction=='w')	{
			vehicle.setLane(new Random().nextInt(4+1));
		}
		// sets 'e' cars to be generated in the last 5 rows
		else if (direction=='e')	{
			vehicle.setLane(new Random().nextInt(4+1)+5);
		}
		
		// speed is set between 500-2000
		if (this.trafficCondition=="speed")	{
			speed = new Random().nextInt((2000)+500);
			vehicle.setSpeed(speed);
		}
		
		// time is set between 500-2000
		if (this.trafficCondition=="time")	{
			time=new Random().nextInt((2000)+500);
		}
		
	}
	
	
	
	public void run() {
		// keep generating vehicles until grid drawing has terminated
		while(rm.isFinished()==false) {
			try {
				// time delay for generating the next car is set
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				// vehicle is generated and applies any relevant traffic conditions
				Vehicle vehicle=generateVehicle(direction, rm);
				createTraffic(vehicle);
				Thread t= new Thread(vehicle);
				t.start();
		}
		Statistics stats= new Statistics (runTimeList, genID);
		stats.buildReport();
		
	}	

	
}
