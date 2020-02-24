package shopping;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

@WebServlet("/QRLoginServlet")
public class QRLoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String HOME = System.getProperty("user.home");
	private final String QRPATH = HOME + "\\Desktop\\yolo-object-detection\\images\\QR.jpg";
	private final String CODEPATH = HOME + "\\Desktop\\yolo-object-detection\\capture.py";
	//private static final String PYTHON = "C:\\Python36\\python.exe ";
	private static final String PYTHON = HOME + "\\AppData\\Local\\Programs\\Python\\Python36-32\\python.exe ";

    public QRLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter pw = response.getWriter();
		
		UserDAO userdao = new UserDAO();

		try {
			
			StringBuilder sb = new StringBuilder(PYTHON);
	        sb.append(CODEPATH);
	        
	        Runtime.getRuntime().exec(sb.toString()).waitFor();
			Thread.sleep(3000);
	        
			File file = new File(QRPATH);
			String decodedText = decodeQRCode(file);
			
			UserModel model = userdao.selectUserbyToken(decodedText);
			String name = model.getUser();
			
			if (decodedText != null && name != null) {
				
				request.getSession().setAttribute("name", name);
				userdao.deleteToken(name);
				pw.print("<body onload=\"alert('Login successfully!')\"></body>");
				request.getRequestDispatcher("shopping.jsp").include(request, response);
				
			} else {
			    
				pw.print("<body onload=\"alert('No QR Code found in the image! Please try again!')\"></body>");
				request.getRequestDispatcher("index.jsp").include(request, response);
				
			}
            
        } catch (IOException e) {
        	
        	e.printStackTrace();
        	pw.print("<body onload=\"alert('Time-out occurs! Please try again!')\"></body>");
			request.getRequestDispatcher("index.jsp").include(request, response);
        	
        } catch (InterruptedException e) {
        	e.printStackTrace();
		}
		
		pw.close();
		
	}

    private String decodeQRCode(File qrCodeimage) throws IOException {
    	
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            return null;
        }
        
    }
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}