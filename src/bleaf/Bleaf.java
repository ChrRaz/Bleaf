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
        //ArrayList<KnowledgeBase> KBS = new ArrayList<KnowledgeBase>(); // Create an ArrayList object
        // TODO code application logic here
        Sentence sent1 = new Sentence();
        sent1.Add(new Literal(1));
        Literal L2 = new Literal(2);
        sent1.AddNeg(L2);
        Sentence sent2 = new Sentence();
        sent2.Add(L2);
        sent2.Add(new Literal(3));
        
        KnowledgeBase KB = new KnowledgeBase();
        KB.Add(sent1);
        KB.Add(sent2);
        KB.Print();
        //Litterals.add(new Litteral(1));
        //Litterals.add(new Litteral(0));
	}

        
    }

   
    
    
    


