/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bleaf;

import java.util.Scanner;

/**
 * @author patrickbruus
 */
public class UI {

    public static String decision(String[] array) {
        boolean decisionMade = false;
        Scanner in = new Scanner(System.in);

        while (decisionMade == false) {
            String name = "";
            System.out.println(array[0]);
            name = in.next();
            String namelowcase = name.toLowerCase();
            for (int i = 1; i < array.length; i++) {
                if (array[i].equals(namelowcase)) {
                    return namelowcase;

                }
            }
            System.out.println("Error try again");
        }
        return "";
    }

    public static void userInput() {

        String[] arrayMode = {"Do you want to use resolution or remainders? Type rs for resolution or rm for remainders", "rs", "rm"};
        String decisionMode = UI.decision(arrayMode);
        ClauseSet KB = ClauseSetting();

        if (decisionMode.equals(arrayMode[1])) {
            ClauseSet phi;
            if (KB == Example1() || KB == Example2()) {
                phi = ClauseSettingPhi(arrayMode[1]);

            } else {
                System.out.println("Now enter the clauses for not Phi you want");
                phi = manualClauseSetting();
            }
            Results.showResolution(KB, phi);
        } else {
            ClauseSet phi;
            if (KB == Example1() || KB == Example2()) {
                phi = ClauseSettingPhi(arrayMode[2]);

            } else {
                System.out.println("Now enter the clauses for Phi you want");
                phi = manualClauseSetting();
            }
            Results.showRemainders(KB, phi);
        }
    }

    private static ClauseSet ClauseSetting() {
        ClauseSet clauseSet = new ClauseSet();
        String[] arraySrc = {"What Knowlegde Base do you want to use? " + System.lineSeparator() + "Example 1 "+Example1() + System.lineSeparator() + "Example 2 "+ Example2() + System.lineSeparator() + " Type 1 for example 1 or 2 for example 2 or 3 for your own", "1", "2", "3"};
        String decisionSrc = UI.decision(arraySrc);
        if (decisionSrc.equals(arraySrc[1])) {
            clauseSet = Example1();
        } else if (decisionSrc.equals(arraySrc[2])) {
            clauseSet = Example2();
        } else {
            clauseSet = manualClauseSetting();
        }
        return clauseSet;

    }

    private static ClauseSet ClauseSettingPhi(String choice) {
        ClauseSet clauseSet = new ClauseSet();
        if (choice.equals("rs")) {
            System.out.println("Now enter the clauses for not Phi you want");
        } else {
            System.out.println("Now enter the clauses for Phi you want");
        }

        String[] arraySrc = {"What Phi do you want to use? " + System.lineSeparator() + "Phi 1 " + ExamplePhi1() + " for remainders and not Phi 1 " + ExampleNotPhi1() + " for resolution" + System.lineSeparator() + "Phi 2 " + ExamplePhi2() + " for remainders and not Phi 2 " + ExampleNotPhi2() + " for resolution" + System.lineSeparator() + " Type 1 for Phi 1 or 2 for Phi 2 or 3 for your own", "1", "2", "3"};
        String decisionSrc = UI.decision(arraySrc);
        if (decisionSrc.equals(arraySrc[1])) {
            if (choice.equals("rs")) {
                clauseSet = ExampleNotPhi1();
            } else {
                clauseSet = ExamplePhi1();

            }
        } else if (decisionSrc.equals(arraySrc[2])) {
            if (choice.equals("rs")) {
                clauseSet = ExampleNotPhi2();
            } else {
                clauseSet = ExamplePhi2();

            }
        } else {
            clauseSet = manualClauseSetting();
        }
        return clauseSet;

    }

