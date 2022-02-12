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
 * @autor Davi
 */
public class DespesaDAO {

    private Connection conexao = null;

    public DespesaDAO() {

        conexao = moduloConexao.conector();

    }
    
    public int GetCodigoDespesa(int id_conta, int dia, int mes, int ano) throws SQLException {

        PreparedStatement pst_SelectCodDespesa;
        ResultSet rs_SelectCodDespesa;

        int cod_despesa;

        String SelectCodDespesa = "SELECT cod_despesa FROM despesa WHERE conta_id_conta = ? AND dia = ? AND mes = ? AND ano = ?;";

        pst_SelectCodDespesa = conexao.prepareStatement(SelectCodDespesa);

        pst_SelectCodDespesa.setInt(1, id_conta);
        pst_SelectCodDespesa.setInt(2, dia);
        pst_SelectCodDespesa.setInt(3, mes);
        pst_SelectCodDespesa.setInt(4, ano);

        rs_SelectCodDespesa = pst_SelectCodDespesa.executeQuery();

        if (rs_SelectCodDespesa.next())
            cod_despesa = rs_SelectCodDespesa.getInt("cod_despesa");
        else 
            cod_despesa = -1;//DISPARAR EXCEPTION
        
        pst_SelectCodDespesa.close();
        rs_SelectCodDespesa.close();

        return cod_despesa;
    }
    
    public void InsertDespesa(Despesa despesa, int cod_receita, int id_categoria) throws SQLException {

        String InsertDespesa = "";

        PreparedStatement pst_InsertDespesa;

        //<editor-fold defaultstate="collapsed" desc="----- Verificando tipo de Insert da Despesa --">
        if (despesa.getF_pagamento().equals("DINHEIRO")) {

                InsertDespesa = "INSERT INTO despesa (\n"
                            + "	receita_cod_receita, \n"
                            + "	dia, \n"
                            + "	mes, \n"
                            + "	ano, \n"
                            + "	conta_id_conta, \n"
                            + "	categoria_id, \n"
                            + "	valor, \n"
                            + "	f_pagamento, \n"
                            + "	estatus)\n"
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            //InsertDespesa = "INSERT INTO despesa (receita_cod_receita, dia, mes, ano, conta_id_conta, categoria_id, valor, f_pagamento, estatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        } else {

            if (despesa.getF_pagamento().equals("DÉBITO")) {

                InsertDespesa = "INSERT INTO despesa (\n"
                            + "	receita_cod_receita, \n"
                            + "	dia, \n"
                            + "	mes, \n"
                            + "	ano, \n"
                            + "	conta_id_conta, \n"
                            + "	categoria_id, \n"
                            + "	num_cartao_debito, \n"
                            + "	valor, \n"
                            + "	f_pagamento, \n"
                            + "	estatus)\n"
                            + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            } else {

                InsertDespesa = "INSERT INTO despesa (\n"
                            + "	receita_cod_receita, \n"
                            + "	dia, \n"
                            + "	mes, \n"
                            + "	ano, \n"
                            + "	conta_id_conta, \n"
                            + "	categoria_id, \n"
                            + "	num_cartao_credito, \n"
                            + "	valor, \n"
                            + "	f_pagamento, \n"
                            + "	estatus)\n"
                            + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            }
        }

        //</editor-fold>        
        
        
        //<editor-fold defaultstate="collapsed" desc="----- Insert da Despesa --">
        pst_InsertDespesa = conexao.prepareStatement(InsertDespesa);

        pst_InsertDespesa.setInt(1, cod_receita);
        pst_InsertDespesa.setInt(2, despesa.getDia());
        pst_InsertDespesa.setInt(3, despesa.getMes());
        pst_InsertDespesa.setInt(4, despesa.getAno());
        pst_InsertDespesa.setInt(5, despesa.getId_conta());
        pst_InsertDespesa.setInt(6, id_categoria);

        if (despesa.getF_pagamento().equals("DINHEIRO")) {

            pst_InsertDespesa.setFloat(7, despesa.getValor());
            pst_InsertDespesa.setString(8, despesa.getF_pagamento());
            pst_InsertDespesa.setString(9, despesa.getEstatus());

        } else {

            pst_InsertDespesa.setLong(7, despesa.getNum_cartao());
            pst_InsertDespesa.setFloat(8, despesa.getValor());
            pst_InsertDespesa.setString(9, despesa.getF_pagamento());
            pst_InsertDespesa.setString(10, despesa.getEstatus());

        }

        pst_InsertDespesa.executeUpdate();

        //</editor-fold>
       
        pst_InsertDespesa.close();
    }
    
    public void InsertDespesaCredito(Despesa despesa) throws SQLException, NullPointerException {

        PreparedStatement pst_InsertDespesaCredito;
        
        String InsertDespesaCredito = "INSERT INTO despesa_credito (n_parcelas, valor_parcela, n_parcelas_pagas, despesa_cod_despesa) VALUES(?,?,?,?)";

        pst_InsertDespesaCredito = conexao.prepareStatement(InsertDespesaCredito);

        pst_InsertDespesaCredito.setInt(1, despesa.getNum_parcelas());
        pst_InsertDespesaCredito.setFloat(2, (despesa.getValor() / despesa.getNum_parcelas()));
        pst_InsertDespesaCredito.setInt(3, 0);
        pst_InsertDespesaCredito.setInt(4, despesa.getCod_despesa());

        pst_InsertDespesaCredito.executeUpdate();

        pst_InsertDespesaCredito.close();
    }
    
    public boolean DespesaCCTemCredito(long num_cartao, float valor, int id_conta) throws SQLException {

        boolean retorno;
        
        PreparedStatement pst;
        ResultSet rs;

        String consulta = "SELECT credito FROM cartao_credito WHERE n_cartao_credito = ? AND conta_id_conta = ?";

        pst = conexao.prepareStatement(consulta);

        pst.setLong(1, num_cartao);

        pst.setLong(2, id_conta);

        rs = pst.executeQuery();

        if (rs.next()) {

            if (valor <= rs.getFloat("credito"))
                retorno =  true;
            else 
                retorno = false;
            
        } else 
            retorno = false;
        
        pst.close();
        rs.close();
        
        return retorno;
    }
    
    public void UpdateDespesaTipoCredito(Despesa despesa, int id_categoria) throws SQLException {

        PreparedStatement pst;

        String updateDespesaCredito = "UPDATE despesa  D INNER JOIN despesa_credito DC "
                                    + "ON D.cod_despesa = DC.despesa_cod_despesa "
                                    + "SET "
                                    + " dia = ?,"
                                    + " mes = ?,"
                                    + " ano = ?,"
                                    + " valor = ?,"
                                    + " categoria_id = ?,"
                                    + " f_pagamento = ?,"
                                    + " num_cartao_credito= ?,"
                                    + " n_parcelas = ?,"
                                    + " estatus= ?,"
                                    + " descricao= ? WHERE D.cod_despesa = ? AND D.conta_id_conta = ?;";

        pst = conexao.prepareStatement(updateDespesaCredito);

        pst.setInt(1, despesa.getDia());
        pst.setInt(2, despesa.getMes());
        pst.setInt(3, despesa.getAno());
        pst.setFloat(4, despesa.getValor());
        pst.setInt(5, id_categoria);
        pst.setString(6, despesa.getF_pagamento());
        pst.setLong(7, despesa.getNum_cartao());
        pst.setInt(8, despesa.getNum_parcelas());
        pst.setString(9, despesa.getEstatus());
        pst.setString(10, despesa.getDescricao());
        pst.setInt(11, despesa.getCod_despesa());
        pst.setInt(12, despesa.getId_conta());

        pst.executeUpdate();

        pst.close();
    }
    
