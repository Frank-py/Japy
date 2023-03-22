package Java.examplecodes;
import javax.swing.*;
import java.awt.*;

public class MessengerInterface extends JFrame {

    private final ImageIcon logo = new ImageIcon("src/pictures/prof5.png");
    private final ImageIcon plus = new ImageIcon("src/pictures/plus.png");

    public MessengerInterface() {
        initComponents();
    }

    private void initComponents() {
        JPanel panelLeft = new JPanel(new BorderLayout());
        panelLeft.setBackground(new Color(27, 37, 43));
        panelLeft.setPreferredSize(new Dimension(150, 0));
        JButton btnAddUser = new JButton("Add User", plus);
        btnAddUser.setForeground(Color.WHITE);
        btnAddUser.setBackground(new Color(47, 49, 54));
        btnAddUser.setFocusable(false);
        panelLeft.add(btnAddUser, BorderLayout.NORTH);
        JList<String> userList = new JList<>(new String[]{"User 1", "User 2", "User 3"});
        userList.setForeground(Color.WHITE);
        userList.setBackground(new Color(27, 37, 43));
        panelLeft.add(userList, BorderLayout.CENTER);

        JPanel panelRight = new JPanel(new BorderLayout());
        panelRight.setBackground(new Color(27, 37, 43));
        JTextArea chatArea = new JTextArea();
        chatArea.setForeground(Color.WHITE);
        chatArea.setBackground(new Color(27, 37, 43));
        JScrollPane scrollPane = new JScrollPane(chatArea);
        panelRight.add(scrollPane, BorderLayout.CENTER);
        JTextField messageField = new JTextField();
        messageField.setForeground(Color.WHITE);
        messageField.setBackground(new Color(47, 49, 54));
        messageField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelRight.add(messageField, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLeft, panelRight);
        splitPane.setResizeWeight(0.1);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Messenger");
        setIconImage(logo.getImage());
        getContentPane().add(splitPane);
        setMinimumSize(new Dimension(500, 500));
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MessengerInterface::new);
    }
}
