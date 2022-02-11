/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ordenacao;

import Model.Data;
import Model.Receita;
import java.util.Comparator;

/**
 *
 * @author Alan
 */
public class ReceitaMesASC implements Comparator<Receita>{

    public ReceitaMesASC() {
    }

    @Override
    public int compare(Receita t, Receita t1) {
     
        if (t.getMes()> t1.getMes()) {

            return 1;

        } else if (t.getMes() < t1.getMes()) {

            return -1;

        } else {

            return 0;
        }
    
    }
    
}
