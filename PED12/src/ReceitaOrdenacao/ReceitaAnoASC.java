/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReceitaOrdenacao;

import Model.Data;
import Model.Receita;
import java.util.Comparator;

/**
 *
 * @author Alan
 */
public class ReceitaAnoASC implements Comparator<Receita>{

    public ReceitaAnoASC() {
    }

    @Override
    public int compare(Receita t, Receita t1) {
       
        if (t.getAno() > t1.getAno()) {

            return 1;

        } else if (t.getAno() < t1.getAno()) {

            return -1;

        } else {

            return 0;
        }

    }

}
