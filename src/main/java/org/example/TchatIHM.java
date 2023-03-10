//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
//Home Page http://guigenie.cjb.net - Check often for new versions!

import org.example.TchatAPIRequestUtils;
import org.example.beans.MessageBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TchatIHM extends JPanel {
    private JButton jbSend;
    private JButton jbReload;
    private JTextArea jcomp3;
    private JTextArea jcomp4;

    public TchatIHM() {
        //construct components
        jbSend = new JButton("Envoyer");
        jbReload = new JButton("Refresh");
        jcomp3 = new JTextArea(5, 5);
        jcomp4 = new JTextArea(5, 5);

        //adjust size and set layout
        setPreferredSize(new Dimension(614, 571));
        setLayout(null);

        //add components
        add(jbSend);
        add(jbReload);
        add(jcomp3);
        add(jcomp4);

        //set component bounds (only needed by Absolute Positioning)
        jbSend.setBounds(485, 510, 100, 20);
        jbReload.setBounds(485, 465, 100, 20);
        jcomp3.setBounds(30, 65, 555, 390);
        jcomp4.setBounds(25, 465, 445, 75);

        jbSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TchatAPIRequestUtils.sendMessage(new MessageBean("MonIhm", jcomp4.getText()));
                    jcomp4.setText("");

                    jbReload.doClick();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    jcomp3.setText("Une erreur est survenue : " + ex.getMessage());
                }
            }
        });

        jbReload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<MessageBean> list =  TchatAPIRequestUtils.getAllMessage();
                     String text = "";

                    for (MessageBean messageBean : list) {
                        text += messageBean.getPseudo() + " : " + messageBean.getMessage() + "\n";
                    }
                    jcomp3.setText(text);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    jcomp3.setText("Une erreur est survenue : " + ex.getMessage());
                }
            }
        });

        jbReload.doClick();
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("MyPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new TchatIHM());
        frame.pack();
        frame.setVisible(true);
    }
}
