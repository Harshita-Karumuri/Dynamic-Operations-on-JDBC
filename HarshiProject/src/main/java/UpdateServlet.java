
import java.io.IOException;
import java.net.http.HttpClient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String id=req.getParameter("id");
	String name=req.getParameter("name");
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/swiggy", "root", "root");
		String sql="update login set name=? where id=?";
		PreparedStatement pmst=conn.prepareStatement(sql);
		pmst.setString(1, name);
		pmst.setString(2, id);
		int i=pmst.executeUpdate();
		if(i>0) {
			resp.sendRedirect("del.jsp");
		}
		else {
			resp.sendRedirect("update.jsp");
		}
		conn.close();
		pmst.close();
	} 
	
	catch (Exception e) {
		e.printStackTrace();
	}
}
}
