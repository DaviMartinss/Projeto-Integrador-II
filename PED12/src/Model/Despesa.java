/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Utilities.Validacao;

/**
 *
 * @author Alan
 */
public class Despesa {

    private int dia;
    private int mes;
    private int ano;
    private float valor;
    private String categoria;
    private String descricao;
    private String f_pagamento;
    private Long num_cartao;
    private String estatus;
    private int num_parcelas;
    private int num_parcelas_pagas;
    private float valor_parcela;
    private int id_conta;
    private int cod_despesa;
    private int id_categoria;
    
      public Despesa(DespesaBuild build){
        this.dia = build.dia;
        this.mes = build.mes;
        this.ano = build.ano;
        this.valor = build.valor;
        this.categoria = build.categoria;
        this.descricao = build.descricao;
        this.f_pagamento = build.f_pagamento;
        this.num_cartao = build.num_cartao;
        this.estatus = build.estatus;
        this.num_parcelas = build.num_parcelas;
        this.num_parcelas_pagas = build.num_parcelas_pagas;
        this.valor_parcela = build.valor_parcela;
        this.id_conta = build.id_conta;
        this.cod_despesa = build.cod_despesa;
        this.id_categoria = build.id_categoria;
        
    }
   
    public static class DespesaBuild{
        private int dia;
        private int mes;
        private int ano;
        private float valor;
        private String categoria;
        private String descricao;
        private String f_pagamento;
        private Long num_cartao;
        private String estatus;
        private int num_parcelas;
        private int num_parcelas_pagas;
        private float valor_parcela;
        private int id_conta;
        private int cod_despesa;
        private int id_categoria;
        
        // obrigatórios
        public DespesaBuild(int codDespesa){
            this.cod_despesa = codDespesa;
        }
        
        public DespesaBuild(){}
        
         public DespesaBuild Dia(int dia){
             this.dia = dia;
             return this;
         }
         
          public DespesaBuild Mes(int mes){
              this.mes = mes;
              return this;
          }
          
          public DespesaBuild Ano(int ano){
              this.ano = ano;
              return this;
          }
          public DespesaBuild Valor(float valor){
               this.valor = valor;
               return this;
           }
          public DespesaBuild Categoria(String categoria){
               this.categoria = categoria;
               return this;
           }
           public DespesaBuild Descricao(String descricao){
               this.descricao = descricao;
               return this;
           }
           
            public DespesaBuild FormaPagamento(String formaPagamento){
               this.f_pagamento = formaPagamento;
               return this;
           }
            
             public DespesaBuild NumeroCartao(long numCartao){
               this.num_cartao = numCartao;
               return this;
           }
             
             public DespesaBuild Status(String status){
               this.estatus = status;
               return this;
           }
             
             public DespesaBuild NumeroParcelas(int numeroParcelas){
               this.num_parcelas = numeroParcelas;
               return this;
           }
             
             public DespesaBuild NumeroParcelasPagas(int numParcelasPagas){
               this.num_parcelas_pagas = numParcelasPagas;
               return this;
           }
             
             public DespesaBuild ValorParcela(float valorParcela){
               this.valor_parcela = valorParcela;
               return this;
           }
             
             public DespesaBuild IdConta(int idConta){
               this.id_conta = idConta;
               return this;
           }
             
             public DespesaBuild idCategoria(int idCategoria){
               this.id_categoria = idCategoria;
               return this;
           }
             
