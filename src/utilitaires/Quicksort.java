package utilitaires;

import objets.Objet;
import objets.Objets;

import java.util.Collections;

public class Quicksort {
    private static void swap(Objets l, int a, int b) {
        Collections.swap(l.get(), a, b);
    }

    private static int partition(Objets l, int first, int last) {
        Objet pivot = l.get(last);
        int j = first;
        for (int i = first; i < last; ++i) {
            if (l.get(i).compareTo(pivot) <= 0) {
                swap(l, i, j);
                ++j;
            }
        }
        swap(l, last, j);
        return j;
    }

    public static void quicksort(Objets l, int first, int last) {
        if (first < last) {
            int pivot = partition(l, first, last);
            quicksort(l, first, pivot - 1);
            quicksort(l, pivot + 1, last);
        }
    }
}
