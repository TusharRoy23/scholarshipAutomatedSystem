package sp1project;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class CancelScholarshipOfAStudent extends JFrame implements ActionListener{
	
	private JPanel apanel;
    private JTable table;
    private JButton cancel_btn, back_btn, instuction_btn;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JTextField txtsearch;
    ConnectionAndGetValueFromDatabase context = new ConnectionAndGetValueFromDatabase();
    Vector<String> columns = new Vector<String>();
    ArrayList<String> studentId = new ArrayList<String>();
    TableRowSorter<TableModel> rowSorter ;
    String[] stu_id = null; String[] sem_id = null;
    String[] pass_id ;
    static String ida ; static String sems;
    static int counts = 0;
    Vector<String> s_id = new Vector<String>();
    ArrayList<String> cancel_btn_studentId = new ArrayList<String>();
    CancelScholarshipOfAStudent(){
		first_check();
		this.Initialization();
		mark_value();
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == cancel_btn){
				if(!studentId.isEmpty()){
					if(counts == 1){
						counts = 0;
						context.UpdateIsApprovedColumn1to0inScholarship_information(ida, sems);
						if(Integer.parseInt(context.ReturnScholarship_IDFromScholarship_information(ida)) == 1){
							context.UpdateAndAddSeatsOfScholarship_typeWhereScholarship_ID("1");
						}
						else{
							context.UpdateAndAddSeatsOfScholarship_typeWhereScholarship_ID("2");
						}
						cancel_btn_studentId.addAll(context.ReturnStudent_IDsOfScholarship_informationWhereStudent_IDs(studentId));
						show_approved_only_Refresh();
						cancel_btn_studentId = new ArrayList<String>();
					}
					else{
						JOptionPane.showMessageDialog(null, "No student is selected");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "There is no approved student");
				}
		}
		else if(e.getSource() == instuction_btn){
			InstructionOfCancelAScholarship ioc = new InstructionOfCancelAScholarship();
			ioc.setVisible(true);
		}
		else if(e.getSource() == back_btn){
			AdminPanel sa = new AdminPanel();
			sa.setVisible(true);
			this.setVisible(false);
		}
	}
	public void first_check(){
		String max_sem = context.ReturnOngoingSemester_IDFromsemester();
		s_id = context.ReturnStudent_IDWhosHaveMaxSemester(max_sem);
		 stu_id = s_id.toArray(new String[0]);
		 for(int i = 0; i<s_id.size(); i++){
			 if(Integer.parseInt(context.ReturnIsApprovedOfAStudent_ID(stu_id[i])) == 1)
			 {
				 studentId.add(stu_id[i]);
			 }
		 }
		 stu_id = new String[studentId.size()];
		 stu_id = studentId.toArray(stu_id);
	}
	public void catch_val(Dataclass db){
		this.ida = db.GetStudent_IDTableSensorValue();
		this.sems = db.GetSemester_IDTableSensorValue();
		counts = 1;
	}
	
	public void mark_value(){
		table.getSelectionModel().addListSelectionListener(new TableSensorForCancel(table));
	}
	private void Initialization(){
		apanel = new JPanel();
        apanel.setLayout(null);
        setTitle("Admin Panel");
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        apanel.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(apanel);
        
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
        
        this.tableModel = new DefaultTableModel(context.validStudents(studentId), columns){}; // (data, column name)
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
        this.scrollPane.setBounds(160, 51, 1178, 600);
        this.apanel.add(scrollPane);
        
        JLabel lblNewLabel = new JLabel("Search Student");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(25, 45, 116, 24);
        apanel.add(lblNewLabel);
        
        this.cancel_btn = new JButton("Cancel");
        this.cancel_btn.setBackground(Color.WHITE);
        this.cancel_btn.setForeground(Color.red);
        this.cancel_btn.setBounds(30,146,100,25);
        this.apanel.add(cancel_btn);
        this.cancel_btn.addActionListener(this);
        
        this.back_btn = new JButton("Back");
        this.back_btn.setBackground(Color.WHITE);
        this.back_btn.setForeground(Color.red);
        this.back_btn.setBounds(30,186,100,25);
        this.apanel.add(back_btn);
        this.back_btn.addActionListener(this);
        
        this.instuction_btn = new JButton("Instuction");
        this.instuction_btn.setBackground(Color.WHITE);
        this.instuction_btn.setForeground(Color.red);
        this.instuction_btn.setBounds(30,226,100,25);
        this.apanel.add(instuction_btn);
        this.instuction_btn.addActionListener(this);
        
        txtsearch = new JTextField();
        txtsearch.setBounds(15, 80, 130, 26);
        apanel.add(txtsearch);
        txtsearch.setColumns(10);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JLabel lblNewLabel_1 = new JLabel("*Search by ID");
        lblNewLabel_1.setBounds(20, 116, 173, 15);
        apanel.add(lblNewLabel_1);
        
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        
        txtsearch.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void changedUpdate(DocumentEvent arg0) {
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				    String text = txtsearch.getText();
	                if (text.trim().length() == 0) {
	                    rowSorter.setRowFilter(null);
	                } else {
	                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
	                }
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				String text = txtsearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
				
			}
        });
	}
	public void show_approved_only_Refresh(){
		for(int i = this.tableModel.getRowCount() - 1; i >= 0; i--)
            this.tableModel.removeRow(i);
            Vector<Vector<String>> cate = context.validStudents(cancel_btn_studentId);
        for(int i = 0; i < cate.size(); i++) {
            this.tableModel.addRow(cate.get(i).toArray());
        }
	}
}

