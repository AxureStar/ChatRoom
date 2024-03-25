import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args){
        String user = "";
        try{
            Socket socket = new Socket("127.0.0.1", 8016);
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

            Scanner sc = new Scanner(System.in);
            boolean login = true;
            while(login) {
                System.out.print("Enter Username - ");
                user = sc.nextLine();

                os.writeObject(new CommandFromClient(CommandFromClient.ADDUSER, user));
                CommandFromServer cfs = (CommandFromServer) is.readObject();
                if (cfs.getCommand() == CommandFromServer.CONNECTED){
                    login = false;
                    break;
                }
                System.out.println("Already in Use, Another Username Please");
            }

            ClientListener cl = new ClientListener(is, os, null, user);
            Thread t = new Thread(cl);
            t.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
