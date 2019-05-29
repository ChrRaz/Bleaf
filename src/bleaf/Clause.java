/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bleaf;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author patrickbruus
 */
public class Clause {

    private Set<Literal> posList = new HashSet<>();
    private Set<Literal> negList = new HashSet<>();

    public Clause() {
    }

    public void add(Literal litt) {
        posList.add(litt);
    }

    public void addNeg(Literal litt) {
        negList.add(litt);
    }

    public void addAll(Clause clause) {
        this.posList.addAll(clause.posList);
        this.negList.addAll(clause.negList);
    }

    public void remove(Literal literal) {
        this.posList.remove(literal);
        this.negList.remove(literal);
    }

    public void negate() {
        Set<Literal> holder = this.posList;
        this.posList = this.negList;
        this.negList = holder;
    }

    public Literal complementary(Clause c) {
        Literal found = null;
        for (Literal lit : this.posList) {
            if (c.negList.contains(lit)) {
                if (found == null) {
                    found = lit;
                } else {
                    return null;
                }

            }
        }
        for (Literal lit : this.negList) {
            if (c.posList.contains(lit)) {
                if (found == null) {
                    found = lit;
                } else {
                    return null;
                }
            }
        }
        return found;
    }

    public int size() {
        return this.posList.size() + this.negList.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clause clause = (Clause) o;
        return posList.equals(clause.posList) &&
                negList.equals(clause.negList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(posList, negList);
    }

    @Override
    public String toString() {
        // ~~ Adds a negation sign to all negated literals, contatenates positive and negative literals, and returns them all between braces ~~
        // Works in mysterious ways
        return "{" + Stream.concat(this.posList.stream().map(Literal::toString), this.negList.stream().map(lit -> "¬" + lit)).collect(Collectors.joining(", ")) + "}";
    }

}
