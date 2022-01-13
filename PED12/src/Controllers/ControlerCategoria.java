/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.CategoriaDAO;
import Model.Categoria;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan
 */
public class ControlerCategoria {
    
    public static boolean CadastrarCategoria(Categoria categoria){
        
       CategoriaDAO categoriaDAO = new CategoriaDAO(); 
       
        try {
            
            if (!(categoriaDAO.CategoriaExiste(categoria.getCategoriaTipo(), categoria.getId_conta()))) {

                categoriaDAO.InsertCategoria(categoria);
                
            } else {
                
                JOptionPane.showMessageDialog(null, "Essa categoria já foi cadastrada", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);

                return false;
            }
            
            return true;
            
        }catch (SQLException | HeadlessException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:CadastrarCategoria", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return false;
        
        }
    }
    
    public static boolean AtualizarCategoria(Categoria categoria){
        
       CategoriaDAO categoriaDAO = new CategoriaDAO(); 
       
        try {
            
            if (!(categoriaDAO.CategoriaExiste(categoria.getCategoriaTipo(), categoria.getId_conta()))) {
                
                categoria.setCategoriaId(categoriaDAO.GetCategoriaId(categoria.getId_conta(), categoria.getCategoria_aux()));

                categoriaDAO.UpdateCategoria(categoria);
                
            } else {
                
                JOptionPane.showMessageDialog(null, "Essa categoria já foi cadastrada", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);

                return false;
            }
            
            return true;
            
        }catch (SQLException | HeadlessException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:AtualizarCategoria", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return false;       
        }
    }
    
    public static int GetCategoriaId(int id_conta, String categoria){
        
       CategoriaDAO categoriaDAO = new CategoriaDAO(); 
       
        try {
            
            return categoriaDAO.GetCategoriaId(id_conta, categoria);
               
        }catch (SQLException | HeadlessException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:GetCategoriaId", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return -1;       
        }
    }
    
    public static boolean CategoriaExiste(String categoria, int id_conta){
        
       CategoriaDAO categoriaDAO = new CategoriaDAO(); 
       
        try {
            
            return categoriaDAO.CategoriaExiste(categoria, id_conta);
               
        }catch (SQLException | HeadlessException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:CategoriaExiste", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return false;       
        }
    }
    
    public static String GetTipoCategoria(int id_categoria, int id_conta){
        
       CategoriaDAO categoriaDAO = new CategoriaDAO(); 
       
        try {
            
            return categoriaDAO.GetTipoCategoria(id_categoria, id_conta);
               
        }catch (SQLException | HeadlessException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:CategoriaExiste", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return null;       
        }
    }
    
    public static boolean ApagarCategoria(Categoria categoria, int id_conta){
        
       CategoriaDAO categoriaDAO = new CategoriaDAO(); 
       
        try {
            
            int id_categoria =  categoriaDAO.GetCategoriaId(id_conta, categoria.getCategoriaTipo());
            
            if (!categoriaDAO.CategoriaDespesaExiste(categoria.getCategoriaTipo(), id_conta)) {

                categoriaDAO.DeleteCategoria(id_categoria, id_conta);
                
                return true;
                
            } else {

                JOptionPane.showMessageDialog(null, "Voce possui despesas vinculadas a essa categoria!\n" + 
                                                    "Remova os vinculos para que possa apagar esta categoria");
                return false;
            }
    
        }catch (SQLException | HeadlessException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:CategoriaExiste", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return false;       
        }
    }
    
    public static LinkedList<Categoria> GetListaCategoria(int id_conta){
        
       CategoriaDAO categoriaDAO = new CategoriaDAO(); 
       
        try {
            
            return categoriaDAO.GetListaCategoria(id_conta);
               
        }catch (SQLException | HeadlessException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:GetListaCategoria", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return null;       
        }
    }
    
    public static LinkedList<Categoria> ConsultaCategoria(String arg, int id_conta, boolean ordenar){
        
       CategoriaDAO categoriaDAO = new CategoriaDAO(); 
       
        try {
            
            return categoriaDAO.ConsultaCategoria(arg, id_conta, ordenar);
               
        }catch (SQLException | HeadlessException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:ConsultaCategoria", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return null;       
        }
    }
    
    
}
