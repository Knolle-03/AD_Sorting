package de.hawh.ld.sorting;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdRandom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides methods to create a file with random vectors (4 dimensions) and
 * reading those files, creating a MinPQ of vectors.
 */
public class TestADT {

    /**
     * Creates a file of vectors with 4 dimensions
     * @param n number of vectors to write in file
     * @param filename name of file
     */
    public static void createVectorList(int n, String filename) {
        filename = filename.equals("") ? "vectors.txt" : filename;

        File file = new File(filename);
        try (FileWriter fr = new FileWriter(file)) {
            for (int i = 0; i < n; i++) {
                Vector v = new Vector(StdRandom.uniform(-100.0, 100.0), StdRandom.uniform(-100.0, 100.0), StdRandom.uniform(-100.0, 100.0), StdRandom.uniform(-100.0, 100.0));
                fr.write(v.toString() + "\n");
            }
            System.out.println("Wrote " + n + " vectors to " + filename + ".");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: could not write to " + filename + ".");
        }
    }

    /**
     * Reads a file of vectors and returns a MinPQ of those vectors
     * @param filename name of the file to read the vectors from
     * @return  a priority queue of the read vectors
     * @throws FileNotFoundException if file doesn't exist or cannot be read
     */
    public static MinPQ<Vector> readVectorList(String filename) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(filename)).useDelimiter(Pattern.compile("[A-Za-z,=\\[\\]{}]*"));

        MinPQ<Vector> vectors = new MinPQ<>(new SortByDistance());
        Pattern p = Pattern.compile("(\\d+(?:\\.\\d+))");

        while (sc.hasNextLine()) {
            if (sc.hasNextInt()) {
                int dimension = sc.nextInt();
                double[] tmp = new double[dimension];
                Matcher m = p.matcher(sc.nextLine());

                for (int i = 0; i < dimension; i++) {
                    if (m.find()) tmp[i] = Double.parseDouble(m.group(1));
                }
                vectors.insert(new Vector(tmp));
            }
        }
        return vectors;
    }

    /**
     * Creates a file of random vectors, reads them to a prioritized queue and prints them in order of
     * their distance to the given vector
     * @param args
     */
    public static void main(String[] args) {
        //createVectorList(5000, "vectors.txt");

        MinPQ<Vector> vectors = null;
        try {
            vectors = TestADT.readVectorList("vectors.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            vectors = new MinPQ<Vector>(new SortByDistance());
        }

        for (int i = 0; i < 10 ; i++) {
            //System.out.println(test.getVectors().min());
            System.out.println(vectors.delMin().distanceTo(new Vector(0.0,0.0,0.0,0.0)));

        }

    }
}
