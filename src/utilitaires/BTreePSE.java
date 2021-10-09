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
    private static double inf = 0.0;


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
            if (gauche != null)
                gauche.ajouter(i, false);
            if (droite != null)
                droite.ajouter(i, true);
        }
    }

    private void ajouter(Integer i, boolean droite) {
        if (objets == null) {
            objets = new ArrayList<>(parent.objets);

            if (droite) {
                objets.add(i);
                if (!realisable(intToObj(objets)))
                    parent.droite = null;
            }

            // Borne supérieure
            if (sup() < inf) {
                if (droite)
                    parent.droite = null;
                else
                    parent.gauche = null;
                return;
            }

            initialiserFils();
        }

        else {
            if (this.gauche != null)
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

    private boolean existe(BTreePSE bt) {
        return bt != null && bt.objets != null;
    }

    private boolean realisable(List<Objet> objets) {
        double poids = 0.0, valeur = 0.0;
        for (Objet o : objets) {
            poids += o.getPoids();
            valeur += o.getValeur();
        }

        if (poids > sac.getPoidsMax())
            return false;

        // Mise à jour de la borne inférieure ?
        if (poids <= sac.getPoidsMax() && valeur > inf)
            inf = valeur;

        return true;
    }

    private List<Objet> intToObj(List<Integer> entiers) {
        List<Objet> o = new ArrayList<>();
        List<Objet> s = sac.getObjets();
        for (Integer i : entiers)
            o.add(s.get(i - 1)); // -1 car +1 pour l'affichage
        return o;
    }

    public List<Objet> solution() {
        List<List<Objet>> listeObjets = new ArrayList<>();
        for (List<Integer> feuille : feuilles())
            listeObjets.add(intToObj(feuille));

        List<Objet> solution = new ArrayList<>();
        double poidsSolution = 0.0, valeurSolution = 0.0;

        for (List<Objet> objets : listeObjets) {
            double poids = 0.0, valeur = 0.0;
            for (Objet o : objets) {
                poids += o.getPoids();
                valeur += o.getValeur();
            }
            /*
            Deux cas de figure :
            - la valeur du sac est plus grande que la solution
            - la valeur du sac est la même que la solution mais son poids est plus faible (optimal)
            */
            if (valeur > valeurSolution || (valeur == valeurSolution && poids < poidsSolution)) {
                poidsSolution = poids;
                valeurSolution = valeur;
                solution = objets;
            }
        }
        return solution;
    }

    private int profondeur() {
        if (parent == this)
            return 1;
        return 1 + parent.profondeur();
    }

    private double sup() {
        int objetSuivant = profondeur();
        double sup = 0.0;
        List<Objet> ob = intToObj(objets);
        for (Objet o : ob) {
            sup += o.getValeur();
        }

        while (objetSuivant - 1 < sac.getObjets().size()) {
            sup += sac.getObjets().get(objetSuivant - 1).getValeur();
            ++objetSuivant;
        }

        return sup;
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
