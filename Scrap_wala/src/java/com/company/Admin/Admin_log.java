
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/Admin_log"})
public class Admin_log extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter pw = response.getWriter()) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if ("lokhandesujal890@gmail.com".equalsIgnoreCase(email) && "123".equals(password)) {
//                RequestDispatcher rd = request.getRequestDispatcher("Home.html");
//                rd.forward(request, response);
                    HttpSession hs=request.getSession(true);  
                     hs.setAttribute("email",email);
                     hs.setAttribute("password", password);
                     response.sendRedirect("Admin_home");
    
            } else {
                pw.print("Invalid password");
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.include(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Logging the exception for debugging
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
        return "Admin login servlet";
    }
}
