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
public class KnowledgeBase {
    private ArrayList<Clause> SentenceList = new ArrayList<Clause>();;
    
    public KnowledgeBase(){
    }
    

    public void Add(Clause sent){
        this.SentenceList.add(sent);
    }
    
    public void Print(){
        System.out.print("{");
        for (int i = 0; i < SentenceList.size(); i++) {
           if(i==SentenceList.size()-1){
               SentenceList.get(i).Print();
           }
           else{
               SentenceList.get(i).Print();
               System.out.print(",");
           }
	}
        System.out.print("}");
        
    }
    public void Resolution(Clause sent){
      //  Knowledgespace KBnew = new Knowledgespace();
//        for(s in this.Sentences){
//            KBnew.Add(s);
//        }
//        KBnew.Add(sent.Neg);
//        KBnew.Checkthis; 
    }
   
}
