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

    
    public boolean UpdateCreditoCartaoCredito(int id_conta, float valor, long num_cartao) throws SQLException {

        String UpdateCredito = "UPDATE cartao_credito SET \n"
                            + "credito = (credito - ?), \n"
                            + "valor_fatura = ( valor_fatura + ?) \n"
                            + "WHERE n_cartao_credito = ? AND conta_id_conta = ?;";
        

        PreparedStatement pst_UpdateCredito = null;

        try {

            pst_UpdateCredito = conexao.prepareStatement(UpdateCredito);

            pst_UpdateCredito.setFloat(1, valor);

            pst_UpdateCredito.setFloat(2, valor);

            pst_UpdateCredito.setLong(3, num_cartao);

            pst_UpdateCredito.setLong(4, id_conta);

            pst_UpdateCredito.executeUpdate();
            
            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:UpdateCreditoCartaoCredito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;

        } finally {

            pst_UpdateCredito.close();

        }

    }
    
    
    
    public boolean CadastrarCartaoCredito(CartaoCredito cartao_credito) throws SQLException {

        PreparedStatement pst = null;

        String insert = "insert into cartao_credito (n_cartao_credito, limite, credito, dia_fatura, valor_fatura, bandeira, conta_id_conta) values(?,?,?,?,?,?,?)";

        pst = conexao.prepareStatement(insert);

        try {

            pst.setLong(1, cartao_credito.getN_cartao_credito());
            pst.setFloat(2, (cartao_credito.getLimite() - cartao_credito.getValor_fatura()));
            pst.setFloat(3, cartao_credito.getLimite());
            pst.setInt(4, cartao_credito.getDia_fatura());
            pst.setFloat(5, cartao_credito.getValor_fatura());
            pst.setString(6, cartao_credito.getBandeira());
            pst.setFloat(7, cartao_credito.getId_conta());

            pst.executeUpdate();

            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        } finally {

            pst.close();

        }

    }

    public boolean DespesaCartaoExiste(long num_cartao) throws SQLException {

        String consulta = "select distinct num_cartao from despesa where num_cartao = ?";

        PreparedStatement pst = conexao.prepareStatement(consulta);

        ResultSet rs = null;

        try {

            pst.setLong(1, num_cartao);

            rs = pst.executeQuery();

            if (rs.next()) {

                return true;

            } else {

                return false;
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        }

    }

    public void DescontaCredito(CartaoCredito cartao_credito) {

        //Aqui desconta do limite do cartão de crédito se a dispesa for deste tipo
        String sql = "select limite from cartao_credito where n_cartao_credito = ? and conta_id_conta = ?";

        try {

            PreparedStatement pst1 = conexao.prepareStatement(sql);

            pst1.setLong(1, cartao_credito.getN_cartao_credito());

            pst1.setInt(2, cartao_credito.getId_conta());

            ResultSet consulta = pst1.executeQuery();

            if (consulta.next()) {

                float limite_antigo = consulta.getFloat("limite");
                
                float descontar = cartao_credito.getLimite() - limite_antigo;

                String sql2 = "update cartao_credito set credito = (credito + ?) where n_cartao_credito = ? and conta_id_conta = ?";

                PreparedStatement pst2 = conexao.prepareStatement(sql2);

                pst2.setFloat(1, (cartao_credito.getLimite() - limite_antigo));

                pst2.setLong(2, cartao_credito.getN_cartao_credito());

                pst2.setInt(3, cartao_credito.getId_conta());

                pst2.executeUpdate();

            };

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public boolean UpdateCartaoCredito(CartaoCredito cartao_credito) throws SQLException {

        if (DespesaCartaoExiste(cartao_credito.getN_cartao_aux())) {

            PreparedStatement pst = null;
            PreparedStatement pst2 = null;
            PreparedStatement pst3 = null;

            ResultSet rs = null;

            String update = "update cartao_credito LEFT OUTER JOIN  despesa on cartao_credito.n_cartao_credito = despesa.num_cartao set n_cartao_credito=?, limite=?, dia_fatura=?, valor_fatura=?, bandeira=?, num_cartao= ? where (n_cartao_credito= ?);";

            pst = conexao.prepareStatement(update);

            try {

                pst.setLong(1, cartao_credito.getN_cartao_credito());
                pst.setFloat(2, cartao_credito.getLimite());
                pst.setInt(3, cartao_credito.getDia_fatura());
                pst.setFloat(4, cartao_credito.getValor_fatura());
                pst.setString(5, cartao_credito.getBandeira());
                pst.setLong(6, cartao_credito.getN_cartao_credito());
                pst.setLong(7, cartao_credito.getN_cartao_aux());

                DescontaCredito(cartao_credito);
                
                pst.executeUpdate();
                
                return true;

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e.getMessage());

                return false;

            } finally {

                pst.close();

            }

        } else {

            PreparedStatement pst1 = null;

            String update = "update cartao_credito set n_cartao_credito=?, limite=?, dia_fatura=?, valor_fatura=?, bandeira=? where n_cartao_credito=?";

            pst1 = conexao.prepareStatement(update);

            try {

                pst1.setLong(1, cartao_credito.getN_cartao_credito());
                pst1.setFloat(2, cartao_credito.getLimite());
                pst1.setInt(3, cartao_credito.getDia_fatura());
                pst1.setFloat(4, cartao_credito.getValor_fatura());
                pst1.setString(5, cartao_credito.getBandeira());
                pst1.setLong(6, cartao_credito.getN_cartao_aux());

                DescontaCredito(cartao_credito);
                
                pst1.executeUpdate();

                return true;

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e.getMessage());

                return false;

            } finally {

                pst1.close();

            }

        }
    }

    public boolean DeleteCartaoCredito(CartaoCredito cartao_credito) throws SQLException {

        PreparedStatement pst = null;

        String update = "delete from cartao_credito where n_cartao_credito = ?";

        pst = conexao.prepareStatement(update);

        try {

            pst.setLong(1, cartao_credito.getN_cartao_credito());

            pst.executeUpdate();

            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        } finally {

            pst.close();

        }
    }

    public LinkedList<CartaoCredito> CarregaTabela_Cartao_C(int id_conta) throws SQLException {

        String consulta = "select * from cartao_credito where conta_id_conta=?";

        ResultSet rs = null;

        LinkedList<CartaoCredito> lista_CC = new LinkedList();

        PreparedStatement pst = conexao.prepareStatement(consulta);

        try {

            pst.setInt(1, id_conta);

            rs = pst.executeQuery();

            while (rs.next()) {

                lista_CC.add(new CartaoCredito(
                        Long.parseLong(rs.getString("n_cartao_credito")),
                        Float.parseFloat(rs.getString("limite")),
                        Float.parseFloat(rs.getString("credito")),
                        Integer.parseInt(rs.getString("dia_fatura")),
                        Float.parseFloat(rs.getString("valor_fatura")),
                        rs.getString("bandeira"),
                        id_conta)
                );

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        } finally {

            pst.close();
        }

        return lista_CC;

    }

    public LinkedList<CartaoCredito> Carrega_Faturas(int id_conta) throws SQLException {

        String consulta = "SELECT   \n"
                + "CC.n_cartao_credito,\n"
                + "CC.dia_fatura,\n"
                + "CC.valor_fatura\n"
                + "CC.bandeira\n"
                + "FROM cartao_credito CC \n"
                + "where CC.conta_id_conta = 1;";

        ResultSet rs = null;

        LinkedList<CartaoCredito> listaCC_Faturas = new LinkedList();

        PreparedStatement pst = conexao.prepareStatement(consulta);

        try {

            pst.setInt(1, id_conta);

            rs = pst.executeQuery();

            while (rs.next()) {

                listaCC_Faturas.add(new CartaoCredito(
                        Long.parseLong(rs.getString("n_cartao_credito")),
                        Integer.parseInt(rs.getString("dia_fatura")),
                        Float.parseFloat(rs.getString("valor_fatura")),
                        rs.getString("bandeira"),
                        id_conta)
                );

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        } finally {

            pst.close();
        }

        return listaCC_Faturas;

    }

    public LinkedList<CartaoCredito> ConsultaCartao_C(String tipo, String arg, int id_conta, boolean ordenar) throws SQLException {

        String argumento = "";

        if (ordenar) {

            argumento = "conta_id_conta = " + id_conta + " and " + tipo + " " + "like '" + arg + "%'" + " ORDER BY " + tipo + " ASC";

        } else {

            argumento = "conta_id_conta = " + id_conta + " and " + tipo + " " + "like '" + arg + "%'" + " ORDER BY " + tipo + " DESC";
        }

        String consulta = "SELECT n_cartao_credito, limite, credito, dia_fatura, valor_fatura, bandeira FROM cartao_credito WHERE " + argumento + "";

        ResultSet rs = null;

        PreparedStatement pst = conexao.prepareStatement(consulta);

        LinkedList<CartaoCredito> lista_CC = new LinkedList();

        try {

            rs = pst.executeQuery();

            while (rs.next()) {

                lista_CC.add(new CartaoCredito(
                        Long.parseLong(rs.getString("n_cartao_credito")),
                        Float.parseFloat(rs.getString("limite")),
                        Float.parseFloat(rs.getString("credito")),
                        Integer.parseInt(rs.getString("dia_fatura")),
                        Float.parseFloat(rs.getString("valor_fatura")),
                        rs.getString("bandeira"),
                        id_conta)
                );

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        } finally {

            pst.close();
        }

        return lista_CC;

    }

    public boolean CartaoCreditoExiste(CartaoCredito cartao) throws SQLException {

        String consulta = "select n_cartao_credito from cartao_credito where n_cartao_credito = ? and conta_id_conta =?";

        PreparedStatement pst = conexao.prepareStatement(consulta);

        ResultSet rs = null;

        try {

            pst.setLong(1, cartao.getN_cartao_credito());

            pst.setInt(2, cartao.getId_conta());

            rs = pst.executeQuery();

            if (rs.next()) {

                return true;

            } else {

                return false;
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        }
    }
    
    public CartaoCredito GetCartaoCredito(long numCartao, int id_conta) throws SQLException {

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

        PreparedStatement pst = conexao.prepareStatement(consulta);

        ResultSet rs = null;
        
        CartaoCredito cartao = new CartaoCredito();

        try {

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

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:GetCartaoCredito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return null;
        }
    }
}
