package resolution.methodes;

import resolution.utils.ABR;
import sacados.SacADos;

import java.util.ArrayList;
import java.util.List;

public class PSE extends Methode {
    public void resoudre(SacADos sac) {
        int t = sac.getObjets().size();
        ABR a = new ABR();
        a.ajouter(new ArrayList<>()); // initialisation vide
        for (int i = 1; i <= t; ++i) {
            a.ajouter(new ArrayList<>(List.of(i)));
        }
    }
}
