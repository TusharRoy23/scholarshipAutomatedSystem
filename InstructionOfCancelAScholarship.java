package sp1project;

import java.awt.*;
import javax.swing.*;

public class InstructionOfCancelAScholarship extends JFrame{
	private JPanel apanel;
	private JLabel lb1, lb2, lb3, lb4;
	InstructionOfCancelAScholarship(){
		this.initialize();
	}
	private void initialize(){
		apanel = new JPanel();
        apanel.setLayout(null);
        this.setTitle("Instructions");
        this.setBounds(420, 100, 550, 250);
        apanel.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(apanel);
        
        this.lb1 = new JLabel("INSTUCTIONS");
        this.lb1.setForeground(Color.BLUE);
        this.lb1.setBounds(215, 4, 150, 20);
        this.lb1.setFont(new Font("Serif", Font.BOLD, 17));
        apanel.add(lb1);
        
        this.lb2 = new JLabel("1) Select row by clicking & pressing 'Cancel' button for");
        this.lb2.setForeground(Color.BLACK);
        this.lb2.setBounds(75, 54, 380, 20);
        this.lb2.setFont(new Font("Serif", Font.BOLD, 15));
        apanel.add(lb2);
        
        this.lb3 = new JLabel("canceling that student one by one.");
        this.lb3.setForeground(Color.BLACK);
        this.lb3.setBounds(90, 87, 450, 20);
        this.lb3.setFont(new Font("Serif", Font.BOLD, 15));
        apanel.add(lb3);
        
        this.lb4 = new JLabel("2) Press 'Back' button to go back to the main page.");
        this.lb4.setForeground(Color.BLACK);
        this.lb4.setBounds(75, 120, 450, 20);
        this.lb4.setFont(new Font("Serif", Font.BOLD, 15));
        apanel.add(lb4);
	}
}
