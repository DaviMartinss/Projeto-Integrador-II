/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.ControlerCartaoCredito;
import Model.CartaoCredito;
import Utilities.Validacao;
import com.mysql.cj.util.StringUtils;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */

public class TelaCartaoCredito_cadastrar extends javax.swing.JFrame {
 
    public TelaCartaoCredito_cadastrar() {

        initComponents();
        this.setLocationRelativeTo(null);

        txt_id.setVisible(false);
    }
    
    void TelaPrincipal(){
         
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

    void TelaCartaoCredito() {
       
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

    public void CadastrarCartaoCredito() {
        
        try {

            if (!StringUtils.isNullOrEmpty(txt_NumCC.getText())
                    && !StringUtils.isNullOrEmpty(txt_LimiteCC.getText())
                    && !StringUtils.isNullOrEmpty(txt_ValorFatura.getText())
                    && !StringUtils.isNullOrEmpty(txt_DiaFaturaCC.getText())
                    && !StringUtils.isNullOrEmpty(txt_BandeiraCC.getText())) {

                if (!(Validacao.isNumeric(txt_NumCC.getText())
                        && Validacao.isNumeric(txt_ValorFatura.getText())
                        && Validacao.isNumeric(txt_LimiteCC.getText())
                        && Validacao.isNumeric(txt_DiaFaturaCC.getText()))) {
                    JOptionPane.showMessageDialog(this, "Informe um valor numérico válido!!", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                boolean cadastra = true;

                CartaoCredito cartaoCC = 
                        new CartaoCredito.CartaoCreditoBuild
                        (
                            Long.parseLong(txt_NumCC.getText()))
                            .Limite(Float.parseFloat(txt_LimiteCC.getText()))
                            .DiaFatura(Integer.parseInt(txt_DiaFaturaCC.getText()))
                            .ValorFatura(Float.parseFloat(txt_ValorFatura.getText()))
                            .Bandeira(txt_BandeiraCC.getText())
                            .IdConta(Integer.parseInt(txt_id.getText())
                        ).build();

                if (ControlerCartaoCredito.CartaoCreditoExiste(cartaoCC)) {

                    JOptionPane.showMessageDialog(this, "Número do cartão de crédito já existe", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                    cadastra = false;
                }

                if (!(cartaoCC.ValidaNUM_Cartao(txt_NumCC.getText()))) {

                    JOptionPane.showMessageDialog(this, "Número do cartão de crédito inválido", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                    cadastra = false;
                }

                if (!(cartaoCC.verifica_bandeira())) {

                    JOptionPane.showMessageDialog(this, "Bandeira inválida", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                    cadastra = false;
                }

                if (cartaoCC.getValor_fatura() < 0 || cartaoCC.getLimite() < cartaoCC.getValor_fatura()) {

                    JOptionPane.showMessageDialog(this, "Valor da fatura inválido", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                    cadastra = false;
                }

                if (!(cartaoCC.verifica_dia_fatura())) {

                    JOptionPane.showMessageDialog(this, "Dia da fatura inválido", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                    cadastra = false;
                }

                if (!(cartaoCC.verifica_limite())) {

                    JOptionPane.showMessageDialog(this, "Limite do cartão de crédito inválido", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                    cadastra = false;
                }

                if (cadastra) {

                    ControlerCartaoCredito.CadastrarCartaoCredito(cartaoCC);
                    JOptionPane.showMessageDialog(this, "Cadastrado com sucesso!", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                    TelaCartaoCredito();
                    
                }
                
            } else {

                JOptionPane.showMessageDialog(this, "Todos campos são de preenchimento obrigatório!", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);

            }

        } catch (HeadlessException | NumberFormatException | NullPointerException e) {
            
            JOptionPane.showMessageDialog(this, "Error!" + e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
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

        pageTitle = new javax.swing.JLabel();
        txt_NumCC = new javax.swing.JTextField();
        txt_DiaFaturaCC = new javax.swing.JTextField();
        txt_BandeiraCC = new javax.swing.JTextField();
        txt_LimiteCC = new javax.swing.JTextField();
        txt_ValorFatura = new javax.swing.JTextField();
        cardNum = new javax.swing.JLabel();
        cardLimit = new javax.swing.JLabel();
        invoiceValue = new javax.swing.JLabel();
        invoiceDay = new javax.swing.JLabel();
        cardFlag = new javax.swing.JLabel();
        btn_inicio = new javax.swing.JButton();
        btnCadastraCartao = new javax.swing.JButton();
        background = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cartão de Crédito");
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        pageTitle.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        pageTitle.setText("Cadastrar Cartão de Crédito");
        getContentPane().add(pageTitle);
        pageTitle.setBounds(280, 0, 260, 27);

        txt_NumCC.setBackground(new java.awt.Color(187, 210, 240));
        txt_NumCC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_NumCC);
        txt_NumCC.setBounds(210, 120, 400, 27);

        txt_DiaFaturaCC.setBackground(new java.awt.Color(187, 210, 240));
        txt_DiaFaturaCC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_DiaFaturaCC);
        txt_DiaFaturaCC.setBounds(210, 300, 400, 27);

        txt_BandeiraCC.setBackground(new java.awt.Color(187, 210, 240));
        txt_BandeiraCC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt_BandeiraCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BandeiraCCActionPerformed(evt);
            }
        });
        getContentPane().add(txt_BandeiraCC);
        txt_BandeiraCC.setBounds(210, 360, 400, 27);

        txt_LimiteCC.setBackground(new java.awt.Color(187, 210, 240));
        txt_LimiteCC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt_LimiteCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_LimiteCCActionPerformed(evt);
            }
        });
        getContentPane().add(txt_LimiteCC);
        txt_LimiteCC.setBounds(210, 180, 400, 27);

        txt_ValorFatura.setBackground(new java.awt.Color(187, 210, 240));
        txt_ValorFatura.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_ValorFatura);
        txt_ValorFatura.setBounds(210, 240, 400, 27);

        cardNum.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        cardNum.setText("Número do cartão");
        getContentPane().add(cardNum);
        cardNum.setBounds(210, 100, 120, 27);

        cardLimit.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        cardLimit.setText("Limite");
        getContentPane().add(cardLimit);
        cardLimit.setBounds(210, 160, 60, 27);

        invoiceValue.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        invoiceValue.setText("Valor da Fatura");
        getContentPane().add(invoiceValue);
        invoiceValue.setBounds(210, 220, 100, 27);

        invoiceDay.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        invoiceDay.setText("Dia da Fatura");
        getContentPane().add(invoiceDay);
        invoiceDay.setBounds(210, 280, 90, 27);

        cardFlag.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        cardFlag.setText("Bandeira");
        getContentPane().add(cardFlag);
        cardFlag.setBounds(210, 340, 70, 27);

        btn_inicio.setBackground(new java.awt.Color(105, 69, 219));
        btn_inicio.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_inicio.setForeground(new java.awt.Color(255, 255, 255));
        btn_inicio.setText("Início");
        btn_inicio.setMaximumSize(new java.awt.Dimension(68, 30));
        btn_inicio.setMinimumSize(new java.awt.Dimension(68, 30));
        btn_inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inicioActionPerformed(evt);
            }
        });
        getContentPane().add(btn_inicio);
        btn_inicio.setBounds(30, 40, 100, 27);

        btnCadastraCartao.setBackground(new java.awt.Color(105, 69, 219));
        btnCadastraCartao.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnCadastraCartao.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastraCartao.setText("Cadastra cartao");
        btnCadastraCartao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastraCartaoActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastraCartao);
        btnCadastraCartao.setBounds(210, 410, 150, 27);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Back-2.png"))); // NOI18N
        background.setText("jLabel8");
        getContentPane().add(background);
        background.setBounds(0, 0, 1920, 1080);

        txt_id.setEditable(false);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        getContentPane().add(txt_id);
        txt_id.setBounds(0, 0, 6, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastraCartaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastraCartaoActionPerformed
        // TODO add your handling code here:
            
        CadastrarCartaoCredito();
        
    }//GEN-LAST:event_btnCadastraCartaoActionPerformed

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
        TelaPrincipal();
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
        java.awt.EventQueue.invokeLater(() -> {
            new TelaCartaoCredito_cadastrar().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btnCadastraCartao;
    private javax.swing.JButton btn_inicio;
    private javax.swing.JLabel cardFlag;
    private javax.swing.JLabel cardLimit;
    private javax.swing.JLabel cardNum;
    private javax.swing.JLabel invoiceDay;
    private javax.swing.JLabel invoiceValue;
    private javax.swing.JLabel pageTitle;
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