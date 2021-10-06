package resolution.utils;

import java.util.ArrayList;
import java.util.List;

public class ABR {
    private ABR gauche;
    private ABR droite;
    private List<Integer> objets;

    public ABR() {
        gauche = null;
        droite = null;
        objets = null;
    }

    public void ajouter(List<Integer> o) {
        if (objets == null) {
            objets = o;
            gauche = new ABR();
            droite = new ABR();
        }

        // TODO Ici ? Check si worth continuer la recherche depuis ce noeud

        else {
            // Liste courante + i
            List<Integer> od = new ArrayList<>(objets);
            od.add(o.get(0));
            droite.ajouter(od);

            gauche.ajouter(new ArrayList<>(objets)); // copie
        }
    }

    public boolean noeudVide() {
        return false;
    }
}
