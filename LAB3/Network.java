package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ben on 2017-10-28.
 */

public class Network {
    int[][] capacity;
    int[][] flow;
    int[] previous;
    boolean[] visited;
    int numVertices;
    int sou;
    int sin;
    int FlowMax;
    int positive;
    private static final String NEW_LINE_SEPARATOR = "\n";
    public Network(int n, int s, int t) {
        capacity = new int[n][n];
        //***************
        flow = new int[n][n];
        previous = new int[n];
        visited = new boolean[n];
        this.numVertices = n;
        this.sou = s;
        this.sin = t;
        this.positive = 0;
    }
    public void add(int s, int t, int cap)
    {
        this.capacity[s][t] = cap;
    }

    public void print_result(int s, int t)
    {
       // System.out.println("******************************* RESULT *******************************");
        StringBuilder br = new StringBuilder();
        System.out.println(numVertices - 1);
        System.out.println(sou + " " + sin  + " " + FlowMax);
        ArrayList<Integer>  near;
        int count = 0;
        for(int i = 0; i <numVertices; i++)
        {
                near = getNeighbours(i);
                for(int j : near)
                {
                    if(flow[i][j] > 0) {
                        count++;
                        br.append(i + " " + j + " " + " " + flow[i][j] + NEW_LINE_SEPARATOR);
                    }
                }
        }
        System.out.println(count);
        System.out.println(br.toString());
        //System.out.println(" " + positive);
      //  System.out.println("******************************* FINISH RESULT *******************************");

/*        System.out.println( i + " " +  j + " " + " " + flow[i][j] + " cap -> " + capacity[i][j]);*/
    }

    public void print_edges(int s, int t)
    {
        System.out.println("******************************* PRINT EDGGES *******************************");
        ArrayList<Integer>  near;
        for(int i = 0; i <numVertices; i++)
        {
            if((i != s ) && (i != t) )
            {
                int el = i;
                near = getNeighbours(el);
                for(int j : near)
                {
                    if((j != s ) && (j != t) )
                        System.out.println( i + " -> " +  j + " " + " flow -> " + flow[i][j] + " cap -> " + capacity[i][j]);
                }
            }
        }
        System.out.println("******************************* FINISH *******************************");
    }

    public void print_s(int s, int t)
    {
        System.out.println("******************************* PRINT SOURCE EDGES *******************************");
        ArrayList<Integer>  near;
        near = getNeighbours(s);
        for(int j : near)
        {
            System.out.println( s + " -> " +  j + " " + " flow -> " + flow[s][j] + " cap -> " + capacity[s][j]);
        }

        System.out.println("******************************* FINISH SOURCE *******************************");
    }

    public void print_match(int s, int t)
    {
        System.out.println("******************************* PRINT MATCH *******************************");
        ArrayList<Integer>  near;
        for(int i = 0; i <numVertices; i++)
        {
            if((i != s ) && (i != t) )
            {
                int el = i;
                near = getNeighbours(el);
                for(int j : near)
                {
                    if((j != s ) && (j != t) ) {
                        if(flow[i][j] != 0)
                            System.out.println(i + " -> " + j + " " + " flow -> " + flow[i][j] + " cap -> " + capacity[i][j]);
                    }
                }
            }
        }
        System.out.println("******************************* FINISH *******************************");
    }
    // Compute the maxflow of the network
    public int maxflow(int source, int sink)
    {
        int maxFlow = 0;
        while (augmenting_path(source, sink))
        {
            maxFlow += augment(source, sink);
        }
        this.FlowMax = maxFlow;
        return maxFlow;
    }

    // Augment flow within network along path found
    protected int augment(int source, int sink)
    {
        int bottleneck = Integer.MAX_VALUE;
        int v = sink;
        //compute the bottleneck value along the augmenting path found
        while (previous[v] != -1)
        {
            int unit = capacity[previous[v]][v] - flow[previous[v]][v];
            if (unit < bottleneck)
            {
                bottleneck = unit;
            }
            v = previous[v];
        }
        v = sink;
        //System.out.println("Bottleneck -> " + bottleneck);
        //add flow along the augmenting path
        while (previous[v] != -1)
        {
            //forward edges
            flow[previous[v]][v] += bottleneck;
            //backward edges
            flow[v][previous[v]] -= bottleneck;
            v = previous[v];
        }
        return bottleneck;
    }


    // Locate augmenting path in the Flow Network from s to t
    public boolean augmenting_path (int source, int sink)
    {
        for (int i = 0 ; i < numVertices; i++)
        {
            visited[i] = false;
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(source);

        previous[source] = -1;
        visited[source] = true;

        while (queue.size() > 0)
        {
            int u = queue.poll();
            visited[u] = true;
            for(int element = 0; element < numVertices; element++)
            {
                int end = element;
                int begin = u;
                int cap = capacity[begin][end];
                if (visited[end] == false && cap > flow[begin][end])
                {
                    queue.add(end);
                    visited[end] = true;
                    previous[end] = begin;
                    if(flow[begin][end] > 0)
                        this.positive++;
                }
            }
        }
        if(visited[sink])
        {
/*            for(int v = sink; v != source; v = previous[v] )
            {
                System.out.print(" ( " + v + " -> " + previous[v] + " )");
            }
            System.out.println(" * ");
            System.out.println("-----------------********************************------------------");*/
            return true;
        }
        else
            return false;
    }

    public ArrayList<Integer> getNeighbours(int v)
    {
        ArrayList<Integer> neigh = new ArrayList<Integer>();
        for(int i = 0; i < this.capacity[0].length; i++)
        {
            if(capacity[v][i] != -2)
                neigh.add(i);
        }
        return neigh;
    }

}