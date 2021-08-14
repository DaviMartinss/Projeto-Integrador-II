/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAO.UsuarioDAO;
import Model.CartaoCredito;
import DAO.moduloConexao;
import DAO.CartaoCreditoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.*;
import Views.TelaLogin;

/**
 *
 * @author pc
 */

public class TelaCartaoCredito_cadastrar extends javax.swing.JFrame {
    
    //Usuario usuarioConta = new Usuario();

    public TelaCartaoCredito_cadastrar() {

        initComponents();
        this.setLocationRelativeTo(null);

    }
    void inicio(){
         
         TelaPrincipal TelaPrincipal = null;
         
         if (TelaPrincipal == null) {

             TelaPrincipal = new TelaPrincipal();

             TelaPrincipal.setVisible(true);

             TelaPrincipal.receberID(txt_id.getText());

         } else {

             TelaPrincipal.setVisible(true);

             TelaPrincipal.setState(TelaPrincipal.NORMAL);

             TelaPrincipal.receberID(txt_id.getText());
         }
         
         this.dispose();
    }

    void volta_telaCartaoDeCredito() {
       
        TelaCartao_credito TelaCartao_credito = null;

        if (TelaCartao_credito == null) {

            TelaCartao_credito = new TelaCartao_credito();

            TelaCartao_credito.setVisible(true);

            TelaCartao_credito.receberID(txt_id.getText());

        } else {

            TelaCartao_credito.setVisible(true);

            TelaCartao_credito.setState(TelaPrincipal.NORMAL);

            TelaCartao_credito.receberID(txt_id.getText());

        }

        this.dispose();
    }

    public void cadastro_cartao_credito() {

        CartaoCredito cartao_c = new CartaoCredito(
                Long.parseLong(txt_NumCC.getText()),
                Float.parseFloat(txt_LimiteCC.getText()),
                Integer.parseInt(txt_DiaFaturaCC.getText()),
                Float.parseFloat(txt_ValorFatura.getText()),
                txt_BandeiraCC.getText(),
                Integer.parseInt(txt_id.getText())
        );

        CartaoCreditoDAO cartao_creditoDAO = new CartaoCreditoDAO();

        try {
            
            if (cartao_c.varifica_valor_fatura()
                    && cartao_c.verifica_bandeira_credito()
                    && cartao_c.verifica_dia_fatura()
                    && cartao_c.verifica_limite())
            {
                
                cartao_creditoDAO.CadastrarCartaoCredito(cartao_c);
                
                volta_telaCartaoDeCredito();
                
            }else{
                
                JOptionPane.showMessageDialog(null, "Dados Inválidos!!");
                
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_id = new javax.swing.JTextField();
        txt_NumCC = new javax.swing.JTextField();
        txt_DiaFaturaCC = new javax.swing.JTextField();
        txt_BandeiraCC = new javax.swing.JTextField();
        txt_LimiteCC = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_inicio = new javax.swing.JButton();
        btn_cadastra_cartao = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txt_ValorFatura = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        txt_id.setEditable(false);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cartão de Crédito");
        setPreferredSize(new java.awt.Dimension(700, 500));
        getContentPane().setLayout(null);
        getContentPane().add(txt_NumCC);
        txt_NumCC.setBounds(150, 60, 400, 27);
        getContentPane().add(txt_DiaFaturaCC);
        txt_DiaFaturaCC.setBounds(150, 240, 400, 27);

        txt_BandeiraCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BandeiraCCActionPerformed(evt);
            }
        });
        getContentPane().add(txt_BandeiraCC);
        txt_BandeiraCC.setBounds(150, 300, 400, 27);

        txt_LimiteCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_LimiteCCActionPerformed(evt);
            }
        });
        getContentPane().add(txt_LimiteCC);
        txt_LimiteCC.setBounds(150, 120, 400, 27);

        jLabel1.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel1.setText("Número do cartão");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(150, 40, 102, 16);

        jLabel2.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel2.setText("Limite");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(150, 100, 35, 14);

        jLabel4.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel4.setText("Dia da Fatura");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(150, 220, 90, 16);

        jLabel6.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel6.setText("Bandeira");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(150, 280, 60, 16);

        btn_inicio.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_inicio.setText("Início");
        btn_inicio.setMaximumSize(new java.awt.Dimension(68, 30));
        btn_inicio.setMinimumSize(new java.awt.Dimension(68, 30));
        btn_inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inicioActionPerformed(evt);
            }
        });
        getContentPane().add(btn_inicio);
        btn_inicio.setBounds(150, 370, 63, 25);

        btn_cadastra_cartao.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_cadastra_cartao.setText("Cadastra cartao");
        btn_cadastra_cartao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadastra_cartaoActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cadastra_cartao);
        btn_cadastra_cartao.setBounds(420, 370, 130, 25);

        jLabel7.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel7.setText("Valor da Fatura");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(150, 160, 86, 20);
        getContentPane().add(txt_ValorFatura);
        txt_ValorFatura.setBounds(150, 180, 400, 27);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fundo_principal.png"))); // NOI18N
        jLabel8.setText("jLabel8");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(0, 0, 1958, 1080);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cadastra_cartaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastra_cartaoActionPerformed
        // TODO add your handling code here:
        
        if( txt_NumCC.getText().isEmpty() || txt_LimiteCC.getText().isEmpty() ||
            txt_ValorFatura.getText().isEmpty() || txt_DiaFaturaCC.getText().isEmpty() ||
            txt_BandeiraCC.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "Todos campos são de preenchimento obrigatório!");
            
        }else{
            
            cadastro_cartao_credito();

        }

    }//GEN-LAST:event_btn_cadastra_cartaoActionPerformed

    private void txt_LimiteCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_LimiteCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_LimiteCCActionPerformed

    private void txt_BandeiraCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BandeiraCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BandeiraCCActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void btn_inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inicioActionPerformed
        // TODO add your handling code here:
        inicio();
    }//GEN-LAST:event_btn_inicioActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCartaoCredito_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCartaoCredito_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCartaoCredito_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCartaoCredito_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCartaoCredito_cadastrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cadastra_cartao;
    private javax.swing.JButton btn_inicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txt_BandeiraCC;
    private javax.swing.JTextField txt_DiaFaturaCC;
    private javax.swing.JTextField txt_LimiteCC;
    private javax.swing.JTextField txt_NumCC;
    private javax.swing.JTextField txt_ValorFatura;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables

    public void receberID(String recebe) {

        txt_id.setText(recebe);
    }

}
