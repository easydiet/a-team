package at.easydiet.businessobjects;


import java.sql.Clob;

import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

import at.easydiet.model.NutrimentParameter;
import at.easydiet.model.Recipe;
import at.easydiet.model.RecipeIngredient;

/**
 * This class encapsules a Recipe instance.
 */
public class RecipeBO
{
	private Recipe _model;
	
    /**
     * Initializes a new instance of the {@link RecipeBO} class.
     */
	public RecipeBO()
	{
		// TODO: add default values
		this(new Recipe());
	}
	
    /**
     * Initializes a new instance of the {@link RecipeBO} class.
     * @param model the original model object
     */
	public RecipeBO(Recipe model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link Recipe} object.
	 */
 	public Recipe getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the recipeId of this instance. 
     * @return the recipeId currently set for this instance.
     */
    public long getRecipeId() 
    {
        return _model.getRecipeId();
    }
    
    /**       
     * Sets the recipeId of this instance. 
     * @param recipeId the new recipeId of this instance.
     */    
    public void setRecipeId(long recipeId) 
    {
        _model.setRecipeId(recipeId);
    }

    /**       
     * Gets the name of this instance. 
     * @return the name currently set for this instance.
     */
    public String getName() 
    {
        return _model.getName();
    }
    
    /**       
     * Sets the name of this instance. 
     * @param name the new name of this instance.
     */    
    public void setName(String name) 
    {
        _model.setName(name);
    }

    /**       
     * Gets the blsCode of this instance. 
     * @return the blsCode currently set for this instance.
     */
    public String getBlsCode() 
    {
        return _model.getBlsCode();
    }
    
    /**       
     * Sets the blsCode of this instance. 
     * @param blsCode the new blsCode of this instance.
     */    
    public void setBlsCode(String blsCode) 
    {
        _model.setBlsCode(blsCode);
    }

    /**       
     * Gets the difficulty of this instance. 
     * @return the difficulty currently set for this instance.
     */
    public int getDifficulty() 
    {
        return _model.getDifficulty();
    }
    
    /**       
     * Sets the difficulty of this instance. 
     * @param difficulty the new difficulty of this instance.
     */    
    public void setDifficulty(int difficulty) 
    {
        _model.setDifficulty(difficulty);
    }

    /**       
     * Gets the description of this instance. 
     * @return the description currently set for this instance.
     */
    public Clob getDescription() 
    {
        return _model.getDescription();
    }
    
    /**       
     * Sets the description of this instance. 
     * @param description the new description of this instance.
     */    
    public void setDescription(Clob description) 
    {
        _model.setDescription(description);
    }

    /**       
     * Gets the benefits of this instance. 
     * @return the benefits currently set for this instance.
     */
    public Clob getBenefits() 
    {
        return _model.getBenefits();
    }
    
    /**       
     * Sets the benefits of this instance. 
     * @param benefits the new benefits of this instance.
     */    
    public void setBenefits(Clob benefits) 
    {
        _model.setBenefits(benefits);
    }

    /**       
     * Gets the cookInstructions of this instance. 
     * @return the cookInstructions currently set for this instance.
     */
    public Clob getCookInstructions() 
    {
        return _model.getCookInstructions();
    }
    
    /**       
     * Sets the cookInstructions of this instance. 
     * @param cookInstructions the new cookInstructions of this instance.
     */    
    public void setCookInstructions(Clob cookInstructions) 
    {
        _model.setCookInstructions(cookInstructions);
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

	
    private ParameterDefinitionUnitBO _unit;
    
    /**
     * Gets the currently referenced Unit of this instance.
     * @return the ParameterDefinitionUnit currently referenced in this Recipe. 
     */
    public ParameterDefinitionUnitBO getUnit()
    {
        if(_unit == null)
        {
            _unit = new ParameterDefinitionUnitBO(_model.getUnit());
        }
        return _unit;
    }
    
    /**
     * Sets the Unit to be referenced in this instance
     * @param unit the ParameterDefinitionUnit to reference in this Recipe. 
     */
    public void setUnit(ParameterDefinitionUnitBO unit)
    {
        _unit = unit;
        _model.setUnit(unit.getModel());
    }

	private List<RecipeIngredientBO> _ingredients;
	
    /**
     * Gets a list of referenced Ingredients of this instance.
     * This list is cached, use {@link Recipe#updateIngredientsCache()) to update this cache.
     * @return a cached list of referenced Ingredients wrapped into the correct businessobject. 
     */
    public List<RecipeIngredientBO> getIngredients()
    {
        if(_ingredients == null) 
        {
            _ingredients = new ArrayList<RecipeIngredientBO>();
            for(RecipeIngredient ingredients : _model.getIngredients())
            {
                _ingredients.add(new RecipeIngredientBO(ingredients));
            }
        }
        return _ingredients;
    }
	
    /**
     * Adds a new RecipeIngredient to the list of referenced ingredients.
     * The cache will updated
     * @param ingredients the RecipeIngredient to add. 
     */
    public void addIngredients(RecipeIngredientBO ingredients)
    {
        getIngredients().add(ingredients);
        _model.getIngredients().add(ingredients.getModel());
    }
    
        
    /**
     * Removes the given RecipeIngredient from the list of referenced ingredients.
     * The cache will updated
     * @param ingredients the timespan to add. 
     */
    public void removeIngredients(RecipeIngredientBO ingredients)
    {
        getIngredients().remove(ingredients);
        _model.getIngredients().remove(ingredients.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced ingredients.
     */
    public void updateIngredientsCache()
    {
        _ingredients = null;
        getIngredients();
    }


	private List<NutrimentParameterBO> _nutrimentParameters;
	
    /**
     * Gets a list of referenced NutrimentParameters of this instance.
     * This list is cached, use {@link Recipe#updateNutrimentParametersCache()) to update this cache.
     * @return a cached list of referenced NutrimentParameters wrapped into the correct businessobject. 
     */
    public List<NutrimentParameterBO> getNutrimentParameters()
    {
        if(_nutrimentParameters == null) 
        {
            _nutrimentParameters = new ArrayList<NutrimentParameterBO>();
            for(NutrimentParameter nutrimentParameters : _model.getNutrimentParameters())
            {
                _nutrimentParameters.add(new NutrimentParameterBO(nutrimentParameters));
            }
        }
        return _nutrimentParameters;
    }
	
    /**
     * Adds a new NutrimentParameter to the list of referenced nutrimentParameters.
     * The cache will updated
     * @param nutrimentParameters the NutrimentParameter to add. 
     */
    public void addNutrimentParameters(NutrimentParameterBO nutrimentParameters)
    {
        getNutrimentParameters().add(nutrimentParameters);
        _model.getNutrimentParameters().add(nutrimentParameters.getModel());
    }
    
        
    /**
     * Removes the given NutrimentParameter from the list of referenced nutrimentParameters.
     * The cache will updated
     * @param nutrimentParameters the timespan to add. 
     */
    public void removeNutrimentParameters(NutrimentParameterBO nutrimentParameters)
    {
        getNutrimentParameters().remove(nutrimentParameters);
        _model.getNutrimentParameters().remove(nutrimentParameters.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced nutrimentParameters.
     */
    public void updateNutrimentParametersCache()
    {
        _nutrimentParameters = null;
        getNutrimentParameters();
    }

}