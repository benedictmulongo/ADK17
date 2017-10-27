import java.awt.datatransfer.FlavorEvent;
import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class Main
{

    public static void main(String[] args)
    {
	Scanner sc = new Scanner(System.in);
	int antal_horn = sc.nextInt();
	int s = sc.nextInt();
	int t = sc.nextInt();
	int antal_kanter = sc.nextInt();

	FlowNetwork flw = new FlowNetwork(antal_horn);	
	
	for (int i = 0; i < antal_kanter; i++) {
	    int start = sc.nextInt();
	    int slut = sc.nextInt();
 	    int kap = sc.nextInt();
	    if (start == antal_horn-1) {
	    	flw.insert(0,slut,0,kap);
	    } else if (slut == antal_horn) {
		flw.insert(start,antal_horn-1,0,kap);
	    } else {
	    	flw.insert(start,slut,0,kap);
	    }
	}
	// write your code here
    
        /*
        flw.insert(0,1,0,1);
        flw.insert(0,2,0,1);
       // flw.insert(0,3,0,5);

        flw.insert(1,3,0,1);
        flw.insert(1,4,0,1);
        flw.insert(1,5,0,1);

      //  flw.insert(2,3,0,3);
        flw.insert(2,5,0,1);

       // flw.insert(3,4,0,5);
        flw.insert(3,6,0,1);

        flw.insert(4,6,0,1);

        flw.insert(5,6,0,1);
        */

        //System.out.println(" vw " + flw.edge(0,1));
        //ArrayList<EDGES> g = flw.getConnectedTo(0);
        //for(EDGES e : g)
        //    System.out.println(" -> " + e);
        //flw.findAugment(flw,0,6);
        System.out.println(" Shortest augmented path " + flw.findShortest(flw,0,antal_horn-1));
        print(flw,antal_horn);
        augment(flw,0,antal_horn-1);
//        System.out.println("bottleneck path " + flw.bottleneck(0,6));
        System.out.println("After augmenting !!!?? ");
        print(flw,antal_horn);

        //System.out.println("bottleneck path " + );
/*        for(int i = 0; i < flw.parentOf.length; i++)
            System.out.println(" -- Index = " + i + " " + flw.parentOf[i]);
        for(EDGES r : flw.path1)
            System.out.println("--- 1 " + r);
        System.out.println("bottleneck path " + flw.bottleneck(0,6));*/
    }

    static void print(FlowNetwork flw, int n)
    {
        for(int i = 0; i < n - 1; i++)
        {
            ArrayList<EDGES> g = flw.getConnectedTo(i);
            for(EDGES e : g)
                System.out.println(" -> " + e);
        }
    }

    static void augment(FlowNetwork flw, int s, int t)
    {
        ArrayList<String> tmp = flw.findShortest(flw,s,t);
        while(flw.findShortest(flw,s,t) != null)
        {
            flw.augment_path(s,t);
        }
    }

}
/*
        flw.insert(0,1,3,7);
                flw.insert(0,2,6,6);
                flw.insert(0,3,5,5);
                flw.insert(1,3,1,1);
                flw.insert(1,4,2,2);
                flw.insert(2,3,0,3);
                flw.insert(2,5,6,9);
                flw.insert(3,4,4,5);
                flw.insert(3,6,2,3);
                flw.insert(4,6,6,6);
                flw.insert(5,6,6,8);*/
/*
        flw.insert(0,1,0,7);
                flw.insert(0,2,0,6);
                flw.insert(0,3,0,5);
                flw.insert(1,3,0,1);
                flw.insert(1,4,0,2);
                flw.insert(2,3,0,3);
                flw.insert(2,5,0,9);
                flw.insert(3,4,0,5);
                flw.insert(3,6,0,3);
                flw.insert(4,6,0,6);
                flw.insert(5,6,0,8);*/
/*
        flw.insert(0,1,3,7);
                flw.insert(0,2,5,6);
                flw.insert(0,3,2,5);
                flw.insert(1,3,1,1);
                flw.insert(1,4,2,2);
                flw.insert(2,3,1,3);
                flw.insert(2,5,4,9);
                flw.insert(3,4,2,5);
                flw.insert(3,6,2,3);
                flw.insert(4,6,4,6);
                flw.insert(5,6,4,8);*/
