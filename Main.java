import java.util.*;

public class Main{

	public static void main(String[] args){
		simWalk(100000, 500, 5, 4, 1, 1, 10);
		/*
      Number of random walks made. Increase this to increase confidence. : 100,000 walks
      How many steps each random walk takes. : 500 steps
      How much each step will increase if going up. : 5 point(s)
      How much each step will decrease if going down. Should be positive. : 4 point(s)
      The weight controlling how likely a step is to go up. Increase to make up steps more likely. : 1 stake(s)
      The weight controlling how likely a step is to go down. Increase to make down steps more likely. : 1 stake(s)
      How high each random walk starts. : 10 point(s)
    */
	}

  public static void simWalk(int numwalks, int steps, double upHeight, double downHeight,
	                           double upWeight, double downWeight, double startingHeight){
    double[][] walks =
      manyWalks(numwalks, steps, upHeight, downHeight, upWeight, downWeight, startingHeight);
		int busts = numBustWalks(walks);
		System.out.println(busts + " out of " + walks.length + " walks were a bust");
		walks = floor(walks); // makes sure each walk cannot continue if their points are <= 0
		double avgROI = 0;
		for(double[] walk : walks){
		  avgROI += walk[walk.length - 1];
		}
		avgROI /= numwalks;
		avgROI = (avgROI - startingHeight) / startingHeight;
		System.out.println("Average ROI: " + avgROI);
	}

  public static double[][] manyWalks(int numWalks, int steps, double upHeight, double downHeight,
	                                 double upWeight, double downWeight, double startingHeight){
		double[][] walks = new double[numWalks][steps];
		for(int walk = 0; walk < numWalks; walk++){
			walks[walk] = walk(steps, upHeight, downHeight, upWeight, downWeight, startingHeight);
		}
		
		return walks;
	}
  
	public static double[] walk(int steps, double upHeight, double downHeight, double upWeight,
	                             double downWeight, double startingHeight){
		double[] walk = new double[steps + 1];
		walk[0] = startingHeight;
		for(int step = 1; step <= steps; step++){
      walk[step] = walk[step - 1] +
  			((Math.random() <= downWeight / (downWeight + upWeight)) ? -downHeight : upHeight);
		}

    printWalk(walk);
		return walk;
	}

	public static void printWalk(double[] walk){
  	for(double d : walk){
  		System.out.print(d + " ");
  	}
  	
  	System.out.println();
  	System.out.println();
	}

  public static int numBustWalks(double[][] walks){
	  int num = 0;
	  for(double[] walk : walks){
	    if(bust(walk)){
	      num++;
	    }
	  }
	  
	  return num;
	}
	
	public static boolean bust(double[] walk){
	  for(double d : walk){
	    if(d <= 0){
	      return true;
	    }
	  }
	  
	  return false;
	}
  
	public static double[][] floor(double[][] walks){
	  for(double[] walk : walks){
	    if(bust(walk)){
	      boolean busted = false;
	      for(int i = 0; i < walk.length; i++){
	        if(busted){
	          walk[i] = 0;
	        }
	        else if(walk[i] <= 0){
	          bust = true;
            walk[i] = 0;
	        }
	      }
	    }
	  }
	  
	  return walks;
	}
	
}
