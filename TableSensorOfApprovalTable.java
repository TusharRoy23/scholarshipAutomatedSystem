package sp1project;
import javax.swing.*;
import javax.swing.event.*;
public class TableSensorOfApprovalTable implements ListSelectionListener {
	ConnectionAndGetValueFromDatabase context = new ConnectionAndGetValueFromDatabase();
	JTable table;
	  public TableSensorOfApprovalTable(JTable t){
	      table=t;
	  }
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getValueIsAdjusting()){
			int row=table.getSelectedRow();
	        String id =(String)table.getValueAt(row, 0);
	        String sem_id =  context.ReturnMaxSemester_IDOfAStudentFromScholarship_information(id);
	        scholarshipApprovalTable ion = new scholarshipApprovalTable();
	        Dataclass db = new Dataclass();
	        db.SetTableSensorValue(id, sem_id);
	        ion.catch_value_for_approve(db);
		}
	}
}
