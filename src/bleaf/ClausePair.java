package bleaf;

public class ClausePair implements Comparable<ClausePair> {
    private Clause first, second;

    public ClausePair() {
    }

    public ClausePair(Clause first, Clause second) {
        this.first = first;
        this.second = second;
    }

    public Clause getFirst() {
        return first;
    }

    public Clause getSecond() {
        return second;
    }

    public Clause resolve() {
        Literal literal = first.complementary(second);
        Clause newClause = new Clause();
        newClause.addAll(first);
        newClause.addAll(second);
        newClause.remove(literal);
        return newClause;
    }

    private int units() {
        if (first.size() == 1 && second.size() == 1)
            return 2;
        if (first.size() == 1 || second.size() == 1)
            return 1;
        return 0;
    }

    @Override
    public int compareTo(ClausePair o) {
        return this.units() - o.units();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ClausePair)) return false;
        ClausePair other = (ClausePair) obj;
        return this.first.equals(other.first) && this.second.equals(other.second) ||
                this.first.equals(other.second) && this.second.equals(other.first);
    }

    @Override
    public String toString() {
        return "<" + this.first + ", " + this.second + ">";
    }
}
