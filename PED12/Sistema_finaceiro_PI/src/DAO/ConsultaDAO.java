/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan
 */
public class ConsultaDAO {
    
    
   public static ResultSet carregaTabela(int id_conta) throws SQLException {
       
       Connection conexao = moduloConexao.conector();
       
       String consulta = "select * from cartao_credito where conta_id_conta=?";
       
       PreparedStatement pst = conexao.prepareStatement(consulta);
       
       pst.setInt(1, id_conta);
       
       ResultSet rs = pst.executeQuery();
       
       return rs;
       
   }
   
   public static ResultSet carregaTabela(String tipo, String arg, int id_conta) throws SQLException {
       
       Connection conexao = moduloConexao.conector();
       
       String argumento = "conta_id_conta = " + id_conta + " and " + tipo + " " + "like '" + arg + "%'";
       
       String consulta = "SELECT n_cartao_credito, limite, dia_fatura, valor_fatura, bandeira FROM cartao_credito WHERE " + argumento + "";
       
       PreparedStatement pst = conexao.prepareStatement(consulta);
       
       ResultSet rs = pst.executeQuery();
       
       return rs;
       
   }
    
}
