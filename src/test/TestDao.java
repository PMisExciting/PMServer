package test;

import bean.Comment;
import bean.Forum;
import dao.Dao;

public class TestDao {
	public static void main(String [] args){
		Dao dao = Dao.getInstance();
//		Forum[] forums = dao.getHotForums();
//		for(Forum f:forums)
//			System.out.println(f.getForumTitle() );
		
//		int commentNum = dao.getCommentNum(2);
//		System.out.println(commentNum);
		
		Comment[] comments = dao.getComments(1);
		for(Comment c:comments)
			System.out.println(c.getCommentTime());
	}
}
