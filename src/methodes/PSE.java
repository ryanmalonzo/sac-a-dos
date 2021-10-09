package methodes;

import objets.Objets;
import utilitaires.BTreePSE;
import sacados.SacADos;

public class PSE extends Methode {
    public void resoudre(SacADos sac) {
        BTreePSE bt = new BTreePSE(sac);
        bt.construire();

        Objets contenu = bt.solution();
        double poids = contenu.poids();
        double valeur = contenu.valeur();

        afficher(contenu, poids, valeur);
        //System.out.println(bt); // Affichage des noeuds terminaux
    }
}
