package com.company;

import java.util.ArrayList;

public class cell
{
    int posRow;
    int posCol;
    int piecesId;
    String id_str;
    int dist;
    boolean visited;
    //ArrayList<Integer> [] vinicity;

    cell(int row, int col)
    {
        posRow = row;
        posCol = col;
        dist = 100000;
        visited = false;
      //  vinicity = new ArrayList[4];
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public int getPosCol() {
        return posCol;
    }

    public int getPosRow() {
        return posRow;
    }

    public int getPiecesId() {
        return piecesId;
    }
    public void setPiecesId(int id)
    {
        piecesId = id;
        id_str = ""  + id;
    }

    public cell getUp(int dim)
    {
        //int [] up = {getPosRow() - 1, getPosCol()};
        if( ( getPosRow() - 1 < 0))
            return null;
        else
            return new cell(getPosRow() - 1, getPosCol());
    }
    public cell getDown(int dim)
    {
       // int [] up = {getPosRow() + 1, getPosCol()};
        if((getPosRow() + 1 >= dim))
        {
            //System.out.println("Down ==> dim = " + dim  + " col " + getPosCol() + " row " + getPosRow());
            return null;
        }
        else
            return new cell(getPosRow() + 1, getPosCol());
    }
    public cell getRight(int dim)
    {
      //  int [] up = {getPosRow(), getPosCol() + 1};
        if((getPosCol() + 1 >= dim))
            return null;
        else
            return new cell(getPosRow(), getPosCol() + 1) ;
    }
    public cell getLeft(int dim)
    {
      //  int [] up = {getPosRow(), getPosCol() - 1};
        if((getPosCol() - 1 < 0))
            return null;
        else
            return new cell(getPosRow(),getPosCol() - 1);
    }
    public String toString()
    {
        return " [ row " + posRow + " col " + posCol + " id " + piecesId + " dist " + dist  + " ]";
    }

}
