package de.hawh.ld.sorting;

import java.util.Comparator;

public class SortByDistance implements Comparator<Vector> {
    private Vector vector = new Vector(0.0, 0.0, 0.0, 0.0);
    @Override
    public int compare(Vector v1, Vector v2) {
        if(v1.distanceTo(vector) == v2.distanceTo(vector)) return 0;
        else return v1.distanceTo(vector) > v2.distanceTo(vector) ? 1 : -1;
    }
}