/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.ControlerCategoria;
import Controllers.ControlerDespesa;
import Model.Categoria;
import Model.Despesa;
import ValidacaoComum.Validacao;
import com.mysql.cj.util.StringUtils;
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

        Despesa despesa_aux = new Despesa();

        if
        (
           StringUtils.isNullOrEmpty(txtDia.getText()) &&
           StringUtils.isNullOrEmpty(txtMes.getText()) &&
           StringUtils.isNullOrEmpty(txtAno.getText()) &&
           StringUtils.isNullOrEmpty(txtValor.getText()) &&   
           StringUtils.isNullOrEmpty(salvaF_pagamento) &&  
           StringUtils.isNullOrEmpty(txt_NumCartao.getText()) &&
           StringUtils.isNullOrEmpty(txtParcelas.getText()) &&
           StringUtils.isNullOrEmpty(salvaStatus)
        )
        {
           JOptionPane.showMessageDialog(this, "Nenhum Campo ser vazio");
           return;
        }
        

        if (!(despesa_aux.Update_CamposValidos(txtValor.getText(), txtDia.getText(), txtMes.getText(), txtAno.getText()))) {

            JOptionPane.showMessageDialog(this, "O valor informado é inválido");
            return;
        }

        if (salvaF_pagamento.equals("CRÉDITO")) {
            Validacao valida = new Validacao();
            if (!(valida.ehNum(txtParcelas.getText()))) {
                JOptionPane.showMessageDialog(this, "Número de parcelas inválido");
                return;
            }
        }
        
        String categoria = cbb_categoria.getSelectedItem().toString().trim();
        
        Despesa despesa = null;
        
        switch(salvaF_pagamento){
            
            case "CRÉDITO":
                despesa = new Despesa(
                        Integer.parseInt(txtDia.getText().trim()),
                        Integer.parseInt(txtMes.getText()),
                        Integer.parseInt(txtAno.getText()),
                        Float.parseFloat(txtValor.getText()),
                        categoria,
                        salvaF_pagamento,
                        Long.parseLong(txt_NumCartao.getText()),
                        Integer.parseInt(txtParcelas.getText()),
                        salvaStatus,
                        txtAreaDescricao.getText(),
                        this.despesa.getCod_despesa()
                );
                break;

            case "DÉBITO":
                despesa = new Despesa(
                        Integer.parseInt(txtDia.getText().trim()),
                        Integer.parseInt(txtMes.getText()),
                        Integer.parseInt(txtAno.getText()),
                        Float.parseFloat(txtValor.getText()),
                        categoria,
                        salvaF_pagamento,
                        Long.parseLong(txt_NumCartao.getText()),
                        salvaStatus,
                        txtAreaDescricao.getText(),
                        this.despesa.getCod_despesa()
                );
                break;

            case "DINHEIRO":
                despesa = new Despesa(
                        Integer.parseInt(txtDia.getText().trim()),
                        Integer.parseInt(txtMes.getText()),
                        Integer.parseInt(txtAno.getText()),
                        Float.parseFloat(txtValor.getText()),
                        categoria,
                        salvaF_pagamento,
                        salvaStatus,
                        txtAreaDescricao.getText(),
                        this.despesa.getCod_despesa()
                );
                break;
                
            //VER USO DE ALGUMA EXCEPTION OU NAO    
            default:
                JOptionPane.showMessageDialog(this, "Forma de Pagamento Inexistente ou Não Implementada"); 
                return;

        }

        if (despesa.getF_pagamento().equals("CRÉDITO") || despesa.getF_pagamento().equals("DÉBITO")) {

            if (!(despesa.verifica_num_cartao_despesa())) {

                JOptionPane.showMessageDialog(this, "Número do cartão inválido", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        
        despesa.setId_conta(this.despesa.getId_conta());
        
        ControlerDespesa.AtualizarDespesa(despesa);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        valueTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        valueTitle.setText("Valor:");

        txtValor.setBackground(new java.awt.Color(187, 210, 240));
        txtValor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });

        instNumTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        instNumTitle.setText("Nº de Pacelas: ");

        txtParcelas.setBackground(new java.awt.Color(187, 210, 240));

        cardNumTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        cardNumTitle.setText("Nº do Cartão: ");

        txt_NumCartao.setBackground(new java.awt.Color(187, 210, 240));
        txt_NumCartao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        dateTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        dateTitle.setText("Data: (dd/mm/aaaa)");

        txtDia.setBackground(new java.awt.Color(187, 210, 240));
        txtDia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        dayBar.setFont(new java.awt.Font("Noto Serif", 1, 24)); // NOI18N
        dayBar.setText("/");

        txtMes.setBackground(new java.awt.Color(187, 210, 240));
        txtMes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        monthBar.setFont(new java.awt.Font("Noto Serif", 1, 24)); // NOI18N
        monthBar.setText("/");

        txtAno.setBackground(new java.awt.Color(187, 210, 240));
        txtAno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        statusTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        statusTitle.setText("Status: ");

        rbPago.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbPago.setText("Pago");
        rbPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPagoActionPerformed(evt);
            }
        });

        rbNaoPago.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbNaoPago.setText("Não pago");
        rbNaoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNaoPagoActionPerformed(evt);
            }
        });

        descriptionTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        descriptionTitle.setText("Descrição: ");

        txtAreaDescricao.setBackground(new java.awt.Color(187, 210, 240));
        txtAreaDescricao.setColumns(20);
        txtAreaDescricao.setRows(5);
        txtAreaDescricao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jScrollPane1.setViewportView(txtAreaDescricao);

        payFormTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        payFormTitle.setText("Forma de Pagamento: ");

        rbDebito.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbDebito.setText("Débito");
        rbDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDebitoActionPerformed(evt);
            }
        });

        rbCredito.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbCredito.setText("Crédito");
        rbCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCreditoActionPerformed(evt);
            }
        });

        rbDinheiro.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbDinheiro.setText("Dinheiro");
        rbDinheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDinheiroActionPerformed(evt);
            }
        });

        categoryTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        categoryTitle.setText("Categoria: ");

        cbb_categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_update.setBackground(new java.awt.Color(105, 69, 219));
        btn_update.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_update.setForeground(new java.awt.Color(255, 255, 255));
        btn_update.setText("Atualizar");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btnReceitas.setBackground(new java.awt.Color(105, 69, 219));
        btnReceitas.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnReceitas.setForeground(new java.awt.Color(255, 255, 255));
        btnReceitas.setText("Voltar");
        btnReceitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceitasActionPerformed(evt);
            }
        });

        valueTitle1.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        valueTitle1.setText("Atualizar DESPESA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btnReceitas, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(302, 302, 302))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valueTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(instNumTitle))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cardNumTitle)
                            .addComponent(txt_NumCartao, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valueTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(dayBar, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(monthBar, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(statusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rbPago, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(rbNaoPago))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addComponent(rbCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(payFormTitle)
                                    .addComponent(rbDebito))
                                .addComponent(rbDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(categoryTitle)
                            .addComponent(cbb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descriptionTitle))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(valueTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valueTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(instNumTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardNumTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_NumCartao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dayBar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(monthBar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDia, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbPago, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rbNaoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(payFormTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rbDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rbDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(3, 3, 3)
                        .addComponent(categoryTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(cbb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(descriptionTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReceitas, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69))
        );

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
        
        txtDia.setText(Integer.toString(despesa.getDia()));
        txtMes.setText(Integer.toString(despesa.getMes()));
        txtAno.setText(Integer.toString(despesa.getAno()));
        //txtCategoria.setText(categoria);
        cbb_categoria.setSelectedItem(despesa.getCategoria());
        txtValor.setText(Float.toString(despesa.getValor()));
        
        if(despesa.getNum_cartao() != null)
            txt_NumCartao.setText(Long.toString(despesa.getNum_cartao()));
            
        if(despesa.getNum_parcelas() > 0)
            txtParcelas.setText(Integer.toString(despesa.getNum_parcelas()));
        
        if(StringUtils.isNullOrEmpty(despesa.getDescricao()))
            txtAreaDescricao.setText(despesa.getDescricao());
        
        switch (despesa.getF_pagamento()) 
        {
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
        
        if (despesa.getEstatus().equals("PAGO")) 
        {
            salvaStatus = "PAGO";
            rbPago.setSelected(true);
            rbNaoPago.setSelected(false);
        } 
        else 
        {
            salvaStatus = "NÃO PAGO";
            rbNaoPago.setSelected(true);
            rbPago.setSelected(false);
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