
import java.io.IOException;
import java.net.http.HttpClient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class getservlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int id=Integer.parseInt(req.getParameter("id"));
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/swiggy", "root", "root");
		String sql="select * from login where id=?";
		PreparedStatement pmst=conn.prepareStatement(sql);
		pmst.setInt(1, id);
		ResultSet rs=pmst.executeQuery();
		while(rs.next()) {
			System.out.println("name : "+rs.getString("name"));
			System.out.println("email : "+rs.getString("email"));
			System.out.println("password : "+rs.getString("password"));
		}
		conn.close();
		pmst.close();
	} 
	
	catch (Exception e) {
		e.printStackTrace();
	}
}
}
