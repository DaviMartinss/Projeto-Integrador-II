/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.ControlerCartaoCredito;
import Controllers.ControlerTabela;
import Model.CartaoCredito;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.mysql.cj.util.StringUtils;
import java.awt.HeadlessException;

/**
 *
 * @author pc
 */
public class TelaCartao_credito extends javax.swing.JFrame {

    private long salva_num_cartao = 0;
    private boolean salvaLinhaAtiva = false;

    public TelaCartao_credito() {
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

    void TelaCadastrarCartaoCredito() {

        TelaCartaoCredito_cadastrar TelaCadastra_CartaoCredito = null;

        if (TelaCadastra_CartaoCredito == null) {

            TelaCadastra_CartaoCredito = new TelaCartaoCredito_cadastrar();

            TelaCadastra_CartaoCredito.setVisible(true);

            TelaCadastra_CartaoCredito.receberID(txt_id.getText());

        } else {

            TelaCadastra_CartaoCredito.setVisible(true);

            TelaCadastra_CartaoCredito.setState(TelaPrincipal.NORMAL);

            TelaCadastra_CartaoCredito.receberID(txt_id.getText());

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
    
    void TelaAtualizarCartaoCredito(CartaoCredito cartaoCredito) {

        TelaAtualizarCartaoCredito telaAtualizarCartaoCredito = null;

        if (telaAtualizarCartaoCredito == null) {

            telaAtualizarCartaoCredito = new TelaAtualizarCartaoCredito();

            telaAtualizarCartaoCredito.setVisible(true);

            telaAtualizarCartaoCredito.receberCartaoCredito(cartaoCredito); 

        } else {

            telaAtualizarCartaoCredito.setVisible(true);

            telaAtualizarCartaoCredito.setState(TelaPrincipal.NORMAL);

            telaAtualizarCartaoCredito.receberCartaoCredito(cartaoCredito); 

        }

        this.dispose();

    }
    
    void TelaFatura() {

        TelaFatura telaFatura = null;
        
        String num_cartao = "" + jtConsultaCC.getValueAt(jtConsultaCC.getSelectedRow(), 0);
        
        if (!StringUtils.isNullOrEmpty(num_cartao)) {
            
            if (telaFatura == null) {

                telaFatura = new TelaFatura();

                telaFatura.setVisible(true);

                telaFatura.receberIdAndNumCartao(num_cartao,txt_id.getText());

            } else {

                telaFatura.setVisible(true);

                telaFatura.setState(TelaPrincipal.NORMAL);
                  
                telaFatura.receberIdAndNumCartao(num_cartao,txt_id.getText());

            }
            
             this.dispose();
        }else{
            
            JOptionPane.showMessageDialog(this, "Selecione um Cartão de Crédito", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
        }

    }
    
     void sair() {
        TelaLogin tela_login = new TelaLogin();
        tela_login.setVisible(true);
        this.dispose();
    }

    void RecarregaTabela() {

        salvaLinhaAtiva = false;
        
        ControlerTabela.LimpaTabela(jtConsultaCC);

        DefaultTableModel mp = (DefaultTableModel) jtConsultaCC.getModel();
        
        ControlerTabela.RecarregaTabela(mp, Integer.parseInt(txt_id.getText()), "CartaoCredito");
    }
    
    void HabilitarConsulta(){
        
        findTextType.setVisible(true);
        txt_Pesquisa.setVisible(true);
        btPesquisarCC.setVisible(true);
    }
    
    final void DesabilitarConsulta(){
        
        findTextType.setVisible(false);
        txt_Pesquisa.setVisible(false);
        btPesquisarCC.setVisible(false);
    }

    void AtualizarCartaoCredito() {

        if (!(salvaLinhaAtiva)) {
            JOptionPane.showMessageDialog(this, "Nenhum Cartão de Credito foi selecionada para ser atualizado", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        String num_cartao = "" + jtConsultaCC.getValueAt(jtConsultaCC.getSelectedRow(), 0);
        String limite = "" + jtConsultaCC.getValueAt(jtConsultaCC.getSelectedRow(), 1);
        String diaFatura = "" + jtConsultaCC.getValueAt(jtConsultaCC.getSelectedRow(), 3);
        String valorFatura = "" + jtConsultaCC.getValueAt(jtConsultaCC.getSelectedRow(), 4);
        String bandeira = "" + jtConsultaCC.getValueAt(jtConsultaCC.getSelectedRow(), 5);
        
        try {
            
            CartaoCredito cartao_c
                    = new CartaoCredito.CartaoCreditoBuild(
                            Long.parseLong(num_cartao))
                            .Limite(Float.parseFloat(limite))
                            .DiaFatura(Integer.parseInt(diaFatura))
                            .ValorFatura(Float.parseFloat(valorFatura))
                            .Bandeira(bandeira)
                            .IdConta(Integer.parseInt(txt_id.getText()))
                            .build();

            TelaAtualizarCartaoCredito(cartao_c);

        } catch (HeadlessException | NumberFormatException e) {

            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    void ApagarCartaoCredito() {

        if (!(salvaLinhaAtiva)) {
            JOptionPane.showMessageDialog(this, "Nenhum Cartão de Credito foi selecionado para ser atualizado", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        try {

            String num_cartao = "" + jtConsultaCC.getValueAt(jtConsultaCC.getSelectedRow(), 0);
            
            CartaoCredito cartao_c = new CartaoCredito.CartaoCreditoBuild(Long.parseLong(num_cartao)).build();

            ControlerCartaoCredito.ApagarCartaoCredito(cartao_c);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    void ConsultaCartaoCredito(boolean ordenar) {

        String tipo = "";

        String escolha = selectorType.getSelectedItem().toString().trim();
        
        switch(escolha)
        {
            case "Nº Cartão":
                tipo = " " + "n_cartao_credito";
                break;
                
            case "Limite":
                tipo = " " + "limite";
                break;
            
            case "Dia da Fatura":
                tipo = " " + "dia_fatura";
                break;
            
            case "Valor da Fatura":
                tipo = " " + "valor_fatura";
                break;
            
            case "Bandeira":
                tipo = " " + "bandeira";
                break;    
            
            default:
                JOptionPane.showMessageDialog(this, "Tipo de filtro desconhecido ou não implementado!", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
        
        String argumento = txt_Pesquisa.getText();

        ControlerTabela.LimpaTabela(jtConsultaCC);

        DefaultTableModel mp = (DefaultTableModel) jtConsultaCC.getModel();

        ControlerTabela.RecarregaTabelaConsulta(mp, tipo, argumento, Integer.parseInt(txt_id.getText()), ordenar, null, "CartaoCredito");
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
        infoTable = new javax.swing.JScrollPane();
        jtConsultaCC = new javax.swing.JTable();
        selectorType = new javax.swing.JComboBox<>();
        txt_Pesquisa = new javax.swing.JTextField();
        btn_inicio_CC = new javax.swing.JButton();
        btn_receitaCC = new javax.swing.JButton();
        btn_sairCC = new javax.swing.JButton();
        btn_novoCartao_cc = new javax.swing.JButton();
        btn_exclui_cc = new javax.swing.JButton();
        btPesquisarCC = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        userButton = new javax.swing.JButton();
        btnListaFaturas = new javax.swing.JButton();
        findTitle = new javax.swing.JLabel();
        ordenationTitle = new javax.swing.JLabel();
        findTextType = new javax.swing.JLabel();
        btnDESC = new javax.swing.JButton();
        btnASC = new javax.swing.JButton();
        tbHabilitaConsulta = new javax.swing.JToggleButton();
        background = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cartao de Credito");
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        pageTitle.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        pageTitle.setText("Cartão de Crédito");
        getContentPane().add(pageTitle);
        pageTitle.setBounds(330, 0, 170, 24);

        jtConsultaCC.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jtConsultaCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Cartão", "Limite", "Crédito", "Dia (Fatura)", "Valor (Fatura)", "Bandeira"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtConsultaCC.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtConsultaCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtConsultaCCMouseClicked(evt);
            }
        });
        infoTable.setViewportView(jtConsultaCC);
        if (jtConsultaCC.getColumnModel().getColumnCount() > 0) {
            jtConsultaCC.getColumnModel().getColumn(0).setMinWidth(140);
            jtConsultaCC.getColumnModel().getColumn(0).setMaxWidth(140);
            jtConsultaCC.getColumnModel().getColumn(1).setMinWidth(80);
            jtConsultaCC.getColumnModel().getColumn(1).setMaxWidth(80);
            jtConsultaCC.getColumnModel().getColumn(2).setMinWidth(80);
            jtConsultaCC.getColumnModel().getColumn(2).setMaxWidth(80);
            jtConsultaCC.getColumnModel().getColumn(3).setMinWidth(80);
            jtConsultaCC.getColumnModel().getColumn(3).setMaxWidth(80);
            jtConsultaCC.getColumnModel().getColumn(4).setMinWidth(100);
            jtConsultaCC.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        getContentPane().add(infoTable);
        infoTable.setBounds(30, 150, 740, 160);

        selectorType.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        selectorType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nº Cartão", "Limite", "Dia da Fatura", "Valor da Fatura", "Bandeira", " ", " ", " " }));
        getContentPane().add(selectorType);
        selectorType.setBounds(30, 110, 180, 27);

        txt_Pesquisa.setColumns(20);
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
        txt_Pesquisa.setBounds(30, 380, 350, 27);

        btn_inicio_CC.setBackground(new java.awt.Color(105, 69, 219));
        btn_inicio_CC.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_inicio_CC.setForeground(new java.awt.Color(255, 255, 255));
        btn_inicio_CC.setText("Inicio");
        btn_inicio_CC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inicio_CCActionPerformed(evt);
            }
        });
        getContentPane().add(btn_inicio_CC);
        btn_inicio_CC.setBounds(30, 50, 100, 27);

        btn_receitaCC.setBackground(new java.awt.Color(105, 69, 219));
        btn_receitaCC.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_receitaCC.setForeground(new java.awt.Color(255, 255, 255));
        btn_receitaCC.setText("Receitas");
        btn_receitaCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_receitaCCActionPerformed(evt);
            }
        });
        getContentPane().add(btn_receitaCC);
        btn_receitaCC.setBounds(150, 50, 150, 27);

        btn_sairCC.setBackground(new java.awt.Color(105, 69, 219));
        btn_sairCC.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_sairCC.setForeground(new java.awt.Color(255, 255, 255));
        btn_sairCC.setText("Sair");
        btn_sairCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sairCCActionPerformed(evt);
            }
        });
        getContentPane().add(btn_sairCC);
        btn_sairCC.setBounds(670, 50, 100, 27);

        btn_novoCartao_cc.setBackground(new java.awt.Color(105, 69, 219));
        btn_novoCartao_cc.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_novoCartao_cc.setForeground(new java.awt.Color(255, 255, 255));
        btn_novoCartao_cc.setText("Novo Cartão");
        btn_novoCartao_cc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoCartao_ccActionPerformed(evt);
            }
        });
        getContentPane().add(btn_novoCartao_cc);
        btn_novoCartao_cc.setBounds(630, 460, 140, 27);

        btn_exclui_cc.setBackground(new java.awt.Color(210, 59, 239));
        btn_exclui_cc.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_exclui_cc.setForeground(new java.awt.Color(255, 255, 255));
        btn_exclui_cc.setText("Exclui cartão");
        btn_exclui_cc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exclui_ccActionPerformed(evt);
            }
        });
        getContentPane().add(btn_exclui_cc);
        btn_exclui_cc.setBounds(630, 540, 140, 27);

        btPesquisarCC.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btPesquisarCC.setForeground(new java.awt.Color(255, 255, 255));
        btPesquisarCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/lupa.png"))); // NOI18N
        btPesquisarCC.setBorderPainted(false);
        btPesquisarCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarCCActionPerformed(evt);
            }
        });
        getContentPane().add(btPesquisarCC);
        btPesquisarCC.setBounds(390, 370, 40, 40);

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
        btn_update.setBounds(630, 500, 140, 27);

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
        userButton.setBounds(320, 50, 100, 27);

        btnListaFaturas.setBackground(new java.awt.Color(105, 69, 219));
        btnListaFaturas.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnListaFaturas.setForeground(new java.awt.Color(255, 255, 255));
        btnListaFaturas.setText("Detalhar Fatura");
        btnListaFaturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaFaturasActionPerformed(evt);
            }
        });
        getContentPane().add(btnListaFaturas);
        btnListaFaturas.setBounds(630, 330, 140, 27);

        findTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        findTitle.setText("Ordenar/Refinar por:");
        getContentPane().add(findTitle);
        findTitle.setBounds(30, 90, 140, 27);

        ordenationTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        ordenationTitle.setText("Ordenação");
        getContentPane().add(ordenationTitle);
        ordenationTitle.setBounds(220, 90, 100, 27);

        findTextType.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        findTextType.setText("Refinar busca por:");
        getContentPane().add(findTextType);
        findTextType.setBounds(30, 360, 180, 27);

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
        btnDESC.setBounds(340, 110, 120, 27);

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
        btnASC.setBounds(220, 110, 110, 27);

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
        tbHabilitaConsulta.setBounds(30, 330, 160, 27);

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
        txt_id.setBounds(30, 10, 81, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_sairCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sairCCActionPerformed
        // TODO add your handling code here:
        sair();
    }//GEN-LAST:event_btn_sairCCActionPerformed

    private void btn_inicio_CCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inicio_CCActionPerformed
        // TODO add your handling code here:
        TelaPrincipal();
    }//GEN-LAST:event_btn_inicio_CCActionPerformed

    private void btn_receitaCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_receitaCCActionPerformed
        // TODO add your handling code here:
        TelaReceita();
    }//GEN-LAST:event_btn_receitaCCActionPerformed

    private void btn_novoCartao_ccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoCartao_ccActionPerformed
        // TODO add your handling code here:
        TelaCadastrarCartaoCredito();
    }//GEN-LAST:event_btn_novoCartao_ccActionPerformed

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

    private void btPesquisarCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarCCActionPerformed
        // TODO add your handling code here:

        ConsultaCartaoCredito(true);
    }//GEN-LAST:event_btPesquisarCCActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:

        RecarregaTabela();

    }//GEN-LAST:event_formWindowOpened

    private void jtConsultaCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtConsultaCCMouseClicked
        // TODO add your handling code here:
        
        int selLinha = -1;
        selLinha = jtConsultaCC.getSelectedRow();

        if (selLinha != -1) {
            salvaLinhaAtiva = true;
        }
    }//GEN-LAST:event_jtConsultaCCMouseClicked

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:

        AtualizarCartaoCredito();

    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_exclui_ccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exclui_ccActionPerformed
        // TODO add your handling code here:
        ApagarCartaoCredito();
        RecarregaTabela();
    }//GEN-LAST:event_btn_exclui_ccActionPerformed

    private void userButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userButtonActionPerformed
        // TODO add your handling code here:

        TelaUsuario();
    }//GEN-LAST:event_userButtonActionPerformed

    private void btn_ListarFaturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ListarFaturasActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btn_ListarFaturasActionPerformed

    private void btnListaFaturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaFaturasActionPerformed
        // TODO add your handling code here:
        
        if (salvaLinhaAtiva) 
             TelaFatura();
        else
            JOptionPane.showMessageDialog(this, "Nenhum Cartão de Credito foi selecionado", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
          
    }//GEN-LAST:event_btnListaFaturasActionPerformed

    private void btnDESCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDESCActionPerformed

        ConsultaCartaoCredito(false);
    }//GEN-LAST:event_btnDESCActionPerformed

    private void btnASCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnASCActionPerformed
        // TODO add your handling code here:
        ConsultaCartaoCredito(true);
    }//GEN-LAST:event_btnASCActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> {
            new TelaCartao_credito().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btPesquisarCC;
    private javax.swing.JButton btnASC;
    private javax.swing.JButton btnDESC;
    private javax.swing.JButton btnListaFaturas;
    private javax.swing.JButton btn_exclui_cc;
    private javax.swing.JButton btn_inicio_CC;
    private javax.swing.JButton btn_novoCartao_cc;
    private javax.swing.JButton btn_receitaCC;
    private javax.swing.JButton btn_sairCC;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel findTextType;
    private javax.swing.JLabel findTitle;
    private javax.swing.JScrollPane infoTable;
    private javax.swing.JTable jtConsultaCC;
    private javax.swing.JLabel ordenationTitle;
    private javax.swing.JLabel pageTitle;
    private javax.swing.JComboBox<String> selectorType;
    private javax.swing.JToggleButton tbHabilitaConsulta;
    private javax.swing.JTextField txt_Pesquisa;
    private javax.swing.JTextField txt_id;
    private javax.swing.JButton userButton;
    // End of variables declaration//GEN-END:variables

    public void receberID(String recebe) {

        txt_id.setText(recebe);
    }

}
