
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

public class Space {

	// each space can either be empty or contain a vehicle inside
	private char space;
	
	// boolean to determine whether space is occupied or not
	// initially set to false
	private boolean occupied=false;
	
	// lock to prevent two or more vehicles accessing same space
	// lock must be acquired to occupy or leave a space
	private ReentrantLock lock= new ReentrantLock();
	
	// condition used to wait for lock to be released
	private Condition intersect= lock.newCondition();
	
	
	/**
	 * all spaces are initially set to be empty
	 */
	public Space () {
		space=' ';
	}
	
	
	/**
	 * @return either the vehicle occupying the space or an empty space
	 */
	public char getSpace()	{
		return space;
	}
	
	/**
	 * @param vehicle- used to get model that represents a vehicle
	 * checks if lock is being held
	 * sets vehicle to an empty space
	 */
	public void setVehicle(Vehicle vehicle) {
		
		// acquires lock
		lock.lock();
		
		try {
			// if space is occupied
			// wait for space to be free
			while (occupied) {
				intersect.await();
			}
			// otherwise, vehicle is set to the available space
			space= vehicle.getModel();
			// space is now occupied
			occupied=true;
			
		}
		catch(InterruptedException e){
			e.printStackTrace();
        }
		finally {
			// frees lock
			lock.unlock();
		}
	}
	
	/**
	 * checks if lock is being held
	 * sets space to be empty (once a vehicle moves to next space)
	 */
	public void setEmpty()	{
		
		// acquires lock
		lock.lock();
		
		try {
			// sets space to be empty
			space= ' ';
			// space is now not occupied
			// wakes up threads and notifies lock is available
			occupied=false;
			intersect.signalAll();
			
		}
		finally {
			// frees lock
			lock.unlock();
		}
	}

	
}
