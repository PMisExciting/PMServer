package servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import dao.Dao;

/**
 * Servlet implementation class AnimalImgUploadServlet
 */
@WebServlet("/AnimalImgUpload")
public class AnimalImgUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnimalImgUploadServlet() {
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
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		String animalId = null;

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		String icon = "a1.png";// 默认图片

		try {

			sfu.setFileSizeMax(1024 * 50 * 1024);
			List<FileItem> fileItems = sfu.parseRequest(new ServletRequestContext(request));
			FileItem i = fileItems.get(0);
			String t = i.getFieldName();
			System.out.println("控件名+++++++++" + t);
			if (t != null && i.isFormField() && t.equals("animalId")) {
				animalId = i.getString();
			}
			System.out.println("animalId+++++++++" + animalId);

			FileItem item = fileItems.get(1);
			String name = item.getFieldName();
			System.out.println("控件名+++++++++" + name);
			if (name != null && animalId != null && name.equals("picture")) {
				String fileName = item.getName();
				System.out.println("图片名+++++++++" + fileName);
				fileName = fileName.substring(fileName.lastIndexOf("."));
				if (null != fileName && !("".equals(fileName))) {
					ServletContext sctx = getServletContext();
					String path = sctx.getRealPath("img/");// 头像目录
					Date date = new Date();
					Long d = date.getTime();
					File file = new File(path + "/" + animalId + d + fileName);
					System.out.println(file.getAbsolutePath());
					item.write(file);
					icon = file.getName();
				}
			}
			Dao dao = Dao.getInstance();
			int ret = dao.modifyAnimalImg(animalId, icon);
			System.out.println(animalId + " kongge   "+ ret);
			OutputStream out = response.getOutputStream();
			if (ret == 1) {
				out.write("true".getBytes("UTF-8"));
				//System.out.println(item);
				out.flush();
				out.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
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
