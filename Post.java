
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;
import java.awt.TextField;
import java.sql.*;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Post {

    JFrame frame;
    private JTable table;
    JTextArea textArea;
    String id;
    static Connection connection = null;
    static Statement stmt;
    static ResultSet rs;

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Post window = new Post();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Post() throws SQLException {
        initialize();
        connection = DriverManager.getConnection("jdbc:odbc:Project");
        table();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                textArea.requestFocusInWindow();
            }
        });
    }

    private void table() {
        try {
            String query = "Select * from PostNotice;";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void refreshtable() {
        try {
            String query = "Select * from PostNotice;";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            pst.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Initialize the contents of the frame.
     */
//    Connection connection = null;
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setResizable(false);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("board.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 45, 490, 185);

        frame.getContentPane().add(scrollPane);

        table = new JTable();
        table.setRowSelectionAllowed(false);
        table.setFillsViewportHeight(true);
        Color customColor = new Color(85, 240, 220);
        table.setBackground(customColor);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                try {
                    int row = table.getSelectedRow();
                    String Description = (table.getModel().getValueAt(row, 1)).toString();

                    String query = "select * from PostNotice where Description='" + Description + "'";
                    PreparedStatement pst = connection.prepareStatement(query);

                    ResultSet rs = pst.executeQuery();


                    pst.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        scrollPane.setViewportView(table);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(110, 250, 359, 59);
        frame.getContentPane().add(scrollPane_1);

        textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.toString().trim();
        textArea.setFont(new Font("Verdana", Font.PLAIN, 14));

        scrollPane_1.setViewportView(textArea);
        textArea.setForeground(Color.BLACK);
        textArea.setBackground(Color.WHITE);

        JButton btnPost = new JButton("Post");
        btnPost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textArea.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Post Cannot be empty!");
                } else {
                    String query2 = "select usertype + '- ' + userid as hello from temp";
                    PreparedStatement pst2;
                    try {
                        pst2 = connection.prepareStatement(query2);
                        ResultSet rr = pst2.executeQuery();
                        while (rr.next()) {
                            id = rr.getString(1);
                        }
                        String query = "Insert into PostNotice (id,Description) Values (LTRIM(RTRIM(?)), LTRIM(RTRIM(?)))";

                        PreparedStatement pst = connection.prepareStatement(query);

                        pst.setString(1, id);
                        pst.setString(2, textArea.getText().trim());

                        pst.execute();

                        JOptionPane.showMessageDialog(null, "Post Succesfull");

                        refreshtable();
                        pst.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                textArea.setText("");
                textArea.requestFocus();
                refreshtable();
            }
        });
        btnPost.setFont(new Font("Bookman Old Style", Font.PLAIN, 17));
        btnPost.setBounds(125, 325, 98, 29);
        frame.getContentPane().add(btnPost);

        JButton btnNewButton = new JButton("Delete");
        btnNewButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 17));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    String query33 = "select usertype + '- ' + userid as hello from temp";
                    PreparedStatement pst22 = connection.prepareStatement(query33);
                    ResultSet rrr = pst22.executeQuery();
                    while (rrr.next()) {
                        id = rrr.getString(1);
                    }
                    int row2 = table.getSelectedRow();
                    String id2 = (table.getModel().getValueAt(row2, 0)).toString();

                    if (!id.equals(id2)) {
                        JOptionPane.showMessageDialog(frame, "You do not have the permission to delete!");
                    } else {

                        String query = "Delete from PostNotice where Description =? and id=?";
                        try {

                            String query2 = "select usertype + '- ' + userid from temp";
                            PreparedStatement pst2 = connection.prepareStatement(query2);
                            ResultSet rr = pst2.executeQuery();
                            while (rr.next()) {
                                System.out.println("in loop");
                                id = rr.getString(1);
                            }
                            System.out.println(id);
                            PreparedStatement pst = connection.prepareStatement(query);

                            int row = table.getSelectedRow();
                            String Description = (table.getModel().getValueAt(row, 1)).toString();
                            System.out.println(Description);
                            pst.setString(1, Description);
                            pst.setString(2, id);

                            pst.execute();
                            JOptionPane.showMessageDialog(null, "Post Deleted");

                            pst.close();

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        textArea.setText("");
                        textArea.requestFocus();
                        refreshtable();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnNewButton.setBounds(357, 325, 90, 29);
        frame.getContentPane().add(btnNewButton);


        JButton btnNewButton_1 = new JButton("View");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {

                    String query = "select * from PostNotice where Description =?";
                    PreparedStatement pst = connection.prepareStatement(query);
                    pst.setString(1, textArea.getText().trim());

                    int row = table.getSelectedRow();
                    String Description = (table.getModel().getValueAt(row, 1)).toString();

                    final PostDialog s = new PostDialog();
                    s.setVisible(true);
                    s.setLocationRelativeTo(null);
                    s.jTextArea1.setText(Description);
                    s.jTextArea2.setText(Description);

                    String query2 = "select usertype + '- ' + userid as hello from temp";
                    PreparedStatement pst2 = connection.prepareStatement(query2);
                    ResultSet rr = pst2.executeQuery();
                    while (rr.next()) {
                        id = rr.getString(1);
                    }
                    int row2 = table.getSelectedRow();
                    String id2 = (table.getModel().getValueAt(row2, 0)).toString();

                    if (!id.equals(id2)) {
                        s.jTextArea1.setEditable(false);
                        System.out.println(id2);
                        System.out.println(id);

                    } else {
                        s.jButton1.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                try {

                                    String query3 = "Update PostNotice set Description = ltrim(rtrim(?)) where Description = ? ";
                                    PreparedStatement pst3 = connection.prepareStatement(query3);
                                    pst3.setString(1, s.jTextArea1.getText().trim());
                                    pst3.setString(2, s.jTextArea2.getText().trim());

                                    pst3.execute();

                                    JOptionPane.showMessageDialog(null, "Update Succesfull");

                                    refreshtable();
                                    pst3.close();
                                    s.dispose();
                                    textArea.setText("");

                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }

                            }
                        });
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 17));
        btnNewButton_1.setBounds(241, 325, 98, 29);
        frame.getContentPane().add(btnNewButton_1);

    }
}
