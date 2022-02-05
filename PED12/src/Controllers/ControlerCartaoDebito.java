/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.CartaoDebitoDAO;
import Model.CartaoDebito;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan
 */
public class ControlerCartaoDebito {
    
    public static boolean CadastrarCartaoDebito(CartaoDebito cartao_debito){
        
        CartaoDebitoDAO cartao_debitoDAO = new CartaoDebitoDAO();
        
        try {
            
          cartao_debitoDAO.InsertCartaoDebito(cartao_debito);          
          return true;
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:CadastrarCartaoDebito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:CadastrarCartaoDebito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;   
        }
    }
    
    public static boolean UpdateValorAtualCartaoDebito(int id_conta, float valor, long num_cartao){
        
        CartaoDebitoDAO cartao_debitoDAO = new CartaoDebitoDAO();
        
        try {
            
          cartao_debitoDAO.UpdateValorAtualCartaoDebito(id_conta, valor, num_cartao);          
          return true;
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Erro:UpdateValorAtualCartaoDebito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Erro:UpdateValorAtualCartaoDebito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;   
        }
    }
    
    public static boolean DespesaCartaoExiste(long num_cartao){
        
        CartaoDebitoDAO cartao_debitoDAO = new CartaoDebitoDAO();
        
        try {
            
          cartao_debitoDAO.DespesaCartaoExiste(num_cartao);          
          return true;
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:DespesaCartaoExiste", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:DespesaCartaoExiste", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;   
        }
    }
    
    public static CartaoDebito GetCartaoDebito(long numCartao, int id_conta){
        
        CartaoDebitoDAO cartao_debitoDAO = new CartaoDebitoDAO();
        
        try {
            
          return cartao_debitoDAO.GetCartaoDebito(numCartao, id_conta);          
       
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:DespesaCartaoExiste", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return null;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:DespesaCartaoExiste", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return null;   
        }
    }
    
    public static boolean AtualizarCartaoDebito(CartaoDebito cartao_debito){
        
        CartaoDebitoDAO cartao_debitoDAO = new CartaoDebitoDAO();
        
        try {
            
          cartao_debitoDAO.UpdateCartaoDebito(cartao_debito);          
          return true;
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:AtualizarCartaoDebito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:AtualizarCartaoDebito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;   
        }
    }
    
    public static boolean ApagarCartaoDebito(CartaoDebito cartao_debito){
        
        CartaoDebitoDAO cartao_debitoDAO = new CartaoDebitoDAO();
        
        try {
            
          cartao_debitoDAO.DeleteCartaoDebito(cartao_debito);          
          return true;
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:ApagarCartaoDebito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:ApagarCartaoDebito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;   
        }
    }
    
    public static LinkedList<CartaoDebito> ConsultaCartaoDebito(String tipo, String arg, int id_conta, boolean ordenar){
        
        CartaoDebitoDAO cartao_debitoDAO = new CartaoDebitoDAO();
        
        try {
            
          return cartao_debitoDAO.ConsultaCartaoDebito(tipo, arg, id_conta, ordenar);          
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:ConsultaCartaoDebito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return null;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:ConsultaCartaoDebito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return null;   
        }
    }
    
    public static LinkedList<CartaoDebito> GetListaCartaoDebito(int id_conta){
        
        CartaoDebitoDAO cartao_debitoDAO = new CartaoDebitoDAO();
        
        try {
            
          return cartao_debitoDAO.GetListaCartaoDebito(id_conta);          
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:ConsultaCartaoDebito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return null;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:ConsultaCartaoDebito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return null;   
        }
    }
    
    public static boolean CartaoDebitoExiste(CartaoDebito cartao){
        
        CartaoDebitoDAO cartao_debitoDAO = new CartaoDebitoDAO();
        
        try {
            
          cartao_debitoDAO.CartaoDebitoExiste(cartao);          
          return true;
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:CartaoDebitoExiste", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:CartaoDebitoExiste", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;   
        }
    }
    
}