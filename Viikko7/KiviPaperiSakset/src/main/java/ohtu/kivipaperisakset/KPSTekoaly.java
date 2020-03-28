package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KPS {
    
    Tekoaly tekoaly = new Tekoaly();
    
    public String tokanSiirto(String ekanSiirto){
       
        String tokanSiirto = tekoaly.annaSiirto();
            System.out.println("Tietokone valitsi: " + tokanSiirto);
            tekoaly.asetaSiirto(ekanSiirto);
            
            return tokanSiirto;
    }

    

 
}