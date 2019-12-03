package de.hawh.ld.sorting;


import java.util.ArrayList;
import java.util.Collections;
import static java.lang.Math.*;

public class MinMaxHeap<T extends Comparable<T>> {

    T[] heap;

    MinMaxHeap(T[] h){
        heap = (T[]) new Comparable [h.length + 1];
        System.arraycopy(h, 0, heap, 1, h.length);
        for (int i = h.length/2; i > 0 ; i--) {
            pushDown(h, i);
        }
    }

    private void pushDown(T[] h, int i){
        if (isMinLevel(i)){
            pushDownMin(h, i);
        } else {
            pushDownMax(h, i);
        }
    }



    public void pushDownMin(T[] h, int i ){
        int smallest = indexOfSmallestDescendant(i);
        if (iHasChildren(i)){
            if (smallest > 2 * i + 1){
                if (h[smallest].compareTo(h[i]) < 0){
                    swap(h, smallest, i);
                    if (h[smallest].compareTo(h[parent(smallest)]) >= 0){
                        swap(h, smallest, parent(smallest));
                    }
                    pushDownMin(h, smallest);
                }
            } else if (h[smallest].compareTo(h[i]) >= 0){
                swap(h, smallest, i);
            }
        }
    }

    public void pushDownMax(T[] h, int i){
        int smallest = indexOfLargestDescendant(i);
        if (iHasChildren(i)){
            if (smallest > 2 * i + 1){
                if (h[smallest].compareTo(h[i]) >= 0){
                    swap(h, smallest, i);
                    if (h[smallest].compareTo(h[parent(smallest)]) < 0){
                        swap(h, smallest, parent(smallest) );
                    }
                    pushDownMax(h, smallest);
                }
            } else if (h[smallest].compareTo(h[parent(smallest)]) >= 0){
                swap(h, smallest, i);
            }
        }
    }

//    public void pushDownMinIter(T[] h, int m){
//        while(!indexOfChildren(m).isEmpty()){
//            int i = m;
//            int m = indexOfSmallestDescendant(i);
//            if (m > 2 * i + 1){
//                if (h[m].compareTo(h[i]) < 0){
//                    swap(h, m, i);
//                    if (h[m].compareTo(h[parent(m)]) >= 0){
//                        swa
//                    }
//                }
//            }
//        }
//    }


    public void pushUp(T[] h, int i){
        if (i != 1){
            if (isMinLevel(i)){
                if (h[i].compareTo(h[parent(i)]) >= 0){
                    swap(h, i, parent(i));
                    pushUpMax(h, parent(i));
                } else {
                    pushUpMin(h, i);
                }
            } else {
                if (h[i].compareTo(h[parent(i)]) < 0){
                    swap(h, i, parent(i));
                    pushUpMin(h, parent(i));
                } else {
                    pushUpMax(h, i);
                }
            }
        }
    }

    public void pushUpMin(T[] h, int i){
        if (parent(parent(i)) > 0 && h[i].compareTo(h[parent(parent(i))]) < 0){
            swap(h, i, parent(parent(i)));
            pushUpMin(h, parent(parent(i)));
        }
    }

    public void pushUpMax(T[] h, int i){
        if (parent(parent(i)) > 0 && h[i].compareTo(h[parent(parent(i))]) >= 0){
            swap(h, i, parent(parent(i)));
            pushUpMax(h, parent(parent(i)));
        }
    }

    private void swap(T[] a, int i, int j ){
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }


    private ArrayList<Integer> indexOfChildren(int i){
        ArrayList<Integer> indexOfChildren = new ArrayList<>();
        if (i * 2 + 1 <= heap.length + 1){
            indexOfChildren.add(i * 2);
            indexOfChildren.add(i * 2 + 1);
            return indexOfChildren;
        } else if (i * 2 <= heap.length + 1){
            indexOfChildren.add(i * 2);
            return indexOfChildren;
        } else {
            return indexOfChildren;
        }
    }

    private int lastDesc(int i) {

        return i*4+3 < heap.length ? i*4+3 : heap.length;
    }

    /**
     * Returns index of smallest child or grandchild, 0 if none exist.
     * @param i index of base node, i.e. (grand)parent
     * @return index of smallest child or grandchild, 0 if none exist.
     */

    private int indexOfSmallestDescendant(int i){
        if (i*2 >= heap.length) return 0;
        int smallest = i*2;
        for (int j = i*2+1; j <= lastDesc(i); j++) {
            if (heap[j].compareTo(heap[smallest]) < 0) smallest = j;
        }
        return smallest;
    }

    private int indexOfLargestDescendant(int i){
        if (i*2 >= heap.length) return 0;
        int smallest = i*2;
        for (int j = i*2+1; j <= lastDesc(i); j++) {
            if (heap[j].compareTo(heap[smallest]) > 0) smallest = j;
        }
        return smallest;
    }



    private boolean iHasChildren(int i){
        return indexOfChildren(i).size() > 0;
    }

    private int parent (int i){
        return i/2;
    }

    private boolean isMinLevel(int i){
        return log2(i) % 2 == 0;
    }

    private boolean isMaxLevel(int i){
        return !isMinLevel(i);
    }

    private static int log2(int x){
        return (int) (log(x) / log(2));
    }


    public static void main(String[] args) {



        Integer[] intArr = {13, 21, 31, 51, 46, 16, 11, 10, 31, 71, 41, 8};
        MinMaxHeap<Integer> mmh = new MinMaxHeap<>(intArr);
        System.out.println(Collections.singletonList(mmh));
    }



}
