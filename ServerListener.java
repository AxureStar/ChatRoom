import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ServerListener implements Runnable{

    private ObjectInputStream is;
    private ObjectOutputStream os;
    private static NameData nameData = new NameData();
    private static ArrayList<ObjectOutputStream> outs = new ArrayList<>();
    public static ArrayList<String> usernames = new ArrayList<>();

    public ServerListener(ObjectInputStream is, ObjectOutputStream os, String user) {
        this.is = is;
        this.os = os;
        outs.add(os);
        usernames.add(user);
    }

    @Override
    public void run() {
        try{
            System.out.println(usernames.size());
            while(true){
                sendCommand(new CommandFromServer(CommandFromServer.GETUSERS, usernames));
                is.readObject();
            }
//            CommandFromClient cfc = (CommandFromClient) is.readObject();
//            if(cfc.getCommand() == CommandFromClient.ADDUSER)
//            {
//                boolean alreadyHas = false;
//                for(String name: usernames)
//                {
//                    if(name.equals(cfc.getData()))
//                    {
//                        alreadyHas = true;
//                    }
//                }
//                if(!alreadyHas)
//                {
//                    usernames.add(cfc.getData());
//                    System.out.println(cfc.getData() + " just joined so say hello mates.");
//                    sendCommand(new CommandFromServer(CommandFromServer.GETUSERS, usernames));
//                }
//            }else if(cfc.getCommand() == CommandFromClient.REMOVEUSER){
//                usernames.remove(cfc.getData());
//                os.writeObject(new CommandFromServer(CommandFromServer.GETUSERS, usernames));
//            }
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
