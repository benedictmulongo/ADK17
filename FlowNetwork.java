package com.company;

import java.util.ArrayList;
import java.util.*;
import java.io.*;

/**
 * Created by ben on 2017-10-03.
 */
public class FlowNetwork
{
    EDGES [][] kant;
    int source;
    int sink;
    int E;
    int V;
    int parentOf[];
    ArrayList<EDGES> path1;
    FlowNetwork(int Vertex,int s, int t)
    {
        this.kant = new EDGES[Vertex][Vertex];
        this.E = 0;
        this.V = Vertex;

    }
    FlowNetwork(int Vertex)
    {
        this.kant = new EDGES[Vertex][Vertex];
        this.E = 0;
        this.V = Vertex;

    }
    int V() { return V; }
    int E() { return E; }
    void insert(int v, int w, int cap)
    {
        EDGES tmp = new EDGES(v,w,cap);
        this.kant[v][w] = new EDGES(v,w,cap);
        E++;
    }

    void insert(int v, int w, int flow, int cap)
    {
        EDGES tmp = new EDGES(v,w,flow,cap);
        this.kant[v][w] = new EDGES(v,w,flow,cap);
        E++;
    }
    void remove(int v,int w)
    {
        kant[v][w] = null;
        E--;
    }
    EDGES edge(int v, int w)
    { return kant[v][w]; }

    public ArrayList<EDGES> getConnectedTo(int v)
    {
        ArrayList<EDGES> ge = new ArrayList<>();
        for(int i = 0; i < this.kant.length; i++) {
            if(edge(v,i) != null)
                ge.add(edge(v, i));
        }
        return ge;
    }

    public int bottleneck(int s,int t)
    {
        int d = 10000000;
        for (int v = t; v != s; v = this.parentOf[v])
        {// from t to s
            // path.add( " ( " + parentOf[v] + " -> " + v + " )");
            EDGES calc = this.kant[this.parentOf[v]][v];
           // System.out.print(calc);
            if(calc.residualCapTo(v) < d)
                d = calc.residualCapTo(v);
        }
        return d;
    }

    void augment_path(int s,int t)
    {
        int d = bottleneck(s,t);
        for (int v = t; v != s; v = this.parentOf[v])
        {// from t to s
            // path.add( " ( " + parentOf[v] + " -> " + v + " )");
            EDGES calc = this.kant[this.parentOf[v]][v];
            calc.addResidualCapTo(this.parentOf[v],d);
            this.kant[this.parentOf[v]][v] = calc;
        }
    }

    public ArrayList<String> findShortest(FlowNetwork flw, int s, int t)
    {
        int numVs = flw.V();
        boolean visited[] = new boolean[numVs];
        this.parentOf = new int[numVs];

        // enqueue source and mark as visited
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        this.parentOf[s] = -1;

        int[] P = new int[flw.V()];
        Arrays.fill(P, -1);
        P[s] = s;

        // BFS implemented using a queue and a loop
        while (queue.size() != 0) {
            int u = queue.poll();
            ArrayList<EDGES> near = flw.getConnectedTo(u);
            for(EDGES v : near)
            {
                int endd = v.end;
                int cap = v.residualCapTo(endd);
                if(cap > 0 && (!visited[v.end]))
                {
                    queue.add(v.end);
                    this.parentOf[v.end] = u;
                    visited[v.end] = true;

                }
            }
        }

        // have we got from source to sink?
        if (visited[t]) {
            ArrayList<String> path = new ArrayList<String>();
            this.path1 = new ArrayList<EDGES>();
            for (int v = t; v != s; v = this.parentOf[v]) {// from t to s
                // path.add( " ( " + parentOf[v] + " -> " + v + " )");
                path.add(" ( " + v + " -> " + this.parentOf[v] + " )");
                this.path1.add(this.kant[this.parentOf[v]][v]);
            }
            return path;
        }
        else
            return null;

    }
}