    public void UpdateDespesaTipoDebito(Despesa despesa, int id_categoria) throws SQLException {

        PreparedStatement pst;
        
        String updateDespesaDebito = "UPDATE despesa SET"
                                    + " dia = ?,"
                                    + " mes = ?,"
                                    + " ano = ?,"
                                    + " valor = ?,"
                                    + " categoria_id = ?,"
                                    + " f_pagamento = ?,"
                                    + " num_cartao_debito = ?,"
                                    + " estatus = ?,"
                                    + " descricao = ? WHERE cod_despesa = ? AND conta_id_conta = ?;";

        pst = conexao.prepareStatement(updateDespesaDebito);

        pst.setInt(1, despesa.getDia());
        pst.setInt(2, despesa.getMes());
        pst.setInt(3, despesa.getAno());
        pst.setFloat(4, despesa.getValor());
        pst.setInt(5, id_categoria);
        pst.setString(6, despesa.getF_pagamento());
        pst.setLong(7, despesa.getNum_cartao());
        pst.setString(8, despesa.getEstatus());
        pst.setString(9, despesa.getDescricao());
        pst.setInt(10, despesa.getCod_despesa());
        pst.setInt(11, despesa.getId_conta());

        pst.executeUpdate();

        pst.close();
    }
    
    public void UpdateDespesaTipoDinheiro(Despesa despesa, int id_categoria) throws SQLException {

        PreparedStatement pst;

        String update = "UPDATE despesa SET "
                        + "dia = ?,"
                        + " mes = ?,"
                        + " ano = ? ,"
                        + " valor = ?,"
                        + " categoria_id= ?,"
                        + " f_pagamento= ?,"
                        + " estatus = ?,"
                        + " descricao= ? WHERE cod_despesa= ? AND conta_id_conta = ?;";

        pst = conexao.prepareStatement(update);

        pst.setInt(1, despesa.getDia());
        pst.setInt(2, despesa.getMes());
        pst.setInt(3, despesa.getAno());
        pst.setFloat(4, despesa.getValor());
        pst.setInt(5, id_categoria);
        pst.setString(6, despesa.getF_pagamento());
        pst.setString(7, despesa.getEstatus());
        pst.setString(8, despesa.getDescricao());
        pst.setInt(9, despesa.getCod_despesa());
        pst.setInt(10, despesa.getId_conta());

        pst.executeUpdate();

        pst.close();
    }

    public void DeleteDespesa(Despesa despesa) throws SQLException {

        PreparedStatement pst;

        String deleteDespesa = "DELETE FROM despesa WHERE cod_despesa = ? AND conta_id_conta = ?;";

        pst = conexao.prepareStatement(deleteDespesa);

        pst.setLong(1, despesa.getCod_despesa());
        pst.setInt(2, despesa.getId_conta());

        pst.executeUpdate();

        pst.close();
    }

    public LinkedList<Despesa> GetListaDespesaCredito(int id_conta) throws SQLException {
        
        PreparedStatement pst;
        ResultSet rs;
        
        String consulta = "(SELECT\n"
                        + "des.cod_despesa,\n"
                        + "des.dia, \n"
                        + "des.mes, \n"
                        + "des.ano, \n"
                        + "des.valor, \n"
                        + "des.categoria_id, \n"
                        + "des.f_pagamento, \n"
                        + "des.num_cartao_credito, \n"
                        + "des_c.n_parcelas, \n"
                        + "des.estatus, \n"
                        + "des.descricao, \n"
                        + "C.categoriaTipo \n"
                        + "FROM despesa des LEFT OUTER JOIN despesa_credito des_c ON \n"
                        + "(des.cod_despesa = des_c.despesa_cod_despesa) \n"
                        + "LEFT JOIN categoria C ON \n"
                        + "(C.categoriaId = des.categoria_id) \n"
                        + "WHERE des.conta_id_conta = ? AND des.f_pagamento = 'CRÉDITO')"; 
          
        pst = conexao.prepareStatement(consulta);

        LinkedList<Despesa> lista_despesa = new LinkedList<>();

        pst.setInt(1, id_conta);

        rs = pst.executeQuery();

        while (rs.next()) {
            /*
            lista_despesa.add(new Despesa(
                    rs.getInt("dia"),
                    rs.getInt("mes"),
                    rs.getInt("ano"),
                    rs.getFloat("valor"),
                    rs.getString("categoriaTipo"),
                    rs.getString("f_pagamento"),
                    rs.getLong("num_cartao_credito"),
                    rs.getInt("n_parcelas"),
                    rs.getString("estatus"),
                    rs.getString("descricao"),
                    rs.getInt("cod_despesa")
            ));
            */
             lista_despesa.add(new Despesa.DespesaBuild(rs.getInt("cod_despesa"))
                    .Dia(rs.getInt("dia"))
                    .Mes(rs.getInt("mes"))
                    .Ano(rs.getInt("ano"))
                    .Valor(rs.getFloat("valor"))
                    .Categoria(rs.getString("categoriaTipo"))
                    .FormaPagamento(rs.getString("f_pagamento"))
                    .NumeroCartao(rs.getLong("num_cartao_credito"))
                    .NumeroParcelas(rs.getInt("n_parcelas"))
                    .Status(rs.getString("estatus"))
                    .Descricao(rs.getString("descricao"))
                    .build()
            );
        }

        pst.close();
        rs.close();

        return lista_despesa;
    }
    
    public LinkedList<Despesa> GetListaDespesaDebito(int id_conta) throws SQLException {
        
        PreparedStatement pst;
        ResultSet rs;
        
        String consulta = "(SELECT\n"
                        + "des.cod_despesa,\n"
                        + "des.dia, \n"
                        + "des.mes, \n"
                        + "des.ano, \n"
                        + "des.valor, \n"
                        + "des.categoria_id, \n"
                        + "des.f_pagamento, \n"
                        + "des.num_cartao_debito, \n"
                        + "des_c.n_parcelas, \n"
                        + "des.estatus, \n"
                        + "des.descricao, \n"
                        + "C.categoriaTipo \n"
                        + "FROM despesa des LEFT OUTER JOIN despesa_credito des_c ON \n"
                        + "(des.cod_despesa = des_c.despesa_cod_despesa) \n"
                        + "LEFT JOIN categoria C ON \n"
                        + "(C.categoriaId = des.categoria_id) \n"
                        + "WHERE des.conta_id_conta = ? AND des.f_pagamento = 'DÉBITO')"; 
           
        pst = conexao.prepareStatement(consulta);

        LinkedList<Despesa> lista_despesa = new LinkedList<>();

        pst.setInt(1, id_conta);

        rs = pst.executeQuery();

        while (rs.next()) {
            /*
            lista_despesa.add(new Despesa(
                    rs.getInt("dia"),
                    rs.getInt("mes"),
                    rs.getInt("ano"),
                    rs.getFloat("valor"),
                    rs.getString("categoriaTipo"),
                    rs.getString("f_pagamento"),
                    rs.getLong("num_cartao_debito"),
                    rs.getInt("n_parcelas"),
                    rs.getString("estatus"),
                    rs.getString("descricao"),
                    rs.getInt("cod_despesa")
            ));
            */
             lista_despesa.add(new Despesa.DespesaBuild(rs.getInt("cod_despesa"))
                    .Dia(rs.getInt("dia"))
                    .Mes(rs.getInt("mes"))
                    .Ano(rs.getInt("ano"))
                    .Valor(rs.getFloat("valor"))
                    .Categoria(rs.getString("categoriaTipo"))
                    .FormaPagamento(rs.getString("f_pagamento"))
                    .NumeroCartao(rs.getLong("num_cartao_debito"))
                    .NumeroParcelas(rs.getInt("n_parcelas"))
                    .Status(rs.getString("estatus"))
                    .Descricao(rs.getString("descricao"))
                    .build()
            );
        }

        pst.close();
        rs.close();

        return lista_despesa;
    }
    
