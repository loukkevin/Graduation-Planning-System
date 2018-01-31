package kevin.project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class ParseService {
String url;
Document doc; 
List<Course> courseOk;
List<String> courseNotOk;
List<String> courseNotOkTitle;
List<Requirement> requirements;
List<Elective> electives;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public ProgramRequirements parse(String url) throws IOException {
		
                File input = new File (url);
                doc = Jsoup.parse(input, "UTF-8");
		//url = "https://webproc.mnscu.edu/darsia/bar?jobQSeqNo=B6RuercLEqTlEvpnuatkcg%3D%3D&job_id=QKoEMdNUU%2F7wpwAxlSakJ%2F92V68PvRA5";//justins DARS
        //url = "https://webproc.mnscu.edu/darsia/bar?jobQSeqNo=wnxu3hAexFZAYTgc40imxw%3D%3D&job_id=QKoEMdNUU%2F4BeyGSmZzwzAuq6PDTEFUk";//kevin's DARS
		//doc = Jsoup.connect(url).get();
		System.out.println("DARS Parsing");
		courseTaken();

		displayCourseTaken();
		
		courseNotTaken();

		ProgramRequirements progReqs = new ProgramRequirements(requirements,electives,courseOk);
            String requirementsJson = new Gson().toJson(requirements);
            String electivesJson = new Gson().toJson(electives);
            
            System.out.println("requirementsJson:\n" + requirementsJson);
            System.out.println("electivesJson:\n" + electivesJson);
		return progReqs;
	}
        
	private void courseNotTaken() {
                requirements = new ArrayList();
                electives = new ArrayList();
		courseNotOk = new ArrayList();
		courseNotOkTitle = new ArrayList();
		Elements notTakenTitle = doc.select("span[class=auditLineType_17_noSubrequirementTLine]");
		Elements notTaken = doc.select("span[class=auditLineType_29_noSubrequirementAcceptCourses]");
		System.out.println("********************");
		System.out.println("\nCourses not taken");
		System.out.println("********************");
		for(Element course:notTaken) {
			String cNotTaken = course.text();
			courseNotOk.add(cNotTaken);	
		}
		
		for(Element title:notTakenTitle) {
			String requiredTitle = title.text();//;replaceAll("[0-9]+|\\-|\\)","").trim();
                        //System.out.println(requiredTitle);
			courseNotOkTitle.add(requiredTitle);
                        
                        if (!title.ownText().contains("OR") && 
                                !title.previousElementSibling().ownText().contains("OR") && 
                                !title.ownText().contains("Complete")){  
                        Requirement req = new Requirement(title);
                        System.out.println(req.getTitle());
                        System.out.println(req.getRequiredCourse().getName());
                        if (req.getRequiredCourse().getName().length() > 4 && req.getRequiredCourse().getName().length() < 8)//to avoid adding garbage to arrayList
                        requirements.add(req);
                        }
                        if ((title.ownText().contains("OR") || title.ownText().contains("Complete")) && !title.className().equals(title.previousElementSibling().className())){
                        Elective elective = new Elective(title);
                        //System.out.println(elective.getTitle());
                        //System.out.println(elective.getCourse().getTitle);
                        electives.add(elective);
                        }
		}
	
	}

	/**
	 * 
	 */
	private void displayCourseTaken() {
		for(int i = 0; i<courseOk.size(); i++) {		
			if(courseOk.get(i).equals("NAT:ELEC")) {
			courseOk.remove(i);
			} else if(courseOk.get(i).equals("CHEDAS:")) {
			courseOk.remove(i);}
			else if(courseOk.get(i).equals("NAT:TECH")) {
				courseOk.remove(i);}
			else {
				System.out.println(courseOk.get(i).getName());
			}
		}//en outforloop
	}

	/**
	 *  Course Taken
	 */
	private void courseTaken() {
		courseOk = new ArrayList();
		Elements ctable = doc.select("span[class=auditLineType_22_okSubrequirementCourses]");
		System.out.println("********************");
		System.out.println("Courses that have been taken");
		System.out.println("********************");
		for(Element course:ctable) {
			String cTaken = course.text().substring(5,14).replaceAll(" ", "");
			//System.out.println(cTaken);
			Course newCourse = new Course(cTaken,null,0,"description");
			courseOk.add(newCourse);	
		}
	}

}
