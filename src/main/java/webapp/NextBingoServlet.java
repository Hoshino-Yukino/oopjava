package webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NextBingoServlet
 */
@WebServlet("/nextBingo")
public class NextBingoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NextBingoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		var session = request.getSession(true);
		if(session.getAttribute("numbers")==null) {
			response.sendRedirect("startBingo");
		}else {
			int index = (int) session.getAttribute("index");
			int[] numbers = (int[]) session.getAttribute("numbers");
			if(index>=numbers.length) {
				request.setAttribute("message", "全ての乱数を出力しました");
			}else {
				request.setAttribute("message", numbers[index]);
				request.getSession().setAttribute("index", index+1);
			}
			request.getRequestDispatcher("/WEB-INF/nextBingo.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
