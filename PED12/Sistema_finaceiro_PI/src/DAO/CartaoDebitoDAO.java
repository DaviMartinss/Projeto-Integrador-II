/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.CartaoCredito;
import Model.CartaoDebito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan
 */
public class CartaoDebitoDAO {
    
    
    private Connection conexao = null;

    public CartaoDebitoDAO() {
        
        conexao = moduloConexao.conector();
    
    }
    
   public boolean CadastrarCartaoDebito(CartaoDebito cartao_db) throws SQLException {
       
       PreparedStatement pst = null;
       
       String insert = "insert into cartao_debito (n_cartao_debito,valor_atual, bandeira, conta_id_conta) values(?,?, ?, ?)";

       pst = conexao.prepareStatement(insert);
       
        try {

            pst.setLong(1, cartao_db.getN_cartao_debito());
            pst.setFloat(2, cartao_db.getValor_atual());
            pst.setString(3, cartao_db.getBandeira());
            pst.setFloat(4, cartao_db.getId_conta());
            
            pst.executeUpdate();

            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        } finally {

            pst.close();

        }

    }
   public boolean UpdateCartaoDebito(CartaoDebito cartao_debito) throws SQLException {
    
        PreparedStatement pst = null;
         
        String update = "update cartao_debito set n_cartao_debito=?, valor_atual= ?, bandeira=? where n_cartao_debito=?";
        
        pst = conexao.prepareStatement(update);
    
        try {

            pst.setLong(1, cartao_debito.getN_cartao_debito());
            pst.setFloat(2, cartao_debito.getValor_atual());
            pst.setString(3, cartao_debito.getBandeira());
            pst.setLong(4, cartao_debito.getN_cartao_aux());
            
            pst.executeUpdate();
            
            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        }finally{
            
            pst.close();
            
        }
    }
    
   public boolean DeleteCartaoDebito(CartaoDebito cartao_debito) throws SQLException {
    
        PreparedStatement pst = null;
         
        String update = "delete from cartao_debito where n_cartao_debito = ?";
        
        pst = conexao.prepareStatement(update);
    
        try {

            pst.setLong(1, cartao_debito.getN_cartao_debito());
            
            pst.executeUpdate();
            
            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        }finally{
            
            pst.close();
            
        }
    }
   
   public LinkedList<CartaoDebito> CarregaTabela_Cartao_D(int id_conta) throws SQLException {
       
       String consulta = "select * from cartao_debito where conta_id_conta=?";
       
       ResultSet rs = null;
       
       LinkedList<CartaoDebito> lista_CD = new LinkedList();
       
       PreparedStatement pst = conexao.prepareStatement(consulta);
             
       try{
           
           pst.setInt(1, id_conta);
       
           rs = pst.executeQuery();
           
           while(rs.next()){
               
               lista_CD.add(new CartaoDebito(
                 Long.parseLong(rs.getString("n_cartao_debito")),
                 Float.parseFloat(rs.getString("valor_atual")),
                 rs.getString("bandeira"),
                 id_conta)         
                );
           }
           
       }catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
       
       }finally{
           
           pst.close();
       }
       
       return lista_CD;
       
   }
   
   public LinkedList<CartaoDebito> ConsultaCartao_D(String tipo, String arg, int id_conta, boolean ordenar) throws SQLException {
       
       String argumento = "";
       
       if(ordenar){
           
           argumento = "conta_id_conta = " + id_conta + " and " + tipo + " " + "like '" + arg + "%'" + " ORDER BY " + tipo + " ASC";
           
       }else{
           
           argumento = "conta_id_conta = " + id_conta + " and " + tipo + " " + "like '" + arg + "%'" + " ORDER BY " + tipo + " DESC";
       }

       String consulta = "SELECT n_cartao_debito, valor_atual, bandeira FROM cartao_debito WHERE " + argumento + "";
       
       ResultSet rs = null;
       
       LinkedList<CartaoDebito> lista_CD = new LinkedList();
       
       PreparedStatement pst = conexao.prepareStatement(consulta);
       
       try {

           rs = pst.executeQuery();
           
           while(rs.next()){
               
               lista_CD.add(new CartaoDebito(
                 Long.parseLong(rs.getString("n_cartao_debito")),
                 Float.parseFloat(rs.getString("valor_atual")),
                 rs.getString("bandeira"),
                 id_conta)         
                );
           }

       } catch (Exception e) {

           JOptionPane.showMessageDialog(null, e.getMessage());

       } finally{
           
           pst.close();
       }
       
       return lista_CD;
       
   }
   
   public LinkedList<CartaoDebito> PreencherCamposCartao_D(String num_cartao, int id_conta) throws SQLException {
 
       String consulta = "SELECT n_cartao_debito, valor_atual, bandeira FROM cartao_debito WHERE  n_cartao_debito = ?";
    
       PreparedStatement pst = conexao.prepareStatement(consulta);
       
       LinkedList<CartaoDebito> lista_CD = new LinkedList();
       
       ResultSet rs = null;
       
       try {
           
           pst.setLong(1, Long.parseLong(num_cartao));
           
           rs = pst.executeQuery();
           
            while(rs.next()){
               
               lista_CD.add(new CartaoDebito(
                 Long.parseLong(rs.getString("n_cartao_debito")),
                 Float.parseFloat(rs.getString("valor_atual")),
                 rs.getString("bandeira"),
                 id_conta)         
                );
           }

       } catch (Exception e) {

           JOptionPane.showMessageDialog(null, e.getMessage());

       }finally{
           
           pst.close();
       }

       return lista_CD;
       
   }
   
   
    
}
