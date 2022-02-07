package Views;

import Controllers.ControlerDespesa;
import Controllers.ControlerReceita;
import Controllers.ControlerTabela;
import Model.Despesa;
import Model.Receita;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ValidacaoComum.Validacao;

/**
 *
 * @author Alan
 * @author Davi
 */
public class TelaDespesa extends javax.swing.JFrame {

    String salvaF_pagamento = null;
    String salvaStatus = null;
    int salvaCodigoDespesa = -1;
    boolean salvaLinhaAtiva = false;

    public TelaDespesa() {
        initComponents();
        this.setLocationRelativeTo(null);
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
    
    void TelaAtualizarDespesa(Despesa despesa) {

        TelaAtualizarDespesa telaAtualizarDespesa = null;

        if (telaAtualizarDespesa == null) {

            telaAtualizarDespesa = new TelaAtualizarDespesa();

            telaAtualizarDespesa.setVisible(true);

            telaAtualizarDespesa.receberDespesa(despesa);

        } else {

            telaAtualizarDespesa.setVisible(true);

            telaAtualizarDespesa.setState(TelaPrincipal.NORMAL);

            telaAtualizarDespesa.receberDespesa(despesa);
        }

        this.dispose();
    }

    void TelaCartaoCredito() {

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

    void TelaCartaoDebito() {

        TelaCartao_debito TelaCartao_debito = null;

        if (TelaCartao_debito == null) {

            TelaCartao_debito = new TelaCartao_debito();

            TelaCartao_debito.setVisible(true);

            TelaCartao_debito.receberID(txt_id.getText());

        } else {

            TelaCartao_debito.setVisible(true);

            TelaCartao_debito.setState(TelaPrincipal.NORMAL);

            TelaCartao_debito.receberID(txt_id.getText());

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

    void TelaCadastrar_cadastrar() {

        TelaDespesa_cadastrar TelaDespesa_cadastrar = null;

        if (TelaDespesa_cadastrar == null) {

            TelaDespesa_cadastrar = new TelaDespesa_cadastrar();

            TelaDespesa_cadastrar.setVisible(true);

            TelaDespesa_cadastrar.receberID(txt_id.getText());

        } else {

            TelaDespesa_cadastrar.setVisible(true);

            TelaDespesa_cadastrar.setState(TelaPrincipal.NORMAL);

            TelaDespesa_cadastrar.receberID(txt_id.getText());

        }

        this.dispose();

    }
    
    void RecarregaTabela_Despesa() {
        
        salvaLinhaAtiva = false;     
        
        ControlerTabela.LimpaTabela(jtConsultaDespesa);
        
        DefaultTableModel mp = (DefaultTableModel) jtConsultaDespesa.getModel();
        
        ControlerTabela.RecarregaTabela(mp, Integer.parseInt(txt_id.getText()), "Despesa");
    }
    
    void PesquisaDespesa(boolean ordenar) {

        String tipo = "";
        
        boolean filtroConsulta = true;

        String escolha = cbbTipo.getSelectedItem().toString().trim();
        
        switch(escolha){
            
            case "Valor":
                tipo = " " + "valor";
                break;
                
            case "Categoria":
                tipo = " " + "categoriaTipo";
                break;
                
            case "Número do Cartão Crédito":
                tipo = " " + "num_cartao_credito";
                break;
                
            case "Número do Cartão Débito":
                tipo = " " + "num_cartao_debito";
                break;
            
            case "Dia":
                tipo = " " + "dia";
                break;
                
            case "Mês":
                tipo = " " + "mes";
                break;
                
            case "Ano":
                tipo = " " + "ano";
                break;
                
            case "Descrição":
                tipo = " " + "descricao";
                break;
                
            case "Nº Parcelas":
                tipo = " " + "n_parcelas";
                break;

            case "Forma de Pagamento":
                tipo = " " + "f_pagamento";
                break;

            case "Estatus":
                tipo = " " + "estatus";
                break;

            default:
                JOptionPane.showMessageDialog(this, "Tipo de Filtro Inválido"); filtroConsulta = false;
        }

        String argumento = txt_Pesquisa.getText();

        ControlerTabela.LimpaTabela(jtConsultaDespesa);

        try {

            Receita receita = null;
            
            if(!(txtMesReceita.getText().isEmpty() && txtAnoReceita.getText().isEmpty())){
                
                receita = new Receita();
                
                receita.setId_conta(Integer.parseInt(txt_id.getText()));
                receita.setMes(Integer.parseInt(txtMesReceita.getText()));
                receita.setAno(Integer.parseInt(txtAnoReceita.getText()));
            }

            DefaultTableModel mp = (DefaultTableModel) jtConsultaDespesa.getModel();
            
            if (filtroConsulta) 
            {
                if (receita != null) 
                {
                    if (ControlerReceita.GetReceita(Integer.parseInt(txtMesReceita.getText()), Integer.parseInt(txtAnoReceita.getText()), Integer.parseInt(txt_id.getText())) != null) {

                        ControlerTabela.RecarregaTabelaConsulta(mp, tipo, argumento, Integer.parseInt(txt_id.getText()), ordenar, receita, "Despesa");

                    } else {
                        JOptionPane.showMessageDialog(this, "Mês ou Ano da receita inválidos ou inexistentes!");
                    }
                } 
                else 
                {
                    ControlerTabela.RecarregaTabelaConsulta(mp, tipo, argumento, Integer.parseInt(txt_id.getText()), ordenar, null, "Despesa");
                }
            }
            else
                JOptionPane.showMessageDialog(this, "Erro na filtragem da consulta");
            
        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this, "Erro:PesquisaDespesa - " + e.getMessage());
        }
    }

    void AtualizarDespesa() {

        if (!(salvaLinhaAtiva)) {
            JOptionPane.showMessageDialog(this, "Nenhuma despesa foi selecionada para ser atualizda", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String dia = "" + jtConsultaDespesa.getValueAt(jtConsultaDespesa.getSelectedRow(), 1);
        String mes = "" + jtConsultaDespesa.getValueAt(jtConsultaDespesa.getSelectedRow(), 2);
        String ano = "" + jtConsultaDespesa.getValueAt(jtConsultaDespesa.getSelectedRow(), 3);
        String valor = "" + jtConsultaDespesa.getValueAt(jtConsultaDespesa.getSelectedRow(), 4);
        String categoria = "" + jtConsultaDespesa.getValueAt(jtConsultaDespesa.getSelectedRow(), 5);
        String formaPagamento = "" + jtConsultaDespesa.getValueAt(jtConsultaDespesa.getSelectedRow(), 6);
        String numCartao = "" + jtConsultaDespesa.getValueAt(jtConsultaDespesa.getSelectedRow(), 7);
        String numParcelas = "" + jtConsultaDespesa.getValueAt(jtConsultaDespesa.getSelectedRow(), 8);
        String status = "" + jtConsultaDespesa.getValueAt(jtConsultaDespesa.getSelectedRow(), 9);
        String descricao = "" + jtConsultaDespesa.getValueAt(jtConsultaDespesa.getSelectedRow(), 10);
        
        Despesa despesa = null;
        
        switch(formaPagamento){
            
            case "CRÉDITO":
                despesa = new Despesa(
                        Integer.parseInt(dia),
                        Integer.parseInt(mes),
                        Integer.parseInt(ano),
                        Float.parseFloat(valor),
                        categoria,
                        formaPagamento,
                        Long.parseLong(numCartao),
                        Integer.parseInt(numParcelas),
                        status,
                        descricao,
                        salvaCodigoDespesa
                );
                break;

            case "DÉBITO":
                despesa = new Despesa(
                        Integer.parseInt(dia),
                        Integer.parseInt(mes),
                        Integer.parseInt(ano),
                        Float.parseFloat(valor),
                        categoria,
                        formaPagamento,
                        Long.parseLong(numCartao),
                        status,
                        descricao,
                        salvaCodigoDespesa
                );
                break;

            case "DINHEIRO":
                despesa = new Despesa(
                        Integer.parseInt(dia),
                        Integer.parseInt(mes),
                        Integer.parseInt(ano),
                        Float.parseFloat(valor),
                        categoria,
                        formaPagamento,
                        status,
                        descricao,
                        salvaCodigoDespesa
                );
                break;
                
            //VER USO DE ALGUMA EXCEPTION OU NAO    
            default:
                JOptionPane.showMessageDialog(this, "Forma de Pagamento Inexistente ou Não Implementada"); 
                return;

        }

        if ((despesa.getF_pagamento().equals("CRÉDITO") || despesa.getF_pagamento().equals("DÉBITO"))) {

            if (!(despesa.verifica_num_cartao_despesa())) {
                JOptionPane.showMessageDialog(this, "Número do cartão inválido", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        
        despesa.setId_conta(Integer.parseInt(txt_id.getText()));
        TelaAtualizarDespesa(despesa);
    }

    void ApagarDespesa() {

        if (!(salvaLinhaAtiva)) {
            JOptionPane.showMessageDialog(null, "Nenhuma despesa foi selecionada para ser deletada");
            return;
        }
        Despesa despesa = new Despesa(
                salvaCodigoDespesa
        );
        
        despesa.setId_conta(Integer.parseInt(txt_id.getText()));
        ControlerDespesa.ApagarDespesa(despesa);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jtConsultaDespesa = new javax.swing.JTable();
        cbbTipo = new javax.swing.JComboBox<>();
        txt_Pesquisa = new javax.swing.JTextField();
        txtMesReceita = new javax.swing.JTextField();
        txtAnoReceita = new javax.swing.JTextField();
        btnReceitas = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        btnNovaDespesa = new javax.swing.JToggleButton();
        btPesquisarDespesa = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        dateBar = new javax.swing.JLabel();
        findByTitle = new javax.swing.JLabel();
        invoiceDateTitle = new javax.swing.JLabel();
        findTextTitle = new javax.swing.JLabel();
        btnASC = new javax.swing.JButton();
        btnDESC = new javax.swing.JButton();
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
        pageTitle.setText("Despesa");
        getContentPane().add(pageTitle);
        pageTitle.setBounds(360, 0, 90, 27);

        jtConsultaDespesa.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jtConsultaDespesa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Dia", "Mês", "Ano", "Valor", "Categoria", "Forma de Pagamento", "Nº Cartão", "Nº Parcelas", "Estatus", "Descrição"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtConsultaDespesa.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jtConsultaDespesa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtConsultaDespesa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtConsultaDespesaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtConsultaDespesa);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(20, 170, 830, 210);

        cbbTipo.setBackground(new java.awt.Color(187, 210, 240));
        cbbTipo.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        cbbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Valor", "Categoria", "Descrição", "Forma de Pagamento", "Número do Cartão Crédito", "Número do Cartão Débito", "Estatus", "Dia", "Mês", "Ano", "Nº Parcelas" }));
        cbbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTipoActionPerformed(evt);
            }
        });
        getContentPane().add(cbbTipo);
        cbbTipo.setBounds(20, 80, 160, 27);

        txt_Pesquisa.setColumns(200);
        txt_Pesquisa.setName(""); // NOI18N
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
        txt_Pesquisa.setBounds(190, 80, 400, 27);

        txtMesReceita.setBackground(new java.awt.Color(187, 210, 240));
        txtMesReceita.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtMesReceita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMesReceitaKeyPressed(evt);
            }
        });
        getContentPane().add(txtMesReceita);
        txtMesReceita.setBounds(600, 80, 40, 27);

        txtAnoReceita.setBackground(new java.awt.Color(187, 210, 240));
        txtAnoReceita.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtAnoReceita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAnoReceitaKeyPressed(evt);
            }
        });
        getContentPane().add(txtAnoReceita);
        txtAnoReceita.setBounds(660, 80, 60, 27);

