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
public class create_array {
    
    public static void main(String [] args)  throws IOException {

        MinPQ<Long> pq = new MinPQ<Long>();

        Index_vector[] indexer = new Index_vector[28000];
 
        RandomAccessFile br = new RandomAccessFile("lista2.txt", "r");
        BufferedInputStream fromLista2 = new BufferedInputStream(new FileInputStream(new File("lista2.txt")));
        BufferedOutputStream toLista3 = new BufferedOutputStream(new FileOutputStream(new File("lista3.txt")));

        String thisLine;
        String [] b;


        Index_vector g;
        int begin;
        int end;
        long ln_numb;
        long count = 0;
        long hold = 0;
        pq.insert(count);
        int len;
        while (true) {
            
           if (Mio.EOF(fromLista2)) {
                break;
            }
            else {
                thisLine = Mio.GetLine(fromLista2);
            }

            b = splitter(thisLine);
            len = thisLine.length();
            begin = Integer.parseInt(b[1]);
            end = Integer.parseInt(b[b.length-1]);

            hold = hold + len + 1;
            pq.insert(hold);
            ln_numb = pq.delMin();

            if(indexer[hash_str(sub_3(b[0]))] == null)
            {
                g = new Index_vector(sub_3(b[0]),begin, end, ln_numb);
                indexer[hash_str(sub_3(b[0]))] =  g;
            }
        }

        br.seek(pq.min());
        long p = adjust(pq.min()-10,br);
        br.seek(p);

        indexer[indexer.length-2] = new Index_vector("öööh",0, 0, p);

        toLista3.write(Arrays.toString(indexer).getBytes());
        //toLista3.flush();
        toLista3.close();

    }

    static long adjust(long pos, RandomAccessFile ra) throws IOException {
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

    static int computed_function(char tr) {
        //System.out.println("char tr -> " + tr + "value int " + (int)tr);
        String br = "å ä ö";
        String br2 = "Å Ä Ö";
        //return
        String [] t = splitter(br);
        String [] t2 = splitter(br2);

        int r = (int)tr;
        int index = r;

        if(String.valueOf(tr).equals(t[0])) {  //System.out.println("Matchning Å");
            index = 123;
        }
        
        if(String.valueOf(tr).equals(t[1])) { //System.out.println("Matchning Ä");
            index = 124;
        }
        
        if(String.valueOf(tr).equals(t[2])) { //System.out.println("Matchning Ö");
            index = 125;
        }
        if(String.valueOf(tr).equals(t2[0])) {  //System.out.println("Matchning Å");
            index = 123;
        }
        
        if(String.valueOf(tr).equals(t2[1])) { //System.out.println("Matchning Ä");
            index = 124;
        }
        
        if(String.valueOf(tr).equals(t2[2])) { //System.out.println("Matchning Ö");
            index = 125;
        }
        
        if(r >= 65 && r <= 90) {
            index = (r + 32);
        }

        //System.out.println(index-96);
        return index-96;
    }

    static int hash_str(String in) throws IOException {
        String input = new String(in.getBytes("ISO-8859-1"), "ISO-8859-1");
        int index = 0;
        char [] abc = new char[3];
        int s = 900;
        int len = input.length();
        for(int i = 0; i < len; i++) {
            abc[i] = input.charAt(i);
            index = index + computed_function(input.charAt(i))*s;
            s = s /30 ;
        }
        return index;
    }

    static String sub_3(String s) throws IOException {
        String sub = new String(s.getBytes("ISO-8859-1"), "ISO-8859-1");
        if(sub.length() -1 < 3)
            return sub;
        else
            return sub.substring(0,3);
    }

    static String [] splitter(String lis){
        String [] tra = lis.split(" ");
        return tra;
    }

}

