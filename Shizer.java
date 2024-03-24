import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Shizer extends JFrame {

   static ArrayList<String> usernames = new ArrayList<>();
   static TextArea connections = new TextArea();

    public Shizer(){
        this.setLayout(null);
        this.setSize(1050,650);
        this.setTitle("ChatRoom");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        ScrollPane textBox = new ScrollPane();
        TextArea messages = new TextArea();
        textBox.setBounds(20, 20, 700, 500);
        textBox.add(messages);

        messages.setEditable(false);
        messages.setFocusable(false);

        this.add(textBox);
        ScrollPane connectedList = new ScrollPane();
        connectedList.setBounds(760, 20, 250, 500);
        connectedList.add(connections);

        connections.setEditable(false);
        connections.setFocusable(false);

        this.add(connectedList);

        TextField messageBar = new TextField();
        messageBar.setBounds(20, 550, 600, 30);
        messageBar.setFont(new Font("New Times Roman", Font.PLAIN, 20));
        messageBar.setVisible(true);
        this.add(messageBar);

        JButton sendMessage = new JButton();
        sendMessage.setBounds(640, 550, 80, 30);
        sendMessage.setFocusable(false);
        sendMessage.setText("Send");
        this.add(sendMessage);

        sendMessage.addActionListener(e -> {
            if (!messageBar.getText().isEmpty()){
                //we need to pass in the message once enter is pressed to the server listener as a command
            }
        });



        this.setVisible(true);
    }

    public void paint(Graphics g) {
        g.setColor(new Color(73, 103, 101));
        g.fillRect(0, 0, 1200, 800);
        g.setColor(new Color(59, 71, 79));
        g.fillRect(30, 43, 718, 517);
        g.fillRect(770, 43, 268, 517);
    }

    public static void updateNames(String name){
        System.out.println("asd");
        boolean removed = false;
        for(String n: usernames)
        {
            if(n.equals(name))
            {
                usernames.remove(n);
                removed = true;
            }
        }
        if(!removed)
        {
            usernames.add(name);
        }
        String names = "";
        for(String n: usernames)
        {
            names += n+"\n";
        }
        connections.setText(names);
    }
}
