/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Model.Categoria;
import DAO.CategoriaDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan
 */
public class TelaCategoria_cadastrar extends javax.swing.JFrame {

    /**
     * Creates new form TelaCategoria_cadastrar
     */
    public TelaCategoria_cadastrar() {
        
        initComponents();
        
        this.setLocationRelativeTo(null);
        
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
    
    void volta_telaCategoria() {


        TelaCategoria TelaCategoria = null;

        if (TelaCategoria == null) {

            TelaCategoria = new TelaCategoria();

            TelaCategoria.setVisible(true);

            TelaCategoria.receberID(txt_id.getText());

        } else {

            TelaCategoria.setVisible(true);

            TelaCategoria.setState(TelaPrincipal.NORMAL);

            TelaCategoria.receberID(txt_id.getText());

        }

        this.dispose();
    }
    
    void limpa_campo(){
        
        txt_categoria.setText("");
        
    }
    
    
   public void cadastro_categoria() {
        
        Categoria cartategoria_aux = new Categoria();
          
        Categoria categoria = new Categoria(
              txt_categoria.getText(),
              Integer.parseInt(txt_id.getText())
                
        );
        
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        try {
            
                categoriaDAO.CadastrarCategoria(categoria);
                 volta_telaCategoria();
                
            
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
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

        txt_id = new javax.swing.JTextField();
        txt_categoria = new javax.swing.JTextField();
        btn_inicio = new javax.swing.JButton();
        btn_cadastarCategoria = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_inicio.setText("Inicio");
        btn_inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inicioActionPerformed(evt);
            }
        });

        btn_cadastarCategoria.setText("Cadastrar");
        btn_cadastarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadastarCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(btn_inicio))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(txt_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btn_cadastarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_inicio))
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cadastarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cadastarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastarCategoriaActionPerformed
        
        
        cadastro_categoria();
        
        
    }//GEN-LAST:event_btn_cadastarCategoriaActionPerformed

    private void btn_inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inicioActionPerformed
        // TODO add your handling code here:
        
        inicio();
        
    }//GEN-LAST:event_btn_inicioActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCategoria_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCategoria_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCategoria_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCategoria_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCategoria_cadastrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cadastarCategoria;
    private javax.swing.JButton btn_inicio;
    private javax.swing.JTextField txt_categoria;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables

public void receberID(String recebe) {

        txt_id.setText(recebe);
}



}