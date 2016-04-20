
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class AdminViewUsers {

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
                    AdminViewUsers window = new AdminViewUsers();
                    window.frame.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AdminViewUsers() throws SQLException {
        
        initialize();
        connection = DriverManager.getConnection("jdbc:odbc:Project");
        table();
        Combobox();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                textField.requestFocusInWindow();
            }
        });
    }

    private void table() {
        try {
            String a = comboBox.getSelectedItem().toString();
            //a = a.substring(0, a.length() - 1);
            String query = " Select * from " + a;
            PreparedStatement pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void refreshtable() {
        try {
            String a = comboBox.getSelectedItem().toString();
            //a = a.substring(0, a.length() - 1);
            String query = " Select * from " + a;
            PreparedStatement pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            pst.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    ItemListener itemListener = new ItemListener() {
        public void itemStateChanged(ItemEvent itemEvent) {
            if (comboBox.getSelectedItem().equals("Admins")) {
                try {
                    String query = "Select userid, usertype, fname, lname from admins";
                    PreparedStatement pst = connection.prepareStatement(query);
                    rs = pst.executeQuery();
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                refreshtable();
            }

            if (comboBox.getSelectedItem() == "Teachers") {
                try {
                    String query = "Select * from teachers;";
                    PreparedStatement pst = connection.prepareStatement(query);
                    rs = pst.executeQuery();
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                refreshtable();
            }

            if (comboBox.getSelectedItem() == "Students") {
                try {
                    String query = "Select * from students;";
                    PreparedStatement pst = connection.prepareStatement(query);
                    rs = pst.executeQuery();
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                refreshtable();
            }
        }
    };

    private void Combobox() {
        comboBox.addItemListener(itemListener);

        comboBox.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (comboBox.getSelectedItem().equals("Admins")) {
                    try {
                        String query = "Select userid, usertype, fname, lname from admins";
                        PreparedStatement pst = connection.prepareStatement(query);
                        rs = pst.executeQuery();
                        table.setModel(DbUtils.resultSetToTableModel(rs));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    refreshtable();
                }

                if (comboBox.getSelectedItem() == "Teachers") {
                    try {
                        String query = "Select * from teachers;";
                        PreparedStatement pst = connection.prepareStatement(query);
                        rs = pst.executeQuery();
                        table.setModel(DbUtils.resultSetToTableModel(rs));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    refreshtable();
                }

                if (comboBox.getSelectedItem() == "Students") {
                    try {
                        String query = "Select * from students;";
                        PreparedStatement pst = connection.prepareStatement(query);
                        rs = pst.executeQuery();
                        table.setModel(DbUtils.resultSetToTableModel(rs));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    refreshtable();
                }
            }
        });

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
        label.setBounds(225, 277, 122, 30);
        label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        frame.getContentPane().add(label);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 41, 674, 219);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        table.setRowSelectionAllowed(false);

        scrollPane.setViewportView(table);

        textField = new JTextField();
        textField.setBounds(357, 277, 161, 28);
        frame.getContentPane().add(textField);

        textField.toString().trim();
        textField.setFont(new Font("Verdana", Font.PLAIN, 14));
        textField.setForeground(Color.BLACK);
        textField.setBackground(Color.WHITE);

        comboBox = new JComboBox();
        comboBox.setBounds(66, 277, 122, 31);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBox.addItem("Admins");
        comboBox.addItem("Teachers");
        comboBox.addItem("Students");
        frame.getContentPane().add(comboBox);

        JLabel lblView = new JLabel("View :");
        lblView.setBounds(11, 283, 46, 14);
        lblView.setFont(new Font("Tahoma", Font.PLAIN, 15));
        frame.getContentPane().add(lblView);

        JButton btnSendMessage = new JButton("Send Message");
        btnSendMessage.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnSendMessage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {                
                try {
                    final MessageSend f = new MessageSend();
                    f.frame.setVisible(true);
                    f.frame.setLocationRelativeTo(null);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminViewUsers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnSendMessage.setBounds(562, 277, 122, 28);
        frame.getContentPane().add(btnSendMessage);

        textField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:odbc:Project");
                    Statement st = conn.createStatement();
                    String query = "Select userid, usertype, fname, lname from admins WHERE FName LIKE '%" + textField.getText() + "%'";
                    String query1 = "SELECT * FROM teachers WHERE FName LIKE '%" + textField.getText() + "%'";
                    String query2 = "SELECT * FROM students WHERE FName LIKE '%" + textField.getText() + "%'";

                    if (comboBox.getSelectedItem() == "Admins") {
                        rs = st.executeQuery(query);
                    } else if (comboBox.getSelectedItem() == "Teachers") {
                        rs = st.executeQuery(query1);
                    } else if (comboBox.getSelectedItem() == "Students") {
                        rs = st.executeQuery(query2);
                    }

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
