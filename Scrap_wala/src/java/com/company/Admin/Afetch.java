
import com.company.Connection.MyConnection;
import com.mysql.cj.protocol.Resultset;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.servlet.annotation.WebServlet;
@WebServlet("/Afetch")
public class Afetch extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html");

        try {
           Connection cn = MyConnection.getConnection();
            // Create prepared statement
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM items");
            ResultSet rs = ps.executeQuery();

            // Start HTML output
            pw.println("<html><head>");
            pw.println("<style>");
            pw.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }");
            pw.println("h2 { text-align: center; }");
            pw.println(".styled-table { width: 80%; margin: 20px auto; border-collapse: collapse; border-radius: 10px; overflow: hidden; box-shadow: 0 0 20px rgba(0, 0, 0, 0.15); }");
            pw.println(".styled-table thead tr { background-color: #009879; color: #ffffff; text-align: left; }");
            pw.println(".styled-table th, .styled-table td { padding: 12px 15px; }");
            pw.println(".styled-table tbody tr { border-bottom: 1px solid #dddddd; }");
            pw.println(".styled-table tbody tr:nth-of-type(even) { background-color: #f3f3f3; }");
            pw.println(".styled-table tbody tr:last-of-type { border-bottom: 2px solid #009879; }");
            pw.println(".styled-table a { text-decoration: none; color: #009879; }");
            pw.println(".styled-table a img { vertical-align: middle; }");
            pw.println("</style>");
            pw.println("<title>Item Details</title>");
            pw.println("</head><body>");
            pw.println("<h2>Items Details</h2>");
            pw.println("<table class='styled-table'>");
            pw.println("<thead><tr><th>Id</th><th>Name</th><th>Rate</th><th>Edit</th><th>Delete</th></tr></thead>");
            pw.println("<tbody>");

            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String rate = rs.getString(3);
                pw.println("<tr><td>" + id + "</td><td>" + name + "</td><td>" + rate +"/kg"+ "</td>");
                pw.println("<td><a href='Aedit?id=" + id + "'><img src='Images/edit (2).png' width='20px' height='20px'></a></td>");
                pw.println("<td><a href='Adelete?id=" + id + "'><img src='Images/delete (2).png' width='20px' height='20px'></a></td></tr>");
            }

            pw.println("</tbody></table>");
            pw.println("</body></html>");

            // Close the connection
            cn.close();
        } catch (Exception e) {
            pw.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
