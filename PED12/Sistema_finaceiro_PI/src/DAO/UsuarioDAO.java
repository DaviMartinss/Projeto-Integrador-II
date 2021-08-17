package DAO;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan
 */
public class UsuarioDAO {
    
    private Connection conexao = null;

    public UsuarioDAO() {
        
        conexao = moduloConexao.conector();
        
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
    
    
    
}
