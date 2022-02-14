/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.ControlerCategoria;
import Controllers.ControlerDespesa;
import Controllers.ControlerReceita;
import Model.Categoria;
import Model.Despesa;
import Utilities.Validacao;
import com.mysql.cj.util.StringUtils;
import java.awt.HeadlessException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan
 */
public class TelaAtualizarDespesa extends javax.swing.JFrame {

    private Despesa despesa = null;
    String salvaF_pagamento = null;
    String salvaStatus = null;
    int salvaCodigoDespesa = -1;
    
    /**
     * Creates new form TelaAtualizarDespesa
     */
    public TelaAtualizarDespesa() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    void TelaDespesa() {

        TelaDespesa TelaDespesa = null;

        if (TelaDespesa == null) {

            TelaDespesa = new TelaDespesa();

            TelaDespesa.setVisible(true);

            TelaDespesa.receberID(Integer.toString(this.despesa.getId_conta()));

        } else {

            TelaDespesa.setVisible(true);

            TelaDespesa.setState(TelaPrincipal.NORMAL);

            TelaDespesa.receberID(Integer.toString(this.despesa.getId_conta()));

        }

        this.dispose();
    }
    
    void AtualizarDespesa() {
         
        try {

            if (StringUtils.isNullOrEmpty(txtDia.getText())
                    && StringUtils.isNullOrEmpty(txtMes.getText())
                    && StringUtils.isNullOrEmpty(txtAno.getText())
                    && StringUtils.isNullOrEmpty(txtValor.getText())
                    && StringUtils.isNullOrEmpty(salvaF_pagamento)
                    && StringUtils.isNullOrEmpty(txt_NumCartao.getText())
                    && StringUtils.isNullOrEmpty(txtParcelas.getText())
                    && StringUtils.isNullOrEmpty(salvaStatus)) 
            {
                JOptionPane.showMessageDialog(this, "Nenhum Campo pode ser vazio");
                return;
            }
            else 
            {
                if(Validacao.isNumeric(txtDia.getText()) && Validacao.isNumeric(txtMes.getText()) &&
                   Validacao.isNumeric(txtAno.getText()) && Validacao.isNumeric(txtValor.getText()))
                {
                    JOptionPane.showMessageDialog(this, "Campo com dados não númericos!");
                    return;
                }
                  
                String dataFormat = txtDia.getText() + txtMes.getText() + txtAno.getText();

                if (!Validacao.isDate(dataFormat)) {
                    JOptionPane.showMessageDialog(this, "Data Inválida", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                    return;
                }

//                if (salvaF_pagamento.equals("CRÉDITO")) {
//                    if (!(Validacao.isNumeric(txtParcelas.getText()))) {
//                        JOptionPane.showMessageDialog(this, "Número de parcelas inválido");
//                        return;
//                    }
//                }

                String categoria = cbb_categoria.getSelectedItem().toString().trim();

                Despesa despesa = null;

                switch (salvaF_pagamento) {

                    case "CRÉDITO":

                        despesa = new Despesa.DespesaBuild(this.despesa.getCod_despesa())
                                .Dia(Integer.parseInt(txtDia.getText().trim()))
                                .Mes(Integer.parseInt(txtMes.getText()))
                                .Ano(Integer.parseInt(txtAno.getText()))
                                .Valor(Float.parseFloat(txtValor.getText()))
                                .Categoria(categoria)
                                .FormaPagamento(salvaF_pagamento)
                                .NumeroCartao(Long.parseLong(txt_NumCartao.getText()))
                                .NumeroParcelas(Integer.parseInt(txtParcelas.getText()))
                                .Status(salvaStatus)
                                .Descricao(txtAreaDescricao.getText())
                                .build();
                        
                        if(Validacao.isNumeric(txtParcelas.getText()))
                        {
                            JOptionPane.showMessageDialog(this, "Valor do Nº Parcelas é inválido!");
                            return;
                        }
                            
                        break;

                    case "DÉBITO":
                        despesa = new Despesa.DespesaBuild(this.despesa.getCod_despesa())
                                .Dia(Integer.parseInt(txtDia.getText().trim()))
                                .Mes(Integer.parseInt(txtMes.getText()))
                                .Ano(Integer.parseInt(txtAno.getText()))
                                .Valor(Float.parseFloat(txtValor.getText()))
                                .Categoria(categoria)
                                .FormaPagamento(salvaF_pagamento)
                                .NumeroCartao(Long.parseLong(txt_NumCartao.getText()))
                                .Status(salvaStatus)
                                .Descricao(txtAreaDescricao.getText())
                                .build();
                        
                        break;

                    case "DINHEIRO":
                        despesa = new Despesa.DespesaBuild(this.despesa.getCod_despesa())
                                .Dia(Integer.parseInt(txtDia.getText().trim()))
                                .Mes(Integer.parseInt(txtMes.getText()))
                                .Ano(Integer.parseInt(txtAno.getText()))
                                .Valor(Float.parseFloat(txtValor.getText()))
                                .Categoria(categoria)
                                .FormaPagamento(salvaF_pagamento)
                                .Status(salvaStatus)
                                .Descricao(txtAreaDescricao.getText())
                                .build();
                        break;

                    //VER USO DE ALGUMA EXCEPTION OU NAO    
                    default:
                        JOptionPane.showMessageDialog(this, "Forma de Pagamento Inexistente ou Não Implementada");
                        return;
                }
                
                if (!(despesa.ValidarValorDespesa())) {

                     JOptionPane.showMessageDialog(this, "Valor inválido", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                     return;
                 }
                
                if (despesa.getF_pagamento().equals("CRÉDITO") || despesa.getF_pagamento().equals("DÉBITO")) {

                    if (Validacao.isNumeric(txt_NumCartao.getText())) {
                        JOptionPane.showMessageDialog(this, "Campo com dados não númericos!");
                        return;
                    }

                    if (!(despesa.ValidarNumCartao())) {

                        JOptionPane.showMessageDialog(this, "Número do cartão inválido", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                despesa.setId_conta(this.despesa.getId_conta());

                ControlerDespesa.AtualizarDespesa(despesa);

            }
            
        } catch (HeadlessException | NullPointerException | NumberFormatException e) {

            JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    void CarregaCategoria() {

        LinkedList<Categoria> lista_categoria = ControlerCategoria.GetListaCategoria(this.despesa.getId_conta());

        for (Categoria cat : lista_categoria) {

                String Cat_aux =  cat.getCategoriaTipo();
                cbb_categoria.addItem(Cat_aux);
        }

        lista_categoria.clear();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        valueTitle = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        instNumTitle = new javax.swing.JLabel();
        txtParcelas = new javax.swing.JTextField();
        cardNumTitle = new javax.swing.JLabel();
        txt_NumCartao = new javax.swing.JTextField();
        dateTitle = new javax.swing.JLabel();
        txtDia = new javax.swing.JTextField();
        dayBar = new javax.swing.JLabel();
        txtMes = new javax.swing.JTextField();
        monthBar = new javax.swing.JLabel();
        txtAno = new javax.swing.JTextField();
        statusTitle = new javax.swing.JLabel();
        rbPago = new javax.swing.JRadioButton();
        rbNaoPago = new javax.swing.JRadioButton();
        descriptionTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaDescricao = new javax.swing.JTextArea();
        payFormTitle = new javax.swing.JLabel();
        rbDebito = new javax.swing.JRadioButton();
        rbCredito = new javax.swing.JRadioButton();
        rbDinheiro = new javax.swing.JRadioButton();
        categoryTitle = new javax.swing.JLabel();
        cbb_categoria = new javax.swing.JComboBox<>();
        btn_update = new javax.swing.JButton();
        btnReceitas = new javax.swing.JButton();
        valueTitle1 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Atualizar Despesas");
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        valueTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        valueTitle.setText("Valor:");
        getContentPane().add(valueTitle);
        valueTitle.setBounds(30, 100, 41, 27);

        txtValor.setBackground(new java.awt.Color(187, 210, 240));
        txtValor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });
        getContentPane().add(txtValor);
        txtValor.setBounds(30, 120, 100, 27);

        instNumTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        instNumTitle.setText("Nº de Pacelas: ");
        getContentPane().add(instNumTitle);
        instNumTitle.setBounds(400, 100, 87, 27);

        txtParcelas.setBackground(new java.awt.Color(187, 210, 240));
        txtParcelas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtParcelas);
        txtParcelas.setBounds(400, 120, 100, 27);

        cardNumTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        cardNumTitle.setText("Nº do Cartão: ");
        getContentPane().add(cardNumTitle);
        cardNumTitle.setBounds(30, 160, 82, 27);

        txt_NumCartao.setBackground(new java.awt.Color(187, 210, 240));
        txt_NumCartao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_NumCartao);
        txt_NumCartao.setBounds(30, 180, 250, 27);

        dateTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        dateTitle.setText("Data: (dd/mm/aaaa)");
        getContentPane().add(dateTitle);
        dateTitle.setBounds(590, 160, 130, 27);

        txtDia.setBackground(new java.awt.Color(187, 210, 240));
        txtDia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtDia);
        txtDia.setBounds(590, 180, 50, 27);

        dayBar.setFont(new java.awt.Font("Noto Serif", 1, 24)); // NOI18N
        dayBar.setText("/");
        getContentPane().add(dayBar);
        dayBar.setBounds(640, 180, 10, 27);

        txtMes.setBackground(new java.awt.Color(187, 210, 240));
        txtMes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtMes);
        txtMes.setBounds(650, 180, 50, 27);

        monthBar.setFont(new java.awt.Font("Noto Serif", 1, 24)); // NOI18N
        monthBar.setText("/");
        getContentPane().add(monthBar);
        monthBar.setBounds(700, 180, 10, 27);

        txtAno.setBackground(new java.awt.Color(187, 210, 240));
        txtAno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtAno);
        txtAno.setBounds(710, 180, 50, 27);

        statusTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        statusTitle.setText("Status: ");
        getContentPane().add(statusTitle);
        statusTitle.setBounds(30, 370, 60, 27);

        rbPago.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbPago.setText("Pago");
        rbPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPagoActionPerformed(evt);
            }
        });
        getContentPane().add(rbPago);
        rbPago.setBounds(30, 390, 70, 27);

        rbNaoPago.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbNaoPago.setText("Não pago");
        rbNaoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNaoPagoActionPerformed(evt);
            }
        });
        getContentPane().add(rbNaoPago);
        rbNaoPago.setBounds(100, 390, 79, 27);

        descriptionTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        descriptionTitle.setText("Descrição: ");
        getContentPane().add(descriptionTitle);
        descriptionTitle.setBounds(30, 220, 65, 27);

        txtAreaDescricao.setBackground(new java.awt.Color(187, 210, 240));
        txtAreaDescricao.setColumns(20);
        txtAreaDescricao.setRows(5);
        txtAreaDescricao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jScrollPane1.setViewportView(txtAreaDescricao);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 240, 740, 120);

        payFormTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        payFormTitle.setText("Forma de Pagamento: ");
        getContentPane().add(payFormTitle);
        payFormTitle.setBounds(150, 100, 135, 27);

        rbDebito.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbDebito.setText("Débito");
        rbDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDebitoActionPerformed(evt);
            }
        });
        getContentPane().add(rbDebito);
        rbDebito.setBounds(150, 120, 62, 27);

        rbCredito.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbCredito.setText("Crédito");
        rbCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCreditoActionPerformed(evt);
            }
        });
        getContentPane().add(rbCredito);
        rbCredito.setBounds(210, 120, 70, 27);

        rbDinheiro.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbDinheiro.setText("Dinheiro");
        rbDinheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDinheiroActionPerformed(evt);
            }
        });
        getContentPane().add(rbDinheiro);
        rbDinheiro.setBounds(290, 120, 90, 27);

        categoryTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        categoryTitle.setText("Categoria: ");
        getContentPane().add(categoryTitle);
        categoryTitle.setBounds(300, 160, 65, 27);

        cbb_categoria.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        cbb_categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cbb_categoria);
        cbb_categoria.setBounds(300, 180, 270, 27);

        btn_update.setBackground(new java.awt.Color(105, 69, 219));
        btn_update.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_update.setForeground(new java.awt.Color(255, 255, 255));
        btn_update.setText("Atualizar");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_update);
        btn_update.setBounds(640, 520, 140, 27);

        btnReceitas.setBackground(new java.awt.Color(105, 69, 219));
        btnReceitas.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnReceitas.setForeground(new java.awt.Color(255, 255, 255));
        btnReceitas.setText("Voltar");
        btnReceitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceitasActionPerformed(evt);
            }
        });
        getContentPane().add(btnReceitas);
        btnReceitas.setBounds(30, 50, 111, 27);

        valueTitle1.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        valueTitle1.setText("Atualizar Despesa");
        getContentPane().add(valueTitle1);
        valueTitle1.setBounds(300, 0, 170, 30);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Back-2.png"))); // NOI18N
        background.setText("background");
        getContentPane().add(background);
        background.setBounds(0, 0, 1920, 1080);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorActionPerformed

    private void rbPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPagoActionPerformed
        // TODO add your handling code here:
        if (rbPago.isSelected()) {
            salvaStatus = "PAGO";
            rbNaoPago.setSelected(false);

        }
    }//GEN-LAST:event_rbPagoActionPerformed

    private void rbNaoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNaoPagoActionPerformed
        // TODO add your handling code here:

        if (rbNaoPago.isSelected()) {
            salvaStatus = "NÃO PAGO";
            rbPago.setSelected(false);

        }
    }//GEN-LAST:event_rbNaoPagoActionPerformed

    private void rbDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDebitoActionPerformed
        // TODO add your handling code here:

        if (rbDebito.isSelected()) {
            salvaF_pagamento = "DÉBITO";
            txtParcelas.setEnabled(false);
            txt_NumCartao.setEnabled(true);
            rbCredito.setSelected(false);
            rbDinheiro.setSelected(false);

            txtParcelas.setText("");
        }
    }//GEN-LAST:event_rbDebitoActionPerformed

    private void rbCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCreditoActionPerformed
        // TODO add your handling code here:

        if (rbCredito.isSelected()) {
            salvaF_pagamento = "CRÉDITO";
            txtParcelas.setEnabled(true);
            txt_NumCartao.setEnabled(true);
            rbDebito.setSelected(false);
            rbDinheiro.setSelected(false);

        }
    }//GEN-LAST:event_rbCreditoActionPerformed

    private void rbDinheiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDinheiroActionPerformed
        // TODO add your handling code here:

        if (rbDinheiro.isSelected()) {

            salvaF_pagamento = "DINHEIRO";
            rbCredito.setSelected(false);
            rbDebito.setSelected(false);
            txt_NumCartao.setEnabled(false);
            txtParcelas.setEnabled(false);
            
            txt_NumCartao.setText("");
            txtParcelas.setText("");
        }
    }//GEN-LAST:event_rbDinheiroActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:

       AtualizarDespesa();
       TelaDespesa();

    }//GEN-LAST:event_btn_updateActionPerformed

    private void btnReceitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceitasActionPerformed
        // TODO add your handling code here:
        TelaDespesa();
    }//GEN-LAST:event_btnReceitasActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        CarregaCategoria();
        
        try {

            txtDia.setText(Integer.toString(despesa.getDia()));
            txtMes.setText(Integer.toString(despesa.getMes()));
            txtAno.setText(Integer.toString(despesa.getAno()));
            //txtCategoria.setText(categoria);
            cbb_categoria.setSelectedItem(despesa.getCategoria());
            txtValor.setText(Float.toString(despesa.getValor()));

            if (despesa.getNum_cartao() != null) {
                txt_NumCartao.setText(Long.toString(despesa.getNum_cartao()));
            }

            if (despesa.getNum_parcelas() > 0) {
                txtParcelas.setText(Integer.toString(despesa.getNum_parcelas()));
            }

            if (StringUtils.isNullOrEmpty(despesa.getDescricao())) {
                txtAreaDescricao.setText(despesa.getDescricao());
            }

            switch (despesa.getF_pagamento()) {
                case "CRÉDITO":
                    salvaF_pagamento = "CRÉDITO";
                    rbCredito.setSelected(true);
                    rbDebito.setSelected(false);
                    rbDinheiro.setSelected(false);
                    break;

                case "DÉBITO":
                    salvaF_pagamento = "DÉBITO";
                    rbDebito.setSelected(true);
                    rbCredito.setSelected(false);
                    rbDinheiro.setSelected(false);

                    txtParcelas.setEnabled(false);
                    break;

                case "DINHEIRO":
                    salvaF_pagamento = "DINHEIRO";
                    rbDinheiro.setSelected(true);
                    rbDebito.setSelected(false);
                    rbCredito.setSelected(false);

                    txt_NumCartao.setEnabled(false);
                    txtParcelas.setEnabled(false);
                    break;

                default:
                    JOptionPane.showMessageDialog(this, "Forma de Pagamento Desconhecida ou Inválida", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                    return;
            }

            if (despesa.getEstatus().equals("PAGO")) {
                salvaStatus = "PAGO";
                rbPago.setSelected(true);
                rbNaoPago.setSelected(false);
            } else {
                salvaStatus = "NÃO PAGO";
                rbNaoPago.setSelected(true);
                rbPago.setSelected(false);
            }

        } catch (NullPointerException | NumberFormatException e) {

            JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
        }

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
            java.util.logging.Logger.getLogger(TelaAtualizarDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAtualizarDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAtualizarDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAtualizarDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TelaAtualizarDespesa().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btnReceitas;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel cardNumTitle;
    private javax.swing.JLabel categoryTitle;
    private javax.swing.JComboBox<String> cbb_categoria;
    private javax.swing.JLabel dateTitle;
    private javax.swing.JLabel dayBar;
    private javax.swing.JLabel descriptionTitle;
    private javax.swing.JLabel instNumTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel monthBar;
    private javax.swing.JLabel payFormTitle;
    private javax.swing.JRadioButton rbCredito;
    private javax.swing.JRadioButton rbDebito;
    private javax.swing.JRadioButton rbDinheiro;
    private javax.swing.JRadioButton rbNaoPago;
    private javax.swing.JRadioButton rbPago;
    private javax.swing.JLabel statusTitle;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextArea txtAreaDescricao;
    private javax.swing.JTextField txtDia;
    private javax.swing.JTextField txtMes;
    private javax.swing.JTextField txtParcelas;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txt_NumCartao;
    private javax.swing.JLabel valueTitle;
    private javax.swing.JLabel valueTitle1;
    // End of variables declaration//GEN-END:variables


public void receberDespesa(Despesa despesa) {
    
   this.despesa = despesa;
}

}