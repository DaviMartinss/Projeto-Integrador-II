/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author DAVI
 */
public class Cartao {

    public String bandeira;
    public String n_cartao;

    public Cartao() {

    }

    public Cartao(String bandeira, String n_cartao) {
        this.bandeira = bandeira;
        this.n_cartao = n_cartao;
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

    public boolean verifica_bandeira() {

        String bandeira_aux = this.bandeira.replace(" ", "");

        boolean soLetra = true;

        for (int i = 0; i < bandeira_aux.length(); i++) {

            char aux = bandeira_aux.charAt(i);

            if (!(Character.isLetter(aux))) {
                soLetra = false;
            }
        }

        return soLetra;
    }

    private int somaDigitos(int numero) {
            if( numero < 9 ) {
                    return numero;
            } else {
                    return numero%10 + 1;
            }
    }

    //Utilizando o algoritmo de LUHN para validar o número dos cartões
    public boolean ValidarN_Cartao(String numeroCartao) {

        int somaPar = 0;
        int somaImpar = 0;
        int aux = 0;

        //PARES
        for (int j = numeroCartao.length() - 2; j >= 0; j = j - 2) {
            aux = Integer.parseInt(numeroCartao.charAt(j) + "");
            somaPar = somaPar + somaDigitos(aux * 2);
        }

        //IMPARES
        for (int i = numeroCartao.length() - 1; i >= 0; i = i - 2) {
            aux = Integer.parseInt(numeroCartao.charAt(i) + "");
            somaImpar = somaImpar + aux;
        }

        return (somaPar + somaImpar) % 10 == 0;

    }
}