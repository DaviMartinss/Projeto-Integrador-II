/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

import br.com.infox.dal.moduloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import br.com.infox.dal.Cartao_debito;
/**
 *
 * @author pc
 */
public class TelaCartaoDebito_cadastrar extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    boolean flag_Suc_valorAtual = true;
    boolean flag_Suc_bandeira = true;
    
    
    public TelaCartaoDebito_cadastrar() {
        initComponents();
        conexao = moduloConexao.conector();
    }
    
    void voltaTelaCartao_Debito(){

          TelaCartao_debito TelaCartao_debito = null;

        if (TelaCartao_debito == null) {

            TelaCartao_debito = new TelaCartao_debito();

            TelaCartao_debito.setVisible(true);

            TelaCartao_debito.receberID(txt_id.getText());

        } else {

            TelaCartao_debito.setVisible(true);

            TelaCartao_debito.setState(TelaPrincipal.NORMAL);

            TelaCartao_debito.receberID(txt_id.getText());

        }

        this.dispose();

    }
    
    
   public void cadastro_cartao_debito(){
        
         String id_conta = txt_id.getText();
         
         int Id_conta_BD = Integer.parseInt(id_conta);

        String sql = "insert into cartao_debito (n_cartao_debito,valor_atual, bandeira, conta_id_conta) values(?,?, ?, ?)";
        
        try {
            
            pst = conexao.prepareStatement(sql);

            pst.setString(1, txt_numCartDeb.getText());
            pst.setString(2, txt_valorCartaoDeb.getText());
            pst.setString(3, txt_BandCartDeb.getText());
            pst.setInt(4, Id_conta_BD);
            
            int n_cartao_debito = Integer.parseInt(txt_numCartDeb.getText());
            float valor_atual = Float.parseFloat(txt_valorCartaoDeb.getText());
            String bandeira = txt_BandCartDeb.getText();
                    
            Cartao_debito cartao_aux = new Cartao_debito(n_cartao_debito, valor_atual, bandeira);
            if(!(cartao_aux.verifica_Bandeira_cartao_deb())){
                flag_Suc_bandeira = false;
                JOptionPane.showMessageDialog(null, "A bandeira deve conter apenas letras");
                
            }
            
            if(!(cartao_aux.verifica_valor_atual())){
                flag_Suc_valorAtual = false;
                JOptionPane.showMessageDialog(null, "O valor atual deve ser positivo");
                
                
            }
            if(flag_Suc_bandeira && flag_Suc_valorAtual){
                pst.executeUpdate();
            }
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar o cartão de debito");
   
        }
        
        if(flag_Suc_bandeira && flag_Suc_valorAtual){
            
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
        }else{
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar o cartão de debito");
        }
        
        voltaTelaCartao_Debito();
        
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_numCartDeb = new javax.swing.JTextField();
        txt_BandCartDeb = new javax.swing.JTextField();
        btn_cadastraCartDeb = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_valorCartaoDeb = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Número do cartão");

        jLabel2.setText("Bandeira");

        txt_BandCartDeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BandCartDebActionPerformed(evt);
            }
        });

        btn_cadastraCartDeb.setText("Cadastrar");
        btn_cadastraCartDeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadastraCartDebActionPerformed(evt);
            }
        });

        jLabel3.setText("Valor Atual");

        txt_id.setEditable(false);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_numCartDeb)
                            .addComponent(txt_BandCartDeb)
                            .addComponent(txt_valorCartaoDeb, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(btn_cadastraCartDeb)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_numCartDeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txt_valorCartaoDeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_BandCartDeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(btn_cadastraCartDeb)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cadastraCartDebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastraCartDebActionPerformed
        cadastro_cartao_debito();
    }//GEN-LAST:event_btn_cadastraCartDebActionPerformed

    private void txt_BandCartDebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BandCartDebActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BandCartDebActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCartaoDebito_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCartaoDebito_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCartaoDebito_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCartaoDebito_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCartaoDebito_cadastrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cadastraCartDeb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txt_BandCartDeb;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_numCartDeb;
    private javax.swing.JTextField txt_valorCartaoDeb;
    // End of variables declaration//GEN-END:variables



    public void receberID(String recebe){

        txt_id.setText(recebe);
    }


}
