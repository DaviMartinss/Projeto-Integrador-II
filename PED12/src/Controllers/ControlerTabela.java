/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Receita;
import ReceitaOrdenacao.ReceitaAnoASC;
import ReceitaOrdenacao.ReceitaAnoDESC;
import ReceitaOrdenacao.ReceitaDiaASC;
import ReceitaOrdenacao.ReceitaDiaDESC;
import ReceitaOrdenacao.ReceitaMesASC;
import ReceitaOrdenacao.ReceitaMesDESC;
import ReceitaOrdenacao.ReceitaTotalASC;
import ReceitaOrdenacao.ReceitaTotalDESC;
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
                    break;
                    
                case "Despesa":
                    //Implementar
                    break;
                
                case "Cartão Crédito":
                     //Implementar
                    break;
                    
                case "Cartão Débito":
                     //Implementar
                    break;
                
                 case "Fatura":
                     //Implementar
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null, "ERRO INESPERADO");
                    
            };

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        
        }
        
        return mp;
    }
    
    public static DefaultTableModel RecarregaTabelaConsulta(DefaultTableModel mp, String tipo, String argumento, int conta_id, boolean ordenar,String tipoTabela){
        
        try {

            switch (tipoTabela) {

                case "Receita":
                    LinkedList<Receita> listaReceita = ControlerReceita.ConsultaReceita(tipo, argumento, conta_id, ordenar);
                    
                    if (ordenar) {

                        if (tipo.equals(" total")) {
                            Collections.sort(listaReceita, new ReceitaTotalASC());
                        }

                        if (tipo.equals(" dia")) {
                            Collections.sort(listaReceita, new ReceitaDiaASC());
                        }

                        if (tipo.equals(" mes")) {
                            Collections.sort(listaReceita, new ReceitaMesASC());
                        }

                        if (tipo.equals(" ano")) {
                            Collections.sort(listaReceita, new ReceitaAnoASC());
                        }

                    } else {

                        if (tipo.equals(" total")) {
                            Collections.sort(listaReceita, new ReceitaTotalDESC());
                        }

                        if (tipo.equals(" dia")) {
                            Collections.sort(listaReceita, new ReceitaDiaDESC());
                        }

                        if (tipo.equals(" mes")) {
                            Collections.sort(listaReceita, new ReceitaMesDESC());
                        }

                        if (tipo.equals(" ano")) {
                            Collections.sort(listaReceita, new ReceitaAnoDESC());
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
                    //Implementar
                    break;
                
                case "Cartão Crédito":
                     //Implementar
                    break;
                    
                case "Cartão Débito":
                     //Implementar
                    break;
                
                 case "Fatura":
                     //Implementar
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null, "ERRO INESPERADO");                   
            };

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        
        }
        
        return mp;
    }
}