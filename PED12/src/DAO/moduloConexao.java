package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pc
 */
public class moduloConexao {
    
    public static Connection conector(){
        
        java.sql.Connection conexao; 
        
        String driver = "com.mysql.cj.jdbc.Driver";  
        
<<<<<<< Updated upstream
        String url = "jdbc:mysql://127.0.0.1:3307/bd_pedemeia";         
=======
        String url = "jdbc:mysql://127.0.0.1:3306/bd_pedemeia";         
>>>>>>> Stashed changes
        String user = "root";          

        String password = "";  
  
        try{
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
                        
            return conexao;
        
        } catch(ClassNotFoundException | SQLException e){
            System.out.println("Falha ao conectar com o banco de dados " + e); 
            return null;
        }
    } 
}