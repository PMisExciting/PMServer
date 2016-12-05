package bean;

public class Comment {
	private int commentId;
	private String commentContent; 
	private String commentTime; //评论时间
	private int forumId; //评论的文章id
	private String userName; //评论的用户名
	
	public Comment(int commenId, String commentContent, String commentTime, int formId, String userName){
		this.commentId = commenId;
		this.commentContent = commentContent;
		this.commentTime = commentTime;
		this.forumId = formId;
		this.userName = userName;
	}
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
