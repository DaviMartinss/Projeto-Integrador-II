/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.dal;

/**
 *
 * @author pc
 */
public class Cartao_credito {
    public int limite;
    public int dia_fatura;
    public int valor_fatura;
    public String bandeira;

    public Cartao_credito(int limite, int dia_fatura, String bandeira) {
        this.limite = limite;
        this.dia_fatura = dia_fatura;
        //this.valor_fatura = valor_fatura;
        this.bandeira = bandeira;
    }
    
    public Cartao_credito(){
        
    }
    
    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public int getDia_fatura() {
        return dia_fatura;
    }

    public void setDia_fatura(int dia_fatura) {
        this.dia_fatura = dia_fatura;
    }

    public int getValor_fatura() {
        return valor_fatura;
    }

    public void setValor_fatura(int valor_fatura) {
        this.valor_fatura = valor_fatura;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }
    
     public boolean verifica_dia_fatura(){
         
        Data dia_aux = new Data();
        
        dia_aux.setDia(getDia_fatura());
        if(dia_aux.verifica_dia()){
            return  true;
        }else{
            return false;
        }
         /*
        if(getDia_fatura() > 0 && getDia_fatura() <= 31){
            
            return true;
            
        }else{
            
            return false;
        }
*/
    }
     
     public boolean verifica_limite(){
         if (getLimite() > 0) {
             return true;
         } else {
             return false;
         }
     }
     public boolean varifica_valor_fatura(){
         
         if(getValor_fatura() > 0 && getValor_fatura() < getLimite()){
             return true;
         }else{
             return false;
         }
     }
     
     public boolean  verifica_bandeira_credito(){
        
         Cartao cartao_aux = new Cartao();
         
       boolean ver_bandeira;
       ver_bandeira = cartao_aux.verifica_bandeira(bandeira);
       return ver_bandeira;
       
    }         
   // falta verificar o número do cartão
}
