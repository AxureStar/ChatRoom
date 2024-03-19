import java.lang.reflect.Array;
import java.util.ArrayList;

public class NameData {

    public ArrayList<String> names = new ArrayList<>();

    public void addName(String user)
    {
        names.add(user);
    }

    public ArrayList<String> getNames()
    {
        return names;
    }

    public void removeName(String name)
    {
        names.remove(name);
    }

}
