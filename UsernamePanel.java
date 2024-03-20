import javax.swing.*;
import java.awt.*;

public class UsernamePanel extends JPanel {
    public UsernamePanel(){
        this.setLayout(null);
        this.setSize(200,200);

        JTextField username = new JTextField();
        username.setBounds(445,200, 230,30);
        this.add(username);

        JButton jButton = new JButton();
        jButton.setBounds(410, 260, 200, 40);
        this.add(jButton);


        this.setVisible(true);
    }
    public void paint(Graphics g) {
        g.setColor(new Color(73, 103, 101));
        g.fillRect(0, 0, 1200, 800);
        g.setColor(new Color(59, 71, 79));
        g.fillRect(350,170, 350, 150);
        g.setColor(new Color(0, 0, 0));
        g.drawString("Username: ", 365, 220);
        g.setColor(new Color(162, 57, 57));
        g.fillRect(400,250, 250, 40);
    }
}
