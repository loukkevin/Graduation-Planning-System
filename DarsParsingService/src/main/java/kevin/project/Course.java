package kevin.project;

import java.util.ArrayList;
import java.util.List;

public class Course {
	String name;
	List<Course> prerequisites = new ArrayList();
	int credits;
	String description;
	public Course(String name, List<Course> prerequisites, int credits, String description) {
		super();
		this.name = name;
		this.prerequisites = prerequisites;
		this.credits = credits;
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Course> getPrerequisites() {
		return prerequisites;
	}
	public void setPrerequisites(List<Course> prerequisites) {
		this.prerequisites = prerequisites;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
