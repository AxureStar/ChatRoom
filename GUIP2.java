import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GUIP2 extends JFrame{
    ObjectOutputStream os;
    ObjectInputStream is;
    String user = "";
    public GUIP2(ObjectOutputStream os, ObjectInputStream is){
        try {
            this.os = os;
            this.is = is;
            this.setSize(720, 450);
            this.setLayout(null);
            this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            this.setAlwaysOnTop(true);

            JLabel label = new JLabel("Enter Username");
            label.setBounds(245, 150, 230, 20);
            this.add(label);

            JTextField username = new JTextField();
            username.setBounds(245, 170, 230, 30);
            this.add(username);

            JButton jButton = new JButton();
            jButton.setBounds(245, 210, 230, 25);
            jButton.setText("Enter");
            jButton.setFocusable(false);
            this.add(jButton);

            jButton.addActionListener(e -> {
                if (!username.getText().isEmpty()){
                    user = username.getText();
                    try {
                        os.writeObject(user);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });


            this.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
