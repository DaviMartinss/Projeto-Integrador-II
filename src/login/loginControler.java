/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static javafx.scene.input.KeyCode.R;
import javafx.stage.Stage;
import javax.swing.text.View;

/**
 *
 * @author acer
 */
public class loginControler implements Initializable {
    
    @FXML private Label mensagem;
    @FXML private Label legendaEntrar;
    @FXML private javafx.scene.control.Button botaoSair;
    @FXML TextField campoLogin;
    @FXML TextField campoSenha;
    
    @FXML 
    private void botaoCadastro(ActionEvent event){
        System.out.println("cadastrar");
        //findViewById(botaoCadastro).setOnClickListener(new View.OnClickListener(){
        //@override
        //public void cadastrar
        //})
    }

    @FXML
    private void botaoSair(){
        
        Stage stage = (Stage) botaoSair.getScene().getWindow();
        //System.out.println("Saindo!");
        stage.close();
    }
    
    @FXML
    private void legendaEntrar(){
        legendaEntrar.setText("Clique no botão para entrar!");
    }
    
    @FXML 
    private void botaoAjuda(ActionEvent event){
        System.out.println("Abrir material de ajuda");
    }
    
    @FXML
    private void botaoEntrar(ActionEvent event) {
        System.out.println("Clicou!");
        System.out.println(campoLogin.getText());
        System.out.println(campoSenha.getText());
        mensagem.setText("Vamos fazer um pé de meia?!");
    }
    
    @FXML
    private void loginTexto (){
        System.out.println("login!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
}
