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
public class CartaoDebito extends Cartao{

    private long n_cartao_debito;
    private float valor_atual;
    private int id_conta;
    private long n_cartao_aux;
    
    public CartaoDebito(CartaoDebitoBuild build){
        this.n_cartao_debito = build.n_cartao_debito;
        this.valor_atual = build.valor_atual;
        this.id_conta = build.id_conta;
        this.n_cartao_aux = build.n_cartao_aux;
        this.bandeira = build.bandeira;
    }
   
    public static class CartaoDebitoBuild{
        private long n_cartao_debito;
        private float valor_atual;
        private int id_conta;
        private long n_cartao_aux;
        private String bandeira;
        
        public CartaoDebitoBuild(long numCartao){
            this.n_cartao_debito = numCartao;
        }
        
        public CartaoDebitoBuild(){}
        
         public CartaoDebitoBuild ValorAtual(float valorAtual){
             this.valor_atual = valorAtual;
             return this;
         }
         
          public CartaoDebitoBuild IdConta(int idConta){
              this.id_conta = idConta;
              return this;
          }
          
          public CartaoDebitoBuild NumCartaoAux(long numCartaoAux){
              this.n_cartao_aux = numCartaoAux;
              return this;
          }
          
           public CartaoDebitoBuild Bandeira(String bandeira){
              this.bandeira = bandeira;
              return this;
          }
           
          public CartaoDebito build(){
              return new CartaoDebito(this);
          }
        }
    /*
    public CartaoDebito() {

    }

    public CartaoDebito(long n_cartao_debito) {

        this.n_cartao_debito = n_cartao_debito;
    }

    public CartaoDebito(long n_cartao_debito, float valor_atual, String bandeira, int id_conta) {

        this.n_cartao_debito = n_cartao_debito;
        this.valor_atual = valor_atual;
        this.bandeira = bandeira;
        this.id_conta = id_conta;
    }

    public CartaoDebito(long n_cartao_debito, float valor_atual, String bandeira, int id_conta, long num_cart_aux) {

        this.n_cartao_debito = n_cartao_debito;
        this.valor_atual = valor_atual;
        this.bandeira = bandeira;
        this.id_conta = id_conta;
        this.n_cartao_aux = num_cart_aux;
    }
*/
    public int getId_conta() {
        return id_conta;
    }

    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
    }

    public long getN_cartao_debito() {
        return n_cartao_debito;
    }

    public void setN_cartao_debito(long n_cartao_debito) {
        this.n_cartao_debito = n_cartao_debito;
    }

    public float getValor_atual() {
        return valor_atual;
    }

    public void setValor_atual(float valor_atual) {
        this.valor_atual = valor_atual;
    }

    public long getN_cartao_aux() {
        return n_cartao_aux;
    }

    public void setN_cartao_aux(long n_cartao_aux) {
        this.n_cartao_aux = n_cartao_aux;
    }

    public boolean verifica_valor_atual() {

        return getValor_atual() > 0;
    }

    public boolean verifica_Bandeira_cartao_deb() {

        Cartao cartao_aux = new Cartao();

        return (cartao_aux.verifica_bandeira(bandeira));
    }
    // falta verificar o número do cartão

    public boolean verifica_num_cartao_deb() {

        Cartao cartao_aux = new Cartao();

        return (cartao_aux.ValidaNUM_Cartao(Long.toString(n_cartao_debito)));

    }

    public boolean EhVazio(String rec) {
        return rec == null || rec.trim().equals("");
    }

    public boolean UpdateEhVazio(String num_cartao, String valor, String bandeira) {

        return EhVazio(num_cartao) || EhVazio(valor) || EhVazio(bandeira);
    }
}
