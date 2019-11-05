package de.hawh.ld.sorting;

import edu.princeton.cs.algs4.StdRandom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestADT {

    private MinPQ<Vector> vectors;

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

    public void readVectorList(String filename) {

        try (Scanner sc = new Scanner(new File(filename)).useDelimiter(Pattern.compile("[A-Za-z,=\\[\\]{}]*"))) {

            vectors = new MinPQ<>();
            Pattern p = Pattern.compile("(\\d+(?:\\.\\d+))");

            while (sc.hasNextLine()) {
                if (sc.hasNextInt()) {
                    int dimension = sc.nextInt();
                    double[] tmp = new double[dimension];
                    Matcher m = p.matcher(sc.nextLine());

                    for (int i = 0; i < dimension; i++) {
                        if (m.find()) tmp[i] = Double.parseDouble(m.group(1));
                    }
                    Vector vec = new Vector(tmp);
                    vectors.insert(vec);
                    System.out.println(vec);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public MinPQ<Vector> getVectors() {
        return vectors;
    }

    public void setVectors(MinPQ<Vector> vectors) {
        this.vectors = vectors;
    }

    public static void main(String[] args) {
        TestADT test = new TestADT();
        test.readVectorList("vectors.txt");
    }
}
