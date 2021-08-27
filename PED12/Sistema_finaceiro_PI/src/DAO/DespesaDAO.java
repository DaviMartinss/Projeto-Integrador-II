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
import ValidacaoComum.ConsultaDAO;
/**
 *
 * @author Alan
 */
public class DespesaDAO {

    private Connection conexao = null;

    public DespesaDAO() {

        conexao = moduloConexao.conector();

    }

    public void CadastrarDespesa(Despesa despesa) {

        boolean FlagErroCadastroDespesa = true;

        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null;
        PreparedStatement pst3 = null;
        PreparedStatement pst4 = null;
        PreparedStatement pst5 = null;
        PreparedStatement pst6 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;

        int cod_receita = 0;

        String sql1 = "insert into despesa_data (receita_data_cod_receita,dia, mes, ano, conta_id_conta) values(?,?,?,?,?)";

        String sql5 = "select * from receita_data where mes=? and ano=?";

        try {

            pst5 = conexao.prepareStatement(sql5);

            pst5.setInt(1, despesa.getMes());
            pst5.setInt(2, despesa.getAno());

            rs2 = pst5.executeQuery();

            if (rs2.next()) {

                cod_receita = rs2.getInt(1);

            } else {

                JOptionPane.showMessageDialog(null, "ERRO AO CADASTRAR DESPESA");

                FlagErroCadastroDespesa = false;
            }

            pst1 = conexao.prepareStatement(sql1);
            pst1.setInt(1, cod_receita);
            pst1.setInt(2, despesa.getDia());
            pst1.setInt(3, despesa.getMes());
            pst1.setInt(4, despesa.getAno());
            pst1.setInt(5, despesa.getId_conta());

            pst1.executeUpdate();

            int cod_despesa = 0;

            String sql2 = "select * from despesa_data where conta_id_conta = ? and dia=? and mes=? and ano=?";

            pst2 = conexao.prepareStatement(sql2);

            pst2.setInt(1, despesa.getId_conta());
            pst2.setInt(2, despesa.getDia());
            pst2.setInt(3, despesa.getMes());
            pst2.setInt(4, despesa.getAno());

            rs = pst2.executeQuery();

            if (rs.next()) {

                cod_despesa = rs.getInt(1);

                String sql3 = "";

                if (despesa.getF_pagamento().equals("DINHEIRO")) {

                    sql3 = "insert into despesa (despesa_data_cod_despesa, valor, categoria, descricao, f_pagamento, estatus) values(?,?,?,?,?,?)";

                } else {
                    
                    if(despesa.getF_pagamento().equals("DÉBITO")){
                         ConsultaDAO consulta_aux = new ConsultaDAO();
                         if(consulta_aux.CartaoDebitoExiste(despesa.getNum_cartao())){
                             sql3 = "insert into despesa (despesa_data_cod_despesa, valor, categoria, descricao, f_pagamento, num_cartao, estatus) values(?,?,?,?,?,?,?)";
                         }else{
                             
                             FlagErroCadastroDespesa = false;
                         }
                        
                    }else{
                        
                        ConsultaDAO consulta_auxCD = new ConsultaDAO();
                        
                       if(consulta_auxCD.CartaoCreditoExiste(despesa.getNum_cartao())){
                           sql3 = "insert into despesa (despesa_data_cod_despesa, valor, categoria, descricao, f_pagamento, num_cartao, estatus) values(?,?,?,?,?,?,?)";
                       }else{
                           
                           FlagErroCadastroDespesa = false;
                       } 
                        
                    }
                    
                }

                pst3 = conexao.prepareStatement(sql3);
                pst3.setInt(1, cod_despesa);
                pst3.setFloat(2, despesa.getValor());
                pst3.setString(3, despesa.getCategoria());
                pst3.setString(4, despesa.getDescricao());

                pst3.setString(5, despesa.getF_pagamento());

                if (despesa.getF_pagamento().equals("DINHEIRO")) {

                    pst3.setString(6, despesa.getEstatus());

                } else {

                    pst3.setLong(6, despesa.getNum_cartao());

                    pst3.setString(7, despesa.getEstatus());

                }

                pst3.executeUpdate();

                if (despesa.getF_pagamento().equals("CRÉDITO")) {

                    String sql4 = "insert into despesa_credito (n_parcelas, despesa_data_cod_despesa) values(?,?)";

                    pst4 = conexao.prepareStatement(sql4);

                    pst4.setInt(1, despesa.getNum_parcelas());
                    pst4.setInt(2, cod_despesa);

                    pst4.executeUpdate();

                }

            } else {
                JOptionPane.showMessageDialog(null, "ERRO AO CADASTRAR DESPESA");
                FlagErroCadastroDespesa = false;
            }

            if (despesa.getF_pagamento().equals("CRÉDITO")
                    || despesa.getF_pagamento().equals("DÉBITO")
                    || (despesa.getF_pagamento().equals("DINHEIRO") && despesa.getEstatus().equals("PAGO"))) {

                String sql6 = "update receita set total=total-? where conta_id_conta=? and receita_data_cod_receita=?";

                pst6 = conexao.prepareStatement(sql6);

                pst6.setFloat(1, despesa.getValor());
                pst6.setInt(2, despesa.getId_conta());
                pst6.setInt(3, cod_receita);

                pst6.executeUpdate();

            }

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Erro em DespesaDAO:CadastrarDespesa!", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, e.getMessage());

            FlagErroCadastroDespesa = false;

        }

        if (FlagErroCadastroDespesa) {

            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");

        } else {

            JOptionPane.showMessageDialog(null, "Falha ao Cadastrar a Despesa");

        }

    }

