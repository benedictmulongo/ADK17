package com.company;

/**
 * Created by ben on 2017-09-05.
 */
public class Graph
{
    private int V;  // number of vertices
    private int E;  // number of edges
    private Bag<Integer>[] adj;  // adjacency lists
    public Graph(int V)
    {
        this.V = V; this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];  // Create array of lists.
        for (int v = 0; v < V; v++)  // Initialize all lists
            adj[v] = new Bag<Integer>();  // to empty.
    }
    public Graph(int V, int [] edgar)
    {
        this.V = V + 1; this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];  // Create array of lists.
        for (int v = 0; v < V; v++)  // Initialize all lists
            adj[v] = new Bag<Integer>();  // to empty.

        int i,j;

        //this(in.readInt());  // Read V and construct this graph.
        //int E = in.readInt();  // Read E.
        for (i = 0,j = 1; j < edgar.length; i = i + 2, j = j + 2)
        {
            addEdge(edgar[i], edgar[j]);  // and add edge connecting them.
        }
    }
    public int V() { return V; }
    public int E() { return E; }
    public void addEdge(int v, int w)
    {
        adj[v].add(w);  // Add w to v’s list.
        adj[w].add(v);  // Add v to w’s list.
        E++;
    }
    public Iterable<Integer> adj(int v)
    { return adj[v]; }
}
