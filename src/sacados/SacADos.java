package sacados;

import objet.Objet;
import resolution.Methode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SacADos {
    private String chemin;
    private double poidsMax;
    private List<Objet> objets;

    public SacADos() {}

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

            objets = new ArrayList<>();
            // Stocke les objets
            while (sc.hasNextLine()) {
                String[] s = sc.nextLine().split(" ; ");
                objets.add(new Objet(s[0], Double.parseDouble(s[1]), Double.parseDouble(s[2])));
            }

            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public double getPoidsMax() {
        return poidsMax;
    }

    public List<Objet> getObjets() {
        return objets;
    }
}
