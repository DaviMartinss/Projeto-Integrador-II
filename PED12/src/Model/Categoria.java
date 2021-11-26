/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Alan
 */
public class Categoria {

     private int CategiaId;
    private String CategoriaTipo;
    private int id_conta;
    private String Categoria_aux;
    
    
    public Categoria(){
    
    }
    
    public Categoria(String nome, int idConta){
        this.CategoriaTipo = nome;
        this.id_conta = idConta;
    }
    
   
    public Categoria(int CategiaId, String CategoriaTipo) {
        this.CategiaId = CategiaId;
        this.CategoriaTipo = CategoriaTipo;
    }

    
    public Categoria(String Categoria_aux) {
        
        this.Categoria_aux = Categoria_aux;
        
    }

    public Categoria(String CategoriaTipo, String Categoria_aux, int id_conta) {
        this.CategoriaTipo = CategoriaTipo;
        this.Categoria_aux = Categoria_aux;
        this.id_conta = id_conta;
    }
    
   
    public int getCategiaId() {
        return CategiaId;
    }

    public void setCategiaId(int CategiaId) {
        this.CategiaId = CategiaId;
    }

    public String getCategoriaTipo() {
        return CategoriaTipo;
    }

    public void setCategoriaTipo(String CategoriaTipo) {
        this.CategoriaTipo = CategoriaTipo;
    }
    
    public int getId_conta() {
        return id_conta;
    }

    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
    }
    
    
    public String getCategoria_aux() {
        return Categoria_aux;
    }

    public void setCategoria_aux(String Categoria_aux) {
        this.Categoria_aux = Categoria_aux;
    }
    
    
    public boolean valorEhVazio(String valor){
         if(valor == null || valor.trim().equals("")){
            return true;
        }else{
            return false;
        }
    }
}
