package tukorea.web.club.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tukorea.web.club.domain.MovieVO;
import tukorea.web.club.persistence.MovieDAO;


/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/MovieServlet")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MovieServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		String cmdReq;
		cmdReq = request.getParameter("cmd");

		if (cmdReq.equals("join")) {
			response.sendRedirect("movie_add.html");
		} else if (cmdReq.equals("list")) {
			MovieDAO dao = new MovieDAO();
			ArrayList<MovieVO> movieList = dao.getMovieList();
			request.setAttribute("movieList", movieList);
			RequestDispatcher view = request.getRequestDispatcher("movie_list.jsp");
			view.forward(request, response);
		}

		else if (cmdReq.equals("delete")) {
			MovieDAO dao = new MovieDAO();
			String strId = request.getParameter("movieid");
			dao.delete(strId);

			ArrayList<MovieVO> movieList = dao.getMovieList();
			request.setAttribute("movieList", movieList);
			RequestDispatcher view = request.getRequestDispatcher("movie_list.jsp");
			view.forward(request, response);
		}
		
		else if (cmdReq.equals("update")) {
			MovieDAO dao = new MovieDAO();
			MovieVO movie = dao.read(request.getParameter("movieid"));
			request.setAttribute("movie", movie);

			RequestDispatcher view = request.getRequestDispatcher("movie_update.jsp");
			view.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		String message;
		String cmdReq;
		cmdReq = request.getParameter("cmd");

		if (cmdReq.equals("join")) {
			MovieVO movieVO = new MovieVO();

			movieVO.setMovieid(request.getParameter("movieid"));
			movieVO.setMoviename(request.getParameter("moviename"));
			movieVO.setMoviegenre(request.getParameter("moviegenre"));
			movieVO.setRoom(request.getParameter("room"));
			movieVO.setMovietime(request.getParameter("movietime"));

			MovieDAO movieDAO = new MovieDAO();

			if (movieDAO.add(movieVO))
				message = "영화 등록에 성공하였습니다.";
			else
				message = "영화 등록에 실패하였습니다.";

			request.setAttribute("greetings", message);
			request.setAttribute("movie", movieVO);

			RequestDispatcher view = request.getRequestDispatcher("movie_result.jsp");
			view.forward(request, response);
		}
		else if (cmdReq.equals("update")) {
			MovieVO movieVO = new MovieVO();

			movieVO.setMovieid(request.getParameter("movieid"));
			movieVO.setMoviename(request.getParameter("moviename"));
			movieVO.setMoviegenre(request.getParameter("moviegenre"));
			movieVO.setRoom(request.getParameter("room"));
			movieVO.setMovietime(request.getParameter("movietime"));
			
			MovieDAO dao = new MovieDAO();
			
			if (dao.update(movieVO))
				message = "영화 수정이 완료되었습니다.";
			else
				message = "영화 수정이 실패되었습니다.";

			request.setAttribute("greetings", message);
			request.setAttribute("movie", movieVO);

			RequestDispatcher view = request.getRequestDispatcher("movie_result.jsp");
			view.forward(request, response);
		}
	}
}