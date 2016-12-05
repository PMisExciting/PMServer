package bean;

public class Forum {
	private int forumId;
	private String forumTitle; // 文章标题
	private String forumContent; // 文章内容
	private String forumTime; // 文章发布时间
	private String userName; // 发布文章的用户名
	private int commentNum; //评论数量

	public Forum(int forumId, String forumTitle, String forumContent, String forumTime, String userName, int commentNum) {
		this.forumId = forumId;
		this.forumTitle = forumTitle;
		this.forumContent = forumContent;
		this.forumTime = forumTime;
		this.userName = userName;
		this.commentNum = commentNum;
	}

	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public String getForumTitle() {
		return forumTitle;
	}

	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}

	public String getForumContent() {
		return forumContent;
	}

	public void setForumContent(String forumContent) {
		this.forumContent = forumContent;
	}

	public String getForumTime() {
		return forumTime;
	}

	public void setForumTime(String forumTime) {
		this.forumTime = forumTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
}
