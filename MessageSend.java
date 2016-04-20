
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;

public class MessageSend extends JFrame {

    JComboBox comboBox;
    JFrame frame;
    JTable table;
    JTextArea textArea;
    JButton btnSend;
    static Connection connection = null;
    static Statement stmt;
    static ResultSet rs;

    public static Connection dbConnector() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            connection = DriverManager.getConnection("jdbc:odbc:Project");
            //JOptionPane.showMessageDialog(null, "Connection Succesfull");
            return connection;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error");
            return null;
        }
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MessageSend window = new MessageSend();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void FillComboBox() {
        try {
            String query = "SELECT usertype + '- ' + userid as UserId FROM admins UNION SELECT usertype + '- ' + userid as UserId FROM teachers";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                comboBox.addItem(rs.getString("UserId"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Create the application.
     */
    public MessageSend() throws SQLException {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            connection = DriverManager.getConnection("jdbc:odbc:Project");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MessageSend.class.getName()).log(Level.SEVERE, null, ex);
        }

        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {

        frame = new JFrame("Send Message");
        frame.setBounds(100, 100, 400, 330);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("messagesend2.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        comboBox = new JComboBox();
        comboBox.setBounds(117, 20, 167, 29);
        frame.getContentPane().add(comboBox);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(49, 63, 292, 163);
        frame.getContentPane().add(scrollPane);

        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);

        btnSend = new JButton("Send");
        btnSend.setFont(new Font("Verdana", Font.PLAIN, 16));
        btnSend.setBounds(155, 245, 92, 29);
        frame.getContentPane().add(btnSend);

        /*btnSend.addActionListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent arg0) {
        try {
        String outbox2 = "";
        String query1 = "Insert into messages values( (LTRIM(RTRIM(?))), (LTRIM(RTRIM(?))), (LTRIM(RTRIM(?))) )";
        PreparedStatement pst3 = connection.prepareStatement(query1);
        
        String outbox = "Select usertype + '- ' + userid as UserId from temp";
        PreparedStatement pst33 = connection.prepareStatement(outbox);
        ResultSet rr = pst33.executeQuery();
        while (rr.next()) {
        outbox2 = rr.getString(1);
        }
        
        pst3.setString(1, comboBox.getSelectedItem().toString());
        pst3.setString(2, textArea.getText().toString());
        pst3.setString(3, outbox2);
        pst3.execute();
        
        JOptionPane.showMessageDialog(rootPane, "Message Sent!");
        
        frame.dispose();
        
        
        
        
        } catch (SQLException ex) {
        ex.printStackTrace();
        }
        
        }
        
        });*/
                
        FillComboBox();
    }
}
