/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAO.moduloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan
 */
public class TelaDespesa_cadastrar extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst1 = null;
    PreparedStatement pst2 = null;
    PreparedStatement pst3 = null;
    PreparedStatement pst4 = null;
    PreparedStatement pst5 = null;
    PreparedStatement pst6 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    
    boolean FlagErroCadastroDespesa = true;
    
    
    /**
     * Creates new form TelaDespesa_cadastrar
     */
    public TelaDespesa_cadastrar() {
        initComponents();
        conexao = moduloConexao.conector();
        txtParcelas.setEnabled(false);
        txt_NumCartao.setEnabled(false);
        this.setLocationRelativeTo(null);
    }
    
    
    public void cadastrar_despesa() {

        String id_conta = txt_id.getText();
        
        int cod_receita = 0;

        int Id_conta_BD = Integer.parseInt(id_conta);

        String sql1 = "insert into despesa_data (receita_data_cod_receita,dia, mes, ano) values(?,?,?,?)";
        
        String sql5 = "select * from receita_data where mes=? and ano=?";
        
        try {
            
            pst5 = conexao.prepareStatement(sql5);
                    
            pst5.setString(1, txtMes.getText());
            pst5.setString(2, txtAno.getText());
            
            rs2 = pst5.executeQuery();
            
            if(rs2.next()){
                
                cod_receita = rs2.getInt(1);
            
            }else {
                
                JOptionPane.showMessageDialog(null, "ERRO AO CADASTRAR DESPESA");
                
                FlagErroCadastroDespesa = false;
            }

            pst1 = conexao.prepareStatement(sql1);
            pst1.setInt(1, cod_receita);
            pst1.setString(2, txtDia.getText());
            pst1.setString(3, txtMes.getText());
            pst1.setString(4, txtAno.getText());
            
            pst1.executeUpdate();
            
            int cod_despesa = 0;
                
            String sql2 = "select * from despesa_data where dia=? and mes=? and ano=?";

            pst2 = conexao.prepareStatement(sql2);
            pst2.setString(1, txtDia.getText());
            pst2.setString(2, txtMes.getText());
            pst2.setString(3, txtAno.getText());

            rs = pst2.executeQuery();
            
            if(rs.next()){
                
                cod_despesa = rs.getInt(1);

                String sql3 = "insert into despesa (despesa_data_cod_despesa, valor, categoria, descricao, f_pagamento, cartao_debito_n_cartao_debito, cartao_credito_n_cartao_credito, estatus, conta_id_conta) values(?,?,?,?,?,?,?,?,?)";

                pst3 = conexao.prepareStatement(sql3);
                pst3.setInt(1, cod_despesa);
                pst3.setFloat(2, Float.parseFloat(txtValor.getText()));
                pst3.setString(3, txtCategoria.getText());
                pst3.setString(4, txtAreaDescricao.getText());

                if (rbDebito.isSelected()) {

                    pst3.setString(5, "DÉBITO");
                    
                    pst3.setLong(6, Long.parseLong(txt_NumCartao.getText()));
                    
                    pst3.setString(7, null);

                }else if(rbCredito.isSelected()) {

                    pst3.setString(5, "CRÉDITO");
                    
                    pst3.setString(6, null);
                    
                    pst3.setLong(7, Long.parseLong(txt_NumCartao.getText()));

                }else{
                    
                    
                    pst3.setString(5, "DINHEIRO");
                    
                    pst3.setString(6, null);
                    pst3.setString(7, null);
                    
                }

                if (rbPago.isSelected()) {

                    pst3.setString(8, "PAGO");

                } else {

                    pst3.setString(8, "NÃO PAGO");

                }          

                pst3.setInt(9, Id_conta_BD);

                pst3.executeUpdate();

                if (rbCredito.isSelected()) {

                    String sql4 = "insert into despesa_credito (n_parcelas, despesa_data_cod_despesa) values(?,?)";

                    pst4 = conexao.prepareStatement(sql4);
                    
                    pst4.setString(1, txtParcelas.getText());
                    pst4.setInt(2, cod_despesa);
                    
                    pst4.executeUpdate();

                }

            }else{
                JOptionPane.showMessageDialog(null, "ERRO AO CADASTRAR DESPESA");
                FlagErroCadastroDespesa = false;
            }

            String sql6 = "update receita set total=total-? where conta_id_conta=? and receita_data_cod_receita=?";

            pst6 = conexao.prepareStatement(sql6);

            pst6.setFloat(1, Float.parseFloat(txtValor.getText()));
            pst6.setInt(2, Integer.parseInt(txt_id.getText()));
            pst6.setInt(3, cod_receita);

            pst6.executeUpdate();

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
            
            FlagErroCadastroDespesa = false;
            
        }

        
        if(FlagErroCadastroDespesa){
            
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
            Volta_TelaDespesa();
            
        }else{
            
            JOptionPane.showMessageDialog(null, "Falha ao Cadastrar a Despesa");
            
        }
        
    }
    
    void Volta_TelaDespesa() {

        TelaDespesa TelaDespesa = null;

        if (TelaDespesa== null) {

            TelaDespesa = new TelaDespesa();

            TelaDespesa.setVisible(true);

            TelaDespesa.receberID(txt_id.getText());

        } else {

            TelaDespesa.setVisible(true);

            TelaDespesa.setState(TelaPrincipal.NORMAL);

            TelaDespesa.receberID(txt_id.getText());

        }

        this.dispose();
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtAno = new javax.swing.JTextField();
        txtCategoria = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        rbCredito = new javax.swing.JRadioButton();
        rbDebito = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        txtParcelas = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        rbPago = new javax.swing.JRadioButton();
        rbNaoPago = new javax.swing.JRadioButton();
        txtValor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaDescricao = new javax.swing.JTextArea();
        txtDia = new javax.swing.JTextField();
        txtMes = new javax.swing.JTextField();
        btn_CadastrarDespesa = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        rbDinheiro = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        txt_NumCartao = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().add(txtAno);
        txtAno.setBounds(2227, 116, 81, 21);
        getContentPane().add(txtCategoria);
        txtCategoria.setBounds(30, 170, 400, 27);

        jLabel8.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel8.setText("Forma de Pagamento: ");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(30, 370, 135, 17);

        rbCredito.setBackground(new java.awt.Color(142, 185, 236));
        rbCredito.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        rbCredito.setText("Crédito");
        rbCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCreditoActionPerformed(evt);
            }
        });
        getContentPane().add(rbCredito);
        rbCredito.setBounds(100, 390, 80, 25);

        rbDebito.setBackground(new java.awt.Color(142, 185, 236));
        rbDebito.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        rbDebito.setText("Débito");
        rbDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDebitoActionPerformed(evt);
            }
        });
        getContentPane().add(rbDebito);
        rbDebito.setBounds(30, 390, 70, 25);

        jLabel9.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel9.setText("Nº de Pacelas: ");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(250, 420, 87, 17);
        getContentPane().add(txtParcelas);
        txtParcelas.setBounds(250, 440, 50, 27);

        txt_id.setEditable(false);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        getContentPane().add(txt_id);
        txt_id.setBounds(2322, 50, 81, 21);

        jLabel1.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel1.setText("Valor:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 100, 41, 25);

        jLabel2.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel2.setText("Categoria: ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 150, 65, 17);

        jLabel3.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel3.setText("Descrição: ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 200, 65, 17);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Dia: ");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(2185, 50, 30, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Mês:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(2179, 89, 32, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Ano:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(2181, 116, 30, 20);

        jLabel7.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel7.setText("Status: ");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(30, 320, 45, 17);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DAO/teste_icon_pedmeia1.png"))); // NOI18N
        getContentPane().add(jLabel13);
        jLabel13.setBounds(520, 300, 150, 120);

        jButton1.setText("Início");
        getContentPane().add(jButton1);
        jButton1.setBounds(30, 50, 86, 27);

        rbPago.setBackground(new java.awt.Color(142, 185, 236));
        rbPago.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        rbPago.setText("PAGO");
        rbPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPagoActionPerformed(evt);
            }
        });
        getContentPane().add(rbPago);
        rbPago.setBounds(30, 340, 60, 25);

        rbNaoPago.setBackground(new java.awt.Color(142, 185, 236));
        rbNaoPago.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        rbNaoPago.setText("NÃO PAGO");
        rbNaoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNaoPagoActionPerformed(evt);
            }
        });
        getContentPane().add(rbNaoPago);
        rbNaoPago.setBounds(90, 340, 90, 25);
        getContentPane().add(txtValor);
        txtValor.setBounds(30, 120, 400, 27);

        txtAreaDescricao.setColumns(20);
        txtAreaDescricao.setRows(5);
        txtAreaDescricao.setPreferredSize(new java.awt.Dimension(400, 54));
        jScrollPane1.setViewportView(txtAreaDescricao);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 220, 400, 81);
        getContentPane().add(txtDia);
        txtDia.setBounds(2227, 50, 69, 21);
        getContentPane().add(txtMes);
        txtMes.setBounds(2227, 89, 71, 21);

        btn_CadastrarDespesa.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_CadastrarDespesa.setText("Cadastrar Despesa");
        btn_CadastrarDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CadastrarDespesaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_CadastrarDespesa);
        btn_CadastrarDespesa.setBounds(280, 50, 147, 27);

        jLabel10.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        jLabel10.setText("CADASTRO DE DESPESA");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(240, 0, 230, 26);

        rbDinheiro.setBackground(new java.awt.Color(142, 185, 236));
        rbDinheiro.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        rbDinheiro.setText("Dinheiro");
        rbDinheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDinheiroActionPerformed(evt);
            }
        });
        getContentPane().add(rbDinheiro);
        rbDinheiro.setBounds(180, 390, 78, 25);

        jLabel11.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel11.setText("Nº do Cartão: ");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(30, 420, 82, 17);
        getContentPane().add(txt_NumCartao);
        txt_NumCartao.setBounds(30, 440, 200, 27);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DAO/fundo_principal.png"))); // NOI18N
        getContentPane().add(jLabel12);
        jLabel12.setBounds(0, 0, 1920, 1080);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDebitoActionPerformed
        // TODO add your handling code here:

        if(rbDebito.isSelected()){

            txt_NumCartao.setEnabled(true);

            rbDinheiro.setSelected(false);
            
            txtParcelas.setEnabled(false);

            rbCredito.setSelected(false);

        }
    }//GEN-LAST:event_rbDebitoActionPerformed

    private void rbCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCreditoActionPerformed
        // TODO add your handling code here:

        if(rbCredito.isSelected()){
            
            txt_NumCartao.setEnabled(true);
            
            rbDinheiro.setSelected(false);

            txtParcelas.setEnabled(true);

            rbDebito.setSelected(false);

        }
        
        
        if(rbCredito.isSelected() == false){

            txtParcelas.setEnabled(false);

        }

    }//GEN-LAST:event_rbCreditoActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void rbPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPagoActionPerformed
        // TODO add your handling code here:
        if(rbPago.isSelected()){

            rbNaoPago.setSelected(false);

        }

    }//GEN-LAST:event_rbPagoActionPerformed

    private void rbNaoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNaoPagoActionPerformed
        // TODO add your handling code here:

        if(rbNaoPago.isSelected()){

            rbPago.setSelected(false);

        }
    }//GEN-LAST:event_rbNaoPagoActionPerformed

    private void btn_CadastrarDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CadastrarDespesaActionPerformed
        // TODO add your handling code here:
        
        if(  txtValor.getText().isEmpty() || txtCategoria.getText().isEmpty()   ||
             txtDia.getText().isEmpty() ||
             txtMes.getText().isEmpty() || txtAno.getText().isEmpty()           || 
            (rbPago.isSelected() == false && rbNaoPago.isSelected() == false)   ||
            (rbDebito.isSelected() == false && rbCredito.isSelected() == false && rbDinheiro.isSelected() == false)||
            ((rbDebito.isSelected() == true || rbCredito.isSelected() == true) && txt_NumCartao.getText().isEmpty() )||
            (rbCredito.isSelected() && txtParcelas.getText().isEmpty())){
            
            JOptionPane.showMessageDialog(null, "Todos campos são de preenchimento obrigatório!");
            
        }else{
            
            cadastrar_despesa();

        } 
        
    }//GEN-LAST:event_btn_CadastrarDespesaActionPerformed

    private void rbDinheiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDinheiroActionPerformed
        // TODO add your handling code here:
        
        if(rbDinheiro.isSelected()){

            rbCredito.setSelected(false);
            rbDebito.setSelected(false);
            txt_NumCartao.setEnabled(false);
            txtParcelas.setEnabled(false);

        }
    }//GEN-LAST:event_rbDinheiroActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaDespesa_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDespesa_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDespesa_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDespesa_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaDespesa_cadastrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_CadastrarDespesa;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbCredito;
    private javax.swing.JRadioButton rbDebito;
    private javax.swing.JRadioButton rbDinheiro;
    private javax.swing.JRadioButton rbNaoPago;
    private javax.swing.JRadioButton rbPago;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextArea txtAreaDescricao;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtDia;
    private javax.swing.JTextField txtMes;
    private javax.swing.JTextField txtParcelas;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txt_NumCartao;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables


    public void receberID(String recebe) {

        txt_id.setText(recebe);
    
    }


}
