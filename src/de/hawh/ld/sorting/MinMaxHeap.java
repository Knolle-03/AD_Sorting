package de.hawh.ld.sorting;


import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import static java.lang.Math.*;

public class MinMaxHeap<T extends Comparable<T>> {

    private T[] heap;
    private int size;

    public MinMaxHeap(T[] h){
        heap = (T[]) new Comparable [h.length + 1];
        System.arraycopy(h, 0, heap, 1, h.length);
        System.out.println(Arrays.toString(heap));
        for (int i = (heap.length/2); i > 0 ; i--) {
            pushDown(heap, i);
        }
    }

    /**
     * Creates a minMaxHeap of type T
     * @param cap indicates the initial capacity of the minMaxHeap
     */
    public MinMaxHeap(int cap){
        heap = (T[]) new Comparable [cap + 1];
    }

    /**
     * Returns the minimum of the heap in constant time.
     * @return minimum of the heap.
     */
    public T[] findMin(){
        T[] copy = (T[]) new Comparable[1];
        System.arraycopy(heap, 1,copy, 0, 1);
        return copy ;
    }


    /**
     * Returns the maximum of the heap in constant time.
     * @return maximum of the heap.
     */
    public T findMax(){
       if (size < 1) return null;
       if (size == 1) return heap[1];
       if (size == 2) return heap[2];
       else {
          return (heap[2].compareTo(heap[3]) < 0) ? heap[2] : heap[3];
       }
    }


    /**
     * Inserts the given element into the heap.
     * @param element that is to be inserted
     */
    public void insert(T element){
        if (heap.length > size){
            heap[size + 1] = element;
            size++;
            pushUp(heap, size);
            pushUp(heap, size);
        }
    }



    /**
     * Removes the minimum in log(n) time.
     * @return the smallest element of the heap.
     */
    public T removeMin(){
        if(heap.length > size/4) resize(-1);
        T retVal = heap[1];
        heap[1] = heap[size + 1];
        heap[size + 1] = null;
        pushDown(heap, 1);
        size--;
        return retVal;
    }


    /**
     * Removes the maximum in log(n) time.
     * @return the largest element of the heap.
     */
    public T removeMax(){
        if (size < 1) return null;
        if (size == 1 ){
            T retVal = heap[1];
        }
        T retVal = findMax();
        if (heap[2].equals(findMax())) {
            heap[2] = heap[size + 1];
            heap[size + 1] = null;
            pushDown(heap, 2);

        } else {
            heap[3] = heap[size + 1];
            heap[size + 1] = null;
            pushDown(heap, 2);
        }
        return retVal;
    }


