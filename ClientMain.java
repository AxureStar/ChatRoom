import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args){
        try{
            Socket socket = new Socket("127.0.0.1", 8016);
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

            GUI gui = new GUI();


            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Username - ");
            String user = sc.nextLine();

            os.writeObject(new CommandFromClient(CommandFromClient.ADDUSER, user));
            ClientListener cl = new ClientListener(is, os, gui);
            Thread t = new Thread(cl);
            t.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
