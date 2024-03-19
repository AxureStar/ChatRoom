import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ServerListener implements Runnable{

    private ObjectInputStream is;
    private ObjectOutputStream os;
    private String user;
    private static NameData nameData = new NameData();
    private static ArrayList<ObjectOutputStream> outs = new ArrayList<>();
    private static ArrayList<String> usernames = new ArrayList<>();

    public ServerListener(ObjectInputStream is, ObjectOutputStream os, String user, ) {
        this.is = is;
        this.os = os;
        this.user = user;
        outs.add(os);
    }

    @Override
    public void run() {
        try{
            CommandFromClient cfc = (CommandFromClient) is.readObject();
            if(cfc.getCommand() == CommandFromClient.ADDUSER)
            {
                usernames.add(cfc.getData());
                os.writeObject(new CommandFromServer(CommandFromServer.GETUSERS, usernames));
            }else if(cfc.getCommand() == CommandFromClient.REMOVEUSER){
                usernames.remove(cfc.getData());
                os.writeObject(new CommandFromServer(CommandFromServer.GETUSERS, usernames));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
