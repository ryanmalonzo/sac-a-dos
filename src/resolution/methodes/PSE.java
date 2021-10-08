package resolution.methodes;

import resolution.utils.ArbreBinaire;
import sacados.SacADos;

import java.util.List;

public class PSE extends Methode {
    public void resoudre(SacADos sac) {
        ArbreBinaire ab = new ArbreBinaire(sac);
        ab.construire();
        System.out.println(ab);
    }
}