   public boolean DespesaTemCartaoCredito(long num_cartao) throws SQLException {

        String consulta = "select n_cartao_credito from cartao_credito where n_cartao_credito = ?";

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
    
    
    public boolean DespesaTemCartaoDebito(long num_cartao) throws SQLException {

        String consulta = "select n_cartao_debito from cartao_debito where n_cartao_debito = ?";

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
    
    public boolean UpdateDespesa(Despesa despesa) throws SQLException {

        if (despesa.getF_pagamento().equals("CRÉDITO")) {
            
            if(DespesaTemCartaoCredito(despesa.getNum_cartao())){    
                PreparedStatement pst1 = null;
                String update = "UPDATE despesa LEFT OUTER JOIN despesa_data on despesa.despesa_data_cod_despesa = despesa_data.cod_despesa LEFT OUTER JOIN despesa_credito on (despesa.despesa_data_cod_despesa = despesa_credito.despesa_data_cod_despesa) SET dia = ?, mes = ?, ano = ?, valor = ?, categoria = ?, f_pagamento = ?, num_cartao=?, n_parcelas = ?, estatus=?, descricao=? where cod_despesa = ?";

                pst1 = conexao.prepareStatement(update);

                try {

                    pst1.setInt(1, despesa.getDia());
                    pst1.setInt(2, despesa.getMes());
                    pst1.setInt(3, despesa.getAno());
                    pst1.setFloat(4, despesa.getValor());
                    pst1.setString(5, despesa.getCategoria());
                    pst1.setString(6, despesa.getF_pagamento());
                    pst1.setLong(7, despesa.getNum_cartao());
                    pst1.setInt(8, despesa.getNum_parcelas());
                    pst1.setString(9, despesa.getEstatus());
                    pst1.setString(10, despesa.getDescricao());
                    pst1.setInt(11, despesa.getCod_despesa());

                    pst1.executeUpdate();

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null, "Erro em DespesaDAO:UpdateDespesa!", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

                    JOptionPane.showMessageDialog(null, e.getMessage());

                    return false;

                } finally {

                    pst1.close();

                }
            }else{
                // usuário não tem esse cartão de credito
                JOptionPane.showMessageDialog(null, "Você não tem esse cartão de Crédito");
                return false;
            }    

        } else if (despesa.getF_pagamento().equals("DÉBITO")) {
            if(DespesaTemCartaoDebito(despesa.getNum_cartao())){ 
                PreparedStatement pst2 = null;
                String update = "update despesa LEFT OUTER JOIN despesa_data on despesa.despesa_data_cod_despesa = despesa_data.cod_despesa set dia = ?, mes = ?, ano = ?, valor = ?, categoria = ?, f_pagamento = ?, num_cartao=?, estatus = ?, descricao = ? where cod_despesa = ?";

                pst2 = conexao.prepareStatement(update);

                try {

                    pst2.setInt(1, despesa.getDia());
                    pst2.setInt(2, despesa.getMes());
                    pst2.setInt(3, despesa.getAno());
                    pst2.setFloat(4, despesa.getValor());
                    pst2.setString(5, despesa.getCategoria());
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
            }else{
                JOptionPane.showMessageDialog(null, "Você não tem esse cartão de Débito");
                return false;
            }

        } else {
            // é dinheiro
            PreparedStatement pst3 = null;
            String update = "UPDATE despesa LEFT OUTER JOIN despesa_data on despesa.despesa_data_cod_despesa = despesa_data.cod_despesa set dia = ?, mes = ?, ano = ?, valor = ?, categoria=?, f_pagamento=?, estatus = ?, descricao=? where cod_despesa= ?";

            pst3 = conexao.prepareStatement(update);

            try {

                pst3.setInt(1, despesa.getDia());
                pst3.setInt(2, despesa.getMes());
                pst3.setInt(3, despesa.getAno());
                pst3.setFloat(4, despesa.getValor());
                pst3.setString(5, despesa.getCategoria());
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

    public boolean DeleteDespesa(Despesa despesa) throws SQLException {

        PreparedStatement pst = null;

        String update = "delete from despesa_data where cod_despesa = ?";

        pst = conexao.prepareStatement(update);

        try {

            pst.setLong(1, despesa.getCod_despesa());

            pst.executeUpdate();

            return true;

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Erro em DespesaDAO:DeleteDespesa!", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        } finally {

            pst.close();

        }
    }

    public LinkedList<Despesa> CarregaTabela_Despesa(int id_conta) throws SQLException {

        String consulta
                = "(select\n"
                + "des_d.cod_despesa,\n"
                + "des_d.dia,\n"
                + "des_d.mes,\n"
                + "des_d.ano,\n"
                + "des.valor,\n"
                + "des.categoria,\n"
                + "des.f_pagamento,\n"
                + "des.num_cartao,\n"
                + "des_c.n_parcelas,\n"
                + "des.estatus,\n"
                + "des.descricao\n"
                + "from despesa des\n"
                + " LEFT OUTER JOIN despesa_data des_d on\n"
                + "	 des.despesa_data_cod_despesa = des_d.cod_despesa\n"
                + " LEFT OUTER JOIN despesa_credito des_c on \n"
                + "	(des.despesa_data_cod_despesa = des_c.despesa_data_cod_despesa)\n"
                + "WHERE des_d.conta_id_conta = ?)";

        ResultSet rs = null;

        PreparedStatement pst = conexao.prepareStatement(consulta);

        LinkedList<Despesa> lista_despesa = new LinkedList<Despesa>();

        try {

            pst.setInt(1, id_conta);

            rs = pst.executeQuery();

            while (rs.next()) {

                lista_despesa.add(new Despesa(
                        rs.getInt("dia"),
                        rs.getInt("mes"),
                        rs.getInt("ano"),
                        rs.getFloat("valor"),
                        rs.getString("categoria"),
                        rs.getString("f_pagamento"),
                        rs.getLong("num_cartao"),
                        rs.getInt("n_parcelas"),
                        rs.getString("estatus"),
                        rs.getString("descricao"),
                        rs.getInt("cod_despesa")
                ));

            }

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Erro em DespesaDAO:CarregaTabela_Despesa!", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, e.getMessage());

        } finally {

            pst.close();

        }

        return lista_despesa;

    }

    public LinkedList<Despesa> Consulta_Despesa(String tipo, String arg, boolean ordenar, Receita receita) throws SQLException {

        String argumento = "";

        if (ordenar) {

            argumento = " and " + tipo + " " + "like '" + arg + "%'" + " ORDER BY " + tipo + " ASC";

        } else {

            argumento = " and " + tipo + " " + "like '" + arg + "%'" + " ORDER BY " + tipo + " DESC";
        }

        String consulta
                = "(select\n"
                + "des_d.cod_despesa,\n"
                + "des_d.dia,\n"
                + "des_d.mes,\n"
                + "des_d.ano,\n"
                + "des.valor,\n"
                + "des.categoria,\n"
                + "des.f_pagamento,\n"
                + "des.num_cartao,\n"
                + "des_c.n_parcelas,\n"
                + "des.estatus,\n"
                + "des.descricao\n"
                + "from despesa des\n"
                + " LEFT OUTER JOIN despesa_data des_d on\n"
                + "	 des.despesa_data_cod_despesa = des_d.cod_despesa\n"
                + " LEFT OUTER JOIN despesa_credito des_c on \n"
                + "	(des.despesa_data_cod_despesa = des_c.despesa_data_cod_despesa)\n"
                + "WHERE des_d.conta_id_conta = ? and des_d.mes = ? and des_d.ano = ?"
                + argumento + " );";

        ResultSet rs = null;

        PreparedStatement pst = conexao.prepareStatement(consulta);

        LinkedList<Despesa> lista_despesa = new LinkedList<Despesa>();

        try {

            pst.setInt(1, receita.getId_conta());
            pst.setInt(2, receita.getMes());
            pst.setInt(3, receita.getAno());

            rs = pst.executeQuery();

            while (rs.next()) {

                lista_despesa.add(
                        new Despesa(
                                rs.getInt("dia"),
                                rs.getInt("mes"),
                                rs.getInt("ano"),
                                rs.getFloat("valor"),
                                rs.getString("categoria"),
                                rs.getString("f_pagamento"),
                                rs.getLong("num_cartao"),
                                rs.getInt("n_parcelas"),
                                rs.getString("estatus"),
                                rs.getString("descricao"),
                                rs.getInt("cod_despesa")
                        )
                );
            }

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Erro em DespesaDAO:Consulta_Despesa!", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, e.getMessage());

        } finally {

            pst.close();

        }

        return lista_despesa;

    }

    public boolean ValidaConsulta_Despesa(int mes, int ano) throws SQLException {

        String consulta = "select * from receita_data where mes = ? and ano = ?";

        PreparedStatement pst = conexao.prepareStatement(consulta);

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

        String consulta
                = "(select\n"
                + "des_d.dia,\n"
                + "des_d.mes,\n"
                + "des_d.ano,\n"
                + "des.valor,\n"
                + "des.categoria,\n"
                + "des.f_pagamento,\n"
                + "des.num_cartao,\n"
                + "des_c.n_parcelas,\n"
                + "des.estatus,\n"
                + "des.descricao\n"
                + "from despesa des\n"
                + " LEFT OUTER JOIN despesa_data des_d on\n"
                + "	 des.despesa_data_cod_despesa = des_d.cod_despesa\n"
                + " LEFT OUTER JOIN despesa_credito des_c on \n"
                + "	(des.despesa_data_cod_despesa = des_c.despesa_data_cod_despesa)\n"
                + "WHERE des_d.conta_id_conta = ? and des_d.dia = ? and des_d.mes = ? and des_d.ano = ? and des.categoria = ?);";

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
                                rs.getString("categoria"),
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

    public boolean DespesaExiste(Despesa despesa) throws SQLException {

        String consulta1 = "select \n"
                         + "dia, \n"
                         + "mes, \n"
                         + "ano, \n"
                         + "categoria \n"
                         + "from \n"
                         + "despesa_data, despesa \n"
                         + "where despesa.despesa_data_cod_despesa = despesa_data.cod_despesa \n"
                         + "and dia = ? and mes = ? and ano = ? and categoria = ? and conta_id_conta = ?;";

        PreparedStatement pst1 = conexao.prepareStatement(consulta1);

        ResultSet rs1 = null;

        try {

            pst1.setInt(1, despesa.getDia());

            pst1.setInt(2, despesa.getMes());

            pst1.setInt(3, despesa.getAno());
            
            pst1.setString(4, despesa.getCategoria());

            pst1.setInt(5, despesa.getId_conta());

            rs1 = pst1.executeQuery();

            if (rs1.next()) {

                return true;
                
            } 

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Erro em DespesaDAO:DespesaExiste!", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        }
        
        return false;

    }
}
