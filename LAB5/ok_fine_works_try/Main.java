package com.company;

import java.util.*;
import java.io.*;

public class Main
{

    public static void main(String[] args) throws IOException
    {
         File text = new File("graph.txt");
        Scanner sc = new Scanner(text);

//use kattio to read in from file to speed up
        int antal_roller = Integer.valueOf(sc.nextLine());
        int antal_scener = Integer.valueOf(sc.nextLine());
        int antal_skodis = Integer.valueOf(sc.nextLine());

        ArrayList<String>[] roll_to_actors = new ArrayList[antal_roller+1];
        for(int i = 0; i < roll_to_actors.length; i++ )
            roll_to_actors[i] = new ArrayList<>();

        ArrayList<Integer>[] Actors_bis = new ArrayList[antal_roller+antal_skodis];
        for(int i = 0; i < Actors_bis.length; i++ )
            Actors_bis[i] = new ArrayList<>();

        int []  actors_getroles = new int [antal_roller+antal_skodis];
        for(int i = 0; i <actors_getroles.length; i++ )
            actors_getroles[i] = 0;

        int role = 1;
        //************** IMPORTANT ******************
        //initialize role_to_actors p + index
        // ***************
        // test for p1 - p2
        ArrayList<Integer>[] p1_p2 = new ArrayList[3];
        for(int i = 0; i < p1_p2.length; i++ )
            p1_p2[i] = new ArrayList<>();

        for(int i = 0; i < antal_roller; i++)
        {
            String line = sc.nextLine();
            String [] values = splitter(line);
            for(int j = 1; j < values.length; j++) {
                roll_to_actors[role].add("p" +values[j]);
                if(values[j].equals("1") || values[j].equals("2"))
                {
                    p1_p2[Integer.valueOf(values[j])].add(role);
                }
            }

            role++;
        }
/*        int inc = 0;
        for(ArrayList<Integer> e : p1_p2)
        {
            System.out.println(" [Skadespelare  " + inc++ + "] could play  ->  " + e);
        }

        // test if the role_to_actors work
        int incr = 0;
        for(ArrayList<String> e : roll_to_actors)
        {
            System.out.println(" [Roll  " + incr++ + "] could be played by  ->  " + e);
        }*/


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
                    if((!allroles[Integer.valueOf(values[j])].contains(roll[Integer.valueOf(values[k])]) && (!(values[j]).equals(values[k]))  ))
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
/*        System.out.println("************************************************");
        for(int i = 1; i < allroles.length; i++)
            System.out.println(" Roll = " + i + " ->  " + allroles[i]);*/

        // Set the numbers of vertex to zero
        // this variable count will be used to check the
        // termination condition during the coloring process
        int count = 0;
        boolean [] isColored = new boolean[antal_roller +1];
        int super_Actors = 0;
        /******************************************
         Make sure that p1 and p2 always play
         ******************************************/


        int temp = (int) (Math.random() * (p1_p2[1].size()));
        int vertex_temp = p1_p2[1].get(temp);
        while(true)
        {
            if(allroles[vertex_temp].size() <= (antal_roller - 2))
            {
                break;
            }
            else
            {
                //  System.out.println("oh oh oh oh = ? " + vertex_temp);
                temp = (int) (Math.random() * (p1_p2[1].size()));
                vertex_temp = p1_p2[1].get(temp);
            }
        }

        //    System.out.println("vertex of  1 is  == " + vertex_temp);
        roll[vertex_temp].putColor("p1");
        isColored[vertex_temp] = true;
        actors_getroles[1] = 1;

        for(int i = 0; i < p1_p2[2].size();i++)
        {
            vertex_temp = p1_p2[2].get(i);
            //   System.out.println("vertex_temp == " + vertex_temp);
            int p2_count = 0;
            for(int k = 0; k <allroles[vertex_temp].size(); k++)
            {
                //     System.out.println("k = " + k);
                // make sure that p1 is not neighbour to p2
                if(allroles[vertex_temp].get(k).color.equals("p1"))
                {
                    break;
                }
                else
                    p2_count++;
            }
            if(p2_count == allroles[vertex_temp].size())
            {
                //       System.out.println("p2_count = " + p2_count);
                roll[vertex_temp].putColor("p2");
                actors_getroles[2] = 1;
                isColored[vertex_temp] = true;
                break;
            }


        }
        count = 2;
/*        for(int j = 1; j < allroles.length; j++)
            System.out.println(" **** Roll = " + j + " ->  " + allroles[j]);*/


        //****************
        while(count != antal_roller)
        {
            int vertex1 = (int)(1+ Math.random()*(antal_roller));

            while (true)
            {
                if(isColored[vertex1])
                {
                    vertex1 = (int) (1 + Math.random() * (antal_roller));
                }
                else
                {
                    break;
                }
        }

            String candidate;
            for(int i = 0; i < roll_to_actors[vertex1].size();i++)
            {
                candidate = roll_to_actors[vertex1].get(i);
                int cand_count = 0;
                for(int k = 0; k <allroles[vertex1].size(); k++)
                {
                    // make sure that p1 is not neighbour to p2
                    if(allroles[vertex1].get(k).color.equals(candidate) || (allroles[vertex1].get(k).color.equals("p1") && candidate.equals("p2") || (allroles[vertex1].get(k).color.equals("p2") && candidate.equals("p1") ) ))
                    {
                        break;
                    }
                    else
                        cand_count++;

                }
                if((cand_count == allroles[vertex1].size()) && (!isColored[vertex1]) && (roll[vertex1].color.equals("")) )
                {
                    //      System.out.println("cand_count = " + cand_count);
                    roll[vertex1].putColor(candidate);
                    String ind = candidate.substring(1,candidate.length());
                    //      System.out.println("Candidate " + candidate + " ind = "+ ind);
                    int index = Integer.valueOf(ind);
                    //       System.out.println( " Candidate " + candidate + " Index = " + index + " vertex1 roll = " + vertex1 );
                    actors_getroles[index]++;
                    isColored[vertex1] = true;
                    count++;
/*                    for(int j = 1; j < allroles.length; j++)
                        System.out.println(" Roll = " + j + " ->  " + allroles[j]);*/

                    break;
                }

            }
            // check all the neighbours without success
            // create a new color
            if((super_Actors <= antal_roller -1) && (!isColored[vertex1]) && (roll[vertex1].color.equals("")) )
            {
                //  System.out.println("New Color please ");
                antal_skodis = antal_skodis + 1;
                super_Actors++;
                roll[vertex1].putColor("p" + antal_skodis);
                actors_getroles[antal_skodis]++;
                isColored[vertex1] = true;
                count++;
            }
        }
        //     System.out.println(" *********** FINAL RESULT *********** : ");
        //    System.out.println("Antal superskÃ¥disar = " + super_Actors);
/*        for(int j = 1; j < allroles.length; j++)
            System.out.println(" Roll = " + j + " ->  " + allroles[j]);*/

        for(int i = 1; i < roll.length; i++)
        {
         //   System.out.println("roll nr = " + i + " spelas av -> " + roll[i].color);

            String tmp = roll[i].color;
            String ind = tmp.substring(1,tmp.length());
            //      System.out.println("Candidate " + candidate + " ind = "+ ind);
            int index = Integer.valueOf(ind);
            Actors_bis[index].add(i);
        }

        int sum = 0;
        for(int r = 0; r < actors_getroles.length; r++)
        {
            if(actors_getroles[r] != 0)
            {
                sum++;
            }
        }
        // FINAL OUTPUT
        //Antal skÃ¥despelare
        System.out.println("" + sum );
        for(int j = 1; j < antal_skodis + 1; j++)
        {
            if(Actors_bis[j].size() > 0)
            {
                System.out.print("" + j + " " + Actors_bis[j].size());
                for(Integer e : Actors_bis[j])
                {
                    System.out.print(" " + e);
                }
                System.out.println(" ");
            }

        }

    }

    static String [] splitter(String lis)
    {
        String [] tra = lis.split(" ");
        return tra;
    }
}

