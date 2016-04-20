
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

public class Attendance extends JFrame implements MouseListener {

    Object selectedCellValue[] = new Object[50];
    static Statement stmt = null;
    static ResultSet rs = null;
    JLabel lblDate;
    JComboBox comboBox;
    int selectedRow[] = new int[50];
    static int index = 0;
    static Date date;
    static SimpleDateFormat ft;
    static String stringDate;
    static JTable table;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Attendance frame = new Attendance();
                frame.setVisible(true);
            }
        });
    }

    /**
     * Create the frame.
     */
    public Attendance() {
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        // Customer List
        JLabel lblCustomerList = new JLabel("<html><u>Attendance</u></html>");
        lblCustomerList.setBounds(231, 11, 143, 25);
        lblCustomerList.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));
        getContentPane().add(lblCustomerList);

        // ScrollPane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(28, 59, 525, 240);
        getContentPane().add(scrollPane);

        // Table
        table = new JTable();
        scrollPane.setViewportView(table);

        // Button Update
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(464, 310, 89, 30);
        btnUpdate.setFont(new Font("Verdana", Font.PLAIN, 15));
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UpdateData();

                //UpdateData(strUserID); // Update Data
                PopulateData(); // Reload Table

            }
        });
        getContentPane().add(btnUpdate);

        lblDate = new JLabel("");
        lblDate.setBounds(28, 35, 136, 13);
        lblDate.setFont(new Font("Verdana", Font.PLAIN, 15));
        getContentPane().add(lblDate);

        comboBox = new JComboBox();
        comboBox.setBounds(28, 310, 102, 25);
        comboBox.addItem("Search By");
        comboBox.addItem("User Id");
        comboBox.addItem("Name");
        comboBox.addItem("Date");
        getContentPane().add(comboBox);

        textField = new JTextField();
        textField.setBounds(140, 310, 127, 25);
        getContentPane().add(textField);
        textField.setColumns(10);
        textField.setEditable(false);

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:odbc:Project");
                    Statement st = conn.createStatement();
                    String query = "Select * from Attendance2 WHERE Userid LIKE '%" + textField.getText() + "%' order by date desc, userid";
                    String query1 = "SELECT * FROM Attendance2 WHERE Name LIKE '%" + textField.getText() + "%' order by date desc, userid";
                    String query2 = "SELECT * FROM Attendance2 WHERE Date LIKE '%" + textField.getText() + "%' order by date desc, userid";

                    if (comboBox.getSelectedItem() == "Search By") {
                        textField.setEditable(false);
                        PopulateData();
                    } else if (comboBox.getSelectedItem() == "User Id") {
                        textField.setEditable(true);
                        rs = st.executeQuery(query);
                        table.setModel(DbUtils.resultSetToTableModel(rs));
                    } else if (comboBox.getSelectedItem() == "Name") {
                        textField.setEditable(true);
                        rs = st.executeQuery(query1);
                        table.setModel(DbUtils.resultSetToTableModel(rs));
                    } else if (comboBox.getSelectedItem() == "Date") {
                        textField.setEditable(true);
                        rs = st.executeQuery(query2);
                        table.setModel(DbUtils.resultSetToTableModel(rs));
                    }

                    conn.close();

                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });

        textField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:odbc:Project");
                    Statement st = conn.createStatement();
                    String query = "Select * from Attendance2 WHERE Userid LIKE '%" + textField.getText() + "%' order by date desc, userid";
                    String query1 = "SELECT * FROM Attendance2 WHERE Name LIKE '%" + textField.getText() + "%' order by date desc, userid";
                    String query2 = "SELECT * FROM Attendance2 WHERE Date LIKE '%" + textField.getText() + "%' order by date desc, userid";

                    if (comboBox.getSelectedItem() == "Search By") {
                        textField.setEditable(false);
                    } else if (comboBox.getSelectedItem() == "User Id") {
                        textField.setEditable(true);
                        rs = st.executeQuery(query);
                        table.setModel(DbUtils.resultSetToTableModel(rs));
                    } else if (comboBox.getSelectedItem() == "Name") {
                        textField.setEditable(true);
                        rs = st.executeQuery(query1);
                        table.setModel(DbUtils.resultSetToTableModel(rs));
                    } else if (comboBox.getSelectedItem() == "Date") {
                        textField.setEditable(true);
                        rs = st.executeQuery(query2);
                        table.setModel(DbUtils.resultSetToTableModel(rs));
                    }

                    conn.close();

                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }


            }
        });

        clock();
        PopulateData();

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();

                    selectedRow[index] = target.getSelectedRow();
                    int selectedColumn = 0;

                    selectedCellValue[index] = target.getValueAt(selectedRow[index], selectedColumn);
                    index++;
                }
            }
        });

    }

    private void clock() {
        Calendar cal = new GregorianCalendar();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        lblDate.setText("Date :" + year + "/" + (month + 1) + "/" + day + "");

    }

    private static void PopulateData() {

        // Clear table
        table.setModel(new DefaultTableModel());

        // Model for Table
        DefaultTableModel model = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return Boolean.class;

                    default:
                        return String.class;
                }
            }
        };
        table.setModel(model);

        // Add Column
        // model.addColumn("Select");
        model.addColumn("UserId");
        model.addColumn("Name");
        model.addColumn("Date");
        model.addColumn("Select");

        Connection con = null;
        Statement s = null;

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection("jdbc:odbc:Project");

            s = con.createStatement();

            String sql = "SELECT UserId, Name, DATE FROM Attendance order by name Asc";

            ResultSet rec = s.executeQuery(sql);
            int row = 0;
            while ((rec != null) && (rec.next())) {
                model.addRow(new Object[0]);
                model.setValueAt(false, row, 3); // Checkbox
                model.setValueAt(rec.getString("UserId"), row, 0);
                model.setValueAt(rec.getString("Name"), row, 1);
                model.setValueAt(rec.getString("Date"), row, 2);


                row++;
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }



        date = new Date();
        ft = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(ft.format(date));
        stringDate = ft.format(date);

    }

    // Update
    private void UpdateData() {

        Connection connect = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            connect = DriverManager.getConnection("jdbc:odbc:Project");
            System.out.println("Connected");
            s = connect.createStatement();

            System.out.println(selectedCellValue[index]);

            for (int count = 0; count < index; count++) {

                String sql = "select * from attendance where userid = '" + selectedCellValue[count] + "' ";

                rs = s.executeQuery(sql);

                String id = "";
                String name = "";
                Date date2 = new Date();

                int cnt = 0;
                while (rs.next()) {
                    id = rs.getString(1);
                    name = rs.getString(2);
                    date2 = rs.getDate(3);
                    cnt = rs.getInt(5);
                }

                String stringDate2 = ft.format(date2);

                if (stringDate2.matches(stringDate)) {
                    JOptionPane.showMessageDialog(null, "Attendance already done!");
                    System.out.println("attendance done!");
                } else {
                    String query2 = "insert into Attendance2 values('" + id + "', '" + name + "', GETDATE(), 'Present', " + (cnt + 1) + ")";
                    s.execute(query2);
                    JOptionPane.showMessageDialog(null, "Attendance updated");
                }

                String sql2 = "UPDATE Attendance SET Count=(Count+1), Date=(GETDATE()) where UserId = '" + selectedCellValue[count] + "'";

                s.executeUpdate(sql2);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            //JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
    }
}
