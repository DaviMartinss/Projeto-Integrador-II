/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CategoriaOrdenacao;

import java.util.Comparator;
import Model.Categoria;
import java.text.Collator;



public class CategoriaASC implements Comparator<Categoria> {
     
    public CategoriaASC() {
    
     }
     
    @Override
    public int compare(Categoria c, Categoria c1) {

        // c é maior que c1
        if (Collator.getInstance().compare(c.getCategoria_aux(), c1.getCategoria_aux()) > 0) {
            return 1;
            
        } else if (Collator.getInstance().compare(c.getCategoria_aux(), c1.getCategoria_aux()) < 0) {
             // c é menor que c1
            return -1;

        } else {
              // são iguais
            return 0;
        }
    
    }
}
