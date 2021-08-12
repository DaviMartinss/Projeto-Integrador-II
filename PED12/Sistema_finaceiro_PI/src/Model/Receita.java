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
    
    private int dia;
    private int mes;
    private int ano;
    private float total;
    private int id_conta;
    
    public Receita() {
        
    }

    public Receita(int dia, int mes, int ano, float total, int id_conta) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.total = total;
        this.id_conta = id_conta;
    }

    public int getId_conta() {
        return id_conta;
    }

    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
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
        
        if(data_aux.verifica_data()){
            return true;
        }else{
            return false;
        }
    }
}