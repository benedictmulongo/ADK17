

/**
 * Created by ben on 2017-11-02.
 */
import  java.io.*;
import java.util.*;
public class Main1 {
    public static void main(String[] args) throws IOException
    {

       // File text = new File("C:/Users/ben/Desktop/LABB33/data.txt");
        Scanner sc = new Scanner(System.in);
        int antal_horn = sc.nextInt();
        int s = sc.nextInt();
        int t = sc.nextInt();
        int antal_kanter = sc.nextInt();
        NetworkFlow net = new NetworkFlow(antal_horn + 1,s,t);
        int i = 0;
        while(sc.hasNext() && i < antal_kanter)
        {
            int start = sc.nextInt();
            int slut = sc.nextInt();
            int kap = sc.nextInt();
            net.add(start, slut, kap);

            i++;
        }

        int max = net.MaxFlowComputation(s,t);
        net.print(s,t,antal_horn);
    }
}
