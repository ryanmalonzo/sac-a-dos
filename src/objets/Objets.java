package objets;

import java.util.ArrayList;
import java.util.List;

public class Objets {
    private final List<Objet> objets;
    private double poids;
    private double valeur;

    private boolean poidsUpdated;
    private boolean valeurUpdated;

    public Objets() {
        objets = new ArrayList<>();
        poids = 0.0;
        valeur = 0.0;
        poidsUpdated = false;
        valeurUpdated = false;
    }

    public void add(Objet objet) {
        objets.add(objet);
        poidsUpdated = valeurUpdated = false;
    }

    public List<Objet> get() {
        return objets;
    }

    public Objet get(int i) {
        return objets.get(i);
    }

    public int size() {
        return objets.size();
    }

    public double poids() {
        if (poidsUpdated) return poids;
        poids = 0.0;
        for (Objet o : objets) poids += o.getPoids();
        poidsUpdated = true;
        return poids;
    }

    public double valeur() {
        if (valeurUpdated) return valeur;
        valeur = 0.0;
        for (Objet o : objets) valeur += o.getValeur();
        valeurUpdated = true;
        return valeur;
    }
}
