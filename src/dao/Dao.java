package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Comment;
import bean.Forum;
import bean.User;

public class Dao {
	private static String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://127.0.0.1:3306/pmdb?useUnicode=true&characterEncoding=UTF-8";

	// username and password
	String dbUsername = "root";
	String dbPassword = "";

	private static Dao dao;

	private Dao() {
	};

	public static Dao getInstance() {
		if (dao == null) {
			dao = new Dao();
		}
		return dao;
	}

	static {
		try {
			Class.forName(driver).newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public User login(String username, String password) {
		Connection con = null;
		Statement sm = null;
		ResultSet results = null;
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery("select * from user where user.user_name='" + username + "'");
			if (results.next()) {
				// Check password
				String oldPassword = results.getString("user_password");
				if (oldPassword.equals(password)) {
					User user = new User(results.getInt("user_id"), results.getString("user_name"),
							results.getInt("role"));
					return user;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (sm != null) {
					sm.close();
				}
				if (con != null) {
					con.close();
				}
				if (results != null) {
					results.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//获取所有的文章列表
	public Forum[] getForums(){
		ArrayList<Forum> forumList = new ArrayList<Forum>();
		Connection con = null;
		Statement sm = null;
		ResultSet results = null;
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery("select * from forum order by forum_id DESC");
			while(results.next()){
				int id = results.getInt("forum_id");
				int commentNum = getCommentNum(id);
				String userName = "233"; //getUserNameById
				forumList.add(new Forum(id, results.getString("forum_title"), results.getString("forum_content"), results.getTimestamp("forum_time").toString(), userName, commentNum));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (sm != null) {
					sm.close();
				}
				if (con != null) {
					con.close();
				}
				if (results != null) {
					results.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//通过forum_id获取该文章评论的总数
	public int getCommentNum(int forumId){
		Connection con = null;
		Statement sm = null;
		ResultSet results = null;
		int result = 0;
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery("select count(*) from comment where forum_id='"+forumId+"'");
			if(results.next())
				result = results.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (sm != null) {
					sm.close();
				}
				if (con != null) {
					con.close();
				}
				if (results != null) {
					results.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//通过forum_id获取该文章的所有评论内容
	public Comment[] getComments(int forumId){
		ArrayList<Comment> commentList= new ArrayList<Comment>();
		Connection con = null;
		Statement sm = null;
		ResultSet results = null;
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery("select * from comment where forum_id='"+forumId+"'");
			while(results.next()){
				String userName = "233"; //getUserNameById
				commentList.add(new Comment(results.getInt("comment_id"), results.getString("comment_content"), results.getTimestamp("comment_time").toString(), results.getInt("forum_id"), userName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (sm != null) {
					sm.close();
				}
				if (con != null) {
					con.close();
				}
				if (results != null) {
					results.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		Comment[] ret = new Comment[commentList.size()];
		commentList.toArray(ret);
		return ret;
	}

}
