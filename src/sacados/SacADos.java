package sacados;

import objet.Objet;
import resolution.Methode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SacADos {
    private final String chemin;
    private final double poidsMax;
    private Objet[] objets;

    public SacADos(String chemin, double poidsMax) {
        this.chemin = chemin;
        this.poidsMax = poidsMax;
    }

    public void resoudre(Methode methode) {
        if (objets == null)
            parse(chemin);

        switch (methode) {
            case GLOUTONNE -> Methode.Gloutonne(this);
            case DYNAMIQUE -> Methode.Dynamique(this);
            case PSE -> Methode.PSE(this);
        }
    }

    private void parse(String chemin) {
        try {
            Scanner sc = new Scanner(new File(chemin));

            int nbObjets = nbLignes(chemin);

            // Stocke les objets
            Objet[] obj = new Objet[nbObjets];
            int i = 0;
            while (sc.hasNextLine()) {
                String[] s = sc.nextLine().split(" ; ");
                obj[i] = new Objet(s[0],
                        Double.parseDouble(s[1]), Double.parseDouble(s[2]));
                ++i;
            }

            sc.close();
            objets = obj;
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private int nbLignes(String chemin) {
        int nbLignes = 0;

        try {
            Scanner sc = new Scanner(new File(chemin));
            while (sc.hasNextLine()) {
                sc.nextLine();
                ++nbLignes;
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return nbLignes;
    }

    public double getPoidsMax() {
        return poidsMax;
    }

    public Objet[] getObjets() {
        return objets;
    }
}
