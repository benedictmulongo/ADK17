

import java.io.IOException;
import java.io.*;
import java.util.*;
/**
 * Created by ben on 2017-10-04.
 */

public class NetworkFlow {
    private static final String NEW_LINE_SEPARATOR = "\n";
    int flowMax;
    static class Edge
    {
        int s, t, rev, cap, f;

        public Edge(int s, int t, int rev, int cap)
        {
            this.s = s;
            this.t = t;
            this.rev = rev;
            this.cap = cap;
        }

        public String toString()
        {
            String r = s + " " + t + " " + f;
            return r;
        }
    }
    List<Edge>[] gph;
    public NetworkFlow(int nodes, int s, int t)
    {
        gph = new List[nodes];
        for (int i = 0; i < nodes; i++)
            gph[i] = new ArrayList<>();
    }
    public void add(int s, int t, int kap)
    {
        gph[s].add(new Edge(s, t, gph[t].size(), kap));
        gph[t].add(new Edge(t, s, gph[s].size() - 1, 0));
    }
/*    public int bottleneck(int s, int t)
    {
        int b= Integer.MAX_VALUE;
        for (int u = t; u != s; u = previous[u].s)
            b = Math.min(b, previous[u].cap - previous[u].f);
        return b;
    }*/

    public int MaxFlowComputation(int s, int t) {
        int flow = 0;
        int[] q = new int[gph.length];
        while (true) {
            int qt = 0;
            q[qt++] = s;
            Edge[] previous= new Edge[gph.length];
            for (int qh = 0; qh < qt && previous[t] == null; qh++) {
                int cur = q[qh];
                for (Edge e : gph[cur]) {
                    if (previous[e.t] == null && e.cap > e.f) {
                        //  e <- e.t
                        previous[e.t] = e;
                        //we have seen it
                        q[qt++] = e.t;
                    }
                }
            }
            if (previous[t] == null)
                break;
            //computation of the bottleneck
            int bottleneck = Integer.MAX_VALUE;
            for (int u = t; u != s; u = previous[u].s)
                bottleneck = Math.min(bottleneck, previous[u].cap - previous[u].f);
            //processing of the path from sink to source
            // in order to change the status according to the bottleneck
            // forward edges f(e) = f(e) + bottleneck
            // backward edges f(e) = f(e) - bottleneck
            for (int u = t; u != s; u = previous[u].s)
            {
                previous[u].f += bottleneck;
                gph[previous[u].t].get(previous[u].rev).f -= bottleneck;
            }
            //Maflow is equal to the min cut sum of bottleneck
            flow += bottleneck;
        }
        this.flowMax = flow;
        return flow;
    }

    public void print(int s, int t, int n)
    {
        int count = 0;
        StringBuilder br = new StringBuilder();
        System.out.println(n);
        System.out.println(s + " " + t + " " + this.flowMax);
        for(int i = 0; i < this.gph.length; i++)
        {
            List<Edge> e = this.gph[i];
            for(int j = 0; j < e.size(); j++ )
            {
                if(e.get(j).f > 0) {
                    count++;
                    br.append(e.get(j) + " " + NEW_LINE_SEPARATOR);
                }
            }

        }
        System.out.println(count);
        System.out.println(br.toString());
    }



}

