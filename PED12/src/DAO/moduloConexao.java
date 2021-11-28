package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author pc
 */
public class moduloConexao {
    
    public static Connection conector(){
        
        java.sql.Connection conexao = null; 
        
        String driver = "com.mysql.cj.jdbc.Driver";  
        
        String url = "jdbc:mysql://localhost:3306/bd_pedemeia";          
        String user = "root";          

        String password = "14011991";  
  
        try{
   
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
                        
            return conexao;
        
        } catch(Exception e){
            System.out.println("Falha ao conectar com o banco de dados " + e); 
            return null;
        }
    } 
}
