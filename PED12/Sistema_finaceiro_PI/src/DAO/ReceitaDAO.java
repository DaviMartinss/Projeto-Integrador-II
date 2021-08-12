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
    
}
