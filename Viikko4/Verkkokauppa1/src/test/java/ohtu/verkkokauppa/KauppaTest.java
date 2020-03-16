/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        // ...
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
//        // luodaan ensin mock-oliot
//        Pankki pankki = mock(Pankki.class);
//
//        Viitegeneraattori viite = mock(Viitegeneraattori.class);
//        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

//        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanJaPalautetaanOikeatArvot() {

        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);

    }

    @Test
    public void kahdenEriOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanJaPalautetaanOikeatArvot() {

        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 3));
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);   //ostetaan tuotetta numero 2 eli kahvia jonka hinta on 3
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 8);

    }

    @Test
    public void kahdenSamanOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanJaPalautetaanOikeatArvot() {

        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 4));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);   //ostetaan tuotetta numero 1eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 8);

    }

    @Test
    public void kaksiEriOstostaToistaOntoistaEiOlePaaytyttyaPankinMetodiaTilisiirtoKutsutaanJaPalautetaanOikeatArvot() {

        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(0);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 4));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 3));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);   //ostetaan tuotetta numero 2 eli kahvia jota ei ole
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 4);

    }

    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksenTiedot() {

        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 4));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 3));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.aloitaAsiointi();
        k.lisaaKoriin(2);   //ostetaan tuotetta numero 2 eli kahvia jota ei ole
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 3);

    }

    @Test
    public void kauppaPyytaaViitenumeronJokaiselleMaksutapahtumalle() {

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 4));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 3));

        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();k.aloitaAsiointi();
        k.lisaaKoriin(2);   //ostetaan tuotetta numero 2 eli kahvia jota ei ole
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        // tarkistetaan että tässä vaiheessa viitegeneraattorin metodia seuraava()
        // on kutsuttu kerran
        verify(viite, times(1)).uusi();

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        // tarkistetaan että tässä vaiheessa viitegeneraattorin metodia seuraava()
        // on kutsuttu kaksi kertaa
        verify(viite, times(2)).uusi();

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        // tarkistetaan että tässä vaiheessa viitegeneraattorin metodia seuraava()
        // on kutsuttu kolme kertaa        
        verify(viite, times(3)).uusi();

    }
    
    
    @Test
    public void poistaKoristaPoistaaKerranKorista() {
        Tuote t= new Tuote (1, "maito", 4);
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);

        when(varasto.haeTuote(1)).thenReturn(t);
       

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.poistaKorista(1);
        k.tilimaksu("pekka", "12345");

        verify(varasto, times(1)).palautaVarastoon(t);

    }

}


