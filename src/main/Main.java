package main;

import resolution.methodes.*;
import sacados.IMethode;
import sacados.SacADos;

public class Main {
    public static void main(String[] args) {
        double poidsMax = Double.parseDouble(args[1]);
        SacADos sac = new SacADos(args[0], poidsMax);

        IMethode m;
        switch (args[2]) {
            case "gloutonne" -> m = new Gloutonne();
            case "dynamique" -> m = new Dynamique();
            case "pse" -> m = new PSE();
            default -> throw new IllegalStateException("Unexpected value: " + args[2]);
        }

        sac.resoudre(m);
    }
}
