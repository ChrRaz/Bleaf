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
public class Variable {
    
    private String name;
    
    public Variable(String name){
        this.name = name;
    }
    
    public String getVariable(){
        return name;
    }
    
    public void print(){
        System.out.println(name);
    }
   
}
