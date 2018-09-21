import java.util.ArrayList;

public class Statistics {
	
	private ArrayList<Long> runTimeList;
	private int size;
	private char vehicleName;
	
	public Statistics (ArrayList<Long> runTimeList, char vehicleName) {
		
		this.vehicleName=vehicleName;
		this.runTimeList=runTimeList;
		this.size=runTimeList.size();

	}
	
	private long findMean()	{		
		
		long total=0;
		if (runTimeList == null || runTimeList.isEmpty()) {
			return 0;
		}
		
		for (int i=0; i<size; i++) {
			total += runTimeList.get(i);
		}
		
		long average=total / runTimeList.size();
		return average;
	}


	private long findMin() {
		
		
		long min=runTimeList.get(0);
		
		for (int i = 0; i < size; i ++) {
			if (runTimeList.get(i)<min)	{
				min = runTimeList.get(i);
			}				
		}
		return min;
	}
	
	private long findMax() {
		
		long max=runTimeList.get(0);
		
		for (int i = 0; i < size; i ++) {
			if (runTimeList.get(i)>max)	{
				max = runTimeList.get(i);
			}				
		}
		return max;
	}
	
	public void buildReport() {
		StringBuilder report= new StringBuilder();
		report.append("\r\n-----------------------------------------");
		report.append("\r\nGenerator for Vehicle '"+vehicleName+"' Report");
		report.append("\r\n-----------------------------------------");
		report.append("\r\nMean elpased time (in milliseconds): " +findMean());
		report.append("\r\nMinimum elpased time (in milliseconds):" +findMin());
		report.append("\r\nMaximum elpased time (in milliseconds):" +findMax());
		System.out.println(report);
	}
	
}
