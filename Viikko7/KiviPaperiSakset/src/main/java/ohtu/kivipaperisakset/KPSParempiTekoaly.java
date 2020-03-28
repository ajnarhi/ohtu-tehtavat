package ohtu.kivipaperisakset;

import java.util.Scanner;

import java.util.Scanner;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KPS{
    
   
    TekoalyParannettu tekoaly = new TekoalyParannettu(20);
    
    public String tokanSiirto(String ekanSiirto){
       
        String tokanSiirto = tekoaly.annaSiirto();
            System.out.println("Tietokone valitsi: " + tokanSiirto);
            tekoaly.asetaSiirto(ekanSiirto);
            
            return tokanSiirto;
    }


    

    

}
