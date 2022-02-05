/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.DespesaDAO;
import Model.Despesa;
import Model.Receita;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan
 */

public class ControlerDespesa {
    
    public static boolean CadastrarDespesa(Despesa despesa){
        
        DespesaDAO despesaDAO = new DespesaDAO();
        
        try {
            
            Receita receita = new Receita(
                    despesa.getId_conta(),
                    despesa.getMes(),
                    despesa.getAno()
            );
            
            if (despesaDAO.DespesaExiste(despesa)) {

                JOptionPane.showMessageDialog(null, "Já existe uma despesa nesse dia!", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            if (!(ControlerReceita.ReceitaExiste(despesa.getMes(), despesa.getAno(),despesa.getId_conta()) ) ) {
                
                JOptionPane.showMessageDialog(null, "Não existe receita correspondente para essa despesa", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (despesa.getF_pagamento().equals("CRÉDITO")) {

                if (ControlerCartaoCredito.GetCartaoCredito(despesa.getNum_cartao(), despesa.getId_conta()) == null) {

                    JOptionPane.showMessageDialog(null, "Não existe cartão de credito registrado com este número", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    return false;

                } else {

                    if (!(despesaDAO.DespesaCCTemCredito(despesa.getNum_cartao(), despesa.getValor(), despesa.getId_conta()))) {

                        JOptionPane.showMessageDialog(null, "Cartão de Crédito informado não possui crédito disponível", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
            }

            if (despesa.getF_pagamento().equals("DÉBITO")) {

                if (ControlerCartaoDebito.GetCartaoDebito(despesa.getNum_cartao(), despesa.getId_conta()) == null) {

                    JOptionPane.showMessageDialog(null, "Não existe cartão de débito registrado com este número", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            if (!(ControlerReceita.ReceitaTemSaldo(receita, despesa.getValor()))) {

                JOptionPane.showMessageDialog(null, "Receita correspondente não possui Saldo", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            int cod_receita = ControlerReceita.GetCodigoReceita(despesa.getId_conta(), despesa.getMes(), despesa.getAno());

            int id_categoria = ControlerCategoria.GetCategoriaId(despesa.getId_conta(), despesa.getCategoria());

            //INSERINDO DESPESA
            despesaDAO.InsertDespesa(despesa, cod_receita, id_categoria);

            //INSERE DESPESA CREDITO SE FOR DESTE TIPO
            if (despesa.getF_pagamento().equals("CRÉDITO")) {

                int cod_despesa = despesaDAO.GetCodigoDespesa(despesa.getId_conta(), despesa.getDia(), despesa.getMes(), despesa.getAno());

                despesa.setCod_despesa(cod_despesa);

                despesaDAO.InsertDespesaCredito(despesa);

                //ATUALIZA AS INFORMAÇÕES DO CARTAO DE CREDITO AO ADICIONAR NOVA DESPESA DO TIPO
                ControlerCartaoCredito.UpdateCreditoFaturaCartaoCredito(id_categoria, despesa.getValor(), cod_receita);
            
                ///JÁ É PARA SER CADASTRADA COMO PAGA SE FOR NO DEBITO 
            } else if (despesa.getF_pagamento().equals("DÉBITO")){
                
                //ATUALIZA O VALOR DO CARTÃO DE DÉBITO
                ControlerCartaoDebito.UpdateValorAtualCartaoDebito(despesa.getId_conta(), despesa.getValor(), despesa.getNum_cartao());
                
            }
            
            //<editor-fold defaultstate="collapsed" desc="----- Desconta da Receita --">
            if (despesa.getF_pagamento().equals("CRÉDITO")
                    || despesa.getF_pagamento().equals("DÉBITO")
                    || (despesa.getF_pagamento().equals("DINHEIRO") && despesa.getEstatus().equals("PAGO"))) {

                ControlerReceita.UpdateTotalReceita(despesa.getId_conta(), despesa.getValor(), cod_receita);
            }

            //</editor-fold>

            return true;
            
        }catch (SQLException | HeadlessException | NullPointerException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);            
            JOptionPane.showMessageDialog(null, "Erro:CadastrarDespesa", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
            
            return false;
        }
   }
    
    public static boolean AtualizarDespesa(Despesa despesa){
        
       DespesaDAO despesaDAO = new DespesaDAO(); 
       
        try {
            
            int cod_receita = ControlerReceita.GetCodigoReceita(despesa.getId_conta(), despesa.getMes(), despesa.getAno());            
            int id_categoria = ControlerCategoria.GetCategoriaId(despesa.getId_conta(), despesa.getCategoria());
            
           //FAZ TRATTIVAS NECESSÁRIAS DEPENDENDO DO TIPO DA DESPESA
           switch (despesa.getF_pagamento()) {
               
               case "CRÉDITO":
                   if(ControlerCartaoCredito.GetCartaoCredito(despesa.getNum_cartao(), despesa.getId_conta()) != null)
                   {
                       
                       despesaDAO.UpdateDespesaTipoCredito(despesa, id_categoria);
                       
                       //ATUALIZA AS INFORMAÇÕES DO CARTÃO DE CRÉDITO
                       ControlerCartaoCredito.UpdateCreditoFaturaCartaoCredito(despesa.getId_conta(), despesa.getValor(), despesa.getNum_cartao());
                       
                   }else
                   {
                       JOptionPane.showMessageDialog(null, "Você não tem esse cartão de Crédito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                       return false;
                   }  
                   break;
                   
               case "DÉBITO":
                   if(ControlerCartaoDebito.GetCartaoDebito(despesa.getNum_cartao(), despesa.getId_conta()) != null)
                   {
                       
                       despesaDAO.UpdateDespesaTipoDebito(despesa, id_categoria);
                       
                       //ATUALIZA O VALOR DO CARTÃO DE DÉBITO
                       ControlerCartaoDebito.UpdateValorAtualCartaoDebito(despesa.getId_conta(), despesa.getValor(), despesa.getNum_cartao());
                       
                   }else
                   {
                       JOptionPane.showMessageDialog(null, "Você não tem esse cartão de Débito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                       return false;
                   }  
                   break;
                   
               case "DINHEIRO":
                   despesaDAO.UpdateDespesaTipoDinheiro(despesa, id_categoria);
                   break;
                   
               default:
                   //FAZ NADA
                   break;
           }
            
             //<editor-fold defaultstate="collapsed" desc="----- Desconta da Receita --">
            if (despesa.getF_pagamento().equals("CRÉDITO")
                    || despesa.getF_pagamento().equals("DÉBITO")
                    || (despesa.getF_pagamento().equals("DINHEIRO") && despesa.getEstatus().equals("PAGO"))) {

                ControlerReceita.UpdateTotalReceita(despesa.getId_conta(), despesa.getValor(), cod_receita);
            }

            //</editor-fold>
            
            return true;
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "ERRO:AtualizarDespesa", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return false;
              
        }catch(HeadlessException ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);            
            JOptionPane.showMessageDialog(null, "Erro:AtualizarDespesa", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
            
            return false;
        }
    }
    
    public static boolean ApagarDespesa(Despesa despesa){
        
        DespesaDAO despesaDAO = new DespesaDAO();
        
        try {
            
          despesaDAO.DeleteDespesa(despesa);          
          return true;
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Erro:UpdateValorAtualCartaoDebito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Erro:UpdateValorAtualCartaoDebito", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            return false;   
        }
    }
    
    public static LinkedList<Despesa> GetListaDespesa(int id_conta){
        
         DespesaDAO despesaDAO = new DespesaDAO();
        
        try {

            LinkedList<Despesa> lista_despesa = new LinkedList<>();

            lista_despesa.addAll(despesaDAO.GetListaDespesaCredito(id_conta));
            lista_despesa.addAll(despesaDAO.GetListaDespesaDebito(id_conta));
            lista_despesa.addAll(despesaDAO.GetListaDespesaDinheiro(id_conta));

            return lista_despesa;

        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:GetListaDespesa", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
            
            return null;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:GetListaDespesa", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return null;
        }     
    }
    
    
    public static LinkedList<Despesa> ConsultaDespesa(String tipo, String arg, boolean ordenar, int id_conta){
        
         DespesaDAO despesaDAO = new DespesaDAO();
        
        try {

            return despesaDAO.ConsultaDespesa(tipo, arg, ordenar, id_conta);

        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:GetListaDespesa", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
            
            return null;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:GetListaDespesa", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return null;
        }     
    }
    
    public static LinkedList<Despesa> ConsultaDespesaPorReceita(String tipo, String arg, boolean ordenar, Receita receita){
        
         DespesaDAO despesaDAO = new DespesaDAO();
        
        try {

            return despesaDAO.ConsultaDespesaPorReceita(tipo, arg, ordenar, receita);

        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:GetListaDespesa", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
            
            return null;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:GetListaDespesa", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return null;
        }     
    }
    
    
    public static boolean TransferirDespesasEntreReceitas(LinkedList<Despesa> lista_despesasNp, Receita receita_nova){
        
         DespesaDAO despesaDAO = new DespesaDAO();
        
        try {

            for (Despesa despesa : lista_despesasNp)
                despesaDAO.UpdateMesAnoDespesa(despesa, receita_nova);
        
            return true;

        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:TransferirDespesasEntreReceitas", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE); 
            
            return false;
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(null, "ERRO:TransferirDespesasEntreReceitas", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            
            return false;
        }     
    }
    
}
