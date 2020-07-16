import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator; 
import java.util.Map; 
  
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 

public class RecipeReturner {
	
	 @SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception, FileNotFoundException, ParseException, IOException {
		 
		 
		
		Scanner in = new Scanner(System.in);
		System.out.println("How many ingredients do you want to search for: ");
		int ingredNum = in.nextInt();
		
		
		
		String[] ingredients = new String[ingredNum]; 
		System.out.println("Write the names of your ingredients separated by spaces: ");
		System.out.println("(For ingredients with two words, write just the first word)");
		
		for(int i = 0; i < ingredNum; i++) {
			ingredients[i] = in.next();
		}
		
		
			
	try {	
		JSONObject recipesOne = (JSONObject) new JSONParser().parse(new FileReader("recipes_raw_nosource_ar.json"));
		JSONObject recipesTwo = (JSONObject) new JSONParser().parse(new FileReader("recipes_raw_nosource_epi.json"));
		JSONObject recipesThree = (JSONObject) new JSONParser().parse(new FileReader("recipes_raw_nosource_fn.json"));
		
		
		
		ArrayList<JSONObject> recipesList = new ArrayList<JSONObject>(); 
			
			Set<String> oneS = recipesOne.keySet();
				for(String key : oneS) {
					recipesList.add((JSONObject) recipesOne.get(key));
				}
			
				
				Set<String> twoS = recipesTwo.keySet();
			for(String key : twoS) {
				recipesList.add((JSONObject) recipesTwo.get(key));
			}
			
			
			Set<String> threeS = recipesThree.keySet();
			for(String key : threeS) {
				recipesList.add((JSONObject) recipesThree.get(key));
			}
		
		ArrayList<JSONObject> specificRecipes = new ArrayList<JSONObject>();
				
		
			for(int i = 0; i < recipesList.size(); i++) {
				checkRecipe(recipesList.get(i), ingredients, specificRecipes);
			}
	
		System.out.println();	
		for(int i = 0; i < specificRecipes.size(); i ++) {
				
			System.out.println(i+1 + ": " + ((String) (specificRecipes.get(i).get("title"))));
		}
		
		if(specificRecipes.size() == 0) {
			System.out.println("Sorry, there were no recipes found with your combination of ingredients.");
			System.exit(0);
		}
		
		System.out.println();
		System.out.println("Enter the number of the recipe you would like to make: ");
		int recipeNum = in.nextInt();
		
		
		System.out.println();
		System.out.println(specificRecipes.get(recipeNum-1).get("title"));
		
		System.out.println();
		System.out.println("Ingredients: ");
		ArrayList<String> specIngredients = (ArrayList<String>) specificRecipes.get(recipeNum-1).get("ingredients");
			for(String s : specIngredients) {
				System.out.println(s.replace("ADVERTISEMENT", ""));
			}
		
		System.out.println();
		System.out.println("Instructions: ");
		System.out.println((String) specificRecipes.get(recipeNum-1).get("instructions"));
		
	}catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    }
	
	in.close();
	}
	
	public static void checkRecipe(JSONObject recipe, String[] ingredients, ArrayList<JSONObject> specificRecipes) {
		
		
		int counter = 0;
		int ingredNum = ingredients.length; 
		for(int i = 0; i < ingredNum; i++) {
			String ingreds = (String) recipe.get("instructions");
		if(ingreds != null) {
			if(ingreds.contains(ingredients[i])) {
				counter++;
			}
		}
		if(counter == ingredNum) {
			specificRecipes.add(recipe);
			}
		}	
	}
}


