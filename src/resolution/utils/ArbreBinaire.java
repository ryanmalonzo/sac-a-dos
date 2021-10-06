package resolution.utils;

import java.util.ArrayList;
import java.util.List;

public class ArbreBinaire {
    private ArbreBinaire gauche;
    private ArbreBinaire droite;
    private List<Integer> objets;

    private double sup;
    private static double inf = 0;

    private static double poidsMax;

    public ArbreBinaire(double poidsMax) {
        this();
        ArbreBinaire.poidsMax = poidsMax;
    }

    private ArbreBinaire() {
        gauche = null;
        droite = null;
        objets = null;
    }

    public void ajouter(List<Integer> o, Integer i) {
        if (objets == null) {
            objets = o; // new ArrayList<>(o) ?
            gauche = new ArbreBinaire();
            droite = new ArbreBinaire();
        } else {
            // On fabrique liste + i pour le fils droit
            List<Integer> lo = new ArrayList<>(objets);
            lo.add(i);
            droite.ajouter(lo, i);

            // Copie à l'identique pour le fils gauche
            gauche.ajouter(new ArrayList<>(objets), i);
        }
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (gauche.objets == null) // ou droite.objets
            sb.append(objets).append(System.lineSeparator());
        else {
            sb.append(gauche);
            sb.append(droite);
        }
        return sb.toString();
    }
}
