/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.company.Connection.MyConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
       try{
           String email = request.getParameter("email");
           String password = request.getParameter("password");
          Connection cn = MyConnection.getConnection();
             PreparedStatement ps = cn.prepareCall("select * from user where Email_id = ? and binary password = ?");
            ps.setString(1, email);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                String id = rs.getString("user_id");
                String name = rs.getString("username");
                String mobile = rs.getString("mobile_no");
                String city = rs.getString("city");
                String address = rs.getString("address");
                
               HttpSession hs=request.getSession(true);
               hs.setAttribute("id", id);
               hs.setAttribute("name", name);
               hs.setAttribute("mobile", mobile);
               hs.setAttribute("email", email);
               hs.setAttribute("city", city);
               hs.setAttribute("address", address);
        
               response.sendRedirect("Home");

            }
            else
            {
                pw.println("<span style='color:red'><h2>Invalid Email or Password</h2></span>");
               RequestDispatcher rd=request.getRequestDispatcher("index.html");
               rd.include(request, response);
            }
        }
        catch(Exception e)
        {
            pw.println(e.getMessage());
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
