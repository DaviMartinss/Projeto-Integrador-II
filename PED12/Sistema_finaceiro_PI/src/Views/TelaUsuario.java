/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAO.UsuarioDAO;
import DAO.moduloConexao;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alan
 */
public class TelaUsuario extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    /**
     * Creates new form TelaUsuario_consulta
     */
    public TelaUsuario() {
        
        initComponents();
        conexao = moduloConexao.conector();
        this.setLocationRelativeTo(null);
        
        txtNome.setEditable(false);
        txtEmail.setEditable(false);
        txtSenha.setEditable(false);
        
        txt_id.setVisible(false);
        
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
    
     void TelaUsuario_senha(){
         
         TelaUsuario_senha TelaUser_senha = null;
         
         if (TelaUser_senha == null) {

             TelaUser_senha = new TelaUsuario_senha();

             TelaUser_senha.setVisible(true);

             TelaUser_senha.receberID(txt_id.getText());

         } else {

             TelaUser_senha.setVisible(true);

             TelaUser_senha.setState(TelaPrincipal.NORMAL);

             TelaUser_senha.receberID(txt_id.getText());
         }
         
         this.dispose();
    }
     
    void telaUpdateUser(){
     
            int id = Integer.parseInt(this.txt_id.getText());
            
            Usuario user = new Usuario(
                id,
                txtNome.getText(),
                txtEmail.getText()
                
                
        );
          System.out.println("O nome do usuario é " +txtNome.getText());
          System.out.println("O email do usuario é " +txtEmail.getText());
        UsuarioDAO UserDao = new UsuarioDAO();

        try {
            UserDao.UpdateUser(user);
            

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

        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JTextField();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JToggleButton();
        btn_update = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();

        jButton2.setText("jButton2");

        jButton3.setText("jButton3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 500));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        jLabel2.setText("Dados do Usuário");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(250, 0, 180, 24);

        jButton1.setBackground(new java.awt.Color(105, 69, 219));
        jButton1.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Início");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(30, 40, 110, 25);

        jLabel3.setText("Nome: ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 80, 34, 14);

        txtNome.setBackground(new java.awt.Color(187, 210, 240));
        txtNome.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtNome);
        txtNome.setBounds(30, 100, 400, 27);

        jLabel4.setText("Email:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 140, 60, 14);

        txtEmail.setBackground(new java.awt.Color(187, 210, 240));
        txtEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtEmail);
        txtEmail.setBounds(30, 160, 400, 27);

        jLabel5.setText("Senha:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 200, 70, 20);

        txtSenha.setBackground(new java.awt.Color(187, 210, 240));
        txtSenha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtSenha);
        txtSenha.setBounds(30, 220, 400, 27);

        btnAlterar.setBackground(new java.awt.Color(105, 69, 219));
        btnAlterar.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnAlterar.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterar.setText("Alterar Senha");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAlterar);
        btnAlterar.setBounds(530, 310, 140, 27);

        btnExcluir.setBackground(new java.awt.Color(210, 59, 233));
        btnExcluir.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnExcluir.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluir.setText("Excluir");
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(530, 350, 140, 27);

        btn_update.setBackground(new java.awt.Color(105, 69, 219));
        btn_update.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_update.setForeground(new java.awt.Color(255, 255, 255));
        btn_update.setText("Alterar");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_update);
        btn_update.setBounds(530, 270, 140, 27);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fundo_principal.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1920, 1080);
        getContentPane().add(txt_id);
        txt_id.setBounds(500, 160, 60, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
        UsuarioDAO usuario = new UsuarioDAO();

        ResultSet rs = null;

        try {

            rs = usuario.PreencherCampos_Usuario(txt_id.getText());

            if(rs.next()){

                txtNome.setText(rs.getString("nome"));
                txtEmail.setText(rs.getString("email"));
                txtSenha.setText(rs.getString("senha"));

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(this, "Erro ao inserir os dados nos campos!!");
        }
        
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        inicio();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        TelaUsuario_senha();
        /*
        txtNome.setEnabled(true);
        txtEmail.setEnabled(true);
        txtSenha.setEnabled(true);
       */
        
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        
        if(btn_update.getText().equals("Alterar")){
            
            btn_update.setText("Atualizar");
            
            txtNome.setEditable(true);
            txtEmail.setEditable(true);
            txtSenha.setEditable(true);
        
        }else{
            
            btn_update.setText("Alterar");
            
            txtNome.setEditable(false);
            txtEmail.setEditable(false);
            txtSenha.setEditable(false);
            
            telaUpdateUser();
            
        }
        
        
    }//GEN-LAST:event_btn_updateActionPerformed

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
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JToggleButton btnExcluir;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtSenha;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables

    public void receberID(String recebe){

        txt_id.setText(recebe);
    }



}
