package methodes;

import objet.Objet;
import utilitaires.BTreePSE;
import sacados.SacADos;

import java.util.List;

public class PSE extends Methode {
    public void resoudre(SacADos sac) {
        BTreePSE bt = new BTreePSE(sac);
        bt.construire();

        List<Objet> contenu = bt.solution();
        double poids = 0.0, valeur = 0.0;
        for (Objet o : contenu) {
            poids += o.getPoids();
            valeur += o.getValeur();
        }

        afficher(contenu, poids, valeur);
        //System.out.println(bt);
    }
}
