package util.upload;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.Part;

public class UploadManager {
  
  private static final String PATH = "upload";

  public void upload(String ctxPath, Collection<Part> parts) throws IOException {
    String path = getPath(ctxPath);
    for(Part part : parts) {
      String fileName = part.getSubmittedFileName();
      part.write(path + File.separator + fileName);
    }
  }
  
  private String getPath(String ctxPath) {
    String uploadPath = ctxPath + PATH;
    File uploadDir = new File(uploadPath);
    if(!uploadDir.exists()) {
      uploadDir.mkdir(); //create directory
    }
    return uploadPath;
  }
}
