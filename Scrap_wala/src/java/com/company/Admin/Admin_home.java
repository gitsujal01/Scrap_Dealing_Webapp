import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/Admin_home"})
public class Admin_home extends HttpServlet {

    private static final String ERROR_MESSAGE = "Please Login First";
    private static final String PROFILE_PAGE = "Admin_profile.html";
    private static final String ADD_ITEMS_PAGE = "insert.html";
    private static final String SHOW_ITEMS_PAGE = "Afetch";
    private static final String USER_STATUS_PAGE = "User_status";
    private static final String LOGIN_PAGE = "index.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter pw = response.getWriter()) {
            HttpSession session = request.getSession(false); // Check if session exists

            // Start HTML content
            pw.println("<!DOCTYPE html>");
            pw.println("<html lang='en'>");
            pw.println("<head>");
            pw.println("<meta charset='UTF-8'>");
            pw.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            pw.println("<title>Admin Home</title>");
            pw.println("<style>");
            pw.println("body {");
            pw.println("    font-family: Arial, sans-serif;");
            pw.println("    background-image: url('Images/disco.png');");
            pw.println("    background-size: cover;");
            pw.println("    background-position: center;");
            pw.println("    margin: 0;");
            pw.println("    padding: 0;");
            pw.println("}");
            pw.println(".container {");
            pw.println("    max-width: 600px;");
            pw.println("    margin: 50px auto;");
            pw.println("    background: rgba(255, 255, 255, 0.9);");
            pw.println("    padding: 20px;");
            pw.println("    border-radius: 8px;");
            pw.println("    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);");
            pw.println("    text-align: center;");
            pw.println("}");
            pw.println("h2 {");
            pw.println("    color: #4CAF50;");
            pw.println("    margin-bottom: 20px;");
            pw.println("}");
            pw.println("a {");
            pw.println("    display: inline-block;");
            pw.println("    margin: 10px;");
            pw.println("    padding: 10px 20px;");
            pw.println("    text-decoration: none;");
            pw.println("    color: white;");
            pw.println("    background-color: #4CAF50;");
            pw.println("    border-radius: 5px;");
            pw.println("    transition: background-color 0.3s ease;");
            pw.println("}");
            pw.println("a:hover {");
            pw.println("    background-color: #45a049;");
            pw.println("}");
            pw.println(".error {");
            pw.println("    color: red;");
            pw.println("    font-size: 18px;");
            pw.println("    margin-bottom: 20px;");
            pw.println("}");
            pw.println("</style>");
            pw.println("</head>");
            pw.println("<body>");

            // Check session status
            if (session != null) {
                // If user is logged in
                pw.println("<div class='container'>");
                pw.println("<h2>Welcome, Admin</h2>");
                pw.println("<a href='" + PROFILE_PAGE + "'>Profile</a>");
                pw.println("<a href='" + ADD_ITEMS_PAGE + "'>Add Items</a>");
                pw.println("<a href='" + SHOW_ITEMS_PAGE + "'>Show Items</a>");
                pw.println("<a href='" + USER_STATUS_PAGE + "'>Show Requests</a>");
                pw.println("<a href='Logout.html'>Logout</a>");
                pw.println("</div>");
            } else {
                // If session is invalid or expired
                pw.println("<div class='container'>");
                pw.println("<h2 class='error'>" + ERROR_MESSAGE + "</h2>");
                pw.println("<a href='" + LOGIN_PAGE + "'>Go to Login</a>");
                pw.println("</div>");
            }

            // End HTML content
            pw.println("</body>");
            pw.println("</html>");
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
        return "Admin Home Servlet";
    }
}
