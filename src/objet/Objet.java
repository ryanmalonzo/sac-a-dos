package objet;

public class Objet {
    private final double valeur;
    private final double poids;

    public Objet(double valeur, double poids) {
        this.valeur = valeur;
        this.poids = poids;
    }

    public double getValeur() {
        return valeur;
    }

    public double getPoids() {
        return poids;
    }
}
