
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Model.Categoria;
import Model.Despesa;
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
    
    public boolean DeleteCategoria(Categoria categoria, int id) throws SQLException {

        PreparedStatement pst = null;
       
       if(consultaCat_desData(categoria, id, ConsultaIdCategoria_despesa(categoria.getCategoria_aux(), id))){
           
        
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
        }else{
           JOptionPane.showMessageDialog(null, "Falha: Voce têm uma despesa com essa categoria");
           return false;
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
    
    
    public int GetCategoriaId(int id_conta, String categoria) throws SQLException {

        String SelectCategoriaId = "SELECT categoriaId FROM categoria WHERE (categoriaTipo = ? AND conta_id_conta = ?);";

        PreparedStatement pst_SelectCategoriaId = null;
        ResultSet rs_SelectCategoriaId = null;

        try {

            pst_SelectCategoriaId = conexao.prepareStatement(SelectCategoriaId);
            
            pst_SelectCategoriaId.setString(1, categoria);
            
            pst_SelectCategoriaId.setInt(2, id_conta);
           
            rs_SelectCategoriaId = pst_SelectCategoriaId.executeQuery();

            if (rs_SelectCategoriaId.next()) 
                return rs_SelectCategoriaId.getInt("categoriaId");
            else 
                return 0;
            

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "Erro:GetCategoriaId", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return 0;

        } finally {

            pst_SelectCategoriaId.close();
            rs_SelectCategoriaId.close();

        }

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
    
    public boolean consultaCat_desData(Categoria cat, int id, int id_apag){
        
        PreparedStatement pst1 = null;
        ResultSet rs1 = null;
        
        boolean apa = true;
        
        String consulta1 = "select categoria_id from despesa_data dt left join despesa d on dt.cod_despesa = d.despesa_data_cod_despesa where dt.conta_id_conta = ?";
        
        try{
            
            pst1= conexao.prepareStatement(consulta1);
            
            pst1.setInt(1, id);
            
            rs1 = pst1.executeQuery();
           
            while (rs1.next()) {
                
                Despesa Desp_cat_aux = new Despesa();
                
                Desp_cat_aux.setId_categoria(rs1.getInt("categoria_id"));
                
                if(id_apag == rs1.getInt("categoria_id")){
                    apa = false;
                }
            }
            
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, "Falha ao consultar as categorias da despesas");
        }
        
        if(apa){
            
            return true;
            
        }else{
            
            return false;
        }
        
    }
    
    public String GetTipoCategoria(int id_categoria, int id_conta) throws SQLException{
        
        String GetTipoCategoria = "SELECT categoriaTipo FROM categoria WHERE (categoriaId = ? AND conta_id_conta= ?)";

        PreparedStatement pst_GetTipoCategoria = null;

        ResultSet rs_GetTipoCategoria = null;

        try {
            
            pst_GetTipoCategoria = conexao.prepareStatement(GetTipoCategoria);

            pst_GetTipoCategoria.setInt(1, id_categoria);

            pst_GetTipoCategoria.setInt(2, id_conta);

            rs_GetTipoCategoria = pst_GetTipoCategoria.executeQuery();

            if (rs_GetTipoCategoria.next()) {

                return rs_GetTipoCategoria.getString("categoriaTipo");
            } 

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            JOptionPane.showMessageDialog(null, "Erro:GetTipoCategoria", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
        }
        finally
        {
            pst_GetTipoCategoria.close();
            rs_GetTipoCategoria.close();
        }
        
        return null;
    }
    
   
    
}
