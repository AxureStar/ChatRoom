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
    public static final int ALREADYINUSE = 2;
    public static final int USERLEFT = 3;
    public static final int CONNECTED = 4;


    public CommandFromServer(int command, ArrayList<String> arrayList) {
        this.command = command;
        this.usernames = arrayList;
    }
    public CommandFromServer(int command, String data) {
        this.command = command;
        this.data = data;
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