    public LinkedList<Despesa> GetListaDespesaDinheiro(int id_conta) throws SQLException {
        
        PreparedStatement pst;
        ResultSet rs;
        
        String consulta = "SELECT "
                        + "des.cod_despesa,"
                        + " des.dia,"
                        + " des.mes,"
                        + " des.ano,"
                        + " des.valor,"
                        + " des.categoria_id,"
                        + " des.f_pagamento,"
                        + " des.estatus,"
                        + " des.descricao,"
                        + " C.categoriaTipo FROM despesa des LEFT JOIN categoria C ON (C.categoriaId = des.categoria_id) "
                        + " WHERE des.conta_id_conta = ? AND des.f_pagamento = 'DINHEIRO'; ";
        
        pst = conexao.prepareStatement(consulta);

        LinkedList<Despesa> lista_despesa = new LinkedList<>();

        pst.setInt(1, id_conta);

        rs = pst.executeQuery();

        while (rs.next()) {
            /*
            lista_despesa.add(new Despesa(
                    rs.getInt("dia"),
                    rs.getInt("mes"),
                    rs.getInt("ano"),
                    rs.getFloat("valor"),
                    rs.getString("categoriaTipo"),
                    rs.getString("f_pagamento"),
                    rs.getString("estatus"),
                    rs.getString("descricao"),
                    rs.getInt("cod_despesa")
            ));
            */
            
             lista_despesa.add(new Despesa.DespesaBuild(rs.getInt("cod_despesa"))
                    .Dia(rs.getInt("dia"))
                    .Mes(rs.getInt("mes"))
                    .Ano(rs.getInt("ano"))
                    .Valor(rs.getFloat("valor"))
                    .Categoria(rs.getString("categoriaTipo"))
                    .FormaPagamento(rs.getString("f_pagamento"))
                    .Status(rs.getString("estatus"))
                    .Descricao(rs.getString("descricao"))
                    .build()
            );
        }

        pst.close();
        rs.close();

        return lista_despesa;
    }
    
    public LinkedList<Despesa> ConsultaDespesaPorReceita(String tipo, String arg, boolean ordenar, Receita receita) throws SQLException {

        String consultaDespesaPorReceita;

        PreparedStatement pst;
        ResultSet rs;

        LinkedList<Despesa> lista_despesa = new LinkedList<>();

        String argumento = "";

        if (arg == null || arg.equals("")) {

            if (ordenar) {
                argumento = " ORDER BY " + tipo + " ASC";
            } else {
                argumento = " ORDER BY " + tipo + " DESC";
            }

        } else {
            if (ordenar) {
                argumento = " AND " + tipo + " " + "LIKE '%" + arg + "%'" + " ORDER BY " + tipo + " ASC";
            } else {
                argumento = " AND " + tipo + " " + "LIKE '%" + arg + "%'" + " ORDER BY " + tipo + " DESC";
            }
        }

        consultaDespesaPorReceita = "SELECT "
                                    + "des.cod_despesa, "
                                    + "des.dia, "
                                    + "des.mes, "
                                    + "des.ano, "
                                    + "des.valor,"
                                    + "des.categoria_id, "
                                    + "des.f_pagamento, "
                                    + "des.num_cartao_credito, "
                                    + "des.num_cartao_debito, "
                                    + "des_c.n_parcelas, des.estatus, "
                                    + "des.descricao, "
                                    + "c.categoriaTipo FROM despesa des LEFT OUTER JOIN despesa_credito des_c "
                                    + "ON (des.cod_despesa = des_c.despesa_cod_despesa) LEFT OUTER JOIN categoria c ON  "
                                    + "(des.categoria_id = c.categoriaId) "
                                    + "WHERE des.conta_id_conta = ? AND des.mes = ? AND des.ano = ?" + argumento;

        pst = conexao.prepareStatement(consultaDespesaPorReceita);

        pst.setInt(1, receita.getId_conta());
        pst.setInt(2, receita.getMes());
        pst.setInt(3, receita.getAno());

        rs = pst.executeQuery();

        while (rs.next()) {
            
            switch (rs.getString("f_pagamento")) {
                
                case "CRÉDITO":
                    /*
                    lista_despesa.add(
                            
                            new Despesa(
                                    rs.getInt("dia"),
                                    rs.getInt("mes"),
                                    rs.getInt("ano"),
                                    rs.getFloat("valor"),
                                    rs.getString("categoriaTipo"),
                                    rs.getString("f_pagamento"),
                                    rs.getLong("num_cartao_credito"),
                                    rs.getInt("n_parcelas"),
                                    rs.getString("estatus"),
                                    rs.getString("descricao"),
                                    rs.getInt("cod_despesa")
                            )
                    );
                            */
                            lista_despesa.add(
                            new Despesa.DespesaBuild(rs.getInt("cod_despesa"))
                                    .Dia(rs.getInt("dia"))
                                    .Mes(rs.getInt("mes"))
                                    .Ano(rs.getInt("ano"))
                                    .Valor(rs.getFloat("valor"))
                                    .Categoria(rs.getString("categoriaTipo"))
                                    .FormaPagamento(rs.getString("f_pagamento"))
                                    .NumeroCartao(rs.getLong("num_cartao_credito"))
                                    .NumeroParcelas(rs.getInt("n_parcelas"))
                                    .Status(rs.getString("estatus"))
                                    .Descricao(rs.getString("descricao"))
                                    .build()
                    );
                    break;
                    
                case "DÉBITO":
                    /*
                    lista_despesa.add(
                            new Despesa(
                                    rs.getInt("dia"),
                                    rs.getInt("mes"),
                                    rs.getInt("ano"),
                                    rs.getFloat("valor"),
                                    rs.getString("categoriaTipo"),
                                    rs.getString("f_pagamento"),
                                    rs.getLong("num_cartao_debito"),
                                    rs.getString("estatus"),
                                    rs.getString("descricao"),
                                    rs.getInt("cod_despesa")
                            )
                    );
                    */
                     lista_despesa.add(
                            new Despesa.DespesaBuild(rs.getInt("cod_despesa"))
                                    .Dia(rs.getInt("dia"))
                                    .Mes(rs.getInt("mes"))
                                    .Ano(rs.getInt("ano"))
                                    .Valor(rs.getFloat("valor"))
                                    .Categoria(rs.getString("categoriaTipo"))
                                    .FormaPagamento(rs.getString("f_pagamento"))
                                    .NumeroCartao(rs.getLong("num_cartao_debito"))
                                    .Status(rs.getString("estatus"))
                                    .Descricao(rs.getString("descricao"))
                                    .build()
                    );
                    break;
                    
                default:
                    /*
                    lista_despesa.add(
                            new Despesa(
                                    rs.getInt("dia"),
                                    rs.getInt("mes"),
                                    rs.getInt("ano"),
                                    rs.getFloat("valor"),
                                    rs.getString("categoriaTipo"),
                                    rs.getString("f_pagamento"),
                                    rs.getString("estatus"),
                                    rs.getString("descricao"),
                                    rs.getInt("cod_despesa")
                            )
                    );
                    */
                    lista_despesa.add(
                            new Despesa.DespesaBuild(rs.getInt("cod_despesa"))
                                    .Dia(rs.getInt("dia"))
                                    .Mes(rs.getInt("mes"))
                                    .Ano(rs.getInt("ano"))
                                    .Valor(rs.getFloat("valor"))
                                    .Categoria(rs.getString("categoriaTipo"))
                                    .FormaPagamento(rs.getString("f_pagamento"))
                                    .Status(rs.getString("estatus"))
                                    .Descricao(rs.getString("descricao"))
                                    .build()
                    );
                    break;
            }
        }

        pst.close();
        rs.close();

        return lista_despesa;
    }
    
