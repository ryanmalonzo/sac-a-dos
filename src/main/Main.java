package main;

import resolution.Methode;
import sacados.SacADos;

public class Main {
    public static void main(String[] args) {
        double poidsMax = Double.parseDouble(args[1]);
        SacADos sac = new SacADos(args[0], poidsMax);

        Methode m;
        switch (args[2]) {
            case "gloutonne" -> m = Methode.GLOUTONNE;
            case "dynamique" -> m = Methode.DYNAMIQUE;
            case "pse" -> m = Methode.PSE;
            default -> throw new IllegalStateException("Unexpected value: " + args[2]);
        }

        sac.resoudre(m);
    }
}
