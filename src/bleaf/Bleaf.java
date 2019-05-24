/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bleaf;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
        Literal s = new Literal("s");

        Clause first = new Clause();
        first.add(p); first.add(q); first.addNeg(r);

        Clause second = new Clause();
        second.addNeg(p); second.addNeg(r);

        Clause third = new Clause();
        third.addNeg(q);

        Clause fourth = new Clause();
        fourth.addNeg(p); fourth.add(r); fourth.add(s);

        Clause fifth = new Clause();
        fifth.add(s); fifth.add(q); fifth.add(r);


        ClauseSet KB = new ClauseSet(first,second,third,fourth,fifth);

        Clause phi1 = new Clause();
        phi1.add(r);

        Clause phi2 = new Clause();
        phi2.addNeg(p);

        Clause phi3 = new Clause();
        phi3.addNeg(r);

        System.out.println("KB");
        System.out.println(KB);

        System.out.println("Resolution");
        System.out.println(KB.resolution(new ClauseSet(phi1)));
        //System.out.println(KB.resolution(new ClauseSet(phi2)));
        //System.out.println(KB.resolution(new ClauseSet(phi3)));

        System.out.println("Remainders");
        System.out.println(KB.remainders(new ClauseSet(phi1)));
        System.out.println(KB.remainders(new ClauseSet(phi1)).stream().map(ClauseSet::toString).collect(Collectors.joining("\n")));
        //System.out.println(KB.remainders(new ClauseSet(phi2)));
        //System.out.println(KB.remainders(new ClauseSet(phi3)));
    }

        
    }

   
    
    
    


