import com.company.Connection.MyConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/Uedit"})
public class Uedit extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        try {
            String id = request.getParameter("id");
            String name = "", mobile = "", email = "", address = "", city = "";

            Connection cn = MyConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM user WHERE user_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                name = rs.getString(2);
                email = rs.getString(3);
                mobile = rs.getString(4);
                address = rs.getString(5);
                city = rs.getString(7);
            }

            // Start HTML structure
            pw.println("<html><head>");
            pw.println("<title>Edit User</title>");
            pw.println("<style>");
            pw.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }");
            pw.println(".container { max-width: 600px; margin: auto; background: white; padding: 20px; border-radius: 5px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); }");
            pw.println("h2 { text-align: center; color: #333; }");
            pw.println("label { display: block; margin: 10px 0 5px; }");
            pw.println("input[type='text'] { width: 100%; padding: 10px; margin-bottom: 10px; border: 1px solid #ccc; border-radius: 4px; }");
            pw.println("input[type='submit'] { background-color: #4CAF50; color: white; border: none; padding: 10px 15px; border-radius: 5px; cursor: pointer; }");
            pw.println("input[type='submit']:hover { background-color: #45a049; }");
            pw.println(".error { color: red; text-align: center; }");
            pw.println("</style>");
            pw.println("</head><body>");

            // Form for editing user details
            pw.println("<div class='container'>");
            pw.println("<h2>Edit User</h2>");
            pw.println("<form action='Uupdate' method='post'>");
            pw.println("<input type='text' name='id' value='" + id + "' readonly>");
            pw.println("<label for='name'>Name:</label>");
            pw.println("<input type='text' name='name' value='" + name + "' required>");
            pw.println("<label for='email'>Email:</label>");
            pw.println("<input type='text' name='email' value='" + email + "' required>");
            pw.println("<label for='mobile'>Mobile:</label>");
            pw.println("<input type='text' name='mobile' value='" + mobile + "' required>");
            pw.println("<label for='address'>Address:</label>");
            pw.println("<input type='text' name='address' value='" + address + "' required>");
            pw.println("<label for='city'>City:</label>");
            pw.println("<input type='text' name='city' value='" + city + "' required>");
            pw.println("<input type='submit' value='Update'>");
            pw.println("</form>");
            pw.println("</div>");

            // Close the connection
            cn.close();
        } catch (Exception e) {
            pw.println("<div class='error'>Error: " + e.getMessage() + "</div>");
        } finally {
            pw.println("</body></html>");
            pw.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "User Edit Servlet";
    }
}
