/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bleaf;
import java.util.ArrayList;
/**
 *
 * @author patrickbruus
 */
public class Bleaf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        KnowledgeBase KB = new KnowledgeBase();
        
        
        
        Clause sent1 = new Clause();
        sent1.Add(new Literal(1));
        Literal L2 = new Literal(2);
        sent1.AddNeg(L2);
        
        Clause sent2 = new Clause();
        sent2.Add(L2);
        sent2.Add(new Literal(3));
        
        KB.Add(sent1);
        KB.Add(sent2);
        KB.Print();

    }

        
    }

   
    
    
    


