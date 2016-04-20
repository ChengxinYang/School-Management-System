import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminStudentTable extends JFrame{

    //private String table;
    public Connection con;
    public AdminStudentTable(){
        try {
            con=DriverManager.getConnection("jdbc:odbc:Project");
        } catch (SQLException ex) {
            Logger.getLogger(AdminStudentTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public JTable getTable(String table)throws Exception{
        JTable t1=new JTable();
        DefaultTableModel dm=new DefaultTableModel();
        Statement st=con.createStatement();   
        ResultSet rs=st.executeQuery("select UserId, Fname, Lname, gender, Phone, email, birth from "+table);
        ResultSetMetaData rsmd=rs.getMetaData();
        //Coding to get columns-
        int cols=rsmd.getColumnCount();
        String c[]=new String[cols];
        for(int i=0;i<cols;i++){
            c[i]=rsmd.getColumnName(i+1);
            dm.addColumn(c[i]);
        }
        //get data from rows
        Object row[]=new Object[cols];
        while(rs.next()){
             for(int i=0;i<cols;i++){
                    row[i]=rs.getString(i+1);
                }
            dm.addRow(row);
        }
        t1.setModel(dm);
        con.close();
        return t1;
    }
    
    public JTable getTable(String table,String query)throws Exception{
        JTable t1=new JTable();
        DefaultTableModel dm=new DefaultTableModel();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        ResultSetMetaData rsmd=rs.getMetaData();
        //Coding to get columns-
        int cols=rsmd.getColumnCount();
        String c[]=new String[cols];
        for(int i=0;i<cols;i++){
            c[i]=rsmd.getColumnName(i+1);
            dm.addColumn(c[i]);
        }


        //get data from rows
        Object row[]=new Object[cols];
        while(rs.next()){
             for(int i=0;i<cols;i++){
                    row[i]=rs.getString(i+1);
                }
            dm.addRow(row);
        }
        t1.setModel(dm);
        con.close();
        return t1;
    }


    public static void main(String ar[])throws Exception{
        JFrame f=new JFrame();
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:Project");
        AdminStudentTable obj = new AdminStudentTable();
        JScrollPane sp=new JScrollPane(obj.getTable("students"));
        f.add(sp);
        f.setSize(600, 200);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);       
        f.setResizable(false);
        f.setLocationRelativeTo(null);

        
                
    }

}