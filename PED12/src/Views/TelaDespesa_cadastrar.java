package Views;

import Controllers.ControlerCategoria;
import Controllers.ControlerDespesa;
import Controllers.ControlerReceita;
import Model.Categoria;
import Model.Despesa;
import Utilities.Validacao;
import com.mysql.cj.util.StringUtils;
import javax.swing.JOptionPane;
import java.util.LinkedList;

/**
 *
 * @author Alan
 */
public class TelaDespesa_cadastrar extends javax.swing.JFrame {


    ControlerReceita controlerReceita= null;

    boolean FlagErroCadastroDespesa = true;

    /**
     * Creates new form TelaDespesa_cadastrar
     */
    public TelaDespesa_cadastrar() {
        initComponents();
        txtParcelas.setEnabled(false);
        txt_NumCartao.setEnabled(false);
        this.setLocationRelativeTo(null);
        txt_id.setVisible(false);
        cbb_categoria.removeAllItems();
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
            
        try {
           int id_aux = Integer.parseInt(txt_id.getText()); 
            
            LinkedList<Categoria> lista_categoria = ControlerCategoria.GetListaCategoria(id_aux);
            
            for (Categoria cat : lista_categoria) {

                String Cat_aux =  cat.getCategoria_aux();
                cbb_categoria.addItem(Cat_aux);
            }

            lista_categoria.clear();
            
        } catch (NumberFormatException e) {
            
            JOptionPane.showMessageDialog(null, "Falha listar categoria:" + e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
    }
     
     void CadastrarDespesa(){
         String categoria = cbb_categoria.getSelectedItem().toString().trim();
        
        if (txtValor.getText().isEmpty() 
                || txtDia.getText().isEmpty()
                || txtMes.getText().isEmpty() 
                || txtAno.getText().isEmpty()
                || (rbPago.isSelected() == false && rbNaoPago.isSelected() == false)
                || (rbDebito.isSelected() == false && rbCredito.isSelected() == false && rbDinheiro.isSelected() == false)
                || ((rbDebito.isSelected() == true || rbCredito.isSelected() == true) && StringUtils.isNullOrEmpty(txt_NumCartao.getText()))
                || (rbCredito.isSelected() && StringUtils.isNullOrEmpty(txtParcelas.getText()))) 
        {

            JOptionPane.showMessageDialog(null, "Todos campos são de preenchimento obrigatório!", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);

        } else {

            if (!(Validacao.isNumeric(txtDia.getText()) &&
                  Validacao.isNumeric(txtMes.getText()) && 
                  Validacao.isNumeric(txtAno.getText()) && 
                  Validacao.isNumeric(txtValor.getText()))) 
            {
                JOptionPane.showMessageDialog(this, "Somente dados númericos!");
                return;
            }
            if (!(rbDinheiro.isSelected())) {

                if (rbCredito.isSelected()) {

                    if (!(Validacao.isNumeric(txtParcelas.getText()))) {
                        JOptionPane.showMessageDialog(this, "Quantidade Parcelas Inválida!", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                if (!(Validacao.isNumeric(txt_NumCartao.getText()))) {
                    JOptionPane.showMessageDialog(this, "Número do Cartão Inválido!", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            Despesa despesa = new Despesa(
                    Integer.parseInt(txtDia.getText()),
                    Integer.parseInt(txtMes.getText()),
                    Integer.parseInt(txtAno.getText()),
                    Float.parseFloat(txtValor.getText()),
                    categoria,
                    txtAreaDescricao.getText(),
                    Integer.parseInt(txt_id.getText())
            );

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

            boolean cadastra = true;

            String dataFormat = txtDia.getText() + txtMes.getText() + txtAno.getText();
            
            if(!Validacao.isDate(dataFormat))
            {
                JOptionPane.showMessageDialog(this, "Data Inválida", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
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

                ControlerDespesa.CadastrarDespesa(despesa);
                Volta_TelaDespesa();

            } else {

                JOptionPane.showMessageDialog(this, "Dados Inválidos!!", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaDescricao = new javax.swing.JTextArea();
        cbb_categoria = new javax.swing.JComboBox<String>();
        txtParcelas = new javax.swing.JTextField();
        txtValor = new javax.swing.JTextField();
        txtDia = new javax.swing.JTextField();
        txtMes = new javax.swing.JTextField();
        txtAno = new javax.swing.JTextField();
        txt_NumCartao = new javax.swing.JTextField();
        rbCredito = new javax.swing.JRadioButton();
        rbDebito = new javax.swing.JRadioButton();
        rbDinheiro = new javax.swing.JRadioButton();
        rbPago = new javax.swing.JRadioButton();
        rbNaoPago = new javax.swing.JRadioButton();
        startButton = new javax.swing.JButton();
        btn_CadastrarDespesa = new javax.swing.JButton();
        valueTitle = new javax.swing.JLabel();
        catSelectTitle = new javax.swing.JLabel();
        descriptionTitle = new javax.swing.JLabel();
        dateTitle = new javax.swing.JLabel();
        dayBarTitle = new javax.swing.JLabel();
        monthBarTitle = new javax.swing.JLabel();
        statusTitle = new javax.swing.JLabel();
        payFormTitle = new javax.swing.JLabel();
        invoiceTitle = new javax.swing.JLabel();
        cardNumTitle = new javax.swing.JLabel();
        iconPed12 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(700, 500));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        pageTitle.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        pageTitle.setText("CADASTRO DE DESPESA");
        getContentPane().add(pageTitle);
        pageTitle.setBounds(280, 0, 230, 24);

        txtAreaDescricao.setColumns(20);
        txtAreaDescricao.setRows(5);
        txtAreaDescricao.setPreferredSize(new java.awt.Dimension(400, 54));
        jScrollPane1.setViewportView(txtAreaDescricao);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 160, 740, 113);

        cbb_categoria.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        cbb_categoria.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cbb_categoria);
        cbb_categoria.setBounds(420, 110, 130, 27);

        txtParcelas.setBackground(new java.awt.Color(187, 210, 240));
        txtParcelas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtParcelas);
        txtParcelas.setBounds(640, 300, 130, 27);

        txtValor.setBackground(new java.awt.Color(187, 210, 240));
        txtValor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtValor);
        txtValor.setBounds(30, 110, 180, 27);

        txtDia.setBackground(new java.awt.Color(187, 210, 240));
        txtDia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtDia);
        txtDia.setBounds(230, 110, 50, 27);

        txtMes.setBackground(new java.awt.Color(187, 210, 240));
        txtMes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtMes);
        txtMes.setBounds(290, 110, 50, 27);

        txtAno.setBackground(new java.awt.Color(187, 210, 240));
        txtAno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnoActionPerformed(evt);
            }
        });
        getContentPane().add(txtAno);
        txtAno.setBounds(350, 110, 50, 27);

        txt_NumCartao.setBackground(new java.awt.Color(187, 210, 240));
        txt_NumCartao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_NumCartao);
        txt_NumCartao.setBounds(300, 300, 300, 27);

        rbCredito.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbCredito.setText("Crédito");
        rbCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCreditoActionPerformed(evt);
            }
        });
        getContentPane().add(rbCredito);
        rbCredito.setBounds(100, 300, 80, 27);

        rbDebito.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbDebito.setText("Débito");
        rbDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDebitoActionPerformed(evt);
            }
        });
        getContentPane().add(rbDebito);
        rbDebito.setBounds(30, 300, 70, 27);

        rbDinheiro.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbDinheiro.setText("Dinheiro");
        rbDinheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDinheiroActionPerformed(evt);
            }
        });
        getContentPane().add(rbDinheiro);
        rbDinheiro.setBounds(180, 300, 90, 27);

        rbPago.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbPago.setText("Pago");
        rbPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPagoActionPerformed(evt);
            }
        });
        getContentPane().add(rbPago);
        rbPago.setBounds(570, 110, 80, 27);

        rbNaoPago.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbNaoPago.setText("Não pago");
        rbNaoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNaoPagoActionPerformed(evt);
            }
        });
        getContentPane().add(rbNaoPago);
        rbNaoPago.setBounds(650, 110, 120, 27);

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
        startButton.setBounds(30, 50, 150, 27);

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
        btn_CadastrarDespesa.setBounds(30, 350, 200, 27);

        valueTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        valueTitle.setText("Valor:");
        getContentPane().add(valueTitle);
        valueTitle.setBounds(30, 90, 50, 27);

        catSelectTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        catSelectTitle.setText("Categoria: ");
        getContentPane().add(catSelectTitle);
        catSelectTitle.setBounds(420, 90, 80, 27);

        descriptionTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        descriptionTitle.setText("Descrição: ");
        getContentPane().add(descriptionTitle);
        descriptionTitle.setBounds(30, 140, 80, 27);

        dateTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        dateTitle.setText("Data: (dd/mm/aaaa)");
        getContentPane().add(dateTitle);
        dateTitle.setBounds(230, 90, 130, 27);

        dayBarTitle.setFont(new java.awt.Font("Noto Serif", 1, 24)); // NOI18N
        dayBarTitle.setText("/");
        getContentPane().add(dayBarTitle);
        dayBarTitle.setBounds(280, 110, 10, 27);

        monthBarTitle.setFont(new java.awt.Font("Noto Serif", 1, 24)); // NOI18N
        monthBarTitle.setText("/");
        getContentPane().add(monthBarTitle);
        monthBarTitle.setBounds(340, 110, 10, 27);

        statusTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        statusTitle.setText("Status: ");
        getContentPane().add(statusTitle);
        statusTitle.setBounds(570, 90, 60, 27);

        payFormTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        payFormTitle.setText("Forma de Pagamento: ");
        getContentPane().add(payFormTitle);
        payFormTitle.setBounds(30, 270, 140, 27);

        invoiceTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        invoiceTitle.setText("Nº de Pacelas: ");
        getContentPane().add(invoiceTitle);
        invoiceTitle.setBounds(640, 270, 110, 27);

        cardNumTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        cardNumTitle.setText("Nº do Cartão: ");
        getContentPane().add(cardNumTitle);
        cardNumTitle.setBounds(300, 270, 120, 27);

        iconPed12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icon-140x100.png"))); // NOI18N
        getContentPane().add(iconPed12);
        iconPed12.setBounds(620, 370, 150, 120);

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
        txt_id.setBounds(2322, 50, 81, 20);

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
        CadastrarDespesa();
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

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // TODO add your handling code here:
        Volta_TelaPrincipal();
    }//GEN-LAST:event_startButtonActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> {
            new TelaDespesa_cadastrar().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btn_CadastrarDespesa;
    private javax.swing.JLabel cardNumTitle;
    private javax.swing.JLabel catSelectTitle;
    private javax.swing.JComboBox<String> cbb_categoria;
    private javax.swing.JLabel dateTitle;
    private javax.swing.JLabel dayBarTitle;
    private javax.swing.JLabel descriptionTitle;
    private javax.swing.JLabel iconPed12;
    private javax.swing.JLabel invoiceTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel monthBarTitle;
    private javax.swing.JLabel pageTitle;
    private javax.swing.JLabel payFormTitle;
    private javax.swing.JRadioButton rbCredito;
    private javax.swing.JRadioButton rbDebito;
    private javax.swing.JRadioButton rbDinheiro;
    private javax.swing.JRadioButton rbNaoPago;
    private javax.swing.JRadioButton rbPago;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel statusTitle;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextArea txtAreaDescricao;
    private javax.swing.JTextField txtDia;
    private javax.swing.JTextField txtMes;
    private javax.swing.JTextField txtParcelas;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txt_NumCartao;
    private javax.swing.JTextField txt_id;
    private javax.swing.JLabel valueTitle;
    // End of variables declaration//GEN-END:variables

    public void receberID(String recebe) {

        txt_id.setText(recebe);
    }
}