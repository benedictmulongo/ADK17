package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

/**
 * Created by ben on 2017-09-09.
 */
public class index_array
{
    public static void main(String [] args)  throws IOException
    {

        //System.out.println();
        String ab = "AAA";
        int index = hash_str(ab);
        //Index_vector [] indexer = new Index_vector[120000];
        //ArrayList<Index_vector> indexer = new ArrayList<Index_vector>();
        Index_vector[] indexer = new Index_vector[120000];
        System.out.println(" + " + index);


        BufferedReader br = new BufferedReader(new FileReader("temp.txt"));
        ArrayList<Integer> position;
        Map<String,ArrayList<Integer>> mapning = new LinkedHashMap<String, ArrayList<Integer>>();

        String thisLine;
        String [] b;


        Index_vector g;
        int begin;
        int end;
        int ln_numb;

        while ((thisLine = br.readLine()) != null) {

            b = splitter(thisLine);

            begin = Integer.parseInt(b[1]);
            end = Integer.parseInt(b[b.length-2]);
            ln_numb = Integer.parseInt(b[b.length-1]);

            if(indexer[hash_str(sub_3(b[0]))] == null)
            {
                //mapning.get(b[0]).add(Integer.parseInt(b[1]));
                //Index_vector g;
                g = new Index_vector(sub_3(b[0]),begin, end, ln_numb);
                indexer[hash_str(sub_3(b[0]))] =  g;
            }

        }
        /*System.out.println();*/
        for( Index_vector i : indexer) {
            if(i != null)
                System.out.println("This indexer -> ? " + i);
        }
        int [] l = two_index(indexer,"Winor");
        System.out.println("2 indexes " + l[0] + " " + l[1] );
        System.out.println("1 -> " + indexer[l[0]] + " 2 -> " + indexer[l[1]]);

        System.out.println("Now .... it is beginning : >>>>>>>");
        System.out.println(binary("temp.txt",indexer,"Winor"));
        System.out.println("Weah ...... or    whoo >>>>>>>>>>>>");

        //Final step !!!!!!!!!!!!!!!!!!!
        //read lines
        /*String line32 = Files.readAllLines(Paths.get("temp.txt")).get(4);*/
        //get the line number
        int ln = binary("temp.txt",indexer,"key");
        //get line n with n - 1
        String read_ln = Files.readAllLines(Paths.get("temp.txt")).get(ln-1);
        //splitt the line with String key searched and occurences index -> 2 ... random_ac.length-2
        String [] random_ac = splitter(read_ln);
        //get the text file L  read at random from index i = random_ac[1] .... random_ac[random_ac.length - 2]
        String fl = "File L";
        RandomAccessFile rd = new RandomAccessFile(fl , "r");
        int count = 0;
        //       for( int i = random_ac[1] .... random_ac[random_ac.length - 2])

        for( int j = Integer.parseInt(random_ac[count]); count <= 25 ;  j++)
        {
            count++;
            rd.seek(j);
            System.out.println(rd.readLine());
//            if ( count > 25)
//                "do you want more"
//            yes -> continue
//                no -> break
        }



    }

    static int computed_function(char tr)
    {
        int r = (int)tr;
        System.out.println(" char in f(x) -> " + r);
        if(r >= 65 && r <= 90) {
            return (r + 32);
        }
        else if(r >= 97 && r <= 122) {
            return (r);
        }
        else
            return r;
    }

    static int hash_str(String input)
    {
        int index = 0;
        char [] abc = new char[3];
        int s = 900;
        int len = input.length();
        if(len >= 3)
        {
            for(int i = 0; i < 3; i++) {
                abc[i] = input.charAt(i);
                index = index + computed_function(input.charAt(i))*s;
                s = s /30 ;
            }
        }
        else
        {
            for(int i = 0; i < len-1; i++) {
                abc[i] = input.charAt(i);
                index = index + computed_function(input.charAt(i))*s;
                s = s /30 ;
            }

        }
        return index;
    }

    static String sub_3(String sub)
    {
        return sub.substring(0,3);
    }

    static int [] two_index(Index_vector[] indexer, String key)
    {
        int [] ind = new int [2];
        int i1 = hash_str(sub_3(key));
        ind[0] = i1;
        int i2;
        for(i2 = i1 + 1; i2 < indexer.length-1; i2++)
        {
            if(indexer[i2] != null)
                break;
        }
        ind[1] = i2;
        return ind;
    }
    //should return the line number
    static int binary( String text,Index_vector[] indexer,  String key)  throws IOException
    {

        int [] ind = two_index(indexer, key);
        int low, high, mid;
        low = indexer[ind[0]].line_nr;
        if(indexer[ind[1]] == null) {
            return low;
        }else
            high = indexer[ind[1]].line_nr;

        int temp;

        if(low > high) {
            temp = low;
            low = high;
            high = temp;
        }


        return recursiveBinarySearch(text,low,high,key);
    }

    private static int recursiveBinarySearch(String text,int low, int high, String key)  throws IOException {
            if (low > high)  // The list has been exhausted without a match
                return -low - 1;

            int mid = (int)Math.ceil((low + high) / 2) + 1;
            String line = Files.readAllLines(Paths.get(text)).get(mid - 1);
            String bo = splitter(line)[0];
            int vrai_faux = bo.compareToIgnoreCase(key);

            if (vrai_faux > 0)
                return recursiveBinarySearch(text, low, mid - 1, key);
            else if (vrai_faux == 0)
                return mid;
            else
                return recursiveBinarySearch(text, mid + 1, high, key);
        }

    static String [] splitter(String lis)
    {
        String [] tra = lis.split(" ");
        return tra;
    }

}
