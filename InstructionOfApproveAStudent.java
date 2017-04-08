package sp1project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.*;

public class InstructionOfApproveAStudent extends JFrame{
	private JPanel apanel;
	private JLabel lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9, lb10, lb11;
	InstructionOfApproveAStudent(){
		this.initialize();
	}
	private void initialize(){
		apanel = new JPanel();
        apanel.setLayout(null);
        this.setTitle("Instruction");
        this.setBounds(420, 100, 550, 445);
        apanel.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(apanel);
        
        this.lb1 = new JLabel("INSTUCTIONS");
        this.lb1.setForeground(Color.BLUE);
        this.lb1.setBounds(215, 4, 150, 20);
        this.lb1.setFont(new Font("Serif", Font.BOLD, 17));
        apanel.add(lb1);
        
        this.lb2 = new JLabel("1) First select any category.");
        this.lb2.setForeground(Color.BLACK);
        this.lb2.setBounds(75, 54, 250, 20);
        this.lb2.setFont(new Font("Serif", Font.BOLD, 15));
        apanel.add(lb2);
        
        this.lb3 = new JLabel("2) Select row by clicking & press 'Approve' button to approve.");
        this.lb3.setForeground(Color.BLACK);
        this.lb3.setBounds(75, 87, 450, 20);
        this.lb3.setFont(new Font("Serif", Font.BOLD, 15));
        apanel.add(lb3);
        
        this.lb4 = new JLabel("3) Pressing 'Approve all' button, all student will be approved");
        this.lb4.setForeground(Color.BLACK);
        this.lb4.setBounds(75, 120, 450, 20);
        this.lb4.setFont(new Font("Serif", Font.BOLD, 15));
        apanel.add(lb4);
        
        this.lb5 = new JLabel("within the limited seat of that scholarship category.");
        this.lb5.setForeground(Color.BLACK);
        this.lb5.setBounds(90, 140, 450, 20);
        this.lb5.setFont(new Font("Serif", Font.BOLD, 15));
        apanel.add(lb5);
        
        this.lb6 = new JLabel("4) You can increase & decrease seat as many as you want.");
        this.lb6.setForeground(Color.BLACK);
        this.lb6.setBounds(75, 170, 450, 20);
        this.lb6.setFont(new Font("Serif", Font.BOLD, 15));
        apanel.add(lb6);
        
        this.lb7 = new JLabel("5) Pressing 'Refresh' button after increasing & decreasing seats.");
        this.lb7.setForeground(Color.BLACK);
        this.lb7.setBounds(75, 200, 450, 20);
        this.lb7.setFont(new Font("Serif", Font.BOLD, 15));
        apanel.add(lb7);
        
        this.lb8 = new JLabel("6) Pressing 'Show all students', you can see all students information.");
        this.lb8.setForeground(Color.BLACK);
        this.lb8.setBounds(75, 233, 450, 20);
        this.lb8.setFont(new Font("Serif", Font.BOLD, 15));
        apanel.add(lb8);
        
        this.lb9 = new JLabel("7) Pressing 'Show Approved Students', you can see all approved");
        this.lb9.setForeground(Color.BLACK);
        this.lb9.setBounds(75, 263, 450, 20);
        this.lb9.setFont(new Font("Serif", Font.BOLD, 15));
        apanel.add(lb9);
        
        this.lb10 = new JLabel("students.");
        this.lb10.setForeground(Color.BLACK);
        this.lb10.setBounds(92, 285, 450, 20);
        this.lb10.setFont(new Font("Serif", Font.CENTER_BASELINE, 15));
        apanel.add(lb10);
        
        this.lb11 = new JLabel("8) Press 'Exit' button for quit from the whole system.");
        this.lb11.setForeground(Color.BLACK);
        this.lb11.setBounds(75, 307, 450, 20);
        this.lb11.setFont(new Font("Serif", Font.BOLD, 15));
        apanel.add(lb11);
	}
}
