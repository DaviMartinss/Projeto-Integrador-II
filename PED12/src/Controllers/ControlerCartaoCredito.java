/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.CartaoCreditoDAO;
import Model.CartaoCredito;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan
 */
public class ControlerCartaoCredito {
    
    public static boolean CadastrarCartaoCredito(CartaoCredito cartao_credito){
        
        CartaoCreditoDAO ccDAO = new CartaoCreditoDAO();
        
        try {
            
          ccDAO.InsertCartaoCredito(cartao_credito);          
          return true;
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:CadastrarCartaoCredito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:CadastrarCartaoCredito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;   
        }
    }
    
    public static boolean UpdateCreditoFaturaCartaoCredito(int id_conta, float valor, long num_cartao){
        
        CartaoCreditoDAO ccDAO = new CartaoCreditoDAO();
        
        try {
            
          ccDAO.UpdateCreditoFaturaCartaoCredito(id_conta, valor, num_cartao);          
          return true;
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Erro:UpdateCreditoFaturaCartaoCredito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Erro:UpdateCreditoFaturaCartaoCredito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;   
        }
    }
    
    public static boolean  AtualizarCartaoCredito(CartaoCredito cartao_credito) {

        CartaoCreditoDAO ccDAO = new CartaoCreditoDAO();

        try {

            CartaoCredito cartaoCC_antigo = ccDAO.GetCartaoCredito(cartao_credito.getN_cartao_aux(), cartao_credito.getId_conta());

            float credito_att = 0;

            //RESUMINDO: LIMITE SO PODE SER REDUZIDO SE N TIVER DESPESA NP NO CARTAO
            //CRÉDITO ATUALIZADO
            if (cartaoCC_antigo.getLimite() < cartao_credito.getLimite())
                credito_att = (cartao_credito.getLimite() - cartaoCC_antigo.getLimite()) + cartaoCC_antigo.getCredito();
            else 
            {
                if (!ccDAO.DespesaNpCartaoExiste(cartao_credito.getN_cartao_aux()))
                    credito_att = cartaoCC_antigo.getCredito() - (cartaoCC_antigo.getLimite() - cartao_credito.getLimite());
                else 
                    return false;               
            }

            if (credito_att != 0)
                cartao_credito.setCredito(credito_att);
            else
                cartao_credito.setCredito(cartaoCC_antigo.getCredito());

            ccDAO.UpdateCartaoCredito(cartao_credito);

            return true;

        } catch (SQLException | NullPointerException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Erro:AtualizarCartaoCredito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;
        }
    }
    
    public static boolean ApagarCartaoCredito(CartaoCredito cartao_credito){
        
        CartaoCreditoDAO ccDAO = new CartaoCreditoDAO();
        
        try {
            
          ccDAO.DeleteCartaoCredito(cartao_credito);          
          return true;
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:ApagarCartaoCredito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:ApagarCartaoCredito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;   
        }
    }
    
    public static LinkedList<CartaoCredito> GetListaCartaoCredito(int id_conta){
        
         CartaoCreditoDAO ccDAO = new CartaoCreditoDAO();
        
        try {
            
            return ccDAO.GetListaCartaoCredito(id_conta);
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:GetListaCartaoCredito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
            
            return null;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:GetListaCartaoCredito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return null;
        }     
    }
    
    public static boolean CartaoCreditoExiste(CartaoCredito cartao){
        
         CartaoCreditoDAO ccDAO = new CartaoCreditoDAO();
        
        try {
            
            return ccDAO.CartaoCreditoExiste(cartao);
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:GetListaCartaoCredito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
            
            return false;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:GetListaCartaoCredito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return false;
        }     
    }
    
    public static CartaoCredito GetCartaoCredito(long numCartao, int id_conta){
        
         CartaoCreditoDAO ccDAO = new CartaoCreditoDAO();
        
        try {
            
            return ccDAO.GetCartaoCredito(numCartao, id_conta);
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:GetCartaoCredito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
            
            return null;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:GetCartaoCredito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return null;
        }     
    }
    
    public static LinkedList<CartaoCredito> ConsultaCartaoCredito(String tipo, String arg, int id_conta, boolean ordenar){
        
         CartaoCreditoDAO ccDAO = new CartaoCreditoDAO();
        
        try {
            
            return ccDAO.ConsultaCartaoCredito(tipo, arg, id_conta, ordenar);
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:ConsultaCartaoCredito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
            
            return null;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:ConsultaCartaoCredito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return null;
        }     
    }
    
}
