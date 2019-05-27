package bleaf;

import java.util.Set;

public class Results {
    public static void showResults(ClauseSet KB, ClauseSet negPhi, ClauseSet phi) {
        System.out.println("KB");
        System.out.println(KB);

        System.out.println("Resolution");
        System.out.println(KB.resolution(new ClauseSet(negPhi)));

        System.out.println("Remainders");
        Set<ClauseSet> remset = KB.remainders(new ClauseSet(negPhi));
        System.out.println(remset);
        ClauseSet intersection = new ClauseSet(KB);
        for (ClauseSet rem : remset) {
            System.out.println(rem.resolution(new ClauseSet(phi)));
            System.out.println(rem.size());
            System.out.println(rem.relSize());
            intersection.clauseList.retainAll(rem.clauseList);
        }

        System.out.println(ClauseSet.gamma(remset));
        System.out.println("Intersections:");
        System.out.println(intersection);

    }


}
