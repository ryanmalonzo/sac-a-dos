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

    /**
     * Construit la racine de l'arbre
     *
     * @param sac Le sac à dos à résoudre
     */
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

    /**
     * Construit l'arbre binaire d'énumération des combinaisons possibles
     * d'objets du sac
     */
    private void construire() {
        for (Objet objet : sac) {
            gauche.ajouter(objet, false);
            droite.ajouter(objet, true);
        }
    }

    /**
     * Ajoute un objet dans un arbre
     *
     * @param objet   L'objet à ajouter
     * @param isRight Si l'arbre est le noeud droit de son parent
     */
    private void ajouter(Objet objet, boolean isRight) {
        if (objets == null) {
            objets = new Objets(parent.objets);
            if (isRight) {
                objets.add(objet);
                if (!realisable(objets)) {
                    parent.droite = null;
                    return;
                }
            }
            // Comparaison avec la borne inférieure
            if (sup() < inf) {
                if (isRight)
                    parent.droite = null;
                else
                    parent.gauche = null;
                return;
            }
            initialiserFils();
        } else {
            if (gauche != null)
                gauche.ajouter(objet, false);
            if (droite != null)
                droite.ajouter(objet, true);
        }
    }

    private int profondeur() {
        if (parent == this)
            return 1;
        return 1 + parent.profondeur();
    }

    /**
     * Détermine si les objets du noeud rentrent dans le sac
     * et met à jour la borne inférieure de l'arbre en cas
     * de meilleure solution
     *
     * @param objets Les objets à tester
     * @return Vrai ou faux selon que les objets rentrent
     * dans le sac ou non
     */
    private boolean realisable(Objets objets) {
        if (objets.getPoids() > sac.getPoidsMax())
            return false;

        double valeur = objets.getValeur();
        // Mise à jour de la borne inférieure
        if (valeur >= inf)
            inf = valeur;
        return true;
    }

    /**
     * Calcule la somme des valeurs des objets du sac
     * et de celles des objets potentiellement restants
     *
     * @return La somme (borne supérieure)
     */
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

    /**
     * Obtient la liste des noeuds terminaux de l'arbre
     *
     * @return La liste
     */
    private List<Objets> feuilles() {
        List<Objets> feuilles = new ArrayList<>();
        parcours(feuilles);
        return feuilles;
    }

    /**
     * Parcourt l'arbre en profondeur à la recherche des
     * noeuds terminaux de l'arbre
     *
     * @param feuilles La liste à peupler des objets
     *                 des noeuds terminaux
     */
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

    /**
     * Détermine la solution optimale de résolution
     * du sac à dos
     *
     * @return La solution optimale
     */
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Objets o : feuilles())
            sb.append(o.get().toString()).append(System.lineSeparator());
        return sb.toString();
    }
}
