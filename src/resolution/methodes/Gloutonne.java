package resolution.methodes;

import objet.Objet;
import sacados.SacADos;

import java.util.ArrayList;
import java.util.List;

public class Gloutonne extends Methode {
    public void resoudre(SacADos sac) {
        List<Objet> objets = sac.getObjets();
        quicksort(objets);

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
}
