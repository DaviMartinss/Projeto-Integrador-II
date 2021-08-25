/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAO.ReceitaDAO;
import DAO.moduloConexao;
import Model.Data;
import Model.Receita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static javax.swing.text.html.HTML.Tag.HEAD;

/**
 *
 * @author pc
 */
public class TelaReceita extends javax.swing.JFrame {

    /**
     * Creates new form TelaReceita
     */

    int salvaMes = 0;
    int SalvaAno = 0;
    boolean salvaLinhaAtiva = false;

    public TelaReceita() {
        initComponents();
        this.setLocationRelativeTo(null);
        txt_id.setVisible(false);
        
        txt_total.setEditable(false);
        txt_dia.setEditable(false);
        txt_mes.setEditable(false);
        txt_ano.setEditable(false);
    }
    
    void inicio(){
         
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
    
    void despesa(){
        
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
    
    void cadastra_receita(){
          
         TelaReceita_cadastrar TelaReceita_cadastrar = null;
     
           if (TelaReceita_cadastrar == null) {

               TelaReceita_cadastrar = new  TelaReceita_cadastrar();

               TelaReceita_cadastrar.setVisible(true);
               
               TelaReceita_cadastrar.receberID(txt_id.getText());

           } else {

               TelaReceita_cadastrar.setVisible(true);

               TelaReceita_cadastrar.setState(TelaPrincipal.NORMAL);

               TelaReceita_cadastrar.receberID(txt_id.getText());
                
           }
           
         this.dispose();
    }
    
    
    void RecarregaTabela_Receita(){
        
        salvaLinhaAtiva = false;
        
        ResultSet rs = null;
         
         DefaultTableModel mp1 = (DefaultTableModel) jtConsultaCD.getModel();
        
        int l = mp1.getRowCount();
        
        if(l>0){
            while(l>0){
                //Limpa tabela sempre que for fazer uma nova consulta
                ((DefaultTableModel) jtConsultaCD.getModel()).removeRow(l - 1);
                
                //Menos um pois a primeira linha é a linha zero
                l--;
            }
        }
        
        try{
            
            ReceitaDAO receitaDAO = new ReceitaDAO();
            
            DefaultTableModel mp = (DefaultTableModel) jtConsultaCD.getModel();  

            LinkedList<Receita> lista_receita = receitaDAO.CarregaTabela_Receita(Integer.parseInt(txt_id.getText()));

            for (Receita receita : lista_receita) {
                
                    String Col0 = Float.toString(receita.getTotal());
                    String Col1 = Integer.toString(receita.getDia());
                    String Col2 = Integer.toString(receita.getMes());
                    String Col3 = Integer.toString(receita.getAno());

                    mp.addRow(new String[]{Col0, Col1, Col2, Col3});
                   
            }
            
            lista_receita.clear();
    
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(this, e.getMessage());
            
        }
        
        jtConsultaCD.setAutoCreateRowSorter(true);
         
         
     }
    
    void update_receita() {
        
        if(!(salvaLinhaAtiva)){
            JOptionPane.showMessageDialog(null, "Nenhuma receita foi selecionada para ser atualizda");
            return;
        }
        
        
        Receita receita_aux = new Receita();
        
        if(!(receita_aux.UpdateEhVazio(txt_dia.getText(), txt_mes.getText(), txt_ano.getText(), txt_total.getText() ))){
             
            Receita receita_atua = new Receita(
                Integer.parseInt(txt_dia.getText()),
                Integer.parseInt(txt_mes.getText()),
                Integer.parseInt(txt_ano.getText()),
                Float.parseFloat(txt_total.getText()),
                Integer.parseInt(txt_id.getText()),
                salvaMes,
                SalvaAno
                    
        );

        ReceitaDAO receita_DAO = new ReceitaDAO();

        try {
               
            receita_DAO.UpdateReceita(receita_atua);
                
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }
      }else{
            JOptionPane.showMessageDialog(null, "Nenhum campo pode ser nulo");
        }
    }
    
     void delete_receita() {
             if(!(salvaLinhaAtiva)){
                JOptionPane.showMessageDialog(null, "Nenhuma receita foi selecionada para ser deletada");
            return;
        }
            Receita receita= new Receita(
                    
            Integer.parseInt(txt_id.getText()),
            Integer.parseInt(txt_mes.getText()),
            Integer.parseInt(txt_ano.getText())
        );

            ReceitaDAO receitaDAO = new ReceitaDAO();

        try {
           
             receitaDAO.DeleteReceita(receita);
                   
        } catch (Exception e) {
             
            System.out.println("Foi no delete_receita");
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
     
    void LimpaCampos_Receita(){
         
        txt_total.setText("");
        txt_dia.setText("");
        txt_mes.setText(""); 
        txt_ano.setText(""); 
        
     }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        btn_inicio = new javax.swing.JButton();
        btn_despesas = new javax.swing.JButton();
        btnCartao_cred = new javax.swing.JButton();
        btnCartao_Deb = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_dia = new javax.swing.JTextField();
        txt_mes = new javax.swing.JTextField();
        txt_ano = new javax.swing.JTextField();
        btn_NovaReceita = new javax.swing.JButton();
        cbbTipo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtConsultaCD = new javax.swing.JTable();
        txt_Pesquisa = new javax.swing.JTextField();
        rbAscendente = new javax.swing.JRadioButton();
        rbDescendente = new javax.swing.JRadioButton();
        btPesquisarCD = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        txt_id = new javax.swing.JTextField();
        btn_excluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(700, 500));
        setPreferredSize(new java.awt.Dimension(700, 500));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel7.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        jLabel7.setText("Receitas");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(320, 0, 90, 24);

        btn_inicio.setBackground(new java.awt.Color(105, 69, 219));
        btn_inicio.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_inicio.setForeground(new java.awt.Color(255, 255, 255));
        btn_inicio.setText("Início");
        btn_inicio.setMaximumSize(new java.awt.Dimension(68, 30));
        btn_inicio.setMinimumSize(new java.awt.Dimension(68, 30));
        btn_inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inicioActionPerformed(evt);
            }
        });
        getContentPane().add(btn_inicio);
        btn_inicio.setBounds(30, 40, 80, 25);

