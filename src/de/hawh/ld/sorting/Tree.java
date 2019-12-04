package de.hawh.ld.sorting;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Tree<Key extends Comparable<Key>, Value> extends BST<Key, Value> {

    private Node root;


    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }

        public String toString(int level) {
            return key + "(" + val + ") > e:" + level + "[" +
                    ((null == left) ? "-" : left.toString(level +1)) + " | " +
                    ((null == right) ? "-" : right.toString(level +1)) + "]" ;
        }


    }



    public Tree(BST<Key, Value> bst){
        bst.levelOrder().forEach(key -> put(key, bst.get(key)));
    }



    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void changeKey(Key oldKey, Key newKey){
        changeKey(root, oldKey, newKey);

    }

    private Node changeKey(Node x, Key oldKey, Key newKey) {
        if (x == null) return null;

        int cmp = oldKey.compareTo(x.key);
        if      (cmp < 0) x.left  = changeKey(x.left, oldKey, newKey);
        else if (cmp > 0) x.right = changeKey(x.right, oldKey, newKey);
        else {
            x.key = newKey;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }


//    @Override
//    public String toString() {
//        return toString(root);
//    }
//
//    private String toString(Node x){
//        if (x == null) return null;
//        String leftRoot = toString(x.left);
//        System.out.println(leftRoot);
//        String rightRoot = toString(x.right);
//        return leftRoot + rightRoot;
//    }

    @Override
    public String toString() {
        return (null == root) ? "[empty tree]" : "e0: " + root.toString(1);
    }

    /*************************************************************************
     *  Check integrity of BST data structure.
     ***************************************************************************/
    private boolean check() {
        if (!isBST())            StdOut.println("Not in symmetric order");
        return isBST();
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }


    public static void main(String[] args) {
        BST<Integer, String> test1 = new BST<>();


        for (int i = 0; i < 5 ; i++) {
            int val = i; //StdRandom.uniform(0, 100);
            test1.put(val, "number: " + val);
        }

        Tree<Integer, String> tree = new Tree<>(test1);
        System.out.println(tree);
        System.out.println(tree.check());
        tree.changeKey(3, 239857);
        System.out.println(tree);
        System.out.println(tree.check());

    }

}
