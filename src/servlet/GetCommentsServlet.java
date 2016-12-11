package servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.Comment;
import dao.Dao;

/**
 * Servlet implementation class GetCommentsServlet
 */
@WebServlet("/GetComments")
public class GetCommentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCommentsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		int forumId = Integer.parseInt(request.getParameter("forumId"));
		
		Dao dao = Dao.getInstance();
		Comment[] commentList = dao.getComments(forumId);
		
		OutputStream out = response.getOutputStream();
		JSONArray array = new JSONArray();
		
		for(Comment c: commentList){
			JSONObject item = new JSONObject();
			item.put("commentId", c.getCommentId());
			item.put("commentContent", c.getCommentContent());
			item.put("commentTime", c.getCommentTime());
			item.put("userName", c.getUserName());
			
			array.put(item);
		}
		
		out.write(array.toString().getBytes("UTF-8"));
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
