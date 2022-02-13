/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.UsuarioDAO;
import Model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan
 */
public class ControlerUser {
    
    public static boolean Logar(Usuario user) {
        
        UsuarioDAO userDAO = new UsuarioDAO();
        
        try {
            
            return userDAO.Logar(user);
            
        } catch (SQLException | NullPointerException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro:ReceitaTemSaldo ->" + ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);            
            
            return false;
        }
    }
    
    public static boolean CadastrarUsuario(Usuario usuario) {
        
        UsuarioDAO userDAO = new UsuarioDAO();
        
        try {
            
            return userDAO.CadastrarUsuario(usuario);
            
        } catch (SQLException | NullPointerException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro:CadastrarUsuario ->" + ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);            
            
            return false;
        }
    }
    
    public static boolean AtualizarUsuario(Usuario usuario) {
        
        UsuarioDAO userDAO = new UsuarioDAO();
        
        try {
            
            return userDAO.UpdateUser(usuario);
            
        } catch (SQLException | NullPointerException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro:AtualizarUsuario ->" + ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);            
            
            return false;
        }
    }
    
    public static boolean ApagarUsuario(int id) {
        
        UsuarioDAO userDAO = new UsuarioDAO();
        
        try {
            
            return userDAO.DeleteUser(id);
            
        } catch (SQLException | NullPointerException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro:AtualizarUsuario ->" + ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);            
            
            return false;
        }
    }
    
    public static boolean AtualizarSenha(Usuario usuario) {
        
        UsuarioDAO userDAO = new UsuarioDAO();
        
        try {
            
            return userDAO.UpdateSenha(usuario);
            
        } catch (SQLException | NullPointerException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro:AtualizarUsuario ->" + ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);            
            
            return false;
        }
    }
    
    public static Usuario GetUsuario(String id_conta) {
        
        UsuarioDAO userDAO = new UsuarioDAO();
        
        try {
            
            return userDAO.GetUsuario(id_conta);
            
        } catch (SQLException | NullPointerException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro:PreencherCamposUsuario ->" + ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);            
            
            return null;
        }        
    }
    
    public static String NomeUsuario(String id_conta) {
        
        UsuarioDAO userDAO = new UsuarioDAO();
        
        try {
            
            return userDAO.NomeUsuario(id_conta);
            
        } catch (SQLException | NullPointerException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro:NomeUsuario ->" + ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);            
            
            return null;
        }        
    }
    
    public static boolean EmailExiste(String email) {
        
        UsuarioDAO userDAO = new UsuarioDAO();
        
        try {
            
            return userDAO.verificaEmailExiste(email);
            
        } catch (SQLException | NullPointerException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro:EmailExiste ->" + ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);            
            
            return false;
        }        
    }
    
    public static String ConsultarEmail(int id) {
        
        UsuarioDAO userDAO = new UsuarioDAO();
        
        try {
            
            return userDAO.consultaEmail(id);
            
        } catch (SQLException | NullPointerException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro:ConsultarEmail ->" + ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);            
            
            return null;
        }        
    }
    
    public static int consultaId(String email) {
        
        UsuarioDAO userDAO = new UsuarioDAO();
        
        try {
            
            return userDAO.consultaId(email);
            
        } catch (SQLException | NullPointerException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro:ConsultarEmail ->" + ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);            
            
            return 0;
        }        
    }   
}