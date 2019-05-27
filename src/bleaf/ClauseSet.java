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



    public boolean add(Clause sent){
        return this.clauseList.add(sent);
    }

    public void addAll(ClauseSet cs){
        cs.clauseList.forEach(this::add);
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

    public long size(){
        int l = 0;
        for(Clause c : clauseList){
            l += c.size();
        }
        return l;
    }

    public double relSize(){
       return (double)this.size()/this.clauseList.size();
    }

    public boolean resolution(ClauseSet phi) {
        SortedSet<ClausePair> todo = new TreeSet<>(Comparator.reverseOrder());
        ClauseSet setOfSupport = new ClauseSet(phi);

        for (Clause cl : setOfSupport.clauseList) {
            for (Clause cl2 : this.allComplementary(cl).clauseList)
                todo.add(new ClausePair(cl, cl2));
            for (Clause cl2 : setOfSupport.allComplementary(cl).clauseList)
                todo.add(new ClausePair(cl, cl2));
        }

        //System.out.println(this);
        while (!todo.isEmpty()) {
            //System.out.println(setOfSupport);
            //System.out.println(todo);
            ClausePair pair = todo.first();
            todo.remove(pair);
            Clause newClause = pair.resolve();

            // Empty clause. Phi is inconsistent with the belief base.
            if (newClause.size() == 0)
                return false;

            if (setOfSupport.add(newClause)) {
                for (Clause clause : this.allComplementary(newClause).clauseList)
                    todo.add(new ClausePair(clause, newClause));
                for (Clause clause : setOfSupport.allComplementary(newClause).clauseList)
                    todo.add(new ClausePair(clause, newClause));
            }
        }

        // No more pair to resolve. Phi is consistent with the belief base.
        return true;
    }

    public ClauseSet findContradiction(ClauseSet phi) {
        SortedSet<ClausePair> todo = new TreeSet<>(Comparator.reverseOrder());
        ClauseSet setOfSupport = new ClauseSet(phi);
        Dictionary<Clause, ClausePair> parents = new Hashtable<>();

        for (Clause cl : setOfSupport.clauseList) {
            for (Clause cl2 : this.allComplementary(cl).clauseList)
                todo.add(new ClausePair(cl, cl2));
            for (Clause cl2 : setOfSupport.allComplementary(cl).clauseList)
                todo.add(new ClausePair(cl, cl2));
        }

        //System.out.println(this);
        while (!todo.isEmpty()) {
            //System.out.println(setOfSupport);
            //System.out.println(todo);
            ClausePair pair = todo.first();
            todo.remove(pair);

            //System.out.println(pair);
            Clause newClause = pair.resolve();
            parents.put(newClause, pair);

            // Empty clause. Phi is inconsistent with the belief base.
            if (newClause.size() == 0) {
                //System.out.println("Parents: " + parents);
                ClauseSet badLeaves = returnLeaves(newClause, parents);

                //System.out.println("Internal leaves: " + badLeaves);
                badLeaves.clauseList.retainAll(this.clauseList);
                return badLeaves;
            }

            if (setOfSupport.add(newClause)) {
                for (Clause clause : this.allComplementary(newClause).clauseList)
                    todo.add(new ClausePair(clause, newClause));
                for (Clause clause : setOfSupport.allComplementary(newClause).clauseList)
                    todo.add(new ClausePair(clause, newClause));
            }
        }

        // No more pair to resolve. Phi is consistent with the belief base.
        return null;
    }

    public Set<ClauseSet> remainders(ClauseSet phi){
        //System.out.println("> " + this + ", " + phi + " <");
        Set<ClauseSet> remSet = new HashSet<>();
        ClauseSet badLeaves = this.findContradiction(phi);
        //System.out.println("Leaves: " + badLeaves);
        if(badLeaves!=null) {
            for (Clause clause : badLeaves.clauseList) {
                ClauseSet cs = new ClauseSet(this);
                cs.clauseList.remove(clause);
                remSet.addAll(cs.remainders(phi));
            }
        }else{
            remSet.add(this);
        }
        return remSet;
    }

    public ClauseSet returnLeaves(Clause newClause, Dictionary<Clause,ClausePair> parents){
        ClauseSet todo = new ClauseSet(newClause);
        ClauseSet cs = new ClauseSet();
        while(!todo.clauseList.isEmpty()) {
            Clause next = todo.clauseList.iterator().next();
            todo.clauseList.remove(next);
            ClausePair pair = parents.get(next);
            //System.out.println("Parent Pair: " + next + " -> " + pair);
            if (pair!=null) {
                todo.add(pair.getFirst());
                todo.add(pair.getSecond());
            } else {
                cs.add(next);
            }
        }
        return cs;
    }

    @Override
    public String toString() {
        return "{" + this.clauseList.stream().map(Clause::toString).collect(Collectors.joining(", ")) + "}";
    }

    public static Set<ClauseSet> gamma(Set<ClauseSet> remainders){
        long max = remainders.stream().mapToLong(ClauseSet::size).max().orElse(-1);

        return remainders.stream().filter(c -> c.size()==max).collect(Collectors.toSet());
    }
}
