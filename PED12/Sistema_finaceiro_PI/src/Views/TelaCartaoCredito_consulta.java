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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class TelaCartaoCredito_consulta extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaCartaoCredito_consulta() {
        initComponents();
        conexao = moduloConexao.conector();
        this.setLocationRelativeTo(null);
    }
    
    private void consultar(){
       String sql = "select * from cartao_credito where conta_id_conta =?";
        
       try {
           String id = txt_id.getText();
           pst = conexao.prepareStatement(sql);
           pst.setString(1, txt_id.getText());
           rs=pst.executeQuery();
           
           if(rs.next()){
               txt_SetNumeroCC.setText(rs.getString(1));
               txt_SetLimiteCC.setText(rs.getString(2));
               txt_DiaFatCC.setText(rs.getString(3));
               txt_valorFatCC1.setText(rs.getString(4));
               txt_SetBandeira.setText(rs.getString(5));
           }
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao realizar consulta");
        }
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
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_DiaFatCC = new javax.swing.JTextField();
        txt_SetBandeira = new javax.swing.JTextField();
        txt_SetNumeroCC = new javax.swing.JTextField();
        txt_SetLimiteCC = new javax.swing.JTextField();
        txt_valorFatCC1 = new javax.swing.JTextField();
        btn_Consultar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JToggleButton();
        btnDeletar = new javax.swing.JToggleButton();
        jLabel6 = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtConsultaCC = new javax.swing.JTable();
        txt_id = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel1.setText("Número");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 70, 67, 17);

        jLabel2.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel2.setText("Limite");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 120, 67, 17);

        jLabel3.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel3.setText("Dia da fatura");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 170, 67, 17);

        jLabel4.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel4.setText("Valor da fatura");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 220, 67, 17);

        jLabel5.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel5.setText("Bandeira");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 270, 67, 17);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DAO/teste_icon_pedmeia1.png"))); // NOI18N
        getContentPane().add(jLabel8);
        jLabel8.setBounds(520, 90, 140, 120);
        getContentPane().add(txt_DiaFatCC);
        txt_DiaFatCC.setBounds(30, 190, 400, 27);

        txt_SetBandeira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SetBandeiraActionPerformed(evt);
            }
        });
        getContentPane().add(txt_SetBandeira);
        txt_SetBandeira.setBounds(30, 290, 400, 27);
        getContentPane().add(txt_SetNumeroCC);
        txt_SetNumeroCC.setBounds(30, 90, 400, 27);

        txt_SetLimiteCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SetLimiteCCActionPerformed(evt);
            }
        });
        getContentPane().add(txt_SetLimiteCC);
        txt_SetLimiteCC.setBounds(30, 140, 400, 27);

        txt_valorFatCC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_valorFatCC1ActionPerformed(evt);
            }
        });
        getContentPane().add(txt_valorFatCC1);
        txt_valorFatCC1.setBounds(30, 240, 400, 27);

        btn_Consultar.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_Consultar.setText("Consultar");
        btn_Consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ConsultarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Consultar);
        btn_Consultar.setBounds(200, 30, 93, 27);

        btnAlterar.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnAlterar.setText("Alterar");
        getContentPane().add(btnAlterar);
        btnAlterar.setBounds(110, 30, 78, 27);

        btnDeletar.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnDeletar.setText("Deletar");
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });
        getContentPane().add(btnDeletar);
        btnDeletar.setBounds(310, 30, 90, 27);

        jLabel6.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        jLabel6.setText("Consulta de Cartão de Crédito");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(220, 0, 280, 20);

        btnVoltar.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });
        getContentPane().add(btnVoltar);
        btnVoltar.setBounds(30, 30, 71, 27);

        jtConsultaCC.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jtConsultaCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Cartão Crédito", "Limite", "Dia da Fatura", "Valor da Fatura", "Bandeira"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtConsultaCC);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 330, 640, 137);

        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        getContentPane().add(txt_id);
        txt_id.setBounds(520, 340, 136, 21);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DAO/fundo_principal.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(0, 0, 1968, 1080);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_idActionPerformed

    private void txt_SetBandeiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SetBandeiraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SetBandeiraActionPerformed

    private void txt_SetLimiteCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SetLimiteCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SetLimiteCCActionPerformed

    private void txt_valorFatCC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_valorFatCC1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_valorFatCC1ActionPerformed

    private void btn_ConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ConsultarActionPerformed
        consultar();
    }//GEN-LAST:event_btn_ConsultarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // TODO add your handling code here:

        volta_telaCartaoDeCredito();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
        DefaultTableModel mp1 = (DefaultTableModel) jtConsultaCC.getModel();
        
        int l = mp1.getRowCount();
        
        if(l>0){
            while(l>0){
                //Limpa tabela sempre que for fazer uma nova consulta
                ((DefaultTableModel) jtConsultaCC.getModel()).removeRow(l - 1);
                
                //Menos um pois a primeira linha é a linha zero
                l--;
            }
        }
        
        try{
            
            String pesquisa = "select * from cartao_credito where conta_id_conta=?";

            pst = conexao.prepareStatement(pesquisa);
            
            pst.setInt(1, Integer.parseInt(txt_id.getText()));

            DefaultTableModel mp = (DefaultTableModel) jtConsultaCC.getModel();
            
            rs = pst.executeQuery();    

            while(rs.next()) {
               
                
                
                String Col0 = rs.getString("n_cartao_credito");
                String Col1 = rs.getString("limite");
                String Col2 = rs.getString("dia_fatura");
                String Col3 = rs.getString("valor_fatura");
                String Col4 = rs.getString("bandeira");
                
                
                //Redimensiona a tabela
                //TamanhoColunas();
                
                mp.addRow(new String[] {Col0,Col1,Col2,Col3,Col4});
                
            }
            
            
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(this, e.getMessage());
            
        }
        
        jtConsultaCC.setAutoCreateRowSorter(true);
        
    
        
        
    }//GEN-LAST:event_formWindowOpened

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeletarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCartaoCredito_consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCartaoCredito_consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCartaoCredito_consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCartaoCredito_consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCartaoCredito_consulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnAlterar;
    private javax.swing.JToggleButton btnDeletar;
    private javax.swing.JToggleButton btnVoltar;
    private javax.swing.JButton btn_Consultar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtConsultaCC;
    private javax.swing.JTextField txt_DiaFatCC;
    private javax.swing.JTextField txt_SetBandeira;
    private javax.swing.JTextField txt_SetLimiteCC;
    private javax.swing.JTextField txt_SetNumeroCC;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_valorFatCC1;
    // End of variables declaration//GEN-END:variables

     public void receberID(String recebe){

        txt_id.setText(recebe);
    }
}
