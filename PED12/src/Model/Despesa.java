/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import ValidacaoComum.Validacao;

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
    private int id_categoria;


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

        Data data_aux = new Data(getDia(), getMes(), getAno());

        if (data_aux.verifica_data()) {

            return true;

        } else {

            return false;
        }
    }

    public boolean validaNumParcelas() {

        if (this.getNum_parcelas() > 0 && this.getNum_parcelas() <= 12) {

            return true;
        } else {

            return false;
        }
    }

    public boolean validaValor() {

        if (getValor() > 0) {
            return true;
        } else {

            return false;
        }
    }

    public boolean valorEhVazio(String valor) {
        if (valor == null || valor.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean EhVazio(String rec) {
        if (rec == null || rec.trim().equals("")) {
            return true;
        } else {
            return false;
        }
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

        if (!(soLetra)) {
            return false;
        }

        return true;
    }

    public boolean verifica_DespesaValida() {

        if (validaValor() && validaDataDespesa() ) {

            return true;
        } else {
            return false;
        }

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

            if (verifica_DespesaValida()) {

                return true;
            } else {
                return false;
            }

        } else {

            return false;
        }
    }

    public boolean UpdateEhVazio(String dia, String mes, String ano, String valor, String f_pag,
            String num_cartao, String num_parc, String status, String desc) {

        if (!(EhVazio(f_pag))) {

            if (f_pag.equals("CRÉDITO")) {

                if (EhVazio(dia) || EhVazio(mes) || EhVazio(ano) || EhVazio(valor)
                        || EhVazio(num_cartao) || EhVazio(num_parc)
                        || EhVazio(status) || EhVazio(desc)) {
                    
                    return true;

                } else {

                    return false;
                }

            } else if (f_pag.equals("DÉBITO")) {

                if (EhVazio(dia) || EhVazio(mes) || EhVazio(ano) || EhVazio(valor)
                        || EhVazio(num_parc)
                        || EhVazio(status) || EhVazio(desc)) {

                    return true;

                } else {

                    return false;
                }

            } else {

                if (EhVazio(dia) || EhVazio(mes) || EhVazio(ano) || EhVazio(valor)
                        || EhVazio(status) || EhVazio(desc)) {

                    return true;

                } else {

                    return false;
                }
            }
        } else {

            return true;
        }
    }

}
