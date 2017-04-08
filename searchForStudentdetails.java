package sp1project;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class searchForStudentdetails extends JFrame{
    private JPanel apanel;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JTextField txtsearch;
    ConnectionAndGetValueFromDatabase context = new ConnectionAndGetValueFromDatabase();
    Vector<String> columns = new Vector<String>();
    Vector<Vector<String>> store = new Vector<Vector<String>>();
    TableRowSorter<TableModel> rowSorter ;
    
    public searchForStudentdetails(){
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
        columns.add("permanent address");
        columns.add("present address");
        columns.add("phone");
        columns.add("email");
        columns.add("sex");
        columns.add("Nationality");
        columns.add("Religion");
        columns.add("Marital Status");
        columns.add("Blood Group");
        
        store = context.getStudent_information();
        this.tableModel = new DefaultTableModel(store, columns){};

        this.table = new JTable(this.tableModel);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(1);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(18);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(18);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(18);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(23);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(10);
        this.table.getColumnModel().getColumn(6).setPreferredWidth(35);
        this.table.getColumnModel().getColumn(7).setPreferredWidth(5);
        this.table.getColumnModel().getColumn(8).setPreferredWidth(1);
        this.table.getColumnModel().getColumn(9).setPreferredWidth(10);
        this.table.getColumnModel().getColumn(10).setPreferredWidth(8);
        this.table.getColumnModel().getColumn(11).setPreferredWidth(8);
        this.table.getColumnModel().getColumn(12).setPreferredWidth(8);
        
        this.scrollPane = new JScrollPane(this.table);
        this.scrollPane.setBounds(160, 51, 1178, 600);
        this.apanel.add(scrollPane);
        
        JLabel lblNewLabel = new JLabel("Search Student");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(25, 45, 116, 24);
        apanel.add(lblNewLabel);
        
        txtsearch = new JTextField();
        txtsearch.setBounds(15, 80, 130, 26);
        apanel.add(txtsearch);
        txtsearch.setColumns(10);
        
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
}

