package com.company;

import java.util.*;
import java.io.*;
import java.awt.*;

public class Main
{

    public static void main(String[] args) throws IOException
    {
        // write your code here
        String NEW_LINE_SEPARATOR = "\n";
        System.out.println("Read data from file !");
        File text = new File("graph.txt");
        Scanner sc = new Scanner(text);

//use kattio to read in from file to speed up
        int antal_roller = Integer.valueOf(sc.nextLine());
        int antal_scener = Integer.valueOf(sc.nextLine());
        int antal_skodis = Integer.valueOf(sc.nextLine());

        ArrayList<String>[] roll_to_actors = new ArrayList[antal_roller+1];
        for(int i = 0; i < roll_to_actors.length; i++ )
            roll_to_actors[i] = new ArrayList<>();
        int role = 1;
        for(int i = 0; i < antal_roller; i++)
        {
            String line = sc.nextLine();
            String [] values = splitter(line);
            for(int j = 1; j < values.length; j++) {
                roll_to_actors[role].add("p" +values[j]);
            }
            role++;
//            br.append("( " + i + " ) " + line+ NEW_LINE_SEPARATOR);

        }

        // test if the role_to_actors work
        int incr = 0;
        for(ArrayList<String> e : roll_to_actors)
        {
            System.out.println(" [Roll  " + incr++ + "] could be played by  ->  " + e);
        }


        // consruct an arraylist for all the rolles for condition 1
        //  r_x -> p1 p2 ... pk
        // ArrayList<> :::::::::::::::::::::::::::::::::::::::

        //grafkonstruktion
      //  Initialize the roles
        Roller[] roll = new Roller[antal_roller +1];
        for(int i = 1; i <= antal_roller; i++ )
            roll[i] = new Roller(i);

        // Create connections between roles
        ArrayList<Roller>[] allroles = new ArrayList[antal_roller+1];
        for(int i = 0; i <= antal_roller; i++ )
            allroles[i] = new ArrayList<>();

        for(int i = 0; i < antal_scener; i++)
        {
            String line = sc.nextLine();
            String [] values = splitter(line);
            for(int j = 1;  j < values.length -1 ; j++)
            {
                //use an hash here to speed up
                for(int k = 2 ; k < values.length; k++ )
                {
                    if(!allroles[Integer.valueOf(values[j])].contains(roll[Integer.valueOf(values[k])]))
                    {
                        allroles[Integer.valueOf(values[j])].add(roll[Integer.valueOf(values[k])]);
                        allroles[Integer.valueOf(values[k])].add(roll[Integer.valueOf(values[j])]);
                    }
                }

            }
        }
        // test if the roles could be changed
       // allroles[2].get(1).putColor("p1");
      //  roll[1].putColor("p2");
        System.out.println("************************************************");
        for(int i = 1; i < allroles.length; i++)
            System.out.println(" Roll = " + i + " ->  " + allroles[i]);

        // Set the numbers of vertex to zero
        // this variable count will be used to check the
        // termination condition during the coloring process
        int count = 0;
        boolean [] isColored = new boolean[antal_roller +1];
        int calcul = 0;
        while(count != antal_roller)
        {
            int vertex1 = (int)(1+ Math.random()*(antal_roller-1));

            while (isColored[vertex1]) {
                    vertex1 = (int) (1 + Math.random() * (antal_roller));
                    System.out.println("true = " + vertex1);
            }

            Roller tmp = roll[vertex1];
            ArrayList<Roller> Neighbours = allroles[vertex1];
            String candidate;
            for(int i = 0; i < roll_to_actors[vertex1].size();i++)
            {
                candidate = roll_to_actors[vertex1].get(i);
                int cand_count = 0;
                calcul = calcul + 10;
                for(int k = 0; k <allroles[vertex1].size(); k++)
                {
                    System.out.println("k = " + k);
                    // make sure that p1 is not neighbour to p2
                    if(allroles[vertex1].get(k).color.equals(candidate) || (allroles[vertex1].get(k).color.equals("p1") && candidate.equals("p2") || (allroles[vertex1].get(k).color.equals("p2") && candidate.equals("p1") ) ))
                    {
                        break;
                    }
                    else
                        cand_count++;

                }
                if(cand_count == allroles[vertex1].size())
                {
                    System.out.println("cand_count = " + cand_count);
                    roll[vertex1].putColor(candidate);
                    isColored[vertex1] = true;
                    calcul = 0;
                    count++;
                    for(int j = 1; j < allroles.length; j++)
                        System.out.println(" Roll = " + j + " ->  " + allroles[j]);
                    break;
                }

            }
            // check all the neighbours without success
            if(calcul == (10*roll_to_actors[vertex1].size()))
            {
                System.out.println("New Color please ");
                antal_skodis = antal_skodis + 1;
                roll[vertex1].putColor("p" + antal_skodis);
                isColored[vertex1] = true;
                calcul = 0;
                count++;
            }
            // else create a new color
        }


    }

    static String [] splitter(String lis)
    {
        String [] tra = lis.split(" ");
        return tra;
    }


}
