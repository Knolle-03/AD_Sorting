package de.hawh.ld.sorting;

import edu.princeton.cs.algs4.Queue;

// Merge bottom up with queues of queues
public class BottomUpMergeSort {


    private static class MergeBottomUpQueue {



        @SuppressWarnings("unchecked")
        public static void sort(Comparable[] orig) {
            Queue<Queue> queue = new Queue<>();
            //add each element of the orig array into a single element queue
            //add the single element queue into a queue of queues.
            for (Comparable comp : orig) {
                Queue<Comparable> temp = new Queue<>();
                temp.enqueue(comp);
                queue.enqueue(temp);
            }
            // dequeue two queues, merge them an enqueue them again until a there's only a queue with one element left.
            while (queue.size() > 1) {
                queue.enqueue(merge(queue.dequeue(), queue.dequeue()));
            }

            // add sorted elements back to the orig array in ascending order
            Queue<Comparable> sorted = queue.dequeue();
            for (int i = 0; i < orig.length; i++) {
                orig[i] = sorted.dequeue();
            }

        }

        @SuppressWarnings(value = "unchecked")
        private static Queue<Comparable> merge(Queue<Comparable> a, Queue<Comparable> b) {
            Queue<Comparable> sortedSubQueue = new Queue<>();
            // add elements of the given queues into a new queue
            // if one of the queues is empty the other one is emptied
            // if both have elements left they are compared and the lower one is added to the new queue
            while (!a.isEmpty() || !b.isEmpty()) {
                if (a.isEmpty()) sortedSubQueue.enqueue(b.dequeue());
                else if (b.isEmpty()) sortedSubQueue.enqueue(a.dequeue());
                else if (a.peek().compareTo(b.peek()) < 0) sortedSubQueue.enqueue(a.dequeue());
                else sortedSubQueue.enqueue(b.dequeue());
            }
            return sortedSubQueue;
        }

    }

    //checks if a given array is sorted
    @SuppressWarnings(value = "unchecked")
    private static boolean isSorted(Comparable[] sortedArray){
        for (int i = 0; i < sortedArray.length - 1 ; i++) {
           if(sortedArray[i].compareTo(sortedArray[i + 1]) > 0) return false;

        }
        return true;
    }


    public static void main(String[] args) {

        int n = 2_000_000;


        // reversed array
        Integer[] nums = new Integer[n];

        for (int i = 0; i < n ; i++) {
            nums[i] = nums.length - i;
        }

        MergeBottomUpQueue.sort(nums);
        System.out.println(isSorted(nums));
    }


}
