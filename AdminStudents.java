//----------------------------Java Imported Packages---------------------------------------
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import org.jdesktop.swingx.prompt.PromptSupport;

//import sun.org.mozilla.javascript.internal.Token;

public class AdminStudents extends JFrame implements ActionListener, KeyListener, FocusListener, WindowListener {

    private static final long serialVersionUID = 1L;

    JPanel AddPanel, EditPanel, DeletePanel, NorthPanel, genderPanel;
    JPanel StudentsPanel = new JPanel();
    JInternalFrame intFrame;
    //ADD PANEL
    JLabel StudentId, StudentsId;
    JLabel lblUserId, lblBirth, LblFName, LblLName, LblEmail, LblPNO;
    JTextField TxtUserId, TxtFName, TxtLName, TxtEmail, TxtPNO, TxtBirth;
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    JButton AddBtnSubmit;
    ButtonGroup bgGender = new ButtonGroup();
    JButton BtnEnter;
    //JLabel StudentsLabel;
    JLabel StudentsLabel, lblGender;
    JRadioButton jrbMale, jrbFemale;
        //END ADD PANEL

    //START EDIT PANEL
    JLabel EditStudentId, EditStudentsId;
    JLabel EditlblUserId, EditlblPassword, EditLblFName, EditLblLName, EditLblEmail, EditLblPNO, LblView;
    JTextField EditTxtUserId, EditTxtFName, EditTxtLName, EditTxtEmail, EditTxtPNO;
    JPasswordField EditTxtPassword;
    JButton EditBtnSubmit;
    ButtonGroup EditbgGender = new ButtonGroup();
    JButton EditBtnEnter;
    JButton BtnView;
    //JLabel EditStudentsLabel;
    JLabel EditStudentsLabel, EditlblGender;
    JRadioButton EditjrbMale, EditjrbFemale;
        //END EDIT PANEL

    //DELETE PANEL
    JLabel lblAdminId, lblAdminPass, lblStudentId;
    JTextField TxtAdminId, TxtStudentId;
    JPasswordField TxtAdminPass;
    JButton BtnDelete;
    //END DELETE PANEL

    JMenuBar mb;
    JMenu fileMnu, viewMnu, reportMnu, helpMnu;
    JMenuItem mnuNew, mnuSave, mnuFind, mnuEdit, mnuDelete, mnuClose;

    JTabbedPane tabbedPane = new JTabbedPane();

    Connection con;
    Statement stmt;
    ResultSet rs;
    boolean flag = false;

    public AdminStudents() {
        super("Student Panel");
        add(GetNorthPanel(), BorderLayout.NORTH);

        AddPanel = new JPanel();
        EditPanel = new JPanel();
        DeletePanel = new JPanel();

        AddPanel.add(GetAddPanel(), BorderLayout.CENTER);
        EditPanel.add(GetEditPanel(), BorderLayout.CENTER);
        DeletePanel.add(GetDeletePanel(), BorderLayout.CENTER);

        //Add Panel
        tabbedPane.add("   Add   ", AddPanel);

               //Edit Panel
        tabbedPane.add("   Edit   ", EditPanel);

        //Delete Panel
        tabbedPane.add("  Delete  ", DeletePanel);

        tabbedPane.setBorder(null);
        add(tabbedPane);


        addWindowListener(new WindowAdapter() {
		      public void windowOpened(WindowEvent e) {
		        TxtUserId.requestFocus();
                       // EditTxtUserId.requestFocus();
		      }
    });
    }

