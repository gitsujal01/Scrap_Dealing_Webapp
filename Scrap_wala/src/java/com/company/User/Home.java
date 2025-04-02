import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/Home"})
public class Home extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession(false);

        try {
            pw.println("<html><head>");
            pw.println("<title>Home Page</title>");
            pw.println("<style>");
            pw.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; ");
            pw.println("background-image: url('Images/disco.png'); background-size: cover; background-position: center; ");
            pw.println("color: white; text-align: center; }");
            pw.println(".content { background-color: rgba(0, 0, 0, 0.6); padding: 20px; border-radius: 10px; display: inline-block; margin: 50px auto; }");
            pw.println("h2 { color: #fff; }");
            pw.println("a { text-decoration: none; color: white; background-color: #4CAF50; padding: 10px 20px; border-radius: 5px; margin: 5px; display: inline-block; }");
            pw.println("a:hover { background-color: #45a049; }");
            pw.println("</style>");
            pw.println("</head><body>");

            if (session != null) {
                // User is logged in
                pw.println("<div class='content'>");
                pw.println("<h2>Welcome to User Home</h2>");
                pw.println("<div><a href='Profile'>Profile</a></div>");
                pw.println("<div><a href='Contact'>Contact to Sell</a></div>");
                pw.println("<div><a href='show.jsp'>Show Items</a></div>");
                pw.println("<div><a href='img_upload.html'>Upload Your Image</a></div>");
                pw.println("<div><a href='sell_scrap_detail.jsp'>Check Estimated Price</a></div>");
                pw.println("<div><a href='Logout'>Logout</a></div>");
                pw.println("</div>");
            } else {
                // User is not logged in
                pw.println("<div class='content'>");
                pw.println("<h2>Please Login First</h2>");
                pw.println("<div><a href='index.html'>Login</a></div>");
                pw.println("</div>");
            }

            pw.println("</body></html>");
        } catch (Exception e) {
            pw.println("<h2>Error: " + e.getMessage() + "</h2>");
        } finally {
            pw.close();
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles user home page and login validation";
    }
}
