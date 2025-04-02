

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) 
public class ImgUpload extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            Part filePart = request.getPart("f"); 
            if (filePart == null || filePart.getSize() == 0) {
                response.getWriter().println("No image uploaded!");
                return;
            }

            InputStream inputStream = filePart.getInputStream(); 

            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scrap_pro", "root", "");

            
            String sql = "INSERT INTO images (ImageEntity) VALUES (?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setBinaryStream(1, inputStream, (int) filePart.getSize());

      
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                response.getWriter().println("✅ Image inserted successfully!");
            } else {
                response.getWriter().println("❌ Image insertion failed.");
            }

            
            pstmt.close();
            con.close();
        } catch (Exception e) {
            response.getWriter().println("⚠️ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
