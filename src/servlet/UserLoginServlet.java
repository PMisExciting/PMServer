package servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bean.User;
import dao.Dao;
import org.json.JSONArray;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLogin")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Dao dao = Dao.getInstance();
		User user = dao.login(username, password);
		OutputStream out = response.getOutputStream();
		JSONArray array = new JSONArray();
		JSONObject item = new JSONObject();

		if (user != null) {
			item.put("userId", user.getUserId());
			item.put("userName", user.getUserName());
			item.put("userRole", user.getUserRole());
		}else
			item = null;
		
		array.put(item);

		out.write(array.toString().getBytes("UTF-8"));
		System.out.println(item);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
