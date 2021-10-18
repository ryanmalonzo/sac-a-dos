package main;

import methodes.Dynamique;
import methodes.Gloutonne;
import methodes.PSE;
import sacados.IMethode;
import sacados.SacADos;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3)
            throw new IllegalArgumentException("Nombre insuffisant de paramètres.");

        double poidsMax = Double.parseDouble(args[1]);
        if (poidsMax <= 0.0)
            throw new IllegalArgumentException("Poids max inférieur ou égal à 0.0");

        SacADos sac = new SacADos(args[0], poidsMax);

        IMethode m;
        switch (args[2]) {
            case "gloutonne":
                m = new Gloutonne();
                break;
            case "dynamique":
                m = new Dynamique();
                break;
            case "pse":
                m = new PSE();
                break;
            default:
                throw new IllegalArgumentException("Nom de méthode inconnue : " + args[2]);
        }

        long start = System.nanoTime();
        sac.resoudre(m);
        long end = System.nanoTime();

        sac.afficher(m);

        double time = (double) (end - start);
        System.out.println("Temps de résolution : " + String.format("%.3f", time / Math.pow(10, 9)) + " s (" +
                String.format("%.0f", time / Math.pow(10, 6)) + " ms)");
    }
}
