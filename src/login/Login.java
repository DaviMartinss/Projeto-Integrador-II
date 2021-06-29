/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import bd_conexao.ModeloConexao;

/**
 *
 * @author acer
 */
public class Login extends Application {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        conexao = ModeloConexao.conector();
        
        System.out.println(conexao);
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLped12.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
