package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JTextField;
 
public class Tapahtumankuuntelija implements ActionListener {
    private JButton plus;
    private JButton miinus;
    private JButton nollaa;
    private JButton undo;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private Sovelluslogiikka sovellus;
    private HashMap<JButton, Komento> komennot;
    private Komento edellinen = null;
 
    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        
        this.undo = undo;

        this.sovellus = new Sovelluslogiikka();
        this.syotekentta=syotekentta;
        this.tuloskentta=tuloskentta;
        this.nollaa=nollaa;
        komennot = new HashMap<>();
        komennot.put(plus, new Summa(tuloskentta, syotekentta, sovellus) );
        komennot.put(miinus, new Erotus(tuloskentta, syotekentta, sovellus) );
        komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta, sovellus) );

    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
                if ( ae.getSource() != undo ) {
            Komento komento = komennot.get((JButton)ae.getSource());
            komento.suorita();
            edellinen = komento;
        } else {
            edellinen.peru();
            edellinen = null;
        }                  
    
        
        int laskunTulos = sovellus.tulos();
         
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
        if ( laskunTulos==0) {
            nollaa.setEnabled(false);
        } else {
            nollaa.setEnabled(true);
        }
        undo.setEnabled(true);
    }
 
}