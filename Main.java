import java.util.*;

public class Main{
  static Scanner in = new Scanner(System.in);

  public static void main(String[] args){
    int walks = 0;
    int steps = 0;
    int gain = 5;
    int loss = 4;
    int startingHeight = 20;
    
    System.out.println("How many walks do you want to simulate? Please enter a positive integer. Try 100 walks.");
    walks = in.nextInt();
    in.nextLine();
    System.out.println();
    System.out.println("How many steps do you want to each walk to take? Please enter a positive integer. Try 10000 steps.");
    steps = in.nextInt();
    in.nextLine();
    System.out.println();
    System.out.println("How many points do you want each gain to be? Please enter a positive integer. Try a gain of 5.");
    gain = in.nextInt();
    in.nextLine();
    System.out.println();
    System.out.println("How many points do you want each loss to be? Please enter a positive integer. Try a loss of 4.");
    loss = in.nextInt();
    in.nextLine();
    System.out.println();
    System.out.println("How many points do you want each walk to start at? Please enter a positive integer. Try a starting height of 20.");
    startingHeight = in.nextInt();
    in.nextLine();
    System.out.println();
    
    simWalk(walks, steps, gain, loss, startingHeight);
  }

  public static void simWalk(int numwalks, int steps, int gain, int loss, int startingHeight){
    int[][] walks = manyWalks(numwalks, steps, gain, loss, startingHeight);
    
    double n = 0;
    for(int[] walk : walks){
      n += walk[walk.length - 1];
    }
    n /= walks.length;
    System.out.println("Average Ending Height: " + n);
  }

  public static int[][] manyWalks(int numWalks, int steps, int gain, int loss, int startingHeight){
    int[][] walks = new int[numWalks][steps];
    for(int walk = 0; walk < numWalks; walk++){
      walks[walk] = walk(steps, gain, loss, startingHeight);
    }
    
    return walks;
  }
  
  public static int[] walk(int steps, int gain, int loss, int startingHeight){
    int[] walk = new int[steps + 1];
    walk[0] = startingHeight;
    for(int step = 1; step <= steps; step++){
      walk[step] = walk[step - 1] +
        ((Math.random() < 0.5) ? -loss : gain);
    }
    
    return walk;
  }

  public static int numBustWalks(int[][] walks){
    int num = 0;
    for(int[] walk : walks){
      if(bust(walk)){
        num++;
      }
    }
    
    return num;
  }
	
  public static boolean bust(int[] walk){
    for(int n : walk){
      if(n <= 0){
        return true;
      }
    }
    
    return false;
  }
  
  public static int[][] floor(int[][] walks){
    for(int[] walk : walks){
      if(bust(walk)){
        boolean busted = false;
        for(int i = 0; i < walk.length; i++){
          if(busted){
            walk[i] = 0;
          }
          else if(walk[i] <= 0){
            busted = true;
            walk[i] = 0;
          }
        }
      }
    }
    
    return walks;
  }
  
  
}

