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
    private int Salva_Mes;
    private int Salva_ano;
    private int cod_receita;
    
    public Receita() {
        
    }

    public Receita(int dia, int mes, int ano, float total, int id_conta) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.total = total;
        this.id_conta = id_conta;
    }

    public Receita(int id_conta, int mes, int ano ) {
        this.id_conta = id_conta;
        this.mes = mes;
        this.ano = ano;
        
    }
    
    
    
    public Receita(int dia, int mes, int ano, float total, int id_conta, int Salva_Mes, int Salva_ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.total = total;
        this.id_conta = id_conta;
        this.Salva_Mes = Salva_Mes;
        this.Salva_ano = Salva_ano;
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
    
    public int getSalva_Mes() {
        return Salva_Mes;
    }

    public void setSalva_Mes(int Salva_Mes) {
        this.Salva_Mes = Salva_Mes;
    }

    public int getSalva_ano() {
        return Salva_ano;
    }

    public void setSalva_ano(int Salva_ano) {
        this.Salva_ano = Salva_ano;
    }
    
    public int getCod_receita() {
        return cod_receita;
    }

    public void setCod_receita(int cod_receita) {
        this.cod_receita = cod_receita;
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
    
     public boolean valorEhVazio(String valor){
         if(valor == null || valor.trim().equals("")){
            return true;
        }else{
            return false;
        }
    }
     
    public boolean UpdateEhVazio(String dia, String mes, String ano, String valor){
        Data data_aux = new Data();
        
        if(data_aux.dataEhVazia(dia, mes, ano) || valorEhVazio(valor)){
            return true;
        }else{
            return false;
        }
    }

}
