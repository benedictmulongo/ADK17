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
public class index_array_3
{
    public static void main(String [] args)  throws IOException
    {
        String input_key = "";
        if( args.length >= 1)
            input_key = args[0];

        MinPQ<Long> pq = new MinPQ<Long>();

        Index_vector[] indexer = new Index_vector[180000];
//sätt in the sista elementet position av sista ordet som förekkommer
//indexer[indexer.length-1] = ;
 
        RandomAccessFile br = new RandomAccessFile("lista2.txt", "r");
	BufferedInputStream fromLista1 = new BufferedInputStream(new FileInputStream(new File("lista2.txt")));
        ArrayList<Integer> position;
        Map<String,ArrayList<Integer>> mapning = new LinkedHashMap<String, ArrayList<Integer>>();
String abc = "öööööööö";
String abcd = "öööh";



        String thisLine;
        String [] b;


        Index_vector g;
        int begin;
        int end;
        long ln_numb;
        long count = 0;
        //do_that_once(br,indexer);
        long hold = 0;
        pq.insert(count);
int len;
        while (true) {
            
           if (Mio.EOF(fromLista1)) {
                break;
            }
            else {
                thisLine = Mio.GetLine(fromLista1);
            }

            b = splitter(thisLine);
            len = thisLine.length();
            begin = Integer.parseInt(b[1]);
            end = Integer.parseInt(b[b.length-1]);

            hold = hold + len + 1;
            pq.insert(hold);
            ln_numb = pq.delMin();

//System.out.println("string oord -> " + b[0] + " hash : -> " + hash_str(sub_3(b[0])) + " ok ?"); 
            if(indexer[hash_str(sub_3(b[0]))] == null)
            {
                g = new Index_vector(sub_3(b[0]),begin, end, ln_numb);
                indexer[hash_str(sub_3(b[0]))] =  g;
                //System.out.println(indexer[hash_str(sub_3(b[0]))]); 
            }
        }

//System.out.println("is empty ? " + pq.isEmpty() + " size " + pq.size());
//System.out.println(" min " + pq.min());
br.seek(pq.min());
long p = adjust(pq.min()-10,br);
br.seek(p);
//System.out.println("sista raden? " + br.readLine());

indexer[indexer.length-1] = new Index_vector("öööh",0, 0, p);
//System.out.println("is empty ? " + pq.isEmpty() + " size " + pq.size());


        System.out.println("************************1*****************************" ); 
        //long s = binary("lista2.txt",indexer,"aktieemissioner");
        long s = binary("lista2.txt",indexer,input_key);
	//System.out.println(s);
        System.out.println("********************binary**********************" ); 
        if(s < 0) {
            System.out.println("ordet finns ej ): ");
        }
        else {
            br.seek(s);
            //System.out.println(" long is -> byte () ??? " + s + " " + " ordet line " + br.readLine());
		System.out.println("*******************************************************" );
		//br.seek(s);
		String [] raden = splitter(br.readLine());
		int antal_ord = raden.length-1;
		String korpus = "/info/adk17/labb1/korpus";
		long red;
		//System.out.println("raden ?? " + raden.toString());
		System.out.println("Antal förekomster av ord i korpus: " + antal_ord);
	      
		RandomAccessFile korp = new RandomAccessFile(korpus, "r");
       if(antal_ord < 25)
       {
          /* for(int i = 1; i < r*******************************************************
aden.length ; i++) {
            red = Long.parseLong(raden[i]);
            System.out.println(read_60(red,korp,input_key));
            
          }*/
          skriv_ut(raden,input_key,korp);
        }
        else
        {
            System.out.println("Antal förekomster överskrider 25. Vill du ändå har de utskrivna ? (yes/no)");
            Scanner input = new Scanner(System.in);
            String k = input.next();
            if( k.equals("yes"))
{
skriv_ut(raden,input_key,korp);
}
else
{
System.out.println("Hejdå !");
}
            
        }
	}
    }

    static void skriv_ut(String [] raden, String input_key, RandomAccessFile korp) throws IOException
{
long red;
                for(int i = 1; i < raden.length ; i++) {
            red = Long.parseLong(raden[i]);
            System.out.println(read_60(red,korp,input_key));
            
          }
}

