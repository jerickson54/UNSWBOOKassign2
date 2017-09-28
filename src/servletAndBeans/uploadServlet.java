package servletAndBeans;

import java.io.*;
import java.net.URL;
import java.util.*;
 
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

import db.Friends;



/**
 * Servlet implementation class uploadServlet
 */
@WebServlet("/uploadServlet")
public class uploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//TODO: Include this in the RegisterCommand
		//save dynamically based on class location
		URL url =Friends.class.getResource("../../../img/profile_pictures/");
		String file = url.getPath();
		//System.out.println(file);
		
		ServletContext context = request.getServletContext();
		String path = context.getRealPath("/img/profile_picture");
		System.out.println(path);

		final String UPLOAD_DIRECTORY = file;	
		//process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        String location = file + name;
                        //store location into the database for a user!!
                        //System.out.println(location);
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                    }
                }
           
               //File uploaded successfully
               System.out.println("File uploaded successfully!");
            } catch (Exception ex) {
               System.out.println("File failed due to " + ex);
            }          
         
        }else{
        	 System.out.println("This servlet only uploads the file.");
        }
	      //request.getRequestDispatcher("/login.jsp").forward(request,response);
	     
	}
	*/
	
}
