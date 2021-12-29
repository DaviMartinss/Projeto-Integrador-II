/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Despesa;
import Model.Receita;
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
public class ReceitaDAO {

    private Connection conexao = null;

    public ReceitaDAO() {

        conexao = moduloConexao.conector();

    }

    public boolean ReceitaTemSaldo(Receita receita, float valor) throws SQLException {

        String SelectSaldo = "SELECT total FROM receita WHERE conta_id_conta = ? AND mes = ? AND ano = ?; ";

        PreparedStatement pst_SelectSaldo = null;

        ResultSet rs_SelectSaldo = null;

        try {

            pst_SelectSaldo = conexao.prepareStatement(SelectSaldo);
            
            pst_SelectSaldo.setLong(1, receita.getId_conta());
            
            pst_SelectSaldo.setLong(2, receita.getMes());
            
            pst_SelectSaldo.setLong(3, receita.getAno());

            rs_SelectSaldo = pst_SelectSaldo.executeQuery();

            if (rs_SelectSaldo.next()) {

                if (valor <= rs_SelectSaldo.getFloat("total"))                  
                    return true;
                else
                    return false;

            }else
                return false;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            JOptionPane.showMessageDialog(null, "Erro:ReceitaTemSaldo", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;

        } finally {

            pst_SelectSaldo.close();
            rs_SelectSaldo.close();

        }

    }

    public boolean CadastrarReceita(Receita receita_nova) throws SQLException {

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

            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:CadastrarReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;

        } finally {

            pst_InsertReceita.close();

        }

    }

    public boolean UpdateReceita(Receita receita) throws SQLException {

        PreparedStatement pst_UpdateReceita = null;

        String UpdateReceita = "UPDATE receita SET total = ?, dia = ?, mes = ?, ano = ? WHERE conta_id_conta = ? AND mes = ? AND ano = ?;";

        try {

            pst_UpdateReceita = conexao.prepareStatement(UpdateReceita);

            pst_UpdateReceita.setFloat(1, receita.getTotal());
            pst_UpdateReceita.setInt(2, receita.getDia());
            pst_UpdateReceita.setInt(3, receita.getMes());
            pst_UpdateReceita.setInt(4, receita.getAno());
            pst_UpdateReceita.setInt(5, receita.getId_conta());
            pst_UpdateReceita.setInt(6, receita.getSalva_Mes());
            pst_UpdateReceita.setInt(7, receita.getSalva_ano());

            pst_UpdateReceita.executeUpdate();

            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:UpdateReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;

        } finally {

            pst_UpdateReceita.close();

        }
    }

    public boolean DeleteReceita(Receita receita) throws SQLException {

        String DeleteReceita = "DELETE FROM receita WHERE mes = ? AND ano = ? AND conta_id_conta = ?";

        PreparedStatement pst_DeleteReceita = null;

        try {

            pst_DeleteReceita = conexao.prepareStatement(DeleteReceita);

            pst_DeleteReceita.setInt(1, receita.getMes());
            pst_DeleteReceita.setInt(2, receita.getAno());
            pst_DeleteReceita.setInt(3, receita.getId_conta());

            pst_DeleteReceita.executeUpdate();

            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:DeleteReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;

        } finally {

            pst_DeleteReceita.close();
        }

    }

