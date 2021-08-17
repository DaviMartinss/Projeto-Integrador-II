/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.CartaoDebito;
import Model.Data;
import Model.Receita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan
 */
public class ReceitaDAO {
    
    private Connection conexao = null;

    public ReceitaDAO() {
        
        conexao = moduloConexao.conector();
    
    }
    
    public boolean CadastrarReceita(Receita receita) throws SQLException {
    
        int cod_receita = 0;
        
        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null;
        PreparedStatement pst3 = null;
        
        ResultSet rs = null;

        String sql1 = "insert into receita_data (dia, mes, ano) values(?,?,?)";
        
        String sql2 = "select * from receita_data where dia=? and mes=? and ano=?";
        
        String sql3 = "insert into receita (receita_data_cod_receita, total, conta_id_conta) values(?,?,?)";

        pst1 = conexao.prepareStatement(sql1);
    
        try {

            pst1 = conexao.prepareStatement(sql1);
            pst1.setInt(1, receita.getDia());
            pst1.setInt(2, receita.getMes());
            pst1.setInt(3, receita.getAno());
        
            pst1.executeUpdate();
            
            pst2 = conexao.prepareStatement(sql2);
            
            pst2.setInt(1, receita.getDia());
            pst2.setInt(2, receita.getMes());
            pst2.setInt(3, receita.getAno());
                   
            rs = pst2.executeQuery();
            
            if(rs.next()){
                
                cod_receita = rs.getInt(1);
                
                pst3 = conexao.prepareStatement(sql3);

                pst3.setInt(1, cod_receita);
                pst3.setFloat(2, receita.getTotal());
                pst3.setInt(3, receita.getId_conta());

                pst3.executeUpdate();
                
                return true;
                
            }else{
                
                JOptionPane.showMessageDialog(null, "ERRO CADASTRO DE RECEITA");
                return false;
            } 

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        }finally{
            
            pst1.close();
            pst2.close();
            pst3.close();
            
        }

    }
    
    public boolean UpdateReceita(Receita receita) throws SQLException {
    
        PreparedStatement pst = null;
         
        String update = "update (receita R join receita_data Rdt on R.receita_data_cod_receita = Rdt.cod_receita) set total = ?, dia = ?, mes = ?, ano = ? where (conta_id_conta = ? and mes = ? and ano = ?)";
        
        pst = conexao.prepareStatement(update);
    
        try {
            
            pst.setFloat(1, receita.getTotal());
            pst.setInt(2, receita.getDia());
            pst.setInt(3, receita.getMes());
            pst.setInt(4, receita.getAno());
            pst.setInt(5, receita.getId_conta());
            pst.setInt(6, receita.getSalva_Mes());
            pst.setInt(7, receita.getSalva_ano());
            
            pst.executeUpdate();
            
            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        }finally{
            
            pst.close();
            
        }
    }
    
    
    public ResultSet CarregaTabela_Receita(int id_conta) throws SQLException {
       
       String consulta = "SELECT re.total, re_da.dia, re_da.mes, re_da.ano FROM" +
               " receita_data re_da, receita re " +
               "WHERE re.conta_id_conta = ? and re.receita_data_cod_receita = re_da.cod_receita";
       
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
   
   public ResultSet Consulta_Receita(String tipo, String arg, int id_conta, boolean ordenar) throws SQLException {
       
       String argumento = "";
       
       if(ordenar){
           
           argumento = " and " + tipo + " " + "like '" + arg + "%'" + " ORDER BY " + tipo + " ASC";
           
       }else{
           
           argumento = " and " + tipo + " " + "like '" + arg + "%'" + " ORDER BY " + tipo + " DESC";
       }

       String consulta = "SELECT re.total, re_da.dia, re_da.mes, re_da.ano FROM" +
               " receita_data re_da, receita re " +
               "WHERE re.conta_id_conta = ? and re.receita_data_cod_receita = re_da.cod_receita" +
               argumento + "";
       
       ResultSet rs = null;
       
       PreparedStatement pst = conexao.prepareStatement(consulta);
       
       try {
           
           pst.setInt(1, id_conta);

           rs = pst.executeQuery();

       } catch (Exception e) {

           JOptionPane.showMessageDialog(null, e.getMessage());

       } 
       
       return rs;
       
   }
   
   public ResultSet PreencherCampos_Receita(String dia, String mes, String ano, String id_conta) throws SQLException {
 
       String consulta = "SELECT re.total, re_da.dia, re_da.mes, re_da.ano FROM" +
               " receita_data re_da, receita re " +
               "WHERE re.conta_id_conta = ? and re.receita_data_cod_receita = re_da.cod_receita " +
               "and re_da.dia = ? and re_da.mes = ? and re_da.ano = ?";
       
       PreparedStatement pst = conexao.prepareStatement(consulta);
       
       ResultSet rs = null;
       
       try {
           
           pst.setInt(1, Integer.parseInt(id_conta));
           
           pst.setInt(2, Integer.parseInt(dia));
           
           pst.setInt(3, Integer.parseInt(mes));
           
           pst.setInt(4, Integer.parseInt(ano));
           
           rs = pst.executeQuery();

       } catch (Exception e) {

           JOptionPane.showMessageDialog(null, e.getMessage());

       }

       return rs;
       
   }
   
   
    
}
