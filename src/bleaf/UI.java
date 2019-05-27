/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bleaf;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author patrickbruus
 */
public class UI {

    public static void userInput() {
        ClauseSet KB = ClauseSetting();
        System.out.println("Now enter the clauses for not Phi you want");
        ClauseSet negPhi = ClauseSetting();
        System.out.println("Now enter the clauses for Phi you want");
        ClauseSet Phi = ClauseSetting();
        
        doTheThings(KB,negPhi,Phi);

    }

    private static ClauseSet ClauseSetting() {
        boolean isClausesSet = false;
        ClauseSet clauseSet = new ClauseSet();
        while (isClausesSet == false) {
            Clause clause = new Clause();

            Scanner in = new Scanner(System.in);
            System.out.println("Enter the literals you want in your clause");
            System.out.println("After each literal set a , to indicate new literal");
            System.out.println("To negate a literal use - before it");
            if (0 == (int)clauseSet.size()){
            } else {
                System.out.println("You know have "+clauseSet);
            }

            String litterals = in.next();
            String[] litteralsArray = litterals.split(",");
            for (String litteralElement : litteralsArray) {
                String newlitteralElement = litteralElement.replace("-", "");

                Literal lit = new Literal(newlitteralElement);

                if (litteralElement.contains("-")) {
                    clause.addNeg(lit);

                } else {
                    clause.add(lit);
                }

            }
            clauseSet.add(clause);
            boolean decicionMade = false;
            while (decicionMade == false) {
                String name = "";
                System.out.println("Do you want to add another clause type y for yes or n for no");
                name = in.next();
                String namelowcase = name.toLowerCase();
                if (name.equals("y")) {
                    decicionMade = true;
                } else if (name.equals("n")) {
                    decicionMade = true;
                    isClausesSet = true;
                } else {
                    System.out.println("Error try again");

                }

            }

        }
        return clauseSet;
    }
    
    
    public static void Example(){
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

        Clause negPhi1 = new Clause();
        negPhi1.add(r);
        Clause phi1 = new Clause();
        phi1.addNeg(r);

        doTheThings(KB,new ClauseSet(negPhi1),new ClauseSet(phi1));
        

        //System.out.println(KB.remainders(new ClauseSet(negPhi1)).stream().map(ClauseSet::toString).collect(Collectors.joining("\n")));
        //System.out.println(KB.remainders(new ClauseSet(phi2)));
        //System.out.println(KB.remainders(new ClauseSet(phi3)));
    }
    
    public static void doTheThings(ClauseSet KB, ClauseSet negPhi, ClauseSet phi){
    System.out.println("KB");
        System.out.println(KB);

        System.out.println("Resolution");
        System.out.println(KB.resolution(new ClauseSet(negPhi)));

        System.out.println("Remainders");
        Set<ClauseSet> remset = KB.remainders(new ClauseSet(negPhi));
        System.out.println(remset);
        for(ClauseSet rem : remset){
            System.out.println(rem.resolution(new ClauseSet(phi)));
            System.out.println(rem.size());
            System.out.println(rem.relSize());
        }
        System.out.println(ClauseSet.gamma(remset));
}

}
