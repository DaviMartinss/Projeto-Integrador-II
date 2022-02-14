/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Receita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Alan
 */
public class ReceitaDAO {

    private Connection conexao = null;

    public ReceitaDAO() {
        conexao = moduloConexao.conector();
    }
    
    public float GetReceitaSaldo(Receita receita, float valor) throws SQLException, NullPointerException {

        PreparedStatement pst_SelectSaldo;
        ResultSet rs_SelectSaldo;
        float saldo = 0;

        String SelectSaldo = "SELECT total FROM receita WHERE conta_id_conta = ? AND mes = ? AND ano = ?; ";

        pst_SelectSaldo = conexao.prepareStatement(SelectSaldo);

        pst_SelectSaldo.setLong(1, receita.getId_conta());

        pst_SelectSaldo.setLong(2, receita.getMes());

        pst_SelectSaldo.setLong(3, receita.getAno());

        rs_SelectSaldo = pst_SelectSaldo.executeQuery();

        if (rs_SelectSaldo.next()) {
            saldo = rs_SelectSaldo.getFloat("total");
        }

        pst_SelectSaldo.close();
        rs_SelectSaldo.close();

        return saldo;
    }

    public void InsertReceita(Receita receita_nova) throws SQLException, NullPointerException {

        PreparedStatement pst_InsertReceita = null;

        String insertReceita = "INSERT INTO receita (dia, mes, ano, conta_id_conta, total) VALUES(?, ?, ?, ?, ?);";

        pst_InsertReceita = conexao.prepareStatement(insertReceita);

        pst_InsertReceita.setInt(1, receita_nova.getDia());
        pst_InsertReceita.setInt(2, receita_nova.getMes());
        pst_InsertReceita.setInt(3, receita_nova.getAno());
        pst_InsertReceita.setInt(4, receita_nova.getId_conta());
        pst_InsertReceita.setFloat(5, receita_nova.getTotal());

        pst_InsertReceita.executeUpdate();

        pst_InsertReceita.close();
    }
    
    
    public void UpdateReceita(Receita receita) throws SQLException, NullPointerException {

        PreparedStatement pst_UpdateReceita = null;

        String UpdateReceita = "UPDATE receita SET total = ?, dia = ?, mes = ?, ano = ? WHERE conta_id_conta = ? AND mes = ? AND ano = ?;";

        pst_UpdateReceita = conexao.prepareStatement(UpdateReceita);

        pst_UpdateReceita.setFloat(1, receita.getTotal());
        pst_UpdateReceita.setInt(2, receita.getDia());
        pst_UpdateReceita.setInt(3, receita.getMes());
        pst_UpdateReceita.setInt(4, receita.getAno());
        pst_UpdateReceita.setInt(5, receita.getId_conta());
        pst_UpdateReceita.setInt(6, receita.getSalva_Mes());
        pst_UpdateReceita.setInt(7, receita.getSalva_ano());

        pst_UpdateReceita.executeUpdate();

        pst_UpdateReceita.close();

    }
    
    public void DeleteReceita(Receita receita) throws SQLException, NullPointerException {
        
        PreparedStatement pst_DeleteReceita;

        String DeleteReceita = "DELETE FROM receita WHERE mes = ? AND ano = ? AND conta_id_conta = ?";

        pst_DeleteReceita = conexao.prepareStatement(DeleteReceita);

        pst_DeleteReceita.setInt(1, receita.getMes());
        pst_DeleteReceita.setInt(2, receita.getAno());
        pst_DeleteReceita.setInt(3, receita.getId_conta());

        pst_DeleteReceita.executeUpdate();

        pst_DeleteReceita.close();
    }
    
