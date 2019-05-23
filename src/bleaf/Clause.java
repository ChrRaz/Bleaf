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
public class Clause {

    private ArrayList<Literal> Litterals = new ArrayList<Literal>();
    private ArrayList<Literal> LitteralsNeg = new ArrayList<Literal>();

    public Clause() {
    }

    public void Add(Literal litt) {
        Litterals.add(litt);
    }

    public void AddNeg(Literal litt) {
        LitteralsNeg.add(litt);
    }

    public void Print() {
        System.out.print("{");
        for (int i = 0; i < Litterals.size(); i++) {
            if (i == Litterals.size() - 1) {
                Litterals.get(i).Print();
                if(LitteralsNeg.size()>0){
                    System.out.print(",");
                }
            } else {
                Litterals.get(i).Print();
                System.out.print(",");
            }

        }
        for (int i = 0; i < LitteralsNeg.size(); i++) {
            System.out.print("-");
            if (i == LitteralsNeg.size() - 1) {
                LitteralsNeg.get(i).Print();
            } else {
                LitteralsNeg.get(i).Print();
                System.out.print(",");
            }
        }
        System.out.print("}");
    }

}
