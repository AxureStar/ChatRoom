import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.*;
import java.awt.*;


public class GUI extends JFrame {
    ClientPanel clientPanel = new ClientPanel();
    UsernamePanel usernamePanel = new UsernamePanel();
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
}
