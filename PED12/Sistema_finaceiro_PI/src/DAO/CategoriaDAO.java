
package DAO;

import DAO.moduloConexao;
import Model.CartaoCredito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Model.Categoria;
import java.sql.ResultSet;
import java.util.LinkedList;
/**
 *
 * @author pc
 */
public class CategoriaDAO {
    
    private Connection conexao = null;

    public CategoriaDAO() {

        conexao = moduloConexao.conector();

    }

    public boolean CadastrarCategoria(Categoria categoria) throws SQLException {
        
        Categoria categoria_aux  = new Categoria();
        
        if(!(categoria_aux.valorEhVazio(categoria.getCategoriaTipo()))){
        
            if(!(CategoriaExiste(categoria.getCategoriaTipo(), categoria.getId_conta()))){
                PreparedStatement pst = null;

                String insert = "insert into categoria (categoriaTipo, conta_id_conta) values(?, ?);";

                pst = conexao.prepareStatement(insert);

                try {

                    pst.setString(1, categoria.getCategoriaTipo().toUpperCase());
                    pst.setInt(2, categoria.getId_conta());

                    pst.executeUpdate();

                    return true;

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null, e.getMessage());

                    return false;

                } finally {

                    pst.close();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Essa categoria já foi cadastrada");

                return false;
            }
        }else{
            
            JOptionPane.showMessageDialog(null, "Nenhum campo pode ser nulo");

            return false;
        }
   }
    
    public boolean UpdateCategoria(Categoria categoria) throws SQLException {
         
        if(!(CategoriaExiste(categoria.getCategoriaTipo(), categoria.getId_conta() ))){

            PreparedStatement pst = null;

            String update = "update categoria set categoriaTipo=? where categoriaTipo = ?;";

            pst = conexao.prepareStatement(update);

            try {
                
                pst.setString(1, categoria.getCategoriaTipo().toUpperCase());
                pst.setString(2, categoria.getCategoria_aux());


                pst.executeUpdate();

                return true;

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e.getMessage());

                return false;

            } finally {

                pst.close();
            }
        }else{
            
            JOptionPane.showMessageDialog(null, "Essa categoria já foi cadastrada");
            
            return false;
        }
    }
    
    
    
    public boolean DeleteCategoria(Categoria categoria) throws SQLException {

        PreparedStatement pst = null;

        String delete = "delete from categoria where categoriaTipo = ?";

        pst = conexao.prepareStatement(delete);

        try {

            pst.setString(1, categoria.getCategoria_aux());
            
            pst.executeUpdate();

            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;

        } finally {

            pst.close();
        }

    }
    
    public LinkedList<Categoria> Consulta_Categoria(String arg, int id_conta, boolean ordenar) throws SQLException {

        String argumento = "";

        if (ordenar) {

            argumento = " and categoriaTipo" + " " + "like '" + arg + "%'";

        } else {

            argumento = " and categoriaTipo" + " " + "like '" + arg + "%'";
        }

        String consulta = "SELECT categoriaTipo from categoria where categoria.conta_id_conta = ?"
                        + argumento + "";
        
        ResultSet rs = null;

        PreparedStatement pst = conexao.prepareStatement(consulta);

        LinkedList<Categoria> lista_categoria = new LinkedList();

        try {

            pst.setInt(1, id_conta);

            rs = pst.executeQuery();

            while (rs.next()) {

                lista_categoria.add(new Categoria(
                        rs.getString("categoriaTipo"),
                        id_conta)
                );

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        } finally {

            pst.close();
        }

        return lista_categoria;

    }
    
    
    
    
    // busca o id de uma categoria pelo o id da despesa
    public int ConsultaIdCategoria_despesa(String cat, int id_conta) throws SQLException {

        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta = "select categoriaId from categoria where (categoriaTipo = ? and conta_id_conta = ?)";

        pst = conexao.prepareStatement(consulta);

        try {
            
            pst.setString(1, cat);
            pst.setInt(2, id_conta);
            
             rs = pst.executeQuery();
            
            
            if (rs.next()) {

                 return rs.getInt(1);

            } else {

                return -1;
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            return -1;

        } finally {

            pst.close();
        }

    }
   
    
    // parte para ser modificada
    
    
    public LinkedList<Categoria> CarregaTabela_categoria(int id_conta) throws SQLException {

        String consulta = "select * from categoria where conta_id_conta = ?";

        ResultSet rs = null;

        LinkedList<Categoria> lista_Categoria = new LinkedList();

        PreparedStatement pst = conexao.prepareStatement(consulta);

        try {

            pst.setInt(1, id_conta);

            rs = pst.executeQuery();
                
           
            while (rs.next()) {

                lista_Categoria.add(new Categoria(
                        rs.getString("categoriaTipo"))
                        
                );

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        } finally {

            pst.close();
        }

        return lista_Categoria;

    }
    
    
    public LinkedList<Categoria> PreencherCamposCategoria(String cat, int id_conta) throws SQLException {

        String consulta = "select * from categoria where (categoriaTipo = ? and conta_id_conta = ?)";

        PreparedStatement pst = conexao.prepareStatement(consulta);

        ResultSet rs = null;

        LinkedList<Categoria> lista_categoria = new LinkedList();

        try {
            pst.setString(1, cat);
            pst.setInt(2, id_conta);

            rs = pst.executeQuery();

            while (rs.next()) {
                 
                
                lista_categoria.add(new Categoria(
                    rs.getString("categoriaTipo"))
                    
                );
                
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        } finally {

            pst.close();
        }
        
        
        return lista_categoria;

    }
    
        
    public boolean CategoriaExiste(String cat, int id_conta) throws SQLException {

        String consulta = "select * from categoria where (categoriaTipo = ? and conta_id_conta= ?)";

        PreparedStatement pst = conexao.prepareStatement(consulta);

        ResultSet rs = null;

        try {

            pst.setString(1, cat);

            pst.setInt(2, id_conta);

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
    
}
