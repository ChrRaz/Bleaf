/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bleaf;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author patrickbruus
 */
public class Clause {

    private Set<Literal> posList = new HashSet<Literal>();
    private Set<Literal> negList = new HashSet<Literal>();


    public Clause() {
    }

    public void add(Literal litt) {
        posList.add(litt);
    }

    public void addNeg(Literal litt) {
        negList.add(litt);
    }

    public boolean containsComplementary(Clause c) {
        for (Literal litt : c.posList) {
            if (this.negList.contains(litt)) {
                return true;
            }
        }
        for (Literal litt : c.negList) {
            if (this.posList.contains(litt)) {
                return true;
            }
        }
        return false;
    }
/*
    public void Print() {
        System.out.print("{");
        for (int i = 0; i < posList.size(); i++) {
            if (i == posList.size() - 1) {
                posList.get(i).Print();
                if(negList.size()>0){
                    System.out.print(",");
                }
            } else {
                posList.get(i).Print();
                System.out.print(",");
            }

        }
        for (int i = 0; i < negList.size(); i++) {
            System.out.print("-");
            if (i == negList.size() - 1) {
                negList.get(i).Print();
            } else {
                negList.get(i).Print();
                System.out.print(",");
            }
        }
        System.out.print("}");
    }
*/
}
