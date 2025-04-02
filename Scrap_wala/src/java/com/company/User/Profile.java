import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Profile", urlPatterns = {"/Profile"})
public class Profile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Retrieve session
            HttpSession session = request.getSession(false);

            if (session != null) {
                // Fetch user details from session
                String id = (String) session.getAttribute("id");
                String name = (String) session.getAttribute("name");
                String mobile = (String) session.getAttribute("mobile");
                String email = (String) session.getAttribute("email");
                String address = (String) session.getAttribute("address");
                String city = (String) session.getAttribute("city");

                // Generate HTML response
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>User Profile</title>");
                out.println("<style>");
                out.println("body {");
                out.println("    font-family: Arial, sans-serif;");
                out.println("    margin: 0;");
                out.println("    padding: 0;");
                out.println("    background-image: url('Images/disco.png');");
                out.println("    background-size: cover;");
                out.println("    background-position: center;");
                out.println("    color: #fff;");
                out.println("}");
                out.println(".container {");
                out.println("    max-width: 800px;");
                out.println("    margin: 50px auto;");
                out.println("    padding: 20px;");
                out.println("    background-color: rgba(0, 0, 0, 0.7);");
                out.println("    border-radius: 10px;");
                out.println("    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);");
                out.println("}");
                out.println("h1 {");
                out.println("    text-align: center;");
                out.println("    margin-bottom: 20px;");
                out.println("    color: #4CAF50;");
                out.println("}");
                out.println("table {");
                out.println("    width: 100%;");
                out.println("    border-collapse: collapse;");
                out.println("    margin: 20px 0;");
                out.println("}");
                out.println("th, td {");
                out.println("    padding: 15px;");
                out.println("    text-align: left;");
                out.println("}");
                out.println("th {");
                out.println("    background-color: #4CAF50;");
                out.println("    color: white;");
                out.println("}");
                out.println("td {");
                out.println("    background-color: #f4f4f4;");
                out.println("    color: #333;");
                out.println("}");
                out.println("a {");
                out.println("    text-decoration: none;");
                out.println("    color: white;");
                out.println("    background-color: #4CAF50;");
                out.println("    padding: 10px 20px;");
                out.println("    border-radius: 5px;");
                out.println("    display: inline-block;");
                out.println("}");
                out.println("a:hover {");
                out.println("    background-color: #45a049;");
                out.println("}");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container'>");
                out.println("<h1>User Profile</h1>");
                out.println("<table>");
                out.println("<caption style='font-size: 20px; margin-bottom: 10px;'>Your Details</caption>");
                out.println("<tr>");
                out.println("    <th>ID</th>");
                out.println("    <th>Name</th>");
                out.println("    <th>Email</th>");
                out.println("    <th>Mobile</th>");
                out.println("    <th>Address</th>");
                out.println("    <th>City</th>");
                out.println("    <th>Edit</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("    <td>" + id + "</td>");
                out.println("    <td>" + name + "</td>");
                out.println("    <td>" + email + "</td>");
                out.println("    <td>" + mobile + "</td>");
                out.println("    <td>" + address + "</td>");
                out.println("    <td>" + city + "</td>");
                out.println("    <td><a href='Uedit?id=" + id + "'>");
                out.println("        <img src='Images/edit (2).png' width='30px' height='30px' alt='Edit'>");
                out.println("    </a></td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<div style='text-align: center; margin-top: 20px;'>");
                out.println("<a href='Logout.html'>Logout</a>");
                out.println("</div>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            } else {
                // If session is expired or not found
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head><title>Session Expired</title></head>");
                out.println("<body style='font-family: Arial, sans-serif; text-align: center; margin-top: 50px;'>");
                out.println("<h2>Session expired. Please <a href='index.html' style='color: #4CAF50;'>log in</a> again.</h2>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (Exception e) {
            // Handle errors
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head><title>Error</title></head>");
                out.println("<body style='font-family: Arial, sans-serif; text-align: center; margin-top: 50px;'>");
                out.println("<h2>An error occurred: " + e.getMessage() + "</h2>");
                out.println("</body>");
                out.println("</html>");
            }
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
        return "User Profile Servlet";
    }
}