          public Despesa build(){
              return new Despesa(this);
          }
    }
/*
    public Despesa() {

    }

    public Despesa(int cod_despesa) {

        this.cod_despesa = cod_despesa;
    }
    

    public Despesa(int dia, int mes, int ano, float valor, String categoria, String descricao, int id_conta) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
        this.id_conta = id_conta;
    }

    public Despesa(int dia, int mes, int ano, float valor, String categoria, String f_pagamento, Long num_cartao, int num_parcelas, String estatus, String descricao) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
        this.f_pagamento = f_pagamento;
        this.num_cartao = num_cartao;
        this.estatus = estatus;
        this.num_parcelas = num_parcelas;

    }

    public Despesa(int dia, int mes, int ano, float valor, String categoria, String f_pagamento, Long num_cartao, int num_parcelas, String estatus, String descricao, int cod_despesa) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
        this.f_pagamento = f_pagamento;
        this.num_cartao = num_cartao;
        this.estatus = estatus;
        this.num_parcelas = num_parcelas;
        this.cod_despesa = cod_despesa;
    }

    public Despesa(int dia, int mes, int ano, float valor, String categoria, String f_pagamento, Long num_cartao, String estatus, String descricao, int cod_despesa) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.valor = valor;
        this.categoria = categoria;
        this.f_pagamento = f_pagamento;
        this.num_cartao = num_cartao;
        this.estatus = estatus;
        this.descricao = descricao;
        this.cod_despesa = cod_despesa;
    }

    public Despesa(int dia, int mes, int ano, float valor, String categoria, String f_pagamento, String estatus, String descricao, int cod_despesa) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.valor = valor;
        this.categoria = categoria;
        this.f_pagamento = f_pagamento;
        this.estatus = estatus;
        this.descricao = descricao;
        this.cod_despesa = cod_despesa;
    }
    // construtor -> Crédito
    
    public Despesa(int dia, int mes, int ano, float valor, int categoria, String f_pagamento, Long num_cartao_credito, int num_parcelas, String status, String descricao, int cod_despesa) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.valor = valor;
        this.categoria = "teste";
        this.f_pagamento = f_pagamento;
        this.num_cartao = num_cartao_credito;
        this.num_parcelas = num_parcelas;
        this.estatus = estatus;
        this.descricao = descricao;
        this.cod_despesa = cod_despesa;
    }
    */
    public int getNum_parcelas_pagas() {
        return num_parcelas_pagas;
    }

    public void setNum_parcelas_pagas(int num_parcelas_pagas) {
        this.num_parcelas_pagas = num_parcelas_pagas;
    }

    public float getValor_parcela() {
        return valor_parcela;
    }

    public void setValor_parcela(float valor_parcela) {
        this.valor_parcela = valor_parcela;
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

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getF_pagamento() {
        return f_pagamento;
    }

    public void setF_pagamento(String f_pagamento) {
        this.f_pagamento = f_pagamento;
    }

    public Long getNum_cartao() {
        return num_cartao;
    }

    public void setNum_cartao(Long num_cartao) {
        this.num_cartao = num_cartao;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getNum_parcelas() {
        return num_parcelas;
    }

    public void setNum_parcelas(int num_parcelas) {
        this.num_parcelas = num_parcelas;
    }

    public int getId_conta() {
        return id_conta;
    }

    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
    }

    public int getCod_despesa() {
        return cod_despesa;
    }

    public void setCod_despesa(int cod_despesa) {
        this.cod_despesa = cod_despesa;
    }
    
    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
    
    public boolean validaDataDespesa() {

        Data data_aux = new Data.DataBuild(getMes(), getAno())
                .Dia(getDia())
                .build();
        

        return data_aux.verifica_data();
    }

    public boolean validaNumParcelas() {

        return this.getNum_parcelas() > 0 && this.getNum_parcelas() <= 12;
    }

    public boolean validaValor() {

        return getValor() > 0;
    }

    public boolean valorEhVazio(String valor) {
        return valor == null || valor.trim().equals("");
    }

    public boolean verifica_num_cartao_despesa() {

        Cartao cartao_aux = new Cartao();

        return (cartao_aux.ValidaNUM_Cartao(Long.toString(this.num_cartao)));

    }

    public boolean verifica_Categoria(String categoria) {

        String categoria_aux = categoria.replace(" ", "");

        boolean soLetra = true;

        for (int i = 0; i < categoria_aux.length(); i++) {

            char aux = categoria_aux.charAt(i);

            if (!(Character.isLetter(aux))) {
                soLetra = false;
            }
        }

        return soLetra;
    }

    public boolean verifica_DespesaValida() {

        return validaValor() && validaDataDespesa();

    }
       

    public boolean Update_CamposValidos(String valor, String dia, String mes, String ano) {
        Validacao valida = new Validacao();

        int dia_aux = valida.converteParaInt(dia);
        int mes_aux = valida.converteParaInt(mes);
        int ano_aux = valida.converteParaInt(ano);
        float valor_aux = valida.converteParaFloat(valor);

        if (dia_aux != -1 && mes_aux != -1 && ano_aux != -1 && valor_aux != -1) {
            setDia(dia_aux);
            setMes(mes_aux);
            setAno(ano_aux);
            setValor(valor_aux);

            return verifica_DespesaValida();

        } else {

            return false;
        }
    }
}