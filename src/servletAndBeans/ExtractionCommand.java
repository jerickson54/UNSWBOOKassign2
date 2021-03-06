package servletAndBeans;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.bag.SynchronizedSortedBag;

import db.messages;
import db.messagesDAO;
import unsw.curation.api.extractnamedentity.ExtractEntitySentence;
import unsw.curation.api.tokenization.ExtractionKeywordImpl;






public class ExtractionCommand implements Command {
	
	public static void doExtraction(){
		//later replace with own use
		String type = "Test is Bully";
		
		String content = "Bill Gates is an interesting fellow. He likes New York. He founded Microsoft. Oregon. Antagonistic";
		ExtractEntitySentence fSentence = new ExtractEntitySentence();
		List<String> nothing = new ArrayList<String>();
		nothing.add("Sorry no results");
		
		if(type.equals("Extract Keywords")){
			ExtractionKeywordImpl ek = new ExtractionKeywordImpl();
			URL url =ExtractionCommand.class.getResource("/");

			String file = url.getPath() + "servletAndBeans/englishStopwords.txt";
			
		
			
			try {
				String keys = ek.ExtractSentenceKeyword(content, new File(file));
				List<String> keywords = Arrays.asList(keys.split("\\s*,\\s*"));
				for(String s : keywords)
					System.out.println(s);
				
				
			} catch (Exception e) {
				System.out.println(nothing.get(0));
			}
		}
		
		if(type.equals("Test is Bully")){
			ExtractionKeywordImpl ek = new ExtractionKeywordImpl();
			URL url =ExtractionCommand.class.getResource("/");

			String file = url.getPath() + "servletAndBeans/bullFlagWords.txt";
			String file2 = url.getPath() + "servletAndBeans/comparison.txt";
			
		
			
			try {
				String keys = ek.ExtractSentenceKeyword(content, new File(file));
				List<String> keywords = Arrays.asList(keys.split("\\s*,\\s*"));
				
				String keysComparison = ek.ExtractSentenceKeyword(content, new File(file2));
				List<String> keywordsComparison = Arrays.asList(keysComparison.split("\\s*,\\s*"));
				
				if(keywords.size() != keywordsComparison.size())
					System.out.println("I contain harmful information");
				
				else
					System.out.println("Dont worry about it");
				
				
			} catch (Exception e) {
				System.out.println(nothing.get(0));
			}
		}
		
		if(type.equals("Extract People")){
			try {
				List<String> person = fSentence.ExtractPerson(content);
				for(String p: person)
					System.out.println(p);
			} catch (URISyntaxException e) {
				System.out.println(nothing.get(0));
			}
		}

		if(type.equals("Extract Organizations")){
			try {
				List<String> organizations = fSentence.ExtractOrganization(content);
				for(String o: organizations)
					System.out.println(o);
			} catch (URISyntaxException e) {
				System.out.println(nothing.get(0));
			}
		}

		if(type.equals("Extract Locations")){
			try {
				List<String> locations = fSentence.ExtractLocation(content);
				for(String l:locations)
					System.out.println(l);
			} catch (URISyntaxException e) {
				System.out.println(nothing.get(0));
			}
		}
		
		
		
	}
	
	public extractionResultsBean setupBean(List<String> listSet){
		extractionResultsBean b = new extractionResultsBean();
		b.setExtractionResults(listSet);
		return b;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String type = request.getParameter("type");

		String id = request.getParameter("currentID");
		
		String content = "";
		
		List<messages> allMessages = messagesDAO.search(id);
		for(messages m : allMessages){
			if(m.getMessage().length() > 42)
			content += m.getMessage().substring(0,m.getMessage().length()-42) + " ";
			else
				content+=m.getMessage() + " ";
		}

		
		
		ExtractEntitySentence fSentence = new ExtractEntitySentence();
		List<String> nothing = new ArrayList<String>();
		nothing.add("Sorry no results");
		
		if(type.equals("Extract Keywords")){
			ExtractionKeywordImpl ek = new ExtractionKeywordImpl();
			URL url =ExtractionCommand.class.getResource("/");

			String file = url.getPath() + "servletAndBeans/englishStopwords.txt";
			
		
			
			try {
				String keys = ek.ExtractSentenceKeyword(content, new File(file));
				List<String> keywords = Arrays.asList(keys.split("\\s*,\\s*"));
				extractionResultsBean Results = setupBean(keywords);
				request.setAttribute("results", Results);
				
				
				
			} catch (Exception e) {
				extractionResultsBean noResults = setupBean(nothing);
				request.setAttribute("results", noResults);
			}
		}
		
		if(type.equals("Extract People")){
			try {
				List<String> person = fSentence.ExtractPerson(content);
				extractionResultsBean Results = setupBean(person);
				request.setAttribute("results", Results);
					
			} catch (URISyntaxException e) {
				extractionResultsBean noResults = setupBean(nothing);
				request.setAttribute("results", noResults);
			}
		}

		if(type.equals("Extract Organizations")){
			try {
				List<String> organizations = fSentence.ExtractOrganization(content);
				extractionResultsBean Results = setupBean(organizations);
				request.setAttribute("results", Results);
			} catch (URISyntaxException e) {
				extractionResultsBean noResults = setupBean(nothing);
				request.setAttribute("results", noResults);
			}
		}

		if(type.equals("Extract Locations")){
			try {
				List<String> locations = fSentence.ExtractLocation(content);
				extractionResultsBean Results = setupBean(locations);
				request.setAttribute("results", Results);
			} catch (URISyntaxException e) {
				extractionResultsBean noResults = setupBean(nothing);
				request.setAttribute("results", noResults);
			}
		}
		
		return "extractionResults.jsp";
		
	}
	
	//public static void main(String args[]){
	//	doExtraction();
	//}

}
