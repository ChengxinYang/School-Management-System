
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class Marks extends JFrame implements TableModelListener, MouseListener, ActionListener {

    JPanel NPanel = new JPanel();
    JPanel start = new JPanel();
    JPanel EnglishPanel = new JPanel();
    JPanel GeoPanel = new JPanel();
    JPanel MathPanel = new JPanel();
    JPanel SciencePanel = new JPanel();
    JPanel HistoryPanel = new JPanel();
    JPanel ITPanel = new JPanel();
    JLabel title;
    JPasswordField passfield;
    String item = "";
    String pass = "";
    String tabName = "";
    static Connection connection = null;
    static Statement stmt = null;
    static ResultSet rs = null;
    JTabbedPane tabbedPane = new JTabbedPane();
    private JTable table_1 = new JTable();
    private JScrollPane scrollPane_1 = new JScrollPane();
    private JTable table_2 = new JTable();
    private JScrollPane scrollPane_2 = new JScrollPane();
    private JTable table_3 = new JTable();
    private JScrollPane scrollPane_3 = new JScrollPane();
    private JTable table_4 = new JTable();
    private JScrollPane scrollPane_4 = new JScrollPane();
    private JTable table_5 = new JTable();
    private JScrollPane scrollPane_5 = new JScrollPane();
    private final JTable table = new JTable();
    private final JScrollPane scrollPane = new JScrollPane();

    public Marks() throws SQLException {

        connection = DriverManager.getConnection("jdbc:odbc:Project");
        setBackground(Color.WHITE);
        getContentPane().setBackground(Color.WHITE);
        getContentPane().add(NPanel(), BorderLayout.NORTH);
        // firstLabel.setBounds(250, 5, 77, 29);
        // firstLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 21));
        // secondLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 21));
        EnglishPanel.setBackground(Color.WHITE);
        GeoPanel.setBackground(Color.WHITE);
        // EnglishPanel.add(firstLabel);
        // GeoPanel.add(secondLabel);
        tabbedPane.setBackground(Color.WHITE);
        tabbedPane.add("Start Page", start);
        tabbedPane.setBackgroundAt(0, Color.WHITE);
        tabbedPane.addMouseListener(this);
        
    	ImageIcon titleIcon = new ImageIcon("markstab.png");
	JLabel title = new JLabel(titleIcon);
        start.add(title);
        
        tabbedPane.add("English", EnglishPanel);
        tabbedPane.setBackgroundAt(0, Color.WHITE);
        
        tabbedPane.add("Geography", GeoPanel);
        GeoPanel.setLayout(null);
        scrollPane_1.setBounds(22, 23, 531, 230);

        GeoPanel.add(scrollPane_1);
        GeoPanel.addMouseListener(this);

        scrollPane_1.setViewportView(table_1);
        tabbedPane.setBackgroundAt(1, Color.WHITE);
        HistoryPanel.setBackground(Color.WHITE);
        
        tabbedPane.add("History", HistoryPanel);
        HistoryPanel.setLayout(null);
        scrollPane_2.setBounds(22, 23, 531, 230);

        HistoryPanel.add(scrollPane_2);
        scrollPane_2.setViewportView(table_2);
        ITPanel.setBackground(Color.WHITE);
        tabbedPane.add("IT", ITPanel);
        ITPanel.setLayout(null);
        scrollPane_3.setBounds(22, 23, 531, 230);

        ITPanel.add(scrollPane_3);
        scrollPane_3.setViewportView(table_3);
        MathPanel.setBackground(Color.WHITE);
        tabbedPane.add("Math", MathPanel);
        MathPanel.setLayout(null);
        scrollPane_4.setBounds(22, 23, 531, 230);

        MathPanel.add(scrollPane_4);
        scrollPane_4.setViewportView(table_4);
        SciencePanel.setBackground(Color.WHITE);
        tabbedPane.add("Science", SciencePanel);
        SciencePanel.setLayout(null);
        SciencePanel.addMouseListener(this);
        scrollPane_5.setBounds(22, 23, 531, 230);

        SciencePanel.add(scrollPane_5);
        scrollPane_5.setViewportView(table_5);
        EnglishPanel.setLayout(null);
        scrollPane.setBounds(22, 23, 531, 230);

        EnglishPanel.add(scrollPane);
        scrollPane.setViewportView(table);

        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 15));
        getContentPane().add(tabbedPane);

        table();
        table_1.addMouseListener(this);
        table_2.addMouseListener(this);
        table_3.addMouseListener(this);
        table_4.addMouseListener(this);
        table_5.addMouseListener(this);
        table.addMouseListener(this);
        //item();
        //Password();

        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                try {

                    if (tabbedPane.getSelectedIndex() == 0) {
                        tabName = "start";
                    } else if (tabbedPane.getSelectedIndex() == 1) {
                        tabName = "English";
                    } else if (tabbedPane.getSelectedIndex() == 2) {
                        tabName = "Geography";
                    } else if (tabbedPane.getSelectedIndex() == 3) {
                        tabName = "History";
                    } else if (tabbedPane.getSelectedIndex() == 4) {
                        tabName = "IT";
                    } else if (tabbedPane.getSelectedIndex() == 5) {
                        tabName = "Math";
                    } else if (tabbedPane.getSelectedIndex() == 6) {
                        tabName = "Science";
                    }


                    passfield = new JPasswordField();
                    pass = passfield.getText();
                    stmt = connection.createStatement();

                    String query = "Select * from teachers where Subject = '" + tabName + "'";
                    ResultSet rs = stmt.executeQuery(query);


                    while (rs.next()) {
                        pass = rs.getString(2);
                    }
                    System.out.println(pass);

                    //String query = "Select * from teachers where Subject = '" + tabName + "' and Password='" + pass + "'";
                    //ResultSet rs = stmt.executeQuery(query);

                    if (!tabName.equals("start")) {
                   
                    int message = JOptionPane.showConfirmDialog(null, passfield, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (message == JOptionPane.OK_OPTION) {
                        String password = new String(passfield.getPassword());

                        if (!password.equals(pass)) {
                            stateChanged(e);
                            if (message == JOptionPane.OK_CANCEL_OPTION) {
                                dispose();
                            }
                        }

                        System.err.println("You entered: " + password);
                    } else if (message == JOptionPane.OK_CANCEL_OPTION) {
                        dispose();
                    }
                    } 
                } catch (SQLException ex) {
                    Logger.getLogger(Marks.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == table_1) {

            item = "Geography";
        } else if (e.getSource() == table_2) {

            item = "History";
        } else if (e.getSource() == table_3) {

            item = "IT";
        } else if (e.getSource() == table_4) {

            item = "Math";
        } else if (e.getSource() == table_5) {

            item = "Science";
        } else if (e.getSource() == table) {

            item = "English";
        }

        if (e.getSource() == GeoPanel) {
            System.out.println("Geo");
            // JOptionPane.showInputDialog(null, "Hjd");
        } else if (e.getSource() == HistoryPanel) {
            JOptionPane.showInputDialog(null, "Hjd");
        }

    }

    private void table() {
        try {
            String query = "SELECT Userid, Name, Sem1, Sem2, (ISNULL(Sem1,3)+ISNULL(Sem2,4))/2.0 AS Average FROM ENGLISH;";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            table.getModel().addTableModelListener(this);

            String query1 = "SELECT Userid, Name, Sem1, Sem2, (ISNULL(Sem1,3)+ISNULL(Sem2,4))/2.0 AS Average FROM Geography";
            PreparedStatement pst1 = connection.prepareStatement(query1);
            ResultSet rs1 = pst1.executeQuery();
            table_1.setModel(DbUtils.resultSetToTableModel(rs1));
            table_1.getModel().addTableModelListener(this);

            String query2 = "SELECT Userid, Name, Sem1, Sem2, (ISNULL(Sem1,3)+ISNULL(Sem2,4))/2.0 AS Average FROM History";
            PreparedStatement pst2 = connection.prepareStatement(query2);
            ResultSet rs2 = pst2.executeQuery();
            table_2.setModel(DbUtils.resultSetToTableModel(rs2));
            table_2.getModel().addTableModelListener(this);

            String query3 = "SELECT Userid, Name, Sem1, Sem2, (ISNULL(Sem1,3)+ISNULL(Sem2,4))/2.0 AS Average FROM IT";
            PreparedStatement pst3 = connection.prepareStatement(query3);
            ResultSet rs3 = pst3.executeQuery();
            table_3.setModel(DbUtils.resultSetToTableModel(rs3));
            table_3.getModel().addTableModelListener(this);

            String query4 = "SELECT Userid, Name, Sem1, Sem2, (ISNULL(Sem1,3)+ISNULL(Sem2,4))/2.0 AS Average FROM Math";
            PreparedStatement pst4 = connection.prepareStatement(query4);
            ResultSet rs4 = pst4.executeQuery();
            table_4.setModel(DbUtils.resultSetToTableModel(rs4));
            table_4.getModel().addTableModelListener(this);

            String query5 = "SELECT Userid, Name, Sem1, Sem2, (ISNULL(Sem1,3)+ISNULL(Sem2,4))/2.0 AS Average FROM Science";
            PreparedStatement pst5 = connection.prepareStatement(query5);
            ResultSet rs5 = pst5.executeQuery();
            table_5.setModel(DbUtils.resultSetToTableModel(rs5));
            table_5.getModel().addTableModelListener(this);






        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void refreshtable() {
        try {
            String query = "Select * from English";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            // table_1.setModel(DbUtils.resultSetToTableModel(rs));

            pst.close();
            table();
            //tableChanged(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void tableChanged(TableModelEvent e) {

        System.out.println(item);
        try {
            System.out.println("Connected");
            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model = (TableModel) e.getSource();
            String columnName = model.getColumnName(column);
            Object data = model.getValueAt(row, column);
            Object roll = model.getValueAt(row, 0);
            System.out.println(data.toString());
            System.out.println(roll.toString());
            System.out.println(columnName);
            System.out.println(item);
            String query = "UPDATE " + item + " SET " + columnName + " = '" + data.toString() + "' WHERE userid='" + roll.toString() + "' ";
            System.out.println("changed");
            PreparedStatement pst;

            pst = connection.prepareStatement(query);

            pst.executeUpdate();
            // pst.close();
            refreshtable();

        } catch (SQLException ex) {
            //Logger.getLogger(Marks.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    JPanel NPanel() {
        NPanel = new JPanel();
        NPanel.setBackground(Color.WHITE);
        NPanel.add(title());
        return NPanel;
    }

    JLabel title() {
        title = new JLabel("<html><u>Marks</u></html>");
        title.setBackground(Color.WHITE);
        title.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 25));
        return title;
    }

    public static void main(String[] args) {

        try {
            Marks tp = new Marks();
            tp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            tp.setSize(600, 400);
            //tp.setResizable(false);
            tp.setLocationRelativeTo(null);
            tp.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Marks.class.getName()).log(Level.SEVERE, null, ex);
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
    public void actionPerformed(ActionEvent e) {
    }
}
