
import java.util.ArrayList;

/**
 * Created by ben on 2017-11-13.
 */
public class grafen
{
    ArrayList<Integer>[] graf;
    int n;

    grafen(int nodes)
    {
        graf = new ArrayList[nodes];
        for(int j = 0; j < graf.length; j++)
            graf[j] = new ArrayList<>();
        n = 0;
    }
    public void add(ArrayList<Integer> vertex)
    {
        graf[n] = vertex;
        incr();
    }
    public void add(int index, int element)
    {
        graf[index].add(element);
    }

    public void incr()
    {
        this.n++;
    }
    public ArrayList<Integer> get(int x)
    {
        return graf[x];
    }

    public void print()
    {
        for(int j = 0; j < graf.length; j++)
        {
            System.out.println(" graph index = " + j + " -> " + graf[j]);
        }
    }
}
