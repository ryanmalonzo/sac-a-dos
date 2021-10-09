package utilitaires;

import objet.Objet;
import sacados.SacADos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BTreePSE {
    private BTreePSE gauche;
    private BTreePSE droite;
    private BTreePSE parent;

    private List<Integer> objets;

    private static SacADos sac;

    private double sup;
    private static double inf = 0;


    public BTreePSE(SacADos sac) {
        this();
        parent = this;
        objets = new ArrayList<>(); // racine vide
        initialiserFils();
        BTreePSE.sac = sac;
    }

    private BTreePSE() {
        gauche = null;
        droite = null;
        objets = null;
    }

    private void initialiserFils() {
        gauche = new BTreePSE();
        droite = new BTreePSE();
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
            if (droite) {
                objets.add(i);
                List<Objet> o = intToObj(objets);
                if (!realisable(o)) parent.droite = null;
            }

            initialiserFils();
        }

        else {
            gauche.ajouter(i, false);
            if (this.droite != null)
                this.droite.ajouter(i, true);
        }
    }

    public List<List<Integer>> feuilles() {
        List<List<Integer>> f = new ArrayList<>();
        dfs(f);
        return f;
    }

    private void dfs(List<List<Integer>> feuilles) {
        if (existe(gauche) || existe(droite)) {
            if (existe(gauche))
                gauche.dfs(feuilles);
            if (existe(droite))
                droite.dfs(feuilles);
        } else {
            feuilles.add(objets);
        }
    }

    private boolean existe(BTreePSE ab) {
        return ab != null && ab.objets != null;
    }

    private boolean realisable(List<Objet> objets) {
        double poids = 0.0, valeur = 0.0;
        for (Objet o : objets) {
            poids += o.getPoids();
            valeur += o.getValeur();
        }

        // Poids des objets trop grands
        if (poids > sac.getPoidsMax())
            return false;

        // Mise à jour de la borne inférieure
        if (poids <= sac.getPoidsMax() && valeur > inf)
            inf = valeur;

        return true;
    }

    // TODO remove
    public static double getInf() {
        return inf;
    }

    private List<Objet> intToObj(List<Integer> entiers) {
        List<Objet> o = new ArrayList<>();
        List<Objet> s = sac.getObjets();
        for (Integer i : entiers)
            o.add(s.get(i - 1));
        return o;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<List<Integer>> feuilles = feuilles();
        Collections.reverse(feuilles); // affichage inversé

        for (List<Integer> feuille : feuilles)
            sb.append(feuille).append(System.lineSeparator());

        return sb.toString();
    }
}
