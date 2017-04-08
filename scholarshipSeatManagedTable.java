package sp1project;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class scholarshipSeatManagedTable extends JInternalFrame implements ActionListener{
	private JPanel one_panel;
	private JTable table;
	private JLabel lb, lb1;
	private JButton addbtn, decbtn, refreshbtn;
	private JTextField tf;
	private JRadioButton freedomRb, resultRb;
	private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private ButtonGroup bG;
    ConnectionAndGetValueFromDatabase context = new ConnectionAndGetValueFromDatabase();
    scholarshipSeatManagedTable(){
		super("Scholarship Information", true, true, true, true);
		this.Initialization();
	}	
	private void Initialization(){
		
		this.setBounds(26, 460, 640,230);
		this.setIconifiable(true);
		this.setVisible(true);
		
		this.one_panel = new JPanel();
        this.one_panel.setLayout(null);
		this.add(one_panel);
        this.one_panel.setBackground(Color.LIGHT_GRAY);
        
        this.freedomRb = new JRadioButton();
        this.freedomRb.setBounds(425, 13, 30, 20);
        this.freedomRb.setBackground(Color.lightGray);
        this.one_panel.add(freedomRb);
        this.freedomRb.addActionListener(this);
        
        this.lb = new JLabel("Freedom Fighter");
        this.lb.setBounds(460, 13, 140, 20);
        this.one_panel.add(lb);
        
        this.resultRb = new JRadioButton();
        this.resultRb.setBounds(425, 47, 30, 20);
        this.resultRb.setBackground(Color.lightGray);
        this.one_panel.add(resultRb);
        this.resultRb.addActionListener(this);
        
        this.lb1 = new JLabel("Result");
        this.lb1.setBounds(460, 47, 140, 20);
        this.one_panel.add(lb1);
        
        this.bG = new ButtonGroup();
        this.bG.add(freedomRb);
        this.bG.add(resultRb);
        
        tf = new JTextField();
        tf.setBounds(450,78, 130, 26);
        one_panel.add(tf);
        tf.setColumns(10);
        
        addbtn = new JButton("Increase");
        addbtn.setBounds(425, 130, 87, 25);
        this.one_panel.add(addbtn);
        this.addbtn.addActionListener(this);
        
        decbtn = new JButton("Decrease");
        decbtn.setBounds(520, 130, 92, 25);
        this.one_panel.add(decbtn);
        this.decbtn.addActionListener(this);
	    Vector<String> columns = new Vector<String>();
	    
	    columns.add("scholarship_name");
        columns.add("seat");
        
        this.tableModel = new DefaultTableModel(context.ShowScholarshipInformationFromScholarship_type(), columns){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.table = new JTable(this.tableModel);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(10);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(25);
        
        this.scrollPane = new JScrollPane(this.table);
        this.scrollPane.setBounds(25, 10, 390, 178);
        this.one_panel.add(scrollPane);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== addbtn && freedomRb.isSelected()){
			if(!tf.getText().trim().isEmpty()){
				int SeatValue = Integer.parseInt(this.tf.getText());
				context.AddAndUpdateSeatOfThatScholarship_IDFromScholarship_type(SeatValue, 1);
				Refresh();
			}
			else{
				JOptionPane.showMessageDialog(null, "There is no input");
			}
		}
		else if(e.getSource() == decbtn && freedomRb.isSelected()){
			if(!tf.getText().trim().isEmpty()){
				int SeatValue = Integer.parseInt(this.tf.getText());
				context.AddAndUpdateSeatOfThatScholarship_IDFromScholarship_type(SeatValue, 3);
				Refresh();
			}
			else{
				JOptionPane.showMessageDialog(null, "There is no input");
			}
		}
		else if(e.getSource() == addbtn && resultRb.isSelected()){
			if(!tf.getText().trim().equals("")){
				int SeatValue = Integer.parseInt(this.tf.getText());
				context.AddAndUpdateSeatOfThatScholarship_IDFromScholarship_type(SeatValue, 2);
				Refresh();
			}
			else{
				JOptionPane.showMessageDialog(null, "There is no input");
			}
		}
		else if(e.getSource() == decbtn && resultRb.isSelected()){
			if(!tf.getText().trim().equals("")){
				int SeatValue = Integer.parseInt(this.tf.getText());
				context.AddAndUpdateSeatOfThatScholarship_IDFromScholarship_type(SeatValue, 4);
				Refresh();
			}
			else{
				JOptionPane.showMessageDialog(null, "There is no input");
			}
		}
		else if(e.getSource() == addbtn ){
			JOptionPane.showMessageDialog(null, "select any category");
		}
		else if(e.getSource() == decbtn ){
			JOptionPane.showMessageDialog(null, "select any category");
		}
	}
	public void Refresh(){
		for(int i = this.tableModel.getRowCount() - 1; i >= 0; i--)
            this.tableModel.removeRow(i);
            Vector<Vector<String>> cate = context.ShowScholarshipInformationFromScholarship_type();
            for(int i = 0; i < cate.size(); i++) {
            	this.tableModel.addRow(cate.get(i).toArray());
            }
	}
}
