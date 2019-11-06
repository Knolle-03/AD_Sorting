package de.hawh.ld.sorting;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;


public  class KNearSort {
    
    public static void sort(Comparable[] a, int k ){

        // initialize a MinPQ with a capacity of k + 1 elements
        MinPQ<Comparable> pq = new MinPQ<>(k + 1);


        // insert first k + 1 element in the pq.
        for (int i = 0; i < k + 1 ; i++) {
            pq.insert(a[i]);
        }

        // insert the minimum of the MinPQ into the array
        // for each inserted element add one which is k + 1 indices away.
        int index = 0;
        for (int i = k + 1; i < a.length; i++) {
            a[index++] = pq.delMin();
            pq.insert(a[i]);
        }
        // insert remaining elements of the MinPQ into the array
        while (!pq.isEmpty()) {
            a[index++] = pq.delMin();
        }
    }
    // checks if a given array is sorted
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // compares two elements and returns
    // a negative int if the first one is smaller
    // 0 if they are the same
    // a positive int if the first one is larger
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    public static void main(String[] args) {
        int n = 200;
        Integer[] intArr2 = new Integer[n];
        for (int i = 0; i < n; i++) {
            intArr2[i] = i;
        }
        int k = 2;
        int lo = 0;
        int hi = k + 1;
        System.out.println(Arrays.toString(intArr2));


        for (int i = 0; i < intArr2.length/(k + 2) ; i++) {
            StdRandom.shuffle(intArr2, lo, hi);
            lo = hi;
            hi = lo + k + 1;
        }

        System.out.println(Arrays.toString(intArr2));

        KNearSort.sort(intArr2, k);
        System.out.println(Arrays.toString(intArr2));
        System.out.println(isSorted(intArr2));

    }


    
}
