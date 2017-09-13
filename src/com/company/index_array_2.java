/**
 * Created by ben on 2017-09-12.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.*;
import java.io.*;

/**
 * Created by ben on 2017-09-09.
 */
public class index_array_2
{
    public static void main(String [] args)  throws IOException
    {
        MinPQ<Long> pq = new MinPQ<Long>();
        //System.out.println();
        String ab = "AAA";
        int index = hash_str(ab);
        //Index_vector [] indexer = new Index_vector[120000];
        //ArrayList<Index_vector> indexer = new ArrayList<Index_vector>();
        Index_vector[] indexer = new Index_vector[120000];
        System.out.println(" + " + index);

        //BufferedReader br = new BufferedReader(new FileReader("temp.txt"));
        RandomAccessFile br = new RandomAccessFile("temp.txt", "r");
        ArrayList<Integer> position;
        Map<String,ArrayList<Integer>> mapning = new LinkedHashMap<String, ArrayList<Integer>>();

        String thisLine;
        String [] b;


        Index_vector g;
        int begin;
        int end;
        long ln_numb;
        long count = 0;
        //do_that_once(br,indexer);
        long hold;
        pq.insert(count);
        while ((thisLine = br.readLine()) != null) {
            //String in = new String(thisLine.getBytes("ISO-8859-1"), "UTF-8");
//            b = splitter(in);
            b = splitter(thisLine);

            begin = Integer.parseInt(b[1]);
            end = Integer.parseInt(b[b.length-2]);
            //ln_numb = Integer.parseInt(b[b.length-1]);
            hold = br.getFilePointer();
            pq.insert(hold);
            ln_numb = pq.delMin();

       /*     System.out.println("getfilepointer() -> " + ln_numb);
            System.out.println(sub_3(b[0]));
            System.out.println(hash_str(sub_3(b[0]))); */

            if(indexer[hash_str(sub_3(b[0]))] == null)
            {
                g = new Index_vector(sub_3(b[0]),begin, end, ln_numb);
                indexer[hash_str(sub_3(b[0]))] =  g;
            }
        }
        /*System.out.println();*/
    /*    for( Index_vector i : indexer) {
            if(i != null) {
                System.out.println("This indexer -> ? " + i);
                //System.out.println("element -> ? " + indexer[i]);
                br.seek(i.line_nr);
                System.out.println(" read line by indexer getf ? " + br.readLine());
            }
        } */
        ///******************************************************************
/*        int [] l = two_index(indexer,"krazer");
        System.out.println("2 indexes " + l[0] + " " + l[1] );
        System.out.println("1 -> " + indexer[l[0]] + " 2 -> " + indexer[l[1]]); */
        // varje tecken är 1 byte 58-46 -> 12 -> 12 tecken
//        br.seek((long)58);
//        System.out.println("oj ojo -> : " + br.readLine());
        // 3 tecken differens till nästa rad
   /*     br.seek((long)72);
        String tr = br.readLine();
        if(tr.equals(""))
            System.out.println("Tomma listan ??????? ");
        System.out.println("oj ojo -> : " + tr  + " length : " + tr.length());
        long ad = adjust(72,br);
        System.out.println("Adjust long (a) -> : " + ad + " read -> " );
        System.out.println("*******************************************************" ); */
        long s = binary("temp.txt",indexer,"aktieemissioner");
        if(s < 0) {
            System.out.println("ordet finns ej ): ");
        }
        else {
            br.seek(s);
            System.out.println(" long is -> byte () ??? " + s + " " + " ordet line " + br.readLine());
        }
        System.out.println("*******************************************************" );
	br.seek(s);
        String [] raden = splitter(br.readLine());
        int antal_ord = raden.length-1;
        String korpus = "/info/adk17/labb1/korpus";
        long red;
	System.out.println("raden ?? " + raden.toString());
	System.out.println("Antal ord: " + antal_ord);
        RandomAccessFile korp = new RandomAccessFile(korpus, "r");
        for(int i = 1; i < raden.length ; i++) {
            red = Long.parseLong(raden[i]);

            korp.seek(red);
            System.out.println("Varje rad i korpus: " + korp.readLine());
            
        }
    }

    static long binary( String text,Index_vector[] indexer,  String key)  throws IOException
    {

        int [] ind = two_index(indexer, key);
        if(indexer[ind[0]] == null)
            return -1101011;
        long low, high, mid;
        low = indexer[ind[0]].line_nr;

        if(indexer[ind[1]] == null) {
            return low;
        }else
            high = (int)indexer[ind[1]].line_nr;

        long temp;

        if(low > high) {
            temp = low;
            low = high;
            high = temp;
        }

        System.out.println("low -> " + low + " high -> " + high);
        RandomAccessFile br = new RandomAccessFile(text,"r");
        return recursiveBinarySearch(text,low,high,key,br);
    }

