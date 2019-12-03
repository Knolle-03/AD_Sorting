package de.hawh.ld.sorting;

import java.util.Arrays;

public class SelfOrgSearch<T> {

    private int[] keys;
    private T[] values;
    private int size;

    public SelfOrgSearch(){
        this(10);
    }

    public SelfOrgSearch(int length){
        keys = new int[length];
        values = (T[]) new Object[length];
    }

    public void add(T element){
        if(size >= values.length) resize();

        values[size] = element;
        size++;
    }

    int getSize() {
        return size;
    }

    T[] getValues() {
        return values;
    }


    public T get(T value){
        //if(index > size) throw new IndexOutOfBoundsException("haha");
        for (int i = 0; i < size ; i++) {
            if (value.equals(values[i])){

                keys[i]++;
                swap(i);
                return values[i];
            }
        }

        return null;
    }

    private void swap(int index) {
        int tmp = keys[index];
        T retVal = values[index];
        while (index > 0 && keys[index] > keys[index - 1]) {

            keys[index] = keys[index - 1];
            values[index] = values[index - 1];
            keys[index - 1] = tmp;
            values[index - 1] = retVal;
            index--;
        }
    }

    private void resize(){
        keys = Arrays.copyOf(keys, keys.length * 2);
        values = Arrays.copyOf(values, values.length * 2);
    }

    @Override
    public String toString() {
        return Arrays.toString(keys) + "\n" + Arrays.toString(values);
    }

    public static void main(String[] args) {
        SelfOrgSearch<Integer> test1 = new SelfOrgSearch(10);
        SelfOrgSearch<String> test2  = new SelfOrgSearch(10);





        for (int i = 0; i < 10 ; i++) {
            test1.add(i);
            test2.add("number: " + i);
        }

        test1.get(7);
        test2.get("number: 5");
        test1.get(6);
        test1.get(6);

        System.out.println(test1);
        System.out.println(test2.get("number: 5"));
        System.out.println(test2);
    }
}
