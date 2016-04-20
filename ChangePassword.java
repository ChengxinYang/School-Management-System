
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;


public class ChangePassword extends JFrame implements ActionListener{

    JPanel center = new JPanel();
    JPanel NPanel;

    JLabel title, userId, pass, newPass, newPass2 , empLbl;
    JTextField getUserId, getPass, getNewPass, getNewPass2;
    JButton btnChange;

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    boolean flag = false;

    GridBagConstraints gbc;

    public ChangePassword()
    {
        add(GetUserPanel(), BorderLayout.CENTER);
        add(GetNPanel(), BorderLayout.NORTH);


    }
    JPanel GetNPanel()
    {
    	NPanel = new JPanel();
    	ImageIcon titleIcon = new ImageIcon("titleicon.png");
		JLabel title = new JLabel(titleIcon);

		NPanel.add(title);
    	return NPanel;
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



    JPanel GetUserPanel(){

        center=new JPanel();
        center.setLayout(new GridBagLayout());
        center.setBackground(Color.WHITE);

		GridBagConstraints gbcUserId = new GridBagConstraints();
		gbcUserId.gridx = 0; //column
        gbcUserId.gridy = 0; //row
        gbcUserId.fill=GridBagConstraints.BOTH;
        gbcUserId.insets = new Insets(10, 10, 10, 10);
        center.add(getUserId(), gbcUserId);


        GridBagConstraints gbcTxtUserId = new GridBagConstraints();
        gbcTxtUserId.gridx = 1;
        gbcTxtUserId.gridy = 0;
        center.add(getTxtUserId(), gbcTxtUserId);


        GridBagConstraints gbcoldPass = new GridBagConstraints();
        gbcoldPass.gridx = 0; //column
        gbcoldPass.gridy = 1;
        gbcoldPass.fill=GridBagConstraints.BOTH;
        gbcoldPass.insets = new Insets(10, 10, 10, 10);
        center.add(getoldPass(), gbcoldPass);

        GridBagConstraints gbcTxtoldpass = new GridBagConstraints();
        gbcTxtoldpass.gridx = 1;
        gbcTxtoldpass.gridy = 1;
        center.add(getTxtoldpass(), gbcTxtoldpass);

        GridBagConstraints gbcnewpass = new GridBagConstraints();
        gbcnewpass.gridx = 0; //column
        gbcnewpass.gridy = 2;
        gbcnewpass.fill=GridBagConstraints.BOTH;
        gbcnewpass.insets = new Insets(10, 10, 10, 10);
        center.add(getnewpass(), gbcnewpass);

        GridBagConstraints gbcTxtnewpass = new GridBagConstraints();
        gbcTxtnewpass.gridx = 1;
        gbcTxtnewpass.gridy = 2;
        center.add(getTxtnewpass(), gbcTxtnewpass);

        GridBagConstraints gbcnewpass2 = new GridBagConstraints();
        gbcnewpass2.gridx = 0; //column
        gbcnewpass2.gridy = 3;
        gbcnewpass2.fill=GridBagConstraints.BOTH;
        gbcnewpass2.insets = new Insets(10, 10, 10, 10);
        center.add(getnewpass2(), gbcnewpass2);

        GridBagConstraints gbcTxtnewpass2 = new GridBagConstraints();
        gbcTxtnewpass2.gridx = 1;
        gbcTxtnewpass2.gridy = 3;
        center.add(getTxtnewpass2(), gbcTxtnewpass2);

        GridBagConstraints gbcemplbl= new GridBagConstraints();
        gbcemplbl.gridx = 1;
        gbcemplbl.gridy = 4;
        center.add(getemplbl(), gbcemplbl);

        GridBagConstraints gbcBtnChange= new GridBagConstraints();
        gbcBtnChange.gridx = 1;
        gbcBtnChange.gridy = 5;
        //gbcnewpass.insets = new Insets(10, 10, 10, 10);
        center.add(getBtnChange(), gbcBtnChange);

        return center;
    }

    JLabel getUserId()
    {
    	userId = new JLabel("User Id");
    	userId.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
    	return userId;
    }

    JTextField getTxtUserId()
    {
    	getUserId = new JTextField(10);
    	return getUserId;
    }

    JLabel getoldPass()
    {
    	pass = new JLabel("Password");
    	pass.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
    	return pass;
    }

    JTextField getTxtoldpass()
    {
    	getPass = new JTextField(10);
    	return getPass;
    }

    JLabel getnewpass()
    {
    	newPass = new JLabel("New Password");
    	newPass.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
    	return newPass;
    }

    JTextField getTxtnewpass()
    {
    	getNewPass = new JTextField(10);
    	return getNewPass;
    }

    JLabel getnewpass2()
    {
    	newPass2 = new JLabel("Confirm Password");
    	newPass2.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
    	return newPass2;
    }

    JTextField getTxtnewpass2()
    {
    	getNewPass2 = new JTextField(10);
    	return getNewPass2;
    }

    JButton getBtnChange()
    {
    	btnChange = new JButton(" Submit ");
    	btnChange.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
    	btnChange.addActionListener(this);
    	return btnChange;
    }

    JLabel getemplbl()
    {
    	empLbl = new JLabel(" ");
    	return empLbl;
    }

    public void actionPerformed(ActionEvent e)
    {
        if(GetConnection() == true){
            try
            {
                if(e.getSource()==btnChange)
                {
                    String pass="";
                    String User_Id = getUserId.getText().trim();
                    String User_Pass = getPass.getText().trim();
                    String New_Pass = getNewPass.getText().trim();
                    String New_Pass2 = getNewPass2.getText().trim();

                    rs = stmt.executeQuery(" SELECT userid, password FROM admins WHERE UserId = '"+User_Id+"' and Password= '"+User_Pass+"' UNION SELECT userid, password FROM teachers WHERE UserId = '"+User_Id+"' and Password= '"+User_Pass+"' ");
                     
                    while(rs.next())
                    {
                        pass=rs.getString("password");
                    }
                    
                    if(User_Pass.equals("")||New_Pass.equals("")||New_Pass2.equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Fill in Everything!");
                    }
                    else if(!pass.equals(User_Pass))
                    {
                        JOptionPane.showMessageDialog(null, "Old Password is wrong");
                    }
                    else if(!New_Pass.equals(New_Pass2))
                    {
                        JOptionPane.showMessageDialog(null, "Passwords do not match");
                    }
                    else if(New_Pass.length() < 8){
                        JOptionPane.showMessageDialog(rootPane, "New Password Too Short");
                    }
                    else{
                        if ( pass.equals(User_Pass) && New_Pass.equals(New_Pass2) )
                        {

                                try
                                {
                                   stmt.executeUpdate("update admins set password= '" + New_Pass + "' where UserId= '" + User_Id + "' ");
                                   stmt.executeUpdate("update teachers set password= '" + New_Pass + "' where UserId= '" + User_Id + "' ");
                                   JOptionPane.showMessageDialog(null, "Password Changed");
                                   dispose();
                                }
                                catch (Exception ex)
                                {
                                    ex.printStackTrace();
                                    //System.err.print("error");
                                }
                        }
                    }

                    
                    

                }
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }

        }else{
            System.out.println("Not Connected");
        }
    }



    public static void main(String[] args)
    {
        ChangePassword pass = new ChangePassword();
        /* pass.setSize(600, 400);
        pass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pass.setLocationRelativeTo(null);
        pass.setResizable(false);
        pass.getContentPane().setBackground(Color.white);
        pass.setVisible(true);*/
    }
}
