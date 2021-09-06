/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAO.CategoriaDAO;
import DAO.DespesaDAO;
import DAO.ReceitaDAO;
import DAO.moduloConexao;
import Model.Categoria;
import Model.Data;
import Model.Despesa;
import Model.Receita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ValidacaoComum.Validacao;
import java.util.LinkedList;

/**
 *
 * @author Alan
 */
public class TelaDespesa_cadastrar extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst1 = null;
    PreparedStatement pst2 = null;
    PreparedStatement pst3 = null;
    PreparedStatement pst4 = null;
    PreparedStatement pst5 = null;
    PreparedStatement pst6 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;

    boolean FlagErroCadastroDespesa = true;

    /**
     * Creates new form TelaDespesa_cadastrar
     */
    public TelaDespesa_cadastrar() {
        initComponents();
        conexao = moduloConexao.conector();
        txtParcelas.setEnabled(false);
        txt_NumCartao.setEnabled(false);
        this.setLocationRelativeTo(null);
        txt_id.setVisible(false);
    }

    void Volta_TelaDespesa() {

        TelaDespesa TelaDespesa = null;

        if (TelaDespesa == null) {

            TelaDespesa = new TelaDespesa();

            TelaDespesa.setVisible(true);

            TelaDespesa.receberID(txt_id.getText());

        } else {

            TelaDespesa.setVisible(true);

            TelaDespesa.setState(TelaPrincipal.NORMAL);

            TelaDespesa.receberID(txt_id.getText());

        }

        this.dispose();

    }

    void Volta_TelaPrincipal() {

        TelaPrincipal Telaprin = null;

        if (Telaprin == null) {

            Telaprin = new TelaPrincipal();

            Telaprin.setVisible(true);

            Telaprin.receberID(txt_id.getText());

        } else {

            Telaprin.setVisible(true);

            Telaprin.setState(TelaPrincipal.NORMAL);

            Telaprin.receberID(txt_id.getText());

        }

        this.dispose();

    }
    
     void CarregaCategoria(){
        
        CategoriaDAO categoria = new CategoriaDAO();
            
        try {
           int id_aux = Integer.parseInt(txt_id.getText()); 
            
            LinkedList<Categoria> lista_categoria = categoria.CarregaTabela_categoria(20);
            
            
            for (Categoria cat : lista_categoria) {

                String Cat_aux =  cat.getCategoria_aux();
                cbb_categoria.addItem(Cat_aux);
            }

            lista_categoria.clear();
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Falha listar categoria");
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

        txtCategoria = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        rbCredito = new javax.swing.JRadioButton();
        rbDebito = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        txtParcelas = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        rbPago = new javax.swing.JRadioButton();
        rbNaoPago = new javax.swing.JRadioButton();
        txtValor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaDescricao = new javax.swing.JTextArea();
        txtDia = new javax.swing.JTextField();
        txtMes = new javax.swing.JTextField();
        txtAno = new javax.swing.JTextField();
        btn_CadastrarDespesa = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        rbDinheiro = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        txt_NumCartao = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        cbb_categoria = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(700, 500));
        setMinimumSize(new java.awt.Dimension(700, 500));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        txtCategoria.setBackground(new java.awt.Color(187, 210, 240));
        txtCategoria.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtCategoria);
        txtCategoria.setBounds(420, 50, 300, 27);

        jLabel8.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel8.setText("Forma de Pagamento: ");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(210, 250, 140, 27);

        rbCredito.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbCredito.setText("Crédito");
        rbCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCreditoActionPerformed(evt);
            }
        });
        getContentPane().add(rbCredito);
        rbCredito.setBounds(280, 270, 80, 27);

        rbDebito.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbDebito.setText("Débito");
        rbDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDebitoActionPerformed(evt);
            }
        });
        getContentPane().add(rbDebito);
        rbDebito.setBounds(210, 270, 70, 27);

        jLabel9.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel9.setText("Nº de Pacelas: ");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(360, 300, 110, 27);

        txtParcelas.setBackground(new java.awt.Color(187, 210, 240));
        txtParcelas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtParcelas);
        txtParcelas.setBounds(360, 320, 90, 27);

        jLabel1.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel1.setText("Valor:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 90, 50, 27);

        jLabel2.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel2.setText("Categoria: ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(370, 90, 80, 27);

        jLabel3.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel3.setText("Descrição: ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 140, 80, 27);

        jLabel4.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel4.setText("Data: (dd/mm/aaaa)");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(190, 90, 130, 27);

        jLabel5.setFont(new java.awt.Font("Noto Serif", 1, 24)); // NOI18N
        jLabel5.setText("/");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(240, 110, 10, 27);

        jLabel6.setFont(new java.awt.Font("Noto Serif", 1, 24)); // NOI18N
        jLabel6.setText("/");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(300, 110, 10, 27);

        jLabel7.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel7.setText("Status: ");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(30, 250, 60, 27);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/teste_icon_pedmeia1.png"))); // NOI18N
        getContentPane().add(jLabel13);
        jLabel13.setBounds(620, 280, 150, 120);

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
        jButton1.setBounds(30, 50, 150, 25);

        rbPago.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbPago.setText("Pago");
        rbPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPagoActionPerformed(evt);
            }
        });
        getContentPane().add(rbPago);
        rbPago.setBounds(30, 270, 60, 27);

        rbNaoPago.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbNaoPago.setText("Não pago");
        rbNaoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNaoPagoActionPerformed(evt);
            }
        });
        getContentPane().add(rbNaoPago);
        rbNaoPago.setBounds(90, 270, 110, 27);

        txtValor.setBackground(new java.awt.Color(187, 210, 240));
        txtValor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtValor);
        txtValor.setBounds(30, 110, 150, 27);

        txtAreaDescricao.setColumns(20);
        txtAreaDescricao.setRows(5);
        txtAreaDescricao.setPreferredSize(new java.awt.Dimension(400, 54));
        jScrollPane1.setViewportView(txtAreaDescricao);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 160, 740, 113);

        txtDia.setBackground(new java.awt.Color(187, 210, 240));
        txtDia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtDia);
        txtDia.setBounds(190, 110, 50, 27);

        txtMes.setBackground(new java.awt.Color(187, 210, 240));
        txtMes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtMes);
        txtMes.setBounds(250, 110, 50, 27);

        txtAno.setBackground(new java.awt.Color(187, 210, 240));
        txtAno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnoActionPerformed(evt);
            }
        });
        getContentPane().add(txtAno);
        txtAno.setBounds(310, 110, 50, 27);

        btn_CadastrarDespesa.setBackground(new java.awt.Color(105, 69, 219));
        btn_CadastrarDespesa.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_CadastrarDespesa.setForeground(new java.awt.Color(255, 255, 255));
        btn_CadastrarDespesa.setText("Cadastrar Despesa");
        btn_CadastrarDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CadastrarDespesaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_CadastrarDespesa);
        btn_CadastrarDespesa.setBounds(30, 370, 200, 25);

        jLabel10.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        jLabel10.setText("CADASTRO DE DESPESA");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(280, 0, 230, 24);

        rbDinheiro.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbDinheiro.setText("Dinheiro");
        rbDinheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDinheiroActionPerformed(evt);
            }
        });
        getContentPane().add(rbDinheiro);
        rbDinheiro.setBounds(360, 270, 90, 27);

        jLabel11.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel11.setText("Nº do Cartão: ");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(30, 300, 120, 27);

        txt_NumCartao.setBackground(new java.awt.Color(187, 210, 240));
        txt_NumCartao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_NumCartao);
        txt_NumCartao.setBounds(30, 320, 300, 27);

        txt_id.setEditable(false);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        getContentPane().add(txt_id);
        txt_id.setBounds(2322, 50, 81, 20);

        cbb_categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cbb_categoria);
        cbb_categoria.setBounds(370, 120, 90, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDebitoActionPerformed
        // TODO add your handling code here:

        if (rbDebito.isSelected()) {

            txt_NumCartao.setEnabled(true);

            rbDinheiro.setSelected(false);

            txtParcelas.setEnabled(false);

            rbCredito.setSelected(false);

            rbPago.setEnabled(false);

            rbNaoPago.setEnabled(false);

            rbPago.setSelected(true);

            rbNaoPago.setSelected(false);

            txt_NumCartao.setText("");
            txtParcelas.setText("");

        } else {

            txtParcelas.setEnabled(false);
            txt_NumCartao.setEnabled(false);

            rbPago.setSelected(false);

            rbNaoPago.setSelected(false);

            rbPago.setEnabled(true);

            rbNaoPago.setEnabled(true);

        }


    }//GEN-LAST:event_rbDebitoActionPerformed

    private void rbCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCreditoActionPerformed
        // TODO add your handling code here:

        if (rbCredito.isSelected()) {

            txt_NumCartao.setEnabled(true);

            rbDinheiro.setSelected(false);

            txtParcelas.setEnabled(true);

            rbDebito.setSelected(false);

            rbPago.setEnabled(false);

            rbNaoPago.setEnabled(false);

            rbPago.setSelected(false);

            rbNaoPago.setSelected(true);

            txt_NumCartao.setText("");
            txtParcelas.setText("");

        } else {

            txtParcelas.setEnabled(false);
            txt_NumCartao.setEnabled(false);

            rbPago.setSelected(false);

            rbNaoPago.setSelected(false);

            rbPago.setEnabled(true);

            rbNaoPago.setEnabled(true);

        }

    }//GEN-LAST:event_rbCreditoActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void rbPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPagoActionPerformed
        // TODO add your handling code here:
        if (rbPago.isSelected()) {

            rbNaoPago.setSelected(false);

        }

    }//GEN-LAST:event_rbPagoActionPerformed

    private void rbNaoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNaoPagoActionPerformed
        // TODO add your handling code here:

        if (rbNaoPago.isSelected()) {

            rbPago.setSelected(false);

        }

    }//GEN-LAST:event_rbNaoPagoActionPerformed

    private void btn_CadastrarDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CadastrarDespesaActionPerformed
        // TODO add your handling code here:

        if (txtValor.getText().isEmpty() || txtCategoria.getText().isEmpty()
                || txtDia.getText().isEmpty()
                || txtMes.getText().isEmpty() || txtAno.getText().isEmpty()
                || (rbPago.isSelected() == false && rbNaoPago.isSelected() == false)
                || (rbDebito.isSelected() == false && rbCredito.isSelected() == false && rbDinheiro.isSelected() == false)
                || ((rbDebito.isSelected() == true || rbCredito.isSelected() == true) && txt_NumCartao.getText().isEmpty())
                || (rbCredito.isSelected() && txtParcelas.getText().isEmpty())) {

            JOptionPane.showMessageDialog(null, "Todos campos são de preenchimento obrigatório!", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);

        } else {

            Validacao valida = new Validacao();

            if (!(valida.ehNum(txtDia.getText()) && valida.ehNum(txtMes.getText()) && valida.ehNum(txtAno.getText()) && valida.ehNum(txtValor.getText()))) {
                JOptionPane.showMessageDialog(null, "Valor inválido!");
                return;
            }
            if (!(rbDinheiro.isSelected())) {

                if (rbCredito.isSelected()) {

                    if (!(valida.ehNum(txtParcelas.getText()))) {
                        JOptionPane.showMessageDialog(null, "Valor inválido!");
                        return;
                    }
                }

                if (!(valida.ehNum(txt_NumCartao.getText()))) {
                    JOptionPane.showMessageDialog(null, "Valor inválido!");
                    return;
                }
            }

            Despesa despesa = new Despesa(
                    Integer.parseInt(txtDia.getText()),
                    Integer.parseInt(txtMes.getText()),
                    Integer.parseInt(txtAno.getText()),
                    Float.parseFloat(txtValor.getText()),
                    txtCategoria.getText(),
                    txtAreaDescricao.getText(),
                    Integer.parseInt(txt_id.getText())
            );

            Data data = new Data(Integer.parseInt(txtDia.getText()),
                    Integer.parseInt(txtMes.getText()),
                    Integer.parseInt(txtAno.getText()));

            if (rbDebito.isSelected()) {

                despesa.setF_pagamento("DÉBITO");

                despesa.setNum_cartao(Long.parseLong(txt_NumCartao.getText()));

            } else if (rbCredito.isSelected()) {

                despesa.setF_pagamento("CRÉDITO");

                despesa.setNum_cartao(Long.parseLong(txt_NumCartao.getText()));

                despesa.setNum_parcelas(Integer.parseInt((txtParcelas.getText())));

            } else {

                despesa.setF_pagamento("DINHEIRO");

            }

            if (rbPago.isSelected()) {

                despesa.setEstatus("PAGO");

            } else {

                despesa.setEstatus("NÃO PAGO");
            }

            DespesaDAO despesaDAO = new DespesaDAO();

            ReceitaDAO receitaDAO = new ReceitaDAO();

            Receita receita = new Receita(Integer.parseInt(txt_id.getText()),
                    Integer.parseInt(txtMes.getText()),
                    Integer.parseInt(txtAno.getText())
            );

            boolean cadastra = true;

            try {

                try {

                    if (despesaDAO.DespesaExiste(despesa)) {

                        JOptionPane.showMessageDialog(this, "Já existe uma despesa nesse dia!", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);

                        cadastra = false;

                    }

                    if (!(receitaDAO.ReceitaExiste(receita))) {

                        JOptionPane.showMessageDialog(this, "Não existe receita correspondente para essa despesa", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

                        cadastra = false;

                    }

                    if (despesa.getF_pagamento().equals("CRÉDITO")) {

                        if (!(despesaDAO.DespesaTemCartaoCredito(despesa.getNum_cartao(), despesa.getId_conta()))) {

                            JOptionPane.showMessageDialog(this, "Não existe cartão de credito registrado com este número", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

                            cadastra = false;

                        } else {

                            if (!(despesaDAO.DespesaCC_TemCredito(despesa.getNum_cartao(), despesa.getValor(), despesa.getId_conta()))) {

                                JOptionPane.showMessageDialog(this, "Cartão de Crédito informado não possui crédito disponível", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

                                cadastra = false;

                            }

                        }

                    }

                    if (despesa.getF_pagamento().equals("DÉBITO")) {

                        if (!(despesaDAO.DespesaTemCartaoDebito(despesa.getNum_cartao(), despesa.getId_conta()))) {

                            JOptionPane.showMessageDialog(this, "Não existe cartão de débito registrado com este número", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

                            cadastra = false;

                        } else {

                            if (!(despesaDAO.DespesaCD_TemSaldo(receita, despesa.getValor()))) {

                                JOptionPane.showMessageDialog(this, "Receita correspondente não possui Saldo", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

                                cadastra = false;

                            }

                        }

                    }

                } catch (SQLException ex) {

                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }

                if (!(data.verifica_dia())) {

                    JOptionPane.showMessageDialog(this, "Dia inválido!", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);

                    cadastra = false;

                }

                if (!(data.verifica_mes())) {

                    JOptionPane.showMessageDialog(this, "Mês inválido!", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);

                    cadastra = false;

                }

                if (!(data.verifica_ano())) {

                    JOptionPane.showMessageDialog(this, "Ano inválido!", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);

                    cadastra = false;

                }

                if (!(despesa.verifica_Categoria(txtCategoria.getText()))) {

                    JOptionPane.showMessageDialog(this, "Categoria inválida", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);

                    cadastra = false;

                }

                if (!(despesa.validaValor())) {

                    JOptionPane.showMessageDialog(this, "Valor inválido", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);

                    cadastra = false;

                }

                if (despesa.getF_pagamento().equals("CRÉDITO") || despesa.getF_pagamento().equals("DÉBITO")) {

                    if (!(despesa.verifica_num_cartao_despesa())) {

                        JOptionPane.showMessageDialog(this, "Número do cartão inválido", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);

                        cadastra = false;

                    }

                }

                if (despesa.getF_pagamento().equals("CRÉDITO")) {

                    if (!(despesa.validaNumParcelas())) {

                        JOptionPane.showMessageDialog(this, "Número de parcelas inválido", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);

                        cadastra = false;
                    }

                }

                if (cadastra) {

                    despesaDAO.CadastrarDespesa(despesa);

                    Volta_TelaDespesa();

                } else {

                    JOptionPane.showMessageDialog(this, "Dados Inválidos!!");
                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(this, "Erro no cadastro!", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            }

        }

    }//GEN-LAST:event_btn_CadastrarDespesaActionPerformed

    private void rbDinheiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDinheiroActionPerformed
        // TODO add your handling code here:

        if (rbDinheiro.isSelected()) {

            rbCredito.setSelected(false);
            rbDebito.setSelected(false);

            rbNaoPago.setEnabled(true);
            rbPago.setEnabled(true);

            rbNaoPago.setSelected(false);
            rbPago.setSelected(false);

            txt_NumCartao.setEnabled(false);
            txtParcelas.setEnabled(false);

            txt_NumCartao.setText("");
            txtParcelas.setText("");

        }
    }//GEN-LAST:event_rbDinheiroActionPerformed

    private void txtAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Volta_TelaPrincipal();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
        CarregaCategoria();
        
        
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
            java.util.logging.Logger.getLogger(TelaDespesa_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDespesa_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDespesa_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDespesa_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaDespesa_cadastrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_CadastrarDespesa;
    private javax.swing.JComboBox<String> cbb_categoria;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbCredito;
    private javax.swing.JRadioButton rbDebito;
    private javax.swing.JRadioButton rbDinheiro;
    private javax.swing.JRadioButton rbNaoPago;
    private javax.swing.JRadioButton rbPago;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextArea txtAreaDescricao;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtDia;
    private javax.swing.JTextField txtMes;
    private javax.swing.JTextField txtParcelas;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txt_NumCartao;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables

    public void receberID(String recebe) {

        txt_id.setText(recebe);

    }

}
