package resolution.utils;

import sacados.SacADos;

import java.util.ArrayList;
import java.util.List;

public class ArbreBinaire {
    private ArbreBinaire gauche;
    private ArbreBinaire droite;
    private ArbreBinaire parent;

    private List<Integer> objets;

    private SacADos sac;

    private double sup;
    private static double inf = 0;


    public ArbreBinaire(SacADos sac) {
        this();
        parent = this;
        objets = new ArrayList<>(); // racine vide
        initialiserFils();
        this.sac = sac;
    }

    private ArbreBinaire() {
        gauche = null;
        droite = null;
        objets = null;
    }

    private void initialiserFils() {
        gauche = new ArbreBinaire();
        droite = new ArbreBinaire();
        gauche.parent = this;
        droite.parent = this;
    }

    /**
     * Construit un arbre binaire d'énumération des combinaisons possibles
     * d'objets du sac
     */
    public void construire() {
        for (int i = 1; i <= sac.getObjets().size(); ++i) {
            gauche.ajouter(i, false);
            droite.ajouter(i, true);
        }
    }

    private void ajouter(Integer i, boolean droite) {
        if (objets == null) {
            objets = new ArrayList<>(parent.objets);
            if (droite) objets.add(i);

            initialiserFils();
        }
        else {
            gauche.ajouter(i, false);
            this.droite.ajouter(i, true);
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
