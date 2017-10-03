package servletAndBeans;

import db.Friends;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class UploadCommand implements Command{
    private static final long serialVersionUID = 1L;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //save dynamically based on class location
        URL url = Friends.class.getResource("../../../img/profile_pictures/");
        String filePath = url.getPath();
        System.out.println(filePath);
        File file;
        
        //PrintWriter out = response.getWriter();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024*4);
        factory.setRepository(new File("c:\\temp"));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(100*1024);
        
        try{
        	List fileItems = upload.parseRequest(request);
        	Iterator i = fileItems.iterator();
        	while(i.hasNext()){
        		FileItem fi = (FileItem)i.next();
        		if(!fi.isFormField()){
        			String fieldName = fi.getFieldName();
        			String fileName = fi.getName();
        			String finalPath = "file:" + filePath + fileName.substring(fileName.lastIndexOf("\\") + 1);
        			//System.out.println(finalPath);
        			String contentType = fi.getContentType();
        			boolean isInMemory = fi.isInMemory();
        			long sizeInBytes = fi.getSize();
        			
        			if(fileName.lastIndexOf("\\") >= 0)
        					file = new File(filePath+fileName.substring(fileName.lastIndexOf("\\")));
        			else
        				file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")+1));
        			fi.write(file);
        			
        		}
        	}
        }catch(Exception ex){
        	System.out.println(ex);
        }
        	
        
        
        //request.getRequestDispatcher("/login.jsp").forward(request,response);
        return "default.jsp";
    }
}
