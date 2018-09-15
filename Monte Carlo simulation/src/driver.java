import java.util.Scanner;
import java.util.Random;
import java.lang.Math;
import java.lang.Thread;



public class driver {
	
	private static double pie;
	
	
	
	public static void main(String[] args) throws InterruptedException {
		
		Scanner s = new Scanner(System.in);
		
		
		System.out.println("This assignment deals with writing a Monte Carlo simulation. We will use multiple threads to do throw darts on a cardboard several times in parallel, then combine the results to determine a final value for Pie ");
		System.out.println("Enter the number of threads that you want to simulate. This should be between 1 and 20 (including the bounds)");
		int nThreads = s.nextInt();
		System.out.println("Enter the number of arrows you want to throw in each thread. This should be any positive number.");
		int nArrows = s.nextInt();
		
		Thread myThreads[] = new Thread[nThreads];
		Random r = new Random();
		double[] pieValues= new double[nThreads];
		
		
		for(int i =0;i<nThreads;i++){
			
			myThreads[i]= new Thread(){
				int countDartsHitting =0;
				int countDartsOutsideCircle = 0;	
				public void run(){
					for(int j=0;j<nArrows;j++){
						
						if(Math.hypot(r.nextDouble()*2-1,r.nextDouble()*2-1)<=1){
							countDartsHitting++;
						}
						
					}
					
					pie=(countDartsHitting/(double)nArrows)*4;
					
				}
			};
			
			
		}
	for(int i=0;i<nThreads;i++){
		
		myThreads[i].start();
		myThreads[i].join();
		pieValues[i]=pie;
		pie=0.0;
		
		
	}
	
	double finalPie=0.0;
	
	for(int i =0;i<pieValues.length;i++){
		//System.out.println(pieValues[i]);
		finalPie+=pieValues[i];
	}
	System.out.println("The value of pie is: " + finalPie/(double)pieValues.length);
}
	
}

