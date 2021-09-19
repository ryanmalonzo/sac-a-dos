package resolution.algorithmes;

import objet.Objet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Quicksort {
    private static void swap(Objet[] t, int a, int b) {
        Objet tmp = t[a];
        t[a] = t[b];
        t[b] = tmp;
    }

    private static int partition(Objet[] t, int first, int last) {
        Objet pivot = t[last];
        int j = first;
        for (int i = first; i < last; ++i) {
            if (t[i].compareTo(pivot) <= 0) {
                swap(t, i, j);
                ++j;
            }
        }
        swap(t, last, j);
        return j;
    }

    public static void quicksort(Objet[] t, int first, int last) {
        if (first < last) {
            int pivot = partition(t, first, last);
            quicksort(t, first, pivot - 1);
            quicksort(t, pivot + 1, last);
        }
    }

    /*public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        for (int i = 1; i <= 20; ++i)
            l.add(i);
        Collections.shuffle(l);
        int[] t = l.stream().mapToInt(Integer::intValue).toArray(); // conversion AL<Integer> => int[]
        quicksort(t, 0, t.length - 1);
        System.out.println(Arrays.toString(t));
    }*/
}
