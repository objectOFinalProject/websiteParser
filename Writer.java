import org.json.simple.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 
 * @author Payal, Mariana, Rudra
 * This class is responsible for saving the course
 * information, given through an ArrayList, to a
 * text file and a JSON file.
 *
 */
public class Writer {
	/**
	 * 
	 * @param courses list of courses, given by ScreenScraper class
	 */
	public void writeToText(ArrayList<Course> courses) {
		try {
			//creates text-output stream
			PrintWriter writer = new PrintWriter(new BufferedWriter
					(new FileWriter("output.txt")));
			
			for(Course c: courses) {
				writer.println(c);
			}
			
			writer.close();
		}
		catch(Exception e) {
			System.out.println("Something went wrong...");
		}		
	}
	
	/**
	 * 
	 * @param courses list of courses, given by ScreenScraper class
	 */
	@SuppressWarnings("unchecked")
	public void writeToJSON(ArrayList<Course> courses) {
		try {
			PrintWriter writer = new PrintWriter(new BufferedWriter(
					new FileWriter("output.json")));
			
			JSONObject courseObj;
			JSONArray array = new JSONArray();
			
			for(Course c: courses) {
				courseObj = new JSONObject();
				courseObj.put("course number", c.getCourseNumber());
				courseObj.put("course title", c.getCourseTitle());
				courseObj.put("course credits", c.getCourseCredits());
				
				
				array.add(courseObj);
			}
			
			JSONObject outer = new JSONObject();
			outer.put("courses", array);
			writer.println(outer.toString());
			writer.close();
		}
		catch(Exception e) {
			System.out.println("Something went wrong...");
		}
	}
}