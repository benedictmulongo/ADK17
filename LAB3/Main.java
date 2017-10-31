package com.company;
import  java.io.*;
import java.util.*;

public class Main
{

    public static void main(String[] args) throws IOException
    {
        System.out.println("enter input data");
        File text = new File("C:/Users/ben/Desktop/LABB33/data.txt");
        Scanner sc = new Scanner(text);
        int antal_horn = sc.nextInt();
        int s = sc.nextInt();
        int t = sc.nextInt();
        int antal_kanter = sc.nextInt();
        EDGES [] funka = new EDGES[antal_kanter];

        int i = 0;
        while(sc.hasNext() && i < antal_kanter)
        {
            //System.out.println("index -> " + i);
            int start = sc.nextInt();
            int slut = sc.nextInt();
            int kap = sc.nextInt();
            //System.out.println("start -> " + start + " slut -> " + slut +" kap -> " + kap );
            funka[i] = new EDGES(start,slut,0,kap);
            i++;
        }
        for( EDGES e : funka)
            System.out.println("Edges -> " + e);
        Network net = new Network(antal_horn + 1,s,t,funka);
        System.out.println("Net max flow -> " + net.maxflow_computation(s,t));
        System.out.println("Neighbours of vertex " + s + " -> " + net.getNeighbours(s));
        //print edges
        net.print_edges(s,t);
        //print matchning
        net.print_match(s,t);
    }

}
