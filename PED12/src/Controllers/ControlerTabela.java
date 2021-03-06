/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.CartaoCredito;
import Model.CartaoDebito;
import Model.Categoria;
import Model.Despesa;
import Model.Receita;
import com.mysql.cj.util.StringUtils;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alan
 */
public class ControlerTabela {
   
    
    public static void LimpaTabela(JTable tabela){
        
        DefaultTableModel mp1 = (DefaultTableModel) tabela.getModel();

        int l = mp1.getRowCount();

        if (l > 0) {
            while (l > 0) {
                //Limpa tabela sempre que for fazer uma nova consulta
                ((DefaultTableModel) tabela.getModel()).removeRow(l - 1);

                //Menos um pois a primeira linha é a linha zero
                l--;
            }
        }  
    }
    
    public static DefaultTableModel RecarregaTabela(DefaultTableModel mp, int conta_id,String tipoTabela){
        
        try {

            switch (tipoTabela) {

                case "Receita":
                    LinkedList<Receita> listaReceita = ControlerReceita.GetListaReceita(conta_id);
                    
                    listaReceita.forEach((receita) -> {
                        String Col0 = Float.toString(receita.getTotal());
                        String Col1 = Integer.toString(receita.getDia());
                        String Col2 = Integer.toString(receita.getMes());
                        String Col3 = Integer.toString(receita.getAno());

                        mp.addRow(new String[]{Col0, Col1, Col2, Col3});
                    });
                    
                    listaReceita.clear();
                    break;
                    
                case "Despesa":
                    LinkedList<Despesa> lista_despesa = ControlerDespesa.GetListaDespesa(conta_id);

                    String Col7;
                    
                    for (Despesa despesa : lista_despesa) {

                        String Col0 = Integer.toString(despesa.getCod_despesa());
                        String Col1 = Integer.toString(despesa.getDia());
                        String Col2 = Integer.toString(despesa.getMes());
                        String Col3 = Integer.toString(despesa.getAno());
                        String Col4 = Float.toString(despesa.getValor());
                        String Col5 = despesa.getCategoria();
                        String Col6 = despesa.getF_pagamento();
                        
                        if (despesa.getNum_cartao() != null)
                            Col7 = Long.toString(despesa.getNum_cartao());
                        else
                            Col7 = "----";
                        
                        String Col8 = Integer.toString(despesa.getNum_parcelas());
                        String Col9 = despesa.getEstatus();
                        
                        String Col10 = despesa.getDescricao();

                        if (StringUtils.isNullOrEmpty(Col8) || Col8.equals("0")) 
                            Col8 = "----";
                        
                        if (StringUtils.isNullOrEmpty(Col10))
                            Col10 = "----";

                        mp.addRow(new String[]{Col0, Col1, Col2, Col3,
                            Col4, Col5, Col6, Col7,
                            Col8, Col9, Col10});
                    }

                    break;
                
                case "CartaoCredito":
                    LinkedList<CartaoCredito> lista_CC = ControlerCartaoCredito.GetListaCartaoCredito(conta_id);

                    lista_CC.forEach((cartao) -> {
                        String Col0 = Long.toString(cartao.getN_cartao_credito());
                        String Col1 = Float.toString(cartao.getLimite());
                        String Col2 = Float.toString(cartao.getCredito());
                        String Col3 = Integer.toString(cartao.getDia_fatura());
                        String Col4 = Float.toString(cartao.getValor_fatura());
                        String Col5 = cartao.getBandeira();

                        mp.addRow(new String[]{Col0, Col1, Col2, Col3, Col4, Col5});
                    });

                    lista_CC.clear();
                    break;
                    
                case "CartaoDebito":
                    LinkedList<CartaoDebito> lista_CD = ControlerCartaoDebito.GetListaCartaoDebito(conta_id);

                    lista_CD.forEach((cartao) -> {
                        String Col0 = Long.toString(cartao.getN_cartao_debito());
                        String Col1 = Float.toString(cartao.getValor_atual());
                        String Col2 = cartao.getBandeira();

                        mp.addRow(new String[]{Col0, Col1, Col2});
                    });

                    lista_CD.clear();
                    break;
                    
                case "Categoria":
                    LinkedList<Categoria> listaCategoria = ControlerCategoria.GetListaCategoria(conta_id);
                    
                    Collections.sort(listaCategoria, new Categoria.CategoriaASC());
                    
                    listaCategoria.forEach(categoria -> {
                        
                        String Col0 = categoria.getCategoriaTipo();                        
                        mp.addRow(new String[]{Col0});
                    });
                    
                    listaCategoria.clear();
                    break;
                
                case "Fatura":
                    
                    break;
                
                case "Main":
                    float totReceita = 0, totDespesas = 0, totCD = 0, totCC = 0;

                    Receita receita = ControlerReceita.GetReceitaAtual(conta_id);

                    if (receita != null) 
                    {
                        LinkedList<Despesa> despesas = ControlerDespesa.GetListaDespesaNpPorReceita(conta_id, "NÃO PAGO", receita);
                        totDespesas = despesas.stream().map((despesa) -> despesa.getValor()).reduce(totDespesas, (accumulator, _item) -> accumulator + _item);
                        despesas.clear();
                        
                        totReceita = receita.getTotal();
                    }

                    LinkedList<CartaoDebito> listaCD = ControlerCartaoDebito.GetListaCartaoDebito(conta_id);
                    LinkedList<CartaoCredito> listaCC = ControlerCartaoCredito.GetListaCartaoCredito(conta_id);

                    totCD = listaCD.stream().map((cartaoCD) -> cartaoCD.getValor_atual()).reduce(totCD, (accumulator, _item) -> accumulator + _item);
                    totCC = listaCC.stream().map((cartaoCC) -> cartaoCC.getCredito()).reduce(totCC, (accumulator, _item) -> accumulator + _item);

                    mp.addRow(new Float[]{totReceita, totDespesas, totCC, totCD});

                    listaCD.clear();
                    listaCC.clear();

                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null, "ERRO NO TIPO DE TABELA PARA SER RECARREGADA");
                    
            };

        } catch (NumberFormatException | NullPointerException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        
        }
        
