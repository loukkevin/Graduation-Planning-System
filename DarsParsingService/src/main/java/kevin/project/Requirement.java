package kevin.project;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.jsoup.nodes.Element;

/**
 *
 * @author Kevin
 */
public class Requirement {
    String title;
    Course requiredCourse;
    
    public Requirement (Element titleElement){
        Element requirementCourse = titleElement.nextElementSibling();
        this.title = titleElement.ownText();
        String requirementTitleClassName = titleElement.className();
        boolean isCourseElement = false;
        while(isCourseElement == false){//Check to see if the title is contained in more than one element
            if (requirementTitleClassName.equals(requirementCourse.className())){
                title.concat(requirementCourse.ownText());
                requirementCourse = requirementCourse.nextElementSibling();
            }
            else
                isCourseElement = true;
        }
        String courseTitle = requirementCourse.ownText().replaceAll("SELECT FROM: ","");
        this.requiredCourse =  new Course (courseTitle,null,3,courseTitle);
        
        
    }
    
    public Course getRequiredCourse() {
		return requiredCourse;
	}

	public void setRequiredCourse(Course requiredCourse) {
		this.requiredCourse = requiredCourse;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle (){
        return title;
    }
}
