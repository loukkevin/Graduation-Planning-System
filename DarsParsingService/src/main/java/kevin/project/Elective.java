package kevin.project;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import org.jsoup.nodes.Element;

/**s
 *
 * @author Kevin
 */
public class Elective {

    String title;
    List<Course> electiveCourses;
    int numOfCredits = 0; 
    int numOfCourses = 0;  
    
    public List<Course> getElectiveCourses() {
		return electiveCourses;
	}

	public void setElectiveCourses(List<Course> electiveCourses) {
		this.electiveCourses = electiveCourses;
	}

	public int getNumOfCredits() {
		return numOfCredits;
	}

	public void setNumOfCredits(int numOfCredits) {
		this.numOfCredits = numOfCredits;
	}

	public int getNumOfCourses() {
		return numOfCourses;
	}

	public void setNumOfCourses(int numOfCourses) {
		this.numOfCourses = numOfCourses;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Elective (Element titleElement){
        Element electiveTitle = titleElement;
        Element electiveCoursesText = titleElement.nextElementSibling();
        this.title = titleElement.ownText();
        this.electiveCourses = new ArrayList();
        
        String electiveTitleClassName = electiveTitle.className();
        boolean isCourseElement = false;
        while(isCourseElement == false){//Check to see if the title is contained in more than one element
            if (electiveTitleClassName.equals(electiveCoursesText.className())){
                title = title.concat(" " + electiveCoursesText.ownText());
                electiveCoursesText = electiveCoursesText.nextElementSibling();
                
            }
            else
                isCourseElement = true;
        }
        
        //determine if the elective has a minimum credit requirement or a minimum courses requirement
        boolean done = false;
                
        if (title.contains("credits")){
            String[] temp = title.split(" ");
            String temp2;
            int index = 0;
            while (!done){
                temp2 = temp[index];
                if (temp2.contains("credits")){
                    this.numOfCredits = Integer.parseInt(temp[index-1]);
                    done = true;
                }
                index++;
            }
        }else if (title.contains("courses")){
            done = false;
            String[] temp = title.split(" ");
            String temp2;
            int index = 0;
            while (!done){
                temp2 = temp[index];
                if (temp2.contains("courses")){
                    this.numOfCourses = Integer.parseInt(temp[index-1]);
                    done = true;
                }
                index++;
            }
        }else
            this.numOfCourses = 1;
        
        //Retrieve courses and transform the data to be uniformly formatted
        String electiveCoursesString = electiveCoursesText.ownText().replaceAll("SELECT FROM: ","");
        if (electiveCoursesText.className().equals(electiveCoursesText.nextElementSibling().className()))
            electiveCoursesString += (" " + electiveCoursesText.nextElementSibling().ownText());

        System.out.println(title);
        String[] coursesTypeString = electiveCoursesString.split("[0-9|,| |(|)]+");
        String[] coursesStringSplit = electiveCoursesString.split("[,| |(|)]+");
        List<String> courseTypeList = new ArrayList();
        List<String> courses = new ArrayList();
        for (int i = 0; i < coursesTypeString.length; i++){
                courseTypeList.add(coursesTypeString[i]);
               // System.out.println(coursesTypeString[i]);
        }
        int index = 0;
        String courseType = coursesTypeString[index];
        String value;
        for (int i = 0; i < coursesStringSplit.length; i++){  
            value = coursesStringSplit[i];
            if (value.contains(courseType)){//first course of type
                if (value.length()>4){
                System.out.println(value);
                electiveCourses.add(new Course(value,null,3,value));
                }
            }
            else if (!value.contains(courseType)){//remaining
                String courseTypeAndNumber = courseType + value;
                if (courseTypeAndNumber.length() > 4){
                System.out.println(courseTypeAndNumber);
                electiveCourses.add(new Course(courseTypeAndNumber,null,3,courseTypeAndNumber));
                }
            }
          //check to see if next value contains the next course type
            if (index < (coursesTypeString.length-1) && coursesStringSplit[i+1].contains(coursesTypeString[index+1])){
                index++;
                courseType = coursesTypeString[index];
            }
        }
    }
    
    public String getTitle (){
        return title;
    }

}
