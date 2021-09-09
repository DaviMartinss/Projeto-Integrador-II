/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAO.UsuarioDAO;
import javax.swing.JOptionPane;
import Model.Usuario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class TelaLogin extends javax.swing.JFrame {
    /**
     * Creates new form TelaLogin
     */
    
    public TelaLogin() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    TelaPrincipal TelaPrincipal;
    
    public void logar() throws SQLException{   
        
        Usuario user = new Usuario(txtEmail.getText(), txtSenha.getText());

        UsuarioDAO userDAO = new UsuarioDAO();
        
//        boolean logar = userDAO.logar(user);
        
        try {
            
            if(userDAO.logar(user)){

                if (TelaPrincipal == null) {

                    TelaPrincipal = new TelaPrincipal();

                    TelaPrincipal.setVisible(true);

                    String id = Integer.toString(user.getId_conta());

                    TelaPrincipal.receberID(id);
                    
                } else {

                    TelaPrincipal.setVisible(true);

                    TelaPrincipal.setState(TelaPrincipal.NORMAL);

                    String id = Integer.toString(user.getId_conta());

                    TelaPrincipal.receberID(id);
                }
                
                this.dispose();

            }else{
                JOptionPane.showMessageDialog(null, "Usuario ou senha inválido");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    void cadastro(){
         TelaUsuario_cadastrar cadastroCliente = new TelaUsuario_cadastrar();
         cadastroCliente.setVisible(true);
         this.dispose();
    }
    
    
    /*
    public int consultaIdconta(){
        
        int id_conta = -1;
        try {
            
             id_conta=rs.getInt(1);
                
            SalvaIdconta(id_conta);
        } catch (Exception e) {
         
            JOptionPane.showMessageDialog(null, e);
        }
        
        return id_conta;
        
    }
    */
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        btnCadastro = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel5.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        jLabel5.setText("Bem Vinde ao Ped1/2");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(300, 0, 220, 26);

        jLabel1.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel1.setText("Email");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(200, 190, 60, 27);

        jLabel2.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel2.setText("Senha");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(200, 260, 50, 27);

        txtEmail.setBackground(new java.awt.Color(187, 210, 240));
        txtEmail.setAlignmentX(1.0F);
        txtEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        getContentPane().add(txtEmail);
        txtEmail.setBounds(200, 210, 400, 27);
        txtEmail.getAccessibleContext().setAccessibleName("Digite seu e-mail ou login");
        txtEmail.getAccessibleContext().setAccessibleDescription("Digite aqui o seu e-mail ou seu login");

        btnLogin.setBackground(new java.awt.Color(105, 69, 219));
        btnLogin.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Entrar");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin);
        btnLogin.setBounds(200, 330, 150, 27);

        btnCadastro.setBackground(new java.awt.Color(105, 69, 219));
        btnCadastro.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnCadastro.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastro.setText("Novo Cadastro");
        btnCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastro);
        btnCadastro.setBounds(450, 330, 150, 27);

        txtSenha.setBackground(new java.awt.Color(187, 210, 240));
        txtSenha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(txtSenha);
        txtSenha.setBounds(200, 280, 400, 27);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/teste_icon_pedmeia1.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(320, 70, 130, 100);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fundo_principal.png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 1920, 1080);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
       
        try {
            logar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao logar");
        }

    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroActionPerformed
        cadastro();
      
        
    }//GEN-LAST:event_btnCadastroActionPerformed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaActionPerformed

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastro;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables


}
