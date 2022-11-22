package tukorea.web.club.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tukorea.web.club.domain.UserVO;
import tukorea.web.club.service.UserService;


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
		HttpSession session = request.getSession();

		if (cmdReq.equals("join")) {
			response.sendRedirect("register.html");
		} else if (cmdReq.equals("list")) {
			UserService service = new UserService();
			ArrayList<UserVO> userList = service.getAllUser();
			
			request.setAttribute("userList", userList);
			RequestDispatcher view = request.getRequestDispatcher("user_list.jsp");
			view.forward(request, response);
		}

		else if (cmdReq.equals("delete")) {
			UserService service = new UserService();
			String strId = request.getParameter("userid");
			service.deleteUser(strId);

			ArrayList<UserVO> userList = service.getAllUser();
			
			request.setAttribute("userList", userList);
			RequestDispatcher view = request.getRequestDispatcher("user_list.jsp");
			view.forward(request, response);
		}
		
		else if (cmdReq.equals("update")) {
			UserService service = new UserService();
			UserVO user = service.readUser(request.getParameter("userid"));
			request.setAttribute("user", user);

			RequestDispatcher view = request.getRequestDispatcher("update.jsp");
			view.forward(request, response);
		}
		else if (cmdReq.equals("login")) {
			response.sendRedirect("login.html");
		}
		else if (cmdReq.equals("logout")) {
			session.invalidate();
			RequestDispatcher view = request.getRequestDispatcher("welcome.jsp");
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
		
		
		if (cmdReq.equals("join")) {
			UserVO userVO = new UserVO();

			userVO.setUserid(request.getParameter("userid"));
			userVO.setPasswd(request.getParameter("passwd"));
			userVO.setUsername(request.getParameter("username"));
			userVO.setAnum(request.getParameter("anum"));
			userVO.setMobile(request.getParameter("mobile"));
			userVO.setEmail(request.getParameter("email"));
			userVO.setUsertype(1);

			UserService service = new UserService();

			if (service.addUser(userVO))
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
			UserService service = new UserService();
			
			userVO.setUserid(request.getParameter("userid"));
			userVO.setPasswd(request.getParameter("passwd"));
			userVO.setUsername(request.getParameter("username"));
			userVO.setAnum(request.getParameter("anum"));
			userVO.setMobile(request.getParameter("mobile"));
			userVO.setEmail(request.getParameter("email"));
			
			if(service.isAdmin(request.getParameter("userid"))) {
				userVO.setUsertype(Integer.parseInt(request.getParameter("usertype")));
			}
			
			
			
			if (service.updateUser(userVO))
				message = "수정이 완료되었습니다.";
			else
				message = "수정 실패입니다.";

			request.setAttribute("greetings", message);
			request.setAttribute("user", userVO);

			RequestDispatcher view = request.getRequestDispatcher("result.jsp");
			view.forward(request, response);
		}
		else if (cmdReq.equals("login")) {
			
			UserService service = new UserService();
			
			if(service.loginUser(request.getParameter("userid"), request.getParameter("passwd"))) {
				session.setAttribute("loginId", request.getParameter("userid"));
				if(service.isAdmin(request.getParameter("userid"))) {
					session.setAttribute("admin", true);
				}
				RequestDispatcher view = request.getRequestDispatcher("welcome.jsp");
				view.forward(request, response);
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				 out.println("<script>alert('아이디 또는 비밀번호가 틀렸습니다.'); location.href='login.html';</script>");
			}
			
		}
	}
}