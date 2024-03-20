import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;


public class GUI extends JFrame {
    ClientPanel clientPanel = new ClientPanel();
    UsernamePanel usernamePanel = new UsernamePanel();
    ArrayList<String> localUNList = new ArrayList<>();
    Boolean entered = false;
    public GUI(){
        this.setLayout(null);
        this.setSize(1050,650);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.add(usernamePanel);
        this.setVisible(true);
    }

    public void switchPanels(){
        if (!entered){
            this.remove(usernamePanel);
            this.add(clientPanel);
        }
        else{
            this.remove(clientPanel);
            this.add(usernamePanel);
        }
    }

    public boolean isNameAvailable(String name){
        for(String name1: localUNList)
        {
            if(name.equals(name1))
            {
                return false;
            }
        }
        return true;
    }

    public void UpdateUsernames(ArrayList<String> list)
    {
        localUNList = list;
    }
}
