/**
 * 
 * @author Mariana, Payal, Rudra
 * This class grabs data from a website, creates objects from it, 
 * and stores them in an ArrayList.
 *
 */
 
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ScreenScraper {
	static ArrayList<Course> courses = new ArrayList<Course>();
	public static void main(String[] args) {
		System.out.print("Enter url: ");
		Scanner sc = new Scanner(System.in);
		String addr = "http://lewisu.smartcatalogiq.com/Undergrad-2018-2019/Undergraduate-Catalog/College-of-Arts-and-Sciences/Computer-Science/Computer-Science-Bachelor-of-Science";
		String linesCollection = "";
		String line;
		
		try {
			URL link = new URL(addr);
			Scanner linksc = new Scanner(link.openStream());
			while (linksc.hasNextLine()){
				line = linksc.nextLine();
				System.out.println(line);
				linesCollection += line;
			}
			courses =  convertLinesToCourses(linesCollection);
			linksc.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Could not connect to the website.");
		}	
		

	}	
	private static ArrayList<Course> convertLinesToCourses(String linesCollection){
		String[] websiteLines = linesCollection.split("<div id=\"degreeRequirements\">");
		String[] tableStrings = websiteLines[0].split("<table>");
		String[] tBodyStrings = tableStrings[0].split("<tbody>");
		String[] courseStrings = tBodyStrings[0].split("<tr>");
		for (String string : tBodyStrings) {
			System.out.println(tBodyStrings);
			System.out.println(Arrays.toString(tBodyStrings));
		}
		for (String strings: courseStrings) {
			System.out.print(courseStrings);
			System.out.println(Arrays.toString(courseStrings));
		}
		for(String course : tBodyStrings) {
			String[] elementStrings = course.split("<td");
			Course courseObj = new Course();
			for(String elementString : elementStrings) {
				String[] elements = elementString.split("\">");
				if(elements[0].contains("coursenumber")) {
				int endOfCourseNameIndex = elements[2].indexOf("</a></td><td");
				String classId = elements[2].substring(0, endOfCourseNameIndex);
				courseObj.setCourseNumber(classId);
				}
				if(elements[0].contains("coursetitle")) {
				int endOfCourseTitleIndex = elements[1].indexOf("</td><td");
				String classTitle = elements[1].substring(0, endOfCourseTitleIndex);
				courseObj.setCourseTitle(classTitle);
				}
				if(elements[0].contains("credits")) {
				String credits = elements[1].substring(0, 1);
				courseObj.setCourseCredits(credits);
				}
			}
			courses.add(courseObj);
		}
		for(Course course: courses) {
			System.out.printf("%s\t%s\t%s\n", course.getCourseNumber(), course.getCourseTitle(), course.getCourseCredits());
			
		}
		
		return courses;
		//return null;
		
	}
}
