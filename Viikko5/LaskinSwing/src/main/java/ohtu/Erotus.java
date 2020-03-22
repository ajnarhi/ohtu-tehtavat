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
public class Erotus extends Komento{
    
     public Erotus(JTextField tuloskentta, JTextField syotekentta, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, sovellus);
    }

       @Override
    public void suorita() {
       int arvo = 0;
 
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
 
       
            sovellus.miinus(arvo);
    }  
    
    @Override 
    public void peru(){
          int arvo = 0;
 
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
 
       
            sovellus.plus(arvo);
        
        
    }
}
