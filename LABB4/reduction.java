

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by ben on 2017-11-09.
 */
public class reduction
{
    /*    Graffärgning
    Indata: En oriktad graf och ett antal färger m. Isolerade hörn och dubbelkanter kan förekomma, inte öglor.

        Fråga: Kan hörnen i grafen färgas med högst m färger så att inga grannar har samma färg?

    Indataformat:
    Rad ett: tal V (antal hörn, tex:\displaystyle 1 \leq V \leq 300)
    Rad två: tal E (antal kanter, tex:\displaystyle 0\le E\le 25000)
    Rad tre: mål m (max antal färger, tex:\displaystyle 1\le m\le 2^{30})
    En rad för varje kant (E stycken) med kantens ändpunkter (hörnen numreras från 1 till V)*/
    public static void main(String[] args) throws IOException
    {

        String NEW_LINE_SEPARATOR = "\n";
      //  System.out.println("Read data from file !");
        File text = new File("data.txt");
        Scanner sc = new Scanner(text);
        int antal_horn = Integer.valueOf(sc.nextLine());
        int antal_kanter = Integer.valueOf(sc.nextLine());
        int antal_farg = Integer.valueOf(sc.nextLine());
        int i = 0;
        graph graphReduc = new graph(antal_horn + 1);

        while(sc.hasNext() && i < antal_kanter)
        {
            //System.out.println("index -> " + i);
            String line = sc.nextLine();
            String [] values = splitter(line);
            graphReduc.add(Integer.valueOf(values[0]),Integer.valueOf(values[1]));
            i++;
        }
/*        graphReduc.print();
        System.out.println("************************************************");
        graphReduc.cycle(graphReduc,4);
        System.out.println(graphReduc.hasCycle());
        for(int j = 0; j < graphReduc.parent.length; j++)
        {
            System.out.println("index = " + j + " " + graphReduc.parent[j]);
        }
        System.out.println("**************1**************");
        graphReduc.bfs(graphReduc);
        System.out.println("**************2**************");*/
        graphReduc.bfs(graphReduc);
        System.out.println(antal_horn);
        System.out.println(graphReduc.antal_scener);
        //case antal_farg = 0 -> antalfarg + 1
        System.out.println(antal_farg);

        StringBuilder tra = new StringBuilder();
        StringBuilder skadis = new StringBuilder();
        for(int j = 1; j < antal_farg + 1;j++)
        {
            skadis.append(j + " ");
        }
        for(int j = 0; j < antal_horn -1;j++)
        {
            tra.append(antal_farg+ " "+ skadis.toString() + NEW_LINE_SEPARATOR);
        }
        tra.append(antal_farg+ " "+ skadis.toString());
        System.out.println(tra.toString());
        System.out.println(graphReduc.br.toString());

    }
    static String [] splitter(String lis)
    {
        String [] tra = lis.split(" ");
        return tra;
    }
}
