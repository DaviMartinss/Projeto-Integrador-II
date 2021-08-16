/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Despesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

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
            
            if(rs2.next()){
                
                cod_receita = rs2.getInt(1);
            
            }else {
                
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
            
            if(rs.next()){
                
                cod_despesa = rs.getInt(1);
                
                String sql3 = "";
                
                if (despesa.getF_pagamento().equals("DINHEIRO")){
                    
                    sql3 = "insert into despesa (despesa_data_cod_despesa, valor, categoria, descricao, f_pagamento, estatus) values(?,?,?,?,?,?)";

                }else{
                    
                    sql3 = "insert into despesa (despesa_data_cod_despesa, valor, categoria, descricao, f_pagamento, num_cartao, estatus) values(?,?,?,?,?,?,?)";
                
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

            }else{
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
            
            JOptionPane.showMessageDialog(null, e);

            FlagErroCadastroDespesa = false;
            
        }

        
        if(FlagErroCadastroDespesa){
            
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
            
        }else{
            
            JOptionPane.showMessageDialog(null, "Falha ao Cadastrar a Despesa");
            
        }
        
    }
}
