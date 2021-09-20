package resolution.methodes;

import objet.Objet;
import sacados.SacADos;

import java.util.ArrayList;
import java.util.List;

public class Dynamique extends Methode {
    public void resoudre(SacADos sac) {
        List<Objet> objets = sac.getObjets();

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

        int poids = j; // pour affichage

        // Récupérer les objets à mettre dans le sac
        List<Objet> contenu = new ArrayList<>();
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
}
