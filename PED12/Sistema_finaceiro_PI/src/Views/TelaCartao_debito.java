/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAO.CartaoDebitoDAO;
import DAO.moduloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class TelaCartao_debito extends javax.swing.JFrame {

    /**
     * Creates new form TelaCartao_debito
     */
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaCartao_debito() {
        initComponents();
         conexao = moduloConexao.conector();
         this.setLocationRelativeTo(null);
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
       
    void sair(){
         //telaCliente cadastroCliente = new telaCliente();
         TelaLogin tela_login = new TelaLogin();
         tela_login.setVisible(true);
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
    
     void cadastraCartao_Deb(){
         
         TelaCartaoDebito_cadastrar TelaCadastra_CartaoDebito = null;
                 
         if (TelaCadastra_CartaoDebito == null) {

               TelaCadastra_CartaoDebito = new  TelaCartaoDebito_cadastrar();

               TelaCadastra_CartaoDebito.setVisible(true);
            
               TelaCadastra_CartaoDebito.receberID(txt_id.getText());

           } else {

               TelaCadastra_CartaoDebito.setVisible(true);

               TelaCadastra_CartaoDebito.setState(TelaPrincipal.NORMAL);

               TelaCadastra_CartaoDebito.receberID(txt_id.getText());
                
           }
         
         
         this.dispose();
    }
     
     
     void TelaConsulta_CartaoDebito(){
        
        TelaCartaoDebito_consulta TelaConsulta = null;

        if (TelaConsulta == null) {

            TelaConsulta = new TelaCartaoDebito_consulta();

            TelaConsulta.setVisible(true);

            TelaConsulta.receberID(txt_id.getText());

        } else {

            TelaConsulta.setVisible(true);

            TelaConsulta.setState(TelaCartaoCredito_cadastrar.NORMAL);

            TelaConsulta.receberID(txt_id.getText());

        }

        this.dispose();
        
    }
     
     
     void RecarregaTabela_CartaoDB(){
         
         DefaultTableModel mp1 = (DefaultTableModel) jtConsultaCD.getModel();
        
        int l = mp1.getRowCount();
        
        if(l>0){
            while(l>0){
                //Limpa tabela sempre que for fazer uma nova consulta
                ((DefaultTableModel) jtConsultaCD.getModel()).removeRow(l - 1);
                
                //Menos um pois a primeira linha é a linha zero
                l--;
            }
        }
        
        try{
            
            CartaoDebitoDAO cartao_c = new CartaoDebitoDAO();
            
            DefaultTableModel mp = (DefaultTableModel) jtConsultaCD.getModel();  

            rs = cartao_c.CarregaTabela_Cartao_D(Integer.parseInt(txt_id.getText()));

            while(rs.next()) {
                
                String Col0 = rs.getString("n_cartao_debito");
                String Col1 = rs.getString("valor_atual");
                String Col2 = rs.getString("bandeira");
                
                
                //Redimensiona a tabela
                //TamanhoColunas();
                
                mp.addRow(new String[] {Col0,Col1,Col2});
                
            }
            
            
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(this, e.getMessage());
            
        }
        
        jtConsultaCD.setAutoCreateRowSorter(true);
         
         
     }
     
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_NumCartaoD = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_Bandeira = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_Valor = new javax.swing.JTextField();
        cbbTipo = new javax.swing.JComboBox<>();
        txt_Pesquisa = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rbAscendente = new javax.swing.JRadioButton();
        rbDescendente = new javax.swing.JRadioButton();
        btPesquisarCD = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtConsultaCD = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 500));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jButton1.setBackground(new java.awt.Color(105, 69, 219));
        jButton1.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Inicio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(30, 30, 90, 27);

        jButton2.setBackground(new java.awt.Color(105, 69, 219));
        jButton2.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Sair");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(580, 30, 90, 27);

        jButton4.setBackground(new java.awt.Color(105, 69, 219));
        jButton4.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Receitas");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(240, 30, 110, 27);

        jButton5.setBackground(new java.awt.Color(105, 69, 219));
        jButton5.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Usuário");
        getContentPane().add(jButton5);
        jButton5.setBounds(490, 30, 90, 27);

        jButton3.setBackground(new java.awt.Color(105, 69, 219));
        jButton3.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Novo Cartao");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(560, 80, 110, 27);

        btnConsultar.setBackground(new java.awt.Color(105, 69, 219));
        btnConsultar.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnConsultar.setForeground(new java.awt.Color(255, 255, 255));
        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        getContentPane().add(btnConsultar);
        btnConsultar.setBounds(120, 30, 120, 27);

        jLabel2.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        jLabel2.setText("Cartão de Débito");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(260, 0, 160, 26);

        jButton7.setBackground(new java.awt.Color(105, 69, 219));
        jButton7.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Excluir Cartão");
        getContentPane().add(jButton7);
        jButton7.setBounds(350, 30, 140, 27);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1920, 0);

        jLabel4.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel4.setText("Número do cartão");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 70, 108, 17);

        txt_NumCartaoD.setBackground(new java.awt.Color(187, 210, 240));
        txt_NumCartaoD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_NumCartaoD);
        txt_NumCartaoD.setBounds(30, 90, 400, 27);

        jLabel5.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel5.setText("Bandeira");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 170, 55, 17);

        txt_Bandeira.setBackground(new java.awt.Color(187, 210, 240));
        txt_Bandeira.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt_Bandeira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BandeiraActionPerformed(evt);
            }
        });
        getContentPane().add(txt_Bandeira);
        txt_Bandeira.setBounds(30, 190, 400, 27);

        jLabel3.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel3.setText("Valor");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 120, 100, 17);

        txt_Valor.setBackground(new java.awt.Color(187, 210, 240));
        txt_Valor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_Valor);
        txt_Valor.setBounds(30, 140, 400, 27);

        cbbTipo.setBackground(new java.awt.Color(187, 210, 240));
        cbbTipo.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        cbbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nº Cartão", "Valor", "Bandeira", " ", " ", " " }));
        getContentPane().add(cbbTipo);
        cbbTipo.setBounds(30, 230, 120, 27);

        txt_Pesquisa.setBackground(new java.awt.Color(187, 210, 240));
        txt_Pesquisa.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt_Pesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_PesquisaActionPerformed(evt);
            }
        });
        txt_Pesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_PesquisaKeyReleased(evt);
            }
        });
        getContentPane().add(txt_Pesquisa);
        txt_Pesquisa.setBounds(30, 260, 400, 27);

        jLabel6.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel6.setText("Ordenação");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(470, 240, 80, 17);

        rbAscendente.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        rbAscendente.setText("Ascendente");
        rbAscendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAscendenteActionPerformed(evt);
            }
        });
        getContentPane().add(rbAscendente);
        rbAscendente.setBounds(470, 260, 100, 27);

        rbDescendente.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        rbDescendente.setText("Descendente");
        rbDescendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDescendenteActionPerformed(evt);
            }
        });
        getContentPane().add(rbDescendente);
        rbDescendente.setBounds(570, 260, 110, 27);

        btPesquisarCD.setBackground(new java.awt.Color(105, 69, 219));
        btPesquisarCD.setForeground(new java.awt.Color(255, 255, 255));
        btPesquisarCD.setText("Pesquisar");
        btPesquisarCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarCDActionPerformed(evt);
            }
        });
        getContentPane().add(btPesquisarCD);
        btPesquisarCD.setBounds(560, 290, 110, 30);

        jtConsultaCD.setBackground(new java.awt.Color(187, 210, 240));
        jtConsultaCD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jtConsultaCD.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jtConsultaCD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Cartão Débito", "Valor", "Bandeira"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtConsultaCD.setGridColor(new java.awt.Color(187, 210, 240));
        jtConsultaCD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtConsultaCDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtConsultaCD);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 330, 640, 137);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fundo_principal.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(0, 0, 1920, 1080);

        txt_id.setEditable(false);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        getContentPane().add(txt_id);
        txt_id.setBounds(370, 90, 81, 21);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        sair();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        inicio();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        receita();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        cadastraCartao_Deb();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        // TODO add your handling code here:
        
        TelaConsulta_CartaoDebito();
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void txt_BandeiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BandeiraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BandeiraActionPerformed

    private void txt_PesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_PesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_PesquisaActionPerformed

    private void txt_PesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PesquisaKeyReleased
        // TODO add your handling code here
        
        if(txt_Pesquisa.getText().isEmpty()){
            RecarregaTabela_CartaoDB();
        }
        
    }//GEN-LAST:event_txt_PesquisaKeyReleased

    private void rbAscendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAscendenteActionPerformed
        // TODO add your handling code here:

        if(rbAscendente.isSelected()){

            rbDescendente.setSelected(false);

        }

    }//GEN-LAST:event_rbAscendenteActionPerformed

    private void rbDescendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDescendenteActionPerformed
        // TODO add your handling code here:

        if(rbDescendente.isSelected()){

            rbAscendente.setSelected(false);

        }

    }//GEN-LAST:event_rbDescendenteActionPerformed

    private void btPesquisarCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarCDActionPerformed
        // TODO add your handling code here:

        if(rbAscendente.isSelected() || rbDescendente.isSelected()){

            boolean ordenar = true;

            String tipo = "";

            String escolha = cbbTipo.getSelectedItem().toString().trim();

            if (escolha.equals("Nº Cartão")) {
                tipo = " " + "n_cartao_debito";
            }

            if (escolha.equals("Valor")) {
                tipo = " " + "valor_atual";
            }

            if (escolha.equals("Bandeira")) {
                tipo = " " + "bandeira";
            }

            if (rbAscendente.isSelected()) {

                ordenar = true;

            } else {

                ordenar = false;
            }

            String argumento = txt_Pesquisa.getText();

            DefaultTableModel mp1 = (DefaultTableModel) jtConsultaCD.getModel();

            int l = mp1.getRowCount();

            if (l > 0) {
                while (l > 0) {
                    //Limpa tabela sempre que for fazer uma nova consulta
                    ((DefaultTableModel) jtConsultaCD.getModel()).removeRow(l - 1);

                    //Menos um pois a primeira linha é a linha zero
                    l--;
                }
            }

            try {

                CartaoDebitoDAO cartao_d = new CartaoDebitoDAO();

                DefaultTableModel mp = (DefaultTableModel) jtConsultaCD.getModel();

                rs = cartao_d.ConsultaCartao_D(tipo, argumento, Integer.parseInt(txt_id.getText()), ordenar);

                while (rs.next()) {

                    String Col0 = rs.getString("n_cartao_debito");
                    String Col1 = rs.getString("valor_atual");
                    String Col2 = rs.getString("bandeira");

                    mp.addRow(new String[]{Col0, Col1, Col2});

                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(this, e.getMessage());

            }

            jtConsultaCD.setAutoCreateRowSorter(true);

        }else{

            JOptionPane.showMessageDialog(null, "Tipo de Ordenação Obrigatório");

        }

    }//GEN-LAST:event_btPesquisarCDActionPerformed

    private void jtConsultaCDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtConsultaCDMouseClicked
        // TODO add your handling code here:

        String num_cartao = "" + jtConsultaCD.getValueAt(jtConsultaCD.getSelectedRow(), 0);

        CartaoDebitoDAO cartaoDAO = new CartaoDebitoDAO();

        ResultSet rs = null;

        try {

            rs = cartaoDAO.PreencherCamposCartao_D(num_cartao);

            if(rs.next()){

                txt_NumCartaoD.setText(rs.getString("n_cartao_debito"));
                txt_Valor.setText(rs.getString("valor_atual"));
                txt_Bandeira.setText(rs.getString("bandeira"));

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(this, "Erro ao selecionar os dados!!");
        }

    }//GEN-LAST:event_jtConsultaCDMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
        
         RecarregaTabela_CartaoDB();
        
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
            java.util.logging.Logger.getLogger(TelaCartao_debito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCartao_debito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCartao_debito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCartao_debito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCartao_debito().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btPesquisarCD;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JComboBox<String> cbbTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtConsultaCD;
    private javax.swing.JRadioButton rbAscendente;
    private javax.swing.JRadioButton rbDescendente;
    private javax.swing.JTextField txt_Bandeira;
    private javax.swing.JTextField txt_NumCartaoD;
    private javax.swing.JTextField txt_Pesquisa;
    private javax.swing.JTextField txt_Valor;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables


    public void receberID(String recebe){

        txt_id.setText(recebe);
    }


}
