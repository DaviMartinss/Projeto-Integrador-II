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

/**
 *
 * @author Alan
 */
public class ConsultaDAO {
    
    
   public static ResultSet carregaTabela() throws SQLException {
       
       Connection conexao = moduloConexao.conector();
       
       String consulta = "SELECT n_cartao_credito, limite, dia_fatura, valor_fatura, bandeira FROM cartao_credito";
       
       PreparedStatement pst = conexao.prepareStatement(consulta);
       
       ResultSet rs = pst.executeQuery();
       
       return rs;
       
   }
   
   public static ResultSet carregaTabela(String tipo, String arg) throws SQLException {
       
       Connection conexao = moduloConexao.conector();
       
       String argumento = tipo + " " +"like '" + arg + "%'";
       
       String consulta = "SELECT n_cartao_credito, limite, dia_fatura, valor_fatura, bandeira FROM cartao_credito where " + argumento +"";
       
       PreparedStatement pst = conexao.prepareStatement(consulta);
       
       ResultSet rs = pst.executeQuery();
       
       return rs;
       
   }
    
}
