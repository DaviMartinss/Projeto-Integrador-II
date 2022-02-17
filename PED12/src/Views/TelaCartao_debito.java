/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package Views;

import Controllers.ControlerCartaoDebito;
import Model.CartaoDebito;
import Controllers.ControlerTabela;
import java.awt.HeadlessException;
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
    long salva_num_cartao_debito = 0;
    boolean salvaLinhaAtiva = false;

    public TelaCartao_debito() {
        initComponents();

        this.setLocationRelativeTo(null);
        DesabilitarConsulta();
        txt_id.setVisible(false);
    }

    void TelaPrincipal() {

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

    void TelaUsuario() {

        TelaUsuario TelaUsuario = null;

        if (TelaUsuario == null) {

            TelaUsuario = new TelaUsuario();

            TelaUsuario.setVisible(true);

            TelaUsuario.receberID(txt_id.getText());

        } else {

            TelaUsuario.setVisible(true);

            TelaUsuario.setState(TelaPrincipal.NORMAL);

            TelaUsuario.receberID(txt_id.getText());

        }

        this.dispose();

    }
    
    void TelaAtualizarCartaoDebito(CartaoDebito cartaoDebito) {

        TelaAtualizarCartaoDebito telaAtualizarCartaoDebito = null;

        if (telaAtualizarCartaoDebito == null) {

            telaAtualizarCartaoDebito = new TelaAtualizarCartaoDebito();

            telaAtualizarCartaoDebito.setVisible(true);

            telaAtualizarCartaoDebito.receberCartaoDebito(cartaoDebito);

        } else {

            telaAtualizarCartaoDebito.setVisible(true);

            telaAtualizarCartaoDebito.setState(telaAtualizarCartaoDebito.NORMAL);

            telaAtualizarCartaoDebito.receberCartaoDebito(cartaoDebito);
        }

        this.dispose();
    }
    
    void HabilitarConsulta(){
        
        findTextTitle.setVisible(true);
        txt_Pesquisa.setVisible(true);
        btPesquisarCD.setVisible(true);
        
    }
    
    final void DesabilitarConsulta(){
        
        findTextTitle.setVisible(false);
        txt_Pesquisa.setVisible(false);
        btPesquisarCD.setVisible(false);
    }

    void sair() {
        TelaLogin tela_login = new TelaLogin();
        tela_login.setVisible(true);
        this.dispose();
    }

    void TelaReceita() {

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

    void TelaCartaoDebito_cadastrar() {

        TelaCartaoDebito_cadastrar TelaCadastra_CartaoDebito = null;

        if (TelaCadastra_CartaoDebito == null) {

            TelaCadastra_CartaoDebito = new TelaCartaoDebito_cadastrar();

            TelaCadastra_CartaoDebito.setVisible(true);

            TelaCadastra_CartaoDebito.receberID(txt_id.getText());

        } else {

            TelaCadastra_CartaoDebito.setVisible(true);

            TelaCadastra_CartaoDebito.setState(TelaPrincipal.NORMAL);

            TelaCadastra_CartaoDebito.receberID(txt_id.getText());

        }

        this.dispose();
    }

    void RecarregaTabela() {
        salvaLinhaAtiva = false;

        try {
            
            ControlerTabela.LimpaTabela(jtConsultaCD);

            DefaultTableModel mp = (DefaultTableModel) jtConsultaCD.getModel();

            ControlerTabela.RecarregaTabela(mp, Integer.parseInt(txt_id.getText()), "CartaoDebito");

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    void AtualizarCartaoDebito() {
        
        
        try {

            if (!(salvaLinhaAtiva)) {
                JOptionPane.showMessageDialog(this, "Nenhum Cartão de Débito foi selecionada para ser atualizado", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String num_cartao = "" + jtConsultaCD.getValueAt(jtConsultaCD.getSelectedRow(), 0);
            String valor = "" + jtConsultaCD.getValueAt(jtConsultaCD.getSelectedRow(), 1);
            String bandeira = "" + jtConsultaCD.getValueAt(jtConsultaCD.getSelectedRow(), 2);

            CartaoDebito cartao_d
                    = new CartaoDebito.CartaoDebitoBuild(Long.parseLong(num_cartao))
                            .ValorAtual(Float.parseFloat(valor))
                            .Bandeira(bandeira)
                            .IdConta(Integer.parseInt(txt_id.getText()))
                            .build();

            TelaAtualizarCartaoDebito(cartao_d);

        } catch (HeadlessException | NumberFormatException | NullPointerException e) {

            JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
        }
    }

    void ApagarCartaoDebito() {
        
        if (!(salvaLinhaAtiva)) {
            JOptionPane.showMessageDialog(this, "Nenhum Cartão de Débito foi selecionada para ser deletado", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {

            String num_cartao = "" + jtConsultaCD.getValueAt(jtConsultaCD.getSelectedRow(), 0);

            CartaoDebito cartao_d = new CartaoDebito.CartaoDebitoBuild(Long.parseLong(num_cartao)).build();

            ControlerCartaoDebito.ApagarCartaoDebito(cartao_d);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    void ConsultaCartaoDebito(boolean ordenar) {

        String tipo = "";

        String escolha = cbbTipo.getSelectedItem().toString().trim();
        
        switch(escolha)
        {
            case "Nº Cartão":
                tipo = " " + "n_cartao_debito";
                break;
            
            case "Valor":
                tipo = " " + "valor_atual";
                break;
            
            case "Bandeira":
                tipo = " " + "bandeira";
                break;
            
            default:
                JOptionPane.showMessageDialog(this, "Tipo de filtro desconhecido ou não implementado!", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }

        String argumento = txt_Pesquisa.getText();

        try {

            ControlerTabela.LimpaTabela(jtConsultaCD);

            DefaultTableModel mp = (DefaultTableModel) jtConsultaCD.getModel();

            ControlerTabela.RecarregaTabelaConsulta(mp, tipo, argumento, Integer.parseInt(txt_id.getText()), ordenar, null, "CartaoDebito");
            
        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this, e.getMessage());
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
        cbbTipo = new javax.swing.JComboBox<>();
        txt_Pesquisa = new javax.swing.JTextField();
        cardTable = new javax.swing.JScrollPane();
        jtConsultaCD = new javax.swing.JTable();
        btPesquisarCD = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        incomeButton = new javax.swing.JButton();
        userButton = new javax.swing.JButton();
        newCardButton = new javax.swing.JButton();
        delCardButton = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        findByTitle = new javax.swing.JLabel();
        ordenationTitle = new javax.swing.JLabel();
        findTextTitle = new javax.swing.JLabel();
        btnASC = new javax.swing.JButton();
        btnDESC = new javax.swing.JButton();
        tbHabilitaConsulta = new javax.swing.JToggleButton();
        background = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cartao de Debito");
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        pageTitle.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        pageTitle.setText("Cartão de Débito");
        getContentPane().add(pageTitle);
        pageTitle.setBounds(320, 0, 160, 27);

        cbbTipo.setBackground(new java.awt.Color(187, 210, 240));
        cbbTipo.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        cbbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nº Cartão", "Valor", "Bandeira", " ", " ", " " }));
        getContentPane().add(cbbTipo);
        cbbTipo.setBounds(20, 100, 140, 27);

        txt_Pesquisa.setColumns(100);
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
        txt_Pesquisa.setBounds(20, 370, 350, 27);

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
        cardTable.setViewportView(jtConsultaCD);

        getContentPane().add(cardTable);
        cardTable.setBounds(20, 140, 760, 170);

        btPesquisarCD.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btPesquisarCD.setForeground(new java.awt.Color(255, 255, 255));
        btPesquisarCD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/lupa.png"))); // NOI18N
        btPesquisarCD.setBorder(null);
        btPesquisarCD.setBorderPainted(false);
        btPesquisarCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarCDActionPerformed(evt);
            }
        });
        getContentPane().add(btPesquisarCD);
        btPesquisarCD.setBounds(380, 360, 40, 40);

        startButton.setBackground(new java.awt.Color(105, 69, 219));
        startButton.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        startButton.setForeground(new java.awt.Color(255, 255, 255));
        startButton.setText("Inicio");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        getContentPane().add(startButton);
        startButton.setBounds(20, 40, 100, 27);

        exitButton.setBackground(new java.awt.Color(105, 69, 219));
        exitButton.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        exitButton.setForeground(new java.awt.Color(255, 255, 255));
        exitButton.setText("Sair");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(exitButton);
        exitButton.setBounds(680, 40, 100, 27);

        incomeButton.setBackground(new java.awt.Color(105, 69, 219));
        incomeButton.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        incomeButton.setForeground(new java.awt.Color(255, 255, 255));
        incomeButton.setText("Receitas");
        incomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incomeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(incomeButton);
        incomeButton.setBounds(440, 40, 100, 27);

        userButton.setBackground(new java.awt.Color(105, 69, 219));
        userButton.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        userButton.setForeground(new java.awt.Color(255, 255, 255));
        userButton.setText("Usuário");
        userButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userButtonActionPerformed(evt);
            }
        });
        getContentPane().add(userButton);
        userButton.setBounds(560, 40, 100, 27);

        newCardButton.setBackground(new java.awt.Color(105, 69, 219));
        newCardButton.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        newCardButton.setForeground(new java.awt.Color(255, 255, 255));
        newCardButton.setText("Novo Cartao");
        newCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newCardButtonActionPerformed(evt);
            }
        });
        getContentPane().add(newCardButton);
        newCardButton.setBounds(640, 450, 140, 27);

        delCardButton.setBackground(new java.awt.Color(210, 59, 239));
        delCardButton.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        delCardButton.setForeground(new java.awt.Color(255, 255, 255));
        delCardButton.setText("Excluir Cartão");
        delCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delCardButtonActionPerformed(evt);
            }
        });
        getContentPane().add(delCardButton);
        delCardButton.setBounds(640, 530, 140, 27);

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
        btn_update.setBounds(640, 490, 140, 27);

        findByTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        findByTitle.setText("Ordenar/Refinar por: ");
        getContentPane().add(findByTitle);
        findByTitle.setBounds(20, 80, 140, 27);

        ordenationTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        ordenationTitle.setText("Tipo de Ordenação");
        getContentPane().add(ordenationTitle);
        ordenationTitle.setBounds(180, 80, 140, 27);

        findTextTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        findTextTitle.setText("Refinar busca por:");
        getContentPane().add(findTextTitle);
        findTextTitle.setBounds(20, 350, 170, 27);

        btnASC.setBackground(new java.awt.Color(105, 69, 219));
        btnASC.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnASC.setForeground(new java.awt.Color(255, 255, 255));
        btnASC.setText("Ascendente");
        btnASC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnASCActionPerformed(evt);
            }
        });
        getContentPane().add(btnASC);
        btnASC.setBounds(180, 100, 110, 27);

        btnDESC.setBackground(new java.awt.Color(105, 69, 219));
        btnDESC.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnDESC.setForeground(new java.awt.Color(255, 255, 255));
        btnDESC.setText("Descendente");
        btnDESC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDESCActionPerformed(evt);
            }
        });
        getContentPane().add(btnDESC);
        btnDESC.setBounds(300, 100, 130, 27);

        tbHabilitaConsulta.setBackground(new java.awt.Color(105, 69, 219));
        tbHabilitaConsulta.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        tbHabilitaConsulta.setForeground(new java.awt.Color(255, 255, 255));
        tbHabilitaConsulta.setText("Refinar Busca");
        tbHabilitaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbHabilitaConsultaActionPerformed(evt);
            }
        });
        getContentPane().add(tbHabilitaConsulta);
        tbHabilitaConsulta.setBounds(20, 320, 190, 30);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Back-2.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 1920, 1080);

        txt_id.setEditable(false);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        getContentPane().add(txt_id);
        txt_id.setBounds(0, 0, 81, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        sair();
    }//GEN-LAST:event_exitButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // TODO add your handling code here:
        TelaPrincipal();
    }//GEN-LAST:event_startButtonActionPerformed

    private void incomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incomeButtonActionPerformed
        // TODO add your handling code here:
        TelaReceita();
    }//GEN-LAST:event_incomeButtonActionPerformed

    private void newCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newCardButtonActionPerformed
        // TODO add your handling code here:
        TelaCartaoDebito_cadastrar();
    }//GEN-LAST:event_newCardButtonActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void txt_PesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_PesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_PesquisaActionPerformed

    private void txt_PesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PesquisaKeyReleased
        // TODO add your handling code here

        if (txt_Pesquisa.getText().isEmpty()) {
            RecarregaTabela();
        }

    }//GEN-LAST:event_txt_PesquisaKeyReleased

    private void btPesquisarCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarCDActionPerformed
        // TODO add your handling code here:
    
        ConsultaCartaoDebito(true);
    }//GEN-LAST:event_btPesquisarCDActionPerformed

    private void jtConsultaCDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtConsultaCDMouseClicked
        // TODO add your handling code here:

        String num_cartao = "" + jtConsultaCD.getValueAt(jtConsultaCD.getSelectedRow(), 0);

        
        salva_num_cartao_debito = Long.parseLong(num_cartao);
        salvaLinhaAtiva = true;
        
    }//GEN-LAST:event_jtConsultaCDMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:

        RecarregaTabela();

    }//GEN-LAST:event_formWindowOpened

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:

        AtualizarCartaoDebito();

    }//GEN-LAST:event_btn_updateActionPerformed

    private void delCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delCardButtonActionPerformed
        // TODO add your handling code here:
        ApagarCartaoDebito();
        RecarregaTabela();
    }//GEN-LAST:event_delCardButtonActionPerformed

    private void userButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userButtonActionPerformed
        // TODO add your handling code here:

        TelaUsuario();
    }//GEN-LAST:event_userButtonActionPerformed

    private void btnASCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnASCActionPerformed
        // TODO add your handling code here:
        ConsultaCartaoDebito(true);
 
    }//GEN-LAST:event_btnASCActionPerformed

    private void btnDESCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDESCActionPerformed

        ConsultaCartaoDebito(false);
    }//GEN-LAST:event_btnDESCActionPerformed

    private void tbHabilitaConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbHabilitaConsultaActionPerformed

        if(tbHabilitaConsulta.isSelected())
            HabilitarConsulta();
        else
            DesabilitarConsulta();

    }//GEN-LAST:event_tbHabilitaConsultaActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> {
            new TelaCartao_debito().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btPesquisarCD;
    private javax.swing.JButton btnASC;
    private javax.swing.JButton btnDESC;
    private javax.swing.JButton btn_update;
    private javax.swing.JScrollPane cardTable;
    private javax.swing.JComboBox<String> cbbTipo;
    private javax.swing.JButton delCardButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel findByTitle;
    private javax.swing.JLabel findTextTitle;
    private javax.swing.JButton incomeButton;
    private javax.swing.JTable jtConsultaCD;
    private javax.swing.JButton newCardButton;
    private javax.swing.JLabel ordenationTitle;
    private javax.swing.JLabel pageTitle;
    private javax.swing.JButton startButton;
    private javax.swing.JToggleButton tbHabilitaConsulta;
    private javax.swing.JTextField txt_Pesquisa;
    private javax.swing.JTextField txt_id;
    private javax.swing.JButton userButton;
    // End of variables declaration//GEN-END:variables

    public void receberID(String recebe) {

        txt_id.setText(recebe);
    }
}