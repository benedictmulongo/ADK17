package com.company;

/**
 * Created by ben on 2017-09-07.
 */

public class trie<Key extends Comparable<Key>, Value> {
/*    private Node root;             // root of BST

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private int N = 26;
        private Node [] Value;

        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            for (int v = 0; v < N; v++)  // Initialize all lists
                Value[v] = new Node(null,null,0);  // to empty.
        }
    }

    *//**
     * Initializes an empty symbol table.
     *//*
    public trie() {
    }

    /*//*****

    public void add(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calledput() with a null key");

        root = add(root, key, val);
    }

    //Key="sagera"
    // for all character s in key, ind = index(s)
    // root.value[ind] != null;
    // root.value[ind] = new Node(s,val,26)
        // recursive call from root.value[ind] with "agera"
    private Node add(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val,1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    /*//*****






    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (key == null) throw new IllegalArgumentException("called get() with a null key");
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calledput() with a null key");
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val,1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    int index(char ta)
    {
        int t = (int)ta;
        int r = t - 97;
        return r;
    }*/

/*
    public static void main(String[] args) {
        BST<String, Integer> st = new BST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.levelOrder())
            StdOut.println(s + " " + st.get(s));

        StdOut.println();

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }*/
}

