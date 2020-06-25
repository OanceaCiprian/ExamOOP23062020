package sports;

import java.util.Arrays;

public class Category {

	private String name;
	private String[] activities;
	
	public Category(String name, String... activities) {
		this.name = name;
		this.activities = activities;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActivities() {
		return Arrays.toString(activities);
	}

	public void setActivities(String[] activities) {
		this.activities = activities;
	}

	@Override
	public String toString() {
		return Arrays.toString(activities);
	}
}
