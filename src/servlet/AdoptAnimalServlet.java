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

import bean.Animal;
import bean.User;
import dao.Dao;

/**
 * Servlet implementation class AdoptAnimalServlet
 */
@WebServlet("/GetAdoptAnimal")
public class AdoptAnimalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdoptAnimalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");

		Dao dao = Dao.getInstance();
		Animal[] adoptList = dao.getAdoptAnimalList();
		OutputStream out = response.getOutputStream();
		JSONArray array = new JSONArray();
		

		if (adoptList != null) {
			for (Animal a: adoptList) {
				JSONObject item = new JSONObject();
				item.put("animalId", a.getAnimalId());
				item.put("animalName", a.getAnimalName());
				item.put("animalDescription", a.getAnimalDescription());
				item.put("animalPicture", a.getAnimalPicture());
				item.put("userName", a.getUserName());
				item.put("time", a.getTime());
				array.put(item);
			}
		}else {
			JSONObject item = null;
			array.put(item);
		}
		
		out.write(array.toString().getBytes("UTF-8"));
		//System.out.println(item);
		out.flush();
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
