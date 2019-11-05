package de.hawh.ld.sorting;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;


//Sorts a k near sorted array in N log k time - hopefully

public  class KNearSort {
    
    public static void sort(Comparable[] a, int k ){



        MinPQ<Comparable> pq = new MinPQ<>(k + 1);


        // insert first k + 1 element in the pq.
        // (k operations)
        for (int i = 0; i < k + 1 ; i++) {
            pq.insert(a[i]);
        }

        // i is the index for the remaining elements in a[]
        // and index target index of for current minimum element in
        // MinPQ.
        int index = 0;
        for (int i = k + 1; i < a.length; i++) {
            a[index++] = pq.delMin();
            pq.insert(a[i]);
        }

        while (!pq.isEmpty()) {
            a[index++] = pq.delMin();
        }
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }


    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    public static void main(String[] args) {
        int n = 200;
        Integer[] intArr2 = new Integer[n];
        for (int i = 0; i < n; i++) {
            intArr2[i] = i;
        }
        int k = 5;
        int lo = 0;
        int hi = k + 1;
        System.out.println(Arrays.toString(intArr2));


        for (int i = 0; i < intArr2.length/(k + 2) ; i++) {
            StdRandom.shuffle(intArr2, lo, hi);
            lo = hi;
            hi = lo + k + 1;
        }

        System.out.println(Arrays.toString(intArr2));

        KNearSort.sort(intArr2, 5);
        System.out.println(Arrays.toString(intArr2));
        System.out.println(isSorted(intArr2));

    }


    
}
