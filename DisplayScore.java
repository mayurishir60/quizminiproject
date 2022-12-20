import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class DisplayScore {
	
	Connection connection = null;
	PreparedStatement ps = null;
	
	void Score() throws SQLException {
	
	try {
		
	ConnectionTest test = new ConnectionTest();
	connection =test.getConnectionDetails();
	
	ps = connection.prepareStatement("select * from student");
	// Submit the SQL statement to Database
	ResultSet rs = ps.executeQuery();
	
	while(rs.next()) {
		
		System.out.println("student id:" + rs.getInt(1));
		System.out.println("student Name:" + rs.getString(2));
		System.out.println("student Score :" + rs.getInt(3));
		System.out.println("----------------------------------------------");
	
	}
	
	// display student  Score from id
	Scanner sc = new Scanner(System.in);
	
	System.out.println("Enter User Id");
	int userId = sc.nextInt();
	
	// Create the statement
	ps = connection.prepareStatement("select * from student where id = ?");
	ps.setInt(1, userId);
	
	// Submit the SQL statement to Database
	ResultSet r = ps.executeQuery();
	
	while(r.next()) {
		
		System.out.println("student Name: " + r.getString(2));
		System.out.println("student Score :" + r.getInt(3));
		
	}

	r.close();
	
	} catch (Exception e) {
		System.out.println(e);
	} finally {
		
		connection.close();
		ps.close();
	}
  }
}