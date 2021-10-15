package utilitaires;

import objets.Objet;
import objets.Objets;
import sacados.SacADos;

import java.util.ArrayList;
import java.util.List;

public class BSTree {
    private static SacADos sac;
    private static double inf;
    private BSTree parent;
    private BSTree gauche;
    private BSTree droite;
    private Objets objets;

    public BSTree(SacADos sac) {
        parent = this;
        objets = new Objets();
        BSTree.sac = sac;
        BSTree.inf = 0.0;
        initialiserFils();
        this.construire();
    }

    private BSTree() {
        gauche = null;
        droite = null;
        objets = null;
    }

    private void initialiserFils() {
        gauche = new BSTree();
        droite = new BSTree();
        gauche.parent = droite.parent = this;
    }

    private void construire() {
        for (Objet objet : sac) {
            if (gauche != null)
                gauche.ajouter(objet, false);
            if (droite != null)
                droite.ajouter(objet, true);
        }
    }

    private void ajouter(Objet item, boolean isRight) {
        if (objets == null) {
            objets = new Objets(parent.objets);
            if (isRight) {
                objets.add(item);
                if (!realisable(objets)) {
                    parent.droite = null;
                    return;
                }
            }
            // Comparaison avec la borne supérieure
            if (sup() < inf) {
                if (isRight)
                    parent.droite = null;
                else
                    parent.gauche = null;
                return;
            }
            initialiserFils();
        } else {
            if (droite != null)
                droite.ajouter(item, true);
            if (gauche != null)
                gauche.ajouter(item, false);
        }
    }

    private double sup() {
        // Objets du sac
        double sup = objets.getValeur();

        // Objets restants
        int indexSuivant = this.profondeur() - 1;
        List<Objet> restants = sac.sub(indexSuivant, sac.size());
        for (Objet o : restants)
            sup += o.getValeur();

        return sup;
    }

    private int profondeur() {
        if (parent == this)
            return 1;
        return 1 + parent.profondeur();
    }

    private boolean realisable(Objets objets) {
        if (objets.getPoids() > sac.getPoidsMax())
            return false;

        double valeur = objets.getValeur();

        // Mise à jour de la borne inférieure
        if (valeur > inf) inf = valeur;

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Objets o : feuilles())
            sb.append(o.get()).append(System.lineSeparator());
        return sb.toString();
    }

    private List<Objets> feuilles() {
        List<Objets> feuilles = new ArrayList<>();
        parcours(feuilles);
        return feuilles;
    }

    private void parcours(List<Objets> feuilles) {
        boolean e = false;
        if (existe(gauche)) {
            gauche.parcours(feuilles);
            e = true;
        }
        if (existe(droite)) {
            droite.parcours(feuilles);
            e = true;
        }
        if (!e) feuilles.add(objets);
    }

    private boolean existe(BSTree b) {
        return b != null && b.objets != null;
    }

    public Objets solution() {
        List<Objets> feuilles = feuilles();

        Objets solution = new Objets();
        double poidsSolution = 0.0, valeurSolution = 0.0;

        for (Objets obs : feuilles) {
            double poids = obs.getPoids();
            double valeur = obs.getValeur();
            /*
            Deux cas de figure :
            - la valeur des objets est plus grande que la solution courante
            - la valeur des objets est la même que la solution courante mais son poids est plus faible (optimal)
            */
            if (valeur > valeurSolution || (valeur == valeurSolution && poids < poidsSolution)) {
                poidsSolution = poids;
                valeurSolution = valeur;
                solution = obs;
            }
        }
        return solution;
    }
}
