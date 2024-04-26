import java.util.*;

public class Main{

  public static void main(String[] args){
    int steps = 50;
    int gain = 5;
    int loss = 4;
    int startingHeight = 3;
    Scanner in = new Scanner(System.in);

    // System.out.println("How many steps do you want to each walk to take? Please enter a positive integer. Try 50 steps.");
    // steps = in.nextInt();
    // in.nextLine();
    // System.out.println();
    // System.out.println("How many points do you want each gain to be? Please enter a positive integer. Try a gain of 5.");
    // gain = in.nextInt();
    // in.nextLine();
    // System.out.println();
    // System.out.println("How many points do you want each loss to be? Please enter a positive integer. Try a loss of 4.");
    // loss = in.nextInt();
    // in.nextLine();
    // System.out.println();
    // System.out.println("How many points do you want each walk to start at? Please enter a positive integer. Try a starting height of 3.");
    // startingHeight = in.nextInt();
    // in.nextLine();
    // System.out.println();
    
    simWalk(steps, gain, loss, startingHeight);
  }

  public static void simWalk(int steps, int gain, int loss, int startingHeight){
    int[] walks = manyWalks(steps, gain, loss, startingHeight);
    
    double n = 0;
    for(int walk : walks){
      n += walk;
    }
    n /= walks.length;
    n -= startingHeight;
    System.out.println("Average Gain: " + n);
    n = numBustWalks(walks);
    System.out.println(n + " out of " + walks.length + " walks were a bust");
  }

  public static int[] manyWalks(int steps, int gain, int loss, int startingHeight){
    int[] walks = new int[250];
    for(int walk = 0; walk < 250; walk++){
      walks[walk] = walk(steps, gain, loss, startingHeight);
    }
    
    return walks;
  }

  public static int walk(int flips, int gain, int loss, int start){
    int sum = start;
    int t = 0;
    while(t < flips){
      t++;
      sum += (Math.random() < 0.5) ? gain : -loss;
      if(sum <= 0){
        return 0;
      }
    }
    
    return sum;
  }
  
  
  public static int numBustWalks(int[] walks){
    int num = 0;
    for(int walk : walks){
      if(walk <= 0){
        num++;
      }
    }
    
    return num;
  }
  
}