    public LinkedList<Despesa> ConsultaDespesa(String tipo, String arg, boolean ordenar, int id_conta) throws SQLException {
        
        String consulta;

        PreparedStatement pst;
        ResultSet rs;

        LinkedList<Despesa> lista_despesa = new LinkedList<>();
        
        String argumento = "";
        
        if(arg == null || arg.equals("")){
            
            if(ordenar)
                argumento = " ORDER BY " + tipo + " ASC";
            else
                argumento = " ORDER BY " + tipo + " DESC";
            
        }else{
            if (ordenar)
                argumento = " AND " + tipo + " " + "LIKE '%" + arg + "%'" + " ORDER BY " + tipo + " ASC";
            else             
                argumento = " AND " + tipo + " " + "LIKE '%" + arg + "%'" + " ORDER BY " + tipo + " DESC";            
        }
        
        consulta = "SELECT "
                 + "des.cod_despesa,"
                 + "des.dia, "
                 + "des.mes, "
                 + "des.ano, "
                 + "des.valor, "
                 + "des.categoria_id, "
                 + "des.f_pagamento, "
                 + "des.num_cartao_credito, "
                 + "des.num_cartao_debito, "
                 + "des_c.n_parcelas, "
                 + "des.estatus, "
                 + "des.descricao, "
                 + "c.categoriaTipo FROM despesa des LEFT OUTER JOIN despesa_credito des_c ON  "
                 + "(des.cod_despesa = des_c.despesa_cod_despesa)  LEFT OUTER JOIN categoria c ON "
                 + "(des.categoria_id = c.categoriaId) WHERE des.conta_id_conta = ? " + argumento;

        pst = conexao.prepareStatement(consulta);

        pst.setInt(1, id_conta);

        rs = pst.executeQuery();

        while (rs.next()) {
            
            switch (rs.getString("f_pagamento")) {
                
                case "CRÉDITO":
                    /*
                    lista_despesa.add(
                            new Despesa(
                                    rs.getInt("dia"),
                                    rs.getInt("mes"),
                                    rs.getInt("ano"),
                                    rs.getFloat("valor"),
                                    rs.getString("categoriaTipo"),
                                    rs.getString("f_pagamento"),
                                    rs.getLong("num_cartao_credito"),
                                    rs.getInt("n_parcelas"),
                                    rs.getString("estatus"),
                                    rs.getString("descricao"),
                                    rs.getInt("cod_despesa")
                            )
                    );
                    */
                     lista_despesa.add(
                            new Despesa.DespesaBuild(rs.getInt("cod_despesa"))
                            .Dia(rs.getInt("dia"))
                            .Mes(rs.getInt("mes"))
                            .Ano(rs.getInt("ano"))
                            .Valor(rs.getFloat("valor"))
                            .Categoria(rs.getString("categoriaTipo"))
                            .FormaPagamento(rs.getString("f_pagamento"))
                            .NumeroCartao(rs.getLong("num_cartao_credito"))
                            .NumeroParcelas(rs.getInt("n_parcelas"))
                            .Status(rs.getString("estatus"))
                            .Descricao(rs.getString("descricao"))
                            .build()                                    
                    );  
                    break;
                    
                case "DÉBITO":
                    /*
                    lista_despesa.add(
                            new Despesa(
                                    rs.getInt("dia"),
                                    rs.getInt("mes"),
                                    rs.getInt("ano"),
                                    rs.getFloat("valor"),
                                    rs.getString("categoriaTipo"),
                                    rs.getString("f_pagamento"),
                                    rs.getLong("num_cartao_debito"),
                                    rs.getString("estatus"),
                                    rs.getString("descricao"),
                                    rs.getInt("cod_despesa")
                            )
                    );
                    */
                    lista_despesa.add(
                            new Despesa.DespesaBuild(rs.getInt("cod_despesa"))
                            .Dia(rs.getInt("dia"))
                            .Mes(rs.getInt("mes"))
                            .Ano(rs.getInt("ano"))
                            .Valor(rs.getFloat("valor"))
                            .Categoria(rs.getString("categoriaTipo"))
                            .FormaPagamento(rs.getString("f_pagamento"))
                            .NumeroCartao(rs.getLong("num_cartao_debito"))
                            .Status(rs.getString("estatus"))
                            .Descricao(rs.getString("descricao"))
                            .build()
                    );  
                    break;
                    
                default:
                    /*
                    lista_despesa.add(
                            new Despesa(
                                    rs.getInt("dia"),
                                    rs.getInt("mes"),
                                    rs.getInt("ano"),
                                    rs.getFloat("valor"),
                                    rs.getString("categoriaTipo"),
                                    rs.getString("f_pagamento"),
                                    rs.getString("estatus"),
                                    rs.getString("descricao"),
                                    rs.getInt("cod_despesa")
                            )
                    );
                    */
                     lista_despesa.add(
                            new Despesa.DespesaBuild(rs.getInt("cod_despesa"))
                                    .Dia(rs.getInt("dia"))
                                    .Mes(rs.getInt("mes"))
                                    .Ano(rs.getInt("ano"))
                                    .Valor(rs.getFloat("valor"))
                                    .Categoria(rs.getString("categoriaTipo"))
                                    .FormaPagamento(rs.getString("f_pagamento"))
                                    .Status(rs.getString("estatus"))
                                    .Descricao(rs.getString("descricao"))
                                    .build()
                    );  
                    break;
            }
        }

        pst.close();
        rs.close();
        
        return lista_despesa;
    }

