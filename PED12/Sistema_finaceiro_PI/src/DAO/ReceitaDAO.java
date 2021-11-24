/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.CartaoDebito;
import Model.Data;
import Model.Despesa;
import Model.Receita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public Receita GetUltimaReceita(int conta_id) throws SQLException{
        
        String SelectReceita = 
                            "SELECT Max(cod_receita) cod_receita, R.mes, R.ano FROM receita R \n"
                            + "WHERE R.conta_id_conta = ? AND R.cod_receita = (SELECT Max(cod_receita) FROM receita);";
        
        Receita receita = new Receita();
        
        try
        {
            PreparedStatement pst_SelectReceita = conexao.prepareStatement(SelectReceita);
            
            pst_SelectReceita.setInt(1, conta_id);
            
            ResultSet rs_SelectReceita = pst_SelectReceita.executeQuery();
            
            if(rs_SelectReceita.next()){
                receita.setCod_receita(rs_SelectReceita.getInt("cod_receita"));
                receita.setMes(rs_SelectReceita.getInt("mes"));
                receita.setAno(rs_SelectReceita.getInt("ano"));
                receita.setId_conta(conta_id);
            }
               
                  
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            JOptionPane.showMessageDialog(null, "Erro:GetUltimaReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
        
        return receita;
    }

    public void CadastrarReceita(Receita receita_nova){
        
        ReceitaDAO receitaDAO = new ReceitaDAO();
            
        DespesaDAO despesaDAO = new DespesaDAO();
        
        String insertReceita = "INSERT INTO receita (dia, mes, ano, conta_id_conta, total) VALUES(?, ?, ?, ?, ?);";
   
        PreparedStatement pst_InsertReceita = null;

        try {

           Receita ultima_receita = receitaDAO.GetUltimaReceita(receita_nova.getId_conta());   
           
            if (ultima_receita != null) {

                LinkedList<Despesa> lista_despesasNp = despesaDAO.GetListaDespesasNpUltimaReceita(ultima_receita);

                pst_InsertReceita = conexao.prepareStatement(insertReceita);

                pst_InsertReceita.setInt(1, receita_nova.getDia());
                pst_InsertReceita.setInt(2, receita_nova.getMes());
                pst_InsertReceita.setInt(3, receita_nova.getAno());
                pst_InsertReceita.setInt(4, receita_nova.getId_conta());
                pst_InsertReceita.setFloat(5, receita_nova.getTotal());

                pst_InsertReceita.executeUpdate();
                
                receita_nova = receitaDAO.GetUltimaReceita(receita_nova.getId_conta());

                despesaDAO.TransferirDespesasEntreReceitas(lista_despesasNp, receita_nova);

            }
            
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            JOptionPane.showMessageDialog(null, "ERRO:CadastrarReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
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

        } finally {

            pst.close();

        }
    }

    public boolean DeleteReceita(Receita receita) throws SQLException {

        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        ResultSet rs = null;

        String sql = "select cod_receita from (receita R join receita_data Rdt on R.receita_data_cod_receita = Rdt.cod_receita) where (conta_id_conta = ? and mes = ? and ano = ?)";
        try {

            pst = conexao.prepareStatement(sql);

            pst.setInt(1, receita.getId_conta());
            pst.setInt(2, receita.getMes());
            pst.setInt(3, receita.getAno());

            rs = pst.executeQuery();

            if (rs.next()) {

                receita.setCod_receita(rs.getInt(1));
                System.out.println("o cod da receita é " + receita.getCod_receita());

                String delete = "delete from receita_data where cod_receita = ?";

                pst1 = conexao.prepareStatement(delete);

                try {

                    pst1.setInt(1, receita.getCod_receita());
                    pst1.executeUpdate();

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null, e.getMessage());
                    return false;

                } finally {

                    pst1.close();
                }

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.toString());
            return false;

        } finally {

            pst.close();
        }

        return true;
    }

    public LinkedList<Receita> CarregaTabela_Receita(int id_conta) throws SQLException {

        String consulta = "SELECT re.total, re_da.dia, re_da.mes, re_da.ano FROM"
                + " receita_data re_da, receita re "
                + "WHERE re.conta_id_conta = ? and re.receita_data_cod_receita = re_da.cod_receita";

        ResultSet rs = null;

        PreparedStatement pst = conexao.prepareStatement(consulta);

        LinkedList<Receita> lista_receita = new LinkedList();

        try {

            pst.setInt(1, id_conta);

            rs = pst.executeQuery();

            while (rs.next()) {

                lista_receita.add(new Receita(
                        rs.getInt("dia"),
                        rs.getInt("mes"),
                        rs.getInt("ano"),
                        rs.getFloat("total"),
                        id_conta)
                );

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        } finally {

            pst.close();
        }

        return lista_receita;

    }

    public LinkedList<Receita> Consulta_Receita(String tipo, String arg, int id_conta, boolean ordenar) throws SQLException {

        String argumento = "";

        if (ordenar) {

            argumento = " and " + tipo + " " + "like '" + arg + "%'";

        } else {

            argumento = " and " + tipo + " " + "like '" + arg + "%'";
        }

        String consulta = "SELECT re.total, re_da.dia, re_da.mes, re_da.ano FROM"
                + " receita_data re_da, receita re "
                + "WHERE re.conta_id_conta = ? and re.receita_data_cod_receita = re_da.cod_receita"
                + argumento + "";

        ResultSet rs = null;

        PreparedStatement pst = conexao.prepareStatement(consulta);

        LinkedList<Receita> lista_receita = new LinkedList();

        try {

            pst.setInt(1, id_conta);

            rs = pst.executeQuery();

            while (rs.next()) {

                lista_receita.add(new Receita(
                        Integer.parseInt(rs.getString("dia")),
                        Integer.parseInt(rs.getString("mes")),
                        Integer.parseInt(rs.getString("ano")),
                        Float.parseFloat(rs.getString("total")),
                        id_conta)
                );

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        } finally {

            pst.close();
        }

        return lista_receita;

    }

    public LinkedList<Receita> PreencherCampos_Receita(String dia, String mes, String ano, String id_conta) throws SQLException {

        String consulta = "SELECT re.total, re_da.dia, re_da.mes, re_da.ano FROM"
                + " receita_data re_da, receita re "
                + "WHERE re.conta_id_conta = ? and re.receita_data_cod_receita = re_da.cod_receita "
                + "and re_da.dia = ? and re_da.mes = ? and re_da.ano = ?";

        PreparedStatement pst = conexao.prepareStatement(consulta);

        ResultSet rs = null;

        LinkedList<Receita> lista_receita = new LinkedList();

        try {

            pst.setInt(1, Integer.parseInt(id_conta));

            pst.setInt(2, Integer.parseInt(dia));

            pst.setInt(3, Integer.parseInt(mes));

            pst.setInt(4, Integer.parseInt(ano));

            rs = pst.executeQuery();

            while (rs.next()) {

                lista_receita.add(new Receita(
                        Integer.parseInt(rs.getString("dia")),
                        Integer.parseInt(rs.getString("mes")),
                        Integer.parseInt(rs.getString("ano")),
                        Float.parseFloat(rs.getString("total")),
                        Integer.parseInt(id_conta))
                );

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        } finally {

            pst.close();
        }

        return lista_receita;

    }

    public boolean ReceitaExiste(Receita receita) throws SQLException {

        //String consulta = "select * from receita_data where mes = ? and ano = ?";
        String consulta = "select * from receita_data rdt inner join receita R on (rdt.cod_receita = R.receita_data_cod_receita) where R.conta_id_conta = ? and mes = ? and ano = ?";

        PreparedStatement pst = conexao.prepareStatement(consulta);

        ResultSet rs = null;

        try {
            
            pst.setInt(1, receita.getId_conta());
            pst.setInt(2, receita.getMes());
            pst.setInt(3, receita.getAno());

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

}
