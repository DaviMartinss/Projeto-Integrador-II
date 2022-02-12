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
    
    public boolean logar(Usuario user) throws SQLException, NullPointerException {

       String sql = "select * from conta where email=? and senha=?";

       PreparedStatement pst = conexao.prepareStatement(sql);
       
       boolean retorno = false;

        pst.setString(1, user.getEmail());
        pst.setString(2, user.getSenha());

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            user.setId_conta(rs.getInt(1));

            retorno = true;

        } else {

            JOptionPane.showMessageDialog(null, "Usuario ou senha inválido");

            retorno = false;
        }

        pst.close();

        return retorno;
    }
    
    public boolean CadastrarUsuario(Usuario usuario) throws SQLException, NullPointerException {

        boolean retorno = false;
        PreparedStatement pst = null;

        if (!(verificaEmailExiste(usuario.getEmail()))) {

            String insert = "INSERT INTO conta (nome, email, senha) VALUES(?,?,?)";

            pst = conexao.prepareStatement(insert);

            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEmail());
            pst.setString(3, usuario.getSenha());

            //pst.setString(5, txtCadastraAvatar.getText());
            pst.executeUpdate();

            retorno = true;

        } else {

            JOptionPane.showMessageDialog(null, "Email já é cadastrado");
            retorno = false;
        }

        pst.close();

        return retorno;

    }
    
     public boolean UpdateUser(Usuario usuario) throws SQLException, NullPointerException {
            
         String email_aux = consultaEmail(usuario.getId_conta());
         
         boolean retorno = false;
         PreparedStatement pst = null;
         
         if (!email_aux.equals(usuario.getEmail())) {

             if (!(verificaEmailExiste(usuario.getEmail()))) {

                 String insert = "UPDATE conta SET nome= ? , email = ? WHERE id_conta = ?";

                 pst = conexao.prepareStatement(insert);

                 pst.setString(1, usuario.getNome());
                 pst.setString(2, usuario.getEmail());
                 pst.setInt(3, usuario.getId_conta());

                 pst.executeUpdate();

                 retorno = true;

             } else {
                 JOptionPane.showMessageDialog(null, "Esse email já foi cadastrado");
                 retorno = false;
             }
        }else{
            // é igual ao seu email atual
            retorno = true;
        }
            
         pst.close();
         return retorno;
    }
    
    public boolean UpdateSenha(Usuario usuario) throws SQLException, NullPointerException {

        PreparedStatement pst = null;
        boolean retorno = false;
        String insert = "UPDATE conta SET senha = ? WHERE id_conta = ?";

        pst = conexao.prepareStatement(insert);

        pst.setString(1, usuario.getSenha());
        pst.setInt(2, usuario.getId_conta());

        pst.executeUpdate();

        retorno = true;

        pst.close();
        
        return retorno;
}
    
     public ResultSet PreencherCampos_Usuario(String id_conta) throws SQLException {
 
       String consulta = "SELECT * FROM conta WHERE id_conta = ?";
       
       PreparedStatement pst = conexao.prepareStatement(consulta);
       
       ResultSet rs = null;
       
       try {
           
           pst.setInt(1, Integer.parseInt(id_conta));
           
           rs = pst.executeQuery();

       } catch (NumberFormatException | SQLException e) {

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

       } catch (NumberFormatException | SQLException e) {

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
            
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.toString());
            return false;

        } finally {

            pst.close();
        }

        return true;
    }
    
   
    public boolean valida_UpdateSenha(String senha1, String senha2) {

        return senha1.equals(senha2);
    }
    
    public boolean verificaEmailExiste(String email)throws SQLException {
        
        String consulta = "select id_conta from conta where email = ?";

        PreparedStatement pst = conexao.prepareStatement(consulta);

        ResultSet rs = null;

        try {

            pst.setString(1, email);

            rs = pst.executeQuery();

            return rs.next();

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

       } catch (SQLException e) {

           JOptionPane.showMessageDialog(null, "Falha ao consultar email");
       }
       
       return email;
       
    }
    
    
    public int consultaId(String email) throws SQLException{
       
       String consulta = "select id_conta from conta where email = ?";

       int id = -1;
       
       PreparedStatement pst = conexao.prepareStatement(consulta);
       
       ResultSet rs = null;
       
       try {
           
           pst.setString(1, email);
           
           rs = pst.executeQuery();
           
           if(rs.next()){
               
              id = rs.getInt("id_conta");
               
           }

       } catch (SQLException e) {

           JOptionPane.showMessageDialog(null, "Falha ao consultar Id");

       }
       
       return id;
       
    }
}