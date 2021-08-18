/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;
import DAO.UsuarioDAO;
import java.sql.*;
import DAO.moduloConexao;
import Model.Usuario;
import javax.swing.JOptionPane;
/**
 *
 * @author pc
 */
public class TelaUsuario_cadastrar extends javax.swing.JFrame {

    /**
     * Creates new form TelaUsuario_cadastrar
     */
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaUsuario_cadastrar() {
        initComponents();
        //conexao = moduloConexao.conector();
        this.setLocationRelativeTo(null);
        
    }
    
    void volta_telaLogin(){
        
         TelaLogin telaDeLogin= new TelaLogin();
         telaDeLogin.setVisible(true);
         this.dispose();
         
    }
    
    
    private void cadastro_cliente(){

        Usuario novo_usuario = new Usuario(txtNome.getText(), txtEmail.getText(), txtSenha.getText());
        
        UsuarioDAO userDAO = new UsuarioDAO();
        
        try {
            
            userDAO.CadastrarUsuario(novo_usuario);
            
            volta_telaLogin();
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
  
    }
    
    void VoltarLogin(){
         //telaCliente cadastroCliente = new telaCliente();
         TelaLogin tela_login = new TelaLogin();
         tela_login.setVisible(true);
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
        btnVoltar = new javax.swing.JButton();
        txtNome = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtSenha = new javax.swing.JTextField();
        txtAvatar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnRealizarCadastro = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel1.setText("Nome");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 100, 50, 27);

        jLabel2.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel2.setText("Email");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 160, 50, 27);

        jLabel3.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel3.setText("Senha");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 220, 60, 27);

        btnVoltar.setBackground(new java.awt.Color(105, 69, 219));
        btnVoltar.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnVoltar.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });
        getContentPane().add(btnVoltar);
        btnVoltar.setBounds(30, 50, 100, 27);

        txtNome.setBackground(new java.awt.Color(187, 210, 240));
        txtNome.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtNome);
        txtNome.setBounds(30, 120, 400, 27);

        txtEmail.setBackground(new java.awt.Color(187, 210, 240));
        txtEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtEmail);
        txtEmail.setBounds(30, 180, 400, 27);

        txtSenha.setBackground(new java.awt.Color(187, 210, 240));
        txtSenha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtSenha);
        txtSenha.setBounds(30, 240, 400, 27);

        txtAvatar.setBackground(new java.awt.Color(187, 210, 240));
        txtAvatar.setToolTipText("");
        txtAvatar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtAvatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAvatarActionPerformed(evt);
            }
        });
        getContentPane().add(txtAvatar);
        txtAvatar.setBounds(30, 300, 400, 27);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/teste_icon_pedmeia1.png"))); // NOI18N
        getContentPane().add(jLabel7);
        jLabel7.setBounds(550, 270, 140, 120);

        jLabel6.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        jLabel6.setText("Cadastro de Usuário");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(240, 0, 190, 26);

        jLabel4.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel4.setText("Avatar");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 280, 60, 27);

        btnRealizarCadastro.setBackground(new java.awt.Color(105, 69, 219));
        btnRealizarCadastro.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnRealizarCadastro.setForeground(new java.awt.Color(255, 255, 255));
        btnRealizarCadastro.setText("Realizar cadastro");
        btnRealizarCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarCadastroActionPerformed(evt);
            }
        });
        getContentPane().add(btnRealizarCadastro);
        btnRealizarCadastro.setBounds(290, 350, 138, 27);

        jLabel5.setBackground(new java.awt.Color(187, 210, 240));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fundo_principal.png"))); // NOI18N
        jLabel5.setText("jLabel5");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 1968, 1080);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAvatarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAvatarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAvatarActionPerformed

    private void btnRealizarCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarCadastroActionPerformed
        // TODO add your handling code here:
        
        if(  txtNome.getText().isEmpty() || txtEmail.getText().isEmpty()   ||
             txtSenha.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "Todos campos são de preenchimento obrigatório!");
            
        }else{
            
            cadastro_cliente();

        } 
        
    }//GEN-LAST:event_btnRealizarCadastroActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // TODO add your handling code here:
        
        VoltarLogin();
        
        
    }//GEN-LAST:event_btnVoltarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaUsuario_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaUsuario_cadastrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRealizarCadastro;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtAvatar;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtSenha;
    // End of variables declaration//GEN-END:variables
}