    public LinkedList<Receita> GetListaReceita(int id_conta) throws SQLException {

        String SelectListaReceitas = "SELECT re.total, re.dia, re.mes, re.ano FROM\n"
                                    + "receita re\n"
                                    + "WHERE re.conta_id_conta = ?;";

        ResultSet rs_SelectListaReceitas = null;

        PreparedStatement pst_SelectListaReceitas = conexao.prepareStatement(SelectListaReceitas);

        LinkedList<Receita> lista_receita = new LinkedList<>();

        pst_SelectListaReceitas.setInt(1, id_conta);

        rs_SelectListaReceitas = pst_SelectListaReceitas.executeQuery();

        while (rs_SelectListaReceitas.next()) {
                      
            lista_receita.add(
                    new Receita.ReceitaBuild(
                        id_conta, rs_SelectListaReceitas.getInt("mes"), 
                        rs_SelectListaReceitas.getInt("ano"))
                        .Dia(rs_SelectListaReceitas.getInt("dia"))
                        .Total(rs_SelectListaReceitas.getFloat("total"))
                        .build()
            );
        }

        pst_SelectListaReceitas.close();
        rs_SelectListaReceitas.close();

        return lista_receita;
    }

    public Receita GetUltimaReceita(int conta_id) throws SQLException {

        String SelectReceita
                = "SELECT Max(cod_receita) cod_receita, R.mes, R.ano, R.total FROM receita R \n"
                + "WHERE R.conta_id_conta = ? AND "
                + "R.cod_receita = (SELECT Max(cod_receita) FROM receita WHERE receita.conta_id_conta = ?);";

         Receita receita = new Receita.ReceitaBuild().build();

        PreparedStatement pst_SelectReceita = null;

        ResultSet rs_SelectReceita = null;

        pst_SelectReceita = conexao.prepareStatement(SelectReceita);

        pst_SelectReceita.setInt(1, conta_id);
        pst_SelectReceita.setInt(2, conta_id);

        rs_SelectReceita = pst_SelectReceita.executeQuery();

        if (rs_SelectReceita.next()) {
            receita.setCod_receita(rs_SelectReceita.getInt("cod_receita"));
            receita.setMes(rs_SelectReceita.getInt("mes"));
            receita.setAno(rs_SelectReceita.getInt("ano"));
            receita.setTotal(rs_SelectReceita.getFloat("total"));
            receita.setId_conta(conta_id);
        }

        pst_SelectReceita.close();
        rs_SelectReceita.close();

        return receita;
    }
    
    public Receita GetReceitaAtual(int conta_id) throws SQLException {

        String SelectReceita
                = "SELECT Max(cod_receita) cod_receita, R.mes, R.ano, R.total FROM receita R \n"
                + "WHERE R.conta_id_conta = ? AND \n"
                + "R.ano = (SELECT Max(ano) FROM receita WHERE receita.conta_id_conta = ?) AND\n"
                + "R.mes = \n"
                + "(SELECT Max(mes) FROM receita WHERE receita.conta_id_conta = ? AND \n"
                + "receita.ano = (SELECT Max(ano) FROM receita WHERE receita.conta_id_conta = ?));";

        Receita receita = new Receita.ReceitaBuild().build();

        PreparedStatement pst_SelectReceita = null;

        ResultSet rs_SelectReceita = null;

        pst_SelectReceita = conexao.prepareStatement(SelectReceita);

        pst_SelectReceita.setInt(1, conta_id);
        pst_SelectReceita.setInt(2, conta_id);
        pst_SelectReceita.setInt(3, conta_id);
        pst_SelectReceita.setInt(4, conta_id);

        rs_SelectReceita = pst_SelectReceita.executeQuery();

        if (rs_SelectReceita.next()) {
            receita.setCod_receita(rs_SelectReceita.getInt("cod_receita"));
            receita.setMes(rs_SelectReceita.getInt("mes"));
            receita.setAno(rs_SelectReceita.getInt("ano"));
            receita.setTotal(rs_SelectReceita.getFloat("total"));
            receita.setId_conta(conta_id);
        }

        pst_SelectReceita.close();
        rs_SelectReceita.close();

        return receita;
    }
    
    public void UpdateTotalReceita(int id_conta, float valor, int cod_receita) throws SQLException {

        String UpdateTotal = "UPDATE receita SET total = (total - ?) "
                           + "WHERE conta_id_conta = ? AND cod_receita = ?";

        PreparedStatement pst_UpdateTotal = null;

        pst_UpdateTotal = conexao.prepareStatement(UpdateTotal);

        pst_UpdateTotal.setFloat(1, valor);
        pst_UpdateTotal.setInt(2, id_conta);
        pst_UpdateTotal.setInt(3, cod_receita);

        pst_UpdateTotal.executeUpdate();

        pst_UpdateTotal.close();
    }
    
