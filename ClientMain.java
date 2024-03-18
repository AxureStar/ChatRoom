import java.net.Socket;

public class ClientMain {
    public ClientMain(){
        try{
            Socket socket = new Socket("127.0.0.1", 8010);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
