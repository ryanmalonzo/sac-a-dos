package methodes;

import objets.Objet;
import objets.Objets;
import sacados.SacADos;

public class Gloutonne extends Methode {
    public void resoudre(SacADos sac) {
        Objets objets = sac.getObjets();
        quicksort(objets);

        Objets contenu = new Objets();
        double valeur = 0.0, poids = 0.0;
        for (Objet objet : objets.get()) {
            if (poids + objet.getPoids() <= sac.getPoidsMax()) {
                contenu.add(objet);
                valeur += objet.getValeur();
                poids += objet.getPoids();
            }
        }

        afficher(contenu, poids, valeur);
    }
}
