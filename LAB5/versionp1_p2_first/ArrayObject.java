

import java.util.ArrayList;

public class ArrayObject
{
    ArrayList<Integer> element;

    ArrayObject()
    {
        element = new ArrayList<>();
    }

    public void add(int elem)
    {
        this.element.add(elem);
    }

    public int size()
    {
        return element.size();
    }
    public int get(int i)
    {
        return element.get(i);
    }

    public ArrayList<Integer> getElement() {
        return element;
    }
}
