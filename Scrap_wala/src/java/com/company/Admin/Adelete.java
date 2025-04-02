


import com.company.Connection.MyConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.annotation.WebServlet;




@WebServlet("/Adelete")
public class Adelete extends HttpServlet 
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter pw = resp.getWriter();
    try{
        String id = req.getParameter("id");
       Connection cn = MyConnection.getConnection();
       //create preparedstatemetn obj
       PreparedStatement ps = cn.prepareCall("delete from items where id = ?");
       ps.setString(1, id);
       boolean b = ps.execute();
       if(b==false)
       {
//   pw.println("data delete");
             resp.sendRedirect("Afetch");
       }
       //close the query
       cn.close();
    }
    catch(Exception e)
    {
        pw.println(e.getMessage());
    }

    }}