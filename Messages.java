
import java.awt.BorderLayout;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.KeyboardFocusManager;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.imageio.ImageIO;

public class Messages extends JFrame {

    JFrame frame;
    JTable table;
    JTable table1;
    JDesktopPane desktop = new JDesktopPane();
    JButton button1;
    String id = "";
    String temp = "";
    Object selectedCellValue;

    /**
     * Launch the application.
     */
    static Connection connection = null;
    static Statement stmt;
    static ResultSet rs;

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Messages window = new Messages();
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
    public Messages() throws SQLException {

        initialize();
        connection = DriverManager.getConnection("jdbc:odbc:Project");
        table();
        table1();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    public void table() {

        try {

            String query2 = "select usertype + '- ' + userid as hello from temp";
            PreparedStatement pst2;
            pst2 = connection.prepareStatement(query2);
            ResultSet rr = pst2.executeQuery();
            while (rr.next()) {
                id = rr.getString(1);
            }

            String query = "Select outbox as 'From', message from messages where inbox = '" + id + "'";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {

                    JTable target = (JTable) e.getSource();

                    int selectedRow = target.getSelectedRow();
                    int selectedColumn = 1;

                    selectedCellValue = target.getValueAt(selectedRow, selectedColumn);
                }
            }
        });

        table.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                table.clearSelection();
            }
        });

    }

    public void table1() {
        try {

            String query2 = "select usertype + '- ' + userid as hello from temp";
            PreparedStatement pst2;
            pst2 = connection.prepareStatement(query2);
            ResultSet rr = pst2.executeQuery();
            while (rr.next()) {
                id = rr.getString(1);
            }

            String query = "Select inbox as 'To', message from messages where outbox = '" + id + "'";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        table1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {

                    JTable target = (JTable) e.getSource();

                    int selectedRow = target.getSelectedRow();
                    int selectedColumn = 1;

                    selectedCellValue = target.getValueAt(selectedRow, selectedColumn);
                }
            }
        });

        table1.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                table1.clearSelection();
            }
        });
    }

    public void refreshtable() {
        try {
            String query = "Select outbox as 'From', message from messages where inbox = '" + id + "'";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            pst.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void refreshtable1() {
        try {
            String query = "Select inbox as 'To', message from messages where outbox = '" + id + "'";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
            pst.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void initialize() {

        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBounds(100, 100, 600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("message.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(53, 83, 220, 209);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(324, 83, 220, 209);
        frame.getContentPane().add(scrollPane1);

        table1 = new JTable();
        scrollPane1.setViewportView(table1);

        JLabel lblInbox = new JLabel("Inbox");
        lblInbox.setFont(new Font("Verdana", Font.PLAIN, 17));
        lblInbox.setBounds(129, 58, 65, 14);
        frame.getContentPane().add(lblInbox);

        JLabel lblOutbox = new JLabel("Outbox");
        lblOutbox.setFont(new Font("Verdana", Font.PLAIN, 17));
        lblOutbox.setBounds(397, 58, 65, 14);
        frame.getContentPane().add(lblOutbox);

        JButton btnView = new JButton("View");
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                int row = table.getSelectedRow();
                //String table_1 = (table.getModel().getValueAt(row, 1)).toString();

                MessageDialog md = new MessageDialog();
                md.setVisible(true);
                md.setLocationRelativeTo(null);

                md.jTextArea1.setText((String) selectedCellValue);

                md.jTextArea1.setEditable(false);
                table.getSelectionModel().clearSelection();

                refreshtable();
                refreshtable1();

            }
        });
        btnView.setFont(new Font("Verdana", Font.PLAIN, 15));
        btnView.setBounds(101, 315, 103, 31);
        frame.getContentPane().add(btnView);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Verdana", Font.PLAIN, 15));
        btnDelete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {

                    String query = "Delete from messages where message = '" + selectedCellValue + "' ";
                    PreparedStatement pst2 = connection.prepareStatement(query);
                    pst2.execute();

                    refreshtable();
                    refreshtable1();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        });

        btnDelete.setBounds(229, 315, 97, 31);
        frame.getContentPane().add(btnDelete);

        JButton btnNew = new JButton("New Message");

        btnNew.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                try {
                    final MessageSend f = new MessageSend();
                    f.frame.setVisible(true);
                    f.frame.setLocationRelativeTo(null);

                    f.btnSend.addActionListener(new ActionListener() {

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

                                pst3.setString(1, f.comboBox.getSelectedItem().toString());
                                pst3.setString(2, f.textArea.getText().toString());
                                pst3.setString(3, outbox2);
                                pst3.execute();

                                JOptionPane.showMessageDialog(rootPane, "Message Sent!");
                                f.frame.dispose();
                                refreshtable();
                                refreshtable1();

                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }

                        }

                    });

                    f.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }

        });

        btnNew.setFont(new Font("Verdana", Font.PLAIN, 15));
        btnNew.setBounds(355, 315, 145, 31);
        frame.getContentPane().add(btnNew);
    }

}
