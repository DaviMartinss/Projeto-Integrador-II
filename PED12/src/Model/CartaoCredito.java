/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Utilities.Validacao;

/**
 *
 * @author DAVI
 */

public class CartaoCredito extends Cartao{
    
    private float limite;
    private int dia_fatura;
    private long n_cartao_credito;
    private float valor_fatura;
    private int id_conta;
    private long  n_cartao_aux;
    private float credito;

    public CartaoCredito(CartaoCreditoBuild build){
        this.limite = build.limite;
        this.dia_fatura = build.dia_fatura;
        this.n_cartao_credito = build.n_cartao_credito;
        this.valor_fatura = build.valor_fatura;
        this.bandeira = build.bandeira;
        this.id_conta = build.id_conta;
        this.n_cartao_aux = build.n_cartao_aux;
        this.credito = build.credito;
        
    }
   
    public static class CartaoCreditoBuild{
        
        private float limite;
        private int dia_fatura;
        private long n_cartao_credito;
        private float valor_fatura;
        private String bandeira;
        private int id_conta;
        private long  n_cartao_aux;
        private float credito;
        // obrigatórios
        
        public CartaoCreditoBuild(long numCartao){
            this.n_cartao_credito = numCartao;
        }
        
        public CartaoCreditoBuild(){}
        
        public CartaoCreditoBuild Limite(float limite){
            this.limite = limite;
            return this;
        }
         
        public CartaoCreditoBuild DiaFatura(int diaFatura){
            this.dia_fatura = diaFatura;
            return this;
        }
          
        public CartaoCreditoBuild ValorFatura(float valorFatura){
            this.valor_fatura = valorFatura;
            return this;
        }
          
        public CartaoCreditoBuild Bandeira(String bandeira){
            this.bandeira = bandeira;
            return this;
        }
          
        public CartaoCreditoBuild IdConta(int idConta){
            this.id_conta = idConta;
            return this;
        }
        
        public CartaoCreditoBuild NumCartaoAux(long NumCartaoAux){
            this.n_cartao_aux = NumCartaoAux;
            return this;
        }
        
        public CartaoCreditoBuild Credito(float credito){
            this.credito = credito;
            return this;
        }
           
        public CartaoCredito build(){
            return new CartaoCredito(this);
        }
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
         
        Data dia_aux = new Data.DataBuild().build();
        
        dia_aux.setDia(getDia_fatura());
        
        return dia_aux.verifica_dia();
        
    }
     
    public boolean verifica_limite(){
        return getLimite() > 0;
    }
    
    public boolean varifica_valor_fatura(){
         
        return getValor_fatura() >= 0 && getValor_fatura() <= getLimite();
    }
     
    public boolean verifica_cartaoCreditoValido(){
          
        return varifica_valor_fatura() && verifica_bandeira() && verifica_limite() && verifica_dia_fatura();
              
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
            
            return verifica_cartaoCreditoValido();
            
        }else{
            return false;
        }
    }
}