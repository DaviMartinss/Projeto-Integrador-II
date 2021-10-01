/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAO.CartaoDebitoDAO;
import DAO.moduloConexao;
import Model.CartaoDebito;
import ValidacaoComum.Validacao;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Controllers.ControlerTabela;
import java.sql.SQLException;
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

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_NumCartaoD = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_Bandeira = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_Valor = new javax.swing.JTextField();
        cbbTipo = new javax.swing.JComboBox<>();
        txt_Pesquisa = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rbAscendente = new javax.swing.JRadioButton();
        rbDescendente = new javax.swing.JRadioButton();
        btPesquisarCD = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtConsultaCD = new javax.swing.JTable();
        btn_update = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
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

        jButton1.setBackground(new java.awt.Color(105, 69, 219));
        jButton1.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Inicio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(20, 40, 100, 27);

        jButton2.setBackground(new java.awt.Color(105, 69, 219));
        jButton2.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Sair");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(680, 40, 100, 27);

        jButton4.setBackground(new java.awt.Color(105, 69, 219));
        jButton4.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Receitas");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(440, 40, 100, 27);

        jButton5.setBackground(new java.awt.Color(105, 69, 219));
        jButton5.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Usuário");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(560, 40, 100, 27);

        jButton3.setBackground(new java.awt.Color(105, 69, 219));
        jButton3.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Novo Cartao");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(630, 340, 140, 25);

        jLabel2.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        jLabel2.setText("Cartão de Débito");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(320, 0, 160, 24);

        jButton7.setBackground(new java.awt.Color(210, 59, 239));
        jButton7.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Excluir Cartão");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7);
        jButton7.setBounds(630, 440, 140, 25);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1920, 0);

        jLabel4.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel4.setText("Número do cartão");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 320, 140, 27);

        jLabel8.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel8.setText("Pesquisar por");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(20, 70, 100, 27);

        txt_NumCartaoD.setBackground(new java.awt.Color(187, 210, 240));
        txt_NumCartaoD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_NumCartaoD);
        txt_NumCartaoD.setBounds(20, 340, 400, 27);

        jLabel5.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel5.setText("Bandeira");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 420, 70, 27);

        txt_Bandeira.setBackground(new java.awt.Color(187, 210, 240));
        txt_Bandeira.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt_Bandeira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BandeiraActionPerformed(evt);
            }
        });
        getContentPane().add(txt_Bandeira);
        txt_Bandeira.setBounds(20, 440, 400, 27);

        jLabel3.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel3.setText("Valor");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 370, 100, 27);

        txt_Valor.setBackground(new java.awt.Color(187, 210, 240));
        txt_Valor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_Valor);
        txt_Valor.setBounds(20, 390, 400, 27);

        cbbTipo.setBackground(new java.awt.Color(187, 210, 240));
        cbbTipo.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        cbbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nº Cartão", "Valor", "Bandeira", " ", " ", " " }));
        getContentPane().add(cbbTipo);
        cbbTipo.setBounds(20, 90, 140, 27);

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

        jLabel6.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel6.setText("Ordenação");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(530, 70, 80, 27);

        rbAscendente.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbAscendente.setText("Ascendente");
        rbAscendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAscendenteActionPerformed(evt);
            }
        });
        getContentPane().add(rbAscendente);
        rbAscendente.setBounds(530, 90, 90, 27);

        rbDescendente.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbDescendente.setText("Descendente");
        rbDescendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDescendenteActionPerformed(evt);
            }
        });
        getContentPane().add(rbDescendente);
        rbDescendente.setBounds(620, 90, 110, 27);

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
        jScrollPane1.setViewportView(jtConsultaCD);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 140, 760, 170);

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
        btn_update.setBounds(630, 390, 140, 25);

        jLabel9.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel9.setText("Digite sua pesquisa aqui");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(170, 70, 170, 27);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fundo_principal.png"))); // NOI18N
        getContentPane().add(jLabel7);
        jLabel7.setBounds(0, 0, 1920, 1080);

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        sair();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        inicio();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        receita();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        cadastraCartao_Deb();
    }//GEN-LAST:event_jButton3ActionPerformed

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

    private void btPesquisarCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarCDActionPerformed
        // TODO add your handling code here:

        if (rbAscendente.isSelected() || rbDescendente.isSelected()) {

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

            if (rbAscendente.isSelected()) {

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

        CartaoDebitoDAO cartaoDAO = new CartaoDebitoDAO();

        try {

            LinkedList<CartaoDebito> lista_CD = cartaoDAO.PreencherCamposCartao_D(num_cartao,
                    Integer.parseInt(txt_id.getText()));

            txt_NumCartaoD.setText(Long.toString(lista_CD.element().getN_cartao_debito()));
            txt_Valor.setText(Float.toString(lista_CD.element().getValor_atual()));
            txt_Bandeira.setText(lista_CD.element().getBandeira());
            salva_num_cartao_debito = lista_CD.element().getN_cartao_debito();

            int selLinha = -1;
            selLinha = jtConsultaCD.getSelectedRow();

            if (selLinha != -1) {
                salvaLinhaAtiva = true;
            }

            lista_CD.clear();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(this, "Erro ao selecionar os dados!!");
        }

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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        delete_cartao_debito();
        RecarregaTabela_CartaoDB();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        TelaUsuario();
    }//GEN-LAST:event_jButton5ActionPerformed

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
    private javax.swing.JButton btPesquisarCD;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cbbTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtConsultaCD;
    private javax.swing.JRadioButton rbAscendente;
    private javax.swing.JRadioButton rbDescendente;
    private javax.swing.JTextField txt_Bandeira;
    private javax.swing.JTextField txt_NumCartaoD;
    private javax.swing.JTextField txt_Pesquisa;
    private javax.swing.JTextField txt_Valor;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables

    public void receberID(String recebe) {

        txt_id.setText(recebe);
    }

}
