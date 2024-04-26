
public class CoinFlip{

  public static int flips(int start, int gain, int loss, int flips){
    int sum = start;
    int t = 0;
      while(t < flips){
        t++;
        sum += (Math.random() < 0.5) ? gain : -loss;
        if(sum <= 0){
          return 0;
        }
      }
    }

    return sum;
  }

  public void flip(){
    
    
  }

  public void flips(int n){
    
  }

  
}
