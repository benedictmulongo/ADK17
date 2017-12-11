

import java.util.ArrayList;

/**
 * Created by ben on 2017-11-29.
 */
public class Roller
{
    int id;
    String color;
    //ArrayList<>
    Roller(int identity)
    {
        this.id = identity;
        this.color = "";
    }
    public String getColor() {
        return color;
    }
    public void setIdentity(int identity)
    {
        this.id = identity;
    }

    public void putColor(String couleur)
    {
        this.color = couleur;
    }

    public String toString()
    {
        return "{Roll = " + this. id + " " + "color = " + color + " }";
    }
}
