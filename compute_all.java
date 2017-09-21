package com.company;
import java.io.*;
import java.util.*;
/**
 * Created by ben on 2017-09-21.
 */
public class compute_all
{
        public static void main(String [] args) throws IOException
        {
            System.out.print("Skriv in det ord du söker : ");
            Scanner in = new Scanner(System.in);
            String input = in.next();

            MinPQ<Long> pq = new MinPQ<Long>();
            Index_vector[] indexer = new Index_vector[250];

            RandomAccessFile br = new RandomAccessFile("ordlista.txt", "r");
            BufferedInputStream fromLista1 = new BufferedInputStream(new FileInputStream(new File("ordlista.txt")));
            String thisLine;
            String [] b;


            Index_vector g;
            long ln_numb;
            long count = 0;
            long hold = 0;
            pq.insert(count);

            int len;
            while (true) {

                if (Mio.EOF(fromLista1)) {
                    break;
                } else {
                    thisLine = Mio.GetLine(fromLista1);
                }

                int dist = edit(input,thisLine);
                if(dist < 3) {
                    System.out.println(thisLine + " Dist " + dist);
                    System.out.println("thisline -> "+ thisLine);
                }

            }

/*            while (true) {

                if (Mio.EOF(fromLista1)) {
                    break;
                } else {
                    thisLine = Mio.GetLine(fromLista1);
                }

                b = splitter(thisLine);
                len = thisLine.length();

                hold = hold + len + 1;
                pq.insert(hold);
                ln_numb = pq.delMin();

                if(indexer[hsh(b[0])] == null)
                {
                    g = new Index_vector(b[0].charAt(0), ln_numb);
                    indexer[hsh(b[0])] =  g;
                    //System.out.println(indexer[hash_str(sub_3(b[0]))]);
                    System.out.println( "string -> " + b[0] + " char " + b[0].charAt(0) + " hash -> " + hsh(b[0]));
                }
            }
            for(Index_vector v : indexer) {
                if(v != null)
                    System.out.println("this -> " + v);
            }*/

        }

            /*
            open the file with buffered input stream
            use queu systeme to find the position av each line
            creat a array av holder A[]
            for every read line get the first letter L and hash to a int value
            set A[hash(letter)].add(position av the first occuren of L and the last occurences of L)

            for every input key compute the first letter then -> find position x1 to x2
            calculate e = edit_dist(key, every word in [x1,x2])
            create a array of holder B[]
            for every e set B[e] = every word with have the edit distance e betwen key and [x1,x2]
            return B[1,2,3].arraylist

             */
            static int hsh(String tr) throws IOException
            {
                String in = new String(tr.getBytes("ISO-8859-1"), "UTF-8");
                char a = in.charAt(0);
                String br = "å ä ö";
                String [] k = splitter(br);
                if(String.valueOf(a).equals(k[0]))
                    return 123;
                if(String.valueOf(a).equals(k[1]))
                    return 124;
                if(String.valueOf(a).equals(k[2]))
                    return 125;

                int r = (int)a;
                if(r >= 65 && r <= 90) {
                    return (r + 32);
                }
                else if(r >= 97 && r <= 122) {
                    return (r);
                }
                else
                    return r;
            }

    static String [] splitter(String lis)
    {
        String [] tra = lis.split(" ");
        return tra;
    }

    public static int edit(String a, String b)
    {
        return editDistDP(a, b, a.length(), b.length());
    }

    public static int edit(String a, String b, int n, int m)
    {
        int [][] C = new int [n+1][m+1];
        int X,Y,Z;
        for(int i = 0; i < n; i++)
            C[i][0] = i;
        for(int j = 1; j < n; j++)
            C[0][j] = j;

        for(int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                // *****If first string is empty, only option is to
                // isnert all characters of second string
                if (i==0)
                    C[i][j] = j;  // Min. operations = j
                else if (j==0)
                    C[i][j] = i; // Min. operations = i
                //******

                X = C[i-1][j] + 1;
                Y = C[i][j-1] + 1;
                if(a.charAt(i-1) == b.charAt(j-1)) {
                    Z = C[i - 1][j - 1];
                }else
                {// + 1 or + 2
                    Z = C[i - 1][j - 1] + 2;
                }
                C[i][j] = min(X,Y,Z);
            }
        }
        return C[n][m];
    }
/*    public static int min(int X,int Y,int Z)
    {
        int m = X;
        if(Y < m)
            m = Y;
        if(Z < m)
            m = Z;

        return m;
    }*/

    static int min(int x,int y,int z)
    {
        if (x < y && x <z) return x;
        if (y < x && y < z) return y;
        else return z;
    }

    static int editDistDP(String str1, String str2, int m, int n)
    {
        // Create a table to store results of subproblems
        int dp[][] = new int[m+1][n+1];

        // Fill d[][] in bottom up manner
        for (int i=0; i<=m; i++)
        {
            for (int j=0; j<=n; j++)
            {
                // If first string is empty, only option is to
                // isnert all characters of second string
                if (i==0)
                    dp[i][j] = j;  // Min. operations = j

                    // If second string is empty, only option is to
                    // remove all characters of second string
                else if (j==0)
                    dp[i][j] = i; // Min. operations = i

                    // If last characters are same, ignore last char
                    // and recur for remaining string
                else if (str1.charAt(i-1) == str2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];

                    // If last character are different, consider all
                    // possibilities and find minimum
                else
                    dp[i][j] = 1 + min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1]); // Replace
            }
        }

        return dp[m][n];
    }
}
