
public class RoadManager implements Runnable{

	private final int ROWS;
	// represents roads on the x-axis
	private final int COLUMNS;
	// represents roads on the y-axis
	private Space[][] grid;
	// models a NxM grid space
	// each grid space models an 'intersection'
	private boolean finished=false;
	
	/**
	 * @param rows- sets the number of East-West lanes
	 * @param columns- sets the number of North-South lanes
	 * constructor for Road Manager 
	 * sets the number of spaces used for modelling the grid
	 */
	public RoadManager (int rows, int columns) {
		this.ROWS=rows;
		this.COLUMNS=columns;
		this.grid= new Space[rows][columns];
		
		for (int i=0; i<rows; i++) {
			for (int j=0; j<columns; j++) {
				grid[i][j]=new Space();
			}
		}
	}
	
	/**
	 * @param vehicle- identify lane in which the vehicle drives in
	 * @return int representing the length of the road
	 * length for 'n' and 's' vehicles will equal the number of rows-1
	 * length for 'w' and 'e' vehicles will equal the number of columns-1
	 */
	public int getRoad(Vehicle vehicle) {
		if (vehicle.getDirection()=='n'||vehicle.getDirection()=='s')	{
			return ROWS-1;
		}
		if (vehicle.getDirection()=='w'||vehicle.getDirection()=='e')	{
			return COLUMNS-1;
		}
		else
			return 0;
	}
	
	/**
	 * @return int representing number of rows in grid
	 */
	public int getRows() {
		return ROWS;
	}
	
	/**
	 * @return int columns- representing number of columns in grid
	 */
	public int getColumns() {
		return COLUMNS;
	}
	
	/**
	 * @param x- the x co-ordinate of the intersection
	 * @param y- the y co-ordinate of the intersection
	 * @param vehicle
	 * sets vehicle into a space when it enters intersection
	 */
	public void setPosition(int x, int y, Vehicle vehicle) {
		grid[x][y].setVehicle(vehicle);
	}
	
	/**
	 * @param x- the x co-ordinate of the intersection
	 * @param y- the y co-ordinate of the intersection
	 * sets an empty space when vehicle leaves intersection
	 */
	public void leaveSpace(int x, int y) {
		grid[x][y].setEmpty();
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	/**
	 * draws grid and its contents
	 */
	private void printGrid() {
		System.out.println();
		System.out.println("-----------------------------------------");
	    for (int i = 0; i < ROWS; i++) {
	        for (int j = 0; j < COLUMNS; j++) {
	            System.out.print("|"+grid[i][j].getSpace());
	        }
	        System.out.println("|");
	    }
	    System.out.println("-----------------------------------------");
	}

	public void run() {
		try {
			// grid is drawn 2000 times
			for (int j=0; j<2000; j++)	{
				printGrid();
				Thread.sleep(20);
			}
			// sets boolean flag to 'true' once simulation has ended
			// allows program to terminate once grid has been drawn 2000 times
			finished=true;
		}	
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
		
}
	

