import javax.swing.*;
import java.awt.*;

public class ClientPanel extends JPanel {
    public ClientPanel(){
        this.setLayout(null);
        this.setSize(1050,650);


        ScrollPane textBox = new ScrollPane();
        TextArea messages = new TextArea();
        textBox.setBounds(20, 20, 700, 500);
        textBox.add(messages);

        messages.setEditable(false);
        messages.setFocusable(false);

        this.add(textBox);

        ScrollPane connectedList = new ScrollPane();
        TextArea connections = new TextArea();
        connectedList.setBounds(760, 20, 250, 500);
        connectedList.add(connections);

        connections.setEditable(false);
        connections.setFocusable(false);

        this.add(connectedList);

        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(73, 103, 101));
        g.fillRect(0, 0, 1200, 800);
        g.setColor(new Color(59, 71, 79));
        g.fillRect(20, 43, 718, 517);
        g.fillRect(760, 43, 268, 517);
    }
}
