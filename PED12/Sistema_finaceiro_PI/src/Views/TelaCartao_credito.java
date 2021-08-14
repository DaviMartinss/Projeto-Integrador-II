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

/**
 *
 * @author pc
 */
public class TelaCartao_credito extends javax.swing.JFrame {

    /**
     * Creates new form TelaCartao_credito
     */
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
   
    TelaCartaoCredito_consulta Tela_CartaoConsulta;
    
    public TelaCartao_credito() {
        initComponents();
        conexao = moduloConexao.conector();
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
     
    void sair(){
         //telaCliente cadastroCliente = new telaCliente();
         TelaLogin tela_login = new TelaLogin();
         tela_login.setVisible(true);
         this.dispose();
    }
    
   void TelaConsulta_CartaoCredito(){
        
        TelaCartaoCredito_consulta TelaConsulta = null;

        if (TelaConsulta == null) {

            TelaConsulta = new TelaCartaoCredito_consulta();

            TelaConsulta.setVisible(true);

            TelaConsulta.receberID(txt_id.getText());

        } else {

            TelaConsulta.setVisible(true);

            TelaConsulta.setState(TelaCartaoCredito_cadastrar.NORMAL);

            TelaConsulta.receberID(txt_id.getText());

        }

        this.dispose();
        
    }
    
    void receita(){
        
         TelaReceita TelaReceita = null;

           if (TelaReceita == null) {

               TelaReceita = new TelaReceita();

               TelaReceita.setVisible(true);
               
               TelaReceita.receberID(txt_id.getText());

           } else {

               TelaReceita.setVisible(true);

               TelaReceita.setState(TelaPrincipal.NORMAL);

               TelaReceita.receberID(txt_id.getText());
                
           }
           
         this.dispose();
    }
    
      void cadastra_cartao(){
          
         TelaCartaoCredito_cadastrar TelaCadastra_CartaoCredito = null;
     
           if (TelaCadastra_CartaoCredito == null) {

               TelaCadastra_CartaoCredito = new  TelaCartaoCredito_cadastrar();

               TelaCadastra_CartaoCredito.setVisible(true);
               
               TelaCadastra_CartaoCredito.receberID(txt_id.getText());

           } else {

               TelaCadastra_CartaoCredito.setVisible(true);

               TelaCadastra_CartaoCredito.setState(TelaPrincipal.NORMAL);

               TelaCadastra_CartaoCredito.receberID(txt_id.getText());
                
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

        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btn_inicio_CC = new javax.swing.JButton();
        btn_receitaCC = new javax.swing.JButton();
        btn_sairCC = new javax.swing.JButton();
        btn_novoCartao_cc = new javax.swing.JButton();
        btnConsulta_CC = new javax.swing.JButton();
        btn_exclui_cc = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 500));
        getContentPane().setLayout(null);

        jTextField1.setText("Usuário");
        getContentPane().add(jTextField1);
        jTextField1.setBounds(30, 100, 52, 20);

        jTextField2.setText("Cartão de Crédito");
        getContentPane().add(jTextField2);
        jTextField2.setBounds(30, 140, 105, 20);

        jLabel2.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        jLabel2.setText("Cartão de Crédito");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(260, 0, 170, 24);

        btn_inicio_CC.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_inicio_CC.setText("Inicio");
        btn_inicio_CC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inicio_CCActionPerformed(evt);
            }
        });
        getContentPane().add(btn_inicio_CC);
        btn_inicio_CC.setBounds(20, 50, 63, 25);

        btn_receitaCC.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_receitaCC.setText("Receitas");
        btn_receitaCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_receitaCCActionPerformed(evt);
            }
        });
        getContentPane().add(btn_receitaCC);
        btn_receitaCC.setBounds(120, 50, 83, 25);

        btn_sairCC.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_sairCC.setText("Sair");
        btn_sairCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sairCCActionPerformed(evt);
            }
        });
        getContentPane().add(btn_sairCC);
        btn_sairCC.setBounds(620, 50, 55, 25);

        btn_novoCartao_cc.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_novoCartao_cc.setText("Novo Cartão");
        btn_novoCartao_cc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoCartao_ccActionPerformed(evt);
            }
        });
        getContentPane().add(btn_novoCartao_cc);
        btn_novoCartao_cc.setBounds(480, 50, 101, 25);

        btnConsulta_CC.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnConsulta_CC.setText("Consulta");
        btnConsulta_CC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulta_CCActionPerformed(evt);
            }
        });
        getContentPane().add(btnConsulta_CC);
        btnConsulta_CC.setBounds(230, 50, 83, 25);

        btn_exclui_cc.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_exclui_cc.setText("Exclui cartão");
        getContentPane().add(btn_exclui_cc);
        btn_exclui_cc.setBounds(340, 50, 107, 25);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/teste_icon_pedmeia1.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(530, 300, 140, 110);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fundo_principal.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1920, 1080);

        txt_id.setEditable(false);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        getContentPane().add(txt_id);
        txt_id.setBounds(307, 137, 81, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_sairCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sairCCActionPerformed
        // TODO add your handling code here:
        sair();
    }//GEN-LAST:event_btn_sairCCActionPerformed

    private void btn_inicio_CCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inicio_CCActionPerformed
        // TODO add your handling code here:
        inicio();
    }//GEN-LAST:event_btn_inicio_CCActionPerformed

    private void btn_receitaCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_receitaCCActionPerformed
        // TODO add your handling code here:
        receita();
    }//GEN-LAST:event_btn_receitaCCActionPerformed

    private void btn_novoCartao_ccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoCartao_ccActionPerformed
        // TODO add your handling code here:
        cadastra_cartao();
    }//GEN-LAST:event_btn_novoCartao_ccActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void btnConsulta_CCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulta_CCActionPerformed
        // TODO add your handling code here:
        TelaConsulta_CartaoCredito();
        //Tela_CartaoConsulta.receberID(txt_id.getText());
    }//GEN-LAST:event_btnConsulta_CCActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCartao_credito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCartao_credito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCartao_credito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCartao_credito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCartao_credito().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsulta_CC;
    private javax.swing.JButton btn_exclui_cc;
    private javax.swing.JButton btn_inicio_CC;
    private javax.swing.JButton btn_novoCartao_cc;
    private javax.swing.JButton btn_receitaCC;
    private javax.swing.JButton btn_sairCC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables


    public void receberID(String recebe){

        txt_id.setText(recebe);
    }


}
