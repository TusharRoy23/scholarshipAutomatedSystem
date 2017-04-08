package sp1project;

import java.sql.*;
import java.util.*;

public class ConnectionAndGetValueFromDatabase {
    private Connection connection;
    private Statement statement;
    public ConnectionAndGetValueFromDatabase(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
            this.statement = this.connection.createStatement();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }
    
    public Vector<Vector<String>> getStudent_information(){
        try{
            String sql = "SELECT * FROM student_information ORDER BY student_ID ASC";
            ResultSet rs = this.statement.executeQuery(sql);
            Vector<Vector<String>> FlatList = new Vector<Vector<String>>();
            while(rs.next()){
                Vector<String> cont = new Vector<String>();
                cont.add(rs.getString("student_ID"));
                cont.add(rs.getString("student_name"));
                cont.add(rs.getString("father_name"));
                cont.add(rs.getString("mother_name"));
                cont.add(rs.getString("permanent_address"));
                cont.add(rs.getString("present_address"));
                cont.add(rs.getString("phone"));
                cont.add(rs.getString("email"));
                cont.add(rs.getString("sex"));
                cont.add(rs.getString("nationality"));
                cont.add(rs.getString("religion"));
                cont.add(rs.getString("marital_status"));
                cont.add(rs.getString("blood_group"));
                FlatList.add(cont);
            }
            return FlatList;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public Vector<Vector<String>> getLoginInfo(String username, String password){
        try
		{
			String sql = ("SELECT * FROM student_information WHERE student_ID = '" + username + "' AND password LIKE '" + password + "'");
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<Vector<String>> loginList = new Vector<Vector<String>>();
                        Vector<String> log = new Vector<String>();
			while(rs.next())
                        {
                            log.add(rs.getString("student_ID"));
                            log.add(rs.getString("password"));
                            loginList.add(log);
                            return loginList;
                        }
			return loginList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
    }
    public Vector<String> getScholarshipType(){
    	try{
    		String sql = ("select scholarship_name from scholarship_type");
    		ResultSet rs = this.statement.executeQuery(sql);
    		Vector<String> schol = new Vector<String>();
    		while(rs.next()){
    			schol.add(rs.getString("scholarship_name"));
    		}
    		return schol;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
			return null;
    	}
    }
    public void InsertIntoScholarship_infomation(String id, int scholar_id, int sems_ID){
    	try{
    		String sql = "insert into scholarship_information values ("+id+", "+scholar_id+" , "+ sems_ID+" , '10', 0, 1, 0, null)";
    		this.statement.execute(sql);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    public Vector<String> ReturnScholarshipIDFromScholarship_type(String scholar_name){
    	try{
    		String sql = "select scholarship_ID from scholarship_type where scholarship_name LIKE '" + scholar_name +"'";
    		ResultSet rs = this.statement.executeQuery(sql);
    		Vector<String> scholar_ID = new Vector<String>();
    		while(rs.next()){
    			scholar_ID.add(rs.getString("scholarship_ID"));
    		}
    		return scholar_ID;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public String ReturnLastestSemesterIDFromRegistration(String student_id){ //have to change
    	try{
    		String sql = "SELECT semester_ID FROM `registration` WHERE sinfo_id in(select max(sinfo_id) from "
    				+ "registration group by student_ID order by sinfo_id desc )and student_ID ="+ student_id;
    		ResultSet rs = this.statement.executeQuery(sql);
    		Vector<String> semester_ID = new Vector<String>();
    		while(rs.next()){
    			semester_ID.add(rs.getString("semester_ID"));
    		}
    		return semester_ID.get(0);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public Vector<Vector<String>> ShowScholarshipInformationFromScholarship_type(){
    	try{
    		String sql = "select * from scholarship_type";
    		ResultSet rs = this.statement.executeQuery(sql);
    		Vector<Vector<String>> scholar_list = new Vector<Vector<String>>();
    		while(rs.next()){
    			Vector<String> sch = new Vector<String>();
    			sch.add(rs.getString("scholarship_name"));
    			sch.add(rs.getString("seat"));
    			scholar_list.add(sch);
    		}
    		return scholar_list;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public Vector<String> ReturnStudent_IDFromScholarship_information(){
    	try{
    		String sql = "SELECT student_ID FROM `scholarship_information` WHERE sinfo_id in(select max(sinfo_id) "
    				+ "from scholarship_information group by student_ID order by sinfo_id desc ) and "
    				+ "isApplied = 1 AND isApproved = 0";
    		ResultSet rs = this.statement.executeQuery(sql);
    		Vector<Vector<String>> check = new Vector<Vector<String>>();
    		Vector<String> ret_check = new Vector<String>();
    		while(rs.next()){
        		ret_check.add(rs.getString("student_ID"));
        		check.add(ret_check);
    		}
    		return ret_check;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public Vector<String> ReturnSemester_IDFromScholarship_information(){
    	try{
    		String sql = "SELECT semester_ID FROM `scholarship_information` WHERE sinfo_id "
    		+ "in(select max(sinfo_id) from scholarship_information group by student_ID order by sinfo_id desc )"
    		+ " and isApplied = 1 ";
    		ResultSet rs = this.statement.executeQuery(sql);
    		Vector<Vector<String>> check = new Vector<Vector<String>>();
    		Vector<String> ret_check = new Vector<String>();
    		while(rs.next()){
        		ret_check.add(rs.getString("semester_ID"));
        		check.add(ret_check);
    		}
    		return ret_check;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public String ReturnTotalCreditFromCourse_informationSection_informationRegistrationByJoiningTable(String student_id, String semester_id){
    	try{
    		String sql = "select sum(ci.course_credit) s from course_information ci,section_information si, registration r"
    		+ " where ci.course_ID=si.course_ID and r.section_ID=si.section_ID and r.student_ID="+student_id+" and "
    				+ "r.semester_ID="+semester_id;
    		ResultSet rs = this.statement.executeQuery(sql);
    		Vector<String> ret_check = null;
    		while(rs.next()){
    			ret_check = new Vector<String>();
    			ret_check.add(rs.getString("s"));
    		}
    		return ret_check.get(0);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public String ReturnCgpaFromStudent_academic_information(String student_id){
    	try{
    		String sql = "select cgpa from student_academic_information where student_ID = " + student_id;
    		ResultSet rs = this.statement.executeQuery(sql);
    		Vector<String> ret_check = new Vector<String>();
    		while(rs.next()){
    			ret_check.add(rs.getString("cgpa"));
    		}
    		return ret_check.get(0);
    		
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public Vector<Vector<String>> validStudents(ArrayList<String> student_id){
    	try{
	    		Vector<Vector<String>> check = new Vector<Vector<String>>();
	    		for(int i = 0; i <student_id.size(); i++)
	    		{
	    			Vector<String> ret_check = new Vector<String>();
	    			String a = student_id.get(i);
	    				String sql = "select sti.student_ID sid, sti.student_name snam,sti.father_name sf, "
		    					+ "sti.mother_name sm, sc.cgpa scg, sum(ci.course_credit) cre, semss.semester_name sem, "
		    					+ "sol.scholarship_name snon , sin.check_valid scv, fo.name_of_provider fon, fo.monthly_income foi"
		    					+ " from student_information sti, student_academic_information sc, financial_overview fo, course_information ci,"
		    					+ "scholarship_type sol ,scholarship_information sin, section_information si, semester semss, registration r where sin.sinfo_id in"
		    					+ "(select max(sinfo_id) from scholarship_information group by student_ID order by sinfo_id desc) AND sti.student_ID=r.student_ID "
		    					+ "and sti.student_ID=sc.student_ID AND sti.student_ID= sin.student_ID AND sti.student_ID=fo.student_ID AND "
		    					+ "sti.student_ID = "+a+ " AND sol.scholarship_ID=sin.scholarship_ID AND semss.semester_ID = sin.semester_ID AND ci.course_ID=si.course_ID and r.section_ID=si.section_ID ";
		        		ResultSet rs = this.statement.executeQuery(sql);
		        		while(rs.next()){
		        			ret_check.add(rs.getString("sid"));
		        			ret_check.add(rs.getString("snam"));
		        			ret_check.add(rs.getString("sf"));
		        			ret_check.add(rs.getString("sm"));
		        			ret_check.add(rs.getString("scg"));
		        			ret_check.add(rs.getString("cre"));
		        			ret_check.add(rs.getString("sem"));
		        			ret_check.add(rs.getString("snon"));
		        			ret_check.add(rs.getString("scv"));
		        			ret_check.add(rs.getString("fon"));
		        			ret_check.add(rs.getString("foi"));
		        			check.add(ret_check);
		    		}
    		}
    		return check;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public String ReturnScholarship_IDFromScholarship_information(String student_id){
    	try{
        	String sql = "SELECT scholarship_ID FROM `scholarship_information` WHERE sinfo_id "
        	+ "in(select max(sinfo_id) from scholarship_information group by student_ID order by sinfo_id desc ) "
        	+ "and student_ID = " + student_id;
        	Vector<String> ret_check = new Vector<String>();
        	ResultSet rs = this.statement.executeQuery(sql);
        	while(rs.next()){
        		ret_check.add(rs.getString("scholarship_ID"));
        	}
        	return ret_check.get(0);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public void UpdateIsApprovedColumn0to1inScholarship_information(String student_id,String semester_id){
    	try{
    		String sql = "update scholarship_information set isApproved = 1 WHERE "
    				+ "student_ID = " + student_id+" and semester_ID='"+semester_id+"'";
        	this.statement.execute(sql);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    public void UpdateIsApprovedColumn1to0inScholarship_information(String student_id,String semester_id){
    	try{
    		String sql = "update scholarship_information set isApproved = 0 WHERE student_ID = " + student_id+" and "
    				+ " semester_ID = "+semester_id;
    		this.statement.execute(sql);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    public String ReturnMaxSemester_IDOfAStudentFromScholarship_information(String student_id){
    	try{
    		String sql = "SELECT semester_ID FROM `scholarship_information` WHERE sinfo_id in"
    				+ "(select max(sinfo_id) from scholarship_information group by student_ID order by sinfo_id desc) "
    				+ "and student_ID = " + student_id;
    		ResultSet rs = this.statement.executeQuery(sql);
    		Vector<String> ret_check = new Vector<String>();
    		while(rs.next()){
    			ret_check.add(rs.getString("semester_ID"));
    		}
    		return ret_check.get(0);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public void AddAndUpdateSeatOfThatScholarship_IDFromScholarship_type(int SeatValue, int Count){
    	String sql = "";
    	try{
    		if( Count == 1){
    			sql = "UPDATE scholarship_type SET seat = seat + "+SeatValue+" where scholarship_ID = 1";
    		}
    		else if(Count == 2){
    			 sql = "UPDATE scholarship_type SET seat = seat + "+SeatValue+" where scholarship_ID = 2";
    		}
    		else if(Count == 3){
    			sql = "UPDATE scholarship_type SET seat = seat - "+SeatValue+" where scholarship_ID = 1";
    		}
    		else if(Count == 4){
    			sql = "UPDATE scholarship_type SET seat = seat - "+SeatValue+" where scholarship_ID = 2";
    		}
    		this.statement.execute(sql);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    public String ReturnIsApprovedOfAStudent_ID(String student_id){
    	try{
    		String sql = "SELECT isApproved FROM `scholarship_information` WHERE sinfo_id in(select max(sinfo_id)"
    				+ " from scholarship_information group by student_ID order by sinfo_id desc )and isApplied = 1 "
    				+ "and student_ID = "+ student_id;
    		ResultSet rs = this.statement.executeQuery(sql);
    		Vector<Vector<String>> check = new Vector<Vector<String>>();
    		Vector<String> ret_check = new Vector<String>();
    		while(rs.next()){
        		ret_check.add(rs.getString("isApproved"));
        		check.add(ret_check);
    		}
    		return ret_check.get(0);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public Vector<String> ReturnStudent_IDWhosHaveMaxSemester(String max_sem){
    	try{
    		String sql = "SELECT student_ID FROM `scholarship_information` WHERE sinfo_id in(select max(sinfo_id)"
    		+ " from scholarship_information group by student_ID order by sinfo_id desc )and isApplied = 1 and semester_ID = "+max_sem;
    		ResultSet rs = this.statement.executeQuery(sql);
    		Vector<Vector<String>> check = new Vector<Vector<String>>();
    		Vector<String> ret_check = new Vector<String>();
    		while(rs.next()){
        		ret_check.add(rs.getString("student_ID"));
        		check.add(ret_check);
    		}
    		return ret_check;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public void UpdateIsApprove0to1OfThisStudent_IDs(ArrayList<String> student_id){
    	try{
    		for(int i = 0; i <student_id.size(); i++){
    			String a = student_id.get(i);
    			String sql = "update scholarship_information set isApproved = 1 WHERE student_ID = " + a;
    			this.statement.execute(sql);
    		}
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    public String ReturnSeatFromScholarship_typeWhereScholarship_ID(String scholarship_id){
    	try{
    		String sql = "select seat from scholarship_type where scholarship_ID = " + scholarship_id;
    		ResultSet rs = this.statement.executeQuery(sql);
    		Vector<String> ret_check = new Vector<String>();
    		while(rs.next()){
    			ret_check.add(rs.getString("seat"));
    		}
    		return ret_check.get(0);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public void UpdateAndDecreaseSeatOfScholarship_typeWhereScholarship_ID(String scholarship_id){
    	try{
    		String sql = "UPDATE scholarship_type SET seat = seat - 1 where scholarship_ID = " + scholarship_id;
        	this.statement.execute(sql);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    public ArrayList<String> ReturnStudent_IDFromScholarship_informationWhereScholarship_IDAndStudentIds(String scholarship_id, ArrayList<String> student_id){
    	try{
    		ArrayList<String> check = new ArrayList<String>();
    		for(int i = 0; i<student_id.size(); i++){
    			String id = student_id.get(i);
    			String sql = "SELECT student_ID FROM `scholarship_information` WHERE sinfo_id in(select max(sinfo_id) "
    					+ "from scholarship_information group by student_ID order by sinfo_id desc ) AND isApproved = 0 "
    					+ "AND scholarship_ID = "+scholarship_id+" AND student_ID = "+id;
        		ResultSet rs = this.statement.executeQuery(sql);
        		while(rs.next()){
        			check.add(rs.getString("student_ID"));
        		}
    		}
    		return check;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public void UpdateAndAddSeatsOfScholarship_typeWhereScholarship_ID(String scholarship_id){
    	try{
    		String sql = "UPDATE scholarship_type SET seat = seat + 1 where scholarship_ID = " + scholarship_id;
    		this.statement.execute(sql);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    public ArrayList<String> ReturnStudent_IDsOfScholarship_informationWhereStudent_IDs(ArrayList<String> student_id){
    	try{
    		ArrayList<String> check = new ArrayList<String>();
    		for(int i = 0; i<student_id.size(); i++){
    			String id = student_id.get(i);
    			String sql = "SELECT student_ID FROM `scholarship_information` WHERE sinfo_id in(select max(sinfo_id) "
    					+ "from scholarship_information group by student_ID order by sinfo_id desc ) AND isApproved = 1 "
    					+ "AND student_ID = "+id;
        		ResultSet rs = this.statement.executeQuery(sql);
        		while(rs.next()){
        			check.add(rs.getString("student_ID"));
        		}
    		}
    		return check;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public ArrayList<String> ReturnStudent_IDFromScholarship_informationWhereScholarship_IDAndMaxSemesterOfThisStudent(String scholarship_id, String maximum_semester){
    	try{
    		ArrayList<String> check = new ArrayList<String>();
    		String sql = "SELECT student_ID from `scholarship_information` WHERE sinfo_id in(select max(sinfo_id)"
    				+ " from scholarship_information group by student_ID order by sinfo_id desc ) AND isApproved = 0 "
    				+ "AND check_valid = 1 AND scholarship_ID = "+ scholarship_id + " AND semester_ID = "+ maximum_semester;
    		ResultSet rs = this.statement.executeQuery(sql);
    		while(rs.next()){
    			check.add(rs.getString("student_ID"));
    		}
    		return check;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public void UpdateCheck_Valid0To1OfScholar_informationWhereStudent_IDs(ArrayList<String> student_id){
    	try{
    		for(int i = 0; i<student_id.size(); i++){
    			String id = student_id.get(i);
    			String sql = "UPDATE scholarship_information SET check_valid = 1 where student_ID = " + id;
        		this.statement.execute(sql);
    		}
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    public String ReturnSpecial_sectionFromStudent_informationWhereStudent_ID(String student_id){
    	try{
    		ArrayList<String> check = new ArrayList<String>();
    		String sql = "select special_section from student_information where student_ID = "+ student_id;
    		ResultSet rs = this.statement.executeQuery(sql);
    		while(rs.next()){
    			check.add(rs.getString("special_section"));
    		}
    		return check.get(0);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public String ReturnScholarship_NameFromScholarship_typeWhereScholarship_ID(int scholarship_id){
    	try{
    		ArrayList<String> check = new ArrayList<String>();
    		String sql = "select scholarship_name from scholarship_type where scholarship_ID = "+ scholarship_id;
    		ResultSet rs = this.statement.executeQuery(sql);
    		while(rs.next()){
    			check.add(rs.getString("scholarship_name"));
    		}
    		return check.get(0);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public int getRows(ResultSet rs){
    	int num=0;
    	try{
	    	rs.last();
			num= rs.getRow();
			rs.beforeFirst();
    	}
    	catch(Exception ex){
    		return num;
    	}
		return num;
    }
    public String ReturnIsAppliedFromScholarship_informationWhereStudent_IDAndSemester_ID(String student_id, int semester_id){
    	String sig= "0";
    	try{
    		ArrayList<String> check = new ArrayList<String>();
    		String sql = "select isApplied from scholarship_information WHERE sinfo_id in(select max(sinfo_id) "
    				+ "from scholarship_information group by student_ID order by sinfo_id desc )"
    				+ " AND student_ID = "+ student_id +" AND semester_ID = " +semester_id;
    		ResultSet rs = this.statement.executeQuery(sql);
    		if(getRows(rs)==1){
	    		while(rs.next()){
		    			check.add(rs.getString("isApplied"));
		    			sig="1";
		    	}
    		}
    		return sig;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public String ReturnStudent_nameFromStudent_informationWhereStudent_ID(String student_id){
    	try{
    		ArrayList<String> check = new ArrayList<String>();
    		String sql = "select student_name from student_information where student_ID = " + student_id;
    		ResultSet rs = this.statement.executeQuery(sql);
    		while(rs.next()){
    			check.add(rs.getString("student_name"));
    		}
    		return check.get(0);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public String ReturnDepartmentFromStudent_academic_informationWhereStudent_ID(String student_id){
    	try{
    		ArrayList<String> check = new ArrayList<String>();
    		String sql = "select program from student_academic_information where student_ID = " + student_id;
    		ResultSet rs = this.statement.executeQuery(sql);
    		while(rs.next()){
    			check.add(rs.getString("program"));
    		}
    		return check.get(0);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public String ReturnSemester_nameFromSemesterWhereSemester_ID(String semester_id){
    	try{
    		ArrayList<String> check = new ArrayList<String>();
    		String sql = "select semester_name from semester where semester_ID = " + semester_id;
    		ResultSet rs = this.statement.executeQuery(sql);
    		while(rs.next()){
    			check.add(rs.getString("semester_name"));
    		}
    		return check.get(0);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    public String ReturnOngoingSemester_IDFromsemester(){
    	try{
    		ArrayList<String> check = new ArrayList<String>();
    		String sql = "select max(semester_ID) s from semester";
    		ResultSet rs = this.statement.executeQuery(sql);
    		while(rs.next()){
    			check.add(rs.getString("s"));
    		}
    		return check.get(0);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    } 
}
