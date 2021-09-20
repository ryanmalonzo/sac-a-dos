package objet;

public class Objet implements Comparable<Objet> {
    private final String nom;
    private final double valeur;
    private final double poids;

    public Objet(String nom, double poids, double valeur) {
        this.nom = nom;
        this.poids = poids;
        this.valeur = valeur;
    }

    public String getNom() {
        return nom;
    }

    public double getPoids() {
        return poids;
    }

    public double getValeur() {
        return valeur;
    }

    @Override
    public int compareTo(Objet o) {
        double difference = (valeur / poids) - (o.valeur / o.poids);
        int r =  difference >= 0.0 ? (int) Math.ceil(difference) : (int) Math.floor(difference);
        return r * -1; // tri décroissant méthode gloutonne
    }
}
