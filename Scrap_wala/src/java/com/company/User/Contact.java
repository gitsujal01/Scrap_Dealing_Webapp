/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.company.Connection.MyConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(urlPatterns = {"/Contact"})
public class Contact extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter pw = response.getWriter();
    try {
        HttpSession hs = request.getSession(false);
        if (hs != null) {
            String id = (String) hs.getAttribute("id");

            Connection cn = MyConnection.getConnection();
            PreparedStatement ps = cn.prepareCall("update user set Status = ? where user_id = ?");
            ps.setBoolean(1, true);
            ps.setString(2, id);
            boolean b = ps.execute();

            if (!b) {
                // Start HTML output
                pw.println("<html>");
                pw.println("<head>");
                pw.println("<title>Request Sent</title>");
                pw.println("<style>");
                pw.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }");
                pw.println(".container { max-width: 600px; margin: auto; background: white; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
                pw.println("h1 { color: #333; }");
                pw.println("p { font-size: 16px; }");
                pw.println("a { text-decoration: none; color: #007bff; }");
                pw.println("</style>");
                pw.println("</head>");
                pw.println("<body>");
                pw.println("<div class='container'>");
                pw.println("<h1>Your Request Has Been Sent!</h1>");
                pw.println("<p>Please wait for the dealer's reply  😊😊</p>");
                pw.println("<a href='Home'>Go back to Home</a>");
                pw.println("<br>");
                pw.println("<a href='sell_item.html'>add items to sell</a>");
                pw.println("</div>");
                pw.println("</body>");
                pw.println("</html>");
            }
        }
    } catch (Exception e) {
        pw.print(e.getMessage());
    } finally {
        pw.close();
    }
}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