    public boolean GetConnection() {
        flag = false;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection("jdbc:odbc:Project");
            stmt = con.createStatement();
            flag = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public boolean CloseConnection() {
        flag = false;
        try {
            if (con != null) {
                con.close();
                flag = true;
            }
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }

    public ResultSet GetRecords(String sql) {
        rs = null;
        try {
            rs = stmt.executeQuery(sql);

        } catch (Exception ex) {
            rs = null;
        }
        return rs;
    }

    JPanel GetNorthPanel() {
        NorthPanel = new JPanel();

        ImageIcon titleIcon = new ImageIcon("studentbanner.jpg");

        JLabel title = new JLabel(titleIcon);

        NorthPanel.add(title);

        NorthPanel.setBackground(Color.BLACK);
        return NorthPanel;
    }

//*************************** ADD PANEL TAB 1 ***************************************************
    JPanel GetAddPanel() {
        AddPanel = new JPanel();
        AddPanel.setLayout(new GridBagLayout());
        AddPanel.setBackground(Color.WHITE);

        //START USER ID
        GridBagConstraints UserId = new GridBagConstraints();
        UserId.gridx = 1;
        UserId.gridy = 1;

        UserId.fill = GridBagConstraints.BOTH;
        UserId.insets = new Insets(0, 0, 10, 0);
        AddPanel.add(GetUserId(), UserId);

        GridBagConstraints TxtUserId = new GridBagConstraints();
        TxtUserId.gridx = 2;
        TxtUserId.gridy = 1;

        TxtUserId.insets = new Insets(0, 0, 10, 0);
        AddPanel.add(GetTxtUserId(), TxtUserId);
        //END USER ID

        //START TEACHER F NAME
        GridBagConstraints StudentFName = new GridBagConstraints();
        StudentFName.gridx = 1;
        StudentFName.gridy = 2;

        StudentFName.fill = GridBagConstraints.BOTH;
        StudentFName.insets = new Insets(0, 0, 10, 0);
        AddPanel.add(GetStudentFName(), StudentFName);

        GridBagConstraints TxtStudentFName = new GridBagConstraints();
        TxtStudentFName.gridx = 2;
        TxtStudentFName.gridy = 2;

        TxtStudentFName.insets = new Insets(0, 0, 10, 0);
        AddPanel.add(GetTxtStudentFName(), TxtStudentFName);
                //END TEACHER F NAME

        //START TEACHER L NAME
        GridBagConstraints StudentLName = new GridBagConstraints();
        StudentLName.gridx = 3;
        StudentLName.gridy = 2;
        StudentLName.fill = GridBagConstraints.BOTH;

        StudentLName.insets = new Insets(0, 10, 10, 0);
        AddPanel.add(GetStudentLName(), StudentLName);

        GridBagConstraints TxtTecherLName = new GridBagConstraints();
        TxtTecherLName.gridx = 4;
        TxtTecherLName.gridy = 2;

        TxtTecherLName.insets = new Insets(0, 0, 10, 0);
        AddPanel.add(GetTxtTecherLName(), TxtTecherLName);
                //START TEACHER L NAME

        //START EMAIL
        GridBagConstraints LblStudentEmail = new GridBagConstraints();
        LblStudentEmail.gridx = 1;
        LblStudentEmail.gridy = 3;
        LblStudentEmail.fill = GridBagConstraints.BOTH;

        //LblStudentEmail.insets = new Insets(0, 0, 80, 0);
        AddPanel.add(GetLblStudentEmail(), LblStudentEmail);

        GridBagConstraints TxtTecherEmail = new GridBagConstraints();
        TxtTecherEmail.gridx = 2;
        TxtTecherEmail.gridy = 3;

        //TxtTecherEmail.insets = new Insets(10, 0, 270, 0);
        AddPanel.add(GetTxtTecherEmail(), TxtTecherEmail);
                //END EMAIL

        //START PHONE
        GridBagConstraints LblStudentPNo = new GridBagConstraints();
        LblStudentPNo.gridx = 3;
        LblStudentPNo.gridy = 3;
        LblStudentPNo.fill = GridBagConstraints.BOTH;

        LblStudentPNo.insets = new Insets(0, 10, 0, 0);
        AddPanel.add(LblStudentPNo(), LblStudentPNo);

        GridBagConstraints TxtTecherPNo = new GridBagConstraints();
        TxtTecherPNo.gridx = 4;
        TxtTecherPNo.gridy = 3;

        //TxtTecherPNo.insets = new Insets(10, 0, 0, 0);
        AddPanel.add(GetTxtTecherPNo(), TxtTecherPNo);
                //END PHONE

        //START GENDER
        GridBagConstraints gbcLblGender = new GridBagConstraints();
        gbcLblGender.gridx = 1;//col
        gbcLblGender.gridy = 4;//row
        gbcLblGender.fill = GridBagConstraints.BOTH;
        gbcLblGender.insets = new Insets(5, 0, 0, 0);

        AddPanel.add(GetLblGender(), gbcLblGender);

        GridBagConstraints gbcGenderPanel = new GridBagConstraints();
        gbcGenderPanel.gridx = 2;//col
        gbcGenderPanel.gridy = 4;//row
        gbcGenderPanel.fill = GridBagConstraints.BOTH;
        gbcGenderPanel.insets = new Insets(5, 0, 0, 0);

        AddPanel.add(GetGenderPanel(), gbcGenderPanel);
        //END GENDER
        
        //START BIRTH
        GridBagConstraints birth = new GridBagConstraints();
        birth.gridx = 3;
        birth.gridy = 4;

        birth.fill = GridBagConstraints.BOTH;
        birth.insets = new Insets(10, 10, 10, 0);
        AddPanel.add(GetBirth(), birth);

        GridBagConstraints TxtBirth = new GridBagConstraints();
        TxtBirth.gridx = 4;
        TxtBirth.gridy = 4;

        TxtBirth.insets = new Insets(10, 0, 10, 0);
        AddPanel.add(GetTxtBirth(), TxtBirth);
        //END BIRTH
        
        //START BUTTON SUBMIT
        GridBagConstraints BtnAddSubmit = new GridBagConstraints();
        BtnAddSubmit.gridx = 2;//col
        BtnAddSubmit.gridy = 6;//row
        //gbcTxtAge.fill=GridBagConstraints.BOTH;
        BtnAddSubmit.insets = new Insets(35, 150, 0, 0);

        AddPanel.add(GetBtnAddSubmit(), BtnAddSubmit);
        //END BUTTON SUBMIT

        return AddPanel;
    }

    //START USER ID
    JLabel GetUserId() {
        lblUserId = new JLabel("Student ID   ");
        lblUserId.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        return lblUserId;
    }

    JTextField GetTxtUserId() {
        TxtUserId = new JTextField(10);
        TxtUserId.addKeyListener(this);
       
        return TxtUserId;
    }
        //END USER ID

    //START BIRTH DATE
    JLabel GetBirth() {
        lblBirth = new JLabel("D.O.B   ");
        lblBirth.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        return lblBirth;
    }

    JTextField GetTxtBirth() {
        TxtBirth = new JFormattedTextField(format);
        TxtBirth.setColumns(10);
        TxtBirth.addKeyListener(this);
         PromptSupport.setPrompt("YYYY-MM-DD", TxtBirth);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.HIDE_PROMPT, TxtBirth);
        return TxtBirth;
    }
    //END BIRTH DATE

    //START F NAME
    JLabel GetStudentFName() {
        LblFName = new JLabel("First Name   ");
        LblFName.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        return LblFName;
    }

    JTextField GetTxtStudentFName() {
        TxtFName = new JTextField(10);
        TxtFName.addKeyListener(this);
        return TxtFName;
    }
        //END F NAME

    //START L NAME
    JLabel GetStudentLName() {
        LblLName = new JLabel("Last Name");
        LblLName.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        return LblLName;
    }

    JTextField GetTxtTecherLName() {
        TxtLName = new JTextField(10);
        TxtLName.addKeyListener(this);
        return TxtLName;
    }
        //END L NAME

    //START EMAIL
    JLabel GetLblStudentEmail() {
        LblEmail = new JLabel("Email");
        LblEmail.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        
        return LblEmail;
    }

    JTextField GetTxtTecherEmail() {
        TxtEmail = new JTextField(10);
        TxtEmail.addKeyListener(this);
         PromptSupport.setPrompt("abc@xyz.com", TxtEmail);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.HIDE_PROMPT, TxtEmail);
        return TxtEmail;
    }
        //END EMAIL

