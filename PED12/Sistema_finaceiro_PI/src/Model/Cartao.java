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
public class Cartao {
    public String bandeira;
    public String n_cartao;

    public Cartao() {
    
    }
    
    
    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getN_cartao() {
        return n_cartao;
    }

    public void setN_cartao(String n_cartao) {
        this.n_cartao = n_cartao;
    }
    
     public boolean  verifica_bandeira(String bandeira){
                 
        int cont = 0;
        for (int i = 0; i < bandeira.length(); i++) {
            char aux = bandeira.charAt(i);
                if (!(Character.isLetter(aux))) {
                    cont++;
            }
        }
        
        if (cont != 0) {
            System.out.println("Nome da bandeira inválido - o nome deve conter apenas letras");
            return  false;

        } else {
             return  true;
        }
    }
     
    // falta a função de verificar o número do cartão de credito
    // verfica apenas se é a String é numero e se o tamnho é valido
     
     public boolean verifica_cartao(){
        String numCartao = getN_cartao();
        int tam = numCartao.length();
        if(tam >= 13 && tam <= 16){
            
            boolean isNumeric = true;
            
            for(int  i= 0; i <numCartao.length(); i++){
                if(!Character.isDigit(numCartao.charAt(i))){
                    isNumeric= false;
                }
            }
            if(isNumeric){
                
                return  true;
            }else{
                
                return  false;
            }
        }else{
            
            return  false;
        }
        
     }
}
