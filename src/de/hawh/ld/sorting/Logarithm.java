package de.hawh.ld.sorting;

public class Logarithm {

    public static double logb(double a, double b) {
        return Math.log(a) / Math.log(b);
    }

    public static double log2(double a) {
        return logb(a, 2);
    }

    public static void main(String[] args) {

        for (int i = 1; i < 20 ; i++) {
            System.out.println(Math.floor(log2(i)));
        }
    }
}
