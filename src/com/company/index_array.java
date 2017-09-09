package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.*;

/**
 * Created by ben on 2017-09-09.
 */
public class index_array
{
    private class Index_vector {
        String key;           // sorted by key
        int begin;
        int end;

        public Index_vector(String key, int a, int b) {
            this.key = key;
            this.begin = a;
            this.end = b;
        }
    }

    public static void main(String [] args)  throws IOException
    {

        //System.out.println();
        String ab = "AAA";
        int index = hash_str(ab);
        //Index_vector [] indexer = new Index_vector[120000];
        ArrayList<Index_vector> indexer = new ArrayList<Index_vector>();
        System.out.println(" + " + index);


        BufferedReader br = new BufferedReader(new FileReader("temp.txt"));
        ArrayList<Integer> position;
        Map<String,ArrayList<Integer>> mapning = new LinkedHashMap<String, ArrayList<Integer>>();

        String thisLine;
        String [] b;
        while ((thisLine = br.readLine()) != null) {

            b = splitter(thisLine);

            if(mapning.containsKey(b[0]))
            {
                mapning.get(b[0]).add(Integer.parseInt(b[1]));
            }
            else
            {
                position =  new ArrayList<Integer>();
                position.add(Integer.parseInt(b[1]));
                mapning.put(b[0],position);
            }

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

    static String [] splitter(String lis)
    {
        String [] tra = lis.split(" ");
        return tra;
    }

    static String sub_3(String sub)
    {
        return sub.substring(0,2);
    }

}
