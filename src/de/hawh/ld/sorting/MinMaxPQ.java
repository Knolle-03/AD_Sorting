//package de.hawh.ld.sorting;
//
//import edu.princeton.cs.algs4.MergeX;
//
//import java.lang.reflect.Array;
//import java.util.*;
//import java.util.function.Consumer;
//import static java.lang.Math.*;
//import static de.hawh.ld.sorting.Logarithm.*;
//
////        A min-max heap is a complete binary tree containing alternating min (or even) and max (or odd) levels.
////        Even levels are for example 0, 2, 4, etc, and odd levels are respectively 1, 3, 5, etc.
////        We assume in the next points that the root element is at the first level, i.e., 0.
////
////        Each node in a min-max heap has a data member (usually called key) whose value is used to determine the order of the node in the min-max heap.
////
////        The root element is the smallest element in the min-max heap.
////        One of the two elements in the second level, which is a max (or odd) level, is the greatest element in the min-max heap
////        Let x be any node in a min-max heap.
////        If x is on a min (or even) level, then  x.key is the minimum key among all keys in the subtree with root x.
////        If x is on a max (or odd) level, then x.key is the maximum key among all keys in the subtree with root x.
////        A node on a min (max) level is called a min (max) node.
////        A max-min heap is defined analogously; in such a heap, the maximum value is stored at the root,
////        and the smallest value is stored at one of the root's children.
//public class MinMaxPQ<Key> implements Iterable<Key> {
//
//    private Key[] pq;
//    private int size;
//    private Comparator<Key> comparator;  // optional comparator
//
//
//
//    public MinMaxPQ(int initCapacity) {
//        pq = (Key[]) new Object[initCapacity + 1];
//        size = 0;
//    }
//
//    public MinMaxPQ() {
//        this(1);
//    }
//
//
//    public MinMaxPQ(int initCapacity, Comparator<Key> comparator) {
//        this.comparator = comparator;
//        pq = (Key[]) new Object[initCapacity + 1];
//        size = 0;
//    }
//
//
//    public MinMaxPQ(Key[] keys) {
//        size = keys.length;
//        pq = (Key[]) new Object[keys.length + 1];
//        for (int i = 0; i < size; i++)
//            pq[i+1] = keys[i];
//        for (int k = size/2; k >= 1; k--)
//            pushDown(k);
//    }
//
//    public MinMaxPQ(Comparator<Key> comparator) {
//        this(1, comparator);
//    }
//
//
//
//
//
//    private void pushDownMin(int i){
//        if(size >= 2 * i){
//            //children and grandchildren
//            int[] grandchildren = grandchildren(i);
//            int min = lowestChildOrGrandchild(i);
//            if(Arrays.asList(grandchildren).contains(min)){
//                if(!greater(i , min)){
//                    swap(min, i);
//                    if (greater(min, parent(min))){
//                        swap(min, parent(min));
//                    }
//                    pushDownMin(min);
//                }
//            } else if(!greater(i, min)){
//                swap(min, i);
//            }
//        }
//    }
//
//    private void pushDownMax(int i){
//        if(size >= 2 * i){
//            int[] grandchildren = grandchildren(i);
//            int min = highestChildOrGrandchild(i);
//            if(Arrays.asList(grandchildren).contains(min)){
//                if(greater(min, i)){
//                    swap(min, i);
//                    if (!greater(min, parent(min))){
//                        swap(min, parent(min));
//                    }
//                    pushDownMax(min);
//                }
//            } else if (greater(min, i)){
//                swap(min, i);
//            }
//        }
//    }
//
//
//    private int lowestChildOrGrandchild(int i){
//        int[] children = children(i);
//        int[] grandchildren = grandchildren(i);
//        //refactor!!!!!111!11!1!elf!
//        int min = 100000000;
//        if (children.length > 0 || grandchildren.length > 0){
//            for (int j = 0; j < children.length ; j++) {
//                if (greater(min, children[j])) min = children[j];
//            }
//            for (int k = 0; k < grandchildren.length ; k++) {
//                if (grandchildren[k] < min) min = children[k];
//            }
//
//
//        }
//        if (grandchildren.length > 0) Arrays.sort(grandchildren);
//        if (children.length == 0 && grandchildren.length != 0) return grandchildren[0];
//        if (children.length != 0 && grandchildren.length == 0) return children[0];
//        return (children[0] < grandchildren[0]) ? children[0] : grandchildren[0];
//    }
//
//    private int highestChildOrGrandchild(int i){
//        int[] children = children(i);
//        int[] grandchildren = grandchildren(i);
//        if (children.length > 0) Arrays.sort(children);
//        if (grandchildren.length > 0) Arrays.sort(grandchildren);
//        if (children.length == 0 && grandchildren.length != 0) return grandchildren[grandchildren.length - 1];
//        if (children.length != 0 && grandchildren.length == 0) return children[children.length - 1];
//        return (children[children.length - 1] > grandchildren[grandchildren.length - 1]) ? children[children] : grandchildren[0];
//    }
//
//    private Key[] concat(int[] a, int[] b){
//        int[] concatArr = new int[a.length + b.length];
//        for (int i = 0; i < concatArr.length ; i++) {
//
//        }
//    }
//
//
//    private void swap(int i, int j) {
//        Key tmp = pq[i];
//        pq[i] = pq[j];
//        pq[j] = tmp;
//    }
//
//
//    private boolean greater(int i, int j) {
//        if (comparator == null) {
//            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
//        }
//        else {
//            return comparator.compare(pq[i], pq[j]) > 0;
//        }
//    }
//
//    private void pushDown(int i){
//        if (isMinLevel(i)) pushDownMin(i);
//        else pushDownMax(i);
//    }
//
//
//    private boolean isMinLevel(int i){
//        return floor(log2(i) % 2) == 0;
//    }
//
//    private boolean isMaxLevel(int i){
//        return !isMinLevel(i);
//    }
//
//    private int parent(int i){
//        return i/2;
//    }
//
//    private int grandparent(int i){
//        return i/4;
//    }
//
//    private int[] children(int i)  {
//        if(size < 2 * i) return new int[0];
//        else if(size < 2 * i + 1) return new int[]{2 * i};
//        else return new int[]{2 * i, 2 * i + 1};
//    }
//
//    private int[] grandchildren(int i){
//        if(size < 4 * i) return new int[0];
//        else if (size < 4 * i + 1) return new int[]{4 * i};
//        else if (size < 4 * i + 2) return new int[]{4 * i, 4 * i + 1};
//        else if (size < 4 * i + 3) return new int[]{4 * i, 4 * i + 1, 4 * i + 2};
//        else return new int[] {4 * i, 4 * i + 1, 4 * i + 2, 4 * i + 3 };
//    }
//
//
//
//    @Override
//    public Iterator<Key> iterator() {
//        return null;
//    }
//
//    @Override
//    public void forEach(Consumer<? super Key> action) {
//
//    }
//
//    @Override
//    public Spliterator<Key> spliterator() {
//        return null;
//    }
//
//    @Override
//    public String toString() {
//        return Arrays.toString(pq);
//    }
//
//    public static void main(String[] args) {
//
//        Integer[] intArr = {1233, 421, 132, 55, 324};
//        MinMaxPQ minMaxPQ = new MinMaxPQ(intArr);
//        System.out.println(minMaxPQ);
//
//
//    }
//
//
//
//}
