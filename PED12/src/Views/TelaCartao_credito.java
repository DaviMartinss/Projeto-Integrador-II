/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.ControlerTabela;
import DAO.CartaoCreditoDAO;
import Model.CartaoCredito;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ValidacaoComum.Validacao;

/**
 *
 * @author pc
 */
public class TelaCartao_credito extends javax.swing.JFrame {

    long salva_num_cartao = 0;
    boolean salvaLinhaAtiva = false;

    public TelaCartao_credito() {
        initComponents();

        this.setLocationRelativeTo(null);

        txt_id.setVisible(false);
        txt_NumCartaoC.setEditable(false);
        txt_ValorFatura.setEditable(false);
        txt_Bandeira.setEditable(false);
        txt_Limite.setEditable(false);
        txt_DiaFatura.setEditable(false);

    }

    void inicio() {

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

    void sair() {
        //telaCliente cadastroCliente = new telaCliente();
        TelaLogin tela_login = new TelaLogin();
        tela_login.setVisible(true);
        this.dispose();
    }

    void receita() {

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

    void cadastra_cartao() {

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
    
    void TelaFatura() {

        TelaFatura telaFatura = null;
        
        if (!txt_NumCartaoC.getText().isEmpty()) {
            
            if (telaFatura == null) {

                telaFatura = new TelaFatura();

                telaFatura.setVisible(true);

                telaFatura.receberIdAndNumCartao(txt_NumCartaoC.getText(),txt_id.getText());

            } else {

                telaFatura.setVisible(true);

                telaFatura.setState(TelaPrincipal.NORMAL);
                  
                telaFatura.receberIdAndNumCartao(txt_NumCartaoC.getText(),txt_id.getText());

            }
            
             this.dispose();
        }else{
            
            JOptionPane.showMessageDialog(null, "Selecione um Cartão de Crédito", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
        }

    }

    void RecarregaTabela_CartaoCC() {

        boolean salvaLinhaAtiva = false;
        
        ControlerTabela.LimpaTabela(jtConsultaCC);

        try {

            CartaoCreditoDAO cartao_c = new CartaoCreditoDAO();

            DefaultTableModel mp = (DefaultTableModel) jtConsultaCC.getModel();

            LinkedList<CartaoCredito> lista_CC = cartao_c.CarregaTabela_Cartao_C(Integer.parseInt(txt_id.getText()));

            for (CartaoCredito cartao : lista_CC) {

                String Col0 = Long.toString(cartao.getN_cartao_credito());
                String Col1 = Float.toString(cartao.getLimite());
                String Col2 = Float.toString(cartao.getCredito());
                String Col3 = Integer.toString(cartao.getDia_fatura());
                String Col4 = Float.toString(cartao.getValor_fatura());
                String Col5 = cartao.getBandeira();

                mp.addRow(new String[]{Col0, Col1, Col2, Col3, Col4, Col5});

            }

            lista_CC.clear();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage());

        }

    }

    void update_cartao_credito() {
        if (!(salvaLinhaAtiva)) {
            JOptionPane.showMessageDialog(null, "Nenhum Cartão de Credito foi selecionada para ser atualizado");
            return;
        }
        CartaoCredito cartao_aux = new CartaoCredito();

        if (cartao_aux.UpdateEhVazio(txt_NumCartaoC.getText(), txt_ValorFatura.getText(), txt_Limite.getText(), txt_Bandeira.getText(), txt_DiaFatura.getText())) {
            JOptionPane.showMessageDialog(null, "Nenhum Campo ser vazio");
            return;
        }

        if (!(cartao_aux.Update_CamposValidos(txt_ValorFatura.getText(), txt_Bandeira.getText(), txt_Limite.getText(), txt_DiaFatura.getText()))) {
            JOptionPane.showMessageDialog(null, "Valor inválido");
            return;
        }
        Validacao valida = new Validacao();

        if (!(valida.ehNum(txt_NumCartaoC.getText()))) {
            JOptionPane.showMessageDialog(null, "Valor inválido");
            return;
        }

        CartaoCredito cartao_c = new CartaoCredito(
                Long.parseLong(txt_NumCartaoC.getText()),
                Float.parseFloat(txt_Limite.getText()),
                Integer.parseInt(txt_DiaFatura.getText()),
                Float.parseFloat(txt_ValorFatura.getText()),
                txt_Bandeira.getText(),
                Integer.parseInt(txt_id.getText()),
                salva_num_cartao
        );

        CartaoCreditoDAO cartao_creditoDAO = new CartaoCreditoDAO();

        try {

            if (cartao_c.varifica_valor_fatura()
                    && cartao_c.verifica_bandeira_credito()
                    && cartao_c.verifica_dia_fatura()
                    && cartao_c.verifica_limite()) {

                cartao_creditoDAO.UpdateCartaoCredito(cartao_c);

            } else {

                JOptionPane.showMessageDialog(null, "Dados Inválidos, impossível atuzalizar!!");

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    void delete_cartao_credito() {

        if (!(salvaLinhaAtiva)) {
            JOptionPane.showMessageDialog(null, "Nenhum Cartão de Credito foi selecionado para ser atualizado");
            return;
        }

        CartaoCredito cartao_c = new CartaoCredito(
                salva_num_cartao
        );

        CartaoCreditoDAO cartao_creditoDAO = new CartaoCreditoDAO();

        try {

            cartao_creditoDAO.DeleteCartaoCredito(cartao_c);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    void LimpaCampos_CC() {

        txt_NumCartaoC.setText("");
        txt_ValorFatura.setText("");
        txt_Bandeira.setText("");
        txt_Bandeira.setText("");
        txt_Limite.setText("");
        txt_DiaFatura.setText("");

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
        txt_DiaFatura = new javax.swing.JTextField();
        txt_ValorFatura = new javax.swing.JTextField();
        txt_Bandeira = new javax.swing.JTextField();
        txt_NumCartaoC = new javax.swing.JTextField();
        txt_Limite = new javax.swing.JTextField();
        rbDescendente = new javax.swing.JRadioButton();
        rbAscendente = new javax.swing.JRadioButton();
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
        numCardTitle = new javax.swing.JLabel();
        invoiceValueTitle = new javax.swing.JLabel();
        invoiceDayTitle = new javax.swing.JLabel();
        flagTitle = new javax.swing.JLabel();
        ordenationTitle = new javax.swing.JLabel();
        limitTitle = new javax.swing.JLabel();
        findTextType = new javax.swing.JLabel();
        background = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
                "Nº Cartão Crédito", "Limite", "Crédito", "Dia da Fatura", "Valor da Fatura", "Bandeira"
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

        getContentPane().add(infoTable);
        infoTable.setBounds(30, 150, 740, 110);

        selectorType.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        selectorType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nº Cartão", "Limite", "Dia da Fatura", "Valor da Fatura", "Bandeira", " ", " ", " " }));
        getContentPane().add(selectorType);
        selectorType.setBounds(30, 100, 130, 27);

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
        txt_Pesquisa.setBounds(170, 100, 350, 27);

        txt_DiaFatura.setBackground(new java.awt.Color(187, 210, 240));
        txt_DiaFatura.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_DiaFatura);
        txt_DiaFatura.setBounds(310, 360, 50, 27);

        txt_ValorFatura.setBackground(new java.awt.Color(187, 210, 240));
        txt_ValorFatura.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_ValorFatura);
        txt_ValorFatura.setBounds(450, 310, 150, 27);

        txt_Bandeira.setBackground(new java.awt.Color(187, 210, 240));
        txt_Bandeira.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt_Bandeira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BandeiraActionPerformed(evt);
            }
        });
        getContentPane().add(txt_Bandeira);
        txt_Bandeira.setBounds(30, 360, 250, 27);

        txt_NumCartaoC.setBackground(new java.awt.Color(187, 210, 240));
        txt_NumCartaoC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_NumCartaoC);
        txt_NumCartaoC.setBounds(30, 310, 400, 27);

        txt_Limite.setBackground(new java.awt.Color(187, 210, 240));
        txt_Limite.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_Limite);
        txt_Limite.setBounds(450, 360, 150, 27);

        rbDescendente.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        rbDescendente.setText("Descendente");
        rbDescendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDescendenteActionPerformed(evt);
            }
        });
        getContentPane().add(rbDescendente);
        rbDescendente.setBounds(610, 100, 110, 27);

        rbAscendente.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        rbAscendente.setText("Ascendente");
        rbAscendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAscendenteActionPerformed(evt);
            }
        });
        getContentPane().add(rbAscendente);
        rbAscendente.setBounds(520, 100, 100, 27);

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
        btn_novoCartao_cc.setBounds(630, 310, 140, 27);

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
        btn_exclui_cc.setBounds(630, 430, 140, 27);

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
        btPesquisarCC.setBounds(730, 100, 40, 40);

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
        btn_update.setBounds(630, 350, 140, 27);

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
        btnListaFaturas.setText("Listar Faturas");
        btnListaFaturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaFaturasActionPerformed(evt);
            }
        });
        getContentPane().add(btnListaFaturas);
        btnListaFaturas.setBounds(630, 390, 140, 27);

        findTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        findTitle.setText("Pesquisar por");
        getContentPane().add(findTitle);
        findTitle.setBounds(30, 80, 100, 27);

        numCardTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        numCardTitle.setText("Número do cartão");
        getContentPane().add(numCardTitle);
        numCardTitle.setBounds(30, 290, 130, 27);

        invoiceValueTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        invoiceValueTitle.setText("Valor da Fatura");
        getContentPane().add(invoiceValueTitle);
        invoiceValueTitle.setBounds(450, 290, 100, 27);

        invoiceDayTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        invoiceDayTitle.setText("Dia da Fatura");
        getContentPane().add(invoiceDayTitle);
        invoiceDayTitle.setBounds(310, 340, 100, 27);

        flagTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        flagTitle.setText("Bandeira");
        getContentPane().add(flagTitle);
        flagTitle.setBounds(30, 340, 100, 27);

        ordenationTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        ordenationTitle.setText("Ordenação");
        getContentPane().add(ordenationTitle);
        ordenationTitle.setBounds(520, 80, 100, 27);

        limitTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        limitTitle.setText("Limite");
        getContentPane().add(limitTitle);
        limitTitle.setBounds(450, 340, 100, 27);

        findTextType.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        findTextType.setText("Digite sua pesquisa aqui");
        getContentPane().add(findTextType);
        findTextType.setBounds(170, 80, 180, 27);

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

    private void txt_PesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_PesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_PesquisaActionPerformed

    private void txt_PesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PesquisaKeyReleased
        // TODO add your handling code here

        if (txt_Pesquisa.getText().isEmpty()) {
            RecarregaTabela_CartaoCC();
        }

    }//GEN-LAST:event_txt_PesquisaKeyReleased

    private void btPesquisarCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarCCActionPerformed
        // TODO add your handling code here:

        if (rbAscendente.isSelected() || rbDescendente.isSelected()) {

            boolean ordenar = true;

            String tipo = "";

            String escolha = selectorType.getSelectedItem().toString().trim();

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

            ControlerTabela.LimpaTabela(jtConsultaCC);

            try {

                CartaoCreditoDAO cartao_c = new CartaoCreditoDAO();

                DefaultTableModel mp = (DefaultTableModel) jtConsultaCC.getModel();

                LinkedList<CartaoCredito> lista_CC = cartao_c.ConsultaCartao_C(tipo, argumento, Integer.parseInt(txt_id.getText()), ordenar);

                for (CartaoCredito cartao : lista_CC) {

                    String Col0 = Long.toString(cartao.getN_cartao_credito());
                    String Col1 = Float.toString(cartao.getLimite());
                    String Col2 = Float.toString(cartao.getCredito());
                    String Col3 = Integer.toString(cartao.getDia_fatura());
                    String Col4 = Float.toString(cartao.getValor_fatura());
                    String Col5 = cartao.getBandeira();

                    mp.addRow(new String[]{Col0, Col1, Col2, Col3, Col4, Col5});

                }

                lista_CC.clear();

            } catch (Exception e) {

                JOptionPane.showMessageDialog(this, e.getMessage());

            }

        } else {

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

        if (rbAscendente.isSelected()) {

            rbDescendente.setSelected(false);

        }

    }//GEN-LAST:event_rbAscendenteActionPerformed

    private void rbDescendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDescendenteActionPerformed
        // TODO add your handling code here:

        if (rbDescendente.isSelected()) {

            rbAscendente.setSelected(false);

        }

    }//GEN-LAST:event_rbDescendenteActionPerformed

    private void jtConsultaCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtConsultaCCMouseClicked
        // TODO add your handling code here:

        String num_cartao = "" + jtConsultaCC.getValueAt(jtConsultaCC.getSelectedRow(), 0);
        String limite = "" + jtConsultaCC.getValueAt(jtConsultaCC.getSelectedRow(), 1);
        String diaFatura = "" + jtConsultaCC.getValueAt(jtConsultaCC.getSelectedRow(), 3);
        String valorFatura = "" + jtConsultaCC.getValueAt(jtConsultaCC.getSelectedRow(), 4);
        String bandeira = "" + jtConsultaCC.getValueAt(jtConsultaCC.getSelectedRow(), 5);
        
        txt_NumCartaoC.setText(num_cartao);
        txt_Limite.setText(limite);
        txt_ValorFatura.setText(diaFatura);
        txt_DiaFatura.setText(valorFatura);
        txt_Bandeira.setText(bandeira);
        salva_num_cartao = Long.parseLong(num_cartao);
        
        int selLinha = -1;
        selLinha = jtConsultaCC.getSelectedRow();

        if (selLinha != -1) {
            salvaLinhaAtiva = true;
        }
    }//GEN-LAST:event_jtConsultaCCMouseClicked

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:

        if (btn_update.getText().equals("Alterar")) {

            btn_update.setText("Atualizar");

            txt_NumCartaoC.setEditable(true);
            txt_ValorFatura.setEditable(true);
            txt_Bandeira.setEditable(true);
            txt_Limite.setEditable(true);
            txt_DiaFatura.setEditable(true);

        } else {

            btn_update.setText("Alterar");

            txt_NumCartaoC.setEditable(false);
            txt_ValorFatura.setEditable(false);
            txt_Bandeira.setEditable(false);
            txt_Limite.setEditable(false);
            txt_DiaFatura.setEditable(false);
            
            boolean atualiza = true;
            
            CartaoCreditoDAO cartaoDAO = new CartaoCreditoDAO();
            
            CartaoCredito cartaoCC = new CartaoCredito(
                                      Long.parseLong(txt_NumCartaoC.getText()),
                                      Float.parseFloat(txt_Limite.getText()),
                                      Integer.parseInt(txt_DiaFatura.getText()),
                                      Float.parseFloat(txt_ValorFatura.getText()),
                                      txt_Bandeira.getText(),
                                      Integer.parseInt(txt_id.getText())
                                            
            );
            
            if(!(cartaoCC.verifica_num_cartao_credito())){
                
                JOptionPane.showMessageDialog(null, "Número do cartão de crédito inválido","WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                
                atualiza = false;
            }
            
            if (atualiza) {

                update_cartao_credito();
                RecarregaTabela_CartaoCC();
                LimpaCampos_CC();
                
                JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                
            }else{
                
                JOptionPane.showMessageDialog(null, "Erro ao atualizar","WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                
            }

        }

    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_exclui_ccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exclui_ccActionPerformed
        // TODO add your handling code here:
        delete_cartao_credito();
        RecarregaTabela_CartaoCC();
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
        TelaFatura();
    }//GEN-LAST:event_btnListaFaturasActionPerformed

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
    private javax.swing.JLabel background;
    private javax.swing.JButton btPesquisarCC;
    private javax.swing.JButton btnListaFaturas;
    private javax.swing.JButton btn_exclui_cc;
    private javax.swing.JButton btn_inicio_CC;
    private javax.swing.JButton btn_novoCartao_cc;
    private javax.swing.JButton btn_receitaCC;
    private javax.swing.JButton btn_sairCC;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel findTextType;
    private javax.swing.JLabel findTitle;
    private javax.swing.JLabel flagTitle;
    private javax.swing.JScrollPane infoTable;
    private javax.swing.JLabel invoiceDayTitle;
    private javax.swing.JLabel invoiceValueTitle;
    private javax.swing.JTable jtConsultaCC;
    private javax.swing.JLabel limitTitle;
    private javax.swing.JLabel numCardTitle;
    private javax.swing.JLabel ordenationTitle;
    private javax.swing.JLabel pageTitle;
    private javax.swing.JRadioButton rbAscendente;
    private javax.swing.JRadioButton rbDescendente;
    private javax.swing.JComboBox<String> selectorType;
    private javax.swing.JTextField txt_Bandeira;
    private javax.swing.JTextField txt_DiaFatura;
    private javax.swing.JTextField txt_Limite;
    private javax.swing.JTextField txt_NumCartaoC;
    private javax.swing.JTextField txt_Pesquisa;
    private javax.swing.JTextField txt_ValorFatura;
    private javax.swing.JTextField txt_id;
    private javax.swing.JButton userButton;
    // End of variables declaration//GEN-END:variables

    public void receberID(String recebe) {

        txt_id.setText(recebe);
    }

}
