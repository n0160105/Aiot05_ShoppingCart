package shopping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RefreshServlet")
public class RefreshServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String HOME = System.getProperty("user.home");
	private static final String CODEPATH = HOME + "\\Desktop\\yolo-object-detection\\yolo.py";
	//private static final String PYTHON = "C:\\Python36\\python.exe ";
	private static final String PYTHON = HOME + "\\AppData\\Local\\Programs\\Python\\Python36-32\\python.exe ";
	
	private FruitsDAO fruitsDAO;
	
	public void init() {
		fruitsDAO = new FruitsDAO();
	}

    public RefreshServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter pw = response.getWriter();
		
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("name") != null) {
		
			String name = (String)session.getAttribute("name");
			
			StringBuilder sb = new StringBuilder(PYTHON);
	        sb.append(CODEPATH);
	        sb.append(" -u " + name);
	
	        try {
				Runtime.getRuntime().exec(sb.toString()).waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	
			List<FruitsModel> selectFruits = fruitsDAO.selectFruits(name);
			request.setAttribute("selectFruits", selectFruits);
			request.getRequestDispatcher("shopping.jsp").include(request, response);
			
		} else {
			
			pw.print("<html><body onload=\"alert('Please login first!')\"></body></html>");
			request.getRequestDispatcher("index.jsp").include(request, response);
			
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}