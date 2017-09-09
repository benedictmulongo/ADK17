package com.company;

public class Main {

    public static void main(String[] args)
    {
	// write your code here
/*        Bag<Integer> tre = new Bag<Integer>();
        for(int i = 2; i < 80; i = i*2)
            tre.add(i);

        for(int s : tre)
            System.out.println(s);*/

//test for graph

        int [] v = {1,2,2,3,4,5,9,8,8,7,8,6,6,7};
        Graph gph = new Graph(10,v);
        Bag<Integer> t = (Bag<Integer> )gph.adj(2);
        for(int s : t)
            System.out.println(s);


    }


}
