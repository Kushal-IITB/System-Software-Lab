import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author KB
 */

public class SolveBigEquation {

    static BigDecimal refine(BigDecimal num,BigDecimal prev, int n){    //from the Nthroot.java
        BigDecimal next;
        BigDecimal ndecimal = BigDecimal.valueOf(n);
        
        next = prev.pow(n-1);
        
        next = num.divide(next,20,BigDecimal.ROUND_HALF_UP);
            
        next = next.add(prev.multiply(ndecimal.subtract(BigDecimal.ONE)));
        next = next.divide(ndecimal,20,BigDecimal.ROUND_HALF_UP);

        return next;
    }
    static BigDecimal NthRoot(BigDecimal num,int n){    //from the Nthroot.java
        BigDecimal next;
        BigDecimal epsilon = BigDecimal.valueOf(0.01);
        BigDecimal prev = num.divide(BigDecimal.valueOf(2),3,BigDecimal.ROUND_HALF_UP);

        next = refine(num,prev,n);   
        
        while (((next.subtract(prev)).abs()).compareTo(epsilon)==1){
            
            prev = next;
            next = refine(num,prev,n);
            
        }
        return next;
    }


    static BigDecimal fact(BigDecimal num){     //factorial function
        if(num.compareTo(BigDecimal.ONE)==0)
            return BigDecimal.ONE;
        return num.multiply(fact(num.subtract(BigDecimal.ONE)));
    }

    public static void main(String[] args){
    BigDecimal fiftyFact = fact(BigDecimal.valueOf(50));
    BigDecimal hundredFact = fact(BigDecimal.valueOf(100));
    //calculating term1 and term2 in the quadratic formula for the quadratic equation in A
    BigDecimal term1 = (NthRoot((fiftyFact.pow(2)).add(hundredFact.multiply(BigDecimal.valueOf(4))),2)).divide(BigDecimal.valueOf(2),20,BigDecimal.ROUND_HALF_UP);
    BigDecimal term2 = fiftyFact.divide(BigDecimal.valueOf(2),20,BigDecimal.ROUND_HALF_UP);
    //four possible solutions for A
    BigDecimal a1 = term1.add(term2);
    BigDecimal a2 = term1.subtract(term2);
    BigDecimal a3 = (BigDecimal.ZERO).subtract(a1);
    BigDecimal a4 = (BigDecimal.ZERO).subtract(a2);
    BigDecimal b1 = hundredFact.divide(a1,3,BigDecimal.ROUND_UP);
    BigDecimal b2 = hundredFact.divide(a2,3,BigDecimal.ROUND_UP);
    BigDecimal b3 = hundredFact.divide(a3,3,BigDecimal.ROUND_UP);
    BigDecimal b4 = hundredFact.divide(a4,3,BigDecimal.ROUND_UP);
    System.out.println("");
    a1 = a1.setScale(3, BigDecimal.ROUND_UP);
    a2 = a2.setScale(3, BigDecimal.ROUND_UP);
    a3 = a3.setScale(3, BigDecimal.ROUND_UP);
    a4 = a4.setScale(3, BigDecimal.ROUND_UP);
    System.out.println("A1 = "+a3);
    System.out.println("B1 = "+b3);
    System.out.println("");
    System.out.println("A2 = "+a4);
    System.out.println("B2 = "+b4);
    System.out.println("");
    System.out.println("A3 = "+a2);
    System.out.println("B3 = "+b2);
    System.out.println("");
    System.out.println("A4 = "+a1);
    System.out.println("B4 = "+b1);

    }
}