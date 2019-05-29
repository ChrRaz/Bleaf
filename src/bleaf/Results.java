package bleaf;

import java.util.Set;

public class Results {

    public static void showResolution(ClauseSet KB, ClauseSet phi) {
        System.out.println();
        System.out.println("The original belief base was " + KB);

        boolean res = KB.resolution(new ClauseSet(phi));
        System.out.println("The belief base " + (!res ? "entails" : "does not entail") + " phi");
        System.out.println();
    }

    public static void showRemainders(ClauseSet KB, ClauseSet phi) {
        System.out.println();
        System.out.println("The original belief base was " + KB);

        Set<ClauseSet> remset = KB.remainders(new ClauseSet(phi));
        Set<ClauseSet> gammaremset = ClauseSet.gamma(remset);
        // Compute intersection
        ClauseSet intersection = new ClauseSet(KB);
        for (ClauseSet rem : gammaremset) {
            intersection.clauseList.retainAll(rem.clauseList);
        }

        System.out.println("The remainders are " + intersection);

        System.out.println("The contraction of the belief base is " + intersection);
        System.out.println();
    }

}
