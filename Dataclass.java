package sp1project;

public class Dataclass {
	private String Student_ID, Semester_ID;
	public void SetTableSensorValue(String Student_ID, String Semester_ID){
		this.Student_ID = Student_ID;
		this.Semester_ID = Semester_ID;
	}
	public String GetStudent_IDTableSensorValue(){
		return this.Student_ID;
	}
	public String GetSemester_IDTableSensorValue(){
		return this.Semester_ID;
	}
}
