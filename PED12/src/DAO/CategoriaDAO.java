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

    // Criar Classe
    public CategoriaDAO() {
        conexao = moduloConexao.conector();
    }

    public void InsertCategoria(Categoria categoria) throws SQLException {

        PreparedStatement pst;

        String insert = "INSERT INTO categoria (categoriaTipo, conta_id_conta) VALUES (?, ?);";

        pst = conexao.prepareStatement(insert);

        pst.setString(1, categoria.getCategoriaTipo().toUpperCase());
        pst.setInt(2, categoria.getId_conta());

        pst.executeUpdate();

        pst.close();
    }
    
    public void UpdateCategoria(Categoria categoria) throws SQLException {

        PreparedStatement pst;

        String update = "UPDATE categoria SET categoriaTipo=? WHERE categoriaTipo = ?;";

        pst = conexao.prepareStatement(update);

        pst.setString(1, categoria.getCategoriaTipo().toUpperCase());
        pst.setString(2, categoria.getCategoria_aux());

        pst.executeUpdate();

        pst.close();

    }

    public void DeleteCategoria(Categoria categoria, int id) throws SQLException {

        PreparedStatement pst;

        String delete = "DELETE FROM categoria WHERE categoriaTipo = ?";

        pst = conexao.prepareStatement(delete);

        pst.setString(1, categoria.getCategoria_aux());

        pst.executeUpdate();

        pst.close();

    }

    public LinkedList<Categoria> ConsultaCategoria(String arg, int id_conta, boolean ordenar) throws SQLException {

        String argumento = "";

        PreparedStatement pst;
        ResultSet rs;

        LinkedList<Categoria> lista_categoria = new LinkedList();
        
        argumento = " AND categoriaTipo" + " " + "LIKE '" + arg + "%'";
          
        String consulta = "SELECT categoriaTipo FROM categoria WHERE categoria.conta_id_conta = ?"
                + argumento + "";

        pst = conexao.prepareStatement(consulta);

        pst.setInt(1, id_conta);

        rs = pst.executeQuery();

        while (rs.next()) {

            lista_categoria.add(new Categoria(
                    rs.getString("categoriaTipo"),
                    id_conta)
            );
        }

        pst.close();
        rs.close();

        return lista_categoria;
    }

    public int GetCategoriaId(int id_conta, String categoria) throws SQLException {

        PreparedStatement pst_SelectCategoriaId;
        ResultSet rs_SelectCategoriaId;
        
        int id_categoria;

        String SelectCategoriaId = "SELECT categoriaId FROM categoria WHERE (categoriaTipo = ? AND conta_id_conta = ?);";

        pst_SelectCategoriaId = conexao.prepareStatement(SelectCategoriaId);

        pst_SelectCategoriaId.setString(1, categoria);

        pst_SelectCategoriaId.setInt(2, id_conta);

        rs_SelectCategoriaId = pst_SelectCategoriaId.executeQuery();

        if (rs_SelectCategoriaId.next()) 
            id_categoria = rs_SelectCategoriaId.getInt("categoriaId");
        else 
            return -1;//DISPARAR EXCEPTION
        
        pst_SelectCategoriaId.close();
        rs_SelectCategoriaId.close();
        
        return id_categoria;
    }

