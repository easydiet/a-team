package at.easydiet.businessobjects;

import java.sql.Clob;
import java.util.Set;

import at.easydiet.model.NutrimentParameter;
import at.easydiet.model.ParameterDefinitionUnit;
import at.easydiet.model.Recipe;
import at.easydiet.model.RecipeIngredient;

public class RecipeBO
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(RecipeBO.class);
    
    private Recipe _recipe;

    /**
     * Gets the recipe.
     * @return the recipe
     */
    public Recipe getRecipe()
    {
        return _recipe;
    }

    /** 
     * Initializes a new instance of the {@link RecipeBO} class. 
     * @param recipe
     */
    public RecipeBO(Recipe recipe)
    {
        super();
        _recipe = recipe;
    }

    /**
     * @return
     * @see at.easydiet.model.Recipe#getRecipeId()
     */
    public long getRecipeId()
    {
        return _recipe.getRecipeId();
    }

    /**
     * @param recipeId
     * @see at.easydiet.model.Recipe#setRecipeId(long)
     */
    public void setRecipeId(long recipeId)
    {
        _recipe.setRecipeId(recipeId);
    }

    /**
     * @return
     * @see at.easydiet.model.Recipe#getName()
     */
    public String getName()
    {
        return _recipe.getName();
    }

    /**
     * @param name
     * @see at.easydiet.model.Recipe#setName(java.lang.String)
     */
    public void setName(String name)
    {
        _recipe.setName(name);
    }

    /**
     * @return
     * @see at.easydiet.model.Recipe#getBlsCode()
     */
    public String getBlsCode()
    {
        return _recipe.getBlsCode();
    }

    /**
     * @param blsCode
     * @see at.easydiet.model.Recipe#setBlsCode(java.lang.String)
     */
    public void setBlsCode(String blsCode)
    {
        _recipe.setBlsCode(blsCode);
    }

    /**
     * @return
     * @see at.easydiet.model.Recipe#getDifficulty()
     */
    public int getDifficulty()
    {
        return _recipe.getDifficulty();
    }

    /**
     * @param difficulty
     * @see at.easydiet.model.Recipe#setDifficulty(int)
     */
    public void setDifficulty(int difficulty)
    {
        _recipe.setDifficulty(difficulty);
    }

    /**
     * @return
     * @see at.easydiet.model.Recipe#getDescription()
     */
    public Clob getDescription()
    {
        return _recipe.getDescription();
    }

    /**
     * @param description
     * @see at.easydiet.model.Recipe#setDescription(java.sql.Clob)
     */
    public void setDescription(Clob description)
    {
        _recipe.setDescription(description);
    }

    /**
     * @return
     * @see at.easydiet.model.Recipe#getBenefits()
     */
    public Clob getBenefits()
    {
        return _recipe.getBenefits();
    }

    /**
     * @param benefits
     * @see at.easydiet.model.Recipe#setBenefits(java.sql.Clob)
     */
    public void setBenefits(Clob benefits)
    {
        _recipe.setBenefits(benefits);
    }

    /**
     * @return
     * @see at.easydiet.model.Recipe#getCookInstructions()
     */
    public Clob getCookInstructions()
    {
        return _recipe.getCookInstructions();
    }

    /**
     * @param cookInstructions
     * @see at.easydiet.model.Recipe#setCookInstructions(java.sql.Clob)
     */
    public void setCookInstructions(Clob cookInstructions)
    {
        _recipe.setCookInstructions(cookInstructions);
    }

    /**
     * @return
     * @see at.easydiet.model.Recipe#getAmount()
     */
    public float getAmount()
    {
        return _recipe.getAmount();
    }

    /**
     * @param amount
     * @see at.easydiet.model.Recipe#setAmount(float)
     */
    public void setAmount(float amount)
    {
        _recipe.setAmount(amount);
    }

    /**
     * @return
     * @see at.easydiet.model.Recipe#getUnit()
     */
    public ParameterDefinitionUnit getUnit()
    {
        return _recipe.getUnit();
    }

    /**
     * @param unit
     * @see at.easydiet.model.Recipe#setUnit(at.easydiet.model.ParameterDefinitionUnit)
     */
    public void setUnit(ParameterDefinitionUnit unit)
    {
        _recipe.setUnit(unit);
    }

    /**
     * @return
     * @see at.easydiet.model.Recipe#getIngredients()
     */
    public Set<RecipeIngredient> getIngredients()
    {
        return _recipe.getIngredients();
    }

    /**
     * @param ingredients
     * @see at.easydiet.model.Recipe#setIngredients(java.util.Set)
     */
    public void setIngredients(Set<RecipeIngredient> ingredients)
    {
        _recipe.setIngredients(ingredients);
    }

    /**
     * @return
     * @see at.easydiet.model.Recipe#getNutrimentParameters()
     */
    public Set<NutrimentParameter> getNutrimentParameters()
    {
        return _recipe.getNutrimentParameters();
    }

    /**
     * @param nutrimentParameters
     * @see at.easydiet.model.Recipe#setNutrimentParameters(java.util.Set)
     */
    public void setNutrimentParameters(
            Set<NutrimentParameter> nutrimentParameters)
    {
        _recipe.setNutrimentParameters(nutrimentParameters);
    }
    
    @Override
    public String toString()
    {
        return getName();
    }
    
    
}
