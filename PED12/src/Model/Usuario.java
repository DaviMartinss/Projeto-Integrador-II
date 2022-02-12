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

public class Usuario {
    
    private String nome;
    private String email;
    private String senha;
    private String senhaConfirma;
    private String salvaEmail_aux;
    private int id_conta;
    
     public Usuario(UsuarioBuild build){
        this.nome = build.nome;
        this.email = build.email;
        this.senha = build.senha;
        this.senhaConfirma = build.senhaConfirma;
        this.id_conta = build.id_conta;
        
    }
   
    public static class UsuarioBuild{
        private String nome;
        private String email;
        private String senha;
        private String senhaConfirma;
        private int id_conta;
        
        public UsuarioBuild(String email){
            this.email = email;
        }
        
        public UsuarioBuild(){}
        
         public UsuarioBuild Nome(String nome){
             this.nome = nome;
             return this;
         }
         
          public UsuarioBuild Email(String email){
              this.email = email;
              return this;
          }
          
          public UsuarioBuild Senha(String senha){
              this.senha = senha;
              return this;
          }
           
          public UsuarioBuild ConfirmaSenha(String senha){
              this.senhaConfirma = senha;
              return this;
          }
          
          public UsuarioBuild IdConta(int idConta){
              this.id_conta = idConta;
              return this;
          }
          
          public Usuario build(){
              return new Usuario(this);
          }
        }
    /*
    public Usuario() {
       
    }
    
    // recupera senha
    public Usuario(String email){
        this.email = email;
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
    */
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
    
    public String getSalvaEmail_aux() {
        return salvaEmail_aux;
    }

    public void setSalvaEmail_aux(String salvaEmail_aux) {
        this.salvaEmail_aux = salvaEmail_aux;
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
        return soLetra;
    }

    
    public boolean validaTamSenha(String senha){
        return senha.length() >= 6;
    }
    
    public boolean EhVazio(String rec){
        return rec == null || rec.trim().equals("");
    }
    
    public boolean UpdateEhVazio(){
           
        return EhVazio(getNome()) || EhVazio(getEmail());
    }   
}