package de.hawh.ld.sorting;

import java.util.Arrays;

public class SelfOrgSearch2<T> extends SelfOrgSearch<T> {

    public SelfOrgSearch2(int length){
        super(length);
    }

    @Override
    public T get(T value){
        for (int i = 0; i < getSize() ; i++) {
            if(value.equals(getValues()[i])){
                swap(i);
                return value;
            }
        }
        return null;
    }

    private void swap(int i) {
        T tmp = getValues()[i];
        for (int j = i; j > 0 ; j--) {
            getValues()[j] = getValues()[j - 1];
        }
        getValues()[0] = tmp;
    }

    @Override
    public String toString() {
        return Arrays.toString(getValues());
    }

    public static void main(String[] args) {
        SelfOrgSearch2<Integer> test1 = new SelfOrgSearch2(10);
        SelfOrgSearch2<String> test2  = new SelfOrgSearch2(10);





        for (int i = 0; i < 10 ; i++) {
            test1.add(i);
            test2.add("number: " + i);
        }

        test1.get(7);
        test2.get("number: 5");
        test2.get("number: 6");
        test2.get("number: 9");

        test1.get(6);
        test1.get(6);
        test1.get(9);

        System.out.println(test1);
        System.out.println(test2);
    }
}
