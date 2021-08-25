/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ValidacaoComum;

/**
 *
 * @author pc
 */
public class Validacao {

    public Validacao() 
    {
        
    }
    
    
    public boolean ehNum(String res){
        
         boolean isNumeric = true;
            String res_aux = res.replace(".", "");
            for(int i = 0; i < res_aux.length(); i++){
                if(!Character.isDigit(res_aux.charAt(i))){
                    isNumeric = false;
                }
            }
            if(isNumeric){
                
                return  true;
                
            }else{
                
                return  false;
            }
        
     }
        

    public int converteParaInt(String valor){
        if(ehNum(valor)){
            return Integer.parseInt(valor);
          }
          
          return -1;
    }
    
    
     public float converteParaFloat(String valor){
         
        if(ehNum(valor)){
            return Float.parseFloat(valor);
          }
          
          return -1;
    }
     
}
