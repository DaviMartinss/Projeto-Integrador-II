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
public class Receita {
     public  int dia;
    public int mes;
    public int ano;
    public float total;

    public Receita(int dia, int mes, int ano, float total) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.total = total;
    }
    public Receita() {
        
    }
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    public boolean verifica_total(){
        if (getTotal() > 0) {
            return  true;
        } else {
            return false;
        }
    }
    
    public boolean verifica_ReceitaValida(){
        Data data_aux = new Data(dia, mes, ano);
        if( (data_aux.verifica_dia()) && (data_aux.verifica_mes()) && (data_aux.verifica_ano()) && verifica_total()){
            return true;
        }else{
            return false;
        }
    }
}
