package methodes;

import objets.Objet;
import objets.Objets;
import sacados.SacADos;

public class Gloutonne extends Methode {
    private Objets contenu;
    private double poids;
    private double valeur;

    public void resoudre(SacADos sac) {
        Objets objets = sac.getObjets();
        quicksort(objets); // Tri rapide par comparaison de ratio valeur/poids

        contenu = new Objets();
        valeur = 0.0;
        poids = 0.0;
        for (Objet objet : objets) {
            if (poids + objet.getPoids() <= sac.getPoidsMax()) {
                contenu.add(objet);
                valeur += objet.getValeur();
                poids += objet.getPoids();
            }
        }
    }

    public void afficher() {
        afficher(contenu, poids, valeur);
    }
}
