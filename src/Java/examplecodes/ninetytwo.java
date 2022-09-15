package Java.examplecodes;

/**
 * 42
 */
public class ninetytwo {

    public static int res = 0;

    public static void main(String[] args) {
        for (int i = 1; i < 10000000; i++) {
            int n = i;
            while (n!=89 && n!=1) {
                n=sum(n);
            }
            
            if(n==89){
                res++;
            }
           
        }
        System.out.println(res);
           // System.out.println(i);

        }
        public static int sum(int n){
            int sum = 0;
            while(n>0){
                sum = sum + (n%10)*(n%10);
                n = n/10;
            }
            return sum;
        }
}
