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
public class CartaoCredito {
    
    private float limite;
    private int dia_fatura;
    private long n_cartao_credito;
    private float valor_fatura;
    private String bandeira;
    private int id_conta;
    
    public CartaoCredito() {
        
    }

    public CartaoCredito(long n_cartao_credito, float limite, int dia_fatura, float valor_fatura, String bandeira, int id_conta) {
        
        this.n_cartao_credito = n_cartao_credito;
        this.limite = limite;
        this.dia_fatura = dia_fatura;
        this.valor_fatura = valor_fatura;
        this.bandeira = bandeira;
        this.id_conta = id_conta;
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
