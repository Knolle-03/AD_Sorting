package de.hawh.ld.sorting;


import edu.princeton.cs.algs4.QuickX;

import java.util.Arrays;

public  class KNearSort {
    
    public static void sort(Comparable[] a, int k ){
        int lo = 0;
        int hi = 2 * k;

        while (!isSorted(a)){
            if(hi > a.length - 1) hi = a.length - 1;


            // Temporary array
            Comparable[] tmp = new Comparable[hi - lo + 1];
            int j = 0;
            for (int i = lo; i <= hi; i++) {
                tmp[j] = a[i];
                j++;
            }

            // Sort the temporary array
            QuickX.sort(tmp);

            // Modifying original array with temporary array elements
            j = 0;
            for (int i = lo; i <= hi; i++) {
                a[i] = tmp[j];
                j++;
            }

            lo +=k;
            hi +=k;

            System.out.println(Arrays.toString(a));
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
//        Integer[] intArr2 = new Integer[10000];
//        while(intArr2[intArr2.length -1] == null) {
//            for (int i = 0; i <; i++) {
//
//            }
//        }
        Integer[] intArr = {2, 1, 3, 5, 4, 6, 9, 8, 7, 12, 11, 10, 15, 14, 13};
        KNearSort.sort(intArr, 2);
    }


    
}
