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
import tukorea.web.club.service.MovieService;


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
			MovieService service = new MovieService();
			ArrayList<MovieVO> movieList = service.getAllMovie();
			request.setAttribute("movieList", movieList);
			RequestDispatcher view = request.getRequestDispatcher("movie_list.jsp");
			view.forward(request, response);
		}

		else if (cmdReq.equals("delete")) {
			MovieService service = new MovieService();
			String strId = request.getParameter("movieid");
			service.deleteMovie(strId);

			ArrayList<MovieVO> movieList = service.getAllMovie();
			request.setAttribute("movieList", movieList);
			RequestDispatcher view = request.getRequestDispatcher("movie_list.jsp");
			view.forward(request, response);
		}
		
		else if (cmdReq.equals("update")) {
			MovieService service = new MovieService();
			MovieVO movie = service.readMovie(request.getParameter("movieid"));
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

			MovieService service = new MovieService();

			if (service.addMovie(movieVO))
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
			
			MovieService service = new MovieService();
			
			if (service.updateMovie(movieVO))
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