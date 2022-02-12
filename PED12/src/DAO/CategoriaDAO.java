package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public void InsertCategoria(Categoria categoria) throws SQLException, NullPointerException {

        PreparedStatement pst;

        String insert = "INSERT INTO categoria (categoriaTipo, conta_id_conta) VALUES (?, ?);";

        pst = conexao.prepareStatement(insert);

        pst.setString(1, categoria.getCategoriaTipo().toUpperCase());
        pst.setInt(2, categoria.getId_conta());

        pst.executeUpdate();

        pst.close();
    }
    
    public void UpdateCategoria(Categoria categoria) throws SQLException, NullPointerException {

        PreparedStatement pst;

        String update = "UPDATE categoria SET categoriaTipo=? WHERE categoriaId = ? AND conta_id_conta = ?;";

        pst = conexao.prepareStatement(update);

        pst.setString(1, categoria.getCategoriaTipo().toUpperCase());
        pst.setInt(2, categoria.getCategoriaId());
        pst.setInt(3, categoria.getId_conta());

        pst.executeUpdate();

        pst.close();

    }

    public void DeleteCategoria(int categoria_id, int conta_id) throws SQLException {

        PreparedStatement pst;

        String delete = "DELETE FROM categoria WHERE categoriaId = ? AND conta_id_conta = ?";

        pst = conexao.prepareStatement(delete);

        pst.setInt(1, categoria_id);
        pst.setInt(2, conta_id);

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
            /*
            lista_categoria.add(new Categoria(
                    rs.getString("categoriaTipo"),
                    id_conta)
            );
            */
             lista_categoria.add(new Categoria.CategoriaBuild()
                    .CategoriaTipo(rs.getString("categoriaTipo"))
                    .CategiaId(id_conta)
                    .build()
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

        String SelectCategoriaId = "SELECT categoriaId FROM categoria WHERE "
                                 + "(categoriaTipo = ? AND conta_id_conta = ?);";

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
            /*
            lista_Categoria.add(new Categoria(
                    rs.getString("categoriaTipo"))
            );
            */
             lista_Categoria.add(new Categoria.CategoriaBuild()
                     .CategoriaTipo(rs.getString("categoriaTipo")).build());
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

    public boolean GetCategoriaData(Categoria cat, int id, int id_apag) throws SQLException {

        PreparedStatement pst1;
        ResultSet rs1;

        boolean apa = true;

        String consulta1 = "SELECT categoria_id FROM despesa_data dt left join despesa d on dt.cod_despesa = d.despesa_data_cod_despesa where dt.conta_id_conta = ?";

        pst1 = conexao.prepareStatement(consulta1);

        pst1.setInt(1, id);

        rs1 = pst1.executeQuery();

        while (rs1.next()) {

            Despesa Desp_cat_aux = new Despesa.DespesaBuild().build();

            Desp_cat_aux.setId_categoria(rs1.getInt("categoria_id"));

            if (id_apag == rs1.getInt("categoria_id")) {
                apa = false;
            }
        }

        return apa;

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
    
}
