import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ServerListener implements Runnable{

    private ObjectInputStream is;
    private ObjectOutputStream os;
    private static ArrayList<ObjectOutputStream> outs = new ArrayList<>();
//    public static ArrayList<String> usernames = new ArrayList<>();
    String messages = "";

    public ServerListener(ObjectInputStream is, ObjectOutputStream os) {
        this.is = is;
        this.os = os;
        outs.add(os);
    }

    @Override
    public void run() {
        try{
            ArrayList<String> usernames = new ArrayList<>();
            while(true){
                CommandFromClient cfc = (CommandFromClient) is.readObject();
                if (cfc.getCommand() == CommandFromClient.ADDUSER){
                    if (usernames.contains(cfc.getData())){
                        os.writeObject(new CommandFromServer(CommandFromServer.ALREADYINUSE, ""));
                    }
                    else{
                        for (int i = 0; i < usernames.size(); i++){
                            os.writeObject(new CommandFromServer(CommandFromServer.GETUSERS, (usernames.get(i))));
                        }
                        usernames.add(cfc.getData());
                        os.writeObject(new CommandFromServer(CommandFromServer.CONNECTED, cfc.getData()));
                        sendCommand(new CommandFromServer(CommandFromServer.GETUSERS, cfc.getData()));
                    }
                }
                else if (cfc.getCommand() == CommandFromClient.REMOVEUSER){
                    usernames.remove(cfc.getData());
                    sendCommand(new CommandFromServer(CommandFromServer.USERLEFT, cfc.getData()));
                }
                else if (cfc.getCommand() == CommandFromClient.SENDMESSAGE){
                    messages += cfc.getData() + "\n";
                    //add which user sent it, then broadcast it to all
                    sendCommand(new CommandFromServer(CommandFromServer.NEWMESSAGE, cfc.getData()));
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
