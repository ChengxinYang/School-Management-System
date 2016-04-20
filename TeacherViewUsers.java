
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.sql.*;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TeacherViewUsers {

    JFrame frame;
    private JTable table;
    JTextField textField;
    String id;
    JComboBox comboBox;

    static Connection connection = null;
    static Statement stmt;
    static ResultSet rs;
    

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TeacherViewUsers window = new TeacherViewUsers();
                    window.frame.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TeacherViewUsers() throws SQLException {
        
        initialize();
        connection = DriverManager.getConnection("jdbc:odbc:Project");
        table();
       // Combobox();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                textField.requestFocusInWindow();
            }
        });
    }

    private void table() {
        try {
            String query = " Select * from students";
            PreparedStatement pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void refreshtable() {
        try {
            String query = " Select * from students";
            PreparedStatement pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            pst.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setResizable(false);
        frame.setSize(700, 355);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("viewusers.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JLabel label = new JLabel("<html>Search by Name: </html>");
        label.setBounds(10, 277, 122, 30);
        label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        frame.getContentPane().add(label);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 41, 674, 219);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        table.setRowSelectionAllowed(false);

        scrollPane.setViewportView(table);

        textField = new JTextField();
        textField.setBounds(135, 277, 161, 28);
        frame.getContentPane().add(textField);

        textField.toString().trim();
        textField.setFont(new Font("Verdana", Font.PLAIN, 14));
        textField.setForeground(Color.BLACK);
        textField.setBackground(Color.WHITE);

        textField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:odbc:Project");
                    Statement st = conn.createStatement();
                    String query2 = "SELECT * FROM students WHERE FName LIKE '%" + textField.getText() + "%'";
                    rs = st.executeQuery(query2);
                   

                    table.setModel(DbUtils.resultSetToTableModel(rs));

                    conn.close();

                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }

        });

//        Combobox();
    }

}