    public int GetCodigoReceita(int id_conta, int mes, int ano) throws SQLException {

        String SelectCodReceita
                = "SELECT cod_receita FROM receita WHERE conta_id_conta = ? AND mes = ? AND ano = ?;";

        PreparedStatement pst_SelectCodReceita = null;

        ResultSet rs_SelectCodReceita = null;

        int cod_receita = 0;

        pst_SelectCodReceita = conexao.prepareStatement(SelectCodReceita);

        pst_SelectCodReceita.setInt(1, id_conta);
        pst_SelectCodReceita.setInt(2, mes);
        pst_SelectCodReceita.setInt(3, ano);

        rs_SelectCodReceita = pst_SelectCodReceita.executeQuery();

        if (rs_SelectCodReceita.next()) {
            cod_receita = rs_SelectCodReceita.getInt("cod_receita");
        }
        //else
        //DISPARAR EXCEPTION;

        pst_SelectCodReceita.close();
        rs_SelectCodReceita.close();

        return cod_receita;
    }
 
    public LinkedList<Receita> ConsultaReceita(String tipo, String arg, int id_conta, boolean ordenar) throws SQLException, NumberFormatException {

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

        LinkedList<Receita> lista_receita = new LinkedList<>();

        pst_ConsultaReceita = conexao.prepareStatement(ConsultaReceita);

        pst_ConsultaReceita.setInt(1, id_conta);

        rs_ConsultaReceita = pst_ConsultaReceita.executeQuery();

        while (rs_ConsultaReceita.next()) {

            lista_receita.add(
                    new Receita.ReceitaBuild(
                        id_conta, 
                        Integer.parseInt(rs_ConsultaReceita.getString("mes")), 
                        Integer.parseInt(rs_ConsultaReceita.getString("ano")))
                        .Dia(Integer.parseInt(rs_ConsultaReceita.getString("dia")))
                        .Total(Float.parseFloat(rs_ConsultaReceita.getString("total")))
                        .build()
            );
        }

        pst_ConsultaReceita.close();
        rs_ConsultaReceita.close();

        return lista_receita;
    }

    public boolean ReceitaExiste(int mes, int ano, int id_conta) throws SQLException {

        boolean result;
        
        PreparedStatement pst_SelectReceitaExiste;
        ResultSet rs_SelectReceitaExiste;
        
        String SelectReceitaExiste = "SELECT COUNT(*) count FROM receita WHERE conta_id_conta = ? AND mes = ? AND ano = ?;";

        pst_SelectReceitaExiste = conexao.prepareStatement(SelectReceitaExiste);

        pst_SelectReceitaExiste.setInt(1, id_conta);
        pst_SelectReceitaExiste.setInt(2, mes);
        pst_SelectReceitaExiste.setInt(3, ano);

        rs_SelectReceitaExiste = pst_SelectReceitaExiste.executeQuery();

        rs_SelectReceitaExiste.next();

        if (rs_SelectReceitaExiste.getInt("count") == 1) {
            result = true;
        } else {
            result = false;
        }
        
        pst_SelectReceitaExiste.close();
        rs_SelectReceitaExiste.close();

        return result;
    }
    
    public Receita GetReceita(int mes, int ano, int id_conta) throws SQLException {

        PreparedStatement pst;
        ResultSet rs;
        
       Receita receita = new Receita.ReceitaBuild().build();
        
        String consulta = "SELECT * FROM receita WHERE mes = ? AND ano = ? AND conta_id_conta = ?";

        pst = conexao.prepareStatement(consulta);

        pst.setInt(1, mes);

        pst.setInt(2, ano);

        pst.setInt(3, id_conta);

        rs = pst.executeQuery();

        if (rs.next()) {

            receita.setDia(rs.getInt("dia"));
            receita.setMes(rs.getInt("mes"));
            receita.setAno(rs.getInt("ano"));
            receita.setTotal(rs.getFloat("total"));
            receita.setCod_receita(rs.getInt("cod_receita"));
            receita.setId_conta(rs.getInt("conta_id_conta"));

        } else {

            return null;//exception
        }
        
        pst.close();
        rs.close();

       return receita;
    }
}