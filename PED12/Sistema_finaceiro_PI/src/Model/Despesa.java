/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
    private int id_conta;
    private int cod_despesa;

    
     
    public Despesa() {
    
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

    public Despesa(int dia, int mes, int ano, float valor, String categoria, String f_pagamento, String estatus, String descricao,int cod_despesa) {
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
    
    
    
}
