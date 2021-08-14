/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.CartaoDebito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
   
   public ResultSet CarregaTabela_Cartao_D(int id_conta) throws SQLException {
       
       String consulta = "select * from cartao_debito where conta_id_conta=?";
       
       ResultSet rs = null;
       
       PreparedStatement pst = conexao.prepareStatement(consulta);
             
       try{
           
           pst.setInt(1, id_conta);
       
           rs = pst.executeQuery();
           
       }catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
       
       }
       
       return rs;
       
   }
   
   public ResultSet ConsultaCartao_D(String tipo, String arg, int id_conta, boolean ordenar) throws SQLException {
       
       String argumento = "";
       
       if(ordenar){
           
           argumento = "conta_id_conta = " + id_conta + " and " + tipo + " " + "like '" + arg + "%'" + " ORDER BY " + tipo + " ASC";
           
       }else{
           
           argumento = "conta_id_conta = " + id_conta + " and " + tipo + " " + "like '" + arg + "%'" + " ORDER BY " + tipo + " DESC";
       }

       String consulta = "SELECT n_cartao_debito, valor_atual, bandeira FROM cartao_debito WHERE " + argumento + "";
       
       ResultSet rs = null;
       
       PreparedStatement pst = conexao.prepareStatement(consulta);
       
       try {

           rs = pst.executeQuery();

       } catch (Exception e) {

           JOptionPane.showMessageDialog(null, e.getMessage());

       } 
       
       return rs;
       
   }
   
   public ResultSet PreencherCamposCartao_D(String num_cartao) throws SQLException {
 
       String consulta = "SELECT n_cartao_debito, valor_atual, bandeira FROM cartao_debito WHERE  n_cartao_debito = ?";
    
       PreparedStatement pst = conexao.prepareStatement(consulta);
       
       ResultSet rs = null;
       
       try {
           
           pst.setLong(1, Long.parseLong(num_cartao));
           
           rs = pst.executeQuery();

       } catch (Exception e) {

           JOptionPane.showMessageDialog(null, e.getMessage());

       }

       return rs;
       
   }
   
   
    
}
