/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bleaf;

/**
 *
 * @author patrickbruus
 */
public class Literal {
    private int id;
    //private boolean name;
    
    public Literal(int id){
       // this.name = name;
        this.id = id;
    }
    
 //   public boolean getNegation(){
       // return name;
 //   }
    
    public int getID(){
        return id;
    }
    
    public void Print(){
        System.out.print(id);
   //     System.out.println(name);
    }
   
}