    //START PHONE
    JLabel LblStudentPNo() {
        LblPNO = new JLabel("Phone Number  ");
        LblPNO.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        
        return LblPNO;
    }

    JTextField GetTxtTecherPNo() {
        TxtPNO = new JTextField(10);
        TxtPNO.addKeyListener(this);
        PromptSupport.setPrompt("8-13 Char", TxtPNO);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.HIDE_PROMPT, TxtPNO);
        
       // TxtPNO.addKeyListener(new KeyAdapter() {
         //   @Override
           // public void keyReleased(KeyEvent e) {
                String typed = TxtPNO.getText();
                TxtPNO.setText(typed.substring(0, Math.min(13, typed.length())));
            //}
       // });
        
        return TxtPNO;
    }
        //END PHONE

    //START GENDER
    JLabel GetLblGender() {
        lblGender = new JLabel("Gender");
        lblGender.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        return lblGender;
    }

    JPanel GetGenderPanel() {
    	genderPanel = new JPanel();
        genderPanel.setBackground(Color.white);
        
        genderPanel.add(GetJrbMale());
        genderPanel.add(GetJrbFemale());

        return genderPanel;
    }

    JRadioButton GetJrbMale() {
        jrbMale = new JRadioButton("Male");
        jrbMale.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
        jrbMale.addKeyListener(this);
        //jrbMale.addFocusListener(this);
        jrbMale.setBackground(Color.white);
        bgGender.add(jrbMale);

        return jrbMale;
    }

    JRadioButton GetJrbFemale() {
        jrbFemale = new JRadioButton("Female");
        jrbFemale.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
        jrbFemale.addKeyListener(this);
        //jrbFemale.addFocusListener(this);
        jrbFemale.setBackground(Color.white);
        bgGender.add(jrbFemale);

        return jrbFemale;
    }
        //END GENDER

    //START BUTTON SUBMIT
    JButton GetBtnAddSubmit() {
        AddBtnSubmit = new JButton("Submit");
        AddBtnSubmit.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        AddBtnSubmit.addActionListener(this);
        AddBtnSubmit.addKeyListener(this);
        
        
       AddBtnSubmit.registerKeyboardAction(AddBtnSubmit.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_FOCUSED);

		AddBtnSubmit.registerKeyboardAction(AddBtnSubmit.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED); 
        return AddBtnSubmit;
    }
        //END BUTTON SUBMIT

//***************************END OF ADD PANEL TAB 1 ***************************************************



