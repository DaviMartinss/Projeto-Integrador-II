/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Model.Categoria;
import javax.swing.JOptionPane;
import DAO.CategoriaDAO;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;
import CategoriaOrdenacao.CategoriaASC;
import CategoriaOrdenacao.CategoriaDESC;
import Controllers.ControlerTabela;
/**
 *
 * @author Alan
 */
public class TelaCategoria extends javax.swing.JFrame {

    boolean salvaLinhaAtiva = false;
    boolean ordena = false;
    String salvaCategoria = null;

    /**
     * Creates new form TelaCategoria
     */
    public TelaCategoria() {
        
        initComponents();

        this.setLocationRelativeTo(null);

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
    
    void limpa_campo(){
        
        txt_NomeCategoria.setText("");
        
    }

    void cadastra_categoria() {

        TelaCategoria_cadastrar TelaCadastra_categoria = null;

        if (TelaCadastra_categoria == null) {

            TelaCadastra_categoria = new TelaCategoria_cadastrar();

            TelaCadastra_categoria.setVisible(true);
            
            TelaCadastra_categoria.receberID(txt_id.getText());

        } else {

            TelaCadastra_categoria.setVisible(true);

            TelaCadastra_categoria.setState(TelaPrincipal.NORMAL);

            TelaCadastra_categoria.receberID(txt_id.getText());

        }

        this.dispose();
    }
    
    
    void update_categoria() {

        if (!(salvaLinhaAtiva)) {
            JOptionPane.showMessageDialog(null, "Nenhuma categoria foi selecionada para ser atualizda");
            return;
        }
        
        Categoria categoria_aux  = new Categoria();
            
        if (!(categoria_aux.valorEhVazio(txt_NomeCategoria.getText()))) {

            Categoria categoria_atua = new Categoria(
                    txt_NomeCategoria.getText(),
                    salvaCategoria,
                    Integer.parseInt(txt_id.getText())
                    
            );
            
            CategoriaDAO categoria_DAO = new CategoriaDAO();

            try {
                
                 categoria_DAO.UpdateCategoria(categoria_atua);
                 
                 limpa_campo();

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Falha ao atualizar a categoria");
            }
        } else {
            
            JOptionPane.showMessageDialog(null, "Nenhum campo pode ser nulo");
        }
    }
    
    
    void delete_categoria() {

        if (!(salvaLinhaAtiva)) {
            JOptionPane.showMessageDialog(null, "Nenhuma categoria foi selecionada para ser deletada");
            return;
        }
        
        Categoria categoria = new Categoria(
        
                salvaCategoria
                    
        );
                
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        try {
            
            categoriaDAO.DeleteCategoria(categoria, Integer.parseInt(txt_id.getText()));
            limpa_campo();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Falha ao deletar categoria");
        }
    }
    
    void RecarregaTabela_Categoria(boolean ordena) {
                
        boolean salvaLinhaAtiva = false;
        
        ControlerTabela.LimpaTabela(jt_categoria);
        
        try {

            CategoriaDAO categoria = new CategoriaDAO();
            DefaultTableModel mp = (DefaultTableModel) jt_categoria.getModel();

            
            LinkedList<Categoria> lista_categoria = categoria.TabelaCategoria(Integer.parseInt(txt_id.getText()));
        
            if(!(ordena)){    
                    
                    for (Categoria cat : lista_categoria) {
                        String Col0 = cat.getCategoria_aux();
                        mp.addRow(new String[]{Col0});
                    }

                    lista_categoria.clear();

            }else{

                if(selectorUp.isSelected()){
                       
                   Collections.sort(lista_categoria, new CategoriaASC() );
                   
                   for (Categoria cat : lista_categoria) {

                        String Col0 =  cat.getCategoria_aux();

                        mp.addRow(new String[]{Col0});
                    }

                    lista_categoria.clear();

                }else{
                    // ordena decrescente
                    
                    Collections.sort(lista_categoria, new CategoriaDESC() );
                   
                   for (Categoria cat : lista_categoria) {

                        String Col0 =  cat.getCategoria_aux();

                        mp.addRow(new String[]{Col0});
                    }

                    lista_categoria.clear();
                    
                }

            }

    }  catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Falha ao realizar a ordenação");

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
        jt_categoria = new javax.swing.JTable();
        txt_NomeCategoria = new javax.swing.JTextField();
        txt_Pesquisa = new javax.swing.JTextField();
        selectorUp = new javax.swing.JRadioButton();
        selectorDown = new javax.swing.JRadioButton();
        btn_voltaInicio = new javax.swing.JButton();
        btn_cadastraCategoria = new javax.swing.JButton();
        btn_deletaCategoria = new javax.swing.JButton();
        btn_ordenar = new javax.swing.JButton();
        btn_updateCategoria = new javax.swing.JButton();
        btPesquisarCD = new javax.swing.JButton();
        findTextTitle = new javax.swing.JLabel();
        nameTitle = new javax.swing.JLabel();
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
        pageTitle.setText("Categorias");
        getContentPane().add(pageTitle);
        pageTitle.setBounds(360, 0, 110, 24);

        jt_categoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Categoria"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_categoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_categoriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jt_categoria);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(150, 250, 300, 199);
        getContentPane().add(txt_NomeCategoria);
        txt_NomeCategoria.setBounds(150, 200, 300, 27);

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
        txt_Pesquisa.setBounds(150, 110, 520, 27);

        selectorUp.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        selectorUp.setText("Ascendente");
        selectorUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectorUpActionPerformed(evt);
            }
        });
        getContentPane().add(selectorUp);
        selectorUp.setBounds(150, 150, 93, 27);

        selectorDown.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        selectorDown.setText("Descendente");
        selectorDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectorDownActionPerformed(evt);
            }
        });
        getContentPane().add(selectorDown);
        selectorDown.setBounds(250, 150, 101, 27);

        btn_voltaInicio.setBackground(new java.awt.Color(105, 69, 219));
        btn_voltaInicio.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_voltaInicio.setForeground(new java.awt.Color(255, 255, 255));
        btn_voltaInicio.setText("Inicio");
        btn_voltaInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_voltaInicioActionPerformed(evt);
            }
        });
        getContentPane().add(btn_voltaInicio);
        btn_voltaInicio.setBounds(40, 40, 80, 27);

        btn_cadastraCategoria.setBackground(new java.awt.Color(105, 69, 219));
        btn_cadastraCategoria.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_cadastraCategoria.setForeground(new java.awt.Color(255, 255, 255));
        btn_cadastraCategoria.setText("Cadastrar");
        btn_cadastraCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadastraCategoriaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cadastraCategoria);
        btn_cadastraCategoria.setBounds(540, 340, 140, 27);

        btn_deletaCategoria.setBackground(new java.awt.Color(210, 59, 239));
        btn_deletaCategoria.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_deletaCategoria.setForeground(new java.awt.Color(255, 255, 255));
        btn_deletaCategoria.setText("Excluir");
        btn_deletaCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deletaCategoriaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_deletaCategoria);
        btn_deletaCategoria.setBounds(540, 420, 140, 27);

        btn_ordenar.setBackground(new java.awt.Color(105, 69, 219));
        btn_ordenar.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_ordenar.setForeground(new java.awt.Color(255, 255, 255));
        btn_ordenar.setText("Ordenar");
        btn_ordenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ordenarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_ordenar);
        btn_ordenar.setBounds(370, 150, 120, 27);

        btn_updateCategoria.setBackground(new java.awt.Color(105, 69, 219));
        btn_updateCategoria.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        btn_updateCategoria.setForeground(new java.awt.Color(255, 255, 255));
        btn_updateCategoria.setText("Alterar");
        btn_updateCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateCategoriaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_updateCategoria);
        btn_updateCategoria.setBounds(540, 380, 140, 27);

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
        btPesquisarCD.setBounds(630, 140, 40, 40);

        findTextTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        findTextTitle.setText("Pesquisar");
        getContentPane().add(findTextTitle);
        findTextTitle.setBounds(150, 90, 60, 27);

        nameTitle.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N
        nameTitle.setText("Nome");
        getContentPane().add(nameTitle);
        nameTitle.setBounds(150, 180, 60, 27);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Back-2.png"))); // NOI18N
        background.setText("jLabel1");
        getContentPane().add(background);
        background.setBounds(0, 0, 1920, 1080);
        background.getAccessibleContext().setAccessibleName("background");

        getContentPane().add(txt_id);
        txt_id.setBounds(0, 0, 49, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jt_categoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_categoriaMouseClicked
        // TODO add your handling code here:
        
        String cat = "" + jt_categoria.getValueAt(jt_categoria.getSelectedRow(), 0);
        txt_NomeCategoria.setText(cat);
        
    }//GEN-LAST:event_jt_categoriaMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
        RecarregaTabela_Categoria(ordena);
    }//GEN-LAST:event_formWindowOpened

    private void btn_deletaCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deletaCategoriaActionPerformed
        // TODO add your handling code here:
        
        delete_categoria();
        RecarregaTabela_Categoria(ordena);
        
    }//GEN-LAST:event_btn_deletaCategoriaActionPerformed

    private void btn_voltaInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_voltaInicioActionPerformed
        // TODO add your handling code here:
        
        inicio();
        
    }//GEN-LAST:event_btn_voltaInicioActionPerformed

    private void btn_ordenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ordenarActionPerformed
        // TODO add your handling code here:
        
         if (selectorUp.isSelected() || selectorDown.isSelected()) {
            RecarregaTabela_Categoria(ordena);
        }else{
            
            JOptionPane.showMessageDialog(null, "Tipo de Ordenação Obrigatório");
        }
    }//GEN-LAST:event_btn_ordenarActionPerformed

    private void selectorUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectorUpActionPerformed
        // TODO add your handling code here:
          if (selectorUp.isSelected()) {
            ordena = true;
            selectorDown.setSelected(false);
            
        }
    }//GEN-LAST:event_selectorUpActionPerformed

    private void selectorDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectorDownActionPerformed
        // TODO add your handling code here:
        
        if (selectorDown.isSelected()) {
            ordena = true;
            selectorUp.setSelected(false);

        }
    }//GEN-LAST:event_selectorDownActionPerformed

    private void btn_cadastraCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastraCategoriaActionPerformed
        // TODO add your handling code here:
         cadastra_categoria();
    }//GEN-LAST:event_btn_cadastraCategoriaActionPerformed

    private void btn_updateCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateCategoriaActionPerformed
        // TODO add your handling code here:

        if (btn_updateCategoria.getText().equals("Alterar")) {

            btn_updateCategoria.setText("Atualizar");

            txt_NomeCategoria.setEditable(true);

        } else {

            btn_updateCategoria.setText("Alterar");

            txt_NomeCategoria.setEditable(false);

            update_categoria();

            RecarregaTabela_Categoria(true);

            limpa_campo();
        }

    }//GEN-LAST:event_btn_updateCategoriaActionPerformed

    private void txt_PesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_PesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_PesquisaActionPerformed

    private void txt_PesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PesquisaKeyReleased

        if (txt_Pesquisa.getText().isEmpty()) {
            RecarregaTabela_Categoria(true);
        }
    }//GEN-LAST:event_txt_PesquisaKeyReleased

    private void btPesquisarCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarCDActionPerformed
        // TODO add your handling code here:

        if (selectorUp.isSelected() || selectorDown.isSelected()) {

            boolean ordenar = true;

            if (selectorUp.isSelected()) {

                ordenar = true;

            } else {

                ordenar = false;
            }

            String argumento = txt_Pesquisa.getText();

            ControlerTabela.LimpaTabela(jt_categoria);

            try {

                CategoriaDAO categoriaDAO = new CategoriaDAO();

                DefaultTableModel mp = (DefaultTableModel) jt_categoria.getModel();

                LinkedList<Categoria> lista_categoria = categoriaDAO.ConsultaCategoria(argumento, Integer.parseInt(txt_id.getText()), ordenar);

                if (ordenar) {

                    Collections.sort(lista_categoria, new CategoriaASC());

                } else {

                    Collections.sort(lista_categoria, new CategoriaDESC());
                }
                
                for (Categoria categoria : lista_categoria) {

                        String Col0 = categoria.getCategoriaTipo();

                        mp.addRow(new String[]{Col0});

                }

                lista_categoria.clear();

            } catch (Exception e) {

                JOptionPane.showMessageDialog(this, e.getMessage());

            }

        } else {

            JOptionPane.showMessageDialog(null, "Tipo de Ordenação Obrigatório");

        }
    }//GEN-LAST:event_btPesquisarCDActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCategoria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btPesquisarCD;
    private javax.swing.JButton btn_cadastraCategoria;
    private javax.swing.JButton btn_deletaCategoria;
    private javax.swing.JButton btn_ordenar;
    private javax.swing.JButton btn_updateCategoria;
    private javax.swing.JButton btn_voltaInicio;
    private javax.swing.JLabel findTextTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_categoria;
    private javax.swing.JLabel nameTitle;
    private javax.swing.JLabel pageTitle;
    private javax.swing.JRadioButton selectorDown;
    private javax.swing.JRadioButton selectorUp;
    private javax.swing.JTextField txt_NomeCategoria;
    private javax.swing.JTextField txt_Pesquisa;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables


public void receberID(String recebe) {

        txt_id.setText(recebe);
}



}
