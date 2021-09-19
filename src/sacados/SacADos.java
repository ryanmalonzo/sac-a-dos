package sacados;

import java.util.List;
import java.util.ArrayList;
import objet.Objet;

public class SacADos {
    private String chemin;
    private double poidsMax;
    private final List<Objet> objets;

    public SacADos() {
        objets = new ArrayList<>();
    }

    public SacADos(String chemin, int poidsMax) {
        this();
        this.chemin = chemin;
        this.poidsMax = poidsMax;
    }

    public static void main(String[] args) {
        SacADos sac = new SacADos();
    }
}
