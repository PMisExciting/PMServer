package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import bean.Animal;
import bean.Comment;
import bean.Forum;
import bean.User;

public class Dao {
	private static String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://127.0.0.1:3306/pmdb?useUnicode=true&characterEncoding=UTF-8";

	// username and password
	String dbUsername = "root";
	String dbPassword = "123456789";

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
	
	public Animal[] getAdoptAnimalList(){
    	
    	ArrayList<Animal> adoptList=new ArrayList<Animal>();
    	Connection con = null;
		Statement sm = null;
		ResultSet results = null;
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery("select * from animal where animal.animal_type = 0");
			while (results.next()) {
            	//comment_time must be null, then occurs null pointer exception
            	//so make comment_time which will never used just be order_time
				int userID = results.getInt("user_id");
				String userName = getUserNameById(userID);
				adoptList.add(new Animal(results.getInt("animal_id"),results.getString("animal_name"),results.getString("animal_description"), results.getTimestamp("animal_time").toString(), results.getString("animal_picture"), userName));
            }
            System.out.println("New:"+adoptList.size());
            results.close();
            sm.close();
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
		
		Animal[] ret=new Animal[adoptList.size()];
        adoptList.toArray(ret);
        return ret;
	}
	
	public Animal[] getLostAnimalList(){
    	
    	ArrayList<Animal> lostList=new ArrayList<Animal>();
    	Connection con = null;
		Statement sm = null;
		ResultSet results = null;
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery("select * from animal where animal.animal_type = 1");
			while (results.next()) {
            	//comment_time must be null, then occurs null pointer exception
            	//so make comment_time which will never used just be order_time
				int userID = results.getInt("user_id");
				String userName = getUserNameById(userID);
				lostList.add(new Animal(results.getInt("animal_id"),results.getString("animal_name"),results.getString("animal_description"), results.getTimestamp("animal_time").toString(), results.getString("animal_picture"), userName));
            }
            System.out.println("New:"+lostList.size());
            results.close();
            sm.close();
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
		
		Animal[] ret=new Animal[lostList.size()];
        lostList.toArray(ret);
        return ret;
	}

	public String getUserNameById(int userID) {
		String userName = null;
        
		Connection con = null;
		Statement sm = null;
		ResultSet results = null;
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery("SELECT * " + "FROM user " + "WHERE user_id='" + userID + "'");
			if (results.next()) {
				userName = results.getString("user_name");
			} else {
				return null;
			}
            results.close();
            sm.close();
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
		
		return userName;

	}

	// 获取所有的文章列表
	public Forum[] getForums() {
		ArrayList<Forum> forumList = new ArrayList<Forum>();
		Connection con = null;
		Statement sm = null;
		ResultSet results = null;
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery("select * from forum order by forum_id DESC");
			while (results.next()) {
				int id = results.getInt("forum_id");
				forumList.add(getForumById(id));
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
		Forum[] ret = new Forum[forumList.size()];
		forumList.toArray(ret);
		return ret;
	}

	// 通过id获取单个文章的内容
	public Forum getForumById(int forumId) {
		Connection con = null;
		Statement sm = null;
		ResultSet results = null;

		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery("select * from forum where forum_id='" + forumId + "'");
			if (results.next()) {
				int commentNum = getCommentNum(forumId);
				String userName = "233"; // getUserNameById
				return new Forum(forumId, results.getString("forum_title"), results.getString("forum_content"),
						results.getTimestamp("forum_time").toString().substring(0,19), userName, commentNum);
			} else
				return null;

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

	// 获取热门文章
	public Forum[] getHotForums() {
		ArrayList<Forum> forumList = new ArrayList<Forum>();
		Connection con = null;
		Statement sm = null;
		ResultSet results = null;
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery(
					"select forum_id, count(*) as count from comment group by forum_id order by count DESC LIMIT 6");
			while (results.next()) {
				int id = results.getInt("forum_id");
				forumList.add(getForumById(id));
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
		Forum[] ret = new Forum[forumList.size()];
		forumList.toArray(ret);
		return ret;
	}
	
	//通过用户id获取和其相关的文章列表
	public Forum[] getForumsByUserId(int userId){
		ArrayList<Forum> forumList = new ArrayList<Forum>();
		Connection con = null;
		Statement sm = null;
		ResultSet results = null;
		
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery(
					"select * from forum where user_id='"+userId+"'");
			while (results.next()) {
				int id = results.getInt("forum_id");
				forumList.add(getForumById(id));
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
		Forum[] ret = new Forum[forumList.size()];
		forumList.toArray(ret);
		return ret;
		
	}

	// 通过forum_id获取该文章评论的总数
	public int getCommentNum(int forumId) {
		Connection con = null;
		Statement sm = null;
		ResultSet results = null;
		int result = 0;
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery("select count(*) from comment where forum_id='" + forumId + "'");
			if (results.next())
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

	// 通过forum_id获取该文章的所有评论内容
	public Comment[] getComments(int forumId) {
		ArrayList<Comment> commentList = new ArrayList<Comment>();
		Connection con = null;
		Statement sm = null;
		ResultSet results = null;
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery("select * from comment where forum_id='" + forumId + "'");
			while (results.next()) {
				String userName = "233"; // getUserNameById
				commentList.add(new Comment(results.getInt("comment_id"), results.getString("comment_content"),
						results.getTimestamp("comment_time").toString().substring(0,19), results.getInt("forum_id"), userName));
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

	public void addComment(int userId, String comment, int forumId){
		Connection con = null;
		Statement sm = null;
		
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			Timestamp time=new java.sql.Timestamp(new java.util.Date().getTime());
			sm.executeUpdate("insert into comment(comment_content, comment_time, forum_id, user_id) values ('" + comment + "', '"
						+ time + "', '" + forumId + "','" + userId + "')");
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addForum(int userId, String title, String content){
		Connection con = null;
		Statement sm = null;
		
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			Timestamp time=new java.sql.Timestamp(new java.util.Date().getTime());
			sm.executeUpdate("insert into forum(forum_title, forum_content, forum_time, user_id) values ('" + title + "', '"
						+ content + "', '" + time + "','" + userId + "')");
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteForum(int forumId){
		Connection con = null;
		Statement sm = null;
		
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			sm.executeUpdate("delete from forum where forum_id='"+forumId+"'");
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteComment(int commentId){
		Connection con = null;
		Statement sm = null;
		
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			sm.executeUpdate("delete from comment where comment_id = '"+commentId+"'");
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
