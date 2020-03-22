/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import javax.swing.JTextField;

/**
 *
 * @author ajnarhi
 */
public class Nollaa extends Komento {
    
    int tulos=0;
       
    public Nollaa(JTextField tuloskentta, JTextField syotekentta, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, sovellus);
    }
    
    
    @Override
    public void suorita() {
            tulos=sovellus.tulos(); 
            sovellus.nollaa();
    }  
    
    @Override 
    public void peru(){
          sovellus.plus(tulos);
        
        
    }
}

