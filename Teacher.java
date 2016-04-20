import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Teacher extends Login{
    
    JFrame frame = new JFrame("Admin");
    JPanel panel;
    JPanel nPanel;
    JLabel a;
    JButton btn1;        
    JButton btn2; 
    JButton btn3;
    JButton btn4;
    JButton btn5;
    JButton btn6;
    
    JLabel name;
    
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    boolean flag = false;
    
    //****************MAIN CONSTRUCTOR******************************************
    public Teacher(){
        
        add(GetNPanel(), BorderLayout.NORTH);
        add(GetCPanel(), BorderLayout.CENTER);
                
    }
    
    //****************SQL CONNECTION********************************************
    
    public boolean GetConnection(){
        
        //To Start Connection
        flag = false;
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection("jdbc:odbc:Project");
            //System.out.println("Connected");
            stmt = con.createStatement();
            flag=true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            flag = false;
        }
        
        return flag;
    }
    
    //To close connection
	public boolean CloseConnection()
	{
		flag=false;
		try
		{
                    con.close();
                    flag=true;
		}catch(Exception ex)
		{
                    flag=false;
		}
		return flag;
	}
    
    //****************CENTER PANEL**********************************************
    JPanel GetCPanel(){
        
        panel = new JPanel();
        
        panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		//gbc.insets = new Insets(1, 1, 1, 1);  //cell padding
                gbc.fill = GridBagConstraints.BOTH; //fill cell area
                gbc.weightx = 1;  // fill horizontal cell area
                gbc.weighty = 1;  //fill vertical cell area
                
                //btn1
                btn1 = new JButton(new ImageIcon("students.png"));
                btn1.setToolTipText("Add / Edit / Remove Teachers");
                gbc.gridx = 0;
		gbc.gridy = 0;
                btn1.addActionListener(this);
		panel.add(btn1, gbc);

                //btn2
                btn2 = new JButton(new ImageIcon("marks.png"));
		gbc.gridx = 1;
		gbc.gridy = 0;
                btn2.addActionListener(this);
		panel.add(btn2, gbc);
		
                //btn3
                btn3 = new JButton(new ImageIcon("attendance.png"));
		gbc.gridx = 2;
		gbc.gridy = 0;
                btn3.addActionListener(this);
		panel.add(btn3, gbc);
                
                //btn4
                btn4 = new JButton(new ImageIcon("notice.png"));
                gbc.gridx = 0;
		gbc.gridy = 1;
                btn4.addActionListener(this);
		panel.add(btn4, gbc);
                
                //btn5
                btn5 = new JButton(new ImageIcon("messages.png"));
                gbc.gridx = 1;
		gbc.gridy = 1;
                btn5.addActionListener(this);
		panel.add(btn5, gbc);
                
                //btn6
                btn6 = new JButton(new ImageIcon("password.png"));
                gbc.gridx = 2;
		gbc.gridy = 1;
                btn6.addActionListener(this);
		panel.add(btn6, gbc);
                
                return panel;
    }
    
    public void mouseClicked(MouseEvent e){
        
        String query = "delete from temp";
            try {
                stmt.execute(query);
                System.out.println("temp deleted!");
            } catch (SQLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        if(e.getSource() == a){
            Login b = new Login();
            b.setResizable(false);
            b.setSize(600,400);
            b.setLocationRelativeTo(null);
            b.setVisible(true);
            b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        dispose();
    }
    
    public void actionPerformed(ActionEvent e)
    {
        
        
        if(btn1 == e.getSource())
        {
            TeacherViewUsers viewUsers;
            try {
                viewUsers = new TeacherViewUsers();
                viewUsers.frame.setResizable(false);
                viewUsers.frame.setSize(700, 355);
                viewUsers.frame.setLocationRelativeTo(null);
                viewUsers.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                viewUsers.frame.getContentPane().setLayout(null);
                viewUsers.frame.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(btn2 == e.getSource())
        {
            Marks marks;
            try {
            marks = new Marks();
            marks.setVisible(true);            
            marks.setSize(600, 400);
            marks.setLocationRelativeTo(null);            
            marks.setResizable(false);
            marks.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        if(btn3 == e.getSource())
        {
            Attendance a;
            
            a = new Attendance();
            a.setResizable(false);
            a.setLocationRelativeTo(null);
            a.setVisible(true);
            a.setSize(600, 400);
            a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
        }
        
        if(btn4 == e.getSource())
        {
            Post post;
            try {
                post = new Post();
            post.frame.setResizable(false);
            post.frame.setLocationRelativeTo(null);
            post.frame.setVisible(true);
            post.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
   
        if (btn5 == e.getSource()) {
            Messages m;
            try {
                m = new Messages();
                m.frame.setBounds(100, 100, 600, 400);
                m.frame.setVisible(true);
                m.frame.setLocationRelativeTo(null);
                m.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                m.frame.getContentPane().setLayout(null);
                m.frame.setResizable(false);
            } catch (SQLException ex) {
                Logger.getLogger(Teacher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(btn6 == e.getSource())
        {
        ChangePassword pass = new ChangePassword();
        pass.setSize(600, 400);
        pass.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pass.setLocationRelativeTo(null);
        pass.setResizable(false);
        pass.getContentPane().setBackground(Color.white);
        pass.setVisible(true);
        }
    }
    
    
    
    
    //****************NORTH PANEL***********************************************
    JPanel GetNPanel(){
        nPanel = new JPanel();
        nPanel.setBackground(Color.white);  
        
        String Fname1 = "";
        String Lname1 = "";
        String UserType = "";
        if (GetConnection() == true) {
            String sql2 = "select * from temp";
           
            try {
                rs = stmt.executeQuery(sql2);
                
                int count = 0;

                while (rs.next()) {
                    count = count + 1;
                    Fname1 = rs.getString(2);
                    Lname1 = rs.getString(3);
                    UserType = rs.getString(4);
                }
            
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        name = new JLabel("Welcome, " + Fname1 + " " + Lname1 + "                                           User Type : " + UserType + "                                                      ");
        a  = new JLabel("<html><u><i>Logout</i></u></html>");
        a.addMouseListener(this);
        a.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        a.setForeground(Color.blue);
        a.setFont(new Font("Arial", Font.BOLD, 14));
        
        nPanel.add(name);
        nPanel.add(a);
        return nPanel;
    }
    
    
    
    public static void main(String[] args){
        new Teacher();  
        
        
        Teacher frame = new Teacher();
        //FRAME
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
    
}

