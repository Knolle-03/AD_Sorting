package de.hawh.ld.sorting;


import edu.princeton.cs.algs4.StdRandom;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;



public class Vector {


    private int d;               // dimension of the vector
    private double[] data;       // array of vector's components

//    @Override
//    public int compareTo(Vector v) {
//        Vector vector = new Vector(0.0, 0.0, 0.0);
//        return Double.compare(this.distanceTo(vector), v.distanceTo(vector));
//    }



    /**
     * Initializes a d-dimensional zero vector.
     *
     * @param d the dimension of the vector
     */
    public Vector(int d) {
        this.d = d;
        data = new double[d];
    }

    /**
     * Initializes a vector from either an array or a vararg list.
     * The vararg syntax supports a constructor that takes a variable number of
     * arugments such as Vector x = new Vector(1.0, 2.0, 3.0, 4.0).
     *
     * @param a the array or vararg list
     */
    public Vector(double ... a) {
        d = a.length;

        // defensive copy so that client can't alter our copy of data[]
        data = new double[d];
        for (int i = 0; i < d; i++)
            data[i] = a[i];
    }

    /**
     * Returns the Euclidean distance between this vector and the specified vector.
     *
     * @param that the other vector
     * @return the Euclidean distance between this vector and that vector
     * @throws IllegalArgumentException if the dimensions of the two vectors are not equal
     */
    public double distanceTo(Vector that) {
        if (this.d != that.d) throw new IllegalArgumentException("Dimensions don't match");
        double sum = 0.0;
        for (int i = 0; i < this.d ; i++) {
            sum = sum + ((that.data[i] - this.data[i]) * (that.data[i] - this.data[i]));
        }
        return Math.sqrt(sum);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "d=" + d +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    public int getDimension() {
        return d;
    }

    public double[] getData() {
        return data;
    }


    public static void main(String[] args) {
        Vector v1 = new Vector(0.0, 0.0, 0.0, 0.0);
        long n = 26_000_000L;
        int m = 10;
        MinPQ<Vector> vectorPQ = new MinPQ<>(10, new SortByDistance());
        //Vector[] vn = new Vector[n];
        for (int i = 0; i < n ; i++) {
            Vector v = new Vector(StdRandom.uniform(-1000.0, 1000.0),
                                  StdRandom.uniform(-1000.0, 1000.0),
                                  StdRandom.uniform(-1000.0, 1000.0),
                                  StdRandom.uniform(-1000.0, 1000.0));
            vectorPQ.insert(v);
           // System.out.println(v.distanceTo(v1));
        }

//
//        Vector v1 = new Vector(1.0, 32.0283, 4234, 234.234);
//        Vector v2 = new Vector(3.0, 3.232, 234234.324, 43274.98);
//        Vector v3 = new Vector(641.0, 2233.232, 2374, 49023.98);
//        Vector v4 = new Vector(3562.0283, 283.23223, 987.3874893, 387493.9304729);

        System.out.println("=================================");

        for (int i = 0; i < m ; i++) {
            System.out.println(vectorPQ.delMin().distanceTo(v1));
        }
    }

}
