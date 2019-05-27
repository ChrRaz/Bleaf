/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bleaf;

/**
 * @author patrickbruus
 */
public class Literal {
    private String name;

    public Literal() {
    }

    public Literal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) || ((obj instanceof Literal) && ((Literal) obj).name.equals(this.name));
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    public void Print() {
        //    System.out.print(id);
        System.out.println(name);
    }

}
