package methodes;

import objets.Objet;
import objets.Objets;
import sacados.SacADos;

public class Dynamique extends Methode {
    public void resoudre(SacADos sac) {
        // Normalise les valeurs de poids des objets et du sac pour n'opérer
        // qu'avec des valeurs entières (accès aux cases du tableau)
        int coefficient = coefficient(sac);
        normaliser(sac, coefficient);

        Objets objets = sac.getObjets();
        int nbObjets = objets.size();
        int maxPoids = (int) sac.getPoidsMax();

        double[][] m = new double[nbObjets][maxPoids + 1];

        // Première ligne
        for (int j = 0; j <= maxPoids; ++j) {
            if (objets.get(0).getPoids() > j)
                m[0][j] = 0;
            else
                m[0][j] = objets.get(0).getValeur();
        }

        // Lignes restantes
        for (int i = 1; i < nbObjets; ++i) {
            for (int j = 0; j <= maxPoids; ++j) {
                if (objets.get(i).getPoids() > j)
                    m[i][j] = m[i - 1][j];
                else
                    m[i][j] = Math.max(m[i - 1][j],
                            m[i - 1][j - (int) objets.get(i).getPoids()] + objets.get(i).getValeur());
            }
        }

        // Poids nécessaire pour bénéfice max.
        int j = maxPoids; // (maxPoids + 1) - 1
        while (m[nbObjets - 1][j] == m[nbObjets - 1][j - 1])
            --j;

        double poids = j; // pour affichage

        // Récupérer les objets à mettre dans le sac
        Objets contenu = new Objets();
        int i = nbObjets - 1;
        while (j > 0) {
            while (i > 0 && m[i][j] == m[i - 1][j])
                --i;
            j = j - (int) objets.get(i).getPoids();
            if (j >= 0) {
                contenu.add(objets.get(i));
            }
            --i;
        }

        // Réinitialisation des poids pour affichage
        for (Objet o : contenu.get())
            o.setPoids(o.getPoids() / coefficient);
        poids = poids / coefficient;

        // Affichage des obtenus contenus dans le sac pour solution optimale
        afficher(contenu, poids, m[nbObjets - 1][maxPoids]);

        // Affichage de la matrice
        /*for (int k = 0; k < nbObjets; ++k) {
            for (int l = 0; l < maxPoids + 1; ++l)
                System.out.printf("%4s ", m[k][l]);
            System.out.println();
        }
        System.out.println();*/
    }

    /**
     * Détermine un coefficient multiplicateur à appliquer aux valeurs de poids
     * pour pouvoir utiliser l'algorithme avec des valeurs décimales
     *
     * @param sac Le sac à dos à résoudre
     * @return La valeur du coefficient (puissance de 10)
     */
    private int coefficient(SacADos sac) {
        // Récupérer le plus grand nombre de zéros après la virgule parmi les poids
        // des objets et le poids maximum du sac
        int nbDecimalesMax = 0;

        for (Objet o : sac.getObjets().get()) {
            // On récupère dans une chaîne les décimales du nombre
            String s = Double.toString(o.getPoids()).split("\\.")[1];
            if (s.length() > nbDecimalesMax)
                nbDecimalesMax = s.length();
        }

        // Poids maximum du sac
        String s2 = Double.toString(sac.getPoidsMax()).split("\\.")[1];
        if (s2.length() > nbDecimalesMax)
            nbDecimalesMax = s2.length();

        return (int) Math.pow(10, nbDecimalesMax);
    }

    /**
     * Transforme tous les poids d'un sac à dos en valeurs entières en les multipliant
     * par un coefficient
     *
     * @param sac         Le sac à dos à normaliser
     * @param coefficient Le coefficient à appliquer
     */
    private void normaliser(SacADos sac, int coefficient) {
        for (Objet o : sac.getObjets().get())
            o.setPoids(o.getPoids() * coefficient);

        sac.setPoidsMax(sac.getPoidsMax() * coefficient);
    }
}
