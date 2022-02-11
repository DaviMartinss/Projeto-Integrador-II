/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Utilities.Validacao;

/**
 *
 * @author pc
 */

public class CartaoCredito extends Cartao{
    
    private float limite;
    private int dia_fatura;
    private long n_cartao_credito;
    private float valor_fatura;
//    private String bandeira;
    private int id_conta;
    private long  n_cartao_aux;
    private float credito;

    
    
    public CartaoCredito() {
        
    }
    
    public CartaoCredito(long n_cartao_credito) {
        
        this.n_cartao_credito = n_cartao_credito;
        
    }

    public CartaoCredito(long n_cartao_credito, int dia_fatura,  float valor_fatura, String bandeira, int id_conta) {
        
        this.dia_fatura = dia_fatura;
        this.n_cartao_credito = n_cartao_credito;
        this.valor_fatura = valor_fatura;
        this.bandeira = bandeira;
        this.id_conta = id_conta;
    }
    
    
    
     public CartaoCredito(long n_cartao_credito, float limite, int dia_fatura, float valor_fatura, String bandeira, int id_conta) {
        
        this.n_cartao_credito = n_cartao_credito;
        this.limite = limite;
        this.dia_fatura = dia_fatura;
        this.valor_fatura = valor_fatura;
        this.bandeira = bandeira;
        this.id_conta = id_conta;
    }
    
    
    public CartaoCredito(long n_cartao_credito, float limite, float credito, int dia_fatura, float valor_fatura, String bandeira, int id_conta) {
        
        this.n_cartao_credito = n_cartao_credito;
        this.limite = limite;
        this.credito = credito;
        this.dia_fatura = dia_fatura;
        this.valor_fatura = valor_fatura;
        this.bandeira = bandeira;
        this.id_conta = id_conta;
    }
    
    
    public CartaoCredito(long n_cartao_credito, float limite, int dia_fatura, float valor_fatura, String bandeira, int id_conta, long n_aux) {
        
        this.n_cartao_credito = n_cartao_credito;
        this.limite = limite;
        this.dia_fatura = dia_fatura;
        this.valor_fatura = valor_fatura;
        this.bandeira = bandeira;
        this.id_conta = id_conta;
        this.n_cartao_aux = n_aux;
    }

    public float getCredito() {
        return credito;
    }

    public void setCredito(float credito) {
        this.credito = credito;
    }
    
    public long getN_cartao_credito() {
        return n_cartao_credito;
    }

    public void setN_cartao_credito(long n_cartao_credito) {
        this.n_cartao_credito = n_cartao_credito;
    }

    public int getId_conta() {
        return id_conta;
    }

    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
    }
    
    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    public int getDia_fatura() {
        return dia_fatura;
    }

    public void setDia_fatura(int dia_fatura) {
        this.dia_fatura = dia_fatura;
    }

    public float getValor_fatura() {
        return valor_fatura;
    }

    public void setValor_fatura(float valor_fatura) {
        this.valor_fatura = valor_fatura;
    }

    public long getN_cartao_aux() {
        return n_cartao_aux;
    }

    public void setN_cartao_aux(long n_cartao_aux) {
        this.n_cartao_aux = n_cartao_aux;
    }
    
    
     public boolean verifica_dia_fatura(){
         
        Data dia_aux = new Data();
        
        dia_aux.setDia(getDia_fatura());
        
        if(dia_aux.verifica_dia()){
            return  true;
            
        }else{
            
            return false;
        }
        
    }
     
     public boolean verifica_limite(){
         if (getLimite() > 0) {
             
             return true;
             
         } else {
             
             return false;
         }
     }
     public boolean varifica_valor_fatura(){
         
         if(getValor_fatura() >= 0 && getValor_fatura() <= getLimite()){
             return true;
         }else{
             return false;
         }
     }
     
     public boolean  verifica_bandeira_credito(){
        
         Cartao cartao_aux = new Cartao();
         
        return (cartao_aux.verifica_bandeira(bandeira));
       
    }         
     
////   // falta verificar o número do cartão
//     public boolean  verifica_num_cartao_credito(){
//                
//        return (ValidaNUM_Cartao(Long.toString(n_cartao_credito)));
//       
//    }  
//     
     public boolean EhVazio(String rec){
        if(rec == null || rec.trim().equals("")){
            return true;
        }else{
            return false;
        }
    }
    
       
      public boolean UpdateEhVazio(String num_cartao, String valor, String limite, String bandeira, String dia){
        
        
       if(EhVazio(num_cartao) || EhVazio(valor) || EhVazio(limite) || EhVazio(bandeira) || EhVazio(dia)){
           
           return true;
           
       }else{
           return  false;
       }
    }
      
    public boolean verifica_cartaoCreditoValido(){
          
          if(varifica_valor_fatura() && verifica_bandeira_credito() && verifica_limite() && verifica_dia_fatura()){
              return true;
          }else{
              return false;
          }
              
      }  
      
      public boolean Update_CamposValidos(String valorFat, String bandeira, String limite, String diaFat){
        Validacao valida = new Validacao();
        
        float valorFat_aux = valida.converteParaFloat(valorFat);
        float limite_aux  = valida.converteParaFloat(limite);
        int dia_aux = valida.converteParaInt(diaFat);
        
        if(valorFat_aux != -1 && limite_aux != -1 && dia_aux != -1 ){
            setValor_fatura(valorFat_aux);
            setLimite(limite_aux);
            setDia_fatura(dia_aux);
            setBandeira(bandeira);
            
            if(verifica_cartaoCreditoValido()){
                return true;
            }else{
                return false;
            }
            
        }else{
            return false;
        }
    }
      
}
