package de.hawh.ld.sorting;


import edu.princeton.cs.algs4.*;



public class SortCompare {

    /**
     * Runs a timed sorting of an collection using the chosen algorithm.
     * @param alg algorithm to use for sorting
     * @param a collection to be sorted
     * @return time needed to sort
     */
    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("Selection")) Selection.sort(a);
        if (alg.equals("Shell"))     Shell.sort(a);
        if (alg.equals("Merge"))     Merge.sort(a);
        if (alg.equals("MergeBU"))   MergeBU.sort(a);
        if (alg.equals("QuickX"))    QuickX.sort(a);
        if (alg.equals("QuickXM5"))  QuickXM5.sort(a);
        if (alg.equals("Heap"))      Heap.sort(a);
        return timer.elapsedTime();
    }

    /**
     * Times the sorting of a specified array a given amount of times.
     * @param alg algorithm to sort by
     * @param N length of random array
     * @param T amount of random arrays to sort
     * @return time needed to sort all arrays
     */
    public static double timeRandomInput(String alg, int N, int T)   {

        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform();
            total += time(alg, a);
            }
        return total;
    }

    /**
     * Compares the time two algorithms need to each sort T random arrays of length N and prints the result
     * @param args
     */
    public static void main(String[] args) {
        String alg1 = "QuickXM5"; //args[0];
        String alg2 = "MergeBU";  //args[1];
        int N = 45; //Integer.parseInt(args[2]);
        int T = 100; //Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        if(alg1.equals("Merge") || alg2.equals("Merge")){
            System.out.println("Merge   arrayAccesses: " + Merge.arrayAccesses + " ratio: " + Merge.arrayAccesses/N);
        }
        if(alg1.equals("MergeBU") || alg2.equals("MergeBU")){
            System.out.println("MergeBU arrayAccesses: " + MergeBU.arrayAccesses + " ratio: " + MergeBU.arrayAccesses/N);
        }
        StdOut.printf("For %d random Doubles\n    %s is", N, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2/t1, alg2);
    }
}

