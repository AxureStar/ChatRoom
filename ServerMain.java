import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args){
        try{
            ServerSocket ss = new ServerSocket(8015);
            while(true){
                Socket person = ss.accept();
                ObjectOutputStream os = new ObjectOutputStream(person.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(person.getInputStream());
                ServerListener serverListener = new ServerListener(is, os);
                Thread a = new Thread(serverListener);
                a.start();
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