//*************************** DELETE PANEL TAB 3 ***************************************************
    JPanel GetDeletePanel() {

        DeletePanel = new JPanel();
        DeletePanel.setLayout(new GridBagLayout());
        DeletePanel.setBackground(Color.WHITE);

        //START USER ID
        GridBagConstraints AdminId = new GridBagConstraints();
        AdminId.gridx = 1;
        AdminId.gridy = 1;

        AdminId.fill = GridBagConstraints.BOTH;
        AdminId.insets = new Insets(0, 0, 10, 0);
        DeletePanel.add(GetAdminId(), AdminId);

        GridBagConstraints TxtAdminId = new GridBagConstraints();
        TxtAdminId.gridx = 2;
        TxtAdminId.gridy = 1;

        TxtAdminId.insets = new Insets(0, 0, 10, 0);
        DeletePanel.add(GetTxtAdminId(), TxtAdminId);
        //END USER ID

        //START PASSWORD
        GridBagConstraints AdminPass = new GridBagConstraints();
        AdminPass.gridx = 3;
        AdminPass.gridy = 1;

        AdminPass.fill = GridBagConstraints.BOTH;
        AdminPass.insets = new Insets(0, 80, 10, 0);
        DeletePanel.add(GetAdminPass(), AdminPass);

        GridBagConstraints TxtAdminPass = new GridBagConstraints();
        TxtAdminPass.gridx = 4;
        TxtAdminPass.gridy = 1;

        TxtAdminPass.insets = new Insets(0, 0, 10, 0);
        DeletePanel.add(GetTxtAdminPass(), TxtAdminPass);
        //END PASSWORD

        //START TEACHER ID
        GridBagConstraints StudentId = new GridBagConstraints();
        StudentId.gridx = 1;
        StudentId.gridy = 2;

        StudentId.fill = GridBagConstraints.BOTH;
        StudentId.insets = new Insets(0, 0, 10, 0);
        DeletePanel.add(GetStudentId(), StudentId);

        GridBagConstraints TxtStudentId = new GridBagConstraints();
        TxtStudentId.gridx = 2;
        TxtStudentId.gridy = 2;

        TxtStudentId.insets = new Insets(0, 0, 10, 0);
        DeletePanel.add(GetTxtStudentId(), TxtStudentId);
        //END TEACHER ID

        //START BUTTON SUBMIT
        GridBagConstraints BtnDelete = new GridBagConstraints();
        BtnDelete.gridx = 3;//col
        BtnDelete.gridy = 3;//row
        //gbcTxtAge.fill=GridBagConstraints.BOTH;
        BtnDelete.insets = new Insets(80, 0, 0, 80);

        DeletePanel.add(GetBtnDelete(), BtnDelete);
        //END BUTTON SUBMIT

        return DeletePanel;
    }

    //START ADMIN ID
    JLabel GetAdminId() {
        lblAdminId = new JLabel("Admin ID   ");
        lblAdminId.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        return lblAdminId;
    }

    JTextField GetTxtAdminId() {
        TxtAdminId = new JTextField(10);
        TxtAdminId.addKeyListener(this);
        return TxtAdminId;
    }
    //END ADMIN ID

    //START ADMIN PASSWORD
    JLabel GetAdminPass() {
        lblAdminPass = new JLabel("Admin Password   ");
        lblAdminPass.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        return lblAdminPass;
    }

    JPasswordField GetTxtAdminPass() {
        TxtAdminPass = new JPasswordField(10);
        TxtAdminPass.addKeyListener(this);
        return TxtAdminPass;
    }

    //END ADMIN PASSWORD

//START TEACHER ID
    JLabel GetStudentId() {
        lblStudentId = new JLabel("Student ID   ");
        lblStudentId.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        return lblStudentId;
    }

    JTextField GetTxtStudentId() {
        TxtStudentId = new JTextField(10);
        TxtStudentId.addKeyListener(this);
        return TxtStudentId;
    }
    //END TEACHER ID

    //START BUTTON DELETE
    JButton GetBtnDelete() {
        BtnDelete = new JButton("Delete");
        BtnDelete.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        BtnDelete.addActionListener(this);
        BtnDelete.addKeyListener(this);
        
                BtnDelete.registerKeyboardAction(BtnDelete.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_FOCUSED);

		BtnDelete.registerKeyboardAction(BtnDelete.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED);
        
        return BtnDelete;
    }
    //END BUTTON DELETE

//***************************END OF DELETE PANEL TAB 3 ***************************************************




