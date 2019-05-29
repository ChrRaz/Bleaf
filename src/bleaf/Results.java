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

        ClauseSet intersection = ClauseSet.contraction(KB,phi);

        System.out.println("The contraction of the belief base is " + intersection);
        System.out.println();
    }

}
