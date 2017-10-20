package servletAndBeans;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONwriter {

	
	public static void main(String args[]){
		
		ObjectMapper mapper = new ObjectMapper();
		
		nodeD3 n1 = new nodeD3("Lebron James",1, "5/5/5", "Male", "theKING");
		
		nodeD3 n2 = new nodeD3("Derrick Rose",2, "1/1/1", "Male", "baller");
		nodeD3 n3 = new nodeD3("Kevin Love",2, "2/2/2", "Male", "LOVE");
		nodeD3 n4 = new nodeD3("Steph Curry",3, "4/4/4", "Male", "theChef");
		nodeD3 n5 = new nodeD3("Kevin Durant",3,"4/4/4", "Male", "Thunder Betrayal");
		nodeD3 n6 = new nodeD3("James Harden",4, "1/12/12" , "Male" , "bestBeard2017");
		
		
		
		nodeD3 [] allNodes = new nodeD3[6];
		allNodes[0] = n1;
		allNodes[1] = n2;
		allNodes[2] = n3;
		allNodes[3] = n4;
		allNodes[4] = n5;
		allNodes[5] = n6;
		
		//links are of this form: source, target,weight
		//FOLLOWS ORDER OF THE NODES
		//meaning 0 = n1;
		//1 = n2
		linkD3 l1 = new linkD3(2,1,1);
		linkD3 l2 = new linkD3(0,2,1);
		linkD3 l3 = new linkD3(3,4,1);
		
		linkD3[] allLinks = new linkD3[3];
		allLinks[0] = l1;
		allLinks[1] = l2;
		allLinks[2] = l3;
		
		JSOND3object toJSON = new JSOND3object(allNodes,allLinks);
		
		
		
		
		try{
			
		
			File dumbPATHS = new File("");
			String location = dumbPATHS.getCanonicalPath() + "/WebContent//toDisplay.json";
			System.out.println(location);
			File toCreate = new File(location);
			mapper.writeValue(toCreate, toJSON);
			
			
			
		}catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
}