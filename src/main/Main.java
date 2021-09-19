package main;

import objet.Objet;
import resolution.Methode;
import sacados.SacADos;

import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        Objet[] objets1 = new Objet[4];
        objets1[0] = new Objet("Boîte 1", 7.0, 13.0);
        objets1[1] = new Objet("Boîte 2", 4.0, 12.0);
        objets1[2] = new Objet("Boîte 3", 3.0, 8.0);
        objets1[3] = new Objet("Boîte 4", 3.0, 10.0);

        SacADos sac1 = new SacADos(objets1, 30);
        sac1.resoudre(Methode.GLOUTONNE);

        Objet[] objets2 = new Objet[5];
        objets2[0] = new Objet("Balle 1", 10.0, 9.0);
        objets2[1] = new Objet("Balle 2", 7.0, 12.0);
        objets2[2] = new Objet("Balle 3", 1.0, 2.0);
        objets2[3] = new Objet("Balle 4", 3.0, 7.0);
        objets2[4] = new Objet("Balle 5", 2.0, 5.0);

        SacADos sac2 = new SacADos(objets2, 15);
        sac2.resoudre(Methode.GLOUTONNE);
    }

    public static void quicksort(Objet[] t, int first, int last) {
        resolution.algorithmes.Quicksort.quicksort(t, first, last);
    }
}
