package de.hawh.ld.sorting;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class Draw {

    public static void draw(Double[] doubleArray){
        StdDraw.setPenColor(Color.RED);
        int n = doubleArray.length;
        for (int i = 0; i < doubleArray.length ; i++) {
            double x = 1.0 * i / n;
            double y = doubleArray[i];
            double rw = 0.25 / n;
            double rh = doubleArray[i] / 2.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }
}
