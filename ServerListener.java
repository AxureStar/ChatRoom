import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ServerListener implements Runnable{

    private ObjectInputStream is;
    private ObjectOutputStream os;
    private static ArrayList<ObjectOutputStream> outs = new ArrayList<>();
    public static ArrayList<String> usernames = new ArrayList<>();

    public ServerListener(ObjectInputStream is, ObjectOutputStream os) {
        this.is = is;
        this.os = os;
        outs.add(os);
    }

    @Override
    public void run() {
        try{
            while(true){
                CommandFromClient cfc = (CommandFromClient) is.readObject();
                if (cfc.getCommand() == CommandFromClient.ADDUSER){
                    if (usernames.contains(cfc.getData())){
                        os.writeObject(new CommandFromServer(CommandFromServer.ALREADYINUSE, ""));
                    }
                    else{
                        for(String SB: usernames){
                            os.writeObject(new CommandFromServer(CommandFromServer.GETUSERS, SB));
                        }
                        usernames.add(cfc.getData());
                        os.writeObject(new CommandFromServer(CommandFromServer.CONNECTED, cfc.getData()));
                        sendCommand(new CommandFromServer(CommandFromServer.GETUSERS, cfc.getData()));
                    }
                }
                if (cfc.getCommand() == CommandFromClient.REMOVEUSER){
                    usernames.remove(cfc.getData());
                    sendCommand(new CommandFromServer(CommandFromServer.USERLEFT, cfc.getData()));
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void sendCommand(CommandFromServer cfs)
    {
        // Sends command to both players
        for (ObjectOutputStream o : outs) {
            try {
                o.writeObject(cfs);
                o.reset();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
