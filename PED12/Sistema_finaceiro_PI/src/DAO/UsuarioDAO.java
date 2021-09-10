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
        
        if(!(verificaEmailExiste(usuario.getEmail()))){
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
        }else{
            
            JOptionPane.showMessageDialog(null, "Email já é cadastrado");
            return false;
        }

    }
    
     public boolean UpdateUser(Usuario usuario) throws SQLException {
            
         String email_aux = consultaEmail(usuario.getId_conta());
        
        if (email_aux != usuario.getEmail()) {
            
            if (!(verificaEmailExiste(usuario.getEmail()))) {
                
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

                } finally {

                    pst.close();

                }
            }else{
                JOptionPane.showMessageDialog(null, "Esse email já foi cadastrado");
                return false;
            }
        }else{
            // é igual ao seu email atual
            return true;
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
    
   public boolean DeleteUser(int id) throws SQLException {

        PreparedStatement pst = null;

        String delete = "delete from conta where id_conta = ?";
        
        try {

            pst = conexao.prepareStatement(delete);

            pst.setInt(1, id);

            pst.executeUpdate();
            
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.toString());
            return false;

        } finally {

            pst.close();
        }

        return true;
    }
    
   
    public boolean valida_UpdateSenha(String senha1, String senha2) {

        if (senha1.equals(senha2)) {
            return true;

        } else {

            return false;

        }
    }
    
    public boolean verificaEmailExiste(String email)throws SQLException {
        
        String consulta = "select id_conta from conta where email = ?";

        PreparedStatement pst = conexao.prepareStatement(consulta);

        ResultSet rs = null;

        try {

            pst.setString(1, email);

            rs = pst.executeQuery();

            if (rs.next()) {

                return true;

            } else {

                return false;
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        }
    }
    
    public String consultaEmail(int id) throws SQLException{
       
       String consulta = "select email from conta where id_conta = ?";

       String email = null;
       
       PreparedStatement pst = conexao.prepareStatement(consulta);
       
       ResultSet rs = null;
       
       try {
           
           pst.setInt(1, id);
           
           rs = pst.executeQuery();
           
           if(rs.next()){
               
               email = rs.getString("email");
               
           }

       } catch (Exception e) {

           JOptionPane.showMessageDialog(null, "Falha ao consultar email");

       }
       
       return email;
       
    }

}
