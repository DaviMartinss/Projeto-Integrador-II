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
    
    public boolean CadastrarCartaoDebito(CartaoDebito cartao_db) throws SQLException{
        
        PreparedStatement pst = null;
        
        String insert = "insert into cartao_debito (n_cartao_debito,valor_atual, bandeira, conta_id_conta) values(?,?, ?, ?)";
        
        pst = conexao.prepareStatement(insert);
        
        try {

            pst.setLong(1, cartao_db.getN_cartao_debito());
            pst.setFloat(2, cartao_db.getValor_atual());
            pst.setString(3, cartao_db.getBandeira());
            pst.setInt(4, cartao_db.getId_conta());
            
            pst.executeUpdate();
            
            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        }finally{
            
            pst.close();
            
        }
        
    }
    
}
