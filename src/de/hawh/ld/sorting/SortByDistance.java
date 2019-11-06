package de.hawh.ld.sorting;

import java.util.Comparator;

/**
 * Comparator used to compare two vectors by their distance to a third vector.
 */
public class SortByDistance implements Comparator<Vector> {
    private Vector vector = new Vector(4);

    /**
     * compares two given vectors to the null vector regarding their distance to it.
     * @param v1 vector 1
     * @param v2 vector 2
     * @return 1 if distance of vector 1 to null vector is bigger than distance of
     *         vector 2 to null vector, 0 if distance is equal, -1 otherwise.
     */
    @Override
    public int compare(Vector v1, Vector v2) {
        if(v1.distanceTo(vector) == v2.distanceTo(vector)) return 0;
        else return v1.distanceTo(vector) > v2.distanceTo(vector) ? 1 : -1;
    }
}