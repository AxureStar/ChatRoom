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

    public ClientListener(ObjectInputStream is,
                           ObjectOutputStream os,
                           Shizer frame) {
        this.is = is;
        this.os = os;
        this.chatFrame = frame;

    }


    @Override
    public void run() {
        Shizer gui = new Shizer();
        try
        {
            while(true)
            {
                CommandFromServer cfs = (CommandFromServer)is.readObject();

                if(cfs.getCommand() == CommandFromServer.GETUSERS)//updates users
                {
                    usernames.add(cfs.getData());
//
                }
                if (cfs.getCommand() == CommandFromServer.USERLEFT){
                    usernames.remove(cfs.getData());
                    System.out.println(usernames.size());
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(usernames.size());
            e.printStackTrace();
        }
    }

}
