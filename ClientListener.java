import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ClientListener implements Runnable{
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;
    private Shizer chatFrame = null;
    ArrayList<String> usernames = new ArrayList<String>();
    String username;

    public ClientListener(ObjectInputStream is,
                           ObjectOutputStream os,
                           Shizer frame, String user) {
        this.is = is;
        this.os = os;
        this.chatFrame = frame;
        username = user;

    }


    @Override
    public void run() {
        Shizer gui = new Shizer(this);
        try
        {
            while(true)
            {
                CommandFromServer cfs = (CommandFromServer)is.readObject();
                System.out.println("Test 1");
                if(cfs.getCommand() == CommandFromServer.GETUSERS)//updates users
                {
                    System.out.println("Test 12");
                    usernames.add(cfs.getData());
                    gui.updateNames(cfs.getData());
                }
                if (cfs.getCommand() == CommandFromServer.USERLEFT){
                    System.out.println("Test 2");
                    gui.updateNames(cfs.getData());
                    usernames.remove(cfs.getData());
                    System.out.println(usernames.size());
                }if(cfs.getCommand() == CommandFromServer.NEWMESSAGE){
                    gui.updateMessage(cfs.getData());
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(usernames.size());
            e.printStackTrace();
        }
    }

    public ArrayList<String> getUsernames(){
        return usernames;
    }
    public void sendMessage(String string){
        try {
            os.writeObject(new CommandFromClient(CommandFromClient.SENDMESSAGE, username + ": " + string));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try{
            os.writeObject(new CommandFromClient(CommandFromClient.REMOVEUSER, username));
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
