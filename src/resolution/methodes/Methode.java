package resolution.methodes;

import objet.Objet;
import sacados.IMethode;

import java.text.DecimalFormat;
import java.util.List;

public abstract class Methode implements IMethode {
    protected static void quicksort(List<Objet> l) {
        resolution.algorithmes.Quicksort.quicksort(l, 0, l.size() - 1);
    }

    protected static void afficher(List<Objet> contenu, double valeur, double poids) {
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);

        System.out.println("Contenu du sac :");
        for (Objet objet : contenu)
            System.out.println(objet.getNom() + ", valeur " + df.format(objet.getValeur()) + ", poids " +
                    df.format(objet.getPoids()));

        System.out.println();
        System.out.println("Valeur totale : " + df.format(valeur) + ", poids total : " + df.format(poids));
        System.out.println();
    }
}
