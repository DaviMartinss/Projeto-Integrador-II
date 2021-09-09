/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAO.CartaoCreditoDAO;
import DAO.UsuarioDAO;
import DAO.moduloConexao;
import Model.CartaoCredito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
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

//        conexao = moduloConexao.conector();
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
    
    void TelaCartaoCredito_ListaFaturas() {

        TelaCartaoCredito_ListaFaturas TelaCC_ListaFaturas = null;

        if (TelaCC_ListaFaturas == null) {

            TelaCC_ListaFaturas = new TelaCartaoCredito_ListaFaturas();

            TelaCC_ListaFaturas.setVisible(true);

            TelaCC_ListaFaturas.receberID(txt_id.getText());

        } else {

            TelaCC_ListaFaturas.setVisible(true);

            TelaCC_ListaFaturas.setState(TelaPrincipal.NORMAL);

            TelaCC_ListaFaturas.receberID(txt_id.getText());

        }

        this.dispose();
    }

    void RecarregaTabela_CartaoCC() {

        boolean salvaLinhaAtiva = false;
        DefaultTableModel mp1 = (DefaultTableModel) jtConsultaCC.getModel();

        int l = mp1.getRowCount();

        if (l > 0) {
            while (l > 0) {
                //Limpa tabela sempre que for fazer uma nova consulta
                ((DefaultTableModel) jtConsultaCC.getModel()).removeRow(l - 1);

                //Menos um pois a primeira linha é a linha zero
                l--;
            }
        }

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

        jLabel2 = new javax.swing.JLabel();
        btn_inicio_CC = new javax.swing.JButton();
        btn_receitaCC = new javax.swing.JButton();
        btn_sairCC = new javax.swing.JButton();
        btn_novoCartao_cc = new javax.swing.JButton();
        btn_exclui_cc = new javax.swing.JButton();
        txt_Pesquisa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtConsultaCC = new javax.swing.JTable();
        cbbTipo = new javax.swing.JComboBox<>();
        btPesquisarCC = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_DiaFatura = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_ValorFatura = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_Bandeira = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_NumCartaoC = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_Limite = new javax.swing.JTextField();
        rbDescendente = new javax.swing.JRadioButton();
        rbAscendente = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        btn_update = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
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

        jLabel2.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        jLabel2.setText("Cartão de Crédito");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(330, 0, 170, 26);

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
        btn_exclui_cc.setBounds(630, 410, 140, 27);

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
        jScrollPane1.setViewportView(jtConsultaCC);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 150, 740, 110);

        cbbTipo.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        cbbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nº Cartão", "Limite", "Dia da Fatura", "Valor da Fatura", "Bandeira", " ", " ", " " }));
        getContentPane().add(cbbTipo);
        cbbTipo.setBounds(30, 100, 130, 27);

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

        jLabel9.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel9.setText("Pesquisar por");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(30, 80, 100, 27);

        jLabel1.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel1.setText("Número do cartão");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 290, 140, 20);

        txt_DiaFatura.setBackground(new java.awt.Color(187, 210, 240));
        txt_DiaFatura.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_DiaFatura);
        txt_DiaFatura.setBounds(310, 360, 50, 27);

        jLabel3.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel3.setText("Valor da Fatura");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(450, 290, 110, 20);

        txt_ValorFatura.setBackground(new java.awt.Color(187, 210, 240));
        txt_ValorFatura.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_ValorFatura);
        txt_ValorFatura.setBounds(450, 310, 150, 27);

        jLabel4.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel4.setText("Dia da Fatura");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(310, 340, 100, 20);

        txt_Bandeira.setBackground(new java.awt.Color(187, 210, 240));
        txt_Bandeira.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt_Bandeira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BandeiraActionPerformed(evt);
            }
        });
        getContentPane().add(txt_Bandeira);
        txt_Bandeira.setBounds(30, 360, 250, 27);

        jLabel5.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel5.setText("Bandeira");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 340, 80, 20);

        txt_NumCartaoC.setBackground(new java.awt.Color(187, 210, 240));
        txt_NumCartaoC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_NumCartaoC);
        txt_NumCartaoC.setBounds(30, 310, 400, 27);

        jLabel6.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel6.setText("Ordenação");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(520, 80, 80, 27);

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

        jLabel7.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel7.setText("Limite");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(450, 340, 50, 20);

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
        btn_update.setBounds(630, 360, 140, 27);

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
        jButton5.setBounds(310, 50, 100, 27);

        jLabel10.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel10.setText("Digite sua pesquisa aqui");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(170, 80, 160, 27);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fundo_principal.png"))); // NOI18N
        getContentPane().add(jLabel8);
        jLabel8.setBounds(0, 0, 1920, 1080);

        txt_id.setEditable(false);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        getContentPane().add(txt_id);
        txt_id.setBounds(30, 10, 81, 21);

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

            String escolha = cbbTipo.getSelectedItem().toString().trim();

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

            DefaultTableModel mp1 = (DefaultTableModel) jtConsultaCC.getModel();

            int l = mp1.getRowCount();

            if (l > 0) {
                while (l > 0) {
                    //Limpa tabela sempre que for fazer uma nova consulta
                    ((DefaultTableModel) jtConsultaCC.getModel()).removeRow(l - 1);

                    //Menos um pois a primeira linha é a linha zero
                    l--;
                }
            }

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

        CartaoCreditoDAO cartaoDAO = new CartaoCreditoDAO();

        ResultSet rs = null;

        try {

            LinkedList<CartaoCredito> lista_CC = cartaoDAO.PreencherCamposCartao_C(num_cartao, Integer.parseInt(txt_id.getText()));

            txt_NumCartaoC.setText(Long.toString(lista_CC.element().getN_cartao_credito()));
            txt_Limite.setText(Float.toString(lista_CC.element().getLimite()));
            txt_ValorFatura.setText(Float.toString(lista_CC.element().getValor_fatura()));
            txt_DiaFatura.setText(Integer.toString(lista_CC.element().getDia_fatura()));
            txt_Bandeira.setText(lista_CC.element().getBandeira());
            salva_num_cartao = lista_CC.element().getN_cartao_credito();

            int selLinha = -1;
            selLinha = jtConsultaCC.getSelectedRow();

            if (selLinha != -1) {
                salvaLinhaAtiva = true;
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(this, "Erro ao selecionar os dados!!");
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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        TelaUsuario();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btn_ListarFaturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ListarFaturasActionPerformed
        // TODO add your handling code here:
        
        TelaCartaoCredito_ListaFaturas();
    }//GEN-LAST:event_btn_ListarFaturasActionPerformed

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
    private javax.swing.JButton btPesquisarCC;
    private javax.swing.JButton btn_exclui_cc;
    private javax.swing.JButton btn_inicio_CC;
    private javax.swing.JButton btn_novoCartao_cc;
    private javax.swing.JButton btn_receitaCC;
    private javax.swing.JButton btn_sairCC;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cbbTipo;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtConsultaCC;
    private javax.swing.JRadioButton rbAscendente;
    private javax.swing.JRadioButton rbDescendente;
    private javax.swing.JTextField txt_Bandeira;
    private javax.swing.JTextField txt_DiaFatura;
    private javax.swing.JTextField txt_Limite;
    private javax.swing.JTextField txt_NumCartaoC;
    private javax.swing.JTextField txt_Pesquisa;
    private javax.swing.JTextField txt_ValorFatura;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables

    public void receberID(String recebe) {

        txt_id.setText(recebe);
    }

}