    private static long recursiveBinarySearch(String text,long low, long high, String key, RandomAccessFile br)  throws IOException {
        if (low > high)  // The list has been exhausted without a match
            return -low - 1;

        long mid = (long)Math.ceil((low + high) / 2) + 1;

        //String line = Files.readAllLines(Paths.get(text)).get(mid - 1);
        br.seek(low);
        String low_line = br.readLine();
        String bo_l = splitter(low_line)[0];
        System.out.println("low -> " + low_line);

        br.seek(high);
        String high_line = br.readLine();
        String bo_h = splitter(high_line)[0];
        System.out.println("high -> " + high_line);

        long mid_r = adjust(mid,br);
        br.seek(mid_r);
        String mid_line = br.readLine();
        String bo_m = splitter(mid_line)[0];
        System.out.println("mid -> " + mid_line);

        int vrai_faux = bo_m.compareToIgnoreCase(key);

        if (vrai_faux > 0) {
            if (high == mid_r)
                return -10201;
            return recursiveBinarySearch(text, low, mid_r, key, br);
        }
        else if (vrai_faux == 0) {
            return mid_r;
        }
        else {
            if (low == mid_r)
                return -10101;
            return recursiveBinarySearch(text, mid_r, high, key, br);
        }
    }


    static long adjust(long pos, RandomAccessFile ra) throws IOException
    {
        long mid = pos;
        ra.seek(mid);
        String path = ra.readLine();
        if(!path.equals("")) {
            while (!path.equals("")) {
                mid--;
                ra.seek(mid);
                path = ra.readLine();
            }
        }
        else
        {
            while (path.equals("")) {
                mid++;
                ra.seek(mid);
                path = ra.readLine();
            }
            mid--;


        }
        return mid + 1;
    }

    static string output(long pos, RandomAccessFile ra) throws IOException
    {
	int num_readline = 1;        
	long start_of_line = adjust(pos,ra);
	string before_pos_long = "";
	string before_pos = "";

	while (pos - start_of_line < 30) {
            start_of_line = adjust(start_of_line-4,ra);
            num_readline += 1;	
	}
	
	for (int i = 0; i < num_readline; i++)
            before_pos += br.readline();

	before_pos += before_pos_long.substring(-30);

	return 
	
        ra.seek(mid);
        String path = ra.readLine();
        if(!path.equals("")) {
            while (!path.equals("")) {
                mid--;
                ra.seek(mid);
                path = ra.readLine();
            }
        }
        else
        {
            while (path.equals("")) {
                mid++;
                ra.seek(mid);
                path = ra.readLine();
            }
            mid--;


        }
        return mid + 1;
    }

    static int computed_function(char tr)
    {
        int r = (int)tr;
        if(r >= 65 && r <= 90) {
            return (r + 32);
        }
        else if(r >= 97 && r <= 122) {
            return (r);
        }
        else
            return r;
    }

    static int hash_str(String in) throws IOException
    {
        String input = new String(in.getBytes("ISO-8859-1"), "UTF-8");
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

    static String sub_3(String s) throws IOException
    {
        String sub = new String(s.getBytes("ISO-8859-1"), "UTF-8");
        if(sub.length() -1 < 3)
            return sub;
        else
            return sub.substring(0,3);
    }

    static int [] two_index(Index_vector[] indexer, String key) throws IOException
    {
        int [] ind = new int [2];
        int i1 = hash_str(sub_3(key));
        ind[0] = i1;
        int i2;
        for(i2 = i1 + 1; i2 < indexer.length-1; i2++)
        {
            if(indexer[i2] != null)
            {
                break;
            }
        }

        ind[1] = i2;
        return ind;
    }
    //should return the line number
  /*  static long binary( String text,Index_vector[] indexer,  String key)  throws IOException
    {

        int [] ind = two_index(indexer, key);
        if(indexer[ind[0]] == null)
            return -1101011;
        long low, high, mid;
        low = indexer[ind[0]].line_nr;

        if(indexer[ind[1]] == null) {
            return low;
        }else
            high = (int)indexer[ind[1]].line_nr;

        long temp;

        if(low > high) {
            temp = low;
            low = high;
            high = temp;
        }

        System.out.println("low -> " + low + " high -> " + high);
        RandomAccessFile br = new RandomAccessFile(text,"r");
        return recursiveBinarySearch(text,low,high,key,br);
    }

    private static long recursiveBinarySearch(String text,long low, long high, String key, RandomAccessFile br)  throws IOException {
        if (low > high)  // The list has been exhausted without a match
            return -low - 1;

        int mid = (int)Math.ceil((low + high) / 2) + 1;

        //String line = Files.readAllLines(Paths.get(text)).get(mid - 1);
        br.seek(low);
        String low_line = br.readLine();
        String bo_1 = splitter(low_line)[0];
        //read line number
        //int line_nr_low = Integer.parseInt(bo_1[bo_1.length()-1]);

        br.seek(high);
        String high_line = br.readLine();
        String bo_2 = splitter(high_line)[0];
        //read line number
        //int line_nr_high = Integer.parseInt(bo_2[bo_2.length()-1]);
        //int mid = (int)Math.ceil((line_nr_low + line_nr_high) / 2) + 1;
        int vrai_faux = bo.compareToIgnoreCase(key);

        if (vrai_faux > 0)
            return recursiveBinarySearch(text, low, mid - 1, key);
        else if (vrai_faux == 0)
            return mid;
        else
            return recursiveBinarySearch(text, mid + 1, high, key);
    }
*/
    static String [] splitter(String lis)
    {
        String [] tra = lis.split(" ");
        return tra;
    }

}

