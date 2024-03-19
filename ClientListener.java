import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ClientListener implements Runnable{
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;
    private GUI chatFrame = null;
    ArrayList<String> usernames = new ArrayList<String>();

    public ClientListener(ObjectInputStream is,
                           ObjectOutputStream os,
                           GUI frame) {
        this.is = is;
        this.os = os;
        this.chatFrame = frame;

    }


    @Override
    public void run() {
        try
        {
            while(true)
            {
                CommandFromServer cfs = (CommandFromServer)is.readObject();

                if(cfs.getCommand() == CommandFromServer.GETUSERS)//updates users
                {
                    usernames = cfs.getUsernames();
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}