    public LinkedList<Receita> GetListaReceita(int id_conta) throws SQLException {

        String SelectListaReceitas = "SELECT re.total, re.dia, re.mes, re.ano FROM\n"
                                    + "receita re\n"
                                    + "WHERE re.conta_id_conta = ?;";

        ResultSet rs_SelectListaReceitas = null;

        PreparedStatement pst_SelectListaReceitas = conexao.prepareStatement(SelectListaReceitas);

        LinkedList<Receita> lista_receita = new LinkedList();

        try {

            pst_SelectListaReceitas.setInt(1, id_conta);

            rs_SelectListaReceitas = pst_SelectListaReceitas.executeQuery();

            while (rs_SelectListaReceitas.next()) {

                lista_receita.add(new Receita(
                        rs_SelectListaReceitas.getInt("dia"),
                        rs_SelectListaReceitas.getInt("mes"),
                        rs_SelectListaReceitas.getInt("ano"),
                        rs_SelectListaReceitas.getFloat("total"),
                        id_conta)
                );

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:GetListaReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

        } finally {

            pst_SelectListaReceitas.close();
            rs_SelectListaReceitas.close();
        }

        return lista_receita;

    }
    
    public Receita GetUltimaReceita(int conta_id) throws SQLException {

        String SelectReceita
                = "SELECT Max(cod_receita) cod_receita, R.mes, R.ano FROM receita R \n"
                + "WHERE R.conta_id_conta = ? AND R.cod_receita = (SELECT Max(cod_receita) FROM receita);";

        Receita receita = new Receita();

        PreparedStatement pst_SelectReceita = null;

        ResultSet rs_SelectReceita = null;

        try {
            pst_SelectReceita = conexao.prepareStatement(SelectReceita);

            pst_SelectReceita.setInt(1, conta_id);

            rs_SelectReceita = pst_SelectReceita.executeQuery();

            if (rs_SelectReceita.next()) {
                receita.setCod_receita(rs_SelectReceita.getInt("cod_receita"));
                receita.setMes(rs_SelectReceita.getInt("mes"));
                receita.setAno(rs_SelectReceita.getInt("ano"));
                receita.setId_conta(conta_id);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:GetUltimaReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

        } finally {

            pst_SelectReceita.close();
            rs_SelectReceita.close();

        }

        return receita;
    }
    
    public boolean UpdateTotalReceita(int id_conta, float valor, int cod_receita) throws SQLException {

        String UpdateTotal = "UPDATE receita SET total = (total - ?) WHERE conta_id_conta = ? AND cod_receita = ?";

        PreparedStatement pst_UpdateTotal = null;

        try {

            pst_UpdateTotal = conexao.prepareStatement(UpdateTotal);

            pst_UpdateTotal.setFloat(1, valor);
            pst_UpdateTotal.setInt(2, id_conta);
            pst_UpdateTotal.setInt(3, cod_receita);

            pst_UpdateTotal.executeUpdate();

            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:UpdateTotalReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;

        } finally {

            pst_UpdateTotal.close();

        }

    }
    
    public int GetCodigoReceita(int id_conta, int mes, int ano) throws SQLException {

        String SelectCodReceita
                = "SELECT cod_receita FROM receita WHERE conta_id_conta = ? AND mes = ? AND ano = ?;";

        PreparedStatement pst_SelectCodReceita = null;

        ResultSet rs_SelectCodReceita = null;

        try {
            pst_SelectCodReceita = conexao.prepareStatement(SelectCodReceita);

            pst_SelectCodReceita.setInt(1, id_conta);
            pst_SelectCodReceita.setInt(2, mes);
            pst_SelectCodReceita.setInt(3, ano);

            rs_SelectCodReceita = pst_SelectCodReceita.executeQuery();

            if (rs_SelectCodReceita.next()) 
                return rs_SelectCodReceita.getInt("cod_receita");
            else
                return 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:GetCodigoReceita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return 0;

        } finally {

            pst_SelectCodReceita.close();
            rs_SelectCodReceita.close();

        }

    }

    public LinkedList<Receita> Consulta_Receita(String tipo, String arg, int id_conta, boolean ordenar) throws SQLException {

        String argumento = "";

        if (ordenar) {

            argumento = " AND " + tipo + " " + "LIKE '" + arg + "%'";

        } else {

            argumento = " AND " + tipo + " " + "LIKE '" + arg + "%'";
        }

        String ConsultaReceita = "SELECT total, dia, mes, ano FROM receita WHERE conta_id_conta = ?"
                        + argumento + "";

        PreparedStatement pst_ConsultaReceita = null;
        
        ResultSet rs_ConsultaReceita = null;

        LinkedList<Receita> lista_receita = new LinkedList();

        try {
            
            pst_ConsultaReceita = conexao.prepareStatement(ConsultaReceita);

            pst_ConsultaReceita.setInt(1, id_conta);

            rs_ConsultaReceita = pst_ConsultaReceita.executeQuery();

            while (rs_ConsultaReceita.next()) {

                lista_receita.add(new Receita(
                        Integer.parseInt(rs_ConsultaReceita.getString("dia")),
                        Integer.parseInt(rs_ConsultaReceita.getString("mes")),
                        Integer.parseInt(rs_ConsultaReceita.getString("ano")),
                        Float.parseFloat(rs_ConsultaReceita.getString("total")),
                        id_conta)
                );

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:Consulta_Receita", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

        } finally {

            pst_ConsultaReceita.close();
            rs_ConsultaReceita.close();
        }

        return lista_receita;

    }

    public boolean ReceitaExiste(Receita receita) throws SQLException {

        String SelectReceitaExiste = "SELECT COUNT(*) count FROM receita WHERE conta_id_conta = ? AND mes = ? AND ano = ?;";

        PreparedStatement pst_SelectReceitaExiste = null;

        ResultSet rs_SelectReceitaExiste = null;

        try {

            pst_SelectReceitaExiste = conexao.prepareStatement(SelectReceitaExiste);

            pst_SelectReceitaExiste.setInt(1, receita.getId_conta());
            pst_SelectReceitaExiste.setInt(2, receita.getMes());
            pst_SelectReceitaExiste.setInt(3, receita.getAno());

            rs_SelectReceitaExiste = pst_SelectReceitaExiste.executeQuery();

            rs_SelectReceitaExiste.next();

            if (rs_SelectReceitaExiste.getInt("count") == 1) 
                return true;
            else 
                return false;
            

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:ReceitaExiste", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;

        } finally {

            pst_SelectReceitaExiste.close();
            rs_SelectReceitaExiste.close();
        }

    }

}
