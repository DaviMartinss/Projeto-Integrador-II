/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAO.CartaoCreditoDAO;
import static DAO.ConsultaDAO.carregaTabela;
import DAO.moduloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
      
      
      void RecarregaTabela_CartaoCC() {

        DefaultTableModel mp1 = (DefaultTableModel) jtConsultaCC.getModel();

        int l = mp1.getRowCount();

        if (l > 0) {
            while (l > 0) {
                //Limpa tabela sempre que for fazer uma nova consulta
                ((DefaultTableModel) jtConsultaCC.getModel()).removeRow(l - 1);

                //Menos um pois a primeira linha é a linha zero
                l--;
            }
        }

        try {

            CartaoCreditoDAO cartao_c = new CartaoCreditoDAO();

            DefaultTableModel mp = (DefaultTableModel) jtConsultaCC.getModel();

            rs = cartao_c.CarregaTabela_Cartao_C(Integer.parseInt(txt_id.getText()));

            while (rs.next()) {

                String Col0 = rs.getString("n_cartao_credito");
                String Col1 = rs.getString("limite");
                String Col2 = rs.getString("dia_fatura");
                String Col3 = rs.getString("valor_fatura");
                String Col4 = rs.getString("bandeira");

                //Redimensiona a tabela
                //TamanhoColunas();
                mp.addRow(new String[]{Col0, Col1, Col2, Col3, Col4});

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage());

        }

        jtConsultaCC.setAutoCreateRowSorter(true);

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
        txt_Pesquisa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtConsultaCC = new javax.swing.JTable();
        cbbTipo = new javax.swing.JComboBox<>();
        btPesquisarCC = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_DiaFatura = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_ValorFatura = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_Bandeira = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_NumCartaoC = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_Limite = new javax.swing.JTextField();
        rbDescendente = new javax.swing.JRadioButton();
        rbAscendente = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jTextField1.setText("Usuário");
        getContentPane().add(jTextField1);
        jTextField1.setBounds(30, 90, 150, 27);

        jTextField2.setText("Cartão de Crédito");
        getContentPane().add(jTextField2);
        jTextField2.setBounds(190, 90, 150, 27);

        jLabel2.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        jLabel2.setText("Cartão de Crédito");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(280, 0, 170, 24);

        btn_inicio_CC.setBackground(new java.awt.Color(189, 137, 212));
        btn_inicio_CC.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_inicio_CC.setText("Inicio");
        btn_inicio_CC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inicio_CCActionPerformed(evt);
            }
        });
        getContentPane().add(btn_inicio_CC);
        btn_inicio_CC.setBounds(20, 40, 90, 27);

        btn_receitaCC.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_receitaCC.setText("Receitas");
        btn_receitaCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_receitaCCActionPerformed(evt);
            }
        });
        getContentPane().add(btn_receitaCC);
        btn_receitaCC.setBounds(120, 40, 100, 27);

        btn_sairCC.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_sairCC.setText("Sair");
        btn_sairCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sairCCActionPerformed(evt);
            }
        });
        getContentPane().add(btn_sairCC);
        btn_sairCC.setBounds(620, 40, 55, 25);

        btn_novoCartao_cc.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_novoCartao_cc.setText("Novo Cartão");
        btn_novoCartao_cc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoCartao_ccActionPerformed(evt);
            }
        });
        getContentPane().add(btn_novoCartao_cc);
        btn_novoCartao_cc.setBounds(480, 40, 130, 27);

        btnConsulta_CC.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnConsulta_CC.setText("Consulta");
        btnConsulta_CC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulta_CCActionPerformed(evt);
            }
        });
        getContentPane().add(btnConsulta_CC);
        btnConsulta_CC.setBounds(230, 40, 100, 27);

        btn_exclui_cc.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_exclui_cc.setText("Exclui cartão");
        getContentPane().add(btn_exclui_cc);
        btn_exclui_cc.setBounds(340, 40, 130, 27);

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
        txt_Pesquisa.setBounds(160, 300, 400, 27);

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
        jtConsultaCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtConsultaCCMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtConsultaCC);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 340, 640, 160);

        cbbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nº Cartão", "Limite", "Dia da Fatura", "Valor da Fatura", "Bandeira", " ", " ", " " }));
        getContentPane().add(cbbTipo);
        cbbTipo.setBounds(30, 300, 120, 27);

        btPesquisarCC.setText("Pesquisar");
        btPesquisarCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarCCActionPerformed(evt);
            }
        });
        getContentPane().add(btPesquisarCC);
        btPesquisarCC.setBounds(570, 300, 100, 27);

        jLabel1.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel1.setText("Número do cartão");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 130, 140, 16);
        getContentPane().add(txt_DiaFatura);
        txt_DiaFatura.setBounds(450, 200, 140, 27);

        jLabel3.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel3.setText("Valor da Fatura");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 180, 110, 16);
        getContentPane().add(txt_ValorFatura);
        txt_ValorFatura.setBounds(30, 200, 400, 27);

        jLabel4.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel4.setText("Dia da Fatura");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(450, 180, 100, 16);

        txt_Bandeira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BandeiraActionPerformed(evt);
            }
        });
        getContentPane().add(txt_Bandeira);
        txt_Bandeira.setBounds(30, 250, 400, 27);

        jLabel5.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel5.setText("Bandeira");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 230, 80, 16);
        getContentPane().add(txt_NumCartaoC);
        txt_NumCartaoC.setBounds(30, 150, 400, 27);

        jLabel6.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel6.setText("Ordenação");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(440, 230, 80, 16);
        getContentPane().add(txt_Limite);
        txt_Limite.setBounds(450, 150, 140, 27);

        rbDescendente.setBackground(new java.awt.Color(142, 183, 235));
        rbDescendente.setText("Descendente");
        rbDescendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDescendenteActionPerformed(evt);
            }
        });
        getContentPane().add(rbDescendente);
        rbDescendente.setBounds(540, 250, 110, 23);

        rbAscendente.setBackground(new java.awt.Color(142, 183, 235));
        rbAscendente.setText("Ascendente");
        rbAscendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAscendenteActionPerformed(evt);
            }
        });
        getContentPane().add(rbAscendente);
        rbAscendente.setBounds(440, 250, 110, 23);

        jLabel7.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel7.setText("Limite");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(450, 130, 35, 16);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fundo_principal.png"))); // NOI18N
        jLabel8.setText("jLabel8");
        jLabel8.setPreferredSize(new java.awt.Dimension(1920, 1080));
        getContentPane().add(jLabel8);
        jLabel8.setBounds(-80, 0, 1920, 1080);

        txt_id.setEditable(false);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        getContentPane().add(txt_id);
        txt_id.setBounds(620, 100, 81, 20);

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

    private void txt_PesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_PesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_PesquisaActionPerformed

    private void txt_PesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PesquisaKeyReleased
        // TODO add your handling code here
        
        if(txt_Pesquisa.getText().isEmpty()){
            RecarregaTabela_CartaoCC();
        }
        
    }//GEN-LAST:event_txt_PesquisaKeyReleased

    private void btPesquisarCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarCCActionPerformed
        // TODO add your handling code here:
        
       if(rbAscendente.isSelected() || rbDescendente.isSelected()){
           
           boolean ordenar = true;

           String tipo = "";

           String escolha = cbbTipo.getSelectedItem().toString().trim();

           if (escolha.equals("Nº Cartão")) {
               tipo = " " + "n_cartao_credito";
           }

           if (escolha.equals("Limite")) {
               tipo = " " + "limite";
           }

           if (escolha.equals("Dia da Fatura")) {
               tipo = " " + "dia_fatura";
           }

           if (escolha.equals("Valor da Fatura")) {
               tipo = " " + "valor_fatura";
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

           DefaultTableModel mp1 = (DefaultTableModel) jtConsultaCC.getModel();

           int l = mp1.getRowCount();

           if (l > 0) {
               while (l > 0) {
                   //Limpa tabela sempre que for fazer uma nova consulta
                   ((DefaultTableModel) jtConsultaCC.getModel()).removeRow(l - 1);

                   //Menos um pois a primeira linha é a linha zero
                   l--;
               }
           }

           try {

               CartaoCreditoDAO cartao_c = new CartaoCreditoDAO();

               DefaultTableModel mp = (DefaultTableModel) jtConsultaCC.getModel();

               rs = cartao_c.ConsultaCartao_C(tipo, argumento, Integer.parseInt(txt_id.getText()), ordenar);

               while (rs.next()) {

                   String Col0 = rs.getString("n_cartao_credito");
                   String Col1 = rs.getString("limite");
                   String Col2 = rs.getString("dia_fatura");
                   String Col3 = rs.getString("valor_fatura");
                   String Col4 = rs.getString("bandeira");

                   mp.addRow(new String[]{Col0, Col1, Col2, Col3, Col4});

               }

           } catch (Exception e) {

               JOptionPane.showMessageDialog(this, e.getMessage());

           }

           jtConsultaCC.setAutoCreateRowSorter(true);
           
           
       }else{
       
           JOptionPane.showMessageDialog(null, "Tipo de Ordenação Obrigatório");
       
       }
             
        
    }//GEN-LAST:event_btPesquisarCCActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
        
        RecarregaTabela_CartaoCC();
        
    }//GEN-LAST:event_formWindowOpened

    private void txt_BandeiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BandeiraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BandeiraActionPerformed

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

    private void jtConsultaCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtConsultaCCMouseClicked
        // TODO add your handling code here:
        
        String num_cartao = "" + jtConsultaCC.getValueAt(jtConsultaCC.getSelectedRow(), 0);
        

        
        CartaoCreditoDAO cartaoDAO = new CartaoCreditoDAO();
        
        ResultSet rs = null;
        
        try {
            
            rs = cartaoDAO.PreencherCamposCartao_C(num_cartao);
            
            if(rs.next()){
                
                txt_NumCartaoC.setText(rs.getString("n_cartao_credito"));
                txt_Limite.setText(rs.getString("limite"));
                txt_ValorFatura.setText(rs.getString("valor_fatura"));
                txt_DiaFatura.setText(rs.getString("dia_fatura"));
                txt_Bandeira.setText(rs.getString("bandeira"));
                
            }
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(this, "Erro ao selecionar os dados!!");
        }
        
    }//GEN-LAST:event_jtConsultaCCMouseClicked

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
    private javax.swing.JButton btPesquisarCC;
    private javax.swing.JButton btnConsulta_CC;
    private javax.swing.JButton btn_exclui_cc;
    private javax.swing.JButton btn_inicio_CC;
    private javax.swing.JButton btn_novoCartao_cc;
    private javax.swing.JButton btn_receitaCC;
    private javax.swing.JButton btn_sairCC;
    private javax.swing.JComboBox<String> cbbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable jtConsultaCC;
    private javax.swing.JRadioButton rbAscendente;
    private javax.swing.JRadioButton rbDescendente;
    private javax.swing.JTextField txt_Bandeira;
    private javax.swing.JTextField txt_DiaFatura;
    private javax.swing.JTextField txt_Limite;
    private javax.swing.JTextField txt_NumCartaoC;
    private javax.swing.JTextField txt_Pesquisa;
    private javax.swing.JTextField txt_ValorFatura;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables


    public void receberID(String recebe){

        txt_id.setText(recebe);
    }


}
