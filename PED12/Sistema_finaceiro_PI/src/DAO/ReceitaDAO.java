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

    public void CadastrarReceita(Receita receita){

        int cod_receita = 0;

        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null;
        PreparedStatement pst3 = null;
        PreparedStatement pst4 = null;
        PreparedStatement pst5 = null;
        PreparedStatement pst6 = null;
        PreparedStatement pst7 = null;
        
        ResultSet rs1 = null;
        
        ResultSet rs2 = null;
        
        ResultSet rs3 = null;
        
        ResultSet rs4 = null;
        
        String sql1 = "select COUNT(cod_receita) qtd_receita from receita_data";
        
        String sql2 = "select mes, ano, conta_id_conta from receita_data rdt left join receita R on\n"
                     + " (rdt.cod_receita = R.receita_data_cod_receita)\n"
                     + "where cod_receita = (select max(cod_receita) from receita_data) and R.conta_id_conta = ?;";
        
        String sql3 = "select cod_despesa, dia, mes, ano, estatus\n"
                     + "from \n"
                     + "(despesa des left join despesa_data dt on des.despesa_data_cod_despesa = dt.cod_despesa)\n"
                     + "where (estatus <> \"PAGO\" and mes = ? and ano = ? and conta_id_conta = ?);";

        String sql4 = "update despesa_data set mes = ?, ano = ? where (cod_despesa = ? and conta_id_conta = ?)";
        
        String sql5 = "insert into receita_data (dia, mes, ano) values(?,?,?)";

        String sql6 = "select * from receita_data where dia = ? and mes = ? and ano = ?";

        String sql7 = "insert into receita (receita_data_cod_receita, total, conta_id_conta) values(?,?,?)";

        try{
            
            pst1 = conexao.prepareStatement(sql1);
                
                rs1 = pst1.executeQuery();
                
                if(rs1.next()){
                    
                    if (rs1.getInt("qtd_receita") > 0) {

                        pst2 = conexao.prepareStatement(sql2);

                        pst2.setInt(1, receita.getId_conta());

                        rs2 = pst2.executeQuery();

                        if (rs2.next()) {

                            pst3 = conexao.prepareStatement(sql3);

                            pst3.setInt(1, rs2.getInt("mes"));
                            pst3.setInt(2, rs2.getInt("ano"));
                            pst3.setInt(3, receita.getId_conta());

                            rs3 = pst3.executeQuery();

                            //Np == Não Paga
                            LinkedList<Despesa> lista_despesaNp = new LinkedList();

                            while (rs3.next()) {

                                Despesa despesa_aux = new Despesa();

                                despesa_aux.setMes(receita.getMes());

                                despesa_aux.setMes(receita.getAno());

                                despesa_aux.setCod_despesa(rs3.getInt("cod_despesa"));
                                
                                despesa_aux.setId_conta(receita.getId_conta());

                                lista_despesaNp.add(despesa_aux);

                            }
                            
                            pst4 = conexao.prepareStatement(sql4);
                            
                            for (Despesa despesa : lista_despesaNp) {

                                pst4.setInt(1, receita.getMes());
                                pst4.setInt(2, receita.getAno());
                                pst4.setInt(3, despesa.getCod_despesa());
                                pst4.setInt(4, receita.getId_conta());
                                
                                pst4.executeUpdate();

                            }
                            
                        }

                    }
                
                }
            
                //return true;
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e.getMessage() + "ERRO NA TRANSFERENCIA DE DESPESA");
            
            //return false;
            
        }
       

        try {

            pst5 = conexao.prepareStatement(sql5);
            
            pst5.setInt(1, receita.getDia());
            pst5.setInt(2, receita.getMes());
            pst5.setInt(3, receita.getAno());

            pst5.executeUpdate();

            pst6 = conexao.prepareStatement(sql6);

            pst6.setInt(1, receita.getDia());
            pst6.setInt(2, receita.getMes());
            pst6.setInt(3, receita.getAno());

            rs4 = pst6.executeQuery();

            if (rs4.next()) {

                cod_receita = rs4.getInt(1);
                    
                pst7 = conexao.prepareStatement(sql7);

                pst7.setInt(1, cod_receita);
                pst7.setFloat(2, receita.getTotal());
                pst7.setInt(3, receita.getId_conta());

                pst7.executeUpdate();

                //return true;

            } else {

                JOptionPane.showMessageDialog(null, "ERRO CADASTRO DE RECEITA");
                //return false;
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

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
