/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAO.CartaoDebitoDAO;
import DAO.ReceitaDAO;
import DAO.moduloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import Model.Data;
import Model.Receita;
/**
 *
 * @author Alan
 */
public class TelaReceita_cadastrar extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst1 = null;
    PreparedStatement pst2 = null;
    PreparedStatement pst3 = null;
    ResultSet rs = null;
    boolean flagSucData = true;
    boolean flagSucTotal = true;
    
    
    /**
     * Creates new form TelaReceita_cadastrar
     */
    public TelaReceita_cadastrar() {
        initComponents();
        conexao = moduloConexao.conector();
        this.setLocationRelativeTo(null);
    }
    
    public void cadastro_receita(){
        
//        int cod_receita = 0;
//        
//        String id_conta = txt_id.getText();
//         
//        int Id_conta_BD = Integer.parseInt(id_conta);
//
//        String sql1 = "insert into receita_data (dia, mes, ano) values(?,?,?)";
//        
//        String sql2 = "select * from receita_data where dia=? and mes=? and ano=?";
//        
//        String sql3 = "insert into receita (receita_data_cod_receita, total, conta_id_conta) values(?,?,?)";
//  
//        try {
//            
//            pst1 = conexao.prepareStatement(sql1);
//            pst1.setString(1, txt_dia.getText());
//            pst1.setString(2, txt_mes.getText());
//            pst1.setString(3, txt_ano.getText());
//            
//            
//            int dia = Integer.parseInt(txt_dia.getText());
//            int mes = Integer.parseInt(txt_dia.getText());
//            int ano = Integer.parseInt(txt_ano.getText());
//            
//            Data data_aux = new Data(dia, mes, ano);
//            
//            if(!(data_aux.verifica_data())){
//                flagSucData = false;
//            }
//            if(flagSucData){
//                pst1.executeUpdate();
//            }    
//            
//            
//            pst2 = conexao.prepareStatement(sql2);
//            
//            pst2.setString(1, txt_dia.getText());
//            pst2.setString(2, txt_mes.getText());
//            pst2.setString(3, txt_ano.getText());
//                   
//                rs = pst2.executeQuery();
//            
//            if(rs.next()){
//                
//                cod_receita = rs.getInt(1);
//                
//                pst3 = conexao.prepareStatement(sql3);
//
//                pst3.setInt(1, cod_receita);
//                pst3.setString(2, txt_total.getText());
//                pst3.setInt(3, Id_conta_BD);
//                
//                float total = Float.parseFloat(txt_total.getText());
//                Receita receita_aux = new Receita();
//                receita_aux.setTotal(total);
//                
//                if(!(receita_aux.verifica_total())){
//                    flagSucTotal = false;
//                }
//                
//                if(flagSucTotal){
//                    pst3.executeUpdate();
//                }
//                
//
//            }else{
//                JOptionPane.showMessageDialog(null, "Usuario ou senha inválido");
//            }
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Falha ao cadastrar receita");
//        }
//        
//        if(flagSucData && flagSucTotal){
//            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
//        }else{
//            JOptionPane.showMessageDialog(null, "Falha ao cadastrar receita");
//        }
        
        Receita receita = new Receita(
                Integer.parseInt(txt_dia.getText()),
                Integer.parseInt(txt_mes.getText()),
                Integer.parseInt(txt_ano.getText()),
                Float.parseFloat(txt_total.getText()),
                Integer.parseInt(txt_id.getText())
        );
        
        ReceitaDAO receitaDAO = new ReceitaDAO();

        try {
            
            
            if (receita.verifica_ReceitaValida())
            {
                
                receitaDAO.CadastrarReceita(receita);
                
                Volta_TelaReceita();
                
            }else{
                
                JOptionPane.showMessageDialog(null, "Dados Inválidos!!");
                
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        
    }
    
    
    void Volta_TelaReceita(){
        
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
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_dia = new javax.swing.JTextField();
        txt_mes = new javax.swing.JTextField();
        txt_ano = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        btn_NovaReceita = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Dia: ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Mês:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Ano:");

        jLabel1.setText("Total: ");

        txt_id.setEditable(false);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });

        btn_NovaReceita.setText("Cadastrar Receita");
        btn_NovaReceita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NovaReceitaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("CADASTRO DE RECEITA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(4, 4, 4))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_ano, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_dia, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_mes, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(btn_NovaReceita))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(144, 144, 144))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addComponent(btn_NovaReceita)
                        .addContainerGap(49, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void btn_NovaReceitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NovaReceitaActionPerformed
        
        if(  txt_dia.getText().isEmpty() || txt_mes.getText().isEmpty()   ||
             txt_ano.getText().isEmpty() || txt_total.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "Todos campos são de preenchimento obrigatório!");
            
        }else{
            
            cadastro_receita();

        }
        
        
    }//GEN-LAST:event_btn_NovaReceitaActionPerformed

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
            java.util.logging.Logger.getLogger(TelaReceita_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaReceita_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaReceita_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaReceita_cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaReceita_cadastrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_NovaReceita;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
