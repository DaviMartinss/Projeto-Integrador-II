/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.DespesaDAO;
import DAO.ReceitaDAO;
import Model.Despesa;
import Model.Receita;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan
 */
public class ControlerReceita {
    
    public static boolean ReceitaTemSaldo(Receita receita, float valor){
        
        ReceitaDAO receitaDAO = new ReceitaDAO();
        
        try {
            
            if (valor <= receitaDAO.GetReceitaSaldo(receita, valor))
                return true;
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            JOptionPane.showMessageDialog(null, "Erro:ReceitaTemSaldo", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            JOptionPane.showMessageDialog(null, "Erro:ReceitaTemSaldo", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
        }
        
        return false;
    }
    
    public static boolean CadastrarReceita(Receita receita_nova){
        
       ReceitaDAO receitaDAO = new ReceitaDAO(); 
       DespesaDAO despesaDAO = new DespesaDAO();
       
        try {
            
          Receita ultimaReceita = receitaDAO.GetUltimaReceita(receita_nova.getId_conta());
            
            if (ultimaReceita != null) {

                LinkedList<Despesa> lista_despesasNp = despesaDAO.GetListaDespesasNpUltimaReceita(ultimaReceita);

                receitaDAO.InsertReceita(receita_nova);

                receita_nova = receitaDAO.GetUltimaReceita(receita_nova.getId_conta());

                despesaDAO.TransferirDespesasEntreReceitas(lista_despesasNp, receita_nova);
            }
            
            return true;
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:CadastrarReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return false;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:CadastrarReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
            
            return false;
        }
    }
    
    public static boolean AtualizarReceita(Receita receita){
        
        ReceitaDAO receitaDAO = new ReceitaDAO();
        
        try {
            
            receitaDAO.UpdateReceita(receita);
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            JOptionPane.showMessageDialog(null, "Erro:AtualizarReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            JOptionPane.showMessageDialog(null, "Erro:AtualizarReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
        }
        
        return false;        
    }
    
    public static boolean ApagarReceita(Receita receita){
        
         ReceitaDAO receitaDAO = new ReceitaDAO();
        
        try {
            
            receitaDAO.DeleteReceita(receita);
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            JOptionPane.showMessageDialog(null, "Erro:AtualizarReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            JOptionPane.showMessageDialog(null, "Erro:AtualizarReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
        }
        
        return false;     
    }
    
    public static LinkedList<Receita> GetListaReceita(int id_conta){
        
         ReceitaDAO receitaDAO = new ReceitaDAO();
        
        try {
            
            return receitaDAO.GetListaReceita(id_conta);
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:GetListaReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
            
            return null;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:GetListaReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return null;
        }     
    }
    
    public static Receita GetUltimaReceita(int conta_id){
        
         ReceitaDAO receitaDAO = new ReceitaDAO();
        
        try {
            
            return receitaDAO.GetUltimaReceita(conta_id);
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:GetUltimaReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return null;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:GetUltimaReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return null;
        }     
    }
    
    public static boolean UpdateTotalReceita(int conta_id, float valor, int cod_receita){
        
         ReceitaDAO receitaDAO = new ReceitaDAO();
        
        try {
            
            receitaDAO.UpdateTotalReceita(conta_id, valor, cod_receita);
            
            return true;
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:UpdateTotalReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return false;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:UpdateTotalReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return false;
        }     
    }
    
    public static int GetCodigoReceita(int conta_id, int mes, int ano) throws Exception{
        
         ReceitaDAO receitaDAO = new ReceitaDAO();
         
         //EXCEÇÃO TEMPORARIA ATE Q SEJA MELHORADO O LANÇAMENTO DAS EXCEPTION
         Exception Exception = null;
        
        try {
            
            return receitaDAO.GetCodigoReceita(conta_id, mes, ano);
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:GetCodigoReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
         
            throw ex;
            
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:GetCodigoReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            throw ex;
        }     
    }
    
    public static LinkedList<Receita> ConsultaReceita(String tipo, String arg, int id_conta, boolean ordenar){
        
         ReceitaDAO receitaDAO = new ReceitaDAO();
        
        try {
            
            return receitaDAO.ConsultaReceita(tipo,arg,id_conta,ordenar);
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:ConsultaReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
            
            return null;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:ConsultaReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return null;
        }     
    }
    
    public static boolean ReceitaExiste(Receita receita){
        
         ReceitaDAO receitaDAO = new ReceitaDAO();
        
        try {
            
            return receitaDAO.ReceitaExiste(receita);
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:ReceitaExiste", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return false;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:ReceitaExiste", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return false;
        }     
    }
    
}