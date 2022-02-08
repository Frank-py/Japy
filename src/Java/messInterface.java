package Java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class messInterface {

    public static Color color = new Color(0x123456);
    public static ImageIcon pic = new ImageIcon("src\\Java\\prof.png");
    public static ImageIcon plus = new ImageIcon("src\\Java\\plus.png");
    public static JButton addUsers;
    public static JFrame frame;
    public static JPanel Users;
    public static boolean createUser = false;
    public static ActionListener act = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addUsers) {
                createUser = true;
            }
        }
    };

    // main for testing not necessary
    public static void main(String[] args) {
        createGUI(0);
        run();
    }

    public static void createGUI(int log) {

        frame = new JFrame();
        frame.setSize(1366, 768);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Messenger");
        frame.setIconImage(pic.getImage());
        frame.getContentPane().setBackground(color);

        Users = new JPanel();
        Users.setLayout(new BoxLayout(Users, BoxLayout.Y_AXIS));
        Users.setBackground(color);

        JPanel space = new JPanel();

        addUsers = new JButton("    Add User    ");
        addUsers.setIcon(plus);
        addUsers.setFont(new Font("MV Boli", Font.BOLD, 15));
        addUsers.setForeground(new Color(0x0000FF));
        addUsers.setFocusable(false);

        addUsers.addActionListener(act);
        Users.add(addUsers);
        frame.add(Users, BorderLayout.WEST);
        frame.add(space, BorderLayout.CENTER);
        frame.setVisible(true);
        // return;

    }

    public static void run() {
        while (true) {
            System.out.println("hu");
            if (createUser) {
                System.out.println("ho");
                JTextField newUser = new JTextField();
                newUser.setFont(new Font("Consolas", Font.PLAIN, 35));
                newUser.setForeground(new Color(0x00FF00));
                newUser.setBackground(color);
                newUser.setCaretColor(Color.white);
                newUser.setSize(new Dimension(10, 10));
                Users.add(newUser);
                frame.add(Users);
                frame.setVisible(true);
                createUser = false;

            }

        }

    }

}