import java.io.Serializable;
import java.util.ArrayList;

public class CommandFromServer implements Serializable {
    // The command being sent to the client
    private int command;
    // Text data for the command
    private String data ="";
    private ArrayList<String> usernames = new ArrayList<>();
    public static final int GETUSERS = 0;
    public static final int NEWMESSAGE = 1;


    public CommandFromServer(int command, String data, ArrayList<String> arrayList) {
        this.command = command;
        this.data = data;
        this.usernames = arrayList;
    }

    public int getCommand() {
        return command;
    }

    public String getData() {
        return data;
    }
    public ArrayList<String> getUsernames() {
        return usernames;
    }

}
