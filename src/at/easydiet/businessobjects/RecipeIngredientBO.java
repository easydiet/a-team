package at.easydiet.businessobjects;

import at.easydiet.model.RecipeIngredient;

/**
 * This class encapsules a RecipeIngredient instance.
 */
public class RecipeIngredientBO 
{
	private RecipeIngredient _model;
	
    /**
     * Initializes a new instance of the {@link RecipeIngredientBO} class.
     */
	public RecipeIngredientBO()
	{
		// TODO: add default values
		this(new RecipeIngredient());
	}
	
    /**
     * Initializes a new instance of the {@link RecipeIngredientBO} class.
     * @param model the original model object
     */
	public RecipeIngredientBO(RecipeIngredient model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link RecipeIngredient} object.
	 */
 	public RecipeIngredient getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the recipeIngredientId of this instance. 
     * @return the recipeIngredientId currently set for this instance.
     */
    public long getRecipeIngredientId() 
    {
        return _model.getRecipeIngredientId();
    }
    
    /**       
     * Sets the recipeIngredientId of this instance. 
     * @param recipeIngredientId the new recipeIngredientId of this instance.
     */    
    public void setRecipeIngredientId(long recipeIngredientId) 
    {
        _model.setRecipeIngredientId(recipeIngredientId);
    }

    /**       
     * Gets the amount of this instance. 
     * @return the amount currently set for this instance.
     */
    public float getAmount() 
    {
        return _model.getAmount();
    }
    
    /**       
     * Sets the amount of this instance. 
     * @param amount the new amount of this instance.
     */    
    public void setAmount(float amount) 
    {
        _model.setAmount(amount);
    }

	
    private RecipeBO _ingredient;
    
    /**
     * Gets the currently referenced Ingredient of this instance.
     * @return the Recipe currently referenced in this RecipeIngredient. 
     */
    public RecipeBO getIngredient()
    {
        if(_ingredient == null)
        {
            _ingredient = new RecipeBO(_model.getIngredient());
        }
        return _ingredient;
    }
    
    /**
     * Sets the Ingredient to be referenced in this instance
     * @param ingredient the Recipe to reference in this RecipeIngredient. 
     */
    public void setIngredient(RecipeBO ingredient)
    {
        _ingredient = ingredient;
        _model.setIngredient(ingredient.getModel());
    }
	
    private RecipeBO _recipe;
    
    /**
     * Gets the currently referenced Recipe of this instance.
     * @return the Recipe currently referenced in this RecipeIngredient. 
     */
    public RecipeBO getRecipe()
    {
        if(_recipe == null)
        {
            _recipe = new RecipeBO(_model.getRecipe());
        }
        return _recipe;
    }
    
    /**
     * Sets the Recipe to be referenced in this instance
     * @param recipe the Recipe to reference in this RecipeIngredient. 
     */
    public void setRecipe(RecipeBO recipe)
    {
        _recipe = recipe;
        _model.setRecipe(recipe.getModel());
    }
}