        btnReceitas.setBackground(new java.awt.Color(105, 69, 219));
        btnReceitas.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnReceitas.setForeground(new java.awt.Color(255, 255, 255));
        btnReceitas.setText("Receitas");
        btnReceitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceitasActionPerformed(evt);
            }
        });
        getContentPane().add(btnReceitas);
        btnReceitas.setBounds(680, 30, 100, 27);

        startButton.setBackground(new java.awt.Color(105, 69, 219));
        startButton.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        startButton.setForeground(new java.awt.Color(255, 255, 255));
        startButton.setText("Início");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        getContentPane().add(startButton);
        startButton.setBounds(20, 30, 90, 27);

        btnNovaDespesa.setBackground(new java.awt.Color(105, 69, 219));
        btnNovaDespesa.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnNovaDespesa.setForeground(new java.awt.Color(255, 255, 255));
        btnNovaDespesa.setText("Nova Despesa");
        btnNovaDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaDespesaActionPerformed(evt);
            }
        });
        getContentPane().add(btnNovaDespesa);
        btnNovaDespesa.setBounds(340, 440, 150, 27);

        btPesquisarDespesa.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btPesquisarDespesa.setForeground(new java.awt.Color(255, 255, 255));
        btPesquisarDespesa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/lupa.png"))); // NOI18N
        btPesquisarDespesa.setBorderPainted(false);
        btPesquisarDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarDespesaActionPerformed(evt);
            }
        });
        getContentPane().add(btPesquisarDespesa);
        btPesquisarDespesa.setBounds(740, 80, 40, 40);

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
        btn_update.setBounds(40, 440, 150, 27);

        btn_excluir.setBackground(new java.awt.Color(210, 59, 239));
        btn_excluir.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_excluir.setForeground(new java.awt.Color(255, 255, 255));
        btn_excluir.setText("Excluir");
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });
        getContentPane().add(btn_excluir);
        btn_excluir.setBounds(630, 440, 150, 27);

        dateBar.setFont(new java.awt.Font("Noto Serif", 1, 24)); // NOI18N
        dateBar.setText(" /");
        getContentPane().add(dateBar);
        dateBar.setBounds(640, 80, 20, 27);

        findByTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        findByTitle.setText("Pesquisar por");
        getContentPane().add(findByTitle);
        findByTitle.setBounds(20, 60, 100, 27);

        invoiceDateTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        invoiceDateTitle.setText("Receita: (mês e ano)");
        getContentPane().add(invoiceDateTitle);
        invoiceDateTitle.setBounds(600, 60, 140, 27);

        findTextTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        findTextTitle.setText("Digite sua pesquisa aqui");
        getContentPane().add(findTextTitle);
        findTextTitle.setBounds(190, 60, 180, 27);

        btnASC.setText("Ascendente");
        btnASC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnASCActionPerformed(evt);
            }
        });
        getContentPane().add(btnASC);
        btnASC.setBounds(20, 110, 120, 30);

        btnDESC.setText("Descendente");
        btnDESC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDESCActionPerformed(evt);
            }
        });
        getContentPane().add(btnDESC);
        btnDESC.setBounds(150, 110, 130, 30);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Back-2.png"))); // NOI18N
        background.setText("jLabel13");
        getContentPane().add(background);
        background.setBounds(0, 0, 1920, 1080);

        txt_id.setEditable(false);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        getContentPane().add(txt_id);
        txt_id.setBounds(2360, 119, 81, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReceitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceitasActionPerformed
        // TODO add your handling code here:
        TelaReceita();
    }//GEN-LAST:event_btnReceitasActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void btnNovaDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaDespesaActionPerformed
        // TODO add your handling code here:

        TelaCadastrar_cadastrar();

    }//GEN-LAST:event_btnNovaDespesaActionPerformed

    private void txt_PesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_PesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_PesquisaActionPerformed

    private void txt_PesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PesquisaKeyReleased
        // TODO add your handling code here

        if (txt_Pesquisa.getText().isEmpty()) {
            RecarregaTabela_Despesa();
        }

    }//GEN-LAST:event_txt_PesquisaKeyReleased

    private void btPesquisarDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarDespesaActionPerformed
        // TODO add your handling code here:
        Validacao valida = new Validacao();
        
        if(!(valida.ehNum(txtMesReceita.getText()) && valida.ehNum(txtAnoReceita.getText()) )){
            JOptionPane.showMessageDialog(this, "Erro na validação da consulta");
            return;
        }
        
        PesquisaDespesa(true);

    }//GEN-LAST:event_btPesquisarDespesaActionPerformed

    private void jtConsultaDespesaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtConsultaDespesaMouseClicked
        // TODO add your handling code here:

        int linhSel = jtConsultaDespesa.getSelectedRow();
        String id = (String) jtConsultaDespesa.getValueAt(linhSel, 0);
        salvaCodigoDespesa = Integer.parseInt(id);

        int selLinha = -1;
        selLinha = jtConsultaDespesa.getSelectedRow();

        if (selLinha != -1) {
            salvaLinhaAtiva = true;
        }
    }//GEN-LAST:event_jtConsultaDespesaMouseClicked

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // TODO add your handling code here:
        TelaPrincipal();
    }//GEN-LAST:event_startButtonActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        RecarregaTabela_Despesa();
    }//GEN-LAST:event_formWindowOpened

    private void txtMesReceitaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMesReceitaKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtMesReceitaKeyPressed

    private void txtAnoReceitaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnoReceitaKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtAnoReceitaKeyPressed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:

        AtualizarDespesa();

 
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        // TODO add your handling code here:
        ApagarDespesa();
        RecarregaTabela_Despesa();
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void cbbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbTipoActionPerformed

    private void btnASCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnASCActionPerformed
        // TODO add your handling code here:
        
        PesquisaDespesa(true);
        
    }//GEN-LAST:event_btnASCActionPerformed

    private void btnDESCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDESCActionPerformed

        
        PesquisaDespesa(false);
        
    }//GEN-LAST:event_btnDESCActionPerformed

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
            java.util.logging.Logger.getLogger(TelaDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TelaDespesa().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btPesquisarDespesa;
    private javax.swing.JButton btnASC;
    private javax.swing.JButton btnDESC;
    private javax.swing.JToggleButton btnNovaDespesa;
    private javax.swing.JButton btnReceitas;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cbbTipo;
    private javax.swing.JLabel dateBar;
    private javax.swing.JLabel findByTitle;
    private javax.swing.JLabel findTextTitle;
    private javax.swing.JLabel invoiceDateTitle;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtConsultaDespesa;
    private javax.swing.JLabel pageTitle;
    private javax.swing.JButton startButton;
    private javax.swing.JTextField txtAnoReceita;
    private javax.swing.JTextField txtMesReceita;
    private javax.swing.JTextField txt_Pesquisa;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables

    public void receberID(String recebe) {

        txt_id.setText(recebe);
    }
}