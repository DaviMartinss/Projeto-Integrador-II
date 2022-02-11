/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class Validacao {
    
    private static final SimpleDateFormat formatDate = new SimpleDateFormat("ddMMyyyy");

    public Validacao() 
    {
        
    }
    
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }
    
    public static boolean isDate(String dataString){
        
        try {
          
            formatDate.setLenient(false);
            formatDate.parse(dataString);
            return true; 
            
        } catch (ParseException e) {
            return false;
        }
    }
    
    public int converteParaInt(String valor){
        if(isNumeric(valor)){
            return Integer.parseInt(valor);
          }
          
          return -1;
    }  
    
     public float converteParaFloat(String valor){
         
        if(isNumeric(valor)){
            return Float.parseFloat(valor);
          }
          
          return -1;
    }    
}