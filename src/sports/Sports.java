package sports;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Facade class for the research evaluation system
 *
 */
public class Sports {

	
	private Set<String> activitiesSet = new HashSet<>();
	private Collection<Category> categories = new ArrayList<>();
	private Map<String, Product> products = new HashMap<>();
	private Collection<Rating> ratings = new ArrayList<>();
	 
    //R1
    /**
     * Define the activities types treated in the portal.
     * The method can be invoked multiple times to add different activities.
     * 
     * @param actvities names of the activities
     * @throws SportsException thrown if no activity is provided
     */
    public void defineActivities (String... activities) throws SportsException {
    	if(activities.length == 0) {
    		throw new SportsException("No activity provided");
    	}
    	for(String a : activities) {
    		activitiesSet.add(a);
    	}
    }

    /**
     * Retrieves the names of the defined activities.
     * 
     * @return activities names sorted alphabetically
     */
    public List<String> getActivities() {
        return activitiesSet.stream()
        		.sorted()
        		.collect(Collectors.toList());
    }


    /**
     * Add a new category of sport products and the linked activities
     * 
     * @param name name of the new category
     * @param activities reference activities for the category
     * @throws SportsException thrown if any of the specified activity does not exist
     */
    public void addCategory(String name, String... linkedActivities) throws SportsException {
    	for(String la : linkedActivities) {
    		if(!activitiesSet.contains(la)) {
    			throw new SportsException("Linked activity not added before");
    		}
    	}
    	Category category = new Category(name, linkedActivities);
    	System.out.println(category.toString());
    	categories.add(category);
    }

    /**
     * Retrieves number of categories.
     * 
     * @return categories count
     */
    public int countCategories() {
        return (int) categories.stream().count();
    }

    /**
     * Retrieves all the categories linked to a given activity.
     * 
     * @param activity the activity of interest
     * @return list of categories (sorted alphabetically)
     */
    public List<String> getCategoriesForActivity(String activity) {
    	List<String> categoriesForActivity = new ArrayList<>();
    	
    	for(String a : activitiesSet) {
    		if(a.contains(activity)) {
    			categoriesForActivity.add(activity);
    		}
    	}

        return categoriesForActivity.stream()
        		.sorted()
        		.collect(Collectors.toList());
    }

    //R2
    /**
     * Add a research group and the relative disciplines.
     * 
     * @param name name of the research group
     * @param disciplines list of disciplines
     * @throws SportsException thrown in case of duplicate name
     */
    public void addProduct(String name, String activityName, String categoryName) throws SportsException {
    	if(products.containsKey(name)) {
    		throw new SportsException("A product with this name already exists");
    	}
    	Product product = new Product(name, activityName, categoryName);
    	products.put(name, product);
    }

    /**
     * Retrieves the list of products for a given category.
     * The list is sorted alphabetically.
     * 
     * @param categoryName name of the category
     * @return list of products
     */
    public List<String> getProductsForCategory(String categoryName){
    	List<String> productsForCategory = new ArrayList<>();
    	for(Product p : products.values()) {
			if(p.getCategoryName() == categoryName) {
				productsForCategory.add(p.getName());
			}
    	}
        return productsForCategory.stream()
        		.sorted()
        		.collect(Collectors.toList());
    }

    /**
     * Retrieves the list of products for a given activity.
     * The list is sorted alphabetically.
     * 
     * @param activityName name of the activity
     * @return list of products
     */
    public List<String> getProductsForActivity(String activityName){
    	List<String> productsForActivity = new ArrayList<>();
    	for(Product p : products.values()) {
    		if(p.getActivityName() == activityName) {
    			productsForActivity.add(p.getActivityName());
    		}
    	}
        return productsForActivity;
    }

    /**
     * Retrieves the list of products for a given activity and a set of categories
     * The list is sorted alphabetically.
     * 
     * @param activityName name of the activity
     * @param categoryNames names of the categories
     * @return list of products
     */
    public List<String> getProducts(String activityName, String... categoryNames){
    	List<String> ps = new ArrayList<>();
    	for(Product p : products.values()) {
    		for(String c : categoryNames) {
        		if(p.getActivityName() == activityName && p.getCategoryName() == c) {
        			ps.add(p.getName());
        		}
    		}
    	}
        return ps;
    }

    //    //R3
    /**
     * Add a new product rating
     * 
     * @param productName name of the product
     * @param userName name of the user submitting the rating
     * @param numStars score of the rating in stars
     * @param comment comment for the rating
     * @throws SportsException thrown numStars is not correct
     */
    public void addRating(String productName, String userName, int numStars, String comment) throws SportsException {
    	if(numStars < 0 || numStars > 5) {
    		throw new SportsException("Rating must be between 0 and 5");
    	}
    	Rating rating = new Rating(productName, userName, numStars, comment);
    	
    	ratings.add(rating);
    }



    /**
     * Retrieves the ratings for the given product.
     * The ratings are sorted by descending number of stars.
     * 
     * @param productName name of the product
     * @return list of ratings sorted by stars
     */
    public List<String> getRatingsForProduct(String productName) {
    	List<String> ratingsForProduct = new ArrayList<>();
    	for(Rating r : ratings) {
    		if(r.getProductName() == productName) {
        		ratingsForProduct.add(r.toString());
    		}
    	}
    	System.out.println(ratingsForProduct);
        return ratingsForProduct
        		.stream()
        		.sorted()
        		.collect(Collectors.toList());
    }


    //R4
    /**
     * Returns the average number of stars of the rating for the given product.
     * 
     * 
     * @param productName name of the product
     * @return average rating
     */
    public double getStarsOfProduct (String productName) {
    	double result = 0;
    	int counter = 0;
    	for(Rating r : ratings) {
    		if(r.getProductName() == productName) {
    			result += r.getNumStars();
    			counter++;
    		}
    	}
    	if(counter > 0) {
        	return result/counter;
    	} else {
    		return 0;
    	}
    }

    /**
     * Computes the overall average stars of all ratings
     *  
     * @return average stars
     */
    public double averageStars() {
    	double result = 0;
    	int counter = 0;
    	for(Rating r : ratings) {
    		result += r.getNumStars();
    		counter++;
    	}

    	if(counter > 0) {
        	return result/counter;
    	} else {
    		return 0;
    	}
    }

    //R5 Statistiche
    /**
     * For each activity return the average stars of the entered ratings.
     * 
     * Activity names are sorted alphabetically.
     * 
     * @return the map associating activity name to average stars
     */
    public SortedMap<String, Double> starsPerActivity() {
    	Map<String, Double> startsPerActivity = new HashMap<>();
        return null;
    }

    /**
     * For each average star rating returns a list of
     * the products that have such score.
     * 
     * Ratings are sorted in descending order.
     * 
     * @return the map linking the average stars to the list of products
     */
    public SortedMap<Double, List<String>> getProductsPerStars () {
    	Map<Double, List<String>> productsPerStars = new HashMap<>();
        return null;
    }

}