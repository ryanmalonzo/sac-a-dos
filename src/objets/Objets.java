package objets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Wrapper pour une liste d'Objet qui permet
 * de ne pas calculer le poids et la valeur cumulés des objets
 * s'il n'y a pas besoin de le faire
 */
public class Objets implements Iterable<Objet> {
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

    /**
     * Ajoute un objet à la liste et marque le calcul du poids
     * et de la valeur de la liste pour actualisation
     *
     * @param objet L'objet à ajouter
     */
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

    /**
     * Calcule le poids des objets si besoin et le renvoie
     *
     * @return Le poids de l'ensemble des objets de la liste
     */
    public double poids() {
        if (poidsUpdated) return poids;
        poids = 0.0;
        for (Objet o : objets) poids += o.getPoids();
        poidsUpdated = true;
        return poids;
    }

    /**
     * Calcule la valeur des objets si besoin et la renvoie
     *
     * @return La valeur de l'ensemble des objets de la liste
     */
    public double valeur() {
        if (valeurUpdated) return valeur;
        valeur = 0.0;
        for (Objet o : objets) valeur += o.getValeur();
        valeurUpdated = true;
        return valeur;
    }

    @Override
    public Iterator<Objet> iterator() {
        return objets.iterator();
    }
}
