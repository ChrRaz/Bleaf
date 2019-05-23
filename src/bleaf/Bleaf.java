/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bleaf;
import java.util.ArrayList;
/**
 *
 * @author patrickbruus
 */
public class Bleaf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Literal p = new Literal("p");
        Literal q = new Literal("q");
        Literal r = new Literal("r");

        Clause first = new Clause();
        first.add(p); first.add(q); first.addNeg(r);
        System.out.println(first);

        Clause second = new Clause();
        second.addNeg(p); second.addNeg(r);
        System.out.println(second);

        Clause third = new Clause();
        third.addNeg(q);
        System.out.println(third);


        ClauseSet KB = new ClauseSet(first,second,third);

        Clause phi1 = new Clause();
        phi1.add(r);

        Clause phi2 = new Clause();
        phi2.addNeg(p);

        Clause phi3 = new Clause();
        phi3.addNeg(r);

        System.out.println(KB);

        System.out.println(KB.resolution(new ClauseSet(phi1)));

        System.out.println(KB.resolution(new ClauseSet(phi2)));

        System.out.println(KB.resolution(new ClauseSet(phi3)));
    }

        
    }

   
    
    
    