//    // busca o id de uma categoria pelo o id da despesa
//    public int GetDespesaCategoria(String cat, int id_conta) throws SQLException {
//
//        PreparedStatement pst = null;
//        ResultSet rs = null;
//        String consulta = "SELECT categoriaId FROM categoria WHERE (categoriaTipo = ? AND conta_id_conta = ?)";
//
//        pst = conexao.prepareStatement(consulta);
//
//        try {
//
//            pst.setString(1, cat);
//            pst.setInt(2, id_conta);
//
//            rs = pst.executeQuery();
//
//            if (rs.next()) {
//
//                return rs.getInt(1);
//
//            } else {
//
//                return -1;
//            }
//
//        } catch (Exception e) {
//
//            JOptionPane.showMessageDialog(null, e.getMessage());
//
//            return -1;
//
//        } finally {
//
//            pst.close();
//        }
//
//    }

    // parte para ser modificada
    public LinkedList<Categoria> GetListaCategoria(int id_conta) throws SQLException {

        PreparedStatement pst;
        ResultSet rs;

        LinkedList<Categoria> lista_Categoria = new LinkedList<>();

        String consulta = "SELECT * FROM categoria WHERE conta_id_conta = ?";

        pst = conexao.prepareStatement(consulta);

        pst.setInt(1, id_conta);

        rs = pst.executeQuery();

        while (rs.next()) {

            lista_Categoria.add(new Categoria(
                    rs.getString("categoriaTipo"))
            );
        }

        pst.close();
        rs.close();

        return lista_Categoria;
    }

    public boolean CategoriaExiste(String categoria, int id_conta) throws SQLException {

        PreparedStatement pst;
        ResultSet rs;

        String consulta = "SELECT * FROM categoria WHERE (categoriaTipo = ? AND conta_id_conta= ?)";

        pst = conexao.prepareStatement(consulta);

        pst.setString(1, categoria);

        pst.setInt(2, id_conta);

        rs = pst.executeQuery();
        
        boolean retorno  = rs.next();

        pst.close();
        rs.close();
        
        return retorno;
    }
    
    public boolean CategoriaDespesaExiste(String categoria, int id_conta) throws SQLException{
  
        PreparedStatement pst;
        ResultSet rs;

        String consulta = "SELECT C.categoriaTipo \n"
                        + "FROM despesa D INNER JOIN categoria C ON D.categoria_id = C.categoriaId\n"
                        + "WHERE C.categoriaTipo = ? AND C.conta_id_conta = ? AND D.conta_id_conta = ?";

        pst = conexao.prepareStatement(consulta);

        pst.setString(1, categoria);
        pst.setInt(2, id_conta);
        pst.setInt(3, id_conta);

        rs = pst.executeQuery();

        boolean retorno = rs.next();
        
        pst.close();
        rs.close();
        
        return retorno;        
    }

    public boolean GetCategoriaData(Categoria cat, int id, int id_apag) {

        PreparedStatement pst1;
        ResultSet rs1;

        boolean apa = true;

        String consulta1 = "SELECT categoria_id FROM despesa_data dt left join despesa d on dt.cod_despesa = d.despesa_data_cod_despesa where dt.conta_id_conta = ?";

        try {

            pst1 = conexao.prepareStatement(consulta1);

            pst1.setInt(1, id);

            rs1 = pst1.executeQuery();

            while (rs1.next()) {

                Despesa Desp_cat_aux = new Despesa();

                Desp_cat_aux.setId_categoria(rs1.getInt("categoria_id"));

                if (id_apag == rs1.getInt("categoria_id")) {
                    apa = false;
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Falha ao consultar as categorias da despesas");
        }

        if (apa) {

            return true;

        } else {

            return false;
        }

    }

    public String GetTipoCategoria(int id_categoria, int id_conta) throws SQLException {

        PreparedStatement pst_GetTipoCategoria;
        ResultSet rs_GetTipoCategoria;

        String categoria = "";

        String GetTipoCategoria = "SELECT categoriaTipo FROM categoria WHERE (categoriaId = ? AND conta_id_conta= ?)";

        pst_GetTipoCategoria = conexao.prepareStatement(GetTipoCategoria);

        pst_GetTipoCategoria.setInt(1, id_categoria);
        pst_GetTipoCategoria.setInt(2, id_conta);

        rs_GetTipoCategoria = pst_GetTipoCategoria.executeQuery();

        if (rs_GetTipoCategoria.next()) {

            categoria = rs_GetTipoCategoria.getString("categoriaTipo");
        } else 
        {
            //DISPARAR EXCEPTION
        }
        
        pst_GetTipoCategoria.close();
        rs_GetTipoCategoria.close();

        return categoria;
    }
    
    /*
    public boolean CadastrarCategoria(Categoria categoria) throws SQLException {

        Categoria categoria_aux = new Categoria();

        if (!(categoria_aux.valorEhVazio(categoria.getCategoriaTipo()))) {

            if (!(CategoriaExiste(categoria.getCategoriaTipo(), categoria.getId_conta()))) {
                PreparedStatement pst = null;

                String insert = "INSERT INTO categoria (categoriaTipo, conta_id_conta) VALUES (?, ?);";

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
            } else {
                JOptionPane.showMessageDialog(null, "Essa categoria já foi cadastrada");

                return false;
            }
        } else {

            JOptionPane.showMessageDialog(null, "Nenhum campo pode ser nulo");

            return false;
        }
    }
    
    
    
    
    */
}
