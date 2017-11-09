
/**
 * Created by ben on 2017-11-08.
 */
import java.awt.*;
import java.util.*;
import java.util.List;

public class graph
{
    static class Edge
    {
        int sourceIn;
        int nextTo;
        Color source;
        String farg;

        public Edge(int s,int t)
        {
            this.sourceIn = s;
            this.nextTo = t;
            source = new Color(255,10,10);
            farg = "none";
        }

        public String toString()
        {
            String r = sourceIn + " -> " + nextTo ;
            return r;
        }
    }
    List<Edge>[] gph;
    List<Edge>[] bra;
    private boolean[] marked;
    private boolean hasCycle;
    int [] parent;
    private static final String SEPARATOR = "\n";
    final int V;  // number of vertices
    int E;  // number of edges
    int [] hashing = new int [25000];
    int antal_scener;
    StringBuilder br = new StringBuilder();
    StringBuilder br_final = new StringBuilder();
    public graph(int nodes)
    {
        this.V = nodes;
        this.E = 0;
        gph = new List[nodes];
        parent = new int [nodes];
     //   hasCycle = false;
        for (int i = 0; i < nodes; i++)
            gph[i] = new ArrayList<>();
        for(int i = 0; i < hashing.length; i++)
            hashing[i] = -5;
        antal_scener = 0;
    }
    public void add(int source, int next)
    {
        if(!gph[source].contains((Edge) new Edge(source, next)))
        {
            gph[source].add(new Edge(source, next));
            gph[next].add(new Edge(next, source));
            E++;
        }
    }

    public void print()
    {
        for(int i = 0; i < this.V; i++)
        {
            System.out.println(gph[i]);
        }
    }

    public void cycle(graph G, int k)
    {
        marked = new boolean[G.V];
        for (int s = k; s < G.V; s++)
            if (!marked[s])
                dfs(G, s, s);
    }
    private void dfs(graph G, int v, int u)
    {
        marked[v] = true;
        for (Edge per : G.gph[v])
        {
            int w = per.nextTo;
            if (!marked[w])
            {
                dfs(G, w, v);
                this.parent[w] = v;
            }
            else if (w != u)
            {
                this.hasCycle = true;
                break;
            }
        }
    }
    public boolean hasCycle()
    { return hasCycle; }

    public void bfs(graph G)
    {
        for(int j = 0; j < this.V; j++)
            bfs(G,j);
    }
    public void bfs(graph G, int s)
    {
        int [] prev = new int[this.V];

        boolean [] visited = new boolean[this.V];
        for(int i = 0; i < visited.length; i++)
            visited[i] = false;

        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[s] = true;  // Mark the source
        queue.add(s);  // and put it on the queue.
        while (queue.size() > 0)
        {
            int v = queue.poll(); // Remove next vertex from the queue.
            for (Edge per : G.gph[v])
            {
                int w = per.nextTo;
                if (!visited[w])  // For every unmarked adjacent vertex,
                {
                    prev[w] = v;  // save last edge on a shortest path,
                    visited[w] = true; // mark it because path is known,
                    queue.add(w); // and add it to the queue.
                    if(hashing[Integer.valueOf(v+""+w)] == -5)
                    {
                        hashing[Integer.valueOf(v+""+w)] = 100;
                        //System.out.println(v + " -> " + w);
                        br.append("2 " + v + " " + w + SEPARATOR);
                        antal_scener++;

                    }

                }
            }
        }
    }

}