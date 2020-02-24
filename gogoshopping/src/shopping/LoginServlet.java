package shopping;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter pw = response.getWriter();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if (isLogin(email, password)) {
			
			request.getSession().setAttribute("name", getUser(email));
			pw.print("<body onload=\"alert('Login successfully!')\"></body>");
			request.getRequestDispatcher("shopping.jsp").include(request, response);
			
		} else {
			
			pw.print("<body onload=\"alert('Username or password incorrect!')\"></body>");
			request.getRequestDispatcher("index.jsp").include(request, response);	
			
		}
		
		pw.close();
		
	}
	
	private boolean isLogin(String email, String password) throws IOException {
		
		UserDAO userdao = new UserDAO();
		UserModel usermodel = userdao.selectUserbyEmail(email);
		
		if (usermodel != null) {
			 String mypassword = usermodel.getPassword();
			 return password.equals(mypassword);
		}
		
		return false;
		
	}
	
	private String getUser(String email) throws IOException {
		return (new UserDAO()).selectUserbyEmail(email).getUser();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}