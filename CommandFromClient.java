import java.io.Serializable;

public class CommandFromClient implements Serializable
{
    // The command being sent to the server
    private int command;
    // Text data for the command
    private String data ="";
    // Command list
    public static final int ADDUSER = 0;//only server can add user clients only update
    public static final int REMOVEUSER = 1;
    public static final int SENDMESSAGE = 2;


    public CommandFromClient(int command, String data) {
        this.command = command;
        this.data = data;
    }

    public int getCommand() {
        return command;
    }

    public String getData() {
        return data;
    }

}
