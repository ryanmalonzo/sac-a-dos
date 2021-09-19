package resolution;

import objet.Objet;
import sacados.SacADos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public enum Methode {
    GLOUTONNE, DYNAMIQUE, PSE;

    // TODO keep wrapper here, move definition in separate file
    public static void Gloutonne(SacADos sac) {
        Objet[] objets = sac.getObjets();
        quicksort(objets, 0, objets.length - 1);

        List<Objet> contenu = new ArrayList<>();
        double valeur = 0.0, poids = 0.0;
        for (Objet objet : objets) {
            if (poids + objet.getPoids() <= sac.getPoidsMax()) {
                contenu.add(objet);
                valeur += objet.getValeur();
                poids += objet.getPoids();
            }
        }

        afficher(contenu, valeur, poids);
    }

    public static void Dynamique(SacADos sac) {

    }

    public static void PSE(SacADos sac) {

    }

    private static void quicksort(Objet[] t, int first, int last) {
        resolution.algorithmes.Quicksort.quicksort(t, first, last);
    }

    private static void afficher(List<Objet> contenu, double valeur, double poids) {
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
