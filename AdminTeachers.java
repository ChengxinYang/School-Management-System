//----------------------------Java Imported Packages---------------------------------------
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.regex.Pattern;
import org.jdesktop.swingx.prompt.PromptSupport;

//import sun.org.mozilla.javascript.internal.Token;

public class AdminTeachers extends JFrame implements ActionListener, KeyListener, FocusListener, WindowListener {

    private static final long serialVersionUID = 1L;

    JPanel AddPanel, EditPanel, DeletePanel, NorthPanel, genderPanel;
    JPanel StudentsPanel = new JPanel();
    JInternalFrame intFrame;
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    //ADD PANEL
    JLabel StudentId, TeachersId;
    JLabel lblUserId, lblPassword, LblFName, LblLName, LblEmail, LblPNO, LblBirth, LblSubject;
    JTextField TxtUserId, TxtFName, TxtLName, TxtEmail, TxtPNO, TxtBirth;
    JPasswordField TxtPassword;
    JButton AddBtnSubmit;
    ButtonGroup bgGender = new ButtonGroup();
    JButton BtnEnter;
    JLabel TeachersLabel;
    JLabel StudentsLabel, lblGender;
    JRadioButton jrbMale, jrbFemale;
    JComboBox jbcSubjects;
    //END ADD PANEL

    //START EDIT PANEL
    JLabel EditStudentId, EditTeachersId;
    JLabel EditlblUserId, EditlblPassword, EditLblFName, EditLblLName, EditLblEmail, EditLblPNO, LblView;
    JTextField EditTxtUserId, EditTxtFName, EditTxtLName, EditTxtEmail, EditTxtPNO;
    JPasswordField EditTxtPassword;
    JButton EditBtnSubmit;
    ButtonGroup EditbgGender = new ButtonGroup();
    JButton EditBtnEnter;
    JButton BtnView;
    JLabel EditTeachersLabel;
    JLabel EditStudentsLabel, EditlblGender;
    JRadioButton EditjrbMale, EditjrbFemale;
        //END EDIT PANEL

    //DELETE PANEL
    JLabel lblAdminId, lblAdminPass, lblTeacherId;
    JTextField TxtAdminId, TxtTeacherId;
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

