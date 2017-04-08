package sp1project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdminPanel extends JFrame implements ActionListener{
	JDesktopPane pane;
	JButton btn, exit_btn, show_approve_btn, refresh_btn, instruction_btn;
	private JTable table;
	
	AdminPanel(){
			
			this.InitializeComponents();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String comand = e.getActionCommand();
			if(comand.equals("Instruction")){
				InstructionOfApproveAStudent in = new InstructionOfApproveAStudent();
				in.setVisible(true);
			}
			else if(comand.equals("Refresh")){
				System.out.print("Hello Refresh");
				
			}
			else if(comand.equals("Show Approved Student")){
				CancelScholarshipOfAStudent ap = new CancelScholarshipOfAStudent();
				this.setVisible(false);
				ap.setVisible(true);
			}
			else if(comand.equals("Show all students")){
				searchForStudentdetails mf=new searchForStudentdetails();
				mf.setVisible(true);
			}
			else if(comand.equals("Logout")){
				LoginPage lg = new LoginPage();
				this.setVisible(false);
				lg.setVisible(true);
			}
		}
		
		private void InitializeComponents(){
			this.pane = new JDesktopPane();
	        this.setSize(1362,732);
	        this.setLocation(0, 0);
	        this.setVisible(true);
	        
	        scholarshipApprovalTable i_one = new scholarshipApprovalTable();
	        this.pane.add(i_one);
	        
	        scholarshipSeatManagedTable i_two = new scholarshipSeatManagedTable();
	        this.pane.add(i_two);
	        
	        this.instruction_btn = new JButton("Instruction");
	        instruction_btn.setBackground(Color.WHITE);
	        instruction_btn.setForeground(Color.BLUE);
	        instruction_btn.setBounds(1105,492,100,25);
            this.pane.add(instruction_btn);
            instruction_btn.addActionListener(this);
	        
	        this.refresh_btn = new JButton("Refresh");
	        refresh_btn.setBackground(Color.WHITE);
	        refresh_btn.setForeground(Color.BLUE);
	        refresh_btn.setBounds(810,542,100,25);
            this.pane.add(refresh_btn);
            refresh_btn.addActionListener(this);
	        	        
	        this.show_approve_btn = new JButton("Show Approved Student");
	        show_approve_btn.setBackground(Color.WHITE);
	        show_approve_btn.setForeground(Color.BLUE);
	        show_approve_btn.setBounds(1052,530,200,25);
            this.pane.add(show_approve_btn);
            show_approve_btn.addActionListener(this);
	        
	        this.btn = new JButton("Show all students");
	        btn.setBackground(Color.WHITE);
	        btn.setForeground(Color.BLUE);
	        btn.setBounds(1078,573,150,25);
            this.pane.add(btn);
            btn.addActionListener(this);
	        
            this.exit_btn = new JButton("Logout");
            exit_btn.setBackground(Color.WHITE);
            exit_btn.setForeground(Color.red);
            exit_btn.setBounds(1105,615,100,25);
            this.pane.add(exit_btn);
            exit_btn.addActionListener(this);
            
	        this.add(pane);
	        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
}
