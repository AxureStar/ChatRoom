import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.*;
import java.awt.*;


public class GUI extends JFrame {
    public GUI(){
        this.setLayout(null);
        this.setSize(1050,650);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        ClientPanel panel = new ClientPanel();
        this.add(panel);

        this.setVisible(true);
    }
}
