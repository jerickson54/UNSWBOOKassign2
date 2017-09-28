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
import java.net.URL;
import java.util.List;

public class UploadCommand implements Command{
    private static final long serialVersionUID = 1L;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //save dynamically based on class location
        //URL url = Friends.class.getResource("../../../img/profile_pictures/");
        //String file = url.getPath();
        //System.out.println(file);

        //String file = request.getSession().getServletContext().getRealPath("/WebContent/img/profile_pictures");
        File file  = new File("/Users/jason/Desktop/UNSWBOOKassign2/Profile_photos");
        //ServletContext context = request.getServletContext();
        //String path = context.getRealPath("/img/profile_picture");
        //System.out.println(path);

        final String UPLOAD_DIRECTORY = file.toString();
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
        return "default.jsp";
    }
}
