package sp1project;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class LoginPage extends JFrame implements ActionListener{
	private JPanel panel;
	private JLabel labeltitle,labelAL,labelUsername,labelPass,lable,lblNewLabel;
	private JButton exit,login;
	private JTextField userName;
	private JPasswordField pass;
	ConnectionAndGetValueFromDatabase context;
	
	public LoginPage(){
            panel = new JPanel();
            panel.setLayout(null);
            setTitle("Scholarship Management System");
            setSize(Toolkit.getDefaultToolkit().getScreenSize());
            panel.setBackground(Color.LIGHT_GRAY);
            getContentPane().add(panel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            labeltitle=new JLabel("American International University-Bangladesh");
            labeltitle.setBounds(456,203, 453, 29);
            labeltitle.setForeground(Color.blue);
            labeltitle.setFont(new Font("Serif", Font.BOLD, 22));
            panel.add(labeltitle);

            labelAL=new JLabel("User Login");
            labelAL.setFont(new Font("Tahoma", Font.PLAIN, 14));
            labelAL.setBounds(640,301,230,20);
            panel.add(labelAL);

            labelUsername=new JLabel("User Name");
            labelUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
            labelUsername.setBounds(522, 372, 230, 20);
            panel.add(labelUsername);

            userName=new JTextField();
            userName.setBounds(612,369,209,29);
            panel.add(userName);

            labelPass=new JLabel("Password");
            labelPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
            labelPass.setBounds(522,431,180,20);
            panel.add(labelPass);

            pass=new JPasswordField();
            pass.setBounds(612,428,209,29);
            panel.add(pass);

            login=new JButton("LOGIN");
            login.setBackground(Color.WHITE);
            login.setForeground(Color.RED);
            login.setBounds(575,496,124,25);
            panel.add(login);
            login.addActionListener(this);

            exit=new JButton("EXIT");
            exit.setBackground(Color.WHITE);
            exit.setForeground(Color.RED);
            exit.setBounds(722,496,124,25);
            panel.add(exit);
            
            lblNewLabel = new JLabel("New label");
            lblNewLabel.setIcon(new ImageIcon("F:\\programing\\Software Project 1\\Scholarship\\Scholarship\\download.png"));
            lblNewLabel.setBounds(600, 37, 131, 129);
            panel.add(lblNewLabel);
            exit.addActionListener(this);
	}
        
	 public void actionPerformed(ActionEvent e) {
	        String comand = e.getActionCommand();
	        if(comand.equals("LOGIN")){
	        	context = new ConnectionAndGetValueFromDatabase();
	            String username = userName.getText();
	            //userName.setText("admin");
	            String password = pass.getText();
	           // pass.setText("admin");
	            String admin_username = "admin";
	            String admin_password = "admin";
	            Vector<Vector<String>> user = context.getLoginInfo(username,password);
	            if(username.equals(admin_username) && password.equals(admin_password)){
                    this.setVisible(false);
                    AdminPanel sad = new AdminPanel();
                    sad.setVisible(true);
	            }
	            else if(!user.isEmpty()){
	            	 String semester = "";
	            	 int sem = 0;
	            	 String credits = "";
	            	 String isApplied = "";
	            	 String	isApproved = "";
	 	            if(Integer.parseInt(context.ReturnLastestSemesterIDFromRegistration(username)) == Integer.parseInt(context.ReturnOngoingSemester_IDFromsemester())){
	 	            	semester = context.ReturnLastestSemesterIDFromRegistration(username);
	 	            	sem = Integer.parseInt(semester);
		 	            credits = context.ReturnTotalCreditFromCourse_informationSection_informationRegistrationByJoiningTable(username,semester);
		 	            semester = context.ReturnSemester_nameFromSemesterWhereSemester_ID(semester);
		 	            if(context.ReturnIsAppliedFromScholarship_informationWhereStudent_IDAndSemester_ID(username,sem).equals("1")){
		 	            	if(context.ReturnIsApprovedOfAStudent_ID(username).equals("1")){
		 	            		isApproved = "YES";
		 	            		isApplied = "YES";
		 	            	}
		 	            	else{
		 	            		isApplied = "YES";
		 	            		isApproved = "No";
		 	            	}
		 	            }
		 	            else{
		 	            	isApplied = "No";
	 	            		isApproved = "No";
		 	            }
	 	            }
	 	            else{
	 	            	semester = "Unregistered";
	 	            	credits = "0";
	 	            	isApproved = "No";
 	            		isApplied = "No";
	 	            }
	 	            String name = context.ReturnStudent_nameFromStudent_informationWhereStudent_ID(username);
	 	            String cgpa = context.ReturnCgpaFromStudent_academic_information(username);
	 	            String dept = context.ReturnDepartmentFromStudent_academic_informationWhereStudent_ID(username);
	 	           StudentPanelForApplication s = new StudentPanelForApplication(username, semester, name, cgpa, dept, credits, isApplied, isApproved);
                    s.setVisible(true);
                    setVisible(false);
	            }
	            else{
	                JOptionPane.showMessageDialog(null, "Incorrect username/passowrd");
	            }
	        }
	        else if(comand.equals("EXIT")){
	            System.exit(0);
	        }
	    }        
}

