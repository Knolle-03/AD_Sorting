package de.hawh.ld.sorting;


import edu.princeton.cs.algs4.*;


public class SortCompare {

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

    public static void main(String[] args) {
        String alg1 = "QuickX"; //args[0];
        String alg2 = "QuickXM5"; //args[1];
        int N = 26_500_000; //Integer.parseInt(args[2]);
        int T = 10; //Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);

        StdOut.printf("QuickX : %.5f \nQuickXM5 : %.5f\n" , t1, t2);
        StdOut.printf("For %d random Doubles\n    %s is", N, alg1);
        StdOut.printf(" %.5f times faster than %s\n", t2/t1, alg2);
    }
}

