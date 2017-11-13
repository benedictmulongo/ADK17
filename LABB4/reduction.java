

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by ben on 2017-11-09.
 */
public class reduction
{

    public static void main(String[] args) throws IOException
    {

        String NEW_LINE_SEPARATOR = "\n";
      //  System.out.println("Read data from file !");
      //  File text = new File("data.txt");
        Scanner sc = new Scanner(System.in);
        int antal_horn = Integer.valueOf(sc.nextLine());
        int antal_kanter = Integer.valueOf(sc.nextLine());
        int antal_farg = Integer.valueOf(sc.nextLine());
        int isolerad = 0;
        //hash structure
        ArrayList<Integer>[] hashen = new ArrayList[antal_horn + 1];
        for(int j = 0; j < hashen.length; j++)
            hashen[j] = new ArrayList<>();
        //graf for the problem
        grafen graf = new grafen(antal_horn + 1);
        int [] referens = new int[antal_horn + 1];
        for(int j = 0; j < referens.length; j++)
            referens[j] = -100;
        //*****
        int i = 0;
//        graph graphReduc = new graph(antal_horn);
        while(sc.hasNext() && i < antal_kanter)
        {
            //System.out.println("index -> " + i);
            String line = sc.nextLine();
            String [] values = splitter(line);
            hashen[Integer.valueOf(values[0])].add(Integer.valueOf(values[1]));
            hashen[Integer.valueOf(values[1])].add(Integer.valueOf(values[0]));
//            graphReduc.add(Integer.valueOf(values[0]),Integer.valueOf(values[1]));
            i++;
        }
/*        System.out.println("****hashen*****");
        for(int k = 0; k < hashen.length; k++)
        {
            System.out.println("index = " + k + " -> " + hashen[k]);
        }
        System.out.println("****hashen*****");*/



  //      System.out.println("****graf construction*****");
        for(int k = 0; k < hashen.length; k++)
        {
            if(hashen[k].size() != 0)
            {
                referens[k] = graf.n;
                graf.add(hashen[k]);
                isolerad++;
            }
        }

/*        System.out.println("isolerad = " + isolerad);
        System.out.println("antal horn = " + antal_horn);*/
        isolerad = antal_horn - isolerad;

        grafen finalgraph = new grafen(antal_horn+1);
        for(int v = 0; v < referens.length; v++)
        {
            if(referens[v] != -100)
            {
                int index = referens[v];
//                System.out.println("index * ref = " + index);
                ArrayList<Integer> current = graf.get(index);
//                System.out.println("neighrbours -> " + current);
                for(int k = 0; k < current.size(); k++)
                {
                    int child = current.get(k);
                    int ref = referens[child];
                    finalgraph.add(index+1,ref+1);
//                    System.out.println("index * ref här = " + index);
                }
            }

        }

/*        System.out.println("**** final grafen begin ? *****");
        finalgraph.print();
        System.out.println("**** final grafen finnish ? *****");
        System.out.println("Antal isolerade = " + isolerad);*/
        StringBuilder scenner = new StringBuilder();
        scenner.append("2 1 3" + NEW_LINE_SEPARATOR);
        scenner.append("2 2 3" + NEW_LINE_SEPARATOR);
        ArrayList<Integer>[] match = finalgraph.graf;
        int max = 0;
        int SCEN = 2;
        for(int k = 0; k < match.length;k++)
        {
            int constant = k;
            if(match[k].size() != 0)
            {
                max = Math.max(max,k);
                ArrayList<Integer> current = match[k];
                for(int r = 0; r < current.size(); r++)
                {
                    int a = constant+2;
                    int b = current.get(r) + 2;
                    scenner.append("2 " + a + " " + b + NEW_LINE_SEPARATOR);
                    SCEN++;
                }
            }
        }
        max = max + 2;
        int v = max + 1;
        StringBuilder tmp = new StringBuilder();
        for(int w = 0; w < isolerad - 1; w++)
        {
            tmp.append("2 " + v + " " + (v + w + 1) + NEW_LINE_SEPARATOR);
            SCEN++;
        }

        scenner.append(tmp.toString());


  //      System.out.println("**** APPEND *****");
        antal_horn = antal_horn + 2;
        System.out.println(antal_horn);
        System.out.println(SCEN);
        int actors = antal_farg + 2;
        System.out.println(actors);
        StringBuilder acteurs = new StringBuilder();
        StringBuilder act = new StringBuilder();

/*        for(int a = 0; a < antal_horn -1; a++)
        {*/
        acteurs.append("" + actors + " ");
        for(int y = 0; y < actors; y++)
        {
            acteurs.append((y + 1) + " ");
        }
           /* acteurs.append(NEW_LINE_SEPARATOR);*/
        for(int a = 0; a < antal_horn -1; a++)
        {
            act.append(acteurs.toString() + NEW_LINE_SEPARATOR );
        }
        act.append(acteurs);
//        }
   //     acteurs.append()

        System.out.println(act.toString());
        System.out.println(scenner.toString());
  //      System.out.println("**** APPEND *****");



    }
    static String [] splitter(String lis)
    {
        String [] tra = lis.split(" ");
        return tra;
    }
}
