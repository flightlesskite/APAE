
import java.util.Random;

public abstract class Vehicle implements Runnable {
	protected char model;
	protected int speed;
	protected int road;
	protected char direction;
	protected final RoadManager rm;
	
	// staticLane- the lane in which the vehicles does not move in
	protected int staticLane;
	// movingLane- the lane in which the will move in
	protected int movingLane;
	
	protected Generator gen;

	
	/**
	 * constructor for Vehicle objects
	 * @param direction- direction in which the car travels
	 * @param rm- uses Road Manager to set vehicles in space
	 */
	public Vehicle (char direction, RoadManager rm) {
		
		// n= north to south, w= west to east, s= south to north, e= east to west
		this.direction=direction;
		this.rm=rm;
		
		// in the case of N-S vehicles,
		// road is modelled by number of spaces in the y-axis of grid
		if(direction=='n'||direction=='s')	{
			road=rm.getRoad(this);
		}
		// in the case of S-W vehicles,
		// road is modelled by number of spaces in the x-axis of grid
		else if (direction=='w'||direction=='e') {
			road=rm.getRoad(this);
		}
		
		// the lane in which a vehicle will move in
		// sets the initial position for vehicles of all directions
		if (direction=='s'|| direction=='e')	{
			movingLane=road;
		}
		else {
			movingLane=0;
		}
				
	}
	
	/**
	 * @return model- the model which represents a vehicle
	 */
	public char getModel() {
		return model;
	}
	
	/**
	 * @return direction- used by RoadManager to get road size
	 */
	public char getDirection() {
		return direction;
	}
	
	/**
	 * @param lane
	 * additional method for Spec2
	 * sets the lane in which the vehicle can be generated in
	 */
	public void setLane (int lane)	{
		this.staticLane=lane;
	}
	
	/**
	 * @param speed
	 * additional method for Spec2
	 * 
	 */
	public void setSpeed (int speed)	{
		this.speed=speed;
	}
	
	public void setGen (Generator gen) {
		this.gen=gen;
	}
	
	/**
	 * function for vehicles to move in the N-S lane
	 * vehicles move from top to bottom
	 * vehicle movement modelled by incrementing movingLane
	 */
	private void moveNS() {
		// setting initial position
		rm.setPosition(movingLane, staticLane, this);
		
		// loops through the entire length of the road
		for(int i= movingLane; i<road; i++)	{
			movingLane++;
			// speed of vehicle is modelled by putting thread to sleep
			try {
				Thread.sleep(speed);
			}	catch (InterruptedException e)	{
				e.printStackTrace();
			}
			// sets the next position
			rm.setPosition(movingLane, staticLane, this);
			// resets the previous position to an empty space
			rm.leaveSpace(movingLane-1, staticLane);
		}
		try {
			Thread.sleep(speed);
		}	catch (InterruptedException e)	{
			e.printStackTrace();
		}
		// car 'falls' off when the opposite edge is reached
		rm.leaveSpace(movingLane, staticLane);
	}
	
	
	/**
	 * function for vehicles to move in the E-W lane
	 * vehicles move from left to right
	 * vehicle movement modelled by incrementing movingLane
	 */
	private void moveWE()	{
		// setting initial position
		rm.setPosition(staticLane, movingLane, this);
		
		// loops through the entire length of the road
		for(int i= movingLane; i<road; i++)	{
			movingLane++;
			// speed of vehicle is modelled by putting thread to sleep
			try {
				Thread.sleep(speed);
			}	catch (InterruptedException e)	{
				e.printStackTrace();
			}
			// sets the next position
			rm.setPosition(staticLane, movingLane, this);
			// resets the previous position to an empty space
			rm.leaveSpace(staticLane, movingLane-1);
		}
		try {
			Thread.sleep(speed);
		}	catch (InterruptedException e)	{
			e.printStackTrace();
		}
		// car 'falls' off when the opposite edge is reached
		rm.leaveSpace(staticLane, movingLane);
	}
	
	/**
	 * function for vehicles to move in the N-S lane
	 * vehicles move from bottom to top
	 * vehicle movement modelled by decrementing movingLane
	 */
	private void moveSN() {
		// setting initial position
		rm.setPosition(movingLane, staticLane, this);
		
		// loops through the entire length of the road
		for(int i= road; i>0; i--)	{
			movingLane--;
			// speed of vehicle is modelled by putting thread to sleep
			try {
				Thread.sleep(speed);
			}	catch (InterruptedException e)	{
				e.printStackTrace();
			}
			// sets the next position
			rm.setPosition(movingLane, staticLane, this);
			// resets the previous position to an empty space
			rm.leaveSpace(movingLane+1, staticLane);
		}
		try {
			Thread.sleep(speed);
		}	catch (InterruptedException e)	{
			e.printStackTrace();
		}
		// car 'falls' off when the opposite edge is reached
		rm.leaveSpace(movingLane, staticLane);
	}
	
	/**
	 * function for vehicles to move in the E-W lane
	 * vehicles move from right to left
	 * vehicle movement modelled by decrementing movingLane
	 */
	private void moveEW()	{
		// setting initial position
		rm.setPosition(staticLane, movingLane, this);
		
		// loops through the entire length of the road
		for(int i= road; i>0; i--)	{
			movingLane--;
			// speed of vehicle is modelled by putting thread to sleep
			try {
				Thread.sleep(speed);
			}	catch (InterruptedException e)	{
				e.printStackTrace();
			}
			// sets the next position
			rm.setPosition(staticLane, movingLane, this);
			// resets the previous position to an empty space
			rm.leaveSpace(staticLane, movingLane+1);
		}
		try {
			Thread.sleep(speed);
		}	catch (InterruptedException e)	{
			e.printStackTrace();
		}
		// car 'falls' off when the opposite edge is reached
		rm.leaveSpace(staticLane, movingLane);
	}
	
	/**
	 * allows vehicles to exist and move in their own threads
	 * direction in which a vehicle moves depends on the assigned indicator
	 * n- moves from North to South, s- moves from South to North
	 * w- moves from West to East, e- moves from East to West 
	 */
	public void run()	{
		
		long lStartTime = System.currentTimeMillis(); 
		
		if (direction=='n')	{
			moveNS();
		}
		else if (direction== 's')	{
			moveSN();
		}
		else if (direction== 'w') {
			moveWE();
		}
		else if (direction=='e')	{
			moveEW();
		}
		
		long lEndTime = System.currentTimeMillis();
		long runTime = lEndTime - lStartTime;
		
		if (gen!= null)	{
			gen.addRunTime(runTime);
		}
	
	}
	
}
