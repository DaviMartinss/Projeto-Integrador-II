/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.ControlerTabela;
import DAO.CategoriaDAO;
import DAO.DespesaDAO;
import DAO.ReceitaDAO;
import Model.Categoria;
import Model.Data;
import Model.Despesa;
import Model.Receita;
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
 * @author Alan
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

        txtValor.setEditable(false);
        txt_NumCartao.setEditable(false);
        txtParcelas.setEditable(false);
        txtCategoria.setEditable(false);
        txtDia.setEditable(false);
        txtMes.setEditable(false);
        txtAno.setEditable(false);
        rbPago.setEnabled(false);
        rbNaoPago.setEnabled(false);
        rbDebito.setEnabled(false);
        rbCredito.setEnabled(false);
        rbDinheiro.setEnabled(false);
        cbb_categoria.removeAllItems();
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

    void cartao_debito() {

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

    void cadastrar_despesa() {

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
    
    void CarregaCategoria(){
        
        CategoriaDAO categoria = new CategoriaDAO();
            
        try {
           int id_aux = Integer.parseInt(txt_id.getText()); 
            
            LinkedList<Categoria> lista_categoria = categoria.CarregaTabela_categoria(id_aux);
            
            
            for (Categoria cat : lista_categoria) {

                String Cat_aux =  cat.getCategoria_aux();
                cbb_categoria.addItem(Cat_aux);
            }

            lista_categoria.clear();
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Falha listar categoria");
        }
        
    }

    void RecarregaTabela_Despesa() {
        
        salvaLinhaAtiva = false;     
        
        ControlerTabela.LimpaTabela(jtConsultaDespesa);

        try {

            String Col0 = null;
            String Col1 = null;
            String Col2 = null;
            String Col3 = null;
            String Col4 = null;
            String Col5 = null;
            String Col6 = null;
            String Col7 = null;
            String Col8 = null;
            String Col9 = null;
            String Col10 = null;

            DespesaDAO despesaDAO = new DespesaDAO();

            DefaultTableModel mp = (DefaultTableModel) jtConsultaDespesa.getModel();

            LinkedList<Despesa> lista_despesa = despesaDAO.CarregaTabela_Despesa(Integer.parseInt(txt_id.getText()));

            for (Despesa despesa : lista_despesa) {

                Col0 = Integer.toString(despesa.getCod_despesa());
                Col1 = Integer.toString(despesa.getDia());
                Col2 = Integer.toString(despesa.getMes());
                Col3 = Integer.toString(despesa.getAno());
                Col4 = Float.toString(despesa.getValor());
                Col5 = despesa.getCategoria();
                Col6 = despesa.getF_pagamento();
                Col7 = Long.toString(despesa.getNum_cartao());
                Col8 = Integer.toString(despesa.getNum_parcelas());
                Col9 = despesa.getEstatus();
                Col10 = despesa.getDescricao();

                if (Col7.equals("0")) {

                    Col7 = "----";

                }

                if (Col8 == null) {

                    Col8 = "----";

                }

                if (Col10 == null) {

                    Col10 = "----";

                }

                mp.addRow(new String[]{Col0, Col1, Col2, Col3,
                    Col4, Col5, Col6, Col7,
                    Col8, Col9, Col10});

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage());

        }

    }

    void PesquisaDespesa(boolean despesas) {

        boolean ordenar = true;

        String tipo = "";

        String escolha = cbbTipo.getSelectedItem().toString().trim();

        if (escolha.equals("Valor")) {
            tipo = " " + "valor";
        }

        if (escolha.equals("Categoria")) {
            tipo = " " + "categoria";
        }

        if (escolha.equals("Número do Cartão")) {
            tipo = " " + "num_cartao";
        }

        if (escolha.equals("Dia")) {
            tipo = " " + "dia";
        }

        if (escolha.equals("Mês")) {
            tipo = " " + "mes";
        }

        if (escolha.equals("Ano")) {
            tipo = " " + "ano";
        }

        if (escolha.equals("Descrição")) {
            tipo = " " + "descricao";
        }

        if (escolha.equals("Nº Parcelas")) {
            tipo = " " + "n_parcelas";
        }

        if (escolha.equals("Forma de Pagamento")) {
            tipo = " " + "f_pagamento";
        }

        if (escolha.equals("Estatus")) {
            tipo = " " + "estatus";
        }

        if (rbAscendente.isSelected()) {

            ordenar = true;

        } else {

            ordenar = false;
        }

        String argumento = txt_Pesquisa.getText();

        ControlerTabela.LimpaTabela(jtConsultaDespesa);

        try {

            String Col0 = null;
            String Col1 = null;
            String Col2 = null;
            String Col3 = null;
            String Col4 = null;
            String Col5 = null;
            String Col6 = null;
            String Col7 = null;
            String Col8 = null;
            String Col9 = null;
            String Col10 = null;

            ResultSet rs = null;

            DespesaDAO despesaDAO = new DespesaDAO();

            Receita receita = new Receita();
            
            receita.setId_conta(Integer.parseInt(txt_id.getText()));
            
            if(!(despesas)){
                
                receita.setMes(Integer.parseInt(txtMesReceita.getText()));
                receita.setAno(Integer.parseInt(txtAnoReceita.getText()));
            }

            DefaultTableModel mp = (DefaultTableModel) jtConsultaDespesa.getModel();

            LinkedList<Despesa> lista_despesa = despesaDAO.Consulta_Despesa(tipo, argumento, ordenar, despesas, txt_id.getText(), receita);
            
            for (Despesa despesa : lista_despesa) {

                Col0 = Integer.toString(despesa.getCod_despesa());
                Col1 = Integer.toString(despesa.getDia());
                Col2 = Integer.toString(despesa.getMes());
                Col3 = Integer.toString(despesa.getAno());
                Col4 = Float.toString(despesa.getValor());
                Col5 = despesa.getCategoria();
                Col6 = despesa.getF_pagamento();
                Col7 = Long.toString(despesa.getNum_cartao());
                Col8 = Integer.toString(despesa.getNum_parcelas());
                Col9 = despesa.getEstatus();
                Col10 = despesa.getDescricao();

                if (Col7 == null) {

                    Col7 = "----";

                }

                if (Col8 == null) {

                    Col8 = "----";

                }

                if (Col10 == null) {

                    Col10 = "----";

                }

                mp.addRow(new String[]{Col0, Col1, Col2, Col3,
                    Col4, Col5, Col6, Col7,
                    Col8, Col9, Col10});

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Erro:PesquisaDespesa - " + e.getMessage());

        }

    }

    void telaUpdateDespesa() {

        if (!(salvaLinhaAtiva)) {
            JOptionPane.showMessageDialog(null, "Nenhuma despesa foi selecionada para ser atualizda");
            return;
        }

        Despesa despesa_aux = new Despesa();

        if (despesa_aux.UpdateEhVazio(txtDia.getText(), txtMes.getText(), txtAno.getText(), txtValor.getText(), salvaF_pagamento, txt_NumCartao.getText(), txtParcelas.getText(), salvaStatus, txtAreaDescricao.getText())) {
            JOptionPane.showMessageDialog(null, "Nenhum Campo ser vazio");
            return;
        }

        if (!(despesa_aux.Update_CamposValidos(txtValor.getText(), txtDia.getText(), txtMes.getText(), txtAno.getText()))) {

            JOptionPane.showMessageDialog(null, "O valor informado é inválido");
            return;
        }

        if (salvaF_pagamento.equals("CRÉDITO")) {
            Validacao valida = new Validacao();
            if (!(valida.ehNum(txtParcelas.getText()))) {
                JOptionPane.showMessageDialog(null, "Número de parcelas inválido");
                return;
            }
        }
        String categoria = cbb_categoria.getSelectedItem().toString().trim();
        
        if (salvaF_pagamento.equals("CRÉDITO")) {

            Despesa despesa = new Despesa(
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
                    salvaCodigoDespesa
                    //Integer.parseInt(txt_id.getText())
                    
            );

            DespesaDAO despesaDao = new DespesaDAO();

            try {
                despesaDao.UpdateDespesa(despesa, Integer.parseInt(txt_id.getText()));

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else if (salvaF_pagamento.equals("DÉBITO")) {
            Despesa despesa = new Despesa(
                    Integer.parseInt(txtDia.getText().trim()),
                    Integer.parseInt(txtMes.getText()),
                    Integer.parseInt(txtAno.getText()),
                    Float.parseFloat(txtValor.getText()),
                    categoria,
                    salvaF_pagamento,
                    Long.parseLong(txt_NumCartao.getText()),
                    salvaStatus,
                    txtAreaDescricao.getText(),
                    salvaCodigoDespesa
                    //Integer.parseInt(txt_id.getText())
            );

            DespesaDAO despesaDao = new DespesaDAO();

            try {
                despesaDao.UpdateDespesa(despesa, Integer.parseInt(txt_id.getText()));

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        } else {
            Despesa despesa = new Despesa(
                    Integer.parseInt(txtDia.getText().trim()),
                    Integer.parseInt(txtMes.getText()),
                    Integer.parseInt(txtAno.getText()),
                    Float.parseFloat(txtValor.getText()),
                    categoria,
                    salvaF_pagamento,
                    salvaStatus,
                    txtAreaDescricao.getText(),
                    salvaCodigoDespesa
                    //Integer.parseInt(txt_id.getText())
            );

            DespesaDAO despesaDao = new DespesaDAO();

            try {
                despesaDao.UpdateDespesa(despesa, Integer.parseInt(txt_id.getText()));

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }

    }

    void delete_despesa() {

        if (!(salvaLinhaAtiva)) {
            JOptionPane.showMessageDialog(null, "Nenhuma despesa foi selecionada para ser deletada");
            return;
        }
        Despesa despesa = new Despesa(
                salvaCodigoDespesa
        );

        DespesaDAO despesaDAO = new DespesaDAO();

        try {

            despesaDAO.DeleteDespesa(despesa);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    void LimpaCampos_Despesa() {

        txtValor.setText("");
        txt_NumCartao.setText("");
        txtParcelas.setText("");
        txtCategoria.setText("");
        txtDia.setText("");
        txtMes.setText("");
        txtAno.setText("");
        txtAreaDescricao.setText("");
        rbPago.setEnabled(false);
        rbNaoPago.setEnabled(false);
        rbDebito.setEnabled(false);
        rbCredito.setEnabled(false);
        rbDinheiro.setEnabled(false);
        rbPago.setSelected(false);
        rbNaoPago.setSelected(false);
        rbDebito.setSelected(false);
        rbCredito.setSelected(false);
        rbDinheiro.setSelected(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jtConsultaDespesa = new javax.swing.JTable();
        btnCartao_cred = new javax.swing.JButton();
        btnCartao_Deb = new javax.swing.JButton();
        btnReceitas = new javax.swing.JButton();
        btn_inicio = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        rbPago = new javax.swing.JRadioButton();
        rbNaoPago = new javax.swing.JRadioButton();
        txtValor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaDescricao = new javax.swing.JTextArea();
        txtDia = new javax.swing.JTextField();
        txtMes = new javax.swing.JTextField();
        txtAno = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        rbDebito = new javax.swing.JRadioButton();
        rbCredito = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtParcelas = new javax.swing.JTextField();
        btnNovaDespesa = new javax.swing.JToggleButton();
        cbbTipo = new javax.swing.JComboBox<>();
        txt_Pesquisa = new javax.swing.JTextField();
        btPesquisarDespesa = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtMesReceita = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtAnoReceita = new javax.swing.JTextField();
        rbDescendente = new javax.swing.JRadioButton();
        rbAscendente = new javax.swing.JRadioButton();
        rbDinheiro = new javax.swing.JRadioButton();
        txt_NumCartao = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btn_update = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbb_categoria = new javax.swing.JComboBox<>();
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
        jScrollPane2.setBounds(20, 150, 760, 120);

        btnCartao_cred.setText("Cartão de Crédito");
        btnCartao_cred.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCartao_credActionPerformed(evt);
            }
        });
        getContentPane().add(btnCartao_cred);
        btnCartao_cred.setBounds(2126, 25, 119, 23);

        btnCartao_Deb.setText("Cartão de Débito");
        btnCartao_Deb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCartao_DebActionPerformed(evt);
            }
        });
        getContentPane().add(btnCartao_Deb);
        btnCartao_Deb.setBounds(2282, 25, 115, 23);

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
        btnReceitas.setBounds(680, 30, 100, 25);

        btn_inicio.setText("Início");
        btn_inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inicioActionPerformed(evt);
            }
        });
        getContentPane().add(btn_inicio);
        btn_inicio.setBounds(2016, 25, 57, 23);

        jLabel1.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel1.setText("Valor:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 270, 41, 27);

        jLabel3.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel3.setText("Descrição: ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(340, 320, 64, 27);

        jLabel4.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel4.setText("Data: (dd/mm/aaaa)");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(610, 270, 130, 27);

        jLabel5.setFont(new java.awt.Font("Noto Serif", 1, 24)); // NOI18N
        jLabel5.setText(" /");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(640, 80, 20, 27);

        jLabel6.setFont(new java.awt.Font("Noto Serif", 1, 24)); // NOI18N
        jLabel6.setText("/");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(720, 290, 10, 27);

        jLabel7.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel7.setText("Status: ");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(20, 320, 60, 27);

        jLabel12.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        jLabel12.setText("Despesa");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(360, 0, 90, 24);

        jLabel8.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel8.setText("Forma de Pagamento: ");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(20, 370, 127, 27);

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
        jButton1.setBounds(20, 30, 90, 25);

        rbPago.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbPago.setText("Pago");
        rbPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPagoActionPerformed(evt);
            }
        });
        getContentPane().add(rbPago);
        rbPago.setBounds(20, 340, 70, 27);

        rbNaoPago.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbNaoPago.setText("Não pago");
        rbNaoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNaoPagoActionPerformed(evt);
            }
        });
        getContentPane().add(rbNaoPago);
        rbNaoPago.setBounds(90, 340, 79, 27);

        txtValor.setBackground(new java.awt.Color(187, 210, 240));
        txtValor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });
        getContentPane().add(txtValor);
        txtValor.setBounds(20, 290, 80, 27);

        txtAreaDescricao.setBackground(new java.awt.Color(187, 210, 240));
        txtAreaDescricao.setColumns(20);
        txtAreaDescricao.setRows(5);
        txtAreaDescricao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jScrollPane1.setViewportView(txtAreaDescricao);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(340, 340, 440, 120);

        txtDia.setBackground(new java.awt.Color(187, 210, 240));
        txtDia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtDia);
        txtDia.setBounds(610, 290, 50, 27);

        txtMes.setBackground(new java.awt.Color(187, 210, 240));
        txtMes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtMes);
        txtMes.setBounds(670, 290, 50, 27);

        txtAno.setBackground(new java.awt.Color(187, 210, 240));
        txtAno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtAno);
        txtAno.setBounds(730, 290, 50, 27);

        jLabel15.setFont(new java.awt.Font("Noto Serif", 1, 24)); // NOI18N
        jLabel15.setText("/");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(660, 290, 10, 27);

        txtCategoria.setBackground(new java.awt.Color(187, 210, 240));
        txtCategoria.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txtCategoria);
        txtCategoria.setBounds(120, 290, 200, 27);

        rbDebito.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbDebito.setText("Débito");
        rbDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDebitoActionPerformed(evt);
            }
        });
        getContentPane().add(rbDebito);
        rbDebito.setBounds(20, 390, 63, 27);

        rbCredito.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbCredito.setText("Crédito");
        rbCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCreditoActionPerformed(evt);
            }
        });
        getContentPane().add(rbCredito);
        rbCredito.setBounds(80, 390, 70, 27);

        jLabel9.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel9.setText("Nº de Pacelas: ");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(210, 320, 84, 27);

        jLabel16.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel16.setText("Pesquisar por");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(20, 60, 100, 27);

        txtParcelas.setBackground(new java.awt.Color(187, 210, 240));
        getContentPane().add(txtParcelas);
        txtParcelas.setBounds(20, 440, 110, 27);

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
        btnNovaDespesa.setBounds(200, 490, 150, 27);

        cbbTipo.setBackground(new java.awt.Color(187, 210, 240));
        cbbTipo.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        cbbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Valor", "Categoria", "Descrição", "Forma de Pagamento", "Número do Cartão", "Estatus", "Dia", "Mês", "Ano", "Nº  Parcelas", " ", " ", " " }));
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

        jLabel10.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel10.setText("Receita: (mês e ano)");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(600, 60, 140, 27);

        txtMesReceita.setBackground(new java.awt.Color(187, 210, 240));
        txtMesReceita.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtMesReceita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMesReceitaKeyPressed(evt);
            }
        });
        getContentPane().add(txtMesReceita);
        txtMesReceita.setBounds(600, 80, 40, 27);

        jLabel11.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel11.setText("Categoria: ");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(120, 270, 61, 27);

        txtAnoReceita.setBackground(new java.awt.Color(187, 210, 240));
        txtAnoReceita.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtAnoReceita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAnoReceitaKeyPressed(evt);
            }
        });
        getContentPane().add(txtAnoReceita);
        txtAnoReceita.setBounds(660, 80, 60, 27);

        rbDescendente.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbDescendente.setText("Descendente");
        rbDescendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDescendenteActionPerformed(evt);
            }
        });
        getContentPane().add(rbDescendente);
        rbDescendente.setBounds(120, 120, 110, 27);

        rbAscendente.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbAscendente.setText("Ascendente");
        rbAscendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAscendenteActionPerformed(evt);
            }
        });
        getContentPane().add(rbAscendente);
        rbAscendente.setBounds(20, 120, 100, 27);

        rbDinheiro.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbDinheiro.setText("Dinheiro");
        rbDinheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDinheiroActionPerformed(evt);
            }
        });
        getContentPane().add(rbDinheiro);
        rbDinheiro.setBounds(150, 390, 90, 27);

        txt_NumCartao.setBackground(new java.awt.Color(187, 210, 240));
        txt_NumCartao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_NumCartao);
        txt_NumCartao.setBounds(340, 290, 250, 27);

        jLabel14.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel14.setText("Nº do Cartão: ");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(340, 270, 76, 27);

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
        btn_update.setBounds(20, 490, 150, 27);

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
        btn_excluir.setBounds(630, 490, 150, 27);

        jLabel2.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel2.setText("Digite sua pesquisa aqui");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(190, 60, 180, 27);

        jLabel17.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel17.setText("Parcelas");
        getContentPane().add(jLabel17);
        jLabel17.setBounds(20, 420, 70, 27);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Back-2.png"))); // NOI18N
        jLabel13.setText("jLabel13");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(0, 0, 1920, 1080);

        cbb_categoria.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        cbb_categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cbb_categoria);
        cbb_categoria.setBounds(210, 340, 110, 27);

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

    private void btnCartao_credActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCartao_credActionPerformed
        // TODO add your handling code here:
        cartao_credito();

    }//GEN-LAST:event_btnCartao_credActionPerformed

    private void btnCartao_DebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCartao_DebActionPerformed
        // TODO add your handling code here:
        cartao_debito();
    }//GEN-LAST:event_btnCartao_DebActionPerformed

    private void btnReceitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceitasActionPerformed
        // TODO add your handling code here:
        receita();
    }//GEN-LAST:event_btnReceitasActionPerformed

    private void btn_inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inicioActionPerformed
        // TODO add your handling code here:
        inicio();
    }//GEN-LAST:event_btn_inicioActionPerformed

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

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void btnNovaDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaDespesaActionPerformed
        // TODO add your handling code here:

        cadastrar_despesa();

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
        
        DespesaDAO despesaDAO = new DespesaDAO();
        
        boolean despesas = false;
        
        if (txtMesReceita.getText().isEmpty() && txtAnoReceita.getText().isEmpty()) {   
            
            despesas = true;
            
        }
        
        if (despesas) {

            if (rbAscendente.isSelected() || rbDescendente.isSelected()) {

                PesquisaDespesa(despesas);

             } else {

                JOptionPane.showMessageDialog(null, "Tipo de Ordenação Obrigatório");

            }

        }else{
            
            try {

                if (despesaDAO.ValidaConsulta_Despesa(Integer.parseInt(txtMesReceita.getText()), Integer.parseInt(txtAnoReceita.getText()))) {

                    if (rbAscendente.isSelected() || rbDescendente.isSelected()) {

                        PesquisaDespesa(despesas);

                    } else {

                        JOptionPane.showMessageDialog(null, "Tipo de Ordenação Obrigatório");

                    }

                } else {

                    JOptionPane.showMessageDialog(this, "Mês ou Ano da receita inválidos ou inexistentes!");
                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(this, "Erro na validação da consulta");

            }
            
            
        }
    }//GEN-LAST:event_btPesquisarDespesaActionPerformed

    private void jtConsultaDespesaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtConsultaDespesaMouseClicked
        // TODO add your handling code here:

        String dia = "" + jtConsultaDespesa.getValueAt(jtConsultaDespesa.getSelectedRow(), 1);

        String mes = "" + jtConsultaDespesa.getValueAt(jtConsultaDespesa.getSelectedRow(), 2);

        String ano = "" + jtConsultaDespesa.getValueAt(jtConsultaDespesa.getSelectedRow(), 3);
        
        String categoria = "" + jtConsultaDespesa.getValueAt(jtConsultaDespesa.getSelectedRow(), 5);

        DespesaDAO despesaDAO = new DespesaDAO();
        int linhSel = jtConsultaDespesa.getSelectedRow();
        String id = null;
        id = (String) jtConsultaDespesa.getValueAt(linhSel, 0);
        salvaCodigoDespesa = Integer.parseInt(id);

        int selLinha = -1;
        selLinha = jtConsultaDespesa.getSelectedRow();

        if (selLinha != -1) {
            salvaLinhaAtiva = true;
        }

        try {

            LinkedList<Despesa> lista_despesa = despesaDAO.PreencherCampos_Despesa(dia, mes, ano, categoria, txt_id.getText());

            txtValor.setText(Float.toString(lista_despesa.element().getValor()));
            txtCategoria.setText(lista_despesa.element().getCategoria());
            txtDia.setText(Integer.toString(lista_despesa.element().getDia()));
            txtMes.setText(Integer.toString(lista_despesa.element().getMes()));
            txtAno.setText(Integer.toString(lista_despesa.element().getAno()));
            txtAreaDescricao.setText(lista_despesa.element().getDescricao());
            txtParcelas.setText(Integer.toString(lista_despesa.element().getNum_parcelas()));
            txt_NumCartao.setText(Long.toString(lista_despesa.element().getNum_cartao()));

            if (lista_despesa.element().getF_pagamento().equals("CRÉDITO")) {

                salvaF_pagamento = "CRÉDITO";
                rbCredito.setSelected(true);
                rbDebito.setSelected(false);
                rbDinheiro.setSelected(false);

            }

            if (lista_despesa.element().getF_pagamento().equals("DÉBITO")) {

                salvaF_pagamento = "DÉBITO";
                rbDebito.setSelected(true);
                rbCredito.setSelected(false);
                rbDinheiro.setSelected(false);

            }

            if (lista_despesa.element().getF_pagamento().equals("DINHEIRO")) {

                salvaF_pagamento = "DINHEIRO";
                rbDinheiro.setSelected(true);
                rbDebito.setSelected(false);
                rbCredito.setSelected(false);
            }

            if (lista_despesa.element().getEstatus().equals("PAGO")) {

                salvaStatus = "PAGO";
                rbPago.setSelected(true);
                rbNaoPago.setSelected(false);

            } else {

                salvaStatus = "NÃO PAGO";
                rbNaoPago.setSelected(true);
                rbPago.setSelected(false);
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(this, "Erro ao selecionar os dados!!");
        }

    }//GEN-LAST:event_jtConsultaDespesaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        inicio();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        CarregaCategoria();
        RecarregaTabela_Despesa();
    }//GEN-LAST:event_formWindowOpened

    private void txtMesReceitaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMesReceitaKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtMesReceitaKeyPressed

    private void txtAnoReceitaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnoReceitaKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtAnoReceitaKeyPressed

    private void rbDescendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDescendenteActionPerformed
        // TODO add your handling code here:

        if (rbDescendente.isSelected()) {

            rbAscendente.setSelected(false);

        }
    }//GEN-LAST:event_rbDescendenteActionPerformed

    private void rbAscendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAscendenteActionPerformed
        // TODO add your handling code here:

        if (rbAscendente.isSelected()) {

            rbDescendente.setSelected(false);

        }
    }//GEN-LAST:event_rbAscendenteActionPerformed

    private void rbDinheiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDinheiroActionPerformed
        // TODO add your handling code here:

        if (rbDinheiro.isSelected()) {

            salvaF_pagamento = "DINHEIRO";
            rbCredito.setSelected(false);
            rbDebito.setSelected(false);
            txt_NumCartao.setEnabled(false);
            txtParcelas.setEnabled(false);

        }
    }//GEN-LAST:event_rbDinheiroActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:

        if (btn_update.getText().equals("Alterar")) {

            btn_update.setText("Atualizar");

            txtValor.setEditable(true);
            txt_NumCartao.setEditable(true);
            txtParcelas.setEditable(true);
            txtCategoria.setEditable(true);
            txtDia.setEditable(true);
            txtMes.setEditable(true);
            txtAno.setEditable(true);
            rbPago.setEnabled(true);
            rbNaoPago.setEnabled(true);
            rbDebito.setEnabled(true);
            rbCredito.setEnabled(true);
            rbDinheiro.setEnabled(true);

        } else {

            btn_update.setText("Alterar");

            txtValor.setEditable(false);
            txt_NumCartao.setEditable(false);
            txtParcelas.setEditable(false);
            txtCategoria.setEditable(false);
            txtDia.setEditable(false);
            txtMes.setEditable(false);
            txtAno.setEditable(false);
            rbPago.setEnabled(false);
            rbNaoPago.setEnabled(false);
            rbDebito.setEnabled(false);
            rbCredito.setEnabled(false);
            rbDinheiro.setEnabled(false);

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

            boolean atualiza = true;

            if (despesa.getF_pagamento().equals("CRÉDITO") || despesa.getF_pagamento().equals("DÉBITO")) {

                if (!(despesa.verifica_num_cartao_despesa())) {

                    JOptionPane.showMessageDialog(this, "Número do cartão inválido", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);

                    atualiza = false;

                }

            }
            
            if (atualiza) {

                telaUpdateDespesa();
                RecarregaTabela_Despesa();
                LimpaCampos_Despesa();

                JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

            } else {

                JOptionPane.showMessageDialog(null, "Erro ao atualizar", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);

            }

        }

    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        // TODO add your handling code here:
        delete_despesa();
        RecarregaTabela_Despesa();
    }//GEN-LAST:event_btn_excluirActionPerformed

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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaDespesa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btPesquisarDespesa;
    private javax.swing.JButton btnCartao_Deb;
    private javax.swing.JButton btnCartao_cred;
    private javax.swing.JToggleButton btnNovaDespesa;
    private javax.swing.JButton btnReceitas;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_inicio;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cbbTipo;
    private javax.swing.JComboBox<String> cbb_categoria;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtConsultaDespesa;
    private javax.swing.JRadioButton rbAscendente;
    private javax.swing.JRadioButton rbCredito;
    private javax.swing.JRadioButton rbDebito;
    private javax.swing.JRadioButton rbDescendente;
    private javax.swing.JRadioButton rbDinheiro;
    private javax.swing.JRadioButton rbNaoPago;
    private javax.swing.JRadioButton rbPago;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtAnoReceita;
    private javax.swing.JTextArea txtAreaDescricao;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtDia;
    private javax.swing.JTextField txtMes;
    private javax.swing.JTextField txtMesReceita;
    private javax.swing.JTextField txtParcelas;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txt_NumCartao;
    private javax.swing.JTextField txt_Pesquisa;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables

    public void receberID(String recebe) {

        txt_id.setText(recebe);
    }

}