    public boolean DespesaExiste(Despesa despesa) throws SQLException {

        PreparedStatement pst_SelectDespesaExiste;

        ResultSet rs_SelectDespesaExiste;
        
        String SelectDespesaExiste = "SELECT COUNT(*) count FROM  despesa \n"
                                    + "WHERE dia = ? AND mes = ? AND ano = ? AND categoria_id = ? AND conta_id_conta = ?;";

        pst_SelectDespesaExiste = conexao.prepareStatement(SelectDespesaExiste);

        pst_SelectDespesaExiste.setInt(1, despesa.getDia());

        pst_SelectDespesaExiste.setInt(2, despesa.getMes());

        pst_SelectDespesaExiste.setInt(3, despesa.getAno());

        pst_SelectDespesaExiste.setString(4, despesa.getCategoria());

        pst_SelectDespesaExiste.setInt(5, despesa.getId_conta());

        rs_SelectDespesaExiste = pst_SelectDespesaExiste.executeQuery();

        rs_SelectDespesaExiste.next();

        if (rs_SelectDespesaExiste.getInt("count") == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public LinkedList<Despesa> GetListaDespesasNpUltimaReceita(Receita receita) throws SQLException {

        String SelectDespesasNp = "SELECT cod_despesa FROM despesa WHERE conta_id_conta = ? AND estatus = 'NÃO PAGO' AND receita_cod_receita = ?;";

        PreparedStatement pst_SelectDespesasNp = conexao.prepareStatement(SelectDespesasNp);

        pst_SelectDespesasNp.setInt(1, receita.getId_conta());
        pst_SelectDespesasNp.setInt(2, receita.getCod_receita());

        ResultSet rs_SelectDespesas = pst_SelectDespesasNp.executeQuery();

        LinkedList<Despesa> lista_despesa = new LinkedList<>();

        while (rs_SelectDespesas.next()) {

           Despesa despesa_aux = new Despesa.DespesaBuild().build();

            despesa_aux.setCod_despesa(rs_SelectDespesas.getInt("cod_despesa"));

            despesa_aux.setId_conta(receita.getId_conta());

            lista_despesa.add(despesa_aux);

        }

        return lista_despesa;

    }
    
    public void UpdateMesAnoDespesa(Despesa despesa, Receita receita_nova) throws SQLException {

        PreparedStatement pst_UpdateDespesa;

        String sql_UpdateDespesa = "UPDATE despesa SET mes = ?, ano = ?, receita_cod_receita = ? WHERE (cod_despesa = ? AND conta_id_conta = ?)";

        pst_UpdateDespesa = conexao.prepareStatement(sql_UpdateDespesa);;
      
        pst_UpdateDespesa.setInt(1, receita_nova.getMes());
        pst_UpdateDespesa.setInt(2, receita_nova.getAno());
        pst_UpdateDespesa.setInt(3, receita_nova.getCod_receita());
        pst_UpdateDespesa.setInt(4, despesa.getCod_despesa());
        pst_UpdateDespesa.setInt(5, receita_nova.getId_conta());

        pst_UpdateDespesa.executeUpdate();

        pst_UpdateDespesa.close();
    }
   
//    public void TransferirDespesasEntreReceitas(LinkedList<Despesa> lista_despesasNp, Receita receita_nova) throws SQLException {
//
//        PreparedStatement pst_UpdateDespesa;
//
//        String sql_UpdateDespesa = "UPDATE despesa SET mes = ?, ano = ?, receita_cod_receita = ? WHERE (cod_despesa = ? AND conta_id_conta = ?)";
//
//        pst_UpdateDespesa = conexao.prepareStatement(sql_UpdateDespesa);;
//
//        for (Despesa despesa : lista_despesasNp) {
//            pst_UpdateDespesa.setInt(1, receita_nova.getMes());
//            pst_UpdateDespesa.setInt(2, receita_nova.getAno());
//            pst_UpdateDespesa.setInt(3, receita_nova.getCod_receita());
//            pst_UpdateDespesa.setInt(4, despesa.getCod_despesa());
//            pst_UpdateDespesa.setInt(5, receita_nova.getId_conta());
//
//            pst_UpdateDespesa.executeUpdate();
//        }
//
//        pst_UpdateDespesa.close();
//    }
    
    public LinkedList<Despesa> GetListaDespesaFatura(Long numCartaoCredito, int id_conta) throws SQLException{

        PreparedStatement pst_SelectDespesasFatura;
        ResultSet rs_SelectDespesasFatura;
        
        LinkedList<Despesa> lista_despesaFatura = new LinkedList<>();
        
        String SelectDespesasFatura = "SELECT \n"
                                    + "des.dia,\n"
                                    + "des.mes,\n"
                                    + "des.ano,\n"
                                    + "des.valor,\n"
                                    + "des.estatus,\n"
                                    + "des.categoria_id,\n"
                                    + "des_c.n_parcelas,\n"
                                    + "des_c.n_parcelas_pagas,\n"
                                    + "des_c.valor_parcela,\n"
                                    + "des.descricao\n"
                                    + "FROM despesa des LEFT JOIN despesa_credito des_c\n"
                                    + "ON des.cod_despesa = des_c.despesa_cod_despesa\n"
                                    + "WHERE des.num_cartao_credito = ? AND des.conta_id_conta = ?";
        
        pst_SelectDespesasFatura = conexao.prepareStatement(SelectDespesasFatura);

        pst_SelectDespesasFatura.setLong(1, numCartaoCredito);
        pst_SelectDespesasFatura.setInt(2, id_conta);

        rs_SelectDespesasFatura = pst_SelectDespesasFatura.executeQuery();

        while (rs_SelectDespesasFatura.next()) {

           Despesa despesa = new Despesa.DespesaBuild().build();

            despesa.setDia(rs_SelectDespesasFatura.getInt("dia"));
            despesa.setMes(rs_SelectDespesasFatura.getInt("mes"));
            despesa.setAno(rs_SelectDespesasFatura.getInt("ano"));
            despesa.setValor(rs_SelectDespesasFatura.getFloat("valor"));
            despesa.setEstatus(rs_SelectDespesasFatura.getString("estatus"));
            despesa.setId_categoria(rs_SelectDespesasFatura.getInt("categoria_id"));
            despesa.setNum_parcelas(rs_SelectDespesasFatura.getInt("n_parcelas"));
            despesa.setNum_parcelas_pagas(rs_SelectDespesasFatura.getInt("n_parcelas_pagas"));
            despesa.setValor_parcela(rs_SelectDespesasFatura.getFloat("valor_parcela"));

            despesa.setDescricao(rs_SelectDespesasFatura.getString("descricao"));

            lista_despesaFatura.add(despesa);
        }

        pst_SelectDespesasFatura.close();
        rs_SelectDespesasFatura.close();

        return lista_despesaFatura;

    }
    
    public LinkedList<Despesa> ConsultaDespesaFatura(String tipo, String arg, int id_conta, boolean ordenar) throws SQLException {

        LinkedList<Despesa> ListaDespesaFatura = new LinkedList<>();
        
        PreparedStatement pst;
        ResultSet rs;
        
        String argumento = "";
        
        if(ordenar)
            argumento =  "des.conta_id_conta = " + id_conta + " AND " + "cat.conta_id_conta = " + id_conta + " AND " + tipo + " " + "LIKE '" + arg + "%'" + " ORDER BY " + tipo + " ASC";
        else
            argumento =  "des.conta_id_conta = " + id_conta + " AND " + "cat.conta_id_conta = " + id_conta + " AND " + tipo + " " + "LIKE '" + arg + "%'" + " ORDER BY " + tipo + " DESC";
       
        
        String consulta = "SELECT \n"
                        + "des.dia,\n"
                        + "des.mes,\n"
                        + "des.ano,\n"
                        + "des.valor,\n"
                        + "des.estatus,\n"
                        + "cat.categoriaTipo,\n"
                        + "des_c.n_parcelas,\n"
                        + "des_c.n_parcelas_pagas,\n"
                        + "des_c.valor_parcela\n"
                        + "FROM despesa des INNER JOIN despesa_credito des_c \n"
                        + "ON des_c.despesa_cod_despesa = des.cod_despesa\n"
                        + "INNER JOIN categoria cat \n"
                        + "ON cat.categoriaId = des.categoria_id\n"
                        + "WHERE " + argumento + "";

        pst = conexao.prepareStatement(consulta);

        rs = pst.executeQuery();

        while (rs.next()) {

            Despesa despesa = new Despesa.DespesaBuild().build();

            despesa.setDia(rs.getInt("dia"));
            despesa.setMes(rs.getInt("mes"));
            despesa.setAno(rs.getInt("ano"));
            despesa.setValor(rs.getFloat("valor"));
            despesa.setEstatus(rs.getString("estatus"));
            despesa.setCategoria(rs.getString("categoriaTipo"));
            despesa.setNum_parcelas(rs.getInt("n_parcelas"));
            despesa.setNum_parcelas_pagas(rs.getInt("n_parcelas_pagas"));
            despesa.setValor_parcela(rs.getFloat("valor_parcela"));
            despesa.setId_conta(id_conta);

            ListaDespesaFatura.add(despesa);
        }

        pst.close();
        rs.close();

        return ListaDespesaFatura;
    }

/*
    public boolean CadastrarDespesa(Despesa despesa) throws SQLException, NullPointerException {

        ReceitaDAO receitaDAO = new ReceitaDAO();

        CategoriaDAO categoriaDAO = new CategoriaDAO();

        int cod_receita = 0, id_categoria = 0;

        String InsertDespesa = "";

        PreparedStatement pst_InsertDespesa = null;
        PreparedStatement pst_InsertDespesaCredito = null;

        try {

            cod_receita = receitaDAO.GetCodigoReceita(despesa.getId_conta(), despesa.getMes(), despesa.getAno());

            id_categoria = categoriaDAO.GetCategoriaId(despesa.getId_conta(), despesa.getCategoria());

            //<editor-fold defaultstate="collapsed" desc="----- Verificando tipo de Insert da Despesa --">
            if (despesa.getF_pagamento().equals("DINHEIRO")) {


                  InsertDespesa = "INSERT INTO despesa (receita_cod_receita, dia, mes, ano, conta_id_conta, categoria_id, valor, f_pagamento, estatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

            } else {

                if (despesa.getF_pagamento().equals("DÉBITO")) {

                    InsertDespesa = "INSERT INTO despesa (\n"
                            + "	receita_cod_receita, \n"
                            + "	dia, \n"
                            + "	mes, \n"
                            + "	ano, \n"
                            + "	conta_id_conta, \n"
                            + "	categoria_id, \n"
                            + "	num_cartao_debito, \n"
                            + "	valor, \n"
                            + "	f_pagamento, \n"
                            + "	estatus)\n"
                            + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

                } else {

                    InsertDespesa = "INSERT INTO despesa (\n"
                            + "	receita_cod_receita, \n"
                            + "	dia, \n"
                            + "	mes, \n"
                            + "	ano, \n"
                            + "	conta_id_conta, \n"
                            + "	categoria_id, \n"
                            + "	num_cartao_credito, \n"
                            + "	valor, \n"
                            + "	f_pagamento, \n"
                            + "	estatus)\n"
                            + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

                }
            }

            //</editor-fold>
            
            
            //<editor-fold defaultstate="collapsed" desc="----- Insert da Despesa --">
            pst_InsertDespesa = conexao.prepareStatement(InsertDespesa);
            
            pst_InsertDespesa.setInt(1, cod_receita);
            pst_InsertDespesa.setInt(2, despesa.getDia());
            pst_InsertDespesa.setInt(3, despesa.getMes());
            pst_InsertDespesa.setInt(4, despesa.getAno());
            pst_InsertDespesa.setInt(5, despesa.getId_conta());
            pst_InsertDespesa.setInt(6, id_categoria);

            if (despesa.getF_pagamento().equals("DINHEIRO")) {

                pst_InsertDespesa.setFloat(7, despesa.getValor());
                pst_InsertDespesa.setString(8, despesa.getF_pagamento());
                pst_InsertDespesa.setString(9, despesa.getEstatus());

            } else {

                pst_InsertDespesa.setLong(7, despesa.getNum_cartao());
                pst_InsertDespesa.setFloat(8, despesa.getValor());
                pst_InsertDespesa.setString(9, despesa.getF_pagamento());
                pst_InsertDespesa.setString(10, despesa.getEstatus());

            }
            
            
                
            pst_InsertDespesa.executeUpdate();
                
   
            //</editor-fold>
            
            
            //<editor-fold defaultstate="collapsed" desc="----- Insert DespesaCredito e Update Cartão Crédito --">
            if (despesa.getF_pagamento().equals("CRÉDITO")) {

                DespesaDAO despesaDAO = new DespesaDAO();

                int cod_despesa = despesaDAO.GetCodigoDespesa(despesa.getId_conta(), despesa.getDia(), despesa.getMes(), despesa.getAno());

                String InsertDespesaCredito = "INSERT INTO despesa_credito (n_parcelas, valor_parcela, n_parcelas_pagas, despesa_cod_despesa) VALUES(?,?,?,?)";

                pst_InsertDespesaCredito = conexao.prepareStatement(InsertDespesaCredito);

                pst_InsertDespesaCredito.setInt(1, despesa.getNum_parcelas());
                pst_InsertDespesaCredito.setFloat(2, (despesa.getValor()/despesa.getNum_parcelas()));
                pst_InsertDespesaCredito.setInt(3, 0);
                pst_InsertDespesaCredito.setInt(4, cod_despesa);

                pst_InsertDespesaCredito.executeUpdate();

                pst_InsertDespesaCredito.close();
                
                CartaoCreditoDAO ccDAO = new CartaoCreditoDAO();

                ccDAO.UpdateCreditoFaturaCartaoCredito(despesa.getId_conta(), despesa.getValor(), despesa.getNum_cartao());

            }

            //</editor-fold>
            
            
            //<editor-fold defaultstate="collapsed" desc="----- Desconta da Receita --">
            if (despesa.getF_pagamento().equals("CRÉDITO")
                    || despesa.getF_pagamento().equals("DÉBITO")
                    || (despesa.getF_pagamento().equals("DINHEIRO") && despesa.getEstatus().equals("PAGO"))) {

                receitaDAO.UpdateTotalReceita(despesa.getId_conta(), despesa.getValor(), cod_receita);

            }

            //</editor-fold>
            
            
            return true;

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            JOptionPane.showMessageDialog(null, "Erro:CadastrarDespesa", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;

        } finally {
            
            pst_InsertDespesa.close();
            
        }
    }
    
        public boolean DespesaTemCartaoCredito(long num_cartao, int id_conta) throws SQLException {

        String consulta = "SELECT n_cartao_credito FROM cartao_credito WHERE n_cartao_credito = ? AND conta_id_conta = ?";

        PreparedStatement pst = conexao.prepareStatement(consulta);

        ResultSet rs = null;

        try {

            pst.setLong(1, num_cartao);

            pst.setLong(2, id_conta);

            rs = pst.executeQuery();

            if (rs.next())
                return true;
            else
                return false;
          

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            JOptionPane.showMessageDialog(null, "Erro:DespesaTemCartaoCredito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;

        }

    }

    public boolean DespesaTemCartaoDebito(long num_cartao, int id_conta) throws SQLException {

        String consulta = "select n_cartao_debito from cartao_debito where n_cartao_debito = ? and conta_id_conta = ?";

        PreparedStatement pst = conexao.prepareStatement(consulta);

        ResultSet rs = null;

        try {

            pst.setLong(1, num_cartao);

            pst.setLong(2, id_conta);

            rs = pst.executeQuery();

            if (rs.next()) {

                return true;

            } else {

                return false;
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        } finally {

            pst.close();

        }

    }
    
    
    public boolean UpdateDespesa(Despesa despesa, int id_conta) throws SQLException {
        
        int idCategoria = -1;
        
        CategoriaDAO categoriaNova = new CategoriaDAO();
        
        
        idCategoria =  categoriaNova.GetCategoriaId(id_conta,despesa.getCategoria());
        
        System.out.println("O id categoria é: "+idCategoria);
        
        
        if (despesa.getF_pagamento().equals("CRÉDITO")) {
            
            
            if (DespesaTemCartaoCredito(despesa.getNum_cartao(), id_conta)) {
                
                PreparedStatement pst1 = null;
                PreparedStatement pst2 = null;
                
                String update = "update despesa  D LEFT OUTER JOIN despesa_credito DC ON D.cod_despesa = DC.despesa_cod_despesa SET dia = ?, mes = ?, ano = ?, valor = ?, categoria_id = ?, f_pagamento = ?, num_cartao_credito= ?, n_parcelas = ?, estatus= ?, descricao= ? WHERE D.cod_despesa = ?;";

                pst1 = conexao.prepareStatement(update);

                try {

                    pst1.setInt(1, despesa.getDia());
                    pst1.setInt(2, despesa.getMes());
                    pst1.setInt(3, despesa.getAno());
                    pst1.setFloat(4, despesa.getValor());
                    pst1.setInt(5, idCategoria);
                    pst1.setString(6, despesa.getF_pagamento());
                    pst1.setLong(7, despesa.getNum_cartao());
                    pst1.setInt(8, despesa.getNum_parcelas());
                    pst1.setString(9, despesa.getEstatus());
                    pst1.setString(10, despesa.getDescricao());
                    pst1.setInt(11, despesa.getCod_despesa());

                    pst1.executeUpdate();

                    //Aqui desconta do limite do cartão de crédito se a dispesa for deste tipo
                    if (despesa.getF_pagamento().equals("CRÉDITO")) {

                        String sql2 = "update cartao_credito set credito = (credito - ?), valor_fatura = ( valor_fatura + ?) where n_cartao_credito = ? and conta_id_conta = ?";

                        pst2 = conexao.prepareStatement(sql2);

                        pst2.setFloat(1, despesa.getValor());

                        pst2.setFloat(2, despesa.getValor());

                        pst2.setLong(3, despesa.getNum_cartao());

                        //pst7.setLong(2, despesa.getId_conta());
                        pst2.setLong(4, despesa.getId_conta());

                        pst2.executeUpdate();

                    }

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null, "Erro em DespesaDAO:UpdateDespesa!", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

                    JOptionPane.showMessageDialog(null, e.getMessage());

                    return false;

                } finally {

                    pst1.close();

                }
            } else {
                // usuário não tem esse cartão de credito
                JOptionPane.showMessageDialog(null, "Você não tem esse cartão de Crédito");
                return false;
            }

        } else if (despesa.getF_pagamento().equals("DÉBITO")) {
            if (DespesaTemCartaoDebito(despesa.getNum_cartao(), id_conta)) {
                PreparedStatement pst2 = null;
                String update = "UPDATE despesa set dia = ?, mes = ?, ano = ?, valor = ?, categoria_id = ?, f_pagamento = ?, num_cartao_debito = ?, estatus = ?, descricao = ? WHERE cod_despesa = ?";

                pst2 = conexao.prepareStatement(update);

                try {

                    pst2.setInt(1, despesa.getDia());
                    pst2.setInt(2, despesa.getMes());
                    pst2.setInt(3, despesa.getAno());
                    pst2.setFloat(4, despesa.getValor());
                    pst2.setInt(5, idCategoria);
                    pst2.setString(6, despesa.getF_pagamento());
                    pst2.setLong(7, despesa.getNum_cartao());
                    pst2.setString(8, despesa.getEstatus());
                    pst2.setString(9, despesa.getDescricao());
                    pst2.setInt(10, despesa.getCod_despesa());

                    pst2.executeUpdate();

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null, "Erro em DespesaDAO:UpdateDespesa!", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

                    JOptionPane.showMessageDialog(null, e.getMessage());

                    return false;

                } finally {

                    pst2.close();

                }
            } else {
                JOptionPane.showMessageDialog(null, "Você não tem esse cartão de Débito");
                return false;
            }

        } else {
            // É DINHEIRO
            System.out.println("É dinheiro");
            PreparedStatement pst3 = null;
            String update = "UPDATE despesa set dia = ?, mes = ?, ano = ? , valor = ?, categoria_id= ?, f_pagamento= ?, estatus = ?, descricao= ? where cod_despesa= ?;";
             
            pst3 = conexao.prepareStatement(update);

            try {
                        
                pst3.setInt(1, despesa.getDia());
                pst3.setInt(2, despesa.getMes());
                pst3.setInt(3, despesa.getAno());
                pst3.setFloat(4, despesa.getValor());
                pst3.setInt(5, idCategoria);
                pst3.setString(6, despesa.getF_pagamento());
                pst3.setString(7, despesa.getEstatus());
                pst3.setString(8, despesa.getDescricao());
                pst3.setInt(9, despesa.getCod_despesa());

                pst3.executeUpdate();

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Erro em DespesaDAO:UpdateDespesa!", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

                JOptionPane.showMessageDialog(null, e.getMessage());

                return false;

            } finally {

                pst3.close();

            }

        }

        return true;
    }

    public LinkedList<Despesa> ConsultaDespesa(String tipo, String arg, boolean ordenar, boolean despesas, String id_conta, Receita receita) throws SQLException {
        
        String consulta;

        PreparedStatement pst;
        ResultSet rs;

        LinkedList<Despesa> lista_despesa = new LinkedList<>();
        
        String argumento = "";
        
        if(arg == null || arg.equals("")){
            
            if(ordenar)
                argumento = " ORDER BY " + tipo + " ASC";
            else
                argumento = " ORDER BY " + tipo + " DESC";
            
        }else{
            if (ordenar)
                argumento = " AND " + tipo + " " + "LIKE '%" + arg + "%'" + " ORDER BY " + tipo + " ASC";
            else             
                argumento = " AND " + tipo + " " + "LIKE '%" + arg + "%'" + " ORDER BY " + tipo + " DESC";            
        }
        
        if (despesas) {
          
            consulta = "SELECT "
                    + "des.cod_despesa,"
                    + "des.dia, "
                    + "des.mes, "
                    + "des.ano, "
                    + "des.valor, "
                    + "des.categoria_id, "
                    + "des.f_pagamento, "
                    + "des.num_cartao_credito, "
                    + "des.num_cartao_debito, "
                    + "des_c.n_parcelas, "
                    + "des.estatus, "
                    + "des.descricao, "
                    + "c.categoriaTipo FROM despesa des LEFT OUTER JOIN despesa_credito des_c ON  "
                    + "(des.cod_despesa = des_c.despesa_cod_despesa)  LEFT OUTER JOIN categoria c ON "
                    + "(des.categoria_id = c.categoriaId) WHERE des.conta_id_conta = ? " + argumento;
          
            pst = conexao.prepareStatement(consulta);

            pst.setInt(1, Integer.parseInt(id_conta));

            rs = pst.executeQuery();

            while (rs.next()) 
            {
                if (rs.getString("f_pagamento").equals("CRÉDITO"))
                {
                    lista_despesa.add(
                            new Despesa(
                                    rs.getInt("dia"),
                                    rs.getInt("mes"),
                                    rs.getInt("ano"),
                                    rs.getFloat("valor"),
                                    rs.getString("categoriaTipo"),
                                    rs.getString("f_pagamento"),
                                    rs.getLong("num_cartao_credito"),
                                    rs.getInt("n_parcelas"),
                                    rs.getString("estatus"),
                                    rs.getString("descricao"),
                                    rs.getInt("cod_despesa")
                            )
                    );

                } 
                else if (rs.getString("f_pagamento").equals("DÉBITO")) 
                {
                    lista_despesa.add(
                            new Despesa(
                                    rs.getInt("dia"),
                                    rs.getInt("mes"),
                                    rs.getInt("ano"),
                                    rs.getFloat("valor"),
                                    rs.getString("categoriaTipo"),
                                    rs.getString("f_pagamento"),
                                    rs.getLong("num_cartao_debito"),
                                    rs.getString("estatus"),
                                    rs.getString("descricao"),
                                    rs.getInt("cod_despesa")
                            )
                    );
                } 
                else
                {
                    lista_despesa.add(
                            new Despesa(
                                    rs.getInt("dia"),
                                    rs.getInt("mes"),
                                    rs.getInt("ano"),
                                    rs.getFloat("valor"),
                                    rs.getString("categoriaTipo"),
                                    rs.getString("f_pagamento"),
                                    rs.getString("estatus"),
                                    rs.getString("descricao"),
                                    rs.getInt("cod_despesa")
                            )
                    );
                }
            }

        } else {
            // segunda parte -> melhor fazer outra função
            consulta = "SELECT "
                    + "des.cod_despesa, "
                    + "des.dia, "
                    + "des.mes, "
                    + "des.ano, "
                    + "des.valor,"
                    + "des.categoria_id, "
                    + "des.f_pagamento, "
                    + "des.num_cartao_credito, "
                    + "des.num_cartao_debito, "
                    + "des_c.n_parcelas, des.estatus, "
                    + "des.descricao, "
                    + "c.categoriaTipo FROM despesa des LEFT OUTER JOIN despesa_credito des_c "
                    + "ON (des.cod_despesa = des_c.despesa_cod_despesa) LEFT OUTER JOIN categoria c ON  "
                    + "(des.categoria_id = c.categoriaId) "
                    + "WHERE des.conta_id_conta = ? AND des.mes = ? AND des.ano = ?" +argumento;
           
            pst = conexao.prepareStatement(consulta);

            pst.setInt(1, receita.getId_conta());
            pst.setInt(2, receita.getMes());
            pst.setInt(3, receita.getAno());

            rs = pst.executeQuery();

            while (rs.next()) 
            {
                if (rs.getString("f_pagamento").equals("CRÉDITO")) 
                {
                    lista_despesa.add(
                            new Despesa(
                                    rs.getInt("dia"),
                                    rs.getInt("mes"),
                                    rs.getInt("ano"),
                                    rs.getFloat("valor"),
                                    rs.getString("categoriaTipo"),
                                    rs.getString("f_pagamento"),
                                    rs.getLong("num_cartao_credito"),
                                    rs.getInt("n_parcelas"),
                                    rs.getString("estatus"),
                                    rs.getString("descricao"),
                                    rs.getInt("cod_despesa")
                            )
                    );
                } 
                else if (rs.getString("f_pagamento").equals("DÉBITO")) 
                {
                    lista_despesa.add(
                            new Despesa(
                                    rs.getInt("dia"),
                                    rs.getInt("mes"),
                                    rs.getInt("ano"),
                                    rs.getFloat("valor"),
                                    rs.getString("categoriaTipo"),
                                    rs.getString("f_pagamento"),
                                    rs.getLong("num_cartao_debito"),
                                    rs.getString("estatus"),
                                    rs.getString("descricao"),
                                    rs.getInt("cod_despesa")
                            )
                    );

                } 
                else 
                {
                    lista_despesa.add(
                            new Despesa(
                                    rs.getInt("dia"),
                                    rs.getInt("mes"),
                                    rs.getInt("ano"),
                                    rs.getFloat("valor"),
                                    rs.getString("categoriaTipo"),
                                    rs.getString("f_pagamento"),
                                    rs.getString("estatus"),
                                    rs.getString("descricao"),
                                    rs.getInt("cod_despesa")
                            )
                    );
                }
            }
        }

        pst.close();
        rs.close();
        
        return lista_despesa;
    }
    
    
     public boolean ValidaConsulta_Despes(int mes, int ano) throws SQLException {

        PreparedStatement pst;
        
        String consulta = "SELECT * FROM receita WHERE mes = ? AND ano = ?";

        pst = conexao.prepareStatement(consulta);

        ResultSet rs = null;

        try {

            pst.setInt(1, mes);

            pst.setInt(2, ano);

            rs = pst.executeQuery();

            if (rs.next()) {

                return true;

            } else {

                return false;
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro em DespesaDAO:ValidaConsulta_Despesa!", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        }
    }

    public LinkedList<Despesa> PreencherCampos_Despesa(String dia, String mes, String ano, String categoria, String id_conta) throws SQLException {
        
        String consulta = "select\n"
                        + "des_d.cod_despesa,\n" 
                        + "des_d.dia,\n"
                        + "des_d.mes,\n"
                        + "des_d.ano,\n"
                        + "des.valor,\n"
                        + "des.categoria_id,\n"
                        + "des.f_pagamento,\n"
                        + "des.num_cartao,\n"
                        + "des_c.n_parcelas,\n"
                        + "des.estatus,\n"
                        + "des.descricao,\n"
                        + "C.categoriaTipo\n"
                        + "from despesa des\n"
                        + "        LEFT OUTER JOIN despesa_data des_d on\n"
                        + "        des.despesa_data_cod_despesa = des_d.cod_despesa\n"
                        + "        LEFT OUTER JOIN despesa_credito des_c on\n" 
                        + "        (des.despesa_data_cod_despesa = des_c.despesa_data_cod_despesa)\n"
                        + "    left join categoria C on\n" 
                        + "    (C.categoriaId = des.categoria_id)\n"
                        + "        WHERE des_d.conta_id_conta = ? and des_d.dia = ? and des_d.mes = ? and des_d.ano = ? and C.categoriaTipo = ?\n";
        
        PreparedStatement pst = conexao.prepareStatement(consulta);

        ResultSet rs = null;

        LinkedList<Despesa> lista_despesa = new LinkedList<Despesa>();

        try {

            pst.setInt(1, Integer.parseInt(id_conta));

            pst.setInt(2, Integer.parseInt(dia));

            pst.setInt(3, Integer.parseInt(mes));

            pst.setInt(4, Integer.parseInt(ano));

            pst.setString(5, categoria);

            rs = pst.executeQuery();

            while (rs.next()) {

                lista_despesa.add(
                        new Despesa(
                                rs.getInt("dia"),
                                rs.getInt("mes"),
                                rs.getInt("ano"),
                                rs.getFloat("valor"),
                                //rs.getString("categoria"),categoriaTipo
                                rs.getString("categoriaTipo"),
                                rs.getString("f_pagamento"),
                                rs.getLong("num_cartao"),
                                rs.getInt("n_parcelas"),
                                rs.getString("estatus"),
                                rs.getString("descricao")
                        )
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro em DespesaDAO:PreencherCampos_Despesa!", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, e.getMessage());

        } finally {

            pst.close();

        }

        return lista_despesa;

    }


    */

}
