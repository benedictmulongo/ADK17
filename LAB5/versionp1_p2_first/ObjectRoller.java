

import java.util.ArrayList;

public class ObjectRoller
{
    ArrayList<Roller> element;

    ObjectRoller()
    {
        element = new ArrayList<Roller>();
    }

    public void add(Roller elem)
    {
        this.element.add(elem);
    }

    public int size()
    {
        return element.size();
    }
    public Roller get(int i)
    {
        return element.get(i);
    }
    public boolean contains(Roller alpha)
    {
        return element.contains(alpha);
    }
    public ArrayList<Roller> getElement() {
        return element;
    }
}
