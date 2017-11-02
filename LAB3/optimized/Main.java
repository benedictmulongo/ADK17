package com.company;
import  java.io.*;
import java.util.*;

public class Main
{

    public static void main(String[] args) throws IOException
    {
       // System.out.println("enter input data");
        long begin = System.currentTimeMillis();
        File text = new File("C:/Users/ben/Desktop/LABB33/data.txt");
        Scanner sc = new Scanner(text);
        int antal_horn = sc.nextInt();
        int s = sc.nextInt();
        int t = sc.nextInt();
        int antal_kanter = sc.nextInt();
        Network network = new Network(antal_horn + 1,s,t);
        int i = 0;
        while(sc.hasNext() && i < antal_kanter)
        {
            //System.out.println("index -> " + i);
            int start = sc.nextInt();
            int slut = sc.nextInt();
            int kap = sc.nextInt();
            network.add(start,slut,kap);
            i++;
        }
/*        System.out.println(network.maxflow_edcompute(s,t));*/
        network.maxflow(s,t);
        long fin = System.currentTimeMillis();
        long r = fin - begin;
        System.out.println("time 1 -> " + r);

        long be = System.currentTimeMillis();
        network.print_result(s,t);
        long fe = System.currentTimeMillis();
        long re = fe - be;
        System.out.println("time 2 -> " + re);
    }
}
