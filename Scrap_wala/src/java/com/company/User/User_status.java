import com.company.Connection.MyConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "User_status", urlPatterns = {"/User_status"})
public class User_status extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Connection cn = MyConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM user WHERE status = ?");
            ps.setBoolean(1, true);  // Fetch only active users
            ResultSet rs = ps.executeQuery();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>User Details</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }");
            out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            out.println("th, td { padding: 10px; text-align: left; border: 1px solid #ccc; }");
            out.println("th { background-color: #4CAF50; color: white; }");
            out.println("tr:hover { background-color: #f5f5f5; }");
            out.println("h2 { color: #333; }");
            out.println("form { margin-top: 20px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>User Details</h2>");
            out.println("</form>");

            out.println("<table>");
            out.println("<tr><th>Id</th><th>Name</th><th>Mobile</th><th>City</th><th>Status</th><th>Details</th></tr>");

            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String mobile = rs.getString(4);
                String city = rs.getString(7);
                Boolean status = rs.getBoolean(8);
                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + mobile + "</td>");
                out.println("<td>" + city + "</td>");
                out.println("<td>" + status + "</td>");
                out.println("<td><a href='details.jsp?id=" + id + "'>More Details</a></td>"); 
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
            cn.close();
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
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
        return "User status servlet";
    }
}
