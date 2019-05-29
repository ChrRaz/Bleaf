/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bleaf;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author patrickbruus
 */
public class ClauseSet {
    public Set<Clause> clauseList = new HashSet<Clause>();
    ;

    public ClauseSet() {
    }

    public ClauseSet(ClauseSet clauseSet) {
        // Add each clause from clauseSet to this clauseSet
        clauseSet.clauseList.forEach(this::add);
    }

    public ClauseSet(Clause... clauses) {
        for (Clause clause : clauses)
            this.add(clause);
    }


    public int units() {
        // The number of unit clauses in this clauseSet
        return (int) this.clauseList.stream().filter(cl -> cl.size() == 1).count();
    }


    public boolean add(Clause sent) {
        return this.clauseList.add(sent);
    }

    public void addAll(ClauseSet cs) {
        cs.clauseList.forEach(this::add);
    }

    //public void retainAll(ClauseSet cs) { cs.clauseList.forEach(this.clauseList::retainAll);}

    public ClauseSet allComplementary(Clause c) {
        ClauseSet comp = new ClauseSet();
        for (Clause myc : this.clauseList) {
            if (myc.complementary(c) != null) {
                comp.add(myc);
            }
        }
        return comp;
    }

    public long size() {
        int l = 0;
        for (Clause c : clauseList) {
            l += c.size();
        }
        return l;
    }

    public double relSize() {
        return (double) this.size() / this.clauseList.size();
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

    public Set<ClauseSet> remainders(ClauseSet phi) {
        //System.out.println("> " + this + ", " + phi + " <");
        Set<ClauseSet> remSet = new HashSet<>();
        ClauseSet badLeaves = this.findContradiction(phi);
        //System.out.println("Leaves: " + badLeaves);
        if (badLeaves != null) {
            for (Clause clause : badLeaves.clauseList) {
                ClauseSet cs = new ClauseSet(this);
                cs.clauseList.remove(clause);
                remSet.addAll(cs.remainders(phi));
            }
        } else {
            remSet.add(this);
        }
        return remSet;
    }
    
    public static ClauseSet contraction(ClauseSet KB, ClauseSet phi){
    
        Set<ClauseSet> remset = KB.remainders(new ClauseSet(phi));
        Set<ClauseSet> gammaremset = ClauseSet.gamma(remset);
        // Compute intersection
        ClauseSet intersection = new ClauseSet(KB);
        for (ClauseSet rem : gammaremset) {
            intersection.clauseList.retainAll(rem.clauseList);
        }
        return intersection;
    }

    public ClauseSet returnLeaves(Clause newClause, Dictionary<Clause, ClausePair> parents) {
        ClauseSet todo = new ClauseSet(newClause);
        ClauseSet cs = new ClauseSet();
        while (!todo.clauseList.isEmpty()) {
            Clause next = todo.clauseList.iterator().next();
            todo.clauseList.remove(next);
            ClausePair pair = parents.get(next);
            //System.out.println("Parent Pair: " + next + " -> " + pair);
            if (pair != null) {
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

    public static Set<Set<ClauseSet>> powerSet(Set<ClauseSet> originalSet) {
        // Function copied from stackoverflow at https://stackoverflow.com/questions/1670862/obtaining-a-powerset-of-a-set-in-java
        Set<Set<ClauseSet>> sets = new HashSet<>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<>());
            return sets;
        }
        List<ClauseSet> list = new ArrayList<>(originalSet);
        ClauseSet head = list.get(0);
        Set<ClauseSet> rest = new HashSet<>(list.subList(1, list.size()));
        for (Set<ClauseSet> set : powerSet(rest)) {
            Set<ClauseSet> newSet = new HashSet<>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }

    public static Set<ClauseSet> gamma(Set<ClauseSet> remainders) {
        Set<Set<ClauseSet>> powerRemainders = ClauseSet.powerSet(remainders);
        Set<ClauseSet> gammaSet = new HashSet<>();
        int h = 0;
        for (Set<ClauseSet> subSet : powerRemainders) {
            if (subSet.isEmpty())
                continue;

            ClauseSet intersection = new ClauseSet(subSet.iterator().next());
            for (ClauseSet remainder : subSet) {
                intersection.clauseList.retainAll(remainder.clauseList);
            }

            int newh = subSet.size() * intersection.clauseList.size();
            if (h < newh) {
                h = newh;
                gammaSet = subSet;
            }

        }
        return gammaSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClauseSet clauseSet = (ClauseSet) o;
        return clauseList.equals(clauseSet.clauseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clauseList);
    }
}
