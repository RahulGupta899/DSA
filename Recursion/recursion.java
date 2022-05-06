import java.util.*;
public class recursion{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        int pow = powerLogarithmic(a,b);
        System.out.println(pow);
    }

    public static int power(int a, int b){
        if(b == 0){
            return 1;
        }
        int pnm1 = power(a,b-1);
        return a*pnm1;
    }

    public static int powerLogarithmic(int a, int b){
        if(b==0) return 1;
        int pnb2 = power(a,b/2) * power(a,b/2);
        return b%2==0? pnb2: pnb2*a;
    }
}