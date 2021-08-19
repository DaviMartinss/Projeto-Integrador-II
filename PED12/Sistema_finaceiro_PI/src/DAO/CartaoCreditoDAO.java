/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.CartaoCredito;
import Model.Usuario;
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
public class CartaoCreditoDAO {
    
    private Connection conexao = null;

    public CartaoCreditoDAO() {
        
        conexao = moduloConexao.conector();
    
    }
    
    
    public boolean CadastrarCartaoCredito(CartaoCredito cartao_credito) throws SQLException {
    
        PreparedStatement pst = null;

        String insert = "insert into cartao_credito (n_cartao_credito, limite, dia_fatura, valor_fatura, bandeira, conta_id_conta) values(?,?,?,?,?,?)";

        pst = conexao.prepareStatement(insert);
    
        try {

            pst.setLong(1, cartao_credito.getN_cartao_credito());
            pst.setFloat(2, cartao_credito.getLimite());
            pst.setInt(3, cartao_credito.getDia_fatura());
            pst.setFloat(4, cartao_credito.getValor_fatura());
            pst.setString(5, cartao_credito.getBandeira());
            pst.setFloat(6, cartao_credito.getId_conta());

            
            pst.executeUpdate();
            
            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        }finally{
            
            pst.close();
            
        }
    
    
    }
    
    public boolean UpdateCartaoCredito(CartaoCredito cartao_credito) throws SQLException {
    
        PreparedStatement pst = null;
         
        String update = "update cartao_credito set n_cartao_credito=?, limite=?, dia_fatura=?, valor_fatura=?, bandeira=? where n_cartao_credito=?";
        
        pst = conexao.prepareStatement(update);
    
        try {

            pst.setLong(1, cartao_credito.getN_cartao_credito());
            pst.setFloat(2, cartao_credito.getLimite());
            pst.setInt(3, cartao_credito.getDia_fatura());
            pst.setFloat(4, cartao_credito.getValor_fatura());
            pst.setString(5, cartao_credito.getBandeira());
            pst.setLong(6, cartao_credito.getN_cartao_aux());

            
            pst.executeUpdate();
            
            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        }finally{
            
            pst.close();
            
        }
    }
    
    
    public LinkedList<CartaoCredito> CarregaTabela_Cartao_C(int id_conta) throws SQLException {
       
       String consulta = "select * from cartao_credito where conta_id_conta=?";
       
       ResultSet rs = null;
       
       LinkedList<CartaoCredito> lista_CC = new LinkedList();
       
       PreparedStatement pst = conexao.prepareStatement(consulta);
             
       try{
           
           pst.setInt(1, id_conta);
       
           rs = pst.executeQuery();
           
           while(rs.next()){
               
               lista_CC.add(new CartaoCredito(
                 Long.parseLong(rs.getString("n_cartao_credito")),
                 Float.parseFloat(rs.getString("limite")),
                 Integer.parseInt(rs.getString("dia_fatura")),
                 Float.parseFloat(rs.getString("valor_fatura")),
                 rs.getString("bandeira"),
                 id_conta)         
               );
               
               
           }
           
           
       }catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
       
       }
       
       return lista_CC;
       
   }
    
   
   
   public LinkedList<CartaoCredito> ConsultaCartao_C(String tipo, String arg, int id_conta, boolean ordenar) throws SQLException {
       
       String argumento = "";
       
       if(ordenar){
           
           argumento = "conta_id_conta = " + id_conta + " and " + tipo + " " + "like '" + arg + "%'" + " ORDER BY " + tipo + " ASC";
           
       }else{
           
           argumento = "conta_id_conta = " + id_conta + " and " + tipo + " " + "like '" + arg + "%'" + " ORDER BY " + tipo + " DESC";
       }

       String consulta = "SELECT n_cartao_credito, limite, dia_fatura, valor_fatura, bandeira FROM cartao_credito WHERE " + argumento + "";
       
       ResultSet rs = null;
       
       PreparedStatement pst = conexao.prepareStatement(consulta);
       
       LinkedList<CartaoCredito> lista_CC = new LinkedList();
       
       try {

           rs = pst.executeQuery();
           
           
           while(rs.next()){
               
               lista_CC.add(new CartaoCredito(
                 Long.parseLong(rs.getString("n_cartao_credito")),
                 Float.parseFloat(rs.getString("limite")),
                 Integer.parseInt(rs.getString("dia_fatura")),
                 Float.parseFloat(rs.getString("valor_fatura")),
                 rs.getString("bandeira"),
                 id_conta)         
               );
               
               
           }
   

       } catch (Exception e) {

           JOptionPane.showMessageDialog(null, e.getMessage());

       } 
       
       return lista_CC;
       
   }
   
   public LinkedList<CartaoCredito> PreencherCamposCartao_C(String num_cartao, int id_conta) throws SQLException {
 
       String consulta = "SELECT n_cartao_credito, limite, dia_fatura, valor_fatura, bandeira FROM cartao_credito WHERE n_cartao_credito = ?";
    
       PreparedStatement pst = conexao.prepareStatement(consulta);
       
       ResultSet rs = null;
       
       LinkedList<CartaoCredito> lista_CC = new LinkedList();
       
       try {
           
           pst.setLong(1, Long.parseLong(num_cartao));
           
           rs = pst.executeQuery();
           
           while(rs.next()){
               
               lista_CC.add(new CartaoCredito(
                 Long.parseLong(rs.getString("n_cartao_credito")),
                 Float.parseFloat(rs.getString("limite")),
                 Integer.parseInt(rs.getString("dia_fatura")),
                 Float.parseFloat(rs.getString("valor_fatura")),
                 rs.getString("bandeira"),
                 id_conta)         
               );
               
               
           }

       } catch (Exception e) {

           JOptionPane.showMessageDialog(null, e.getMessage());

       }

       return lista_CC;
       
   }
    
    
}