    public AdminTeachers() {
        super("Teacher Panel");
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

        ImageIcon titleIcon = new ImageIcon("teacherbanner.jpg");

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

        //START PASSWORD
        GridBagConstraints Password = new GridBagConstraints();
        Password.gridx = 3;
        Password.gridy = 1;

        Password.fill = GridBagConstraints.BOTH;
        Password.insets = new Insets(0, 10, 10, 0);
        AddPanel.add(GetPassword(), Password);

        GridBagConstraints TxtPassword = new GridBagConstraints();
        TxtPassword.gridx = 4;
        TxtPassword.gridy = 1;

        TxtPassword.insets = new Insets(0, 0, 10, 0);
        AddPanel.add(GetTxtPassword(), TxtPassword);
                //END PASSWORD

        //START TEACHER F NAME
        GridBagConstraints TeacherFName = new GridBagConstraints();
        TeacherFName.gridx = 1;
        TeacherFName.gridy = 2;

        TeacherFName.fill = GridBagConstraints.BOTH;
        TeacherFName.insets = new Insets(0, 0, 10, 0);
        AddPanel.add(GetTeacherFName(), TeacherFName);

        GridBagConstraints TxtTeacherFName = new GridBagConstraints();
        TxtTeacherFName.gridx = 2;
        TxtTeacherFName.gridy = 2;

        TxtTeacherFName.insets = new Insets(0, 0, 10, 0);
        AddPanel.add(GetTxtTeacherFName(), TxtTeacherFName);
                //END TEACHER F NAME

        //START TEACHER L NAME
        GridBagConstraints TeacherLName = new GridBagConstraints();
        TeacherLName.gridx = 3;
        TeacherLName.gridy = 2;
        TeacherLName.fill = GridBagConstraints.BOTH;

        TeacherLName.insets = new Insets(0, 10, 10, 0);
        AddPanel.add(GetTeacherLName(), TeacherLName);

        GridBagConstraints TxtTecherLName = new GridBagConstraints();
        TxtTecherLName.gridx = 4;
        TxtTecherLName.gridy = 2;

        TxtTecherLName.insets = new Insets(0, 0, 10, 0);
        AddPanel.add(GetTxtTecherLName(), TxtTecherLName);
                //START TEACHER L NAME

        //START EMAIL
        GridBagConstraints LblTeacherEmail = new GridBagConstraints();
        LblTeacherEmail.gridx = 1;
        LblTeacherEmail.gridy = 3;
        LblTeacherEmail.fill = GridBagConstraints.BOTH;

        //LblTeacherEmail.insets = new Insets(0, 0, 80, 0);
        AddPanel.add(GetLblTeacherEmail(), LblTeacherEmail);

        GridBagConstraints TxtTecherEmail = new GridBagConstraints();
        TxtTecherEmail.gridx = 2;
        TxtTecherEmail.gridy = 3;

        //TxtTecherEmail.insets = new Insets(10, 0, 270, 0);
        AddPanel.add(GetTxtTecherEmail(), TxtTecherEmail);
                //END EMAIL

        //START PHONE
        GridBagConstraints LblTeacherPNo = new GridBagConstraints();
        LblTeacherPNo.gridx = 3;
        LblTeacherPNo.gridy = 3;
        LblTeacherPNo.fill = GridBagConstraints.BOTH;

        LblTeacherPNo.insets = new Insets(0, 10, 0, 0);
        AddPanel.add(LblTeacherPNo(), LblTeacherPNo);

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

        //START SUBJECT
        GridBagConstraints Subject=new GridBagConstraints();
        Subject.gridx=1;//col
	Subject.gridy=6;//row
        Subject.insets = new Insets(0, 0, 0, 40);
	AddPanel.add(GetLblSubject(), Subject);

	GridBagConstraints JbcSubject=new GridBagConstraints();
	JbcSubject.gridx=2;//col
	JbcSubject.gridy=6;//row
        JbcSubject.insets = new Insets(0, 0, 0, 20);
	AddPanel.add(GetJcbSubject(), JbcSubject);

        //END SUBJECT
        
        //START BUTTON SUBMIT
        GridBagConstraints BtnAddSubmit = new GridBagConstraints();
        BtnAddSubmit.gridx = 2;//col
        BtnAddSubmit.gridy = 7;//row
        //gbcTxtAge.fill=GridBagConstraints.BOTH;
        BtnAddSubmit.insets = new Insets(55, 160, 0, 0);

        AddPanel.add(GetBtnAddSubmit(), BtnAddSubmit);
        //END BUTTON SUBMIT

        return AddPanel;
    }

    //START USER ID
    JLabel GetUserId() {
        lblUserId = new JLabel("Teacher ID   ");
        lblUserId.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        return lblUserId;
    }

    JTextField GetTxtUserId() {
        TxtUserId = new JTextField(10);
        TxtUserId.addKeyListener(this);
       
        return TxtUserId;
    }
        //END USER ID

    //START USER ID
    JLabel GetPassword() {
        lblPassword = new JLabel("Password   ");
        lblPassword.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        return lblPassword;
    }

