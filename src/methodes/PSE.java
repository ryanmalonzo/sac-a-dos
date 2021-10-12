package methodes;

import objets.Objets;
import sacados.SacADos;
import utilitaires.BTreePSE;

public class PSE extends Methode {
    private Objets contenu;
    private double poids;
    private double valeur;

    public void resoudre(SacADos sac) {
        BTreePSE bt = new BTreePSE(sac);
        bt.construire();

        contenu = bt.solution();
        poids = contenu.poids();
        valeur = contenu.valeur();
    }

    public void afficher() {
        afficher(contenu, poids, valeur);
        //System.out.println(bt); // Affichage des noeuds terminaux
    }
}