    private static ClauseSet manualClauseSetting() {
        boolean isClausesSet = false;
        ClauseSet clauseSet = new ClauseSet();
        while (isClausesSet == false) {
            Clause clause = new Clause();

            Scanner in = new Scanner(System.in);
            System.out.println("Enter the literals you want in your clause");
            System.out.println("After each literal set a , to indicate new literal");
            System.out.println("To negate a literal use - before it");
            if (0 == (int) clauseSet.size()) {
            } else {
                System.out.println("You know have " + clauseSet);
            }

            String litterals = in.next();
            String[] litteralsArray = litterals.split(",");
            for (String litteralElement : litteralsArray) {
                String newlitteralElement = litteralElement.replace("-", "");

                Literal lit = new Literal(newlitteralElement);

                if (litteralElement.contains("-")) {
                    clause.addNeg(lit);

                } else {
                    clause.add(lit);
                }

            }
            clauseSet.add(clause);
            boolean decisionMade = false;
            while (decisionMade == false) {
                String name = "";
                System.out.println("Do you want to add another clause type y/Y for yes or n/N for no");
                name = in.next();
                String namelowcase = name.toLowerCase();
                if (name.equals("y")) {
                    decisionMade = true;
                } else if (name.equals("n")) {
                    decisionMade = true;
                    isClausesSet = true;
                } else {
                    System.out.println("Error try again");

                }

            }

        }
        return clauseSet;
    }

    public static ClauseSet Example1() {
        Literal p = new Literal("p");
        Literal q = new Literal("q");
        Literal r = new Literal("r");
        Literal s = new Literal("s");

        Clause first = new Clause();
        first.add(p);
        first.add(q);
        first.addNeg(r);

        Clause second = new Clause();
        second.addNeg(p);
        second.addNeg(r);

        Clause third = new Clause();
        third.addNeg(q);

        Clause fourth = new Clause();
        fourth.addNeg(p);
        fourth.add(r);
        fourth.add(s);

        Clause fifth = new Clause();
        fifth.add(s);
        fifth.add(q);
        fifth.add(r);

        ClauseSet KB = new ClauseSet(first, second, third, fourth, fifth);

        return KB;

    }

    public static ClauseSet Example2() {
        Literal p = new Literal("p");
        Literal q = new Literal("q");
        Literal r = new Literal("r");
        Literal s = new Literal("s");
        Literal t = new Literal("t");

        Clause first = new Clause();
        first.add(p);

        Clause second = new Clause();
        second.addNeg(s);

        Clause third = new Clause();
        third.addNeg(q);
        third.add(p);

        Clause fourth = new Clause();
        fourth.add(s);
        fourth.addNeg(t);

        Clause fifth = new Clause();
        fifth.addNeg(p);
        fifth.add(s);
        fifth.add(r);

        Clause sixth = new Clause();
        sixth.addNeg(t);
        sixth.add(r);

        Clause seventh = new Clause();
        seventh.addNeg(r);
        seventh.add(p);

        ClauseSet KB = new ClauseSet(first, second, third, fourth, fifth, sixth, seventh);
        return KB;

    }

    public static ClauseSet ExamplePhi1() {
        Literal r = new Literal("r");
        Clause negPhi = new Clause();
        negPhi.add(r);
        ClauseSet clausePhi = new ClauseSet(negPhi);
        return clausePhi;
    }

    public static ClauseSet ExampleNotPhi1() {
        Literal r = new Literal("r");
        Clause phi = new Clause();
        phi.addNeg(r);
        ClauseSet clausePhi = new ClauseSet(phi);
        return clausePhi;
    }

    public static ClauseSet ExamplePhi2() {
        Literal r = new Literal("p");
        Clause negPhi = new Clause();
        negPhi.add(r);
        ClauseSet clausePhi = new ClauseSet(negPhi);
        return clausePhi;
    }

    public static ClauseSet ExampleNotPhi2() {
        Literal r = new Literal("p");
        Clause phi = new Clause();
        phi.addNeg(r);
        ClauseSet clausePhi = new ClauseSet(phi);
        return clausePhi;
    }
}
