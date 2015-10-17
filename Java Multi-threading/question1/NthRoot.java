import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author KB
 */

public class NthRoot {

    static BigDecimal refine(BigDecimal num,BigDecimal prev, int n){    //refine function from the first inlab
        BigDecimal next;
        BigDecimal ndecimal = BigDecimal.valueOf(n);
        
        next = prev.pow(n-1);
        
        next = num.divide(next,50,BigDecimal.ROUND_HALF_UP);
        
        next = next.add(prev.multiply(ndecimal.subtract(BigDecimal.ONE)));
        next = next.divide(ndecimal,50,BigDecimal.ROUND_HALF_UP);   //refined value

        return next;
    }
    static BigDecimal NthRoot(BigInteger num,int n){
        BigDecimal next;
        BigDecimal numdecimal = new BigDecimal(num);    //converting the biginteger to bigdecimal
        BigDecimal epsilon = BigDecimal.valueOf(0.01);  //setting precision
        BigDecimal prev = numdecimal.divide(BigDecimal.valueOf(2),3,BigDecimal.ROUND_HALF_UP);

        next = refine(numdecimal,prev,n);     
       
        
        

        while ((next.subtract(prev).abs()).compareTo(epsilon)==1){  //iterate till the refined value is close to crude value
            prev = next;
            next = refine(numdecimal,prev,n);
            
        }
        next = next.setScale(6, BigDecimal.ROUND_HALF_UP);
        return next;
    }

    public static void main(String[] args){

        try {
        BigInteger INPUT=new BigInteger(args[0]);
        int n = Integer.parseInt(args[1]);
        System.out.println(n+"th root of "+args[0]+" is "+NthRoot(INPUT,n));
        }
        catch(Exception e){
            System.out.println("Give Valid Input!");
            System.out.println("Suggestion: Give exactly 2 arguements, the first being a BigInteger (and not a BigDecimal) and the second one being Int");
        }
    }
}