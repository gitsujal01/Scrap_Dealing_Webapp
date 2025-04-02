import com.company.Connection.MyConnection;
import java.io.IOException; 
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Ufetch")
public class Ufetch extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html;charset=UTF-8");

        try {
            Connection cn = MyConnection.getConnection();
            // Create prepared statement
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM user");
            ResultSet rs = ps.executeQuery();

            // Start HTML structure
            pw.println("<html><head>");
            pw.println("<title>User Details</title>");
            pw.println("<style>");
            pw.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }");
            pw.println(".container { max-width: 800px; margin: auto; background: white; padding: 20px; border-radius: 5px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); }");
            pw.println("h2 { text-align: center; color: #333; }");
            pw.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            pw.println("th, td { border: 1px solid #4CAF50; padding: 10px; text-align: left; }");
            pw.println("th { background-color: #4CAF50; color: white; }");
            pw.println("caption { font-size: 1.5em; margin: 10px; }");
            pw.println("a img { width: 20px; height: 20px; }");
            pw.println(".error { color: red; text-align: center; }");
            pw.println("</style>");
            pw.println("</head><body>");

            // Table for item details
            pw.println("<div class='container'>");
            pw.println("<h2>User Details</h2>");
            pw.print("<a href = 'Home' style = 'display:block;text-align :center;margin-bottom:20px;font-size:18px;color:#4CAF50;'>Go to Home</a>");
            pw.println("<table>");
            pw.println("<tr><th>Id</th><th>Name</th><th>Email</th><th>Mobile</th><th>Address</th><th>City</th><th>Edit</th></tr>");

            boolean hasItems = false;
            while (rs.next()) {
                hasItems = true;
                String id = rs.getString(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String mobile = rs.getString(4);
                String address = rs.getString(5);
                String city = rs.getString(7);
                pw.println("<tr>");
                pw.println("<td>" + id + "</td>");
                pw.println("<td>" + name + "</td>");
                pw.println("<td>" + email + "</td>");
                pw.println("<td>" + mobile + "</td>");
                pw.println("<td>" + address + "</td>");
                pw.println("<td>" + city + "</td>");
                pw.println("<td><a href='Uedit?id=" + id + "'><img src='Images/edit (2).png' alt='Edit'></a></td>");
                pw.println("</tr>");
            }

            if (!hasItems) {
                pw.println("<tr><td colspan='7' class='error'>No users found.</td></tr>");
            }
            pw.println("</table>");
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
}
