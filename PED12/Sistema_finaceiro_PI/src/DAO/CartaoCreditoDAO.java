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
import java.sql.SQLException;
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
        
    
    
    
}
