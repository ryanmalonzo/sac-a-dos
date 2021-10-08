package resolution.methodes;

import resolution.utils.ArbreBinaire;
import sacados.SacADos;

public class PSE extends Methode {
    public void resoudre(SacADos sac) {
        ArbreBinaire ab = new ArbreBinaire(sac);
        ab.construire();

        // Affichage inversé
        String[] lines = ab.toString().split("\\n");
        StringBuilder sb = new StringBuilder();
        for (int i = lines.length - 1; i >= 0; --i)
            sb.append(lines[i]).append(System.lineSeparator());
        System.out.println(sb);
    }
}
