package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by ben on 2017-10-28.
 */

public class Network {
    int[][] capacity;
    int[][] flow;
    int[] previous;
    boolean[] visited;
    int numVertices;

    public Network(int n, int s, int t, EDGES [] e)
    {
        capacity = new int[n][n];
        flow = new int[n][n];
        previous = new int[n];
        visited = new boolean [n];
        this.numVertices = n;
        //initialize the capacity with -2 in order to easy get neighbours
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
                capacity[i][j] = -2;
        }
        for(int i = 0; i < e.length; i++)
        {

            EDGES back = e[i];
            System.out.println("back.begin " + back.begin + "back.end " + back.end);
            int a = back.begin;
            int b = back.end;

            capacity[a][b] = back.capacity;
        }

    }
    // Compute the maxflow of the network
    public int maxflow_computation (int source, int sink) {
        int maxFlow = 0;
        while (augmenting_path(source, sink))
        {
            maxFlow += augment(source, sink);
        }
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
        System.out.println("Bottleneck -> " + bottleneck);
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
                }

            }
        }
        if(visited[sink])
        {
            for(int v = sink; v != source; v = previous[v] )
            {
                System.out.print(" ( " + v + " -> " + previous[v] + " )");
            }
            System.out.println(" ");
            System.out.println("-----------------********************************------------------");
            return true;
        }
        else
            return false;
    }

    public ArrayList<Integer> getNeighbours(int v)
    {
        ArrayList<Integer> neigh = new ArrayList<Integer>();
        for(int i = 0; i < this.capacity[0].length; i++) {
            if(capacity[v][i] != -2)
                neigh.add(i);

        }
        return neigh;
    }

}