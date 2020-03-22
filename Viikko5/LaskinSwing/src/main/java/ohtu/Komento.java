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
public abstract class Komento {
     protected JTextField tuloskentta;
     protected JTextField syotekentta;
     protected Sovelluslogiikka sovellus;
     
     
    public Komento(JTextField tuloskentta, JTextField syotekentta, Sovelluslogiikka sovellus) {
        this.tuloskentta=tuloskentta;
        this.syotekentta=syotekentta;
        this.sovellus=sovellus;
}
    public abstract void suorita();


    public abstract void peru();
}
