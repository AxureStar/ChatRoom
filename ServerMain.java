import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args){
        try{
            ServerSocket ss = new ServerSocket(8010);
            int number = 0;
            while(true){
                number++;
                Socket person = ss.accept();
                ObjectOutputStream os = new ObjectOutputStream(person.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(person.getInputStream());
                CommandFromClient user = (CommandFromClient) is.readObject();
                System.out.println(user.getData() + " joined");
                ServerListener serverListener = new ServerListener(os, is, "");
                Thread a = new Thread(serverListener);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
