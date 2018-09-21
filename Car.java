
import java.util.Random;

public class Car extends Vehicle {

	/**
	 * @param direction- sets a car's model based on direction
	 * @param rm
	 * speed of cars are randomly set between 0-1000
	 * lane in which cars will be generated from is randomly set 
	 */
	public Car(char direction, RoadManager rm) {
		super(direction, rm);
		
		Random rand = new Random(); 
		speed = rand.nextInt(1000);
		
		if (direction== 'n')	{
			model='o';
			staticLane=rand.nextInt(rm.getColumns());
		
		}
		else if (direction== 'w')	{
			model='-';
			staticLane=rand.nextInt(rm.getRows());
		}
		
		else if (direction== 's')	{
			model='x';
			staticLane=rand.nextInt(rm.getColumns());
		}
		
		else if (direction== 'e')	{
			model='+';
			staticLane=rand.nextInt(rm.getRows());
		}
		
	}
	
	

}
