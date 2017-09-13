/**
 * Created by ben on 2017-09-09.
 */
public class Index_vector
{
    String key;           // sorted by key
    int begin;
    int end;
    long line_nr;

    public Index_vector(String key, int a, int b, long n) {
        this.key = key;
        this.begin = a;
        this.end = b;
        this.line_nr = n;
    }

    public String toString()
    {
        return "[" + key + ", "  + begin + ", " + end + ", " + line_nr +  " ]";
    }
}