    /**
     * The array is resized if there are too many or too few null references in it.
     * if the length of the heap is four times bigger than the size it is halved
     * if the length is equal to the size and an element is inserted it's doubled in length.
     * @param type indicates which action is performed (1 -> double the length, -1 -> half the length)
     */
    private void resize(int type){
        if (type == 1){
            T[] newHeap = heap;
            heap = (T[]) new Comparable[newHeap.length * 2];
            System.arraycopy(newHeap, 1, heap, 1, newHeap.length);
        } else {
            T[] newHeap = (T[]) new Comparable[heap.length/2];
            System.arraycopy(heap, 1, newHeap, 1, (heap.length/4) + 1);
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
        if (iHasChildren(i)){
            int m = indexOfSmallestDescendant(i);
            if (m > 2 * i + 1){
                if (h[m].compareTo(h[i]) < 0){
                    swap(h, m, i);
                    if (h[m].compareTo(h[parent(m)]) > 0){
                        swap(h, m, parent(m));
                    }
                    pushDownMin(h, m);
                }
            } else if (h[m].compareTo(h[i]) < 0){
                swap(h, m, i);
            }
        }
    }

    public void pushDownMax(T[] h, int i){
        int largest = indexOfLargestDescendant(i);
        if (iHasChildren(i)){
            int m = indexOfLargestDescendant(i);
            if (m > 2 * i + 1){
                if (h[m].compareTo(h[i]) > 0){
                    swap(h, m, i);
                    if (h[m].compareTo(h[parent(m)]) < 0){
                        swap(h, m, parent(m) );
                    }
                    pushDownMax(h, m);
                }
            } else if (h[m].compareTo(h[i]) > 0){
                swap(h, m, i);
            }
        }
    }


    public void pushUp(T[] h, int i){
        if (i != 1){
            if (isMinLevel(i)){
                if (h[i].compareTo(h[parent(i)]) > 0){
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
        if (parent(parent(i)) > 0 && h[i].compareTo(h[parent(parent(i))]) > 0){
            swap(h, i, parent(parent(i)));
            pushUpMax(h, parent(parent(i)));
        }
    }



    /**
     * swaps two elements in the heap by index.
     * @param a the array to be sorted
     * @param i index of the first element
     * @param j index of the second element
     */
    private void swap(T[] a, int i, int j ){
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }


    /**
     * Returns the indices of i's children.
     * @param i index of the parent node of the returned children's indices.
     * @return an ArrayList of indices which could be empty if there are non.
     */


    private ArrayList<Integer> indexOfChildren(int i){
        ArrayList<Integer> indexOfChildren = new ArrayList<>();
        if (i * 2 + 1 < heap.length){
            indexOfChildren.add(i * 2);
            indexOfChildren.add(i * 2 + 1);
            return indexOfChildren;
        } else if (i * 2 < heap.length){
            indexOfChildren.add(i * 2);
            return indexOfChildren;
        } else {
            return indexOfChildren;
        }
    }


    /**
     * calculates the index of the last descendant of a given node
     * @param i index of parent/grandparent node
     * @return index of the last descendant of a given node with index i
     */
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
        for (int j = i*2+1; j < lastDesc(i); j++) {
            if (heap[j].compareTo(heap[smallest]) < 0) smallest = j;
        }
        return smallest;
    }


    /**
     * Returns index of largest child or grandchild, 0 if none exist.
     * @param i index of base node, i.e. (grand)parent
     * @return index of largest child or grandchild, 0 if none exist.
     */
    private int indexOfLargestDescendant(int i){
        if (i*2 >= heap.length) return 0;
        int smallest = i*2;
        for (int j = i*2+1; j < lastDesc(i); j++) {
            if (heap[j].compareTo(heap[smallest]) > 0) smallest = j;
        }
        return smallest;
    }


    /**
     * Checks if there are children to a given node.
     * @param i index of base node, i.e. parent
     * @return true if there is at least one child and false if there are non.
     */
    private boolean iHasChildren(int i){
//        return indexOfChildren(i).size() > 0;
        return i*2 < heap.length;
    }


    /**
     * Return the index of the parent node to a given index.
     * @param i index of base node, i.e. child
     * @return index of the corresponding parent node to a given node.
     */
    private int parent (int i){
         return i/2;
    }
    /**
     * Checks if a given node is on a min level.
     * @param i index of a given node
     * @return true if node is on a min level.
     */
    private boolean isMinLevel(int i){
        return log2(i) % 2 == 0;
    }
    /**
     * Checks if a given node is on a max level.
     * @param i index of a given node
     * @return true if node is on a max level.
     */
    private boolean isMaxLevel(int i){
        return !isMinLevel(i);
    }

    /**
     * Used to calculate levels of nodes.
     * @param x index of a given node.
     * @return odd value if heap[x] is on a max level and even value if heap[x] is on a min level.
     */
    private static int log2(int x){
        return (int) (log(x) / log(2));
    }


    @Override
    public String toString() {
        return Arrays.toString(heap);
    }

    public String tree() {
        int max = log2(heap.length);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= log2(heap.length) ; i++) {
            for (int k = i; k <= max; k++) {
                sb.append("   ");
            }
            for (int j = (int) Math.pow(2,i); j < lastInRow(i); j++) {
                sb.append(heap[j].toString());
                for (int k = i; k <= max; k++) {
                    sb.append("  ");
                }
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    private int[] childrenOf(int i) {
        if (i*2+1 < heap.length)    return new int[]{i*2, i*2+1};
        else if (i*2 < heap.length) return new int[]{i*2};
        else                        return new int[]{};
    }

    private int[] grandchildrenOf(int i) {
        if (i*4+3 < heap.length)    return new int[]{i*4, i*4+1, i*4+2, i*4+3};
        if (i*4+2 < heap.length)    return new int[]{i*4, i*4+1, i*4+2};
        if (i*4+1 < heap.length)    return new int[]{i*4, i*4+1};
        else if (i*4 < heap.length) return new int[]{i*4};
        else                        return new int[]{};
    }

    private int lastInRow(int i) {
        int max = (int) Math.pow(2,i+1);
        return (max < heap.length) ? max : heap.length - 1;
    }

    public static void main(String[] args) {
        MinMaxHeap<Integer> mmh = new MinMaxHeap<>(12);
        Integer[] intArr = {13, 21, 31, 51, 46, 16, 11, 10, 31, 41, 71, 8};
        for (int i = 0; i < intArr.length ; i++) {
            mmh.insert(intArr[i]);
        }
        System.out.println(mmh);
        int j = 20;
        Integer[] intArr2 = new Integer[j];
        for (int i = 0; i < j ; i++) {
            intArr2[i] = StdRandom.uniform(0,1000);
        }
        System.out.println(Arrays.toString(intArr2));
        MinMaxHeap<Integer> mmh2 = new MinMaxHeap<>(intArr2);
        System.out.println(mmh2);
//
//        ArrayList<Integer> even = new ArrayList<>();
//        ArrayList<Integer> odd  = new ArrayList<>();
//
//
//        for (int i = 1; i < j ; i++) {
//            if (i % 2 != 0){
//                even.add(mmh2.removeMin());
//            } else {
//                odd.add(mmh2.removeMax());
//            }
//        }
//
//
//        System.out.println(even);
//        System.out.println(odd);
    }



}
