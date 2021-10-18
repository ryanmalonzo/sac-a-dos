package sacados;

import objets.Objet;
import objets.Objets;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

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
    }

    /**
     * Résout le sac à dos selon la méthode passée en paramètre
     *
     * @param methode La méthode à utiliser pour la résolution
     */
    public void resoudre(IMethode methode) {
        if (objets == null)
            parse(chemin);

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
            Scanner sc = new Scanner(new File(chemin));

            objets = new Objets();
            // Stocke les objets
            while (sc.hasNextLine()) {
                String[] s = sc.nextLine().split(" ; ");
                objets.add(new Objet(s[0], Double.parseDouble(s[1]), Double.parseDouble(s[2])));
            }

            sc.close();
        } catch (FileNotFoundException e) {
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
