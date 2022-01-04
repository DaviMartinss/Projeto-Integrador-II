/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package Views;

import DAO.CartaoDebitoDAO;
import Model.CartaoDebito;
import ValidacaoComum.Validacao;
import Controllers.ControlerTabela;
import java.util.LinkedList;
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
        txt_id.setVisible(false);

        txt_NumCartaoD.setEditable(false);
        txt_Valor.setEditable(false);
        txt_Bandeira.setEditable(false);
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

    void cadastraCartao_Deb() {

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

    void RecarregaTabela_CartaoDB() {
        salvaLinhaAtiva = false;

        ControlerTabela.LimpaTabela(jtConsultaCD);

        try {

            CartaoDebitoDAO cartao_c = new CartaoDebitoDAO();

            DefaultTableModel mp = (DefaultTableModel) jtConsultaCD.getModel();

            LinkedList<CartaoDebito> lista_CD = cartao_c.CarregaTabela_Cartao_D(Integer.parseInt(txt_id.getText()));

            for (CartaoDebito cartao : lista_CD) {

                String Col0 = Long.toString(cartao.getN_cartao_debito());
                String Col1 = Float.toString(cartao.getValor_atual());
                String Col2 = cartao.getBandeira();

                mp.addRow(new String[]{Col0, Col1, Col2});

            }

            lista_CD.clear();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage());

        }

    }

    void update_cartao_debito() {
        if (!(salvaLinhaAtiva)) {
            JOptionPane.showMessageDialog(null, "Nenhum Cartão de Credito foi selecionada para ser atualizado");
            return;
        }

        CartaoDebito cartao_aux = new CartaoDebito();
        if (cartao_aux.UpdateEhVazio(txt_NumCartaoD.getText(), txt_Valor.getText(), txt_Bandeira.getText())) {
            JOptionPane.showMessageDialog(null, "Nenhum Campo pode ser nulo no Update");
            return;
        }

        Validacao valida = new Validacao();

        if (!(valida.ehNum(txt_NumCartaoD.getText()) && valida.ehNum(txt_Valor.getText()))) {
            JOptionPane.showMessageDialog(null, "Informe um valor numérico válido!!");
            return;
        }

        CartaoDebito cartao_d = new CartaoDebito(
                Long.parseLong(txt_NumCartaoD.getText()),
                Float.parseFloat(txt_Valor.getText()),
                txt_Bandeira.getText(),
                Integer.parseInt(txt_id.getText()),
                salva_num_cartao_debito
        );

        CartaoDebitoDAO cartao_debitoDAO = new CartaoDebitoDAO();

        try {

            if (cartao_d.verifica_Bandeira_cartao_deb()
                    && cartao_d.verifica_valor_atual()) {

                cartao_debitoDAO.UpdateCartaoDebito(cartao_d);

            } else {

                JOptionPane.showMessageDialog(null, "Dados Inválidos, impossível atuzalizar!!");

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    void delete_cartao_debito() {
        if (!(salvaLinhaAtiva)) {
            JOptionPane.showMessageDialog(null, "Nenhum Cartão de Débito foi selecionada para ser deletado");
            return;
        }
        CartaoDebito cartao_d = new CartaoDebito(
                Long.parseLong(txt_NumCartaoD.getText())
        );

        CartaoDebitoDAO cartao_debitoDAO = new CartaoDebitoDAO();

        try {

            cartao_debitoDAO.DeleteCartaoDebito(cartao_d);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    void LimpaCampos_CD() {

        txt_NumCartaoD.setText("");
        txt_Valor.setText("");
        txt_Bandeira.setText("");

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
        txt_NumCartaoD = new javax.swing.JTextField();
        txt_Valor = new javax.swing.JTextField();
        txt_Bandeira = new javax.swing.JTextField();
        txt_Pesquisa = new javax.swing.JTextField();
        selectorUp = new javax.swing.JRadioButton();
        selectorDown = new javax.swing.JRadioButton();
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
        cardNumTitle = new javax.swing.JLabel();
        findByTitle = new javax.swing.JLabel();
        flagTitle = new javax.swing.JLabel();
        valueTitle = new javax.swing.JLabel();
        ordenationTitle = new javax.swing.JLabel();
        findTextTitle = new javax.swing.JLabel();
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
        pageTitle.setText("Cartão de Débito");
        getContentPane().add(pageTitle);
        pageTitle.setBounds(320, 0, 160, 27);

        cbbTipo.setBackground(new java.awt.Color(187, 210, 240));
        cbbTipo.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        cbbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nº Cartão", "Valor", "Bandeira", " ", " ", " " }));
        getContentPane().add(cbbTipo);
        cbbTipo.setBounds(20, 90, 140, 27);

        txt_NumCartaoD.setBackground(new java.awt.Color(187, 210, 240));
        txt_NumCartaoD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_NumCartaoD);
        txt_NumCartaoD.setBounds(20, 340, 400, 27);

        txt_Valor.setBackground(new java.awt.Color(187, 210, 240));
        txt_Valor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_Valor);
        txt_Valor.setBounds(20, 390, 400, 27);

        txt_Bandeira.setBackground(new java.awt.Color(187, 210, 240));
        txt_Bandeira.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt_Bandeira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BandeiraActionPerformed(evt);
            }
        });
        getContentPane().add(txt_Bandeira);
        txt_Bandeira.setBounds(20, 440, 400, 27);

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
        txt_Pesquisa.setBounds(170, 90, 350, 27);

        selectorUp.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        selectorUp.setText("Ascendente");
        selectorUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectorUpActionPerformed(evt);
            }
        });
        getContentPane().add(selectorUp);
        selectorUp.setBounds(530, 90, 90, 27);

        selectorDown.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        selectorDown.setText("Descendente");
        selectorDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectorDownActionPerformed(evt);
            }
        });
        getContentPane().add(selectorDown);
        selectorDown.setBounds(620, 90, 110, 27);

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
        btPesquisarCD.setBounds(740, 90, 40, 40);

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
        newCardButton.setBounds(630, 340, 140, 27);

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
        delCardButton.setBounds(630, 440, 140, 27);

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
        btn_update.setBounds(630, 390, 140, 27);

        cardNumTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        cardNumTitle.setText("Número do cartão");
        getContentPane().add(cardNumTitle);
        cardNumTitle.setBounds(20, 320, 140, 27);

        findByTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        findByTitle.setText("Pesquisar por");
        getContentPane().add(findByTitle);
        findByTitle.setBounds(20, 70, 100, 27);

        flagTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        flagTitle.setText("Bandeira");
        getContentPane().add(flagTitle);
        flagTitle.setBounds(20, 420, 70, 27);

        valueTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        valueTitle.setText("Valor");
        getContentPane().add(valueTitle);
        valueTitle.setBounds(20, 370, 100, 27);

        ordenationTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        ordenationTitle.setText("Ordenação");
        getContentPane().add(ordenationTitle);
        ordenationTitle.setBounds(530, 70, 80, 27);

        findTextTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        findTextTitle.setText("Digite sua pesquisa aqui");
        getContentPane().add(findTextTitle);
        findTextTitle.setBounds(170, 70, 170, 27);

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
        inicio();
    }//GEN-LAST:event_startButtonActionPerformed

    private void incomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incomeButtonActionPerformed
        // TODO add your handling code here:
        receita();
    }//GEN-LAST:event_incomeButtonActionPerformed

    private void newCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newCardButtonActionPerformed
        // TODO add your handling code here:
        cadastraCartao_Deb();
    }//GEN-LAST:event_newCardButtonActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void txt_BandeiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BandeiraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BandeiraActionPerformed

    private void txt_PesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_PesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_PesquisaActionPerformed

    private void txt_PesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PesquisaKeyReleased
        // TODO add your handling code here

        if (txt_Pesquisa.getText().isEmpty()) {
            RecarregaTabela_CartaoDB();
        }

    }//GEN-LAST:event_txt_PesquisaKeyReleased

    private void selectorUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectorUpActionPerformed
        // TODO add your handling code here:

        if (selectorUp.isSelected()) {

            selectorDown.setSelected(false);

        }

    }//GEN-LAST:event_selectorUpActionPerformed

    private void selectorDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectorDownActionPerformed
        // TODO add your handling code here:

        if (selectorDown.isSelected()) {

            selectorUp.setSelected(false);

        }

    }//GEN-LAST:event_selectorDownActionPerformed

    private void btPesquisarCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarCDActionPerformed
        // TODO add your handling code here:

        if (selectorUp.isSelected() || selectorDown.isSelected()) {

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

            if (selectorUp.isSelected()) {

                ordenar = true;

            } else {

                ordenar = false;
            }

            String argumento = txt_Pesquisa.getText();
            
            ControlerTabela.LimpaTabela(jtConsultaCD);

            try {

                CartaoDebitoDAO cartao_d = new CartaoDebitoDAO();

                DefaultTableModel mp = (DefaultTableModel) jtConsultaCD.getModel();

                LinkedList<CartaoDebito> lista_CD = cartao_d.ConsultaCartao_D(tipo, argumento, Integer.parseInt(txt_id.getText()), ordenar);

                for (CartaoDebito cartao : lista_CD) {

                    String Col0 = Long.toString(cartao.getN_cartao_debito());
                    String Col1 = Float.toString(cartao.getValor_atual());
                    String Col2 = cartao.getBandeira();

                    mp.addRow(new String[]{Col0, Col1, Col2});

                }

                lista_CD.clear();

            } catch (Exception e) {

                JOptionPane.showMessageDialog(this, e.getMessage());

            }

        } else {

            JOptionPane.showMessageDialog(null, "Tipo de Ordenação Obrigatório");

        }

    }//GEN-LAST:event_btPesquisarCDActionPerformed

    private void jtConsultaCDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtConsultaCDMouseClicked
        // TODO add your handling code here:

        String num_cartao = "" + jtConsultaCD.getValueAt(jtConsultaCD.getSelectedRow(), 0);
        String valor = "" + jtConsultaCD.getValueAt(jtConsultaCD.getSelectedRow(), 1);
        String bandeira = "" + jtConsultaCD.getValueAt(jtConsultaCD.getSelectedRow(), 2);

        txt_NumCartaoD.setText(num_cartao);
        txt_Valor.setText(valor);
        txt_Bandeira.setText(bandeira);
        salva_num_cartao_debito = Long.parseLong(num_cartao);
    }//GEN-LAST:event_jtConsultaCDMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:

        RecarregaTabela_CartaoDB();

    }//GEN-LAST:event_formWindowOpened

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:

        if (btn_update.getText().equals("Alterar")) {

            btn_update.setText("Atualizar");

            txt_NumCartaoD.setEditable(true);
            txt_Valor.setEditable(true);
            txt_Bandeira.setEditable(true);

        } else {

            btn_update.setText("Alterar");

            txt_NumCartaoD.setEditable(false);
            txt_Valor.setEditable(false);
            txt_Bandeira.setEditable(false);

            boolean atualiza = true;

            CartaoDebitoDAO cartaoDAO = new CartaoDebitoDAO();

            CartaoDebito cartaoDB = new CartaoDebito(
                    Long.parseLong(txt_NumCartaoD.getText()),
                    Float.parseFloat(txt_Valor.getText()),
                    txt_Bandeira.getText(),
                    Integer.parseInt(txt_id.getText())
            );

            if (!(cartaoDB.verifica_num_cartao_deb())) {

                JOptionPane.showMessageDialog(null, "Número do cartão de débito inválido", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);

                atualiza = false;
            }

            if (atualiza) {

                update_cartao_debito();
                RecarregaTabela_CartaoDB();
                LimpaCampos_CD();
                
                JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                
            }else{
                
                JOptionPane.showMessageDialog(null, "Erro ao atualizar","WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                
            }

        }


    }//GEN-LAST:event_btn_updateActionPerformed

    private void delCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delCardButtonActionPerformed
        // TODO add your handling code here:
        delete_cartao_debito();
        RecarregaTabela_CartaoDB();
    }//GEN-LAST:event_delCardButtonActionPerformed

    private void userButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userButtonActionPerformed
        // TODO add your handling code here:

        TelaUsuario();
    }//GEN-LAST:event_userButtonActionPerformed

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
    private javax.swing.JLabel background;
    private javax.swing.JButton btPesquisarCD;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel cardNumTitle;
    private javax.swing.JScrollPane cardTable;
    private javax.swing.JComboBox<String> cbbTipo;
    private javax.swing.JButton delCardButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel findByTitle;
    private javax.swing.JLabel findTextTitle;
    private javax.swing.JLabel flagTitle;
    private javax.swing.JButton incomeButton;
    private javax.swing.JTable jtConsultaCD;
    private javax.swing.JButton newCardButton;
    private javax.swing.JLabel ordenationTitle;
    private javax.swing.JLabel pageTitle;
    private javax.swing.JRadioButton selectorDown;
    private javax.swing.JRadioButton selectorUp;
    private javax.swing.JButton startButton;
    private javax.swing.JTextField txt_Bandeira;
    private javax.swing.JTextField txt_NumCartaoD;
    private javax.swing.JTextField txt_Pesquisa;
    private javax.swing.JTextField txt_Valor;
    private javax.swing.JTextField txt_id;
    private javax.swing.JButton userButton;
    private javax.swing.JLabel valueTitle;
    // End of variables declaration//GEN-END:variables

    public void receberID(String recebe) {

        txt_id.setText(recebe);
    }

}
