package objets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Wrapper pour une liste d'Objet
 */
public class Objets implements Iterable<Objet> {
    private final List<Objet> objets;
    private double poids;
    private double valeur;

    public Objets() {
        objets = new ArrayList<>();
        poids = valeur = 0.0;
    }

    public Objets(Objets objets) {
        this.objets = new ArrayList<>(objets.objets);
        poids = objets.poids;
        valeur = objets.valeur;
    }

    /**
     * Ajoute un objet à la liste et marque le calcul du poids
     * et de la valeur de la liste pour actualisation
     *
     * @param objet L'objet à ajouter
     */
    public void add(Objet objet) {
        objets.add(objet);
        poids += objet.getPoids();
        valeur += objet.getValeur();
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

    public double getPoids() {
        return poids;
    }

    public double getValeur() {
        return valeur;
    }

    @Override
    public Iterator<Objet> iterator() {
        return objets.iterator();
    }
}
