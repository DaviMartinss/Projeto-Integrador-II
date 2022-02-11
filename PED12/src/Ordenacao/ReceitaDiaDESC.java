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
public class ReceitaDiaDESC implements Comparator<Receita>{

    public ReceitaDiaDESC() {
    }

    @Override
    public int compare(Receita t, Receita t1) {
     
      if (t.getDia() > t1.getDia()) {

            return -1;

        } else if (t.getDia() < t1.getDia()) {

            return 1;

        } else {

            return 0;
        }
    
    
    }
       
    
}
