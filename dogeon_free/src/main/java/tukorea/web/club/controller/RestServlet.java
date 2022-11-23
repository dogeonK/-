package tukorea.web.club.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tukorea.web.club.domain.UserVO;
import tukorea.web.club.persistence.UserDAO;

/**
 * Servlet implementation class RestServlet
 */
@WebServlet("/RestServlet")
public class RestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RestServlet() {
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
		//http://localhost:8080/dogeon_free/RestServlet?cmd=list
		//http://localhost:8080/dogeon_free/RestServlet?cmd=read&userid=1
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		PrintWriter out = response.getWriter();

		String cmdReq;
		cmdReq = request.getParameter("cmd");
		if (cmdReq == null)
			return;

		UserDAO userDAO = new UserDAO();
		JSONArray arrayJson = new JSONArray();

		if (cmdReq.equals("list")) {
			try {
				List<UserVO> userList = userDAO.getUserList();
				for (UserVO vo : userList) {
					JSONObject json = new JSONObject();
					json.put("userid", vo.getUserid());
					json.put("passwd", vo.getPasswd());
					json.put("username", vo.getUsername());
					json.put("snum", vo.getAnum());
					json.put("mobile", vo.getMobile());
					json.put("email", vo.getEmail());
					json.put("usertype", vo.getUsertype());
					arrayJson.put(json);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.print(arrayJson);
		}

		if (cmdReq.equals("read")) {
			try {
				String userid = request.getParameter("userid");
				if (userid == null) {
					out.print("계정을 확인하세요");
					return;
				}
				UserVO vo = userDAO.read(userid);
				JSONObject json = new JSONObject();

				json.put("userid", vo.getUserid());
				json.put("passwd", vo.getPasswd());
				json.put("username", vo.getUsername());
				json.put("snum", vo.getAnum());
				json.put("mobile", vo.getMobile());
				json.put("email", vo.getEmail());
				json.put("usertype", vo.getUsertype());
				
				out.print(json);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
