package sp1project;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

public class StudentPanelForApplication extends JFrame implements ActionListener {
	private JPanel panel;
	private JLabel lblNewLabel_2;
	public JComboBox comboscholar;
	ConnectionAndGetValueFromDatabase context;
	static String  id = null;
	static String semester = null;
	static String names = null;
	static String cgpas = null;
	static String depts = null;
	static String credits = null;
	static String isApplieds = null;
	static String isApproveds = null;
	String os;
	int oh;
	
	 public StudentPanelForApplication(String id, String semester, String names, String cgpas, String depts, String credit, String isApplieds, String isApproveds){ 
		 this.id = id;
		 this.semester = semester;
		 this.names = names;
		 this.cgpas = cgpas;
		 this.depts = depts;
		 this.credits = credit;
		 this.isApplieds = isApplieds;
		 this.isApproveds = isApproveds;
		 
		 panel = new JPanel();
         panel.setLayout(null);
         setTitle("Student Panel");
         setSize(Toolkit.getDefaultToolkit().getScreenSize());
         panel.setBackground(Color.LIGHT_GRAY);
         getContentPane().add(panel);
         
         JLabel labelapply = new JLabel("Apply For Scholarship");
         labelapply.setForeground(Color.BLACK);
         labelapply.setFont(new Font("Tahoma", Font.PLAIN, 14));
         labelapply.setBounds(618, 200, 179, 24);
         panel.add(labelapply);
         
         JLabel sname = new JLabel("Student Name");
         sname.setForeground(Color.BLACK);
         sname.setFont(new Font("Tahoma", Font.PLAIN, 12));
         sname.setBounds(515, 241, 86, 24);
         panel.add(sname);
         
         JLabel ScholarType = new JLabel("Choose Scholarship Type");
         ScholarType.setForeground(Color.BLACK);
         ScholarType.setFont(new Font("Tahoma", Font.PLAIN, 12));
         ScholarType.setBounds(515, 520, 145, 24);
         panel.add(ScholarType);
         
         comboscholar = new JComboBox();
         comboscholar.setBounds(684, 519, 209, 29);
         panel.add(comboscholar);
         
         JButton btnsubmit = new JButton("APPLY");
         btnsubmit.setForeground(Color.RED);
         btnsubmit.setBounds(618, 605, 124, 24);
         btnsubmit.addActionListener(this);
         panel.add(btnsubmit);
         
         JButton btncancle = new JButton("Logout");
         btncancle.setForeground(Color.RED);
         btncancle.setBounds(768, 605, 124, 24);
         btncancle.addActionListener(this);
         panel.add(btncancle);
         
         JLabel lblNewLabel = new JLabel("American International University-Bangladesh");
         lblNewLabel.setForeground(Color.BLUE);
         lblNewLabel.setFont(new Font("Serif", Font.BOLD, 22));
         lblNewLabel.setBounds(464, 160, 452, 29);
         panel.add(lblNewLabel);
         
         JLabel lblNewLabel_1 = new JLabel("");
         lblNewLabel_1.setIcon(new ImageIcon("F:\\programing\\Software Project 1\\Scholarship\\Scholarship\\download.png"));
         lblNewLabel_1.setBounds(618, 11, 129, 129);
         panel.add(lblNewLabel_1);
         
         JLabel sid = new JLabel("Student ID");
         sid.setForeground(Color.BLACK);
         sid.setFont(new Font("Tahoma", Font.PLAIN, 12));
         sid.setBounds(515, 276, 75, 24);
         panel.add(sid);
         
         JLabel cgpa = new JLabel("CGPA");
         cgpa.setForeground(Color.BLACK);
         cgpa.setFont(new Font("Tahoma", Font.PLAIN, 12));
         cgpa.setBounds(515, 311, 75, 24);
         panel.add(cgpa);
         
         JLabel dept = new JLabel("Department");
         dept.setForeground(Color.BLACK);
         dept.setFont(new Font("Tahoma", Font.PLAIN, 12));
         dept.setBounds(515, 346, 76, 24);
         panel.add(dept);
         
         JLabel csemester = new JLabel("Current Semester");
         csemester.setForeground(Color.BLACK);
         csemester.setFont(new Font("Tahoma", Font.PLAIN, 12));
         csemester.setBounds(515, 381, 105, 24);
         panel.add(csemester);
         
         JLabel tcredit = new JLabel("Taken Credit");
         tcredit.setForeground(Color.BLACK);
         tcredit.setFont(new Font("Tahoma", Font.PLAIN, 12));
         tcredit.setBounds(515, 416, 86, 24);
         panel.add(tcredit);
         
         JLabel stu_name = new JLabel();
         stu_name.setFont(new Font("Tahoma", Font.PLAIN, 12));
         stu_name.setBounds(684, 241, 163, 20);
         stu_name.setText(this.names);
         panel.add(stu_name);
         
         JLabel stu_id = new JLabel();
         stu_id.setFont(new Font("Tahoma", Font.PLAIN, 12));
         stu_id.setBounds(684, 276, 134, 18);
         stu_id.setText(this.id);
         panel.add(stu_id);
         
         JLabel stu_cgpa = new JLabel();
         stu_cgpa.setFont(new Font("Tahoma", Font.PLAIN, 12));
         stu_cgpa.setBounds(684, 311, 63, 18);
         stu_cgpa.setText(this.cgpas);
         panel.add(stu_cgpa);
         
         JLabel stu_dept = new JLabel();
         stu_dept.setFont(new Font("Tahoma", Font.PLAIN, 12));
         stu_dept.setBounds(684, 346, 86, 18);
         stu_dept.setText(this.depts);
         panel.add(stu_dept);
         
         JLabel stu_csem = new JLabel();
         stu_csem.setFont(new Font("Tahoma", Font.PLAIN, 12));
         stu_csem.setBounds(684, 381, 208, 18);
         stu_csem.setText(this.semester);
         panel.add(stu_csem);
         
         JLabel stu_tcredit = new JLabel();
         stu_tcredit.setFont(new Font("Tahoma", Font.PLAIN, 12));
         stu_tcredit.setBounds(679, 416, 63, 22);
        stu_tcredit.setText(this.credits);
         panel.add(stu_tcredit);
         
         JLabel isApplied = new JLabel("Applied");
         isApplied.setForeground(Color.BLACK);
         isApplied.setFont(new Font("Tahoma", Font.PLAIN, 12));
         isApplied.setBounds(515, 451, 75, 20);
         panel.add(isApplied);
         
         lblNewLabel_2 = new JLabel();
         lblNewLabel_2.setForeground(Color.BLACK);
         lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
         lblNewLabel_2.setBounds(684, 449, 58, 22);
         lblNewLabel_2.setText(this.isApplieds);
         panel.add(lblNewLabel_2);
         
         JLabel isApproved = new JLabel("Approved");
         isApproved.setForeground(Color.BLACK);
         isApproved.setFont(new Font("Tahoma", Font.PLAIN, 12));
         isApproved.setBounds(515, 482, 75, 18);
         panel.add(isApproved);
         
         JLabel lblNewLabel_3 = new JLabel();
         lblNewLabel_3.setForeground(Color.BLACK);
         lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
         lblNewLabel_3.setBounds(683, 482, 59, 20);
         lblNewLabel_3.setText(this.isApproveds);
         panel.add(lblNewLabel_3);
         
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.comboValue();
	 }
	 public void comboValue(){
		    context = new ConnectionAndGetValueFromDatabase();
		    Vector<String> col = context.getScholarshipType();
		    for(int i = 0 ; i<col.size(); i++){
		    	comboscholar.addItem(col.get(i));
		    }
	 }
	@Override
	public void actionPerformed(ActionEvent event) {
		String com = event.getActionCommand();
        if(com.equals("APPLY")){
        	context = new ConnectionAndGetValueFromDatabase();
        	String sel = comboscholar.getSelectedItem().toString(); // selected scholarship name
            	Vector<String> pins = context.ReturnScholarshipIDFromScholarship_type(sel);
            	String sem_id = context.ReturnLastestSemesterIDFromRegistration(id);
            	String[] pins_arr = pins.toArray(new String[0]);
            	String s_pins = pins_arr[0];
            	int scholar_ID = Integer.parseInt(s_pins);
            	int sems_ID = Integer.parseInt(sem_id);
            	String schol_name = context.ReturnScholarship_NameFromScholarship_typeWhereScholarship_ID(scholar_ID);
            	if(sem_id.equals(context.ReturnOngoingSemester_IDFromsemester())){
            			if(schol_name.equals(context.ReturnSpecial_sectionFromStudent_informationWhereStudent_ID(id))){
                    		if( context.ReturnIsAppliedFromScholarship_informationWhereStudent_IDAndSemester_ID(id, sems_ID) == "0"){
                    			context.InsertIntoScholarship_infomation(id, scholar_ID, sems_ID);
                    			this.lblNewLabel_2.setText("Yes");
                    			this.panel.add(lblNewLabel_2);
                            	JOptionPane.showMessageDialog(null, "Scholarship Applied");
                    		}
                    		else{
                    			JOptionPane.showMessageDialog(null, "Already Applied");
                    		}
                    	}
                    	else if(scholar_ID == 2){
                    		if( context.ReturnIsAppliedFromScholarship_informationWhereStudent_IDAndSemester_ID(id, sems_ID) == "0"){
                    			context.InsertIntoScholarship_infomation(id, scholar_ID, sems_ID);
                            	JOptionPane.showMessageDialog(null, "Scholarship Applied");
                    		}
                    		else{
                    			JOptionPane.showMessageDialog(null, "Already Applied");
                    		}
                    		
                    	}
                    	else{
                    		JOptionPane.showMessageDialog(null, "You are not in Freedom Fighter");
                    	}
            		
            		
            	}
            	else{
            		JOptionPane.showMessageDialog(null, "Your are not registered for this semester");
            	}
        }
        else if(com.equals("Logout")){
        	LoginPage lp = new LoginPage();
        	lp.setVisible(true);
        	this.setVisible(false);
        }
		
	}
}

