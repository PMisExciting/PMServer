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

import bean.Forum;
import dao.Dao;

/**
 * Servlet implementation class GetHotForumsServlet
 */
@WebServlet("/GetHotForums")
public class GetHotForumsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetHotForumsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		Dao dao = Dao.getInstance();
		Forum[] forums = dao.getHotForums();
		OutputStream out = response.getOutputStream();
		JSONArray array = new JSONArray();
		
		for(Forum f:forums){
			JSONObject item = new JSONObject();
			item.put("forumId", f.getForumId());
			item.put("forumTitle", f.getForumTitle());
			item.put("forumContent", f.getForumContent());
			item.put("forumTime", f.getForumTime());
			item.put("userName", f.getUserName());
			item.put("commentNum", f.getCommentNum());
			
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