    JPasswordField GetTxtPassword() {
        TxtPassword = new JPasswordField(10);
        TxtPassword.addKeyListener(this);
         PromptSupport.setPrompt("Minimum 8 Char", TxtPassword);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.HIDE_PROMPT, TxtPassword);
        return TxtPassword;
    }
        //END USER ID

    //START F NAME
    JLabel GetTeacherFName() {
        LblFName = new JLabel("First Name   ");
        LblFName.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        return LblFName;
    }

    JTextField GetTxtTeacherFName() {
        TxtFName = new JTextField(10);
        TxtFName.addKeyListener(this);
        return TxtFName;
    }
        //END F NAME

    //START L NAME
    JLabel GetTeacherLName() {
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
    JLabel GetLblTeacherEmail() {
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
    JLabel LblTeacherPNo() {
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

    //START BIRTH DATE
    JLabel GetBirth() {
        LblBirth = new JLabel("Date Of Birth  ");
        LblBirth.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        return LblBirth;
    }

    JTextField  GetTxtBirth() {
        TxtBirth = new JFormattedTextField(format);
        TxtBirth.setColumns(10);
        TxtBirth.addKeyListener(this);
         PromptSupport.setPrompt("YYYY-MM-DD", TxtBirth);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.HIDE_PROMPT, TxtBirth);
        return TxtBirth;
    }
    //END BIRTH DATE    
    
    //START SUBJECT
    JLabel GetLblSubject()
    {
    LblSubject=new JLabel("Subject");
        LblSubject.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
    return LblSubject;
    }

    JComboBox GetJcbSubject()
    {
	jbcSubjects=new JComboBox();
	jbcSubjects.addItem("English");
	jbcSubjects.addItem("Math");
	jbcSubjects.addItem("Science");
	jbcSubjects.addItem("IT");
        jbcSubjects.addItem("History");
        jbcSubjects.addItem("Geography");
        jbcSubjects.setBackground(Color.white);
        jbcSubjects.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
	return jbcSubjects;
    }
    //END SUBJECT
    
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
        GridBagConstraints TeacherId = new GridBagConstraints();
        TeacherId.gridx = 1;
        TeacherId.gridy = 2;

        TeacherId.fill = GridBagConstraints.BOTH;
        TeacherId.insets = new Insets(0, 0, 10, 0);
        DeletePanel.add(GetTeacherId(), TeacherId);

        GridBagConstraints TxtTeacherId = new GridBagConstraints();
        TxtTeacherId.gridx = 2;
        TxtTeacherId.gridy = 2;

        TxtTeacherId.insets = new Insets(0, 0, 10, 0);
        DeletePanel.add(GetTxtTeacherId(), TxtTeacherId);
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
    JLabel GetTeacherId() {
        lblTeacherId = new JLabel("Teacher ID   ");
        lblTeacherId.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        return lblTeacherId;
    }

    JTextField GetTxtTeacherId() {
        TxtTeacherId = new JTextField(10);
        TxtTeacherId.addKeyListener(this);
        return TxtTeacherId;
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

        //START VIEW TEACHERS
            GridBagConstraints BtnEditTeacherView = new GridBagConstraints();
            BtnEditTeacherView.gridx = 4;//col
            BtnEditTeacherView.gridy = 1;//row
            //BtnEditTeacherView.fill=GridBagConstraints.BOTH;
            BtnEditTeacherView.insets = new Insets(0, 0, 30, 0);

            EditPanel.add(GetBtnView(), BtnEditTeacherView);
        //END VIEW TEACHERS

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
            GridBagConstraints EditTeacherFName = new GridBagConstraints();
            EditTeacherFName.gridx = 1;
            EditTeacherFName.gridy = 3;

            EditTeacherFName.fill = GridBagConstraints.BOTH;
            EditTeacherFName.insets = new Insets(0, 0, 0, 0);
            EditPanel.add(GetEditTeacherFName(), EditTeacherFName);

            GridBagConstraints EditTxtTeacherFName = new GridBagConstraints();
            EditTxtTeacherFName.gridx = 2;
            EditTxtTeacherFName.gridy = 3;

            EditTxtTeacherFName.insets = new Insets(0, 0, 0, 0);
            EditPanel.add(GetEditTxtTeacherFName(), EditTxtTeacherFName);
        //END TEACHER F NAME

        //START TEACHER L NAME
            GridBagConstraints EditTeacherLName = new GridBagConstraints();
            EditTeacherLName.gridx = 3;
            EditTeacherLName.gridy = 3;
            EditTeacherLName.fill = GridBagConstraints.BOTH;

            EditTeacherLName.insets = new Insets(0, 10, 0, 0);
            EditPanel.add(GetEditTeacherLName(), EditTeacherLName);

            GridBagConstraints EditTxtTecherLName = new GridBagConstraints();
            EditTxtTecherLName.gridx = 4;
            EditTxtTecherLName.gridy = 3;

            EditTxtTecherLName.insets = new Insets(0, 0, 0, 0);
            EditPanel.add(GetEditTxtTecherLName(), EditTxtTecherLName);
        //START TEACHER L NAME

        //START EMAIL
            GridBagConstraints EditLblTeacherEmail = new GridBagConstraints();
            EditLblTeacherEmail.gridx = 1;
            EditLblTeacherEmail.gridy = 4;
            EditLblTeacherEmail.fill = GridBagConstraints.BOTH;

            EditLblTeacherEmail.insets = new Insets(10, 0, 50, 0);
            EditPanel.add(GetEditLblTeacherEmail(), EditLblTeacherEmail);

            GridBagConstraints EditTxtTecherEmail = new GridBagConstraints();
            EditTxtTecherEmail.gridx = 2;
            EditTxtTecherEmail.gridy = 4;

            EditTxtTecherEmail.insets = new Insets(10, 0, 50, 0);
            EditPanel.add(GetEditTxtTecherEmail(), EditTxtTecherEmail);
        //END EMAIL

        //START PHONE
            GridBagConstraints LblEditTeacherPNo = new GridBagConstraints();
            LblEditTeacherPNo.gridx = 3;
            LblEditTeacherPNo.gridy = 4;
            LblEditTeacherPNo.fill = GridBagConstraints.BOTH;

            LblEditTeacherPNo.insets = new Insets(10, 0, 50, 0);
            EditPanel.add(LblEditTeacherPNo(), LblEditTeacherPNo);

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
            BtnView = new JButton("<HTML><I><U>View Teachers<U></I></HTML>");
            BtnView.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            BtnView.addActionListener(this);
            BtnView.setBackground(Color.WHITE);
            return BtnView;
        }
    // END TEACHER VIEW

    //START USER ID
        JLabel GetEditUserId() {
            EditlblUserId = new JLabel("Teacher ID   ");
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
        JLabel GetEditTeacherFName() {
        EditLblFName = new JLabel("First Name   ");
        EditLblFName.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        return EditLblFName;
    }

    JTextField GetEditTxtTeacherFName() {
        EditTxtFName = new JTextField(10);
        EditTxtFName.addKeyListener(this);
        return EditTxtFName;
    }
    //END F NAME

    //START L NAME
    JLabel GetEditTeacherLName() {
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
    JLabel GetEditLblTeacherEmail() {
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
    JLabel LblEditTeacherPNo() {
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
                //START WITH 'T'
                if(TxtUserId.getText().equals("")){
                    TxtUserId.setText("T#");
                }
                if(TxtUserId.getText().equals("T")){
                    TxtUserId.setText("T#");
                }
                
   		System.out.println(ex.getKeyCode());
                            
   		if(ex.getKeyCode()==10 && ex.getSource()==TxtUserId)
   		{
   			TxtPassword.requestFocus();
   		}
   		else if(ex.getKeyCode()==10 && ex.getSource()==TxtPassword)
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
                  TxtTeacherId.requestFocus();
              }
              else if(ex.getKeyCode()==10 && ex.getSource()==TxtTeacherId)
              {
                  BtnDelete.requestFocus();
              }
            }

    @Override
    public void keyReleased(KeyEvent ex) {

        String UserId = EditTxtUserId.getText().trim();
        String sql = " Select * from teachers where UserId = '" + UserId + "' ";
        String name = "", fname = "", lname="",email="", phone="";
        
        if(GetConnection() == true){
         
        //Phone cannot have more that 13 values    
        String phone1 = TxtPNO.getText();
        TxtPNO.setText(phone1.substring(0, Math.min(13, phone1.length())));       
        
        String phone2 = EditTxtPNO.getText();
        EditTxtPNO.setText(phone2.substring(0, Math.min(13, phone2.length()))); 
        
        //Teacher ID cannot have more that 10 values    
        String id1 = TxtUserId.getText();
        TxtUserId.setText(id1.substring(0, Math.min(10, id1.length())));       
        
        String id2 = EditTxtUserId.getText();
        EditTxtUserId.setText(id2.substring(0, Math.min(10, id2.length()))); 
        
        String id3 = TxtTeacherId.getText();
        TxtTeacherId.setText(id3.substring(0, Math.min(10, id3.length()))); 
        
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
                        phone = rs.getString(9);
                    }
                    if (name.equalsIgnoreCase(UserId.toString())) {
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
                AdminTeacherTable a = new AdminTeacherTable();
                JScrollPane sp;
                try {
                    sp = new JScrollPane(a.getTable("teachers"));
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

                String esql = " UPDATE Teachers SET Fname='"+eFname+"', LName='"+eLname+"', Email='"+eEmail+"', Phone='"+ePhone+"' WHERE UserId='"+eUser+"' ";

                if(eUser.equals("") || eFname.equals("") || eLname.equals("") || eEmail.equals("") || ePhone.equals(""))
                {
                    JOptionPane.showMessageDialog(rootPane, "Fill In Everything!");
                }
                else
                {
                    if (GetConnection() == true) {

                        try {
                            stmt.executeUpdate(esql);
                            JOptionPane.showMessageDialog(rootPane, "teacher Updated!");
                            System.out.println("Teacher Updated!");

                            EditTxtUserId.setText("");
                            EditTxtFName.setText("");
                            EditTxtLName.setText("");
                            EditTxtEmail.setText("");
                            EditTxtPNO.setText("");

                        } catch (Exception ex1) {
                            ex1.printStackTrace();
                            JOptionPane.showMessageDialog(rootPane, "Teacher does not exist!");
                        }
                        
                }
                    CloseConnection();
                }
            }
        //END SUBMIT BUTTON IN EDIT PANEL



        //START ADD TEACHER BUTTON IN ADD PANEL
            if (e.getSource() == AddBtnSubmit) {
                String UserId = TxtUserId.getText().trim();
                String Password = TxtPassword.getText().trim();
                String UserType = "Teacher";
                String FName = TxtFName.getText().trim();
                String LName = TxtLName.getText().trim();
                String Match = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
                String Date = "^(19|20)\\d\\d  [- /.]  (0[1-9]|1[012])  [- /.]  (0[1-9]|[12][0-9]|3[01])$";
                boolean b;
                String Email = TxtEmail.getText().trim().toString();
                String Birth = TxtBirth.getText().trim();
                String Phone = TxtPNO.getText().trim();
                Object Subject = jbcSubjects.getSelectedItem().toString();

                char Gender = 0;
                if (jrbMale.isSelected()) {
                    Gender = 'M';
                } else if (jrbFemale.isSelected()) {
                    Gender = 'F';
                }


                //EMPTY FIELD VALIDATION
                if (UserId == "" || Password == "" || FName == "" || LName == "" || Email == "" || Phone == "" || Birth == "" || Gender == 0 || Subject == "") {
                    JOptionPane.showMessageDialog(rootPane, "Fill In Everything!");

                }
                // PASSWORD VALIDATION
                else if(Password.length() < 8){                      
                    if(TxtPassword.getText().length() < 8){
                        TxtPassword.setBackground(Color.red);               
                    }
                    else{
                        TxtPassword.setBackground(Color.white);               
                    }
                    JOptionPane.showMessageDialog(rootPane, "Password Too Short");
                    TxtPassword.setText("");
                }
                // EMAIL VALIDATION
                else if (!Email.matches(Match)) {
                    JOptionPane.showMessageDialog(rootPane, "Invalid Email!");
                }
                //DATE VALIDATION
                //else if(!Birth.matches(Date)){
                  //  JOptionPane.showMessageDialog(rootPane, "Invalid Date!");
                //}
                //PHONE NUMBER VALIDATION
                else if(Phone.length() < 8 || Phone.length() > 14){
                    JOptionPane.showMessageDialog(rootPane, "Invalid Phone Number!");                    
                }
                else {

                    if (GetConnection() == true) {
                        String sql = " Insert into Teachers Values('" + UserId + "','" + Password + "','Teacher', '" + FName + "', '" + LName + "', '" + Email + "', '" + Gender + "', '"+Birth+"', '" + Phone + "', '"+Subject+"') ";

                        try {
                            stmt.executeUpdate(sql);
                            JOptionPane.showMessageDialog(rootPane, "New Teacher Added!");
                            System.out.println("New Teacher Added");

                            TxtUserId.setText("");
                            TxtPassword.setText("");
                            TxtFName.setText("");
                            TxtLName.setText("");
                            TxtEmail.setText("");
                            TxtPNO.setText("");
                            TxtBirth.setText("");

                        } catch (Exception ex1) {
                            ex1.printStackTrace();
                            JOptionPane.showMessageDialog(rootPane, "Cannot enter teacher!");
                            TxtUserId.setText("");
                            TxtPassword.setText("");
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
                String TeacherId = TxtTeacherId.getText().trim();

                String sql = " Select * from Admins where UserId = '" + AdminId + "' and Password = '" + AdminPass + "' ";

                if(AdminId.equals("") || AdminPass.equals("") || TeacherId.equals(""))
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
                                    String sql2 = " DELETE FROM teachers WHERE userId LIKE '" + TeacherId + "' ";
                                    try {
                                        stmt.executeUpdate(sql2);
                                        JOptionPane.showMessageDialog(rootPane, TeacherId + " DELETED!");
                                        System.out.println("Teacher Deleted!");

                                        TxtAdminId.setText("");
                                        TxtAdminPass.setText("");
                                        TxtTeacherId.setText("");
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

        AdminTeachers pr = new AdminTeachers();
        
        pr.setResizable(false);
        pr.setSize(600, 480);
        pr.setLocationRelativeTo(null);
        pr.setVisible(true);
        pr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



    }

}
