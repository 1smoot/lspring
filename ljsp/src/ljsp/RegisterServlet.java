package ljsp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setId(request.getParameter("id"));
		user.setPassword(request.getParameter("password"));
		user.setConfirmPassword(request.getParameter("confirmPassword"));
		// check passwd and confirmPassword
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			request.setAttribute("msg", "password not matched!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		// TODO check id duplication
		// TODO save()
		UserDAO.save(user);
		// TODO forward finish page
		response.sendRedirect("registerFinished.jsp");
//		request.getRequestDispatcher("register.jsp").forward(request, response);
	}
}
