package com.company;

/**
 * Created by ben on 2017-10-03.
 */
public class EDGES
{
    int begin, end,flow,capacity;
    EDGES(int A,int B,int CAP)
    {
        this.begin = A;
        this.end = B;
        this.capacity = CAP;
        this.flow = 0;
    }

    EDGES(int A,int B,int fl,int CAP)
    {
        this.begin = A;
        this.end = B;
        this.capacity = CAP;
        this.flow = fl;
    }
    boolean from(int v) {return begin == v;}
    public int getBegin() {return begin;}
    public int getEnd() {return end;}
    int other(int v)
    {
        return from(v) ? begin :end;
    }
//if e = (A,B)  residualCapTo(B) is the residual capacity
    // "I" can augment the edge with from A to B that is
    //capacity - flow
    int residualCapTo(int v)
    {
        if( v == this.begin)
            return flow;
        else
            return capacity - flow;
    }
    void addResidualCapTo(int v, int augment)
    {
        if( v == this.begin)
            flow += augment;
        else
            flow -= augment;
    }

/*    boolean from (int v)
    { return pv == v; }
    int other(int v)
    { return from(v) ? pw : pv; }
    int capRto(int v)
    { return from(v) ? pflow : pcap - pflow; }
    void addflowRto(int v, int d)
    { pflow += from(v) ? -d : d; }*/


    public String toString()
    {
        return "[" + "Edge = { " + this.begin + ", " + this.end + " }, " + this.flow + " / " + this.capacity + "]";
    }
}
