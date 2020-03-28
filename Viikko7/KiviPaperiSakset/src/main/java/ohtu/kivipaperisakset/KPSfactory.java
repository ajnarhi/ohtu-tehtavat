/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

/**
 *
 * @author ajnarhi
 */
public class KPSfactory {
    
    public static KPS createGame(String type){
        
        if(type.equals("a")){
            return new KPSPelaajaVsPelaaja();
        }else if (type.equals("b")){
            return new KPSTekoaly();
            
        }else{
            return new KPSParempiTekoaly();
            
        }
        
       
        
    }
    
}