//*************************** EDIT PANEL TAB 2 ***************************************************
    JPanel GetEditPanel() {
        EditPanel = new JPanel();
        EditPanel.setLayout(new GridBagLayout());
        EditPanel.setBackground(Color.WHITE);

        //START VIEW STUDENTS
            GridBagConstraints BtnEditStudentView = new GridBagConstraints();
            BtnEditStudentView.gridx = 4;//col
            BtnEditStudentView.gridy = 1;//row
            //BtnEditStudentView.fill=GridBagConstraints.BOTH;
            BtnEditStudentView.insets = new Insets(0, 0, 30, 0);

            EditPanel.add(GetBtnView(), BtnEditStudentView);
        //END VIEW STUDENTS

        //START USER ID
            GridBagConstraints EditUserId = new GridBagConstraints();
            EditUserId.gridx = 1;
            EditUserId.gridy = 2;

            EditUserId.fill = GridBagConstraints.BOTH;
            EditUserId.insets = new Insets(0, 0, 10, 0);
            EditPanel.add(GetEditUserId(), EditUserId);

            GridBagConstraints EditTxtUserId = new GridBagConstraints();
            EditTxtUserId.gridx = 2;
            EditTxtUserId.gridy = 2;

            EditTxtUserId.insets = new Insets(0, 0, 10, 0);
            EditPanel.add(GetEditTxtUserId(), EditTxtUserId);
        //END USER ID

        //START TEACHER F NAME
            GridBagConstraints EditStudentFName = new GridBagConstraints();
            EditStudentFName.gridx = 1;
            EditStudentFName.gridy = 3;

            EditStudentFName.fill = GridBagConstraints.BOTH;
            EditStudentFName.insets = new Insets(0, 0, 0, 0);
            EditPanel.add(GetEditStudentFName(), EditStudentFName);

            GridBagConstraints EditTxtStudentFName = new GridBagConstraints();
            EditTxtStudentFName.gridx = 2;
            EditTxtStudentFName.gridy = 3;

            EditTxtStudentFName.insets = new Insets(0, 0, 0, 0);
            EditPanel.add(GetEditTxtStudentFName(), EditTxtStudentFName);
        //END TEACHER F NAME

        //START TEACHER L NAME
            GridBagConstraints EditStudentLName = new GridBagConstraints();
            EditStudentLName.gridx = 3;
            EditStudentLName.gridy = 3;
            EditStudentLName.fill = GridBagConstraints.BOTH;

            EditStudentLName.insets = new Insets(0, 10, 0, 0);
            EditPanel.add(GetEditStudentLName(), EditStudentLName);

            GridBagConstraints EditTxtTecherLName = new GridBagConstraints();
            EditTxtTecherLName.gridx = 4;
            EditTxtTecherLName.gridy = 3;

            EditTxtTecherLName.insets = new Insets(0, 0, 0, 0);
            EditPanel.add(GetEditTxtTecherLName(), EditTxtTecherLName);
        //START TEACHER L NAME

        //START EMAIL
            GridBagConstraints EditLblStudentEmail = new GridBagConstraints();
            EditLblStudentEmail.gridx = 1;
            EditLblStudentEmail.gridy = 4;
            EditLblStudentEmail.fill = GridBagConstraints.BOTH;

            EditLblStudentEmail.insets = new Insets(10, 0, 50, 0);
            EditPanel.add(GetEditLblStudentEmail(), EditLblStudentEmail);

            GridBagConstraints EditTxtTecherEmail = new GridBagConstraints();
            EditTxtTecherEmail.gridx = 2;
            EditTxtTecherEmail.gridy = 4;

            EditTxtTecherEmail.insets = new Insets(10, 0, 50, 0);
            EditPanel.add(GetEditTxtTecherEmail(), EditTxtTecherEmail);
        //END EMAIL

        //START PHONE
            GridBagConstraints LblEditStudentPNo = new GridBagConstraints();
            LblEditStudentPNo.gridx = 3;
            LblEditStudentPNo.gridy = 4;
            LblEditStudentPNo.fill = GridBagConstraints.BOTH;

            LblEditStudentPNo.insets = new Insets(10, 0, 50, 0);
            EditPanel.add(LblEditStudentPNo(), LblEditStudentPNo);

            GridBagConstraints EditTxtTecherPNo = new GridBagConstraints();
            EditTxtTecherPNo.gridx = 4;
            EditTxtTecherPNo.gridy = 4;

            EditTxtTecherPNo.insets = new Insets(10, 0, 50, 0);
            EditPanel.add(GetEditTxtTecherPNo(), EditTxtTecherPNo);
        //END PHONE

        //START BUTTON SUBMIT
            GridBagConstraints BtnEditSubmit = new GridBagConstraints();
            BtnEditSubmit.gridx = 2;//col
            BtnEditSubmit.gridy = 5;//row
            //gbcTxtAge.fill=GridBagConstraints.BOTH;
            BtnEditSubmit.insets = new Insets(15, 160, 0, 0);

            EditPanel.add(GetBtnEditSubmit(), BtnEditSubmit);
        //END BUTTON SUBMIT

        return EditPanel;
    }

    //START TEACHER VIEW
        JButton GetBtnView() {
            BtnView = new JButton("<HTML><I><U>View Students<U></I></HTML>");
            BtnView.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            BtnView.addActionListener(this);
            BtnView.setBackground(Color.WHITE);
            return BtnView;
        }
    // END TEACHER VIEW

    //START USER ID
        JLabel GetEditUserId() {
            EditlblUserId = new JLabel("Student ID   ");
            EditlblUserId.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
            return EditlblUserId;
        }

        JTextField GetEditTxtUserId() {
            EditTxtUserId = new JTextField(10);
            EditTxtUserId.addKeyListener(this);
            //EditTxtUserId.addFocusListener(this);
            PromptSupport.setPrompt("ID + Enter", EditTxtUserId);
            PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.HIDE_PROMPT, EditTxtUserId);
            return EditTxtUserId;
        }
    //END USER ID


    //START F NAME
        JLabel GetEditStudentFName() {
        EditLblFName = new JLabel("First Name   ");
        EditLblFName.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        return EditLblFName;
    }

    JTextField GetEditTxtStudentFName() {
        EditTxtFName = new JTextField(10);
        EditTxtFName.addKeyListener(this);
        return EditTxtFName;
    }
    //END F NAME

    //START L NAME
    JLabel GetEditStudentLName() {
        EditLblLName = new JLabel("Last Name");
        EditLblLName.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        return EditLblLName;
    }

    JTextField GetEditTxtTecherLName() {
        EditTxtLName = new JTextField(10);
        EditTxtLName.addKeyListener(this);
        return EditTxtLName;
    }
     //END L NAME

    //START EMAIL
    JLabel GetEditLblStudentEmail() {
        EditLblEmail = new JLabel("Email");
        EditLblEmail.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        return EditLblEmail;
    }

    JTextField GetEditTxtTecherEmail() {
        EditTxtEmail = new JTextField(10);
        EditTxtEmail.addKeyListener(this);
         PromptSupport.setPrompt("abc@xyz.com", EditTxtEmail);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.HIDE_PROMPT, EditTxtEmail);
        return EditTxtEmail;
    }
        //END EMAIL

    //START PHONE
    JLabel LblEditStudentPNo() {
        EditLblPNO = new JLabel("Phone Number  ");
        EditLblPNO.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        return EditLblPNO;
    }

    JTextField GetEditTxtTecherPNo() {
        EditTxtPNO = new JTextField(10);
        EditTxtPNO.addKeyListener(this);
        return EditTxtPNO;
    }
    //END PHONE

    //START BUTTON SUBMIT
    JButton GetBtnEditSubmit() {
        EditBtnSubmit = new JButton(" Submit ");
        EditBtnSubmit.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        EditBtnSubmit.addActionListener(this);
        EditBtnSubmit.addKeyListener(this);
        
                EditBtnSubmit.registerKeyboardAction(EditBtnSubmit.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_FOCUSED);

		EditBtnSubmit.registerKeyboardAction(EditBtnSubmit.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED);
        
        return EditBtnSubmit;
    }

