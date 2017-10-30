import java.util.*;
import java.lang.*;
import java.io.*;

class MatchReduce
{
    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int e = sc.nextInt();
        int s = x+y+1;
        int t = x+y+2;
        int[][] edges = new int[e+x+y][3];


        for (int i = 0; i < e; i ++) {
            int xn = sc.nextInt();
            int yn = sc.nextInt();
            edges[i][0] = xn;
            edges[i][1] = yn;
            edges[i][2] = 1;
        }

        for (int k = 0; k < (x+y); k++) {
            if (k < x) {
                edges[k+e][0] = x+y+1;
                edges[k+e][1] = k+1;
                edges[k+e][2] = 1;
            } else {
                edges[k+e][0] = k+1;
                edges[k+e][1] = x+y+2;
                edges[k+e][2] = 1;                
            }
	}

        System.out.println(t);
        System.out.println(s + " " + t);
        System.out.println(edges.length);
        for (int j = 0; j < edges.length; j++) {
            System.out.println(edges[j][0] + " " + edges[j][1] + " " + edges[j][2]);
        }


	//MAXFLOW

	int antal_v = sc.nextInt();
        int s2 = sc.nextInt();
        int t2 = sc.nextInt();
        int max_flow = sc.nextInt();
        int edges_with_max = sc.nextInt();
	int max_edges = edges_with_max-x-y;
        int[][] edges2 = new int[max_flow][2];


        for (int i = 0; i < edges2.length; i ++) {
            int xn = sc.nextInt();
            int yn = sc.nextInt();
            int zn = sc.nextInt();
            edges2[i][0] = xn;
            edges2[i][1] = yn;

        }

        System.out.println(x + " " + y);
        System.out.println(edges2.length);
        for (int j = 0; j < edges2.length; j++) {
            System.out.println(edges2[j][0] + " " + edges2[j][1]);
    	}
    }
}
