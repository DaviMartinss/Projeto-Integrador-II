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
public class Cartao_debito {
    public int n_cartao_debito;
    public float valor_atual;
    public String bandeira;

    public Cartao_debito(int n_cartao_debito, float valor_atual, String bandeira) {
        this.n_cartao_debito = n_cartao_debito;
        this.valor_atual = valor_atual;
        this.bandeira = bandeira;
    }

    public int getN_cartao_debito() {
        return n_cartao_debito;
    }

    public void setN_cartao_debito(int n_cartao_debito) {
        this.n_cartao_debito = n_cartao_debito;
    }

    public float getValor_atual() {
        return valor_atual;
    }

    public void setValor_atual(float valor_atual) {
        this.valor_atual = valor_atual;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }
    
    public boolean verifica_valor_atual(){
        
        if(getValor_atual() > 0){
            
            return true;
            
        }else{
            
            return  false;
        }
    }
    
    public boolean verifica_Bandeira_cartao_deb(){
        
        Cartao cartao_aux = new Cartao();
        boolean ver_bandeira;
        ver_bandeira = cartao_aux.verifica_bandeira(bandeira);
        return ver_bandeira;
    }
    // falta verificar o número do cartão
}
