/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multimidia;
import multimidia.Classes.CD;
import multimidia.Classes.DVD;
import multimidia.Classes.Database;
/**
 *
 * @author acer
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CD I1 = new CD("O show", "Marlon", 20, 60);
        DVD I2 = new DVD("Super Nova", "Zequinha", 140);
        
        Database partilera;
        partilera = new Database();
        partilera.addItem(I1);
        partilera.addItem(I2);
        
        partilera.lista();
    }
    
}
