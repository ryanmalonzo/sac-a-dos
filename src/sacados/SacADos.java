package sacados;

import objets.Objet;
import objets.Objets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class SacADos implements Iterable<Objet> {
    private String chemin;
    private Double poidsMax;
    private Objets objets;


    /**
     * Initialise un sac à dos vide
     */
    public SacADos() {
    }

    /**
     * Initialise un sac à dos
     *
     * @param chemin   Le chemin du fichier contenant ses objets formatés
     * @param poidsMax Le poids maximal supporté par le sac
     */
    public SacADos(String chemin, double poidsMax) {
        this.chemin = chemin;
        this.poidsMax = poidsMax;
        parse(chemin);
    }

    /**
     * Résout le sac à dos selon la méthode passée en paramètre
     *
     * @param methode La méthode à utiliser pour la résolution
     */
    public void resoudre(IMethode methode) {
        methode.resoudre(this);
    }

    /**
     * Parse les objets contenus dans un fichier texte
     * et les stocke dans une liste d'objets
     *
     * @param chemin Le chemin du fichier contenant les objets du sac
     */
    public void parse(String chemin) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(chemin));

            objets = new Objets();
            // Stocke les objets
            String line;
            while ((line = bf.readLine()) != null) {
                String[] s = line.split(" ; ");
                objets.add(new Objet(s[0], Double.parseDouble(s[1]), Double.parseDouble(s[2])));
            }

            bf.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Renvoie une sous-liste de la liste d'objets
     *
     * @param fromIndex L'index de début
     * @param toIndex   L'index de fin (exclusif)
     * @return La sous-liste d'objets
     */
    public List<Objet> sub(int fromIndex, int toIndex) {
        return objets.get().subList(fromIndex, toIndex);
    }

    public int size() {
        return objets.size();
    }

    /**
     * Affiche le résultat de la résolution du sac
     *
     * @param m La méthode employée
     */
    public void afficher(IMethode m) {
        m.afficher();
    }

    public double getPoidsMax() {
        return poidsMax;
    }

    public void setPoidsMax(double poidsMax) {
        this.poidsMax = poidsMax;
    }

    public Objets getObjets() {
        return objets;
    }

    public String getChemin() {
        return chemin;
    }

    @Override
    public Iterator<Objet> iterator() {
        return objets.iterator();
    }
}
