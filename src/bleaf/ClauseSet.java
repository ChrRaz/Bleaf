/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bleaf;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author patrickbruus
 */
public class ClauseSet {
    private Set<Clause> clauseList = new HashSet<Clause>();;
    
    public ClauseSet() {
    }

    public ClauseSet(ClauseSet clauseSet) {
        // Add each clause from clauseSet to this clauseSet
        clauseSet.clauseList.forEach(this::add);
    }

    public ClauseSet(Clause... clauses){
        for(Clause clause : clauses)
            this.add(clause);
    }
    public int units() {
        // The number of unit clauses in this clauseSet
        return (int) this.clauseList.stream().filter(cl -> cl.size() == 1).count();
    }
    

    public ClauseSet add(Clause sent){
        this.clauseList.add(sent);
        return this;
    }

    public ClauseSet allComplementary(Clause c) {
        ClauseSet comp = new ClauseSet();
        for (Clause myc : this.clauseList){
            if(myc.complementary(c) != null){
                comp.add(myc);
            }
        }
        return comp;
    }

    public boolean resolution(ClauseSet phi) {
        SortedSet<ClausePair> todo = new TreeSet<>();
        ClauseSet setOfSupport = new ClauseSet(phi);

        for (Clause cl : setOfSupport.clauseList) {
            for (Clause cl2 : this.allComplementary(cl).clauseList)
                todo.add(new ClausePair(cl, cl2));
            for (Clause cl2 : setOfSupport.allComplementary(cl).clauseList)
                todo.add(new ClausePair(cl, cl2));
        }

        while (!todo.isEmpty()) {
            ClausePair pair = todo.first();
            todo.remove(pair);
            System.out.println(pair);
            Clause newClause = pair.resolve();

            // Empty clause. Phi is inconsistent with the belief base.
            if (newClause.size() == 0)
                return false;

            setOfSupport.add(newClause);
            for (Clause clause : this.allComplementary(newClause).clauseList)
                todo.add(new ClausePair(clause, newClause));
            for (Clause clause : setOfSupport.allComplementary(newClause).clauseList)
                todo.add(new ClausePair(clause, newClause));
        }

        // No more pair to resolve. Phi is consistent with the belief base.
        return true;
    }

    public Set<Clause> findContradiction(ClauseSet phi) {
        SortedSet<ClausePair> todo = new TreeSet<>();
        ClauseSet setOfSupport = new ClauseSet(phi);
        Dictionary<Clause, ClausePair> parent = new Hashtable<>();

        for (Clause cl : setOfSupport.clauseList) {
            for (Clause cl2 : this.allComplementary(cl).clauseList)
                todo.add(new ClausePair(cl, cl2));
            for (Clause cl2 : setOfSupport.allComplementary(cl).clauseList)
                todo.add(new ClausePair(cl, cl2));
        }

        while (!todo.isEmpty()) {
            ClausePair pair = todo.first();
            todo.remove(pair);

            System.out.println(pair);
            Clause newClause = pair.resolve();
            parent.put(newClause, pair);

            // Empty clause. Phi is inconsistent with the belief base.
            if (newClause.size() == 0) {
                // TODO: Return all leaves from newClause as set
                return null;
            }

            setOfSupport.add(newClause);
            for (Clause clause : this.allComplementary(newClause).clauseList)
                todo.add(new ClausePair(clause, newClause));
            for (Clause clause : setOfSupport.allComplementary(newClause).clauseList)
                todo.add(new ClausePair(clause, newClause));
        }

        // No more pair to resolve. Phi is consistent with the belief base.
        return null;
    }

    @Override
    public String toString() {
        return "{" + this.clauseList.stream().map(Clause::toString).collect(Collectors.joining(", ")) + "}";
    }

    /*
        public void Print(){
            System.out.print("{");
            for (int i = 0; i < clauseList.size(); i++) {
               if(i==clauseList.size()-1){
                   clauseList.get(i).Print();
               }
               else{
                   clauseList.get(i).Print();
                   System.out.print(",");
               }
        }
            System.out.print("}");

        }*/
    public void Resolution(Clause sent){
      //  Knowledgespace KBnew = new Knowledgespace();
//        for(s in this.Sentences){
//            KBnew.Add(s);
//        }
//        KBnew.Add(sent.Neg);
//        KBnew.Checkthis; 
    }

}
