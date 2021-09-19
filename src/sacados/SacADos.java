package sacados;

import objet.Objet;
import resolution.Methode;

public class SacADos {
    private String chemin;
    private double poidsMax;
    private Objet[] objets;

    // TODO Supprimer (constructeur temporaire)
    public SacADos(Objet[] objets, double poidsMax) {
        this.objets = objets;
        this.poidsMax = poidsMax;
    }

    public SacADos(String chemin, double poidsMax) {
        this.chemin = chemin;
        this.poidsMax = poidsMax;
    }

    public void resoudre(Methode methode) {
        switch (methode) {
            case GLOUTONNE -> Methode.Gloutonne(this);
            case DYNAMIQUE -> Methode.Dynamique(this);
            case PSE -> Methode.PSE(this);
        }
    }

    public double getPoidsMax() {
        return poidsMax;
    }

    public Objet[] getObjets() {
        return objets;
    }
}
