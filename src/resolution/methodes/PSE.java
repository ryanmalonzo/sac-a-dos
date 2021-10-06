package resolution.methodes;

import resolution.utils.ArbreBinaire;
import sacados.SacADos;

import java.util.ArrayList;

public class PSE extends Methode {
    public void resoudre(SacADos sac) {
        ArbreBinaire ab = new ArbreBinaire(sac.getPoidsMax());
        construire(ab, sac.getObjets().size());

        // Affichage inversé
        String[] lines = ab.toString().split("\\n");
        StringBuilder sb = new StringBuilder();
        for (int i = lines.length - 1; i >= 0; --i)
            sb.append(lines[i]).append(System.lineSeparator());
        System.out.println(sb);
    }

    /**
     * Construit un arbre binaire d'énumération de combinaisons
     * d'objets de sac en fonction du nombre d'objets
     * @param ab L'arbre binaire
     * @param n Le nombre d'objets
     */
    private static void construire(ArbreBinaire ab, int n) {
        ab.ajouter(new ArrayList<>(), null); // initialisation vide
        for (int i = 1; i <= n; ++i)
            ab.ajouter(null, i);
    }
}
