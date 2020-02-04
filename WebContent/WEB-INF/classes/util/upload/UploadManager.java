package util.upload;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class UploadManager {
  
  private final String UPLOAD_PATH = "upload";

  public void upload(String ctxPath, HttpServletRequest request) throws IOException, ServletException {
    String path = getPath(ctxPath);
    try {
      for(Part part : request.getParts()) {
        String fileName = part.getSubmittedFileName();
        part.write(path + File.separator + fileName);
      }
    }catch(IOException | ServletException e) {
      e.printStackTrace();
    }
  }
  
  private String getPath(String ctxPath) {
    String uploadPath = ctxPath + UPLOAD_PATH;
    File uploadDir = new File(uploadPath);
    if(!uploadDir.exists()) {
      uploadDir.mkdir(); //create directory
    }
    return uploadPath;
  }
}
