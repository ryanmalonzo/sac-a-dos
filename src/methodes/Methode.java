package methodes;

import objets.Objet;
import objets.Objets;
import sacados.IMethode;

import java.text.DecimalFormat;

/**
 * Classe abstraite de méthode contenant les fonctions communes à chaque implémentation
 * pour le tri et l'affichage du résultat d'une résolution
 */
public abstract class Methode implements IMethode {
    /**
     * Trie une liste d'objets avec l'algorithme de tri rapide
     * @param l La liste d'objets
     */
    protected static void quicksort(Objets l) {
        utilitaires.Quicksort.quicksort(l, 0, l.size() - 1);
    }

    /**
     * Affiche le résultat d'une méthode de résolution du sac à dos
     * @param contenu Les objets composant le sac à dos à l'issue de la méthode
     * @param valeur La valeur totale du sac à dos
     * @param poids Le poids total du sac à dos
     */
    protected static void afficher(Objets contenu, double poids, double valeur) {
        // Formatage à deux chiffres après la virgule
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);

        System.out.println("Contenu du sac :");
        for (Objet objet : contenu.get())
            System.out.printf("%s, poids %5s, valeur %5s\n", objet.getNom(),
                    df.format(objet.getPoids()), df.format(objet.getValeur()));

        System.out.println();
        System.out.println("Poids total : " + df.format(poids) + ", valeur totale : " + df.format(valeur));
        System.out.println();
    }
}
