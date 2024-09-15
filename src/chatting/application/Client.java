package chatting.application;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;



public class Client  implements ActionListener {

    static JFrame f = new JFrame();

    JTextField textarea1;
    static JPanel area1;
    static Box vertical = Box.createVerticalBox();

    static DataOutputStream dout;

    Client(){

        f.setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(161, 22, 196));
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);
        f.add(p1);

        //Adding back button and exit action with it
        ImageIcon arrow = new ImageIcon
                (Objects.requireNonNull(getClass().getResource("Icons\\bkarrow.png")));
        JLabel back = new JLabel(arrow);
        back.setBounds(10, 20, 25, 25);
        p1.add(back);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
//        Adding server profile pic
        ImageIcon profile1 = new ImageIcon
                (Objects.requireNonNull(getClass().getResource("Icons\\profile2.png")));
        JLabel ServerProfile = new JLabel(profile1);
        ServerProfile.setBounds(40, 10, 50, 50);
        p1.add(ServerProfile);

        //        Adding video call icon
        ImageIcon videocall = new ImageIcon
                (Objects.requireNonNull(getClass().getResource("Icons\\videocall.png")));
        JLabel ServerVdo = new JLabel(videocall);
        ServerVdo.setBounds(290, 20, 30, 30);
        p1.add(ServerVdo);

//        //        Adding normal call icon
        ImageIcon normallcall = new ImageIcon
                (Objects.requireNonNull(getClass().getResource("Icons\\nrmlcall.png")));
        JLabel ServerCall = new JLabel(normallcall);
        ServerCall.setBounds(360, 20, 30, 30);
        p1.add(ServerCall);

//        //        Adding more options 3 dots
        ImageIcon threedot = new ImageIcon
                (Objects.requireNonNull(getClass().getResource("Icons\\threedot.png")));
        JLabel threedots = new JLabel(threedot);
        threedots.setBounds(420, 20, 20, 30);
        p1.add(threedots);

        //Adding Name and status
        JLabel User1 = new JLabel("Client User");
        User1.setBounds(110,15,150,18);
        User1.setForeground(Color.WHITE);
        User1.setFont(new Font("SANS_SERIF", Font.BOLD, 18));
        p1.add(User1);
        JLabel status = new JLabel("Active Now");
        status.setBounds(110,35,150,18);
        status.setForeground(Color.WHITE);
        p1.add(status);

        //Text area panel
        area1 = new JPanel();
        area1.setLayout(new BoxLayout(area1, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(area1);
        scrollPane.setBounds(5, 75, 440, 575);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        f.add(scrollPane);

        //Text writing area
        textarea1 = new JTextField();
        textarea1.setBounds(5,655, 310,40);
        textarea1.setFont(new Font("SANS_SERIF", Font.PLAIN, 16));
        f.add(textarea1);

        //Adding  button
        JButton send = new JButton("Send");
        send.setBounds(320,655,123,40);
        send.setBackground(new Color(161, 22, 196));
        send.setForeground(Color.WHITE);
        send.setFont(new Font("SANS_SERIF", Font.BOLD, 16));
        send.addActionListener(this);
        f.add(send);



        f.setSize(450, 700);
        f.setLocation(700, 15);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String txtOut = textarea1.getText();
        JPanel p2 = formatLabel(txtOut);
        area1.setLayout(new BorderLayout());

        JPanel sendTxt = new JPanel(new BorderLayout());
        sendTxt.add(p2, BorderLayout.LINE_END);
        vertical.add(sendTxt);
        vertical.add(Box.createVerticalStrut(12));
        area1.add(vertical, BorderLayout.PAGE_START);

        try {
            dout.writeUTF(txtOut);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        textarea1.setText("");

        f.repaint();
        f.invalidate();
        f.validate();
    }

    public static JPanel formatLabel(String txtOut){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextArea output = new JTextArea(txtOut);
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(240, 200, 250));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(10, 10, 10, 50));
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        output.setEditable(false);
        output.setFocusable(false);
        output.setSize(150, Short.MAX_VALUE);
        output.setPreferredSize(output.getPreferredSize());
        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH : mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);

        return panel;
    }


    public static void main(String[] args) {
        new Client();

        try{
            Socket s = new Socket("127.0.0.1", 4001);
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            while(true){
                area1.setLayout(new BorderLayout());

                String msg = din.readUTF();
                JPanel panel = formatLabel(msg);

                JPanel receiveTxt = new JPanel(new BorderLayout());
                receiveTxt.add(panel, BorderLayout.LINE_START);
                vertical.add((receiveTxt));
                vertical.add(Box.createVerticalStrut(12));
                area1.add(vertical, BorderLayout.PAGE_START);
                f.validate();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
