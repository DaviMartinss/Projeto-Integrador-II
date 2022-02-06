/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.ControlerReceita;
import Model.Receita;
import ValidacaoComum.Validacao;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan
 */
public class TelaAtualizarReceita extends javax.swing.JFrame {
    
    private Receita receita = null;

    /**
     * Creates new form TelaConsultaReceita
     */
    public TelaAtualizarReceita() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    
    void TelaReceita() {

        TelaReceita TelaReceita = null;

        if (TelaReceita == null) {

            TelaReceita = new TelaReceita();
            TelaReceita.setVisible(true);
            TelaReceita.receberID(Integer.toString(receita.getId_conta()));

        } else {

            TelaReceita.setVisible(true);
            TelaReceita.setState(TelaPrincipal.NORMAL);
             TelaReceita.receberID(Integer.toString(receita.getId_conta()));
        }

        this.dispose();
    }
    
    void AtualizarReceita() {
        
        Validacao valida = new Validacao();
        Receita receita_aux = new Receita();

        //USAR EXPRESSÕES LAMBDA PARA RESOLVER ISSO OU EXPRESSOES REGULARES
        if (!(
                valida.ehNum(txt_dia.getText()) && 
                valida.ehNum(txt_mes.getText()) && 
                valida.ehNum(txt_ano.getText()) && 
                valida.ehNum(txt_total.getText())
              )
            ) 
        {
            JOptionPane.showMessageDialog(this, "Informe um valor numérico!!", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!(receita_aux.UpdateEhVazio(txt_dia.getText(), txt_mes.getText(), txt_ano.getText(), txt_total.getText()))) {

            Receita receita_atual
                    = new Receita
                          (
                            Integer.parseInt(txt_dia.getText()),
                            Integer.parseInt(txt_mes.getText()),
                            Integer.parseInt(txt_ano.getText()),
                            Float.parseFloat(txt_total.getText()),
                            receita.getId_conta(),
                            this.receita.getMes(),
                            this.receita.getAno()
                          );

            try {
                //MUDAR NOME DESSES MÉTODOS DE VALIDAÇÃO
                if (receita_atual.Update_CamposValidos(txt_dia.getText(), txt_mes.getText(), txt_ano.getText(), txt_total.getText())) {

                    //Não deixa atualizar para o MÊS E ANO de uma receita PRÉ-EXISTENTE
                    if (!ControlerReceita.ReceitaExiste(receita_atual.getMes(), receita_atual.getAno(), receita_atual.getId_conta())) {

                        ControlerReceita.AtualizarReceita(receita_atual);

                    } else {

                        JOptionPane.showMessageDialog(this, "Já existe uma receita com MÊS e ANO informados!\n Não foi possível atualizar!", "ALERTA", JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Valor Inválido", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                }

            } catch (HeadlessException e) {

                JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum campo pode ser nulo", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
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

        findByTitle1 = new javax.swing.JLabel();
        btnReceitas = new javax.swing.JButton();
        totalTitle = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        dateTitle = new javax.swing.JLabel();
        txt_dia = new javax.swing.JTextField();
        txt_mes = new javax.swing.JTextField();
        dayBarTitle = new javax.swing.JLabel();
        monthBarTitle = new javax.swing.JLabel();
        txt_ano = new javax.swing.JTextField();
        btn_update = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        findByTitle1.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        findByTitle1.setText("Atualizar Receitas");

        btnReceitas.setBackground(new java.awt.Color(105, 69, 219));
        btnReceitas.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnReceitas.setForeground(new java.awt.Color(255, 255, 255));
        btnReceitas.setText("Voltar");
        btnReceitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceitasActionPerformed(evt);
            }
        });

        totalTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        totalTitle.setText("Total: ");

        txt_total.setBackground(new java.awt.Color(187, 210, 240));
        txt_total.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        txt_total.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt_total.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_total.setName(""); // NOI18N
        txt_total.setPreferredSize(new java.awt.Dimension(10, 40));
        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });

        dateTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        dateTitle.setText("Data (dd/mm/aaaa)");

        txt_dia.setBackground(new java.awt.Color(187, 210, 240));
        txt_dia.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        txt_dia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt_dia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_diaActionPerformed(evt);
            }
        });

        txt_mes.setBackground(new java.awt.Color(187, 210, 240));
        txt_mes.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        txt_mes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        dayBarTitle.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        dayBarTitle.setText("/");

        monthBarTitle.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        monthBarTitle.setText("/");

        txt_ano.setBackground(new java.awt.Color(187, 210, 240));
        txt_ano.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        txt_ano.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        btn_update.setBackground(new java.awt.Color(105, 69, 219));
        btn_update.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_update.setForeground(new java.awt.Color(255, 255, 255));
        btn_update.setText("Atualizar");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(txt_mes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(txt_ano, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(monthBarTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(dayBarTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_dia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(149, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(btnReceitas, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(findByTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(203, 203, 203))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(findByTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(totalTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_mes, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ano, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_dia, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(monthBarTitle)
                            .addComponent(dayBarTitle))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReceitas, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReceitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceitasActionPerformed
        // TODO add your handling code here:
        TelaReceita();
    }//GEN-LAST:event_btnReceitasActionPerformed

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void txt_diaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_diaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_diaActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        
            AtualizarReceita();
            TelaReceita();
           
    }//GEN-LAST:event_btn_updateActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:

        txt_total.setText(Float.toString(receita.getTotal()));
        txt_dia.setText(Integer.toString(receita.getDia()));
        txt_mes.setText(Integer.toString(receita.getMes()));
        txt_ano.setText(Integer.toString(receita.getAno()));

    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(TelaAtualizarReceita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAtualizarReceita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAtualizarReceita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAtualizarReceita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TelaAtualizarReceita().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReceitas;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel dateTitle;
    private javax.swing.JLabel dayBarTitle;
    private javax.swing.JLabel findByTitle1;
    private javax.swing.JLabel monthBarTitle;
    private javax.swing.JLabel totalTitle;
    private javax.swing.JTextField txt_ano;
    private javax.swing.JTextField txt_dia;
    private javax.swing.JTextField txt_mes;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables

public void receberReceita(Receita receita) {
    
   this.receita = receita;
}

}
