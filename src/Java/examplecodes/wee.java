package Java.examplecodes;

/**
 * 42
 */
public class wee {
    public static double n = 10101010;
    public static void main(String[] args) {
        while (true) {
        double x = n*n;
            innerloop:
            while (true){
                for (int i = 1; i<10; i++) {
                    
                    double m = n%10;
                    x = x/100;
                    if(m!=10-i){
                        break innerloop;
                    }
                    
                    
                }
            System.out.println(x);
            break;
            }
            n+=1;
            System.out.print(n);
            //System.out.println(x);
            
            
        }
        
                
    }
            
        
        public static int sum(int n){
            int sum = 0;
            while(n>0){
                n = n%10;
                n = n/100;
            }
            return sum;
        }
}
