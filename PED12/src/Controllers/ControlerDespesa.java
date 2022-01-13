/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.DespesaDAO;
import Model.Despesa;

/**
 *
 * @author Alan
 */
public class ControlerDespesa {
    
    public static boolean CadastrarDespesa(Despesa despesa){
        
        DespesaDAO despesaDAO = new DespesaDAO();
        
//        try {
            
            //cod_receita = Controler.GetCodigoReceita(despesa.getId_conta(), despesa.getMes(), despesa.getAno());
            
            //despesaDAO.InsertDespesa(despesa, cod_receita, id_categoria);
                return true;
            
//        }catch (SQLException ex) {
//            
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
//            
//            JOptionPane.showMessageDialog(null, "Erro:ReceitaTemSaldo", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
//        
//        }catch(Exception ex){
//            
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
//            
//            JOptionPane.showMessageDialog(null, "Erro:ReceitaTemSaldo", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
//        }
//        
//        return false;
   }
}