        return mp;
    }
    
    public static DefaultTableModel RecarregaTabelaConsulta(DefaultTableModel mp, String tipo, String argumento, int conta_id, boolean ordenar, Receita receitaConsulta, String tipoTabela){
        
        try {

            switch (tipoTabela) {

                case "Receita":
                    LinkedList<Receita> listaReceita = ControlerReceita.ConsultaReceita(tipo, argumento, conta_id, ordenar);
                    
                    if (ordenar) {

                        switch (tipo) {
                            case " total":
                                Collections.sort(listaReceita, new Receita.ReceitaTotalASC());
                                break;
                            case " dia":
                                Collections.sort(listaReceita, new Receita.ReceitaDiaASC());
                                break;
                            case " mes":
                                Collections.sort(listaReceita, new Receita.ReceitaMesASC());
                                break;
                            case " ano":
                                Collections.sort(listaReceita, new Receita.ReceitaAnoASC());
                                break;
                            default:
                                break;
                        }

                    } else {

                        switch (tipo) {
                            case " total":
                                Collections.sort(listaReceita, new Receita.ReceitaTotalDESC());
                                break;
                            case " dia":
                                Collections.sort(listaReceita, new Receita.ReceitaDiaDESC());
                                break;
                            case " mes":
                                Collections.sort(listaReceita, new Receita.ReceitaMesDESC());
                                break;
                            case " ano":
                                Collections.sort(listaReceita, new Receita.ReceitaAnoDESC());
                                break;
                            default:
                                break;
                        }
                    }

                    listaReceita.forEach((receita) -> {
                        String Col0 = Float.toString(receita.getTotal());
                        String Col1 = Integer.toString(receita.getDia());
                        String Col2 = Integer.toString(receita.getMes());
                        String Col3 = Integer.toString(receita.getAno());

                        mp.addRow(new String[]{Col0, Col1, Col2, Col3});
                    });
                    break;
                    
                case "Despesa":
                    
                    LinkedList<Despesa> lista_despesa = null;
                    
                    if(receitaConsulta != null)
                        lista_despesa = ControlerDespesa.ConsultaDespesaPorReceita(tipo, argumento, ordenar, receitaConsulta);
                    else
                        lista_despesa = ControlerDespesa.ConsultaDespesa(tipo, argumento, ordenar, conta_id);
                    
                    String Col7;
                    
                    for (Despesa despesa : lista_despesa) {

                        String Col0 = Integer.toString(despesa.getCod_despesa());
                        String Col1 = Integer.toString(despesa.getDia());
                        String Col2 = Integer.toString(despesa.getMes());
                        String Col3 = Integer.toString(despesa.getAno());
                        String Col4 = Float.toString(despesa.getValor());
                        String Col5 = despesa.getCategoria();
                        String Col6 = despesa.getF_pagamento();
                        
                        if (despesa.getNum_cartao() != null)
                            Col7 = Long.toString(despesa.getNum_cartao());
                        else
                            Col7 = "----";
                        
                        String Col8 = Integer.toString(despesa.getNum_parcelas());
                        String Col9 = despesa.getEstatus();
                        String Col10 = despesa.getDescricao();

                        if (StringUtils.isNullOrEmpty(Col8) || Col8.equals("0")) 
                            Col8 = "----";
                        
                        if (StringUtils.isNullOrEmpty(Col10))
                            Col10 = "----";

                        mp.addRow(new String[]{Col0, Col1, Col2, Col3,
                            Col4, Col5, Col6, Col7,
                            Col8, Col9, Col10});

                    }

                    break;
                
                case "CartaoCredito":
                    LinkedList<CartaoCredito> lista_CC = ControlerCartaoCredito.ConsultaCartaoCredito(tipo, argumento, conta_id, ordenar);

                    lista_CC.forEach((cartao) -> {
                        String Col0 = Long.toString(cartao.getN_cartao_credito());
                        String Col1 = Float.toString(cartao.getLimite());
                        String Col2 = Float.toString(cartao.getCredito());
                        String Col3 = Integer.toString(cartao.getDia_fatura());
                        String Col4 = Float.toString(cartao.getValor_fatura());
                        String Col5 = cartao.getBandeira();

                        mp.addRow(new String[]{Col0, Col1, Col2, Col3, Col4, Col5});
                    });

                    lista_CC.clear();
                    break;
                    
                case "CartaoDebito":
                    LinkedList<CartaoDebito> lista_CD = ControlerCartaoDebito.ConsultaCartaoDebito(tipo, argumento, conta_id, ordenar);

                    lista_CD.forEach((cartao) -> {
                        String Col0 = Long.toString(cartao.getN_cartao_debito());
                        String Col1 = Float.toString(cartao.getValor_atual());
                        String Col2 = cartao.getBandeira();

                        mp.addRow(new String[]{Col0, Col1, Col2});
                    });

                    lista_CD.clear();
                    break;
                
                case "Categoria":
                    LinkedList<Categoria> listaCategoria;
                    
                    if(!argumento.isEmpty() || !argumento.equals(""))
                        listaCategoria = ControlerCategoria.ConsultaCategoria(argumento, conta_id, ordenar);
                    else
                        listaCategoria = ControlerCategoria.GetListaCategoria(conta_id);
                    
                    if (ordenar) {

                        Collections.sort(listaCategoria, new Categoria.CategoriaASC());

                    } else {

                        Collections.sort(listaCategoria, new Categoria.CategoriaDESC());
                    }

                    listaCategoria.forEach(categoria -> {
                        
                        String Col0 = categoria.getCategoriaTipo();                        
                        mp.addRow(new String[]{Col0});
                    });

                    listaCategoria.clear();
                    break;
                    
                case "Fatura":
                     //Implementar
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null, "ERRO NO TIPO DE TABELA PARA SER RECARREGADA A CONSULTA");                   
            };

        } catch (NumberFormatException | NullPointerException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        
        }
        
        return mp;
    }
}