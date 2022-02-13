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

public class Receita{
    
    //MUDAR O NOME DOS MÉTODOS DE VÁLIDAÇÃO E MELHORAR A LÓGICA ENVOLVIDA
    
    private int dia;
    private int mes;
    private int ano;
    private float total;
    private int id_conta;
    private int Salva_Mes;
    private int Salva_ano;
    private int cod_receita;
    
    public Receita(ReceitaBuild build){
        this.dia = build.dia;
        this.mes = build.mes;
        this.ano = build.ano;
        this.total = build.total;
        this.id_conta = build.id_conta;
        this.Salva_Mes = build.Salva_Mes;
        this.Salva_ano = build.Salva_ano;
        this.cod_receita = build.cod_receita;
    }
   
    public static class ReceitaBuild{
        private int dia;
        private int mes;
        private int ano;
        private float total;
        private int id_conta;
        private int Salva_Mes;
        private int Salva_ano;
        private int cod_receita;
        
        // obrigatórios
        public ReceitaBuild(int idConta, int mes, int ano){
            this.id_conta = idConta;
            this.mes = mes;
            this.ano = ano;
        }
        
        public ReceitaBuild(){}
        
        public ReceitaBuild Dia(int dia){
           this.dia = dia;
           return this;
        }
         
        public ReceitaBuild Mes(int mes){
            this.mes = mes;
            return this;
        }
          
        public ReceitaBuild Ano(int ano){
            this.ano = ano;
            return this;
        }
        
        public ReceitaBuild Total(float total){
            this.total = total;
            return this;
        }
        
        public ReceitaBuild IdConta(int id_conta){
            this.id_conta = id_conta;
            return this;
        }
        
        public ReceitaBuild SalvaMes(int salva_Mes){
            this.Salva_Mes = salva_Mes;
            return this;
        }
           
        public ReceitaBuild SalvaAno(int salvaAno){
            this.Salva_ano = salvaAno;
            return this;
        }
            
        public ReceitaBuild CodReceita(int cod_receita){
            this.cod_receita = id_conta;
            return this;
        }
        
        public Receita build(){
            return new Receita(this);
        }
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
        return getTotal() > 0;
    }
    
    public boolean verifica_ReceitaValida(){
        
        Data data_aux = new Data.DataBuild(mes, ano).Dia(dia).build();
        
        return data_aux.verifica_data() && verifica_total();
    }
    
    public boolean Update_CamposValidos(String dia, String mes, String ano, String valor){
        
        int dia_aux = Integer.parseInt(dia);
        int mes_aux = Integer.parseInt(mes);
        int ano_aux = Integer.parseInt(ano);
        float valor_aux = Float.parseFloat(valor);
        
        if(dia_aux != -1 && mes_aux != -1 && ano_aux != -1 && valor_aux != -1){
            setDia(dia_aux);
            setMes(mes_aux);
            setAno(ano_aux);
            setTotal(valor_aux);
            
            return verifica_ReceitaValida();
            
        }else{
            return false;
        }
    }

}
