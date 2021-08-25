package DAO;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 *
 * @author Alan
 */
public class UsuarioDAO {
    
    private Connection conexao = null;

    public UsuarioDAO() {
        
        conexao = moduloConexao.conector();
        
    }
    
    public boolean logar(Usuario user) throws SQLException {

        String sql = "select * from conta where email=? and senha=?";
        
        PreparedStatement pst = null;
        
        try {
            
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getSenha());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                user.setId_conta(rs.getInt(1));
                
                return true;

            } else {
                
                JOptionPane.showMessageDialog(null, "Usuario ou senha inválido");
                
                return false;
            }
        } catch (Exception e) {
        
            JOptionPane.showMessageDialog(null, "Erro ao logar", "ERROR_MESSAGE", ERROR_MESSAGE);
            
            return false;
        
        }finally{
            
            pst.close();
            
        }

    }
    
    
    
    public boolean CadastrarUsuario(Usuario usuario) throws SQLException {

        PreparedStatement pst = null;

        String insert = "insert into conta (nome, email, senha) values(?,?,?)";

        pst = conexao.prepareStatement(insert);

        try {

            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEmail());
            pst.setString(3, usuario.getSenha());

            //pst.setString(5, txtCadastraAvatar.getText());
            pst.executeUpdate();
            
            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        }finally{
            
            pst.close();
            
        }

    }
    
     public boolean UpdateUser(Usuario usuario) throws SQLException {
        
        PreparedStatement pst = null;

        String insert = "update conta set nome= ? , email = ? where id_conta = ?";

        pst = conexao.prepareStatement(insert);

        try {

            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEmail());
            pst.setInt(3, usuario.getId_conta());

            pst.executeUpdate();
            
            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        }finally{
            
            pst.close();
            
        }

    }
    
    
    public boolean UpdateSenha(Usuario usuario) throws SQLException {
        
        PreparedStatement pst = null;

        String insert = "update conta set senha = ? where id_conta = ?";

        pst = conexao.prepareStatement(insert);

        try {

            pst.setString(1, usuario.getSenha());
            pst.setInt(2, usuario.getId_conta());
            

            
            pst.executeUpdate();
            
            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        }finally{
            
            pst.close();
            
        }

    }
    
     public ResultSet PreencherCampos_Usuario(String id_conta) throws SQLException {
 
       String consulta = "SELECT * FROM conta WHERE id_conta = ?";
       
       PreparedStatement pst = conexao.prepareStatement(consulta);
       
       ResultSet rs = null;
       
       try {
           
           pst.setInt(1, Integer.parseInt(id_conta));
           
           rs = pst.executeQuery();

       } catch (Exception e) {

           JOptionPane.showMessageDialog(null, e.getMessage());

       }

       return rs;
       
   }
     
    public String NomeUsuario(String id_conta) throws SQLException {
 
       String consulta = "SELECT nome FROM conta WHERE id_conta = ?";
       
       String nome = null;
       
       PreparedStatement pst = conexao.prepareStatement(consulta);
       
       ResultSet rs = null;
       
       try {
           
           pst.setInt(1, Integer.parseInt(id_conta));
           
           rs = pst.executeQuery();
           
           if(rs.next()){
               
               nome = rs.getString("nome");
               
           }

       } catch (Exception e) {

           JOptionPane.showMessageDialog(null, e.getMessage());

       }

       return nome;
       
   }
    
   
    public boolean valida_UpdateSenha(String senha1, String senha2) {

        if (senha1.equals(senha2)) {
            return true;

        } else {

            return false;

        }
    }

}
