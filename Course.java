/**
 * 
 * @author Mariana, Payal, Rudra
 * This class contains the private data, getters and setters, constructor, and the toString function.
 *
 */
public class Course {
	private String courseNumber;
	private String courseTitle;
	private String courseCredits;
	public Course() {
		courseNumber = "";
		courseTitle = "";
		courseCredits = "";
		
	}
	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getCourseCredits() {
		return courseCredits;
	}

	public void setCourseCredits(String credits) {
		this.courseCredits = credits;
	}
	Course (String courseNumber, String courseTitle, String courseCredits){
		setCourseNumber(courseNumber);
		setCourseTitle(courseTitle);
		setCourseCredits(courseCredits);
	}
	public String toString(){
		return String.format("%s %s %d", courseNumber, courseTitle, courseCredits);
	}
}
