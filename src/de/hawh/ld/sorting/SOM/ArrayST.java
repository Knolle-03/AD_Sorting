package de.hawh.ld.sorting.SOM;


import java.util.Arrays;

public class ArrayST<T> implements SOSI<T> {

    private T[] values;
    private int[] counter;
    private int size;
    private int n = 0;

    public ArrayST(T[] array){
        values = (T[]) new Object[array.length];
        counter = new int[array.length];
        this.size = array.length;
        for (int i = 0; i < array.length ; i++) {
            add(array[i]);
        }

    }


    @Override
    public void add(T element) {
        if (n > size) throw new IndexOutOfBoundsException("ha ha");

        values[n++] = element;
    }

    public T lookUpByKey (T e){
        for (int i = 0; i < n ; i++) {
            if (e == values[i]){
                counter[i]++;
                swap(values, counter, i, 0);
                for (int j = i; j > 1 ; j--) {
                    swap(values, counter, j, j - 1);
                }
            }
        }
        return e;
    }


    private void swap(T[] arrayST, int[] counter, int i, int j){
        T tmp1 = arrayST[j];
        int tmp2 = counter[j];
        arrayST[j] = arrayST[i];
        counter[j] = counter[i];
        arrayST[i] = tmp1;
        counter[i] = tmp2;
    }


    @Override
    public String toString() {

        String str = "counter: " + Arrays.toString(counter) + "\n" +
                "values : " + Arrays.toString(values);
        return str;
    }

    public static void main(String[] args) {
        Integer[] intArr = new Integer[5];

        for (int i = 0; i < intArr.length ; i++) {
            intArr[i] = i;
        }

        System.out.println(Arrays.toString(intArr));
        ArrayST<Integer> integerArrayST = new ArrayST<>(intArr);
        System.out.println(integerArrayST);
        System.out.println(integerArrayST.n);
        for (int i = integerArrayST.n; i > 0; i--) {
            for (int j = 0; j < i  ; j++) {
                integerArrayST.lookUpByKey(i);
            }

        }

        System.out.println(integerArrayST);



    }
}
