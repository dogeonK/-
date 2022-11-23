package tukorea.web.club.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tukorea.web.club.domain.MovieVO;
import tukorea.web.club.domain.ReservationVO;
import tukorea.web.club.domain.UserVO;
import tukorea.web.club.service.MovieService;
import tukorea.web.club.service.ReservationService;
import tukorea.web.club.service.UserService;

/**
 * Servlet implementation class ReservationServlet
 */
@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReservationServlet() {
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

		String message;
		String cmdReq;
		cmdReq = request.getParameter("cmd");
		HttpSession session = request.getSession();

		if (cmdReq.equals("reservation")) {
			ReservationService r_service = new ReservationService();
			MovieService m_service = new MovieService();

			MovieVO movie = m_service.readMovie(request.getParameter("movieid"));

			request.setAttribute("movie", movie);
			RequestDispatcher view = request.getRequestDispatcher("reservation.jsp");
			view.forward(request, response);
		}
		else if (cmdReq.equals("list")) {
			ReservationService service = new ReservationService();
			
			ArrayList<ReservationVO> reservationList = service.getAllReserve();
			
			request.setAttribute("reservationList", reservationList);
			RequestDispatcher view = request.getRequestDispatcher("reservation_list.jsp");
			view.forward(request, response);
		}
		else if (cmdReq.equals("delete")) {
			ReservationService service = new ReservationService();
			int intId = Integer.parseInt(request.getParameter("reserveid"));
			service.deleteReserve(intId);

			ArrayList<ReservationVO> reservationList = service.getAllReserve();
			request.setAttribute("reservationList", reservationList);
			RequestDispatcher view = request.getRequestDispatcher("reservation_list.jsp");
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
		HttpSession session = request.getSession();

		if (cmdReq.equals("reservation")) {
			MovieService m_service = new MovieService();
			ReservationService r_service = new ReservationService();
			ReservationVO reservationVO = new ReservationVO();

			MovieVO movie = m_service.readMovie(request.getParameter("movieid"));
			
			reservationVO.setUserid(String.valueOf(session.getAttribute("loginId")));
			reservationVO.setMovieid(movie.getMovieid());
			reservationVO.setMoviename(movie.getMoviename());
			reservationVO.setSeatnumber(request.getParameter("seatnumber"));

			if (r_service.addReservation(reservationVO))
				message = "영화 예매에 성공하였습니다.";
			else
				message = "영화 예매에 실패하였습니다.";

			request.setAttribute("greetings", message);
			request.setAttribute("reservation", reservationVO);

			RequestDispatcher view = request.getRequestDispatcher("reservation_result.jsp");
			view.forward(request, response);
		}
	}

}
