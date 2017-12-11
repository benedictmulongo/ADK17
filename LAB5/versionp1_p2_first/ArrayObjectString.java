

import java.util.ArrayList;

public class ArrayObjectString
{
    ArrayList<String> element;

    ArrayObjectString()
    {
        element = new ArrayList<>();
    }

    public void add(String elem)
    {
        this.element.add(elem);
    }

    public int size()
    {
        return element.size();
    }
    public String get(int i)
    {
        return element.get(i);
    }


}
