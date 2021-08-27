/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ValidacaoComum;

import DAO.moduloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class ConsultaDAO {
     private Connection conexao = null;

    public ConsultaDAO() {

        conexao = moduloConexao.conector();
    }
    
    public boolean CartaoCreditoExiste(long num_cartao) throws SQLException {

        String consulta = "select n_cartao_credito from cartao_credito where n_cartao_credito = ?";

        PreparedStatement pst = conexao.prepareStatement(consulta);

        ResultSet rs = null;

        try {

            pst.setLong(1, num_cartao);


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
    
    public boolean CartaoDebitoExiste(long num_cartao) throws SQLException {

        String consulta = "select n_cartao_debito from cartao_debito where n_cartao_debito = ?";

        PreparedStatement pst = conexao.prepareStatement(consulta);

        ResultSet rs = null;

        try {

            pst.setLong(1, num_cartao);


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
