package bleaf;

import java.util.Set;

public class Results {

    public static void showResolution(ClauseSet KB, ClauseSet phi) {
        System.out.println("KB");
        System.out.println(KB);

        System.out.println("Resolution");
        System.out.println(KB.resolution(new ClauseSet(phi)));

    }
    
    public static void showRemainders(ClauseSet KB, ClauseSet phi) {
        System.out.println("KB");
        System.out.println(KB);
        
        Set<ClauseSet> remset = KB.remainders(new ClauseSet(phi));
        Set<ClauseSet> gammaremset = ClauseSet.gamma(remset);
        ClauseSet intersection = new ClauseSet(KB);
        for (ClauseSet rem : gammaremset) {
            intersection.clauseList.retainAll(rem.clauseList);
        }

        System.out.println("Intersections:");
        System.out.println(intersection);
    }

}