//***************************END OF EDIT PANEL TAB 2 ***************************************************


    @Override
    public void focusGained(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public void windowActivated(WindowEvent arg0) {
    }

    @Override
    public void windowClosed(WindowEvent arg0) {
    }

    @Override
    public void windowClosing(WindowEvent arg0) {
    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {
    }

    @Override
    public void windowIconified(WindowEvent arg0) {
    }

   public void keyPressed(KeyEvent ex)
   	{
   		System.out.println(ex.getKeyCode());
                //START WITH 'S'
                if(TxtUserId.getText().equals("")){
                    TxtUserId.setText("S#");
                }
                if(TxtUserId.getText().equals("S")){
                    TxtUserId.setText("S#");
                }
                
   		if(ex.getKeyCode()==10 && ex.getSource()==TxtUserId)
   		{
   			TxtFName.requestFocus();
   		}
   		else if(ex.getKeyCode()==10 && ex.getSource()==TxtFName)
   		{
   			TxtLName.requestFocus();
   		}
                else if(ex.getKeyCode()==10 && ex.getSource()==TxtLName)
   		{
   			TxtEmail.requestFocus();
   		}
                   else if(ex.getKeyCode()==10 && ex.getSource()==TxtEmail)
   		{
   			TxtPNO.requestFocus();
   		}
                   else if(ex.getKeyCode()==10 && ex.getSource()==TxtPNO)
   		{
   			jrbMale.requestFocus();
   		}
                   else if(ex.getKeyCode()==10 && ex.getSource()==jrbMale)
   		{
   			jrbFemale.requestFocus();
   		}
                   else if(ex.getKeyCode()==10 && ex.getSource()==jrbFemale)
   		{
   			TxtBirth.requestFocus();
   		}
                else if(ex.getKeyCode()==10 && ex.getSource()==TxtBirth)
   		{
   			AddBtnSubmit.requestFocus();
   		}

              if(ex.getKeyCode()==10 && ex.getSource()==EditTxtUserId)
              {
                  EditTxtFName.requestFocus();
              }
              else if(ex.getKeyCode()==10 && ex.getSource()==EditTxtFName)
              {
                  EditTxtLName.requestFocus();
              }
              else if(ex.getKeyCode()==10 && ex.getSource()==EditTxtLName)
              {
                  EditTxtEmail.requestFocus();
              }
              else if(ex.getKeyCode()==10 && ex.getSource()==EditTxtEmail)
              {
                  EditTxtPNO.requestFocus();
              }
               else if(ex.getKeyCode()==10 && ex.getSource()==EditTxtPNO)
              {
                  EditBtnSubmit.requestFocus();
              }
              
            if(ex.getKeyCode()==10 && ex.getSource()==TxtAdminId)
            {
                TxtAdminPass.requestFocus();
            }
              else if(ex.getKeyCode()==10 && ex.getSource()==TxtAdminPass)
              {
                  TxtStudentId.requestFocus();
              }
              else if(ex.getKeyCode()==10 && ex.getSource()==TxtStudentId)
              {
                  BtnDelete.requestFocus();
              }
            }

    @Override
    public void keyReleased(KeyEvent ex) {

        String UserId = EditTxtUserId.getText().trim();
        String sql = " Select * from students where UserId = '" + UserId + "' ";
        String name = "", fname = "", lname="",email="", phone="";
        
        if(GetConnection() == true){
         
        //Phone cannot have more that 13 values    
        String phone1 = TxtPNO.getText();
        TxtPNO.setText(phone1.substring(0, Math.min(13, phone1.length())));       
        
        String phone2 = EditTxtPNO.getText();
        EditTxtPNO.setText(phone2.substring(0, Math.min(13, phone2.length()))); 
        
        //Student ID cannot have more that 13 values    
        String id1 = TxtUserId.getText();
        TxtUserId.setText(id1.substring(0, Math.min(10, id1.length())));       
        
        String id2 = EditTxtUserId.getText();
        EditTxtUserId.setText(id2.substring(0, Math.min(10, id2.length()))); 
        
        String id3 = TxtStudentId.getText();
        TxtStudentId.setText(id3.substring(0, Math.min(10, id3.length()))); 
        
        }
            
            
        if (ex.getKeyCode() == 10) {
            if (GetConnection() == true) {
                
                try {
                    //rs =GetRecords(sql);
                    ResultSet rs = stmt.executeQuery(sql);
                    //rs = stmt.executeQuery(FName);
                    while (rs.next()) {
                        name = rs.getString(1);
                        fname = rs.getString(4);
                        lname = rs.getString(5);
                        email = rs.getString(6);
                        phone = rs.getString(8);
                    }
                    if (name.equalsIgnoreCase(UserId.toString())) {
                        System.out.println("abc");
                        EditTxtFName.setText(fname);
                        EditTxtLName.setText(lname);
                        EditTxtEmail.setText(email);
                        EditTxtPNO.setText(phone);
                    }
                    //stmt.executeUpdate(FName);
                } catch (Exception ex1) {
                    ex1.printStackTrace();
                }
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //START VIEW TEACHER BUTTON IN EDIT PANEL
            if(e.getSource() == BtnView){
                AdminStudentTable a = new AdminStudentTable();
                JScrollPane sp;
                try {
                    sp = new JScrollPane(a.getTable("students"));
                    a.add(sp);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                a.setSize(590, 200);
                a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                a.setVisible(true);
                a.setResizable(true);
                a.setLocationRelativeTo(null);
            }
        //END VIEW TEACHER BUTTON IN EDIT PANEL


        //START SUBMIT BUTTON IN EDIT PANEL
            if(e.getSource() == EditBtnSubmit){

                String eUser = EditTxtUserId.getText().trim();
                String eFname = EditTxtFName.getText().trim();
                String eLname = EditTxtLName.getText().trim();
                String eEmail = EditTxtEmail.getText().toString().trim();
                String ePhone = EditTxtPNO.getText().trim();

                String esql = " UPDATE Students SET Fname='"+eFname+"', LName='"+eLname+"', Email='"+eEmail+"', Phone='"+ePhone+"' WHERE UserId='"+eUser+"' ";

                if(eUser.equals("") || eFname.equals("") || eLname.equals("") || eEmail.equals("") || ePhone.equals(""))
                {
                    JOptionPane.showMessageDialog(rootPane, "Fill In Everything!");
                }
                else
                {
                    if (GetConnection() == true) {

                        try {
                            stmt.executeUpdate(esql);
                            JOptionPane.showMessageDialog(rootPane, "student Updated!");
                            System.out.println("Student Updated!");

                            EditTxtUserId.setText("");
                            EditTxtFName.setText("");
                            EditTxtLName.setText("");
                            EditTxtEmail.setText("");
                            EditTxtPNO.setText("");

                        } catch (Exception ex1) {
                            ex1.printStackTrace();
                            JOptionPane.showMessageDialog(rootPane, "Student does not exist!");
                        }
                        
                }
                    CloseConnection();
                }
            }
        //END SUBMIT BUTTON IN EDIT PANEL



        //START ADD TEACHER BUTTON IN ADD PANEL
            if (e.getSource() == AddBtnSubmit) {
                String UserId = TxtUserId.getText().trim();
                String Birth = TxtBirth.getText().trim();
                String UserType = "Student";
                String FName = TxtFName.getText().trim();
                String LName = TxtLName.getText().trim();
                String Match = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
                String Date = "^(19|20)\\d\\d  [- /.]  (0[1-9]|1[012])  [- /.]  (0[1-9]|[12][0-9]|3[01])$";
                boolean b;
                String Email = TxtEmail.getText().trim().toString();
                String Phone = TxtPNO.getText().trim();

                char Gender = 0;
                if (jrbMale.isSelected()) {
                    Gender = 'M';
                } else if (jrbFemale.isSelected()) {
                    Gender = 'F';
                }


                //EMPTY FIELD VALIDATION
                if (UserId == "" || Birth == "" || FName == "" || LName == "" || Email == "" || Phone == "" || Gender == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Fill In Everything!");

                }
                // EMAIL VALIDATION
                else if (!Email.matches(Match)) {
                    JOptionPane.showMessageDialog(rootPane, "Invalid Email!");
                }
                //PHONE NUMBER VALIDATION
                else if(Phone.length() < 8 || Phone.length() > 14){
                    JOptionPane.showMessageDialog(rootPane, "Invalid Phone Number!");                    
                }
                else {

                    if (GetConnection() == true) {
                        String sql = " Insert into Students Values('" + UserId + "', 'Student', '" + FName + "', '" + LName + "', '" + Email + "', '" + Gender + "', '" + Birth + "', '" + Phone + "') ";

                        try {
                            stmt.executeUpdate(sql);
                            JOptionPane.showMessageDialog(rootPane, "New Student Added!");
                            System.out.println("New Student Added");

                            TxtUserId.setText("");
                            TxtBirth.setText("");
                            TxtFName.setText("");
                            TxtLName.setText("");
                            TxtEmail.setText("");
                            TxtPNO.setText("");

                        } catch (Exception ex1) {
                            ex1.printStackTrace();
                            JOptionPane.showMessageDialog(rootPane, "Cannot enter student!");
                            TxtUserId.setText("");
                            TxtBirth.setText("");
                        }

                        CloseConnection();

                    } else {
                        System.out.println("Not Connected!");
                    }
                }

            }
        //END ADD TEACHER BUTTON IN ADD PANEL



        //START DELETE BUTTON IN DELETE PANEL
            if (e.getSource() == BtnDelete) {
                String AdminId = TxtAdminId.getText().trim();
                String AdminPass = TxtAdminPass.getText().trim();
                String StudentId = TxtStudentId.getText().trim();

                String sql = " Select * from Admins where UserId = '" + AdminId + "' and Password = '" + AdminPass + "' ";

                if(AdminId.equals("") || AdminPass.equals("") || StudentId.equals(""))
                {
                   JOptionPane.showMessageDialog(rootPane, "Fill in Everything!");
                }
                else
                {
                if (GetConnection() == true) {
                    try {
                        rs = GetRecords(sql);
                        int count = 0;
                        String usertype = "";

                        while (rs.next()) {
                            count = count + 1;
                            usertype = rs.getString(3);
                        }

                        if (count == 1) {
                            if (usertype.equalsIgnoreCase("Admin")) {
                                int reply = JOptionPane.showConfirmDialog(null, "Are Your Sure ?", "CONFIRM", JOptionPane.YES_NO_OPTION);

                                if (reply == JOptionPane.YES_OPTION) {
                                    String sql2 = " DELETE FROM students WHERE userId LIKE '" + StudentId + "' ";
                                    try {
                                        stmt.executeUpdate(sql2);
                                        JOptionPane.showMessageDialog(rootPane, StudentId + " DELETED!");
                                        System.out.println("Student Deleted!");

                                        TxtAdminId.setText("");
                                        TxtAdminPass.setText("");
                                        TxtStudentId.setText("");
                                    } catch (Exception ex1) {
                                        ex1.printStackTrace();
                                    }
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Admin Not Found!");
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("Not Connected");
                }

                CloseConnection();
                }
            }
        //END DELETE BUTTON IN DELETE PANEL
    }

    public static void main(String[] args) {

        AdminStudents pr = new AdminStudents();

        pr.setResizable(false);
        pr.setSize(600, 450);
        pr.setLocationRelativeTo(null);
        pr.setVisible(true);
        pr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

}
