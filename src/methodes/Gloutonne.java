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
        valeur = poids = 0.0;

        double poidsMax = sac.getPoidsMax();
        for (Objet objet : objets) {
            if (poids + objet.getPoids() <= poidsMax) {
                contenu.add(objet);
                valeur += objet.getValeur();
                poids += objet.getPoids();
            }
        }

        sac.parse(sac.getChemin()); // réinitialisation
    }

    public double getValeur() {
        return valeur;
    }

    public void afficher() {
        afficher(contenu, poids, valeur);
    }
}
