package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.rmi.RemoteException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;


/**
 * Servlet implementation class ModifyAnimalTxtServlet
 */
@WebServlet("/ModifyAnimalTxt")
public class ModifyAnimalTxtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyAnimalTxtServlet() {
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
		
		String animalId = request.getParameter("animalId");
		String animalName = request.getParameter("animalName");
		String animalDescription = request.getParameter("animalDescription");
		String animalTime = request.getParameter("animalTime");
		
		System.out.println(animalTime+" "+ animalId+ " "+ animalName);
		
		try {
			Dao dao = Dao.getInstance();
			int ret = dao.modifyAnimalTxt(animalId, animalName, animalDescription, animalTime);

			if(ret == 1){
				OutputStream out = response.getOutputStream();
				out.write(ret);
			    System.out.println(ret);
			    out.flush();
				out.close();
			}
			//String jsonReturn=callback+"({\"flag\":\""+retStr+"\"})";
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
