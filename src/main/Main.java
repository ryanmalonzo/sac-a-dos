package main;

import resolution.methodes.*;
import sacados.IMethode;
import sacados.SacADos;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        double poidsMax = Double.parseDouble(args[1]);
        if (poidsMax <= 0.0)
            throw new IllegalArgumentException("Poids max inférieur ou égal à 0");

        SacADos sac = new SacADos(args[0], poidsMax);

        IMethode m;
        switch (args[2]) {
            case "gloutonne" -> m = new Gloutonne();
            case "dynamique" -> m = new Dynamique();
            case "pse" -> m = new PSE();
            default -> throw new IllegalArgumentException("Nom de méthode inconnue : " + args[2]);
        }

        sac.resoudre(m);
    }
}
