/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bleaf;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author patrickbruus
 */
public class Bleaf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean decicionMade = false;
        Scanner in = new Scanner(System.in);

            while (decicionMade == false) {
                
                String name = "";
                System.out.println("Do you want to use a primade example? type y yes or n for no");
                name = in.next();
                String namelowcase = name.toLowerCase();
                if (name.equals("y")) {
                    UI.Example();
                } else if (name.equals("n")) {
                    UI.userInput();
                } else {
                    System.out.println("Error try again");

                }

            }
        UI.userInput();
        
        
    }

}

