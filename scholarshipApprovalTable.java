package sp1project;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import com.mysql.jdbc.ResultSetMetaData;

public class scholarshipApprovalTable extends JInternalFrame implements ActionListener{

	private JPanel one_panel;
	private JTable table;
	private JLabel lb, lb1;
	private JButton freedombtn, resultbtn, approvebtn, approveallbtn;
	private JRadioButton cgpaRb, creditRb;
	private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private ButtonGroup bG;
    private int approveall_count = 0;
    private int check_credit_of_freedom = 0;
    private int check_cgpa_of_freedom = 0;
    private int check_credit_of_result = 0;
    private int check_cgpa_of_result = 0;
    private ListSelectionModel listSelectionModel;
    ConnectionAndGetValueFromDatabase context = new ConnectionAndGetValueFromDatabase();
    ArrayList<String> studentId = new ArrayList<String>();
    ArrayList<String> approveall_btn_freedom_studentId = new ArrayList<String>();
    ArrayList<String> approveall_btn_result_studentId = new ArrayList<String>();
    ArrayList<String> for_freedom_studentId = new ArrayList<String>();
    ArrayList<String> for_result_studentId = new ArrayList<String>();
    ArrayList<String> one_for_all_studentId = new ArrayList<String>();
    ArrayList<String> store_one_for_all_studentId = new ArrayList<String>();
    String[] fin; Vector<String> s_id = new Vector<String>();
    String[] stu_id = null; String[] sem_id = null; static String ida ; static String sems;
    String[] freedom_stu_id = null; String[] result_stu_id = null;static int counts = 0; 
    static String maximum_semester;
    int SeatCount = 0;
    Vector<String> columns = new Vector<String>();
    Dataclass dc = new Dataclass();
    scholarshipApprovalTable(){
		super("Scholarship Applicant", true, true, true, true);
		first_check();
		this.Initialization();
		catch_value();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == freedombtn && cgpaRb.isSelected()){ // freedom & cgpa
			store_one_for_all_studentId = new ArrayList<String>();
			SeatCount = Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("1"));
			approveall_count = 1;
			check_cgpa_of_freedom = 1;
			for(int i = 0; i<freedom_stu_id.length; i++){
				if(Double.parseDouble(context.ReturnCgpaFromStudent_academic_information(freedom_stu_id[i]))>3.76 && 
						Integer.parseInt(context.ReturnScholarship_IDFromScholarship_information(freedom_stu_id[i])) == 1 &&
						Integer.parseInt(context.ReturnIsApprovedOfAStudent_ID(freedom_stu_id[i])) == 0){
					one_for_all_studentId.add(freedom_stu_id[i]);
					store_one_for_all_studentId.add(freedom_stu_id[i]);
				}
			}
			Refreshs("1");
			one_for_all_studentId = new ArrayList<String>();
		}
		else if(e.getSource() == freedombtn){ //only show freedom type
			store_one_for_all_studentId = new ArrayList<String>();
			SeatCount = Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("1"));
			approveall_count = 1;
				for(int i = 0; i<freedom_stu_id.length; i++){
					if(Integer.parseInt(context.ReturnScholarship_IDFromScholarship_information(freedom_stu_id[i])) == 1 &&
							Integer.parseInt(context.ReturnIsApprovedOfAStudent_ID(freedom_stu_id[i])) == 0){
						one_for_all_studentId.add(freedom_stu_id[i]);
						store_one_for_all_studentId.add(freedom_stu_id[i]);
					}
				}
				Refreshs("1");
				one_for_all_studentId = new ArrayList<String>();
		}
		else if(e.getSource() == resultbtn && creditRb.isSelected()){ //result & credit
			store_one_for_all_studentId = new ArrayList<String>();
			SeatCount = Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("2"));
			approveall_count = 2;
			check_credit_of_result = 1;
			for(int i = 0; i<result_stu_id.length; i++){
				if(Integer.parseInt(context.ReturnScholarship_IDFromScholarship_information(result_stu_id[i])) == 2 && 
						Integer.parseInt(context.ReturnTotalCreditFromCourse_informationSection_informationRegistrationByJoiningTable(result_stu_id[i], sem_id[i]))>=15 &&
						Integer.parseInt(context.ReturnIsApprovedOfAStudent_ID(result_stu_id[i])) == 0){
					one_for_all_studentId.add(result_stu_id[i]);
					store_one_for_all_studentId.add(result_stu_id[i]);
				}
			};
			Refreshs("2");
			one_for_all_studentId = new ArrayList<String>();
		}
		else if(e.getSource() == freedombtn && creditRb.isSelected()){ //freedom & credit
			store_one_for_all_studentId = new ArrayList<String>();
			SeatCount = Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("1"));
			approveall_count = 1;
			check_credit_of_freedom = 1;
			for(int i = 0; i<freedom_stu_id.length; i++){
				if(Integer.parseInt(context.ReturnScholarship_IDFromScholarship_information(freedom_stu_id[i])) == 1 && 
						Integer.parseInt(context.ReturnTotalCreditFromCourse_informationSection_informationRegistrationByJoiningTable(freedom_stu_id[i], sem_id[i]))>=13 &&
						Integer.parseInt(context.ReturnIsApprovedOfAStudent_ID(freedom_stu_id[i])) == 0){
					one_for_all_studentId.add(freedom_stu_id[i]);
					store_one_for_all_studentId.add(freedom_stu_id[i]);
				}
			}
			Refreshs("1");
			one_for_all_studentId = new ArrayList<String>();
		}
		else if(e.getSource() == resultbtn && cgpaRb.isSelected()){ //result & cgpa
			store_one_for_all_studentId = new ArrayList<String>();
			SeatCount = Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("2"));
			approveall_count = 2;
			check_cgpa_of_result = 1;
			for(int i = 0; i<result_stu_id.length; i++){
				if(Integer.parseInt(context.ReturnScholarship_IDFromScholarship_information(result_stu_id[i])) == 2 && 
						Double.parseDouble(context.ReturnCgpaFromStudent_academic_information(result_stu_id[i]))>=3.75 &&
						Integer.parseInt(context.ReturnIsApprovedOfAStudent_ID(result_stu_id[i])) == 0){
					one_for_all_studentId.add(result_stu_id[i]);
					store_one_for_all_studentId.add(result_stu_id[i]);
				}
			}
			Refreshs("2");
			one_for_all_studentId = new ArrayList<String>();
		}
		else if(e.getSource() == resultbtn){ //only show result type 
			store_one_for_all_studentId = new ArrayList<String>();
			SeatCount = Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("2"));
			approveall_count = 2;
				for(int i = 0; i<result_stu_id.length; i++){
					if(Integer.parseInt(context.ReturnScholarship_IDFromScholarship_information(result_stu_id[i])) == 2 && 
							Integer.parseInt(context.ReturnIsApprovedOfAStudent_ID(result_stu_id[i])) == 0){
						one_for_all_studentId.add(result_stu_id[i]);
						store_one_for_all_studentId.add(result_stu_id[i]);
					}
			    }
				Refreshs("2");
				one_for_all_studentId = new ArrayList<String>();
		}
		else if(e.getSource() == approvebtn && approveall_count == 1 && check_cgpa_of_freedom == 1){ //approve freedom btn & cgpa
				ArrayList<String> check_freedom_fighter_for_empty =  new ArrayList<String>();
				check_freedom_fighter_for_empty.addAll(context.ReturnStudent_IDFromScholarship_informationWhereScholarship_IDAndMaxSemesterOfThisStudent("1", maximum_semester));
				if(0 < Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("1")) &&
						!check_freedom_fighter_for_empty.isEmpty()){
					check_freedom_fighter_for_empty =  new ArrayList<String>();
						if(counts == 1){
							counts = 0;
							if(0 < Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("1"))){
								context.UpdateIsApprovedColumn0to1inScholarship_information(ida, sems);
								context.UpdateAndDecreaseSeatOfScholarship_typeWhereScholarship_ID("1");
								one_for_all_studentId.addAll(context.ReturnStudent_IDFromScholarship_informationWhereScholarship_IDAndStudentIds("1", store_one_for_all_studentId));
								Refreshs("1");
							}
							else{
								JOptionPane.showMessageDialog(null, "Seat Filled !!!");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "No student selected");
						}
						one_for_all_studentId = new ArrayList<String>();
				}
				else{
					JOptionPane.showMessageDialog(null, "Table is Empty");
				}
			}
		else if(e.getSource() == approvebtn && approveall_count == 1 && check_credit_of_freedom == 1 ){//approve freedom & credit
				ArrayList<String> check_result_for_empty =  new ArrayList<String>();
				check_result_for_empty.addAll(context.ReturnStudent_IDFromScholarship_informationWhereScholarship_IDAndMaxSemesterOfThisStudent("1", maximum_semester));
				if(0 < Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("1")) &&
						!check_result_for_empty.isEmpty()){
					check_result_for_empty =  new ArrayList<String>();
						if(counts == 1){
							counts = 0;
							if(0 < Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("1"))){
								context.UpdateIsApprovedColumn0to1inScholarship_information(ida, sems);
								context.UpdateAndDecreaseSeatOfScholarship_typeWhereScholarship_ID("1");
								one_for_all_studentId.addAll(context.ReturnStudent_IDFromScholarship_informationWhereScholarship_IDAndStudentIds("1", store_one_for_all_studentId));
								Refreshs("1");
							}
							else{
								JOptionPane.showMessageDialog(null, "Seat Filled !!!");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "No student selected");
						}
						one_for_all_studentId = new ArrayList<String>();
				}
				else{
					JOptionPane.showMessageDialog(null, "Table is Empty");
				}
			}
		else if(e.getSource() == approvebtn && approveall_count == 2 && check_cgpa_of_result == 1){ //approve result & cgpa btn
			ArrayList<String> check_freedom_fighter_for_empty =  new ArrayList<String>();
			check_freedom_fighter_for_empty.addAll(context.ReturnStudent_IDFromScholarship_informationWhereScholarship_IDAndMaxSemesterOfThisStudent("2", maximum_semester));
			if(0 < Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("2")) &&
					!check_freedom_fighter_for_empty.isEmpty()){
				check_freedom_fighter_for_empty =  new ArrayList<String>();
					if(counts == 1){
						counts = 0;
						if(0 < Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("2"))){
							context.UpdateIsApprovedColumn0to1inScholarship_information(ida, sems);
							context.UpdateAndDecreaseSeatOfScholarship_typeWhereScholarship_ID("2");
							one_for_all_studentId.addAll(context.ReturnStudent_IDFromScholarship_informationWhereScholarship_IDAndStudentIds("2", store_one_for_all_studentId));
							Refreshs("2");
						}
						else{
							JOptionPane.showMessageDialog(null, "Seat Filled !!!");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "No student selected");
					}
					one_for_all_studentId = new ArrayList<String>();
			}
			else{
				JOptionPane.showMessageDialog(null, "Table is Empty");
			}
		}
	else if(e.getSource() == approvebtn && approveall_count == 2 && check_credit_of_result == 1 ){//Approve result & credit
			ArrayList<String> check_result_for_empty =  new ArrayList<String>();
			check_result_for_empty.addAll(context.ReturnStudent_IDFromScholarship_informationWhereScholarship_IDAndMaxSemesterOfThisStudent("2", maximum_semester));
			if(0 < Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("2")) &&
					!check_result_for_empty.isEmpty()){
				check_result_for_empty =  new ArrayList<String>();
					if(counts == 1){
						counts = 0;
						if(0 < Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("2"))){
							context.UpdateIsApprovedColumn0to1inScholarship_information(ida, sems);
							context.UpdateAndDecreaseSeatOfScholarship_typeWhereScholarship_ID("2");
							one_for_all_studentId.addAll(context.ReturnStudent_IDFromScholarship_informationWhereScholarship_IDAndStudentIds("2", store_one_for_all_studentId));
							Refreshs("2");
						}
						else{
							JOptionPane.showMessageDialog(null, "Seat Filled !!!");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "No student selected");
					}
					one_for_all_studentId = new ArrayList<String>();
			}
			else{
				JOptionPane.showMessageDialog(null, "Table is Empty");
			}
		}
	else if(e.getSource() == approvebtn && approveall_count == 1){//Approve Only Freedom
		ArrayList<String> check_result_for_empty =  new ArrayList<String>();
		check_result_for_empty.addAll(context.ReturnStudent_IDFromScholarship_informationWhereScholarship_IDAndMaxSemesterOfThisStudent("1", maximum_semester));
		if(0 < Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("1")) &&
				!check_result_for_empty.isEmpty()){
			check_result_for_empty =  new ArrayList<String>();
				if(counts == 1){
					counts = 0;
					if(0 < Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("1"))){
						context.UpdateIsApprovedColumn0to1inScholarship_information(ida, sems);
						context.UpdateAndDecreaseSeatOfScholarship_typeWhereScholarship_ID("1");
						one_for_all_studentId.addAll(context.ReturnStudent_IDFromScholarship_informationWhereScholarship_IDAndStudentIds("1", store_one_for_all_studentId));
						Refreshs("1");
					}
					else{
						JOptionPane.showMessageDialog(null, "Seat Filled !!!");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "No student selected");
				}
				one_for_all_studentId = new ArrayList<String>();
		}
		else{
			JOptionPane.showMessageDialog(null, "Table is Empty");
		}
	}
	else if(e.getSource() == approvebtn && approveall_count == 2){//Approve Only result
		ArrayList<String> check_result_for_empty =  new ArrayList<String>();
		check_result_for_empty.addAll(context.ReturnStudent_IDFromScholarship_informationWhereScholarship_IDAndMaxSemesterOfThisStudent("2", maximum_semester));
		if(0 < Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("2")) &&
				!check_result_for_empty.isEmpty()){
			check_result_for_empty =  new ArrayList<String>();
				if(counts == 1){
					counts = 0;
					if(0 < Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("2"))){
						context.UpdateIsApprovedColumn0to1inScholarship_information(ida, sems);
						context.UpdateAndDecreaseSeatOfScholarship_typeWhereScholarship_ID("2");
						one_for_all_studentId.addAll(context.ReturnStudent_IDFromScholarship_informationWhereScholarship_IDAndStudentIds("2", store_one_for_all_studentId));
						Refreshs("2");
					}
					else{
						JOptionPane.showMessageDialog(null, "Seat Filled !!!");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "No student selected");
				}
				one_for_all_studentId = new ArrayList<String>();
		}
		else{
			JOptionPane.showMessageDialog(null, "Table is Empty");
		}
	}
		else if(e.getSource() == approvebtn){
				JOptionPane.showMessageDialog(null, "Select any category");
		}
		else if(e.getSource() == approveallbtn && approveall_count == 2){ // for result btn
			SeatCount = Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("2"));
			ArrayList<String> check_result_for_empty =  new ArrayList<String>();
			check_result_for_empty.addAll(context.ReturnStudent_IDFromScholarship_informationWhereScholarship_IDAndMaxSemesterOfThisStudent("2", maximum_semester));
			if(0 < Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("2")) && 
					!check_result_for_empty.isEmpty()){
				check_result_for_empty =  new ArrayList<String>();
				int s = 0;
					for(int i = 0; i<result_stu_id.length; i++){
						if(Integer.parseInt(context.ReturnScholarship_IDFromScholarship_information(result_stu_id[i])) == 2 &&
						Integer.parseInt(context.ReturnIsApprovedOfAStudent_ID(result_stu_id[i])) == 0){
							if(0 < Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("2"))){
								approveall_btn_result_studentId.add(result_stu_id[i]);
								context.UpdateAndDecreaseSeatOfScholarship_typeWhereScholarship_ID("2");
								s = 0;
							}
							else{
								s = 1;
							}
						}
					}
					context.UpdateIsApprove0to1OfThisStudent_IDs(approveall_btn_result_studentId);
					one_for_all_studentId.addAll(context.ReturnStudent_IDFromScholarship_informationWhereScholarship_IDAndMaxSemesterOfThisStudent("2", maximum_semester));
					Refreshs("2");
					one_for_all_studentId = new ArrayList<String>();
					approveall_btn_result_studentId = new ArrayList<String>();
					if(s == 1){
						JOptionPane.showMessageDialog(null, "Results Maximun Seat Reached!!!");
					}
			}
			else{
				JOptionPane.showMessageDialog(null, "Table is Empty");
			}
		}
		else if(e.getSource() == approveallbtn && approveall_count == 1){ // for freedom btn
			SeatCount = Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("1"));
			ArrayList<String> check_freedom_fighter_for_empty =  new ArrayList<String>();
			check_freedom_fighter_for_empty.addAll(context.ReturnStudent_IDFromScholarship_informationWhereScholarship_IDAndMaxSemesterOfThisStudent("1", maximum_semester));
			int s = 0;
			if(0 < Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("1")) && 
					!check_freedom_fighter_for_empty.isEmpty()){
					for(int i = 0; i<freedom_stu_id.length; i++){;
					if(Integer.parseInt(context.ReturnScholarship_IDFromScholarship_information(freedom_stu_id[i])) == 1 &&
							Integer.parseInt(context.ReturnIsApprovedOfAStudent_ID(freedom_stu_id[i])) == 0){
						if( 0 < Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID("1"))){
							context.UpdateAndDecreaseSeatOfScholarship_typeWhereScholarship_ID("1");
							approveall_btn_freedom_studentId.add(freedom_stu_id[i]);
							s = 0;
						}
						else{
							s = 1;
						}
					 }
				   }
					context.UpdateIsApprove0to1OfThisStudent_IDs(approveall_btn_freedom_studentId);
					one_for_all_studentId.addAll(context.ReturnStudent_IDFromScholarship_informationWhereScholarship_IDAndMaxSemesterOfThisStudent("1", maximum_semester));
					Refreshs("1");
					one_for_all_studentId = new ArrayList<String>();
					approveall_btn_freedom_studentId = new ArrayList<String>();
				if(s == 1 ){
					JOptionPane.showMessageDialog(null, "Freedom Fighters Maximun Seat Reached!!!");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Table is Empty");
			}
		}
		else if(e.getSource() == approveallbtn){
			JOptionPane.showMessageDialog(null, "First select any catagory !");
		}
	}
	public void catch_value_for_approve(Dataclass db){
		this.ida = db.GetStudent_IDTableSensorValue();
		this.sems = db.GetSemester_IDTableSensorValue();
		counts = 1;
	}
	public void first_check(){
		s_id = context.ReturnStudent_IDFromScholarship_information();
		Vector<String> s_sem = context.ReturnSemester_IDFromScholarship_information();
		 stu_id = s_id.toArray(new String[0]);
		 sem_id = s_sem.toArray(new String[0]);
		 for(int i = 0; i<s_id.size(); i++){
			 if(Integer.parseInt(context.ReturnOngoingSemester_IDFromsemester()) == Integer.parseInt(context.ReturnMaxSemester_IDOfAStudentFromScholarship_information(stu_id[i]))){
				 if(Double.parseDouble(context.ReturnCgpaFromStudent_academic_information(stu_id[i]))>=3.75 && 
							Integer.parseInt(context.ReturnTotalCreditFromCourse_informationSection_informationRegistrationByJoiningTable(stu_id[i], sem_id[i]))>=13 &&
							Integer.parseInt(context.ReturnIsApprovedOfAStudent_ID(stu_id[i])) == 0 ||
							Integer.parseInt(context.ReturnScholarship_IDFromScholarship_information(stu_id[i])) == 1)
						 {
							 studentId.add(stu_id[i]);
							 if(Integer.parseInt(context.ReturnScholarship_IDFromScholarship_information(stu_id[i])) == 1){
								 for_freedom_studentId.add(stu_id[i]);
							 }
							 else if(Integer.parseInt(context.ReturnScholarship_IDFromScholarship_information(stu_id[i])) == 2){
								 for_result_studentId.add(stu_id[i]);
							 }
						 }
			 }
		 }
		 maximum_semester = context.ReturnOngoingSemester_IDFromsemester();
		 context.UpdateCheck_Valid0To1OfScholar_informationWhereStudent_IDs(studentId);
		 stu_id = new String[studentId.size()];
		 stu_id = studentId.toArray(stu_id);
		 freedom_stu_id = new  String[for_freedom_studentId.size()];
		 freedom_stu_id = for_freedom_studentId.toArray(freedom_stu_id);
		 result_stu_id = new  String[for_result_studentId.size()];
		 result_stu_id = for_result_studentId.toArray(result_stu_id);
	}
	private void Initialization(){
		this.setBounds(7, 10, 1334,440);
		this.setIconifiable(true);
		this.setVisible(true);
		
		this.one_panel = new JPanel();
        this.one_panel.setLayout(null);
		this.add(one_panel);
        this.one_panel.setBackground(Color.lightGray);
        
        this.lb = new JLabel("CGPA");
        this.lb.setBounds(330, 3, 40, 20);
        this.one_panel.add(lb);
        
        this.freedombtn = new JButton("Freedom Fighter");
        this.freedombtn.setBounds(550, 3, 140, 25);
        this.one_panel.add(freedombtn);
        this.freedombtn.addActionListener(this);
        
        this.resultbtn = new JButton("Result");
        this.resultbtn.setBounds(720, 3, 90, 25);
        this.one_panel.add(resultbtn);
        this.resultbtn.addActionListener(this);
        
        this.approvebtn = new JButton("Approve");
        this.approvebtn .setBounds(1219, 93, 90, 25);
        this.one_panel.add(approvebtn);
        this.approvebtn.addActionListener(this);
        
        this.approveallbtn = new JButton("Approve all");
        this.approveallbtn .setBounds(1214, 133, 100, 25);
        this.one_panel.add(approveallbtn);
        this.approveallbtn.addActionListener(this);
        
        columns.add("Student ID");
        columns.add("Student Name");
        columns.add("Father Name");
        columns.add("Mother Name");
		columns.add("Current CGPA");
        columns.add("Taken Credit");
        columns.add("Current Semester");
        columns.add("Scholarship Name");
		columns.add("Payment Validity");
        columns.add("Provider's Name");
        columns.add("Provider's Income");
      
		this.tableModel = new DefaultTableModel(context.validStudents(studentId), columns){};
		this.table = new JTable(this.tableModel);
		this.table.setBackground(Color.lightGray);
		
		this.table.getColumnModel().getColumn(0).setPreferredWidth(4);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(15);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(30);
		this.table.getColumnModel().getColumn(3).setPreferredWidth(20);
		this.table.getColumnModel().getColumn(4).setPreferredWidth(30);
		this.table.getColumnModel().getColumn(5).setPreferredWidth(15);
		this.table.getColumnModel().getColumn(6).setPreferredWidth(15);
		this.table.getColumnModel().getColumn(7).setPreferredWidth(15);
		this.table.getColumnModel().getColumn(8).setPreferredWidth(15);
		this.table.getColumnModel().getColumn(9).setPreferredWidth(15);
		this.table.getColumnModel().getColumn(10).setPreferredWidth(15);
		        
		this.scrollPane = new JScrollPane(this.table);
		this.scrollPane.setBounds(5, 39, 1198, 360);
		this.one_panel.add(scrollPane);
        
        this.cgpaRb = new JRadioButton();
        this.cgpaRb.setBounds(300, 3, 30, 20);
        this.cgpaRb.setBackground(Color.lightGray);
        this.one_panel.add(cgpaRb);
        this.cgpaRb.addActionListener(this);
        
        this.creditRb = new JRadioButton();
        this.creditRb.setBounds(425, 3, 30, 20);
        this.creditRb.setBackground(Color.lightGray);
        this.one_panel.add(creditRb);
        this.creditRb.addActionListener(this);
        
        this.lb1 = new JLabel("CREDIT");
        this.lb1.setBounds(454, 3, 50, 20);
        this.one_panel.add(lb1);
        
        this.bG = new ButtonGroup();
        this.bG.add(cgpaRb);
        this.bG.add(creditRb);
	}
	public void catch_value(){
		table.getSelectionModel().addListSelectionListener(new TableSensorOfApprovalTable(table));
	}
	public void Refreshs(String scholarship_ID){
		for(int i = this.tableModel.getRowCount() - 1; i >= 0; i--)
            this.tableModel.removeRow(i);
            Vector<Vector<String>> cate = context.validStudents(one_for_all_studentId);
            int counts= Integer.parseInt(context.ReturnSeatFromScholarship_typeWhereScholarship_ID(scholarship_ID));
            if(cate.size()<counts){
            	counts=cate.size();
            }
            for(int i = 0; i < counts; i++) {
            	Vector<String> tmp=cate.get(i);
            	for(int j=0;j<tmp.size();j++){
            		String s=tmp.get(j);
            		if(s==null){}
            		else{}
            	}
            	this.tableModel.addRow(cate.get(i).toArray());
           }
	}
}
