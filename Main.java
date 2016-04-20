

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JWindow {

	JPanel panel = new JPanel();
	JProgressBar progressBar = new JProgressBar();
        
        JLabel background=new JLabel(new ImageIcon("school.jpg"));

	public Main() {
                background.setLayout(new BorderLayout());
		background.add(progressBar, BorderLayout.SOUTH);
		background.setFocusable(true); 
		progressBar.setStringPainted(true);
                add(background);

		Timer timer = new Timer(50, new ActionListener() {
			int i = 0;

                        @Override
			public void actionPerformed(ActionEvent evt) {
				progressBar.setValue(i);
				i++;
				panel.repaint();
				if (i == 100) {
					((Timer) evt.getSource()).stop();
					dispose();
                                        
                                        Login login = new Login();
                                        login.setVisible(true);
                                        login.setResizable(false);
                                        login.setSize(600,400);
                                        login.setLocationRelativeTo(null);
                                        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                        //new Admin();
				}
			}
		});
		timer.start();
	}

	public static void main(String[] args) {
		
            Main w = new Main();
            w.setSize(600, 424);
            w.setLocationRelativeTo(null);
            w.setVisible(true);
            
		
	}
}