        btn_despesas.setBackground(new java.awt.Color(105, 69, 219));
        btn_despesas.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_despesas.setForeground(new java.awt.Color(255, 255, 255));
        btn_despesas.setText("Despesas");
        btn_despesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_despesasActionPerformed(evt);
            }
        });
        getContentPane().add(btn_despesas);
        btn_despesas.setBounds(120, 40, 110, 25);

        btnCartao_cred.setBackground(new java.awt.Color(105, 69, 219));
        btnCartao_cred.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnCartao_cred.setForeground(new java.awt.Color(255, 255, 255));
        btnCartao_cred.setText("Cartão de Crédito");
        btnCartao_cred.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCartao_credActionPerformed(evt);
            }
        });
        getContentPane().add(btnCartao_cred);
        btnCartao_cred.setBounds(240, 40, 170, 25);

        btnCartao_Deb.setBackground(new java.awt.Color(105, 69, 219));
        btnCartao_Deb.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btnCartao_Deb.setForeground(new java.awt.Color(255, 255, 255));
        btnCartao_Deb.setText("Cartão de Débito");
        btnCartao_Deb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCartao_DebActionPerformed(evt);
            }
        });
        getContentPane().add(btnCartao_Deb);
        btnCartao_Deb.setBounds(420, 40, 160, 25);

        jLabel1.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel1.setText("Total: ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 290, 50, 27);

        txt_total.setBackground(new java.awt.Color(187, 210, 240));
        txt_total.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        txt_total.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt_total.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_total.setName(""); // NOI18N
        txt_total.setPreferredSize(new java.awt.Dimension(10, 40));
        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });
        getContentPane().add(txt_total);
        txt_total.setBounds(30, 320, 400, 27);

        jLabel4.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel4.setText("Data (dd/mm/aaaa)");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 350, 150, 27);

        jLabel5.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        jLabel5.setText("/");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(80, 370, 10, 24);

        jLabel6.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        jLabel6.setText("/");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(140, 370, 10, 24);

        jLabel8.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jLabel8.setText("Pesquisar por");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(30, 70, 120, 16);

        txt_dia.setBackground(new java.awt.Color(187, 210, 240));
        txt_dia.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        txt_dia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt_dia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_diaActionPerformed(evt);
            }
        });
        getContentPane().add(txt_dia);
        txt_dia.setBounds(30, 370, 50, 27);

        txt_mes.setBackground(new java.awt.Color(187, 210, 240));
        txt_mes.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        txt_mes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_mes);
        txt_mes.setBounds(90, 370, 50, 27);

        txt_ano.setBackground(new java.awt.Color(187, 210, 240));
        txt_ano.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        txt_ano.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(txt_ano);
        txt_ano.setBounds(150, 370, 50, 27);

        btn_NovaReceita.setBackground(new java.awt.Color(105, 69, 219));
        btn_NovaReceita.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_NovaReceita.setForeground(new java.awt.Color(255, 255, 255));
        btn_NovaReceita.setText("Nova Receita");
        btn_NovaReceita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NovaReceitaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_NovaReceita);
        btn_NovaReceita.setBounds(530, 370, 140, 27);

        cbbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Total", "Dia", "Mês", "Ano" }));
        cbbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTipoActionPerformed(evt);
            }
        });
        getContentPane().add(cbbTipo);
        cbbTipo.setBounds(30, 90, 150, 27);

        jtConsultaCD.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        jtConsultaCD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Total", "Dia", "Mês", "Ano"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtConsultaCD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtConsultaCDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtConsultaCD);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 170, 640, 120);

        txt_Pesquisa.setColumns(200);
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
        txt_Pesquisa.setBounds(190, 90, 480, 27);

        rbAscendente.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbAscendente.setText("Ascendente");
        rbAscendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAscendenteActionPerformed(evt);
            }
        });
        getContentPane().add(rbAscendente);
        rbAscendente.setBounds(30, 130, 100, 25);

        rbDescendente.setFont(new java.awt.Font("Noto Serif", 2, 12)); // NOI18N
        rbDescendente.setText("Descendente");
        rbDescendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDescendenteActionPerformed(evt);
            }
        });
        getContentPane().add(rbDescendente);
        rbDescendente.setBounds(130, 130, 110, 25);

        btPesquisarCD.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btPesquisarCD.setForeground(new java.awt.Color(255, 255, 255));
        btPesquisarCD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/lupa.png"))); // NOI18N
        btPesquisarCD.setBorderPainted(false);
        btPesquisarCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarCDActionPerformed(evt);
            }
        });
        getContentPane().add(btPesquisarCD);
        btPesquisarCD.setBounds(630, 120, 40, 40);

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
        btn_update.setBounds(530, 320, 140, 27);

        txt_id.setEditable(false);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        getContentPane().add(txt_id);
        txt_id.setBounds(20, 10, 60, 20);

        btn_excluir.setText("Excluir");
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });
        getContentPane().add(btn_excluir);
        btn_excluir.setBounds(560, 430, 63, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inicioActionPerformed
        // TODO add your handling code here:
        inicio();
    }//GEN-LAST:event_btn_inicioActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void btnCartao_credActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCartao_credActionPerformed
        // TODO add your handling code here:
        cartao_credito();
    }//GEN-LAST:event_btnCartao_credActionPerformed

    private void btnCartao_DebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCartao_DebActionPerformed
        // TODO add your handling code here:
        cartao_debito();
    }//GEN-LAST:event_btnCartao_DebActionPerformed

    private void btn_despesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_despesasActionPerformed
        // TODO add your handling code here:
        
        despesa();
    }//GEN-LAST:event_btn_despesasActionPerformed

    private void btn_NovaReceitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NovaReceitaActionPerformed
       
        
        cadastra_receita();
        
    }//GEN-LAST:event_btn_NovaReceitaActionPerformed

    private void jtConsultaCDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtConsultaCDMouseClicked
        // TODO add your handling code here:

        String dia = "" + jtConsultaCD.getValueAt(jtConsultaCD.getSelectedRow(), 1);
        
        String mes = "" + jtConsultaCD.getValueAt(jtConsultaCD.getSelectedRow(), 2);
        
        String ano = "" + jtConsultaCD.getValueAt(jtConsultaCD.getSelectedRow(), 3);

        ReceitaDAO receita = new ReceitaDAO();
        
        int selLinha = -1;
        selLinha = jtConsultaCD.getSelectedRow();
        
        if(selLinha != -1){
            salvaLinhaAtiva = true;
        }
        
        ResultSet rs = null;


        try {

            LinkedList<Receita> lista_receita = receita.PreencherCampos_Receita(dia, mes, ano, txt_id.getText());

            txt_total.setText(Float.toString(lista_receita.element().getTotal()));
            txt_dia.setText(Integer.toString(lista_receita.element().getDia()));
            txt_mes.setText(Integer.toString(lista_receita.element().getMes()));
            txt_ano.setText(Integer.toString(lista_receita.element().getAno()));

            salvaMes = Integer.parseInt(txt_mes.getText());
            SalvaAno = Integer.parseInt(txt_ano.getText());

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(this, "Erro ao selecionar os dados!!");
        }
    }//GEN-LAST:event_jtConsultaCDMouseClicked

    private void txt_PesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_PesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_PesquisaActionPerformed

    private void txt_PesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PesquisaKeyReleased


        if(txt_Pesquisa.getText().isEmpty()){
            RecarregaTabela_Receita();
        }

    }//GEN-LAST:event_txt_PesquisaKeyReleased

    private void rbAscendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAscendenteActionPerformed
        // TODO add your handling code here:

        if(rbAscendente.isSelected()){

            rbDescendente.setSelected(false);

        }
    }//GEN-LAST:event_rbAscendenteActionPerformed

    private void rbDescendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDescendenteActionPerformed
        // TODO add your handling code here:

        if(rbDescendente.isSelected()){

            rbAscendente.setSelected(false);

        }
    }//GEN-LAST:event_rbDescendenteActionPerformed

    private void btPesquisarCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarCDActionPerformed
        // TODO add your handling code here:

        if(rbAscendente.isSelected() || rbDescendente.isSelected()){

            boolean ordenar = true;

            String tipo = "";

            String escolha = cbbTipo.getSelectedItem().toString().trim();

            if (escolha.equals("Total")) {
                tipo = " " + "total";
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

            if (rbAscendente.isSelected()) {

                ordenar = true;

            } else {

                ordenar = false;
            }

            String argumento = txt_Pesquisa.getText();

            DefaultTableModel mp1 = (DefaultTableModel) jtConsultaCD.getModel();

            int l = mp1.getRowCount();

            if (l > 0) {
                while (l > 0) {
                    //Limpa tabela sempre que for fazer uma nova consulta
                    ((DefaultTableModel) jtConsultaCD.getModel()).removeRow(l - 1);

                    //Menos um pois a primeira linha é a linha zero
                    l--;
                }
            }

            try {

                ReceitaDAO receitaDAO = new ReceitaDAO();

                DefaultTableModel mp = (DefaultTableModel) jtConsultaCD.getModel();

                LinkedList<Receita> lista_receita = receitaDAO.Consulta_Receita(tipo, argumento, Integer.parseInt(txt_id.getText()), ordenar);

                for (Receita receita : lista_receita) {

                    String Col0 = Float.toString(receita.getTotal());
                    String Col1 = Integer.toString(receita.getDia());
                    String Col2 = Integer.toString(receita.getMes());
                    String Col3 = Integer.toString(receita.getAno());

                    mp.addRow(new String[]{Col0, Col1, Col2, Col3});

                }

                lista_receita.clear();

            } catch (Exception e) {

                JOptionPane.showMessageDialog(this, e.getMessage());

            }

            jtConsultaCD.setAutoCreateRowSorter(true);

        }else{

            JOptionPane.showMessageDialog(null, "Tipo de Ordenação Obrigatório");

        }
    }//GEN-LAST:event_btPesquisarCDActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
        RecarregaTabela_Receita();
    }//GEN-LAST:event_formWindowOpened

    private void txt_diaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_diaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_diaActionPerformed

    private void cbbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbTipoActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        
        if(btn_update.getText().equals("Alterar")){
            
            btn_update.setText("Atualizar");
            
            txt_total.setEditable(true);
            txt_dia.setEditable(true);
            txt_mes.setEditable(true);
            txt_ano.setEditable(true);
        
        }else{
            
            btn_update.setText("Alterar");
            
            txt_total.setEditable(false);
            txt_dia.setEditable(false);
            txt_mes.setEditable(false);
            txt_ano.setEditable(false);
            
            update_receita();
            
            RecarregaTabela_Receita();
            
            LimpaCampos_Receita();
        }
        
        
        
    }//GEN-LAST:event_btn_updateActionPerformed

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        // TODO add your handling code here:
        delete_receita();
        RecarregaTabela_Receita();
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
            java.util.logging.Logger.getLogger(TelaReceita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaReceita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaReceita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaReceita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaReceita().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btPesquisarCD;
    private javax.swing.JButton btnCartao_Deb;
    private javax.swing.JButton btnCartao_cred;
    private javax.swing.JButton btn_NovaReceita;
    private javax.swing.JButton btn_despesas;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_inicio;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cbbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtConsultaCD;
    private javax.swing.JRadioButton rbAscendente;
    private javax.swing.JRadioButton rbDescendente;
    private javax.swing.JTextField txt_Pesquisa;
    private javax.swing.JTextField txt_ano;
    private javax.swing.JTextField txt_dia;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_mes;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables


    public void receberID(String recebe){

        txt_id.setText(recebe);
    }


}
