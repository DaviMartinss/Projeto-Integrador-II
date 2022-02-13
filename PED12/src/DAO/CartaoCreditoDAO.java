/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.CartaoCredito;
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

    public void UpdateCreditoFaturaCartaoCredito(int id_conta, float valor, long num_cartao) throws SQLException {
        
        PreparedStatement pst_UpdateCreditoFatura;

        String UpdateCreditoFatura = "UPDATE cartao_credito SET \n"
                                    + "credito = (credito - ?), \n"
                                    + "valor_fatura = ( valor_fatura + ?) \n"
                                    + "WHERE n_cartao_credito = ? AND conta_id_conta = ?;";

        pst_UpdateCreditoFatura = conexao.prepareStatement(UpdateCreditoFatura);

        pst_UpdateCreditoFatura.setFloat(1, valor);

        pst_UpdateCreditoFatura.setFloat(2, valor);

        pst_UpdateCreditoFatura.setLong(3, num_cartao);

        pst_UpdateCreditoFatura.setLong(4, id_conta);

        pst_UpdateCreditoFatura.executeUpdate();

        pst_UpdateCreditoFatura.close();
    }
    
    public void InsertCartaoCredito(CartaoCredito cartao_credito) throws SQLException, NullPointerException {

        PreparedStatement pst_CadastrarCC;

        String insert = "INSERT INTO cartao_credito (n_cartao_credito, limite, credito, dia_fatura, valor_fatura, bandeira, conta_id_conta) VALUES(?,?,?,?,?,?,?)";

        pst_CadastrarCC = conexao.prepareStatement(insert);

        pst_CadastrarCC.setLong(1, cartao_credito.getN_cartao_credito());
        pst_CadastrarCC.setFloat(2, (cartao_credito.getLimite() - cartao_credito.getValor_fatura()));
        pst_CadastrarCC.setFloat(3, cartao_credito.getLimite());
        pst_CadastrarCC.setInt(4, cartao_credito.getDia_fatura());
        pst_CadastrarCC.setFloat(5, cartao_credito.getValor_fatura());
        pst_CadastrarCC.setString(6, cartao_credito.getBandeira());
        pst_CadastrarCC.setFloat(7, cartao_credito.getId_conta());

        pst_CadastrarCC.executeUpdate();

        pst_CadastrarCC.close();
    }
    
    public boolean DespesaNpCartaoExiste(long num_cartao) throws SQLException {

        String consulta = "SELECT num_cartao_credito FROM despesa WHERE num_cartao_credito = ? AND estatus='NÃO PAGO'";

        PreparedStatement pst = conexao.prepareStatement(consulta);

        ResultSet rs;

        pst.setLong(1, num_cartao);

        rs = pst.executeQuery();

        if (rs.next()) {

            pst.close();
            rs.close();

            return true;

        } else {

            pst.close();
            rs.close();

            return false;
        }
    }
    
    public void UpdateCartaoCredito(CartaoCredito cartao_credito) throws SQLException, NullPointerException {

        PreparedStatement pstUpdate;

        String update = "UPDATE cartao_credito SET n_cartao_credito=?, limite=?, dia_fatura=?, valor_fatura=?, bandeira=?, credito=? WHERE (n_cartao_credito= ?);";

        pstUpdate = conexao.prepareStatement(update);

        pstUpdate.setLong(1, cartao_credito.getN_cartao_credito());
        pstUpdate.setFloat(2, cartao_credito.getLimite());
        pstUpdate.setInt(3, cartao_credito.getDia_fatura());
        pstUpdate.setFloat(4, cartao_credito.getValor_fatura());
        pstUpdate.setString(5, cartao_credito.getBandeira());
        pstUpdate.setFloat(6, cartao_credito.getCredito());
        pstUpdate.setLong(7, cartao_credito.getN_cartao_aux());

        pstUpdate.executeUpdate();

        pstUpdate.close();
    }

    public void DeleteCartaoCredito(CartaoCredito cartao_credito) throws SQLException, NullPointerException {

        PreparedStatement pst_DeleteCC;

        String deleteCC = "DELETE FROM cartao_credito WHERE n_cartao_credito = ?";

        pst_DeleteCC = conexao.prepareStatement(deleteCC);

        pst_DeleteCC.setLong(1, cartao_credito.getN_cartao_credito());

        pst_DeleteCC.executeUpdate();

        pst_DeleteCC.close();

    }

    public LinkedList<CartaoCredito> GetListaCartaoCredito(int id_conta) throws SQLException, NullPointerException {

        ResultSet rs_ListaCartaoCredito;
        PreparedStatement pst_ListaCartaoCredito;

        String consulta = "SELECT * FROM cartao_credito WHERE conta_id_conta=?";

        LinkedList<CartaoCredito> lista_CC = new LinkedList();

        pst_ListaCartaoCredito = conexao.prepareStatement(consulta);

        pst_ListaCartaoCredito.setInt(1, id_conta);

        rs_ListaCartaoCredito = pst_ListaCartaoCredito.executeQuery();

        while (rs_ListaCartaoCredito.next()) {

             lista_CC.add
            (new CartaoCredito.CartaoCreditoBuild(
                        Long.parseLong(rs_ListaCartaoCredito.getString("n_cartao_credito")))
                        .Limite(Float.parseFloat(rs_ListaCartaoCredito.getString("limite")))
                        .Credito(Float.parseFloat(rs_ListaCartaoCredito.getString("credito")))
                        .DiaFatura(Integer.parseInt(rs_ListaCartaoCredito.getString("dia_fatura")))
                        .ValorFatura(Float.parseFloat(rs_ListaCartaoCredito.getString("valor_fatura")))
                        .Bandeira(rs_ListaCartaoCredito.getString("bandeira"))
                        .IdConta(id_conta)
                        .build()
            );
        }

        pst_ListaCartaoCredito.close();
        rs_ListaCartaoCredito.close();

        return lista_CC;
    }

    public LinkedList<CartaoCredito> Carrega_Faturas(int id_conta) throws SQLException, NumberFormatException {

        String consulta = "SELECT   \n"
                + "CC.n_cartao_credito,\n"
                + "CC.dia_fatura,\n"
                + "CC.valor_fatura\n"
                + "CC.bandeira\n"
                + "FROM cartao_credito CC \n"
                + "where CC.conta_id_conta = 1;";

        LinkedList<CartaoCredito> listaCC_Faturas = new LinkedList();

        PreparedStatement pst = conexao.prepareStatement(consulta);

        pst.setInt(1, id_conta);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            listaCC_Faturas.add(new CartaoCredito.CartaoCreditoBuild(
                    Long.parseLong(rs.getString("n_cartao_credito")))
                    .DiaFatura(Integer.parseInt(rs.getString("dia_fatura")))
                    .ValorFatura(Float.parseFloat(rs.getString("valor_fatura")))
                    .Bandeira(rs.getString("bandeira"))
                    .IdConta(id_conta)
                    .build()
            );
        }

        pst.close();
        rs.close();

        return listaCC_Faturas;
    }
    
    public LinkedList<CartaoCredito> ConsultaCartaoCredito(String tipo, String arg, int id_conta, boolean ordenar) throws SQLException, NumberFormatException {

        PreparedStatement pst_consulta;
        ResultSet rs_consulta;

        String argumento = "";

        if (ordenar) {

            argumento = "conta_id_conta = " + id_conta + " AND " + tipo + " " + "LIKE '" + arg + "%'" + " ORDER BY " + tipo + " ASC";

        } else {

            argumento = "conta_id_conta = " + id_conta + " AND " + tipo + " " + "LIKE '" + arg + "%'" + " ORDER BY " + tipo + " DESC";
        }

        String consulta = "SELECT n_cartao_credito, limite, credito, dia_fatura, valor_fatura, bandeira FROM cartao_credito WHERE " + argumento + "";

        pst_consulta = conexao.prepareStatement(consulta);

        LinkedList<CartaoCredito> lista_CC = new LinkedList();

        rs_consulta = pst_consulta.executeQuery();

        while (rs_consulta.next()) {
           
            lista_CC.add
            (new CartaoCredito.CartaoCreditoBuild(
                    Long.parseLong(rs_consulta.getString("n_cartao_credito")))
                    .Limite(Float.parseFloat(rs_consulta.getString("limite")))
                    .Credito(Float.parseFloat(rs_consulta.getString("credito")))
                    .DiaFatura(Integer.parseInt(rs_consulta.getString("dia_fatura")))
                    .ValorFatura(Float.parseFloat(rs_consulta.getString("valor_fatura")))
                    .Bandeira(rs_consulta.getString("bandeira"))
                    .IdConta(id_conta)
                    .build()

            );
        }

        pst_consulta.close();
        rs_consulta.close();

        return lista_CC;
    }
    
    public boolean CartaoCreditoExiste(CartaoCredito cartao) throws SQLException, NullPointerException {

        PreparedStatement pst;
        ResultSet rs;

        String consulta = "SELECT n_cartao_credito FROM cartao_credito WHERE n_cartao_credito = ? AND conta_id_conta =?";

        pst = conexao.prepareStatement(consulta);

        pst.setLong(1, cartao.getN_cartao_credito());

        pst.setInt(2, cartao.getId_conta());

        rs = pst.executeQuery();

        if (rs.next()) {

            return true;

        } else {

            return false;
        }
    }
    
    public CartaoCredito GetCartaoCredito(long numCartao, int id_conta) throws SQLException {

        PreparedStatement pst;
        ResultSet rs;

         CartaoCredito cartao = new CartaoCredito.CartaoCreditoBuild().build();

        String consulta = "SELECT \n"
                        + "n_cartao_credito,\n"
                        + "limite,\n"
                        + "credito,\n"
                        + "dia_fatura,\n"
                        + "valor_fatura,\n"
                        + "bandeira,\n"
                        + "conta_id_conta\n"
                        + "FROM cartao_credito\n"
                        + "WHERE n_cartao_credito = ? AND conta_id_conta = ?";

        pst = conexao.prepareStatement(consulta);

        pst.setLong(1, numCartao);

        pst.setInt(2, id_conta);

        rs = pst.executeQuery();

        if (rs.next()) {

            cartao.setId_conta(rs.getInt("conta_id_conta"));
            cartao.setDia_fatura(rs.getInt("dia_fatura"));
            cartao.setLimite(rs.getFloat("limite"));
            cartao.setCredito(rs.getFloat("credito"));
            cartao.setValor_fatura(rs.getFloat("valor_fatura"));
            cartao.setBandeira(rs.getString("bandeira"));
        }

        return cartao;
    }
}