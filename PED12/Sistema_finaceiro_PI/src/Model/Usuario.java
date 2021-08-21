/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author pc
 */

    
import com.mysql.cj.util.StringUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Usuario {
    
    private String nome;
    private String email;
    private String senha;
    private String senhaConfirma;

    
    private int id_conta;
    
    
    public Usuario() {
       
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
    
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String senha, String senhaConfirma, int id_conta) {
        this.senha = senha;
        this.senhaConfirma = senhaConfirma;
        this.id_conta = id_conta;
    }
    
    public Usuario(int id_conta, String nome, String email) {
        this.id_conta = id_conta;
        this.nome = nome;
        this.email = email;
        
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    /**
     * @return the nome
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the id_conta
     */
    public int getId_conta() {
        return id_conta;
    }
    
    public String getSenhaConfirma() {
        return senhaConfirma;
    }

    public void setSenhaConfirma(String senhaConfirma) {
        this.senhaConfirma = senhaConfirma;
    }

    /**
     * @param id_conta the id_conta to set
     */
    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
    }
    
   public boolean validaNomeUser(String nome){
       
        String nomeAux = nome.replace(" ", "");
        
        boolean soLetra = true;
        
        for(int i = 0; i < nomeAux.length(); i++){
            char aux = nomeAux.charAt(i);
            if(!(Character.isLetter(aux))){
                soLetra = false;
            }
        }
        if(!(soLetra)){
           return false;
        }
        
        return true;
    }

    
    public boolean validaTamSenha(String senha){
        if(senha.length() >= 6){
            
            return  true;
            
        }else{
            
            return  false;
        }
    }
    
}
