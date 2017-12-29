package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args)
    {
	// write your code here
        int n = 5;
        cell [][] spelplan = new cell[n][n];
        cell kl = new cell(3,4);
        System.out.println(" The vinicity is : Left " + kl.getLeft(n) + " Down " + kl.getDown(n)+ " Right " + kl.getRight(n));

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                spelplan[i][j] = new cell(i,j);
            }
        }
        System.out.println("spelplan [4][4] = " + spelplan[4][1]);
        // Djistra Algorithm
        // alla pjäser i fram tiden använd random

/*        cell start = spelplan[1][2];

        cell p1 = spelplan[1][4];
        cell p2 = spelplan[2][0];
        cell p3 = spelplan[3][3];
        cell p4 = spelplan[4][1];

        ArrayList<cell> pjaser = new ArrayList<cell>();
        pjaser.add(p1);
        pjaser.add(p2);
        pjaser.add(p3);
        pjaser.add(p4);

        System.out.println(" contains spelplan[2][0] -> " + pjaser.contains(spelplan[3][0]));*/

///**********************Random ******************************
        int count = n;
        ArrayList<cell> pjaser = new ArrayList<cell>();
        while(count > 0)
        {
            int row1 = (int)(Math.random()*n);
            int col1 = (int )(Math.random()*n);
            ArrayList<Integer> no_more =  new ArrayList<Integer>();
            while(true)
            {
                if(!no_more.contains(row1) && !no_more.contains(col1))
                {
                    spelplan[row1][col1].setPiecesId(count);
                    pjaser.add(spelplan[row1][col1]);
                    no_more.add(row1);
                    no_more.add(col1);
                    count--;
                    break;
                }
                else
                {
                    row1 = (int)(Math.random()*n);
                    col1 = (int )(Math.random()*n);
                }

            }
        }

        cell start = pjaser.get((int)(Math.random() * pjaser.size()));
        spelplan[start.posRow][start.posCol].dist = 0;
        spelplan[start.posRow][start.posCol].visited = true;
        System.out.println(" All -> " + pjaser + " ::");
        System.out.println(" Start pjas is :::::: -> " + start + " :::::::::");

        ///-*********************Random end ********************************
        // alla pjäser end !!!!!!!!!!!!!!!!!!!!!!!!!!

        //
        cell current_nearest = new cell(1,2);
        //


        ArrayList<cell> frontier = new ArrayList<cell>();
        ArrayList<cell> Linked;
        ArrayList<cell> new_frontier;
        frontier.add(start);

        while(!frontier.isEmpty())
        {
            new_frontier = new ArrayList<cell>();
            Linked = new ArrayList<cell>();

            for(int i = 0; i < frontier.size(); i++)
            {
                cell current = frontier.get(i);
                Linked.add(current.getDown(n));
                Linked.add(current.getLeft(n));
                Linked.add(current.getRight(n));
                Linked.add(current.getUp(n));

                for(int j = 0; j < Linked.size(); j++)
                {
                    if(Linked.get(j) != null)
                    {
/*                        System.out.println(" row -> " + Linked.get(j));
                        System.out.println(" 1 rowpos -> " + Linked.get(j).posRow);
                        System.out.println(" 2 colpos -> " + Linked.get(j).posCol);
                        System.out.println(" Spelplan rowpos -> " + spelplan[Linked.get(j).posRow][Linked.get(j).posCol]);*/
                        if(!spelplan[Linked.get(j).posRow][Linked.get(j).posCol].visited)
                        {
                            spelplan[Linked.get(j).posRow][Linked.get(j).posCol].dist = current.dist + 1;
                            spelplan[Linked.get(j).posRow][Linked.get(j).posCol].visited = true;
                            new_frontier.add(spelplan[Linked.get(j).posRow][Linked.get(j).posCol]);
                            if(pjaser.contains(spelplan[Linked.get(j).posRow][Linked.get(j).posCol]))
                            {
                                System.out.println(" &&&  " + current_nearest.dist + " &&& ");
                                if((current_nearest.dist > spelplan[Linked.get(j).posRow][Linked.get(j).posCol].dist))
                                {
                                    current_nearest = spelplan[Linked.get(j).posRow][Linked.get(j).posCol];
                                    System.out.println(" &&&  " + current_nearest.dist + " &&& ");
                                }

                            }
                        }
                    }
                }
            }

            frontier = new_frontier;
        }


        //print OUT !!!!!!!!!!!!!!11
        for(int i = 0; i < n; i++)
        {
            System.out.print(" # ");
            for(int j = 0; j < n; j++)
            {
                System.out.print(spelplan[i][j]);
            }
            System.out.println(" # ");
        }

        System.out.println(" # ho ho ho !!! !->  " + current_nearest);

    }
}
