package com.company;

/**
 * Created by ben on 2017-09-25.
 */
public class Array
{
    public int [][] C;
    int N;

    Array(int M)
    {
        this.N = M;
        C = new int[M][M];
        for(int i = 0; i < M; i++)
        {
            C[0][i] = i;
            C[i][0] = i;
        }
    }

    public int [][] get_matrix()
    {
        return C;
    }
}
