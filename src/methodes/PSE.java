package methodes;

import utilitaires.BTreePSE;
import sacados.SacADos;

public class PSE extends Methode {
    public void resoudre(SacADos sac) {
        BTreePSE ab = new BTreePSE(sac);
        ab.construire();
        System.out.println(ab);
        System.out.println(BTreePSE.getInf());
    }
}
