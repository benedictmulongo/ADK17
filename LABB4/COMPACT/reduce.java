
package com.company;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by ben on 2017-11-15.
 */
public class reduce
{
    public static void main (String [] args) throws IOException
    {
        String NEW_LINE_SEPARATOR = "\n";
        //    File text = new File("data.txt");
        Scanner sc = new Scanner(System.in);
        int antal_horn = Integer.valueOf(sc.nextLine()); // v
        int antal_kanter = Integer.valueOf(sc.nextLine()); // e
        int antal_farg = Integer.valueOf(sc.nextLine()); // m

        // If the colours are more or equal than vertices, the answer is yes
        // Output minimal 'yes' casting graph
        if (antal_farg >= antal_horn) {
            generate();

        } else {
            // Roles
            int antal_roller = antal_horn + 3;
            // Scenes
            int antal_scenner = antal_kanter + 2 + antal_horn;
            // Actors
            int actors = antal_farg + 2;

            System.out.println(antal_roller);
            System.out.println(antal_scenner);
            System.out.println(actors);
            // Minimal roles
            System.out.println("1 1");
            System.out.println("1 2");
            System.out.println("1 3");


            for (int i = 4; i <= antal_roller; i++) {
                for (int j = 1; j <= actors; j++) {
                    if (j == 1) {
                        System.out.print(actors + " " + j);
                    } else {
                        System.out.print(" " + j);
                    }
                }
                System.out.println(" ");
            }


            // Minimal scenes
            System.out.println("2 1 3" );
            System.out.println("2 2 3");

            // Ensure no isolated vertices (connect all vertices to scene 3)
            for (int i = 1; i <= antal_horn; i++) {
                System.out.println("2 3 " + (i + 3));
            }

            for (int i = 0; i < antal_scenner && sc.hasNext() ; i++) {
                String line = sc.nextLine();
                String [] values = splitter(line);
                System.out.println("2 " + (Integer.valueOf(values[0]) + 3) + " " + (Integer.valueOf(values[1]) + 3));
            }
        }
    }

    static void generate() {
        String NEW_LINE_SEPARATOR = "\n";
        System.out.println("3");
        System.out.println("2");
        System.out.println("3");
        System.out.println("1 1");
        System.out.println("1 2");
        System.out.println("1 3");
        System.out.println("2 1 3");
        System.out.println("2 2 3");

    }
    static String [] splitter(String lis)
    {
        String [] tra = lis.split(" ");
        return tra;
    }
}