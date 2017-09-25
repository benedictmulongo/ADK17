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

            RandomAccessFile br = new RandomAccessFile("ordlista.txt", "r");
            BufferedInputStream fromLista1 = new BufferedInputStream(new FileInputStream(new File("ordlista.txt")));
            String thisLine;

            int dist;
            while (true)
            {
                if (Mio.EOF(fromLista1)) {
                    break;
                } else {
                    thisLine = Mio.GetLine(fromLista1);
                }

                dist = editDistance(input, thisLine);
                if (dist <= 2) {
                    System.out.println(thisLine + " Dist " + dist);
                    System.out.println(" thisline -> " + thisLine);
                }
            }
            String s = "dabbbhud";
            String f = "nabbad";
           System.out.println(" s - f right ? " + editDistance(s,f));
            System.out.println(" s - f right *** new ***  ? " + edit_dist(f,s));
/*            System.out.println("list[0] " + lista[3].hold.toString());*/

        }

    public static int edit(String a, String b)
    {
        return edit(a, b, a.length(), b.length());
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

    static int min_edit(String a, String b)
    {
        int len1 = a.length();
        int len2 = b.length();

        int [] col = new int[len2 + 1];
        int [] prevCol = new int[len2 + 1];

        for(int i = 0; i < prevCol.length;i++)
            prevCol[i] = i;

        for(int i = 0; i < len1; i++)
        {
            col[0] = i + 1;
            for(int j = 0; j < len2; j++)
            {
                col[j+1] = min(1+col[j],1+ prevCol[1+j],prevCol[j] + ((a.charAt(i) == b.charAt(j)) ? 0 : 1 ));

            }
            //col.swap(prevCol);
            for(int j = 0; j < len2; j++)
            {
                prevCol[j] = col[j];
                col[j] = 0;
            }
        }
        return prevCol[len2];

    }

    static int min_ed(String a, String b)
    {
        int len1 = a.length();
        int len2 = b.length();

        int [] col = new int[len2 + 1];
        int [] prevCol = new int[len2 + 1];

        for(int i = 0; i <= prevCol.length;++i)
            prevCol[i] = i;

        for(int i = 1; i <= len1; ++i)
        {
            col[0] = i;
            for(int j = 0; j <= len2; ++j)
            {
                col[j] = min(1+col[j-1],1+ prevCol[j-1],prevCol[j] + ((a.charAt(i) == b.charAt(j)) ? 0 : 1 ));

            }
            //col.swap(prevCol);
            for(int j = 0; j < len2; j++)
            {
                prevCol[j] = col[j];
                col[j] = 0;
            }
        }
        return prevCol[len2];

    }


    public static int editDistance(String a, String b) {
        if (a.length() < b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }

        // b is the shorter string
        int[] prev = new int[b.length() + 1];

        for (int i = 0; i <= b.length(); ++i) {
            prev[i] = i;
        }

        int[] curr = new int[b.length() + 1];

        for (int i = 1; i <= a.length(); ++i) {
            curr[0] = i;

            for (int j = 1; j <= b.length(); ++j) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    curr[j] = prev[j - 1];
                } else {
                    curr[j] = 1 + Math.min(prev[j - 1], Math.min(prev[j], curr[j - 1]));
                }
            }

            for (int j = 0; j <= b.length(); ++j) {
                prev[j] = curr[j];
                curr[j] = 0;
            }
        }

        return prev[b.length()];
    }

    // Dynamisk beräkning av matrisen
    public static int dist(String w1, String w2) {
        int w1len = w1.length();
        int w2len = w2.length();
        int c;
        int r;
        int lastDiagonal;
        int oldDialog;

        int[] column = new int[w1len+1];

        for (c = 1; c <= w1len; c++)
            column[c] = c;

        for (r = 1; r <= w2len; r++){
            column[0] = r;
            lastDiagonal = r-1;
            for (c = 1; c <= w1len; c++) {
                oldDialog = column[c];
                column[c] = min(column[c]+1, column[c-1]+1, lastDiagonal + (w1.charAt(c - 1) == w2.charAt(r - 1) ? 0 : 1));
                lastDiagonal = oldDialog;
            }
        }
        return column[w1len];
    }

    public static int d(String a, String b)
    {
        int m = a.length();
        int n = b.length();
        int m1,m2,m3;

        int t;

        int q = m;
        int [] d = new int [n+1];
        d[q] = 0;
        System.out.println("q = " + q + " d[q].lengtht = " + d.length);
        for(int j = 1; j <= n; j++)
        {
            q = q + 1;
            t = d[q-1] + 1;
            d[q] = t;
        }

        int p = m;
        for(int i = 1; i <= m; i++)
        {
            p = p - 1;
            q = p;
            d[q] = d[q + 1]+ 1;
            for(int j = 1; j <= n; j++){
                q = q + 1;
//                m1 = d[q] + sub(A[i], B[j]) + (a.charAt(i) == b.charAt(j) ? 0 : 1);
                m1 = d[q] + (a.charAt(i) == b.charAt(j) ? 0 : 1);
                m2 = d[q + 1] + 1;
                m3 = d[q - 1] + 1;
                d[q] = Math.min(m1, Math.min(m2, m3));

            }
        }

        return d[n];
    }

    static int edit_dist(String s1, String s2)
    {
        int len1 = s1.length() , len2 = s2.length();
        int [] col = new int [len2 + 1];
        int [] prevCol = new int [len2 + 1];

        //std::iota(prevCol.begin(), prevCol.end(), 0)
        for(int i = 0; i < prevCol.length; i++)
            prevCol[i] = i;
        for (int i = 0; i < len1; ++i)
        {
            col[0] = i + 1;
            for (int j = 0; j < len2; ++j)
                col[j + 1] = Math.min(1 + col[j], Math.min(1 + prevCol[j], prevCol[j] + (s1.charAt(i) == s2.charAt(j) ? 0 : 1)));
            //col.swap(prevCol);
            int [] temp = col;
            col = prevCol;
            prevCol = temp;
        }
        return prevCol[len2];
    }
}
