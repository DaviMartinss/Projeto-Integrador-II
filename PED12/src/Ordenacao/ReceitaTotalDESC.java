/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ordenacao;

import Model.Receita;
import java.util.Comparator;

/**
 *
 * @author Alan
 */
public class ReceitaTotalDESC implements Comparator<Receita>{

    public ReceitaTotalDESC() {
    }

    @Override
    public int compare(Receita t, Receita t1) {
       
        if (t.getTotal() > t1.getTotal()) {

            return -1;

        } else if (t.getTotal()  < t1.getTotal()) {

            return 1;

        } else {

            return 0;
        }
    }
    
    
    
}
