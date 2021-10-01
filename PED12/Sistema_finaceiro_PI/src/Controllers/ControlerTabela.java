/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alan
 */
public class ControlerTabela {
    
    public static void LimpaTabela(JTable tabela){
        
        DefaultTableModel mp1 = (DefaultTableModel) tabela.getModel();

        int l = mp1.getRowCount();

        if (l > 0) {
            while (l > 0) {
                //Limpa tabela sempre que for fazer uma nova consulta
                ((DefaultTableModel) tabela.getModel()).removeRow(l - 1);

                //Menos um pois a primeira linha é a linha zero
                l--;
            }
        }
        
    }
    
    
    
}
