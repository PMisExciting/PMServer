package bean;

public class Animal {
	private int animalId;
	private String animalName; // 宠物名字
	private String animalDescription; // 宠物的相关描述
	private String animalPicture; // 宠物的照片
	private String userName; // 发布该宠物信息的用户名
	private String time; // 发布该宠物信息的时间

	public Animal(int animalId, String animalName, String animalDescription, String animalPicture, String userName,
			String time) {
		this.animalId = animalId;
		this.animalName = animalName;
		this.animalDescription = animalDescription;
		this.animalPicture = animalPicture;
		this.userName = userName;
		this.time = time;
	}

	public int getAnimalId() {
		return animalId;
	}

	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}

	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public String getAnimalDescription() {
		return animalDescription;
	}

	public void setAnimalDescription(String animalDescription) {
		this.animalDescription = animalDescription;
	}

	public String getAnimalPicture() {
		return animalPicture;
	}

	public void setAnimalPicture(String animalPicture) {
		this.animalPicture = animalPicture;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
