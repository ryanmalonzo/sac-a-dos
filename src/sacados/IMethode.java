package sacados;

public interface IMethode {
    /**
     * Résout le problème du sac à dos selon l'implémentation de méthode utilisée
     *
     * @param sac Le sac à dos à résoudre
     */
    void resoudre(SacADos sac);

    /**
     * Affiche le résultat de la résolution d'un sac à dos
     */
    void afficher();
}
