package at.easydiet.model;


/**
 * Represents a RecipeIngredient
 */
public class RecipeIngredient  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = 8618320415928998257L;
    private long _recipeIngredientId;
    private float _amount;
    private Recipe _ingredient;

    /**
     * Initializes a new instance of the {@link RecipeIngredient} class.
     */
    public RecipeIngredient() 
    {
        // no initialization
    }


    /**
     * Initializes a new instance of the {@link RecipeIngredient} class.
     * @param amount the amount to set for this instance
     * @param ingredient the ingredient to set for this instance
     */
    public RecipeIngredient(float amount, Recipe ingredient) 
    {
       _amount = amount;
       _ingredient = ingredient;
    }
   
    /**       
     * Gets the recipeIngredientId of this instance. 
     * @return the recipeIngredientId currently set for this instance.
     */
    public long getRecipeIngredientId() 
    {
        return _recipeIngredientId;
    }
    
    /**       
     * Sets the recipeIngredientId of this instance. 
     * @param recipeIngredientId the new recipeIngredientId of this instance.
     */    
    public void setRecipeIngredientId(long recipeIngredientId) 
    {
        _recipeIngredientId = recipeIngredientId;
    }
    
    /**       
     * Gets the amount of this instance. 
     * @return the amount currently set for this instance.
     */
    public float getAmount() 
    {
        return _amount;
    }
    
    /**       
     * Sets the amount of this instance. 
     * @param amount the new amount of this instance.
     */    
    public void setAmount(float amount) 
    {
        _amount = amount;
    }
    
    /**       
     * Gets the ingredient of this instance. 
     * @return the ingredient currently set for this instance.
     */
    public Recipe getIngredient() 
    {
        return _ingredient;
    }
    
    /**       
     * Sets the ingredient of this instance. 
     * @param ingredient the new ingredient of this instance.
     */    
    public void setIngredient(Recipe ingredient) 
    {
        _ingredient = ingredient;
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
