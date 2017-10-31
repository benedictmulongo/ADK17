package com.company;

/**
 * Created by ben on 2017-09-26.
 */
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.*;
import java.awt.*;
import java.io.*;

public class digitIm
{
    public static void main(String [] args) throws IOException {

        int r1;
        Color red1  = new Color(210,210,45);
        Color b1 = new Color(120,40,120);
        Color g1 = new Color(128,20,2);
        Color tmp1;
        Color [] tr1 = new Color [3];
        tr1[0] = red1;
        tr1[1] = b1;
        tr1[2] = g1;


        int r;
        Color red  = new Color(255,0,0);
        Color b = new Color(0,0,255);
        Color g = new Color(0,225,0);
        Color tmp;
        Color [] tr = new Color [3];
        tr[0] = red;
        tr[1] = b;
        tr[2] = g;

        BufferedImage great = new BufferedImage(500,300,BufferedImage.TYPE_INT_ARGB);
        for(int i = 0; i < great.getWidth(); i++ )
        {
            for(int j = 0; j < great.getHeight(); j++)
            {
                r = (int)(2*Math.random());

                tmp = tr[r];
                tmp1 = tr1[r];
                //System.out.println(tmp);
                if(circle(i,j)) {
                    great.setRGB(i, j, tmp.getRGB());
                }
                else
                    great.setRGB(i,j,tmp1.getRGB());
            }
        }

        ImageIO.write (great , "GIF" , new File("rgbtest.gif" ) ) ;
    }

    static boolean circle(int x, int y)
    {

        if( (x-250)*(x-250) + (y-150)*(y-150) <= 3200)
            return true;

        return false;
    }
}
