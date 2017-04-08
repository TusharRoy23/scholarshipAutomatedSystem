package sp1project;

import javax.swing.*;
import javax.swing.event.*;

public class TableSensorForCancel implements ListSelectionListener {

	ConnectionAndGetValueFromDatabase context = new ConnectionAndGetValueFromDatabase();
	JTable table;
	  public TableSensorForCancel(JTable t){
	      table=t;
	  }
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getValueIsAdjusting()){
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();
			if(!lsm.isSelectionEmpty())
			{
				int row=table.getSelectedRow();
		        String id =(String)table.getValueAt(row, 0);
		        String sem_id =  context.ReturnMaxSemester_IDOfAStudentFromScholarship_information(id);
		        CancelScholarshipOfAStudent ap = new CancelScholarshipOfAStudent();
		        Dataclass db = new Dataclass();
		        db.SetTableSensorValue(id, sem_id);
		        ap.catch_val(db);
			}
		}
	}

}
