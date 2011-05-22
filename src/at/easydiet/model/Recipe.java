package at.easydiet.model;

import java.sql.Clob;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Recipe
 */
public class Recipe  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = 8149552592327951828L;
    private long _recipeId;
    private String _name;
    private String _blsCode;
    private int _difficulty;
    private Clob _description;
    private Clob _benefits;
    private Clob _cookInstructions;
    private float _amount;
    private ParameterDefinitionUnit _unit;
    private Set<RecipeIngredient> _ingredients = new HashSet<RecipeIngredient>(0);
    private Set<NutrimentParameter> _nutrimentParameters = new HashSet<NutrimentParameter>(0);

    /**
     * Initializes a new instance of the {@link Recipe} class.
     */
    public Recipe() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link Recipe} class.
     * @param difficulty the difficulty to set for this instance
     */
    public Recipe(int difficulty) 
    {
        _difficulty = difficulty;
    }
    
    /**
     * Initializes a new instance of the {@link Recipe} class.
     * @param name the name to set for this instance
     * @param blsCode the blsCode to set for this instance
     * @param difficulty the difficulty to set for this instance
     * @param description the description to set for this instance
     * @param benefits the benefits to set for this instance
     * @param cookInstructions the cookInstructions to set for this instance
     * @param amount the amount to set for this instance
     * @param unit the unit to set for this instance
     */
    public Recipe(String name, String blsCode, int difficulty, Clob description, Clob benefits, Clob cookInstructions, float amount, ParameterDefinitionUnit unit) 
    {
        _name = name;
        _blsCode = blsCode;
        _difficulty = difficulty;
        _description = description;
        _benefits = benefits;
        _cookInstructions = cookInstructions;
        _amount = amount;
        _unit = unit;
    }
    

    /**
     * Initializes a new instance of the {@link Recipe} class.
     * @param name the name to set for this instance
     * @param blsCode the blsCode to set for this instance
     * @param difficulty the difficulty to set for this instance
     * @param description the description to set for this instance
     * @param benefits the benefits to set for this instance
     * @param cookInstructions the cookInstructions to set for this instance
     * @param amount the amount to set for this instance
     * @param unit the unit to set for this instance
     * @param ingredients the ingredients to set for this instance
     * @param nutrimentParameters the nutrimentParameters to set for this instance
     */
    public Recipe(String name, String blsCode, int difficulty, Clob description, Clob benefits, Clob cookInstructions, float amount, ParameterDefinitionUnit unit, Set<RecipeIngredient> ingredients, Set<NutrimentParameter> nutrimentParameters) 
    {
       _name = name;
       _blsCode = blsCode;
       _difficulty = difficulty;
       _description = description;
       _benefits = benefits;
       _cookInstructions = cookInstructions;
       _amount = amount;
       _unit = unit;
       _ingredients = ingredients;
       _nutrimentParameters = nutrimentParameters;
    }
   
    /**       
     * Gets the recipeId of this instance. 
     * @return the recipeId currently set for this instance.
     */
    public long getRecipeId() 
    {
        return _recipeId;
    }
    
    /**       
     * Sets the recipeId of this instance. 
     * @param recipeId the new recipeId of this instance.
     */    
    public void setRecipeId(long recipeId) 
    {
        _recipeId = recipeId;
    }
    
    /**       
     * Gets the name of this instance. 
     * @return the name currently set for this instance.
     */
    public String getName() 
    {
        return _name;
    }
    
    /**       
     * Sets the name of this instance. 
     * @param name the new name of this instance.
     */    
    public void setName(String name) 
    {
        _name = name;
    }
    
    /**       
     * Gets the blsCode of this instance. 
     * @return the blsCode currently set for this instance.
     */
    public String getBlsCode() 
    {
        return _blsCode;
    }
    
    /**       
     * Sets the blsCode of this instance. 
     * @param blsCode the new blsCode of this instance.
     */    
    public void setBlsCode(String blsCode) 
    {
        _blsCode = blsCode;
    }
    
    /**       
     * Gets the difficulty of this instance. 
     * @return the difficulty currently set for this instance.
     */
    public int getDifficulty() 
    {
        return _difficulty;
    }
    
    /**       
     * Sets the difficulty of this instance. 
     * @param difficulty the new difficulty of this instance.
     */    
    public void setDifficulty(int difficulty) 
    {
        _difficulty = difficulty;
    }
    
    /**       
     * Gets the description of this instance. 
     * @return the description currently set for this instance.
     */
    public Clob getDescription() 
    {
        return _description;
    }
    
    /**       
     * Sets the description of this instance. 
     * @param description the new description of this instance.
     */    
    public void setDescription(Clob description) 
    {
        _description = description;
    }
    
    /**       
     * Gets the benefits of this instance. 
     * @return the benefits currently set for this instance.
     */
    public Clob getBenefits() 
    {
        return _benefits;
    }
    
    /**       
     * Sets the benefits of this instance. 
     * @param benefits the new benefits of this instance.
     */    
    public void setBenefits(Clob benefits) 
    {
        _benefits = benefits;
    }
    
    /**       
     * Gets the cookInstructions of this instance. 
     * @return the cookInstructions currently set for this instance.
     */
    public Clob getCookInstructions() 
    {
        return _cookInstructions;
    }
    
    /**       
     * Sets the cookInstructions of this instance. 
     * @param cookInstructions the new cookInstructions of this instance.
     */    
    public void setCookInstructions(Clob cookInstructions) 
    {
        _cookInstructions = cookInstructions;
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
     * Gets the unit of this instance. 
     * @return the unit currently set for this instance.
     */
    public ParameterDefinitionUnit getUnit() 
    {
        return _unit;
    }
    
    /**       
     * Sets the unit of this instance. 
     * @param unit the new unit of this instance.
     */    
    public void setUnit(ParameterDefinitionUnit unit) 
    {
        _unit = unit;
    }
    
    /**       
     * Gets the ingredients of this instance. 
     * @return the ingredients currently set for this instance.
     */
    public Set<RecipeIngredient> getIngredients() 
    {
        return _ingredients;
    }
    
    /**       
     * Sets the ingredients of this instance. 
     * @param ingredients the new ingredients of this instance.
     */    
    public void setIngredients(Set<RecipeIngredient> ingredients) 
    {
        _ingredients = ingredients;
    }
    
    /**       
     * Gets the nutrimentParameters of this instance. 
     * @return the nutrimentParameters currently set for this instance.
     */
    public Set<NutrimentParameter> getNutrimentParameters() 
    {
        return _nutrimentParameters;
    }
    
    /**       
     * Sets the nutrimentParameters of this instance. 
     * @param nutrimentParameters the new nutrimentParameters of this instance.
     */    
    public void setNutrimentParameters(Set<NutrimentParameter> nutrimentParameters) 
    {
        _nutrimentParameters = nutrimentParameters;
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
