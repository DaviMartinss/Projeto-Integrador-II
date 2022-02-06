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
import java.util.LinkedList;

/**
 *
 * @author Alan
 */
public class CartaoDebitoDAO {
    
    
    private Connection conexao = null;

    public CartaoDebitoDAO() {
        
        conexao = moduloConexao.conector();
    
    }
    
   public void InsertCartaoDebito(CartaoDebito cartaoDB) throws SQLException {
   
        PreparedStatement pst;

        String insert = "INSERT INTO cartao_debito (n_cartao_debito,valor_atual, bandeira, conta_id_conta) VALUES(?,?, ?, ?)";

        pst = conexao.prepareStatement(insert);

        pst.setLong(1, cartaoDB.getN_cartao_debito());
        pst.setFloat(2, cartaoDB.getValor_atual());
        pst.setString(3, cartaoDB.getBandeira());
        pst.setFloat(4, cartaoDB.getId_conta());

        pst.executeUpdate();

        pst.close();
   }
        
        
    public boolean DespesaCartaoExiste(long num_cartao) throws SQLException {

        PreparedStatement pst;
        ResultSet rs;

        String consulta = "SELECT num_cartao_debito FROM despesa WHERE num_cartao_debito = ?";

        pst = conexao.prepareStatement(consulta);

        pst.setLong(1, num_cartao);

        rs = pst.executeQuery();

        return rs.next();
    }
   
    public void UpdateCartaoDebito(CartaoDebito cartao_debito) throws SQLException {

        PreparedStatement pst;

        String update = "UPDATE cartao_debito SET n_cartao_debito=?, valor_atual= ?, bandeira=? WHERE n_cartao_debito=?";

        pst = conexao.prepareStatement(update);

        pst.setLong(1, cartao_debito.getN_cartao_debito());
        pst.setFloat(2, cartao_debito.getValor_atual());
        pst.setString(3, cartao_debito.getBandeira());
        pst.setLong(4, cartao_debito.getN_cartao_aux());

        pst.executeUpdate();

        pst.close();
    }

    public void DeleteCartaoDebito(CartaoDebito cartao_debito) throws SQLException {

        PreparedStatement pst;

        String update = "DELETE FROM cartao_debito WHERE n_cartao_debito = ?";

        pst = conexao.prepareStatement(update);

        pst.setLong(1, cartao_debito.getN_cartao_debito());

        pst.executeUpdate();

        pst.close();
    }
    
    public void UpdateValorAtualCartaoDebito(int id_conta, float valor, long num_cartao) throws SQLException {
        
        PreparedStatement pst_UpdateValorAtual;

        String UpdateValorAtual = "UPDATE cartao_debito SET \n"
                                    + "valor_atual = (valor_atual - ?) \n"
                                    + "WHERE n_cartao_debito = ? AND conta_id_conta = ?;";

        pst_UpdateValorAtual = conexao.prepareStatement(UpdateValorAtual);

        pst_UpdateValorAtual.setFloat(1, valor);

        pst_UpdateValorAtual.setLong(2, num_cartao);

        pst_UpdateValorAtual.setInt(3, id_conta);

        pst_UpdateValorAtual.executeUpdate();

        pst_UpdateValorAtual.close();
    }
   
   public LinkedList<CartaoDebito> GetListaCartaoDebito(int id_conta) throws SQLException {
       
       LinkedList<CartaoDebito> lista_CD = new LinkedList();
       
       PreparedStatement pst;
       ResultSet rs;
       
       String consulta = "SELECT * FROM cartao_debito WHERE conta_id_conta=?";
       
       pst = conexao.prepareStatement(consulta);
       
       pst.setInt(1, id_conta);

       rs = pst.executeQuery();

       while (rs.next()) {

           lista_CD.add(new CartaoDebito(
                   Long.parseLong(rs.getString("n_cartao_debito")),
                   Float.parseFloat(rs.getString("valor_atual")),
                   rs.getString("bandeira"),
                   id_conta)
           );
       }

       pst.close();
       rs.close();

       return lista_CD;
   }
   
   public LinkedList<CartaoDebito> ConsultaCartaoDebito(String tipo, String arg, int id_conta, boolean ordenar) throws SQLException {
       
       LinkedList<CartaoDebito> lista_CD = new LinkedList();
       
       PreparedStatement pst;
       ResultSet rs;
       
       String argumento = "";
       
       if(ordenar){
           
           argumento = "conta_id_conta = " + id_conta + " AND " + tipo + " " + "LIKE '" + arg + "%'" + " ORDER BY " + tipo + " ASC";
           
       }else{
           
           argumento = "conta_id_conta = " + id_conta + " AND " + tipo + " " + "LIKE '" + arg + "%'" + " ORDER BY " + tipo + " DESC";
       }

       String consulta = "SELECT n_cartao_debito, valor_atual, bandeira FROM cartao_debito WHERE " + argumento + "";
       
        pst = conexao.prepareStatement(consulta);

        rs = pst.executeQuery();

        while (rs.next()) {

            lista_CD.add(new CartaoDebito(
                    Long.parseLong(rs.getString("n_cartao_debito")),
                    Float.parseFloat(rs.getString("valor_atual")),
                    rs.getString("bandeira"),
                    id_conta)
            );
        }

        pst.close();
        rs.close();

        return lista_CD;
    }
   
   public boolean CartaoDebitoExiste(CartaoDebito cartao) throws SQLException{
       
       PreparedStatement pst;
       ResultSet rs;
       
       String consulta = "SELECT n_cartao_debito FROM cartao_debito WHERE n_cartao_debito = ? AND conta_id_conta =?";
       
       pst = conexao.prepareStatement(consulta);
       
       pst.setLong(1, cartao.getN_cartao_debito());

       pst.setInt(2, cartao.getId_conta());

       rs = pst.executeQuery();
       
       pst.close();
       rs.close();

       return rs.next();
   }
   
   public CartaoDebito GetCartaoDebito(long numCartao, int id_conta) throws SQLException {

        PreparedStatement pst;
        ResultSet rs;

        CartaoDebito cartao = null;

        String consulta = "SELECT \n"
                        + "n_cartao_debito,\n"
                        + "valor_atual,\n"
                        + "bandeira,\n"
                        + "conta_id_conta\n"
                        + "FROM cartao_debito\n"
                        + "WHERE n_cartao_debito = ? AND conta_id_conta = ?";

        pst = conexao.prepareStatement(consulta);

        pst.setLong(1, numCartao);

        pst.setInt(2, id_conta);

        rs = pst.executeQuery();

        if (rs.next()) {
            
            cartao = new CartaoDebito();

            cartao.setId_conta(rs.getInt("conta_id_conta"));
            cartao.setN_cartao_debito(rs.getLong("n_cartao_debito"));
            cartao.setValor_atual(rs.getFloat("valor_atual"));
            cartao.setBandeira(rs.getString("bandeira"));
        }

        return cartao;
    }
    
}
