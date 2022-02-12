/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Utilities.Validacao;
/**
 *
 * @author pc
 */
public class Data{
    
    private int dia;
    private int mes;
    private int ano;
    
     public Data(DataBuild build){
        this.dia = build.dia;
        this.mes = build.mes;
        this.ano = build.ano;
    }
   
    public static class DataBuild{
        private int dia;
        private int mes;
        private int ano;
        
        public DataBuild(int mes, int ano){
            this.mes = mes;
            this.ano = ano;
        }
        
        public DataBuild(){}
        
         public DataBuild Dia(int dia){
             this.dia = dia;
             return this;
         }
         
          public DataBuild Mes(int mes){
              this.mes = mes;
              return this;
          }
          
          public DataBuild Ano(int ano){
              this.ano = ano;
              return this;
          }
           
          public Data build(){
              return new Data(this);
          }
        }
    
    /*
    public Data() {
    
    }
    
     public Data(int mes, int ano) {
        this.mes = mes;
        this.ano = ano;
    }

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }
    */
    
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
    
    public boolean verifica_dia(){
        
        if(getDia() > 0 && getDia() <= 31){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean verifica_mes(){
        if (getMes() > 0 && getMes() <= 12) {
            return true;
        } else {
            return false;
        }
    }
    public boolean verifica_ano(){
        if (getAno() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    
    public boolean verifica_data(){
        
        
        if(verifica_dia() && verifica_mes() && verifica_ano()){
            return  true;
        }else{
            return false;
        }
    }
    
    public boolean diaEhVazio(String dia){
        if(dia == null || dia.trim().equals("")){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean MesEhVazio(String Mes){
        if(Mes == null || Mes.trim().equals("")){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean AnoEhVazio(String ano){
        if(ano == null || ano.trim().equals("")){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean dataEhVazia(String dia, String mes, String ano){
        if(diaEhVazio(dia) || MesEhVazio(mes) || AnoEhVazio(ano)){
            return  true;
        }else{
            return false;
        }
    }
}
