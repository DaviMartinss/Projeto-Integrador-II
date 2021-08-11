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
public class CartaoDebito {
    
    private long n_cartao_debito;
    private float valor_atual;
    private String bandeira;
    private int id_conta;
    
    public CartaoDebito() {
        
    }

    public CartaoDebito(long n_cartao_debito, float valor_atual, String bandeira, int id_conta) {
        
        this.n_cartao_debito = n_cartao_debito;
        this.valor_atual = valor_atual;
        this.bandeira = bandeira;
        this.id_conta = id_conta;
    }

    public int getId_conta() {
        return id_conta;
    }

    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
    }

    public long getN_cartao_debito() {
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
