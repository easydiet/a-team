package at.easydiet.model;

import java.sql.Clob;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a MealLine
 */
public class MealLine  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = -2308968084611486091L;
    private long _mealLineId;
    private float _quantity;
    private Clob _info;
    private Set<MealLine> _mealLines = new HashSet<MealLine>(0);
    private Recipe _recipe;
    private Meal _meal;

    /**
     * Initializes a new instance of the {@link MealLine} class.
     */
    public MealLine() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link MealLine} class.
     * @param quantity the quantity to set for this instance
     * @param recipe the recipe to set for this instance
     * @param meal the meal to set for this instance
     */
    public MealLine(float quantity, Recipe recipe, Meal meal) 
    {
        _quantity = quantity;
        _recipe = recipe;
        _meal = meal;
    }

    /**
     * Initializes a new instance of the {@link MealLine} class.
     * @param quantity the quantity to set for this instance
     * @param info the info to set for this instance
     * @param mealLines the mealLines to set for this instance
     * @param recipe the recipe to set for this instance
     * @param meal the meal to set for this instance
     */
    public MealLine(float quantity, Clob info, Set<MealLine> mealLines, Recipe recipe, Meal meal) 
    {
       _quantity = quantity;
       _info = info;
       _mealLines = mealLines;
       _recipe = recipe;
       _meal = meal;
    }
   
    /**       
     * Gets the mealLineId of this instance. 
     * @return the mealLineId currently set for this instance.
     */
    public long getMealLineId() 
    {
        return _mealLineId;
    }
    
    /**       
     * Sets the mealLineId of this instance. 
     * @param mealLineId the new mealLineId of this instance.
     */    
    public void setMealLineId(long mealLineId) 
    {
        _mealLineId = mealLineId;
    }
    
    /**       
     * Gets the quantity of this instance. 
     * @return the quantity currently set for this instance.
     */
    public float getQuantity() 
    {
        return _quantity;
    }
    
    /**       
     * Sets the quantity of this instance. 
     * @param quantity the new quantity of this instance.
     */    
    public void setQuantity(float quantity) 
    {
        _quantity = quantity;
    }
    
    /**       
     * Gets the info of this instance. 
     * @return the info currently set for this instance.
     */
    public Clob getInfo() 
    {
        return _info;
    }
    
    /**       
     * Sets the info of this instance. 
     * @param info the new info of this instance.
     */    
    public void setInfo(Clob info) 
    {
        _info = info;
    }
    
    /**       
     * Gets the mealLines of this instance. 
     * @return the mealLines currently set for this instance.
     */
    public Set<MealLine> getMealLines() 
    {
        return _mealLines;
    }
    
    /**       
     * Sets the mealLines of this instance. 
     * @param mealLines the new mealLines of this instance.
     */    
    public void setMealLines(Set<MealLine> mealLines) 
    {
        _mealLines = mealLines;
    }
    
    /**       
     * Gets the recipe of this instance. 
     * @return the recipe currently set for this instance.
     */
    public Recipe getRecipe() 
    {
        return _recipe;
    }
    
    /**       
     * Sets the recipe of this instance. 
     * @param recipe the new recipe of this instance.
     */    
    public void setRecipe(Recipe recipe) 
    {
        _recipe = recipe;
    }
    
    /**       
     * Gets the meal of this instance. 
     * @return the meal currently set for this instance.
     */
    public Meal getMeal() 
    {
        return _meal;
    }
    
    /**       
     * Sets the meal of this instance. 
     * @param meal the new meal of this instance.
     */    
    public void setMeal(Meal meal) 
    {
        _meal = meal;
    }
    
    /**
     * Returns a string representation of this instance.
     * @return a string
     */
    @Override
    public String toString() 
    {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		// interesting values
        builder.append("]");
      
        return builder.toString();
    }
}
