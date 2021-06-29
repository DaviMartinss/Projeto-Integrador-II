//Nome do nosso pacote //

package bd_conexao;

import java.sql.Connection;
import java.sql.DriverManager;


//Início da classe de conexão//

public class ModeloConexao {

    public static Connection conector(){
        
        Connection conexao = null; 
        String driver = "com.mysql.cj.jdbc.Driver";          
        String url = "jdbc:mysql://localhost:3306/bd_pedemeia";          
        String user = "root";          
        String password = "admin";          
       
        try{
   
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        
        } catch(Exception e){
    
            //System.out.println(e);
            return null;
        }
        

    } 
    
    
   
}
