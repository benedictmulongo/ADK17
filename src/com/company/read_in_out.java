import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by ben on 2017-09-08.
 */
public class read_in_out
{
    public static void main(String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("/var/tmp/templista"));
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

        String file_name = "temp.txt";
        FileWriter file_w = new FileWriter(file_name);
        BufferedWriter buffer_w = new BufferedWriter(file_w);
        FileReader fi = new FileReader(file_name);
        BufferedReader b_r = new BufferedReader(fi);

        int size;
        ArrayList<Integer> intro;

        for(HashMap.Entry<String, ArrayList<Integer>> enter : mapning.entrySet())
        {
            // write the first string
            buffer_w.write(enter.getKey());
            intro = enter.getValue();
            //
            size = enter.getValue().size();
            //sort here
            Collections.sort(intro);
            //intro.sort(Comparator.comparing(Integer::parseInt));
            for(int i = 0; i < size; i++) {
                Collections.sort(intro);
                buffer_w.write(" " + intro.get(i).toString());
            }
            buffer_w.newLine();
        }

        buffer_w.close();

    }

    static String [] splitter(String lis)
    {
        String [] tra = lis.split(" ");
        return tra;
    }

}
