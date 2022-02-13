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
    
    public boolean Logar(Usuario user) throws SQLException, NullPointerException {

       String sql = "select * from conta where email=? and senha=?";

       boolean retorno;
       
        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            
            retorno = false;
            
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
        }

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
            
            retorno = true;
        }
            
         pst.close();
         return retorno;
    }
    
    public boolean UpdateSenha(Usuario usuario) throws SQLException, NullPointerException {

        boolean retorno = false;
        String insert = "UPDATE conta SET senha = ? WHERE id_conta = ?";

        try (PreparedStatement pst = conexao.prepareStatement(insert)) {
            
            pst.setString(1, usuario.getSenha());
            pst.setInt(2, usuario.getId_conta());
            
            pst.executeUpdate();
            
            retorno = true;
        }
        
        return retorno;
}
    
     public Usuario GetUsuario(String id_conta) throws SQLException, NumberFormatException {
 
       String consulta = "SELECT * FROM conta WHERE id_conta = ?";
       
       ResultSet rs;
       Usuario user;
       
         try (PreparedStatement pst = conexao.prepareStatement(consulta)) {
             
             pst.setInt(1, Integer.parseInt(id_conta));
             rs = pst.executeQuery();
             
             user = null;
             
             if (rs.next()) {

                 user = new Usuario.UsuarioBuild()
                         .Nome(rs.getString("nome"))
                         .Senha(rs.getString("senha"))
                         .Email(rs.getString("email"))
                         .IdConta(rs.getInt("id_conta"))
                         .build();
            }
             
              pst.close();
              rs.close();
         }
        
       return user;
   }
     
    public String NomeUsuario(String id_conta) throws SQLException, NumberFormatException {
 
       String consulta = "SELECT nome FROM conta WHERE id_conta = ?";
       
       String nome = null;
       
        try (PreparedStatement pst = conexao.prepareStatement(consulta)) {
            
            pst.setInt(1, Integer.parseInt(id_conta));
            
           try (ResultSet rs = pst.executeQuery()) {
               if (rs.next())
                   nome = rs.getString("nome");
           }
        }
        

       return nome;   
   }
    
   public boolean DeleteUser(int id) throws SQLException {

       String delete = "DELETE FROM conta WHERE id_conta = ?";
        
        try (PreparedStatement pst = conexao.prepareStatement(delete)) {
            pst.setInt(1, id);
            
            pst.executeUpdate();
        }

       return true;
    }
    
   
    public boolean valida_UpdateSen(String senha1, String senha2) {

        return senha1.equals(senha2);
    }
    
    public boolean verificaEmailExiste(String email)throws SQLException {
        
        String consulta = "SELECT id_conta FROM conta WHERE email = ?";

        boolean retorno;
        
        try (PreparedStatement pst = conexao.prepareStatement(consulta)) {
            
            pst.setString(1, email);
            
            try (ResultSet rs = pst.executeQuery()) {
                retorno = rs.next();
            }
        }
        
        return retorno;
    }
    
    public String consultaEmail(int id) throws SQLException{
       
       String consulta = "SELECT email FROM conta WHERE id_conta = ?";

       String email = null;
       
       PreparedStatement pst = conexao.prepareStatement(consulta);

       pst.setInt(1, id);

       ResultSet rs = pst.executeQuery();

        if (rs.next())
            email = rs.getString("email");
        
       return email;
    }
    
    
    public int consultaId(String email) throws SQLException{
       
       String consulta = "SELECT id_conta FROM conta WHERE email = ?";

       int id = -1;
       
       PreparedStatement pst = conexao.prepareStatement(consulta);

       pst.setString(1, email);

       ResultSet rs = pst.executeQuery();

       if (rs.next()) 
            id = rs.getInt("id_conta");
        
       return id;
    }
}