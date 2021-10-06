package resolution.utils;

import objet.Objet;

import java.util.Collections;
import java.util.List;

public class Quicksort {
    private static void swap(List<Objet> l, int a, int b) {
        Collections.swap(l, a, b);
    }

    private static int partition(List<Objet> l, int first, int last) {
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

    public static void quicksort(List<Objet> l, int first, int last) {
        if (first < last) {
            int pivot = partition(l, first, last);
            quicksort(l, first, pivot - 1);
            quicksort(l, pivot + 1, last);
        }
    }
}
