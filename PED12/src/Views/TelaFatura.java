/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import static Controllers.ControlerTabela.LimpaTabela;
import DAO.CartaoCreditoDAO;
import DAO.CategoriaDAO;
import DAO.DespesaDAO;
import Model.CartaoCredito;
import Model.Despesa;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alan
 */
public class TelaFatura extends javax.swing.JFrame {

    /**
     * Creates new form TelaFatura
     */
    public TelaFatura() {
        
        initComponents();
        this.setLocationRelativeTo(null);
        
    }
    
    
    void cartao_credito() {

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
    

     void RecarregaTabelaFaturas() {

        boolean salvaLinhaAtiva = false;
        
        float totalFatura = 0;
        
        DefaultTableModel mp1 = (DefaultTableModel) jtFaturas.getModel();
        
        LimpaTabela(jtFaturas);

        try {

            DespesaDAO despesaDAO = new DespesaDAO();
            CategoriaDAO categoriaDAO = new CategoriaDAO();

            DefaultTableModel mp = (DefaultTableModel) jtFaturas.getModel();

            LinkedList<Despesa> listaDespesasFatura = despesaDAO.GetListaDespesaFatura(Long.parseLong(txtNumCartao.getText()), Integer.parseInt(txt_id.getText()));

            for (Despesa despesa : listaDespesasFatura) {

                String Col0 = Integer.toString(despesa.getDia());
                String Col1 = Integer.toString(despesa.getMes());
                String Col2 = Integer.toString(despesa.getAno());
                String Col3 = Float.toString(despesa.getValor());
                String Col4 = despesa.getEstatus();
                String Col5 = categoriaDAO.GetTipoCategoria(despesa.getId_categoria(), Integer.parseInt(txt_id.getText()));
                String Col6 = Integer.toString(despesa.getNum_parcelas());
                String Col7 = despesa.getDescricao();
                String Col8 = Integer.toString(despesa.getNum_parcelas_pagas());
                String Col9 = Float.toString(despesa.getValor_parcela());
                
                totalFatura += despesa.getValor_parcela();
                
                mp.addRow(new String[]{Col0, Col1, Col2, Col3, Col4, Col5, Col6, Col7, Col8, Col9});

            }

            listaDespesasFatura.clear();
            
            txtTotalFatura.setText(Float.toString(totalFatura));

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro ao recarregar tabela", JOptionPane.ERROR_MESSAGE);
        }
        

    }
     
    void CarregaInfoCartao(){
        
        try {
            
            CartaoCreditoDAO cartaoDAO = new CartaoCreditoDAO();

            CartaoCredito cartao = cartaoDAO.GetCartaoCredito(Long.parseLong(txtNumCartao.getText()), Integer.parseInt(txt_id.getText()));

            txtDiaFatura.setText(Integer.toString(cartao.getDia_fatura()));
            
            txtBandeira.setText(cartao.getBandeira());
            
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro ao carregar Cartão Crédito", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    void ConsultaFatura(boolean ordena) {
        
        String tipo = "";

        String escolha = selectorType.getSelectedItem().toString().trim();

        if (escolha.equals("Dia")) {
            tipo = " " + "dia";
        }

        if (escolha.equals("Mês")) {
            tipo = " " + "mes";
        }

        if (escolha.equals("Ano")) {
            tipo = " " + "ano";
        }

        if (escolha.equals("Valor Total")) {
            tipo = " " + "valor";
        }

        if (escolha.equals("Estatus")) {
            tipo = " " + "estatus";
        }

        if (escolha.equals("Categoria")) {

            tipo = " " + "categoriaTipo";
        }

        if (escolha.equals("Parcelas")) {

            tipo = " " + "n_parcelas";
        }

        if (escolha.equals("Descrição")) {

            tipo = " " + "descricao";
        }

        if (escolha.equals("Parcelas Pagas")) {

            tipo = " " + "n_parcelas_pagas";
        }

        if (escolha.equals("Valor Parcela")) {

            tipo = " " + "valor_parcela";
        }

        String argumento = findField.getText();

        LimpaTabela(jtFaturas);

        try {

            DespesaDAO despesaDAO = new DespesaDAO();
            CategoriaDAO categoriaDAO = new CategoriaDAO();

            DefaultTableModel mp = (DefaultTableModel) jtFaturas.getModel();

            LinkedList<Despesa> listaDespesaFaturas = despesaDAO.ConsultaDespesaFatura(tipo, argumento, Integer.parseInt(txt_id.getText()), ordena);

            for (Despesa despesa : listaDespesaFaturas) {

                String Col0 = Integer.toString(despesa.getDia());
                String Col1 = Integer.toString(despesa.getMes());
                String Col2 = Integer.toString(despesa.getAno());
                String Col3 = Float.toString(despesa.getValor());
                String Col4 = despesa.getEstatus();
                String Col5 = despesa.getCategoria();
                String Col6 = Integer.toString(despesa.getNum_parcelas());
                String Col7 = despesa.getDescricao();
                String Col8 = Integer.toString(despesa.getNum_parcelas_pagas());
                String Col9 = Float.toString(despesa.getValor_parcela());

                mp.addRow(new String[]{Col0, Col1, Col2, Col3, Col4, Col5, Col6, Col7, Col8, Col9});

            }
            listaDespesaFaturas.clear();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
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

        PageTitle = new javax.swing.JLabel();
        dataTable = new javax.swing.JScrollPane();
        jtFaturas = new javax.swing.JTable();
        selectorType = new javax.swing.JComboBox<>();
        findField = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        findTitle = new javax.swing.JLabel();
        selectorTitle = new javax.swing.JLabel();
        orientationTitle = new javax.swing.JLabel();
        txtNumCartao = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDiaFatura = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTotalFatura = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtBandeira = new javax.swing.JTextField();
        btn_ASC = new javax.swing.JButton();
        btn_DESC = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
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

        PageTitle.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        PageTitle.setText("Faturas");
        getContentPane().add(PageTitle);
        PageTitle.setBounds(370, 0, 76, 27);

        jtFaturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia", "Mês", "Ano", "Valor Total", "Estatus", "Categoria", "Parcelas", "Descrição", "Parcelas Pagas", "Valor Parcela"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dataTable.setViewportView(jtFaturas);

        getContentPane().add(dataTable);
        dataTable.setBounds(30, 220, 750, 232);

        selectorType.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        selectorType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dia", "Mês", "Ano", "Valor Total", "Estatus", "Categoria", "Parcelas", "Descrição", "Parcelas Pagas", "Valor Parcela", " ", " " }));
        getContentPane().add(selectorType);
        selectorType.setBounds(30, 180, 150, 27);

        findField.setColumns(20);
        findField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findFieldActionPerformed(evt);
            }
        });
        findField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                findFieldKeyReleased(evt);
            }
        });
        getContentPane().add(findField);
        findField.setBounds(30, 480, 330, 27);

        backButton.setBackground(new java.awt.Color(105, 69, 219));
        backButton.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("Voltar");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton);
        backButton.setBounds(30, 50, 100, 27);

        searchButton.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        searchButton.setForeground(new java.awt.Color(255, 255, 255));
        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/lupa.png"))); // NOI18N
        searchButton.setBorderPainted(false);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        getContentPane().add(searchButton);
        searchButton.setBounds(370, 470, 40, 40);

        findTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        findTitle.setText("Refinar consulta por:");
        getContentPane().add(findTitle);
        findTitle.setBounds(30, 460, 160, 27);

        selectorTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        selectorTitle.setText("Ordenar/Refinar por:");
        getContentPane().add(selectorTitle);
        selectorTitle.setBounds(30, 160, 140, 27);

        orientationTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        orientationTitle.setText("Tipo de Ordenação");
        getContentPane().add(orientationTitle);
        orientationTitle.setBounds(200, 160, 120, 27);

        txtNumCartao.setBackground(new java.awt.Color(187, 210, 240));
        txtNumCartao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtNumCartao);
        txtNumCartao.setBounds(30, 110, 190, 27);

        jLabel1.setText("Nº Cartão");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 90, 70, 27);

        jLabel2.setText("Dia da Fatura");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(280, 90, 100, 27);

        txtDiaFatura.setBackground(new java.awt.Color(187, 210, 240));
        txtDiaFatura.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtDiaFatura);
        txtDiaFatura.setBounds(280, 110, 80, 27);

        jLabel3.setText("Total Fatura");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(410, 90, 100, 27);

        txtTotalFatura.setBackground(new java.awt.Color(187, 210, 240));
        txtTotalFatura.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtTotalFatura);
        txtTotalFatura.setBounds(410, 110, 130, 27);

        jLabel4.setText("Bandeira");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(590, 90, 100, 27);

        txtBandeira.setBackground(new java.awt.Color(187, 210, 240));
        txtBandeira.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtBandeira);
        txtBandeira.setBounds(590, 110, 180, 27);

        btn_ASC.setBackground(new java.awt.Color(105, 69, 219));
        btn_ASC.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_ASC.setForeground(new java.awt.Color(255, 255, 255));
        btn_ASC.setText("Ascendente");
        btn_ASC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ASCActionPerformed(evt);
            }
        });
        getContentPane().add(btn_ASC);
        btn_ASC.setBounds(200, 180, 110, 27);

        btn_DESC.setBackground(new java.awt.Color(105, 69, 219));
        btn_DESC.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_DESC.setForeground(new java.awt.Color(255, 255, 255));
        btn_DESC.setText("Descendente");
        btn_DESC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DESCActionPerformed(evt);
            }
        });
        getContentPane().add(btn_DESC);
        btn_DESC.setBounds(320, 180, 112, 27);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Back-2.png"))); // NOI18N
        jLabel5.setText("background");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 1920, 1080);

        txt_id.setEditable(false);
        txt_id.setBackground(new java.awt.Color(150, 175, 231));
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        getContentPane().add(txt_id);
        txt_id.setBounds(0, 0, 60, 21);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void findFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_findFieldActionPerformed

    private void findFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_findFieldKeyReleased
        // TODO add your handling code here

        if (findField.getText().isEmpty()) {
            RecarregaTabelaFaturas();
        }
    }//GEN-LAST:event_findFieldKeyReleased

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        cartao_credito();
    }//GEN-LAST:event_backButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:

            ConsultaFatura(true);
        
    }//GEN-LAST:event_searchButtonActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
         CarregaInfoCartao();
        
        RecarregaTabelaFaturas();
    }//GEN-LAST:event_formWindowOpened

    private void btn_ASCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ASCActionPerformed
        // TODO add your handling code here:
        ConsultaFatura(true);
    }//GEN-LAST:event_btn_ASCActionPerformed

    private void btn_DESCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DESCActionPerformed
        // TODO add your handling code here:
        ConsultaFatura(false);
    }//GEN-LAST:event_btn_DESCActionPerformed

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
            java.util.logging.Logger.getLogger(TelaFatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaFatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaFatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaFatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaFatura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PageTitle;
    private javax.swing.JButton backButton;
    private javax.swing.JButton btn_ASC;
    private javax.swing.JButton btn_DESC;
    private javax.swing.JScrollPane dataTable;
    private javax.swing.JTextField findField;
    private javax.swing.JLabel findTitle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTable jtFaturas;
    private javax.swing.JLabel orientationTitle;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel selectorTitle;
    private javax.swing.JComboBox<String> selectorType;
    private javax.swing.JTextField txtBandeira;
    private javax.swing.JTextField txtDiaFatura;
    private javax.swing.JTextField txtNumCartao;
    private javax.swing.JTextField txtTotalFatura;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables

  public void receberIdAndNumCartao(String numCartao, String id_conta) {

        txt_id.setText(id_conta);
        txtNumCartao.setText(numCartao);
    }

}
