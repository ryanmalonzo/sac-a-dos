package objet;

public class Objet implements Comparable<Objet> {
    private final String nom;
    private final double valeur;
    private double poids;

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

    public void setPoids(double poids) {
        this.poids = poids;
    }

    /**
     * Permet la comparaison de deux objets selon le ratio valeur/poids requis pour la méthode gloutonne
     * @param o L'objet comparé à cette instance d'objet
     * @return L'entier résultat de la comparaison
     */
    @Override
    public int compareTo(Objet o) {
        double difference = (valeur / poids) - (o.valeur / o.poids);
        int r =  difference >= 0.0 ? (int) Math.ceil(difference) : (int) Math.floor(difference);
        return r * -1; // tri décroissant méthode gloutonne
    }
}
