package sports;

public class Product {
	
	private String name;
	private String activityName;
	private String categoryName;
	
	public Product(String name, String activityName, String categoryName) {
		this.name = name;
		this.activityName = activityName;
		this.categoryName = categoryName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
