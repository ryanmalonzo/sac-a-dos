package utilitaires;

import methodes.Gloutonne;
import objets.Objet;
import objets.Objets;
import sacados.SacADos;

import java.util.ArrayList;
import java.util.List;

public class BSTree {
    private static SacADos sac;
    private static double inferieure;
    private static BSTree solution;

    private BSTree parent;
    private BSTree gauche;
    private BSTree droite;

    private int indexObjet;
    private double poids;
    private double valeur;
    private boolean contientObjet;
    private boolean isRight;

    /**
     * Construit la racine de l'arbre
     *
     * @param sac Le sac à dos à résoudre
     */
    public BSTree(SacADos sac) {
        BSTree.sac = sac;

        Gloutonne g = new Gloutonne();
        g.resoudre(sac);
        BSTree.inferieure = g.getValeur();
        BSTree.solution = null;

        parent = this;
        initialiserFils();

        indexObjet = -1;
        poids = valeur = 0.0;
        contientObjet = isRight = false;

        this.construire();
    }

    private BSTree() {
        gauche = null;
        droite = null;
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
        int index = 0;
        for (Objet objet : sac) {
            gauche.ajouter(objet, index, false);
            droite.ajouter(objet, index, true);
            ++index;
        }
    }

    /**
     * Ajoute un objet dans un arbre
     *
     * @param objet   L'objet à ajouter
     * @param index   L'index de l'objet à ajouter
     * @param isRight Si l'arbre est le noeud droit de son parent
     */
    private void ajouter(Objet objet, int index, boolean isRight) {
        if (!contientObjet) {
            poids = parent.poids;
            valeur = parent.valeur;
            indexObjet = index;
            contientObjet = true;
            this.isRight = isRight;

            if (isRight) {
                poids += objet.getPoids();
                valeur += objet.getValeur();
                if (!realisable()) {
                    parent.droite = null;
                    return;
                }
            }

            // Comparaison avec la borne inférieure
            if (superieure() < inferieure) {
                if (isRight)
                    parent.droite = null;
                else
                    parent.gauche = null;
                return;
            }
            actualiserSolution();

            initialiserFils();
        } else {
            if (gauche != null)
                gauche.ajouter(objet, index, false);
            if (droite != null)
                droite.ajouter(objet, index, true);
        }
    }

    /**
     * Actualise la solution courante
     */
    private void actualiserSolution() {
        if (solution == null)
            solution = this;
        else if (solution.valeur < valeur)
            solution = this;
        else if (solution.valeur == valeur && poids < solution.poids)
            solution = this;
    }

    /**
     * Détermine si un noeud est une solution possible du sac
     * et actualise la borne inférieure si la valeur du noeud
     * est plus élevée
     *
     * @return Vrai ou faux
     */
    private boolean realisable() {
        if (poids > sac.getPoidsMax())
            return false;

        // Mise à jour de la borne inférieure
        if (valeur > inferieure)
            inferieure = valeur;
        return true;
    }

    /**
     * Calcule la somme des valeurs des objets du sac
     * et de celles des objets potentiellement restants
     *
     * @return La somme (borne supérieure)
     */
    private double superieure() {
        double sup = valeur;

        // Objets restants
        int indexSuivant = indexObjet + 1;
        List<Objet> restants = sac.sub(indexSuivant, sac.size());
        for (Objet o : restants)
            sup += o.getValeur();
        return sup;
    }

    /**
     * Renvoie la liste d'objets solution de la
     * résolution du sac
     *
     * @return Les objets solution
     */
    public Objets solution() {
        List<Integer> i = new ArrayList<>();
        solution.indexsParents(i);

        Objets objets = new Objets();
        Objets objetsSac = sac.getObjets();
        for (Integer index : i)
            objets.add(objetsSac.get(index));
        return objets;
    }

    private void indexsParents(List<Integer> i) {
        if (parent != this) {
            if (isRight) i.add(indexObjet);
            parent.indexsParents(i);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Objet o : solution())
            sb.append(o).append(System.lineSeparator());
        return sb.toString();
    }
}
