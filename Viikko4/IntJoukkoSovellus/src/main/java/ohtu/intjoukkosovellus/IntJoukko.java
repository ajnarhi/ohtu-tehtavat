package ohtu.intjoukkosovellus;

public class IntJoukko {

    /*
    poista copypaste
    anna muuttujille selkeät nimet
    tee metodeista pienempiä ja hyvän koheesion omaavia
     */
    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        IntJoukkoKapasiteetillaJaKasvatuskoolla(KAPASITEETTI, OLETUSKASVATUS);

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        IntJoukkoKapasiteetillaJaKasvatuskoolla(kapasiteetti, kasvatuskoko);
    }

    public IntJoukko(int kapasiteetti) {
        IntJoukkoKapasiteetillaJaKasvatuskoolla(kapasiteetti, OLETUSKASVATUS);

    }

    private void IntJoukkoKapasiteetillaJaKasvatuskoolla(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetti ei voi olla pienempi kuin nolla!");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Et voi kasvattaa taulukkoa luvulla joka on pienempi kuin nolla!");
        }
        lukujono = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {

        if (alkioidenLkm == 0) {
            lukujono[0] = luku;
            alkioidenLkm++;
            return true;
        }
        if (!kuuluu(luku)) {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % lukujono.length == 0) {
                int[] taulukkoVanha = new int[lukujono.length];
                taulukkoVanha = lukujono;
                kopioiTaulukko(lukujono, taulukkoVanha);
                lukujono = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(taulukkoVanha, lukujono);
            }

        }
        return false;
    }

    public boolean kuuluu(int luku) {
        int montakoKertaaLukuoukossa = 0;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                montakoKertaaLukuoukossa++;
            }
        }
        if (montakoKertaaLukuoukossa > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean poista(int luku) {
        int luvunSijaintiTaulukossa = -1;
        int apu;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                luvunSijaintiTaulukossa = i;
                lukujono[luvunSijaintiTaulukossa] = 0;
                break;
            }
        }
        if (luvunSijaintiTaulukossa != -1) {
            for (int j = luvunSijaintiTaulukossa; j < alkioidenLkm - 1; j++) {
                apu = lukujono[j];
                lukujono[j] = lukujono[j + 1];
                lukujono[j + 1] = apu;
            }
            alkioidenLkm--;
            return true;
        }

        return false;
    }

    private void kopioiTaulukko(int[] vanhaTaulukko, int[] uusiTaulukko) {
        for (int i = 0; i < vanhaTaulukko.length; i++) {
            uusiTaulukko[i] = vanhaTaulukko[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + lukujono[0] + "}";
        } else {
            String lukujonoTulostettuna = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                lukujonoTulostettuna += lukujono[i];
                lukujonoTulostettuna += ", ";
            }
            lukujonoTulostettuna += lukujono[alkioidenLkm - 1];
            lukujonoTulostettuna+= "}";
            return lukujonoTulostettuna;
        }
    }

    public int[] toIntArray() {
        int[] joukkoLista = new int[alkioidenLkm];
        for (int i = 0; i < joukkoLista.length; i++) {
            joukkoLista[i] = lukujono[i];
        }
        return joukkoLista;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdisteJoukko = new IntJoukko();
        int[] aLista = a.toIntArray();
        int[] bLista = b.toIntArray();
        for (int i = 0; i < aLista.length; i++) {
            yhdisteJoukko.lisaa(aLista[i]);
        }
        for (int i = 0; i < bLista.length; i++) {
            yhdisteJoukko.lisaa(bLista[i]);
        }
        return yhdisteJoukko;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkausJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkausJoukko.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkausJoukko;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotusJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            erotusJoukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotusJoukko.poista(bTaulu[i]);
        }

        return erotusJoukko;
    }

}
