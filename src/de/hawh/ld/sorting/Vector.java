package de.hawh.ld.sorting;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

public class Vector implements Serializable {

    public static final Vector vector = new Vector(0.0, 0.0, 0.0);

    private static final long sID = 1_231_123_123_545_472L;
    private int d;               // dimension of the vector
    private double[] data;       // array of vector's components

    public class distComparator implements Comparator<Vector> {

        @Override
        public int compare(Vector i, Vector j){
            if(i.distanceTo2(vector)<j.distanceTo2(vector)){
                return -1;
            }
            if(i.distanceTo2(vector)>j.distanceTo2(vector)){
                return 1;
            }
            return 0;
        }
    }

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
    public double distanceTo2(Vector that) {
        if (this.d != that.d) throw new IllegalArgumentException("Dimensions don't agree");
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


        Vector v1 = new Vector(1.0, 32.0283, 4234, 234.234);
        Vector v2 = new Vector(3.0, 3.232, 234234.324, 43274.98);
        Vector v3 = new Vector(641.0, 2233.232, 2374, 49023.98);
        Vector v4 = new Vector(3562.0283, 283.23223, 987.3874893, 387493.9304729);

        Vector vn[] = {v1, v2, v3, v4};

        for (int i = 0; i < vn.length; i++) {
            System.out.println(v1.distanceTo2(vn[i]));
        }

    }

}