    static long binary( String text,Index_vector[] indexer,  String key)  throws IOException
    {

        int [] ind = two_index(indexer, key);
	System.out.println(Arrays.toString(ind));
        if(indexer[ind[0]] == null)
{
System.out.println("Break before return");            
return -1101011;
}
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

System.out.println(low + " " + high);

        //System.out.println("low -> " + low + " high -> " + high);
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
        System.out.println("low -> " + bo_l);

        br.seek(high);
        String high_line = br.readLine();
        String bo_h = splitter(high_line)[0];
        System.out.println("high -> " + bo_h);
long mid_r;
//ändringar
        br.seek(mid);
        br.readLine();
	if (br.getFilePointer() == high) {
	        mid_r = adjust(mid,br);
	} else {
		mid_r = br.getFilePointer();
	}
        //long mid_r = adjust(mid,br);
//long mid_r = adjust(mid,br);
System.out.println("Adjust är klar ? ");
String bo_m;
        br.seek(mid_r);
        String mid_line = br.readLine();
	if (bo_l.equals(bo_h)) {
	return -1010;}
	else {
        bo_m = splitter(mid_line)[0];  }
        System.out.println("mid -> " + bo_m );
System.out.println("Mid är läst ? ");
	if (bo_l.compareToIgnoreCase(key) == 0)
		return low;
	if (bo_h.compareToIgnoreCase(key) == 0)
		return high;
        int vrai_faux = bo_m.compareToIgnoreCase(key);
//if(low_line.length() > (high_line.length-low_line.length)/2)

        if (vrai_faux > 0) {
	System.out.println("bo_m " + bo_m + " compare " + vrai_faux);
            if (high == mid_r)
                return -10201;
            return recursiveBinarySearch(text, low, mid_r, key, br);
        }
        else if (vrai_faux == 0) {
        System.out.println("bo_m " + bo_m + " compare " + vrai_faux);

            return mid_r;
        }
        else {
        System.out.println("bo_m " + bo_m + " compare " + vrai_faux);
if(low_line.length() > (high_line.length()-low_line.length())/2)
{
      mid_r = br.getFilePointer();
}
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

   /* static long adj(long pos, RandomAccessFile ra, int low_line, int high_line) throws IOException
    {
        long mid = pos;
        ra.seek(mid);
        String path = ra.readLine();
long mid_r;
if(low_line.length() > (high_line.length()-low_line.length())/2)
{
      mid_r = br.getFilePointer();
}
        if(!path.equals("")) {
            while (!path.equals("")) {
                mid++;
                ra.seek(mid);
                path = ra.readLine();
            }
        }
        else
        {
            while (path.equals("")) {
                mid--;
                ra.seek(mid);
                path = ra.readLine();
            }
            mid++;


        }
        return mid - 1;ävsborgsföretag
    }*/


    static String read_60(long pos, RandomAccessFile br, String key) throws IOException
    {

        StringBuilder build = new StringBuilder();
        StringBuilder bu = new StringBuilder();

        long pos1,pos2;
        pos1 = pos - 30;
        pos2 = pos + 30 + key.length() + 1 ;
        byte t;
        int n = (int)(pos2-pos1);
        byte [] temp = new byte[n + 1];
        int count = 0;
        for(long i = pos1; i < pos2 + 1; i++)
        {
            br.seek(i);
            t = br.readByte();
            temp[count++] = t;
          //  System.out.println("char ? " + (char)t + " byte ? " + t);
            build.append((char)t);
        }

        String b = build.toString();
        String delim = " \n\r\t,.;"; //insert here all delimitators
        StringTokenizer st = new StringTokenizer(b,delim);
        //System.out.println(st);
        while (st.hasMoreTokens()) {
            bu.append(st.nextToken());
            bu.append(" ");
        }

        return bu.toString();
    }

    static int computed_function(char tr)
    {
//System.out.println("char tr -> " + tr + "value int " + (int)tr);
String br = "å ä ö";
//return
String [] t = splitter(br);
if(String.valueOf(tr).equals(t[0]))
 {  //System.out.println("Matchning Å");
return 101; }
if(String.valueOf(tr).equals(t[1]))
{ //System.out.println("Matchning Ä");
return 151; }
if(String.valueOf(tr).equals(t[2]))
{ //System.out.println("Matchning Ö");
return 191; }
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
        String input = new String(in.getBytes("ISO-8859-1"), "ISO-8859-1");
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
        String sub = new String(s.getBytes("ISO-8859-1"), "ISO-8859-1");
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

    static String [] splitter(String lis)
    {
        String [] tra = lis.split(" ");
        return tra;
    }

}

