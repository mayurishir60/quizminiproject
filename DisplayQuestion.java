import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DisplayQuestion implements Quiz {

	 Connection connection = null;
	 PreparedStatement ps = null;	
	 
	@Override
	public void question() throws SQLException {
		
		int CountRightAnswer=0;
		int a=1;
		int CountWrongAnswer=0;
		int score=0;
		
		try {
				
			ConnectionTest test = new ConnectionTest();
			connection	 = test.getConnectionDetails();
			
			
			 ps = connection.prepareStatement("Select*from question ORDER BY RAND()");
			
			ResultSet rs = ps.executeQuery();
			
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter user name");
			String name = scan.next();
			
			System.out.println("------------- * Start Quiz *---------------");
			while(rs.next()) {


				System.out.println("Question no "+a+":"+rs.getString("Question"));
				System.out.println(" A :"+rs.getString("A"));
				System.out.println(" B :"+rs.getString("B"));
				System.out.println(" C :"+rs.getString("C"));		
				System.out.println(" D :"+rs.getString("D"));
				System.out.println("-----------------------------");
					
				String useranswer = "";

	            char ans;
	            System.out.println("Enter your answer");
	            Scanner sc = new Scanner(System.in);
	            ans = sc.next().charAt(0);

	            switch(ans)
	            {
	                case 'A':
	                	useranswer = rs.getString("A");
	                    break;
	                case 'B':
	                	useranswer = rs.getString("B");
	                    break;
	                case 'C':
	                	useranswer = rs.getString("C");
	                    break;
	                case 'D':
	                	useranswer = rs.getString("D");
	                    break;
	                default:break;
	            }
				
				if(useranswer.equals(rs.getString("answer"))) {
					System.out.println(" Correct Answer");
					CountRightAnswer++;
					a++;
					score++;
					
			}
				else{
					
				    System.out.println("Wrong Answer");	  
				    CountWrongAnswer++;
				    a++;
			}	
		}		
		
			System.out.println("Correct Answer "+CountRightAnswer);
			System.out.println("Wrong Answer "+CountWrongAnswer);
			System.out.println("Total Score "+score);
				
			//Prepare statement
			 ps = connection.prepareStatement("insert into student(studentname,studentscore)values(?,?,?)");
			 
			 ps.setString(1, name);
			 ps.setInt(2 , score);
			 
	         int b = ps.executeUpdate();
	         System.out.println("Record Inserted "+b);
	    	
	    	 rs.close();
		
	    	 if(score > 8){
		            System.out.println("  Grade  A");
		        }
		        else if(score > 6){
		            System.out.println("Grade B");
		        }
		        else if(score < 5) {
		        	System.out.println("Grade C ");
		        }
		         if(score < 3){
		            System.out.println(" Fail ");
		        }	
		         
		        System.out.println(".......................................................");
				}catch(Exception e)	 {
			System.out.println(e);
		}finally {
			ps.close();
			connection.close();
		}
		}
			
	
public static void main(String[] args) throws SQLException {
	
	
	DisplayQuestion display = new DisplayQuestion();
	display.question();
	
	DisplayScore score = new DisplayScore();
	score.Score();
	
}

}
