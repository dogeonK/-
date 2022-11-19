package tukorea.web.club.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tukorea.web.club.domain.UserVO;
import tukorea.web.club.persistence.UserDAO;


/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
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
			response.sendRedirect("register.html");
		} else if (cmdReq.equals("list")) {
			UserDAO dao = new UserDAO();
			ArrayList<UserVO> userList = dao.getUserList();
			request.setAttribute("userList", userList);
			RequestDispatcher view = request.getRequestDispatcher("user_list.jsp");
			view.forward(request, response);
		}

		else if (cmdReq.equals("delete")) {
			UserDAO dao = new UserDAO();
			String strId = request.getParameter("userid");
			dao.delete(strId);

			ArrayList<UserVO> userList = dao.getUserList();
			request.setAttribute("userList", userList);
			RequestDispatcher view = request.getRequestDispatcher("user_list.jsp");
			view.forward(request, response);
		} else if (cmdReq.equals("update")) {
			UserDAO dao = new UserDAO();
			UserVO user = dao.read(request.getParameter("userid"));
			request.setAttribute("user", user);

			RequestDispatcher view = request.getRequestDispatcher("update.jsp");
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
			UserVO userVO = new UserVO();

			userVO.setUserid(request.getParameter("userid"));
			userVO.setPasswd(request.getParameter("passwd"));
			userVO.setUsername(request.getParameter("username"));
			userVO.setAnum(request.getParameter("anum"));
			userVO.setMobile(request.getParameter("mobile"));
			userVO.setEmail(request.getParameter("email"));
			userVO.setUsertype(Integer.parseInt(request.getParameter("usertype")));
			UserDAO userDao = new UserDAO();

			if (userDao.add(userVO))
				message = "가입 축하합니다";
			else
				message = "가입 실패입니다";

			request.setAttribute("greetings", message);
			request.setAttribute("user", userVO);

			RequestDispatcher view = request.getRequestDispatcher("result.jsp");
			view.forward(request, response);
		}
		else if (cmdReq.equals("update")) {
			UserVO userVO = new UserVO();

			userVO.setUserid(request.getParameter("userid"));
			userVO.setPasswd(request.getParameter("passwd"));
			userVO.setUsername(request.getParameter("username"));
			userVO.setAnum(request.getParameter("anum"));
			userVO.setMobile(request.getParameter("mobile"));
			userVO.setEmail(request.getParameter("email"));
			userVO.setUsertype(Integer.parseInt(request.getParameter("usertype")));
			
			UserDAO dao = new UserDAO();
			
			if (dao.update(userVO))
				message = "수정이 완료되었습니다.";
			else
				message = "수정 실패입니다.";

			request.setAttribute("greetings", message);
			request.setAttribute("user", userVO);

			RequestDispatcher view = request.getRequestDispatcher("result.jsp");
			view.forward(request, response);
		}
	}